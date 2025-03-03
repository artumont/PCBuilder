'use client'

import Image from "next/image"
import { motion } from "motion/react"
import { useEffect, useState } from 'react';
import { useSearchParams, useRouter } from 'next/navigation';
import HardwarePicker from "./builder/HardwarePicker";
import { fetchHardwareByName } from "./builder/ApiUtils";
import { HardwareType } from "./builder/Types";

export type Build = {
    cpu: string,
    gpu: string,
    ram: string,
    storage: string,
    motherboard: string,
    psu: string,
    cooling: string
    case: string,
    monitor?: string
    [key: string]: string | undefined
}

export interface ConfigUrlInfo {
    url: string;
    config: string;
    build: Build;
}

export function getConfigUrlInfo(build: Build): ConfigUrlInfo {
    const config = btoa(JSON.stringify(build));
    const url = `${window.location.origin}${window.location.pathname}?config=${config}`;
    return { url, config, build };
}

interface Hardware {
    name: string;
    id: number;
    price: number;
    imageUrl: string;
}

export default function BuilderContent() {
    const [selectedType, setSelectedType] = useState<HardwareType | null>(null);
    const [showPicker, setShowPicker] = useState(false);
    const [totalPrice, setTotalPrice] = useState(0);
    const [isLoadingPrice, setIsLoadingPrice] = useState(false);
    const [selectedPrices, setSelectedPrices] = useState<Record<string, number>>({});
    const searchParams = useSearchParams();
    const router = useRouter();

    const buttonVariants = {
        hidden: { opacity: 0, y: 20 },
        visible: (i: number) => ({
            opacity: 1,
            y: 0,
            transition: {
                delay: i * 0.2,
                duration: 0.5
            }
        }),
        hover: {
            scale: 1.02,
            transition: {
                duration: 0.2
            }
        },
        tap: {
            scale: 0.97,
            transition: {
                duration: 0.2
            }
        }
    }

    const [currentBuild, setCurrentBuild] = useState<Build>({
        cpu: '',
        gpu: '',
        ram: '',
        storage: '',
        motherboard: '',
        psu: '',
        case: '',
        cooling: '',
        monitor: ''
    });

    const fetchPricesForBuild = async (build: Build) => {
        if (Object.values(build).every(v => !v)) return;
        
        setIsLoadingPrice(true);
        const components: { type: HardwareType; name: string; }[] = [
            { type: 'cpu', name: build.cpu },
            { type: 'gpu', name: build.gpu },
            { type: 'ram', name: build.ram },
            { type: 'storage', name: build.storage },
            { type: 'motherboard', name: build.motherboard },
            { type: 'psu', name: build.psu },
            { type: 'case', name: build.case },
            { type: 'cooler', name: build.cooling }
        ];

        if (build.monitor) {
            components.push({ type: 'monitor', name: build.monitor });
        }

        const newSelectedPrices: Record<string, number> = {};
        let total = 0;

        try {
            for (const component of components) {
                if (component.name) {
                    try {
                        const hardware = await fetchHardwareByName(component.type, component.name);
                        const key = component.type === 'cooler' ? 'cooling' : component.type;
                        newSelectedPrices[key] = hardware.price;
                        total += hardware.price;
                    } catch (error) {
                        console.error(`Failed to fetch price for ${component.type}:`, error);
                    }
                }
            }

            setSelectedPrices(newSelectedPrices);
            setTotalPrice(total);
        } finally {
            setIsLoadingPrice(false);
        }
    };

    useEffect(() => {
        const config = searchParams.get("config")
        if (config) {
            try {
                const build = JSON.parse(atob(config)) as Build;
                setCurrentBuild(prev => ({
                    ...prev,
                    ...build
                }));
                fetchPricesForBuild(build);
            } catch (e) {
                console.error("Failed to parse config:", e);
            }
        }
    }, [searchParams])

    const handleSelect = (hardware: Hardware) => {
        const buildKey = selectedType === 'cooler' ? 'cooling' : selectedType as string;
        
        const newBuild = {
            ...currentBuild,
            [buildKey]: hardware.name
        };
        
        const newSelectedPrices = {
            ...selectedPrices,
            [buildKey]: hardware.price
        };
        setSelectedPrices(newSelectedPrices);
        setTotalPrice(Object.values(newSelectedPrices).reduce((sum, price) => sum + price, 0));
        
        setCurrentBuild(newBuild);
        const config = btoa(JSON.stringify(newBuild));

        const params = new URLSearchParams(window.location.search);
        params.set('config', config);
        router.push(`?${params.toString()}`);

        setShowPicker(false);
    };

    const handleShareConfig = async () => {
        const requiredParts = ['cpu', 'gpu', 'ram', 'storage', 'motherboard', 'psu', 'case', 'cooling'];
        const missingParts = requiredParts.filter(part => !currentBuild[part]);

        if (missingParts.length > 0) {
            alert(`Please select all required components: ${missingParts.join(', ')}`);
            return;
        }

        try {
            const config = btoa(JSON.stringify(currentBuild));
            const baseUrl = window.location.origin + window.location.pathname;
            const url = `${baseUrl}?config=${config}`;
            await navigator.clipboard.writeText(url);
            alert('Configuration URL copied to clipboard!');
        } catch (error) {
            console.error('Failed to save configuration:', error);
            alert('Failed to save configuration. Please try again.');
        }
    };

    const getCurrentConfigUrl = () => {
        const config = btoa(JSON.stringify(currentBuild));
        const baseUrl = window.location.origin + window.location.pathname;
        return `${baseUrl}?config=${config}`;
    };

    const handleOpenPicker = (type: HardwareType) => {
        setSelectedType(type);
        setShowPicker(true);
    };

    return (
        <div className="w-full mt-32 lg:mt-5">
            <motion.div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
                <motion.button 
                    className='flex p-2 rounded-lg h-60 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    custom={0}
                    initial="hidden"
                    animate="visible"
                    whileHover="hover"
                    whileTap="tap"
                    onClick={() => handleOpenPicker('cpu')}
                >
                    <div className="flex items-center ml-10">
                        <Image 
                            className="w-24 h-24 mr-5 brightness-0 dark:brightness-100 dark:invert" 
                            src="/assets/builder/cpu.svg" 
                            alt="CPU" 
                            width={96} 
                            height={96} 
                        />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">CPU</h1>
                            <h2 className="text-sm">{currentBuild.cpu || "Select a CPU"}</h2>
                        </div>
                    </div>
                </motion.button>

                <motion.button 
                    className='flex p-2 rounded-lg h-60 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    custom={1}
                    initial="hidden"
                    animate="visible"
                    whileHover="hover"
                    whileTap="tap"
                    onClick={() => handleOpenPicker('gpu')}
                >
                    <div className="flex items-center ml-10">
                    <Image 
                        className="w-24 h-24 mr-5 brightness-0 dark:brightness-100 dark:invert" 
                        src="/assets/builder/gpu.svg" 
                        alt="GPU" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">GPU</h1>
                            <h2 className="text-sm">{currentBuild.gpu || "Select a GPU"}</h2>
                        </div>
                    </div>
                </motion.button>

                <motion.button 
                    className='flex p-2 rounded-lg h-60 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    custom={2}
                    initial="hidden"
                    animate="visible"
                    whileHover="hover"
                    whileTap="tap"
                    onClick={() => handleOpenPicker('ram')}
                >
                    <div className="flex items-center ml-10">
                    <Image 
                        className="w-24 h-24 mr-5 brightness-0 dark:brightness-100 dark:invert" 
                        src="/assets/builder/ram.svg" 
                        alt="RAM" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">RAM</h1>
                            <h2 className="text-sm">{currentBuild.ram || "Select the RAM"}</h2>
                        </div>
                    </div>
                </motion.button>

                <motion.button 
                    className='flex p-2 rounded-lg h-60 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    custom={3}
                    initial="hidden"
                    animate="visible"
                    whileHover="hover"
                    whileTap="tap"
                    onClick={() => handleOpenPicker('storage')}
                >
                    <div className="flex items-center ml-10">
                    <Image 
                        className="w-24 h-24 mr-5 brightness-0 dark:brightness-100 dark:invert" 
                        src="/assets/builder/ssd.svg" 
                        alt="SSD" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl hidden lg:block">Storage</h1>
                            <h1 className="text-4xl lg:hidden">SSD</h1>
                            <h2 className="text-sm">{currentBuild.storage || "Select the storage"}</h2>
                        </div>
                    </div>
                </motion.button>

                <motion.button 
                    className='flex p-2 rounded-lg h-60 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    custom={4}
                    initial="hidden"
                    animate="visible"
                    whileHover="hover"
                    whileTap="tap"
                    onClick={() => handleOpenPicker('motherboard')}
                >
                    <div className="flex items-center ml-10">
                    <Image 
                        className="w-24 h-24 mr-5 brightness-0 dark:brightness-100 dark:invert" 
                        src="/assets/builder/mobo.svg" 
                        alt="MOBO" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl hidden lg:block">Motherboard</h1>
                            <h1 className="text-4xl lg:hidden">Mobo</h1>
                            <h2 className="text-sm">{currentBuild.motherboard || "Select a motherboard"}</h2>
                        </div>
                    </div>
                </motion.button>

                <motion.button 
                    className='flex p-2 rounded-lg h-60 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    custom={5}
                    initial="hidden"
                    animate="visible"
                    whileHover="hover"
                    whileTap="tap"
                    onClick={() => handleOpenPicker('psu')}
                >
                    <div className="flex items-center ml-10">
                    <Image 
                        className="w-24 h-24 mr-5 brightness-0 dark:brightness-100 dark:invert" 
                        src="/assets/builder/psu.svg" 
                        alt="PSU" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">PSU</h1>
                            <h2 className="text-sm">{currentBuild.psu || "Select a PSU"}</h2>
                        </div>
                    </div>
                </motion.button>

                <motion.button 
                    className='flex p-2 rounded-lg h-60 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    custom={6}
                    initial="hidden"
                    animate="visible"
                    whileHover="hover"
                    whileTap="tap"
                    onClick={() => handleOpenPicker('cooler')}
                >
                    <div className="flex items-center ml-10">
                    <Image 
                        className="w-24 h-24 mr-5 brightness-0 dark:brightness-100 dark:invert" 
                        src="/assets/builder/fan.svg" 
                        alt="FAN" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl hidden lg:block">Cooling</h1>
                            <h1 className="text-4xl lg:hidden">AIO</h1>
                            <h2 className="text-sm">{currentBuild.cooling || "Select a cooling system"}</h2>
                        </div>
                    </div>
                </motion.button>

                <motion.button 
                    className='flex p-2 rounded-lg h-60 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    custom={7}
                    initial="hidden"
                    animate="visible"
                    whileHover="hover"
                    whileTap="tap"
                    onClick={() => handleOpenPicker('case')}
                >
                    <div className="flex items-center ml-10">
                    <Image 
                        className="w-24 h-24 mr-5 brightness-0 dark:brightness-100 dark:invert" 
                        src="/assets/builder/case.svg" 
                        alt="CASE" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">Case</h1>
                            <h2 className="text-sm">{currentBuild.case || "Select a case"}</h2>
                        </div>
                    </div>
                </motion.button>

                <motion.button 
                    className='flex p-2 rounded-lg h-60 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    custom={8}
                    initial="hidden"
                    animate="visible"
                    whileHover="hover"
                    whileTap="tap"
                    onClick={() => handleOpenPicker('monitor')}
                >
                    <div className="flex items-center ml-10">
                    <Image 
                        className="w-24 h-24 mr-5 brightness-0 dark:brightness-100 dark:invert" 
                        src="/assets/builder/monitor.svg" 
                        alt="MONITOR" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl hidden lg:block">Monitor</h1>
                            <h1 className="text-4xl lg:hidden">LCD</h1>
                            <h2 className="text-sm">{currentBuild.monitor || "Select a monitor (optional)"}</h2>
                        </div>
                    </div>
                </motion.button>
            </motion.div>

            <motion.div 
                className="mt-8"
                variants={buttonVariants}
                custom={9}
                initial="hidden"
                animate="visible"
            >
                <div className="flex flex-col lg:flex-row gap-4 p-4 lg:p-6 rounded-lg bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary">
                    <div className="flex flex-row lg:flex-col justify-between items-center lg:items-start flex-1 p-4 lg:p-6">
                        <h2 className="text-xl lg:text-2xl font-semibold">Total Price</h2>
                        <p className="text-lg lg:mt-2">
                            {isLoadingPrice ? (
                                <motion.span 
                                    initial={{ opacity: 0.5 }}
                                    animate={{ opacity: 1 }}
                                    transition={{ repeat: Infinity, duration: 1, repeatType: "reverse" }}
                                >
                                    Loading...
                                </motion.span>
                            ) : (
                                `$${totalPrice.toFixed(2)}`
                            )}
                        </p>
                    </div>
                    <motion.button 
                        className="w-full lg:flex-1 p-4 lg:p-6 rounded-lg bg-green-500/30 hover:bg-green-500/50 border-[4px] border-green-500/80 font-semibold text-lg lg:text-xl transition-colors"
                        whileHover="hover"
                        whileTap="tap"
                        onClick={handleShareConfig}
                    >
                        Share Configuration
                    </motion.button>
                </div>
            </motion.div>

            {showPicker && selectedType && (
                <HardwarePicker 
                    type={selectedType}
                    onSelect={handleSelect}
                    onClose={() => setShowPicker(false)}
                />
            )}
        </div>
    )
}
