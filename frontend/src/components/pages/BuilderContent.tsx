'use client'

import Image from "next/image"
import { motion } from "motion/react"
import { useEffect, useState } from 'react';
import { useSearchParams, useRouter } from 'next/navigation';
import HardwarePicker from "./overlays/HardwarePicker";

type Build = {
    cpu: string,
    gpu: string,
    ram: string,
    storage: string,
    motherboard: string,
    psu: string,
    cooling: string
    case: string,
    monitor?: string
}

type HardwareType = 'cpu' | 'gpu' | 'ram' | 'storage' | 'motherboard' | 'psu' | 'case' | 'cooler' | 'monitor';

interface Hardware {
    name: string;
    id: number;
    price: number;
    imageUrl: string;
}

export default function BuilderContent() {
    const [selectedType, setSelectedType] = useState<HardwareType | null>(null);
    const [showPicker, setShowPicker] = useState(false);
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

    useEffect(() => {
        const config = searchParams.get("config")
        if (config) {
            try {
                const build = JSON.parse(atob(config)) as Build;
                setCurrentBuild(prev => ({
                    ...prev,
                    ...build
                }));
            } catch (e) {
                console.error("Failed to parse config:", e);
            }
        }
    }, [searchParams])

    const handleSelect = (hardware: Hardware) => {
        const newBuild = {
            ...currentBuild,
            [selectedType as string]: hardware.name
        };
        
        setCurrentBuild(newBuild);
        const config = btoa(JSON.stringify(newBuild));
        router.push(`?config=${config}`);

        setShowPicker(false);
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
                        alt="RAM" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">Storage</h1>
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
                        alt="RAM" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">Motherboard</h1>
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
                        alt="RAM" 
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
                        alt="RAM" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">Cooling</h1>
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
                        alt="RAM" 
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
                        alt="RAM" 
                        width={96} 
                        height={96} 
                    />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">Monitor</h1>
                            <h2 className="text-sm">{currentBuild.monitor || "Select a monitor (optional)"}</h2>
                        </div>
                    </div>
                </motion.button>
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
