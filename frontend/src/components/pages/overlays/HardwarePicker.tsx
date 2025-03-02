'use client'

import { motion, AnimatePresence } from "motion/react"
import { useState, useEffect } from "react"
import { HardwareType, Hardware } from './Types'
import { fetchHardware } from './ApiUtils'
import { useCPUFilters } from './CpuPicker'
import { useGPUFilters } from './GpuPicker'
import { useRAMFilters } from './RamPicker'
import { useMotherboardFilters } from './MotherboardPicker'
import { useStorageFilters } from './StoragePicker'
import { usePSUFilters } from './PsuPicker'
import { useCaseFilters } from './CasePicker'
import { useMonitorFilters } from './MonitorPicker'
import { useCoolerFilters } from './CoolerPicker'
import { overlayVariants, contentVariants, HardwareGrid } from './PickerComponents'

export default function HardwarePicker({ type, onSelect, onClose }: { 
    type: HardwareType; 
    onSelect: (hardware: Hardware) => void;
    onClose: () => void;
}) {
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const [hardware, setHardware] = useState<Hardware[]>([]);
    const [hasMore, setHasMore] = useState(true);

    const cpuFilters = useCPUFilters();
    const gpuFilters = useGPUFilters();
    const ramFilters = useRAMFilters();
    const motherboardFilters = useMotherboardFilters();
    const storageFilters = useStorageFilters();
    const psuFilters = usePSUFilters();
    const caseFilters = useCaseFilters();
    const monitorFilters = useMonitorFilters();
    const coolerFilters = useCoolerFilters();

    const { searchParams, setSearchParams } = (() => {
        switch (type) {
            case 'cpu': return cpuFilters;
            case 'gpu': return gpuFilters;
            case 'ram': return ramFilters;
            case 'motherboard': return motherboardFilters;
            case 'storage': return storageFilters;
            case 'psu': return psuFilters;
            case 'case': return caseFilters;
            case 'monitor': return monitorFilters;
            case 'cooler': return coolerFilters;
            default: return cpuFilters;
        }
    })();

    useEffect(() => {
        const loadHardware = async () => {
            try {
                setIsLoading(true);
                setError(null);
                const data = await fetchHardware(type, searchParams);
                
                if (searchParams.offset === 0) {
                    setHardware(data.hardwareList);
                } else {
                    setHardware(prev => [...prev, ...data.hardwareList]);
                }
                setHasMore(data.hardwareList.length === searchParams.limit);
            } catch (err) {
                setError('Failed to fetch hardware');
            } finally {
                setIsLoading(false);
            }
        };

        loadHardware();
    }, [type, searchParams]);

    const loadMore = () => {
        setSearchParams(prev => ({
            ...prev,
            offset: (prev.offset || 0) + prev.limit
        }));
    };

    const getFilters = () => {
        switch (type) {
            case 'cpu': return cpuFilters.Filters;
            case 'gpu': return gpuFilters.Filters;
            case 'ram': return ramFilters.Filters;
            case 'motherboard': return motherboardFilters.Filters;
            case 'storage': return storageFilters.Filters;
            case 'psu': return psuFilters.Filters;
            case 'case': return caseFilters.Filters;
            case 'monitor': return monitorFilters.Filters;
            case 'cooler': return coolerFilters.Filters;
            default: return null;
        }
    };

    return (
        <AnimatePresence>
            <motion.div 
                className="fixed inset-0 bg-black bg-opacity-75 z-50 flex justify-center items-start backdrop-blur-sm"
                variants={overlayVariants}
                initial="hidden"
                animate="visible"
                exit="exit"
            >
                <motion.div 
                    className="bg-light-primary dark:bg-dark-primary w-full max-w-7xl h-[90vh] mt-20 rounded-lg p-6 overflow-y-auto border-[7px] border-light-secondary dark:border-dark-secondary"
                    variants={contentVariants}
                    initial="hidden"
                    animate="visible"
                    exit="exit"
                >
                    <div className="flex justify-between items-center mb-6">
                        <h2 className="text-3xl font-semibold">Select {type.toUpperCase()}</h2>
                        <motion.button 
                            className="p-3 hover:bg-light-terciary dark:hover:bg-dark-terciary rounded-lg transition-colors"
                            whileHover={{ scale: 1.05 }}
                            whileTap={{ scale: 0.95 }}
                            onClick={onClose}
                        >
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                            </svg>
                        </motion.button>
                    </div>

                    {getFilters()}

                    {isLoading && searchParams.offset === 0 ? (
                        <div className="flex justify-center items-center h-64">
                            <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-light-secondary dark:border-dark-secondary"></div>
                        </div>
                    ) : error ? (
                        <div className="text-center text-red-500 p-4">
                            {error}
                        </div>
                    ) : (
                        <HardwareGrid 
                            hardware={hardware}
                            onSelect={onSelect}
                            isLoading={isLoading}
                            hasMore={hasMore}
                            onLoadMore={loadMore}
                        />
                    )}
                </motion.div>
            </motion.div>
        </AnimatePresence>
    );
}
