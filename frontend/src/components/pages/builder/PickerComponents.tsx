'use client'

import { motion } from 'motion/react';
import Image from 'next/image';
import { Hardware } from './Types';

export const baseInputClasses = "p-2 rounded-lg bg-light-terciary dark:bg-dark-terciary focus:outline-none focus:ring-2 focus:ring-light-secondary dark:focus:ring-dark-secondary";

export const overlayVariants = {
    hidden: { opacity: 0 },
    visible: { opacity: 1 },
    exit: { opacity: 0 }
};

export const contentVariants = {
    hidden: { y: 50, opacity: 0 },
    visible: { 
        y: 0, 
        opacity: 1,
        transition: {
            duration: 0.3,
            type: "spring",
            damping: 25,
            stiffness: 500
        }
    },
    exit: { y: 50, opacity: 0 }
};

export const itemVariants = {
    hidden: { opacity: 0, y: 20 },
    visible: (i: number) => ({
        opacity: 1,
        y: 0,
        transition: {
            delay: i * 0.1,
            duration: 0.3
        }
    }),
    hover: {
        scale: 1.02,
        transition: { duration: 0.2 }
    },
    tap: {
        scale: 0.98
    }
};

interface CommonFilterProps {
    updateSearchParam: (key: string, value: string | number | undefined) => void;
}

export const CommonFilters: React.FC<CommonFilterProps> = ({ updateSearchParam }) => (
    <>
        <input 
            type="text"
            placeholder="Search by name"
            className={baseInputClasses}
            onChange={(e) => updateSearchParam('name', e.target.value || undefined)}
        />
        <div className="grid grid-cols-2 gap-4">
            <input 
                type="number"
                placeholder="Min price"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minPrice', parseFloat(e.target.value) || undefined)}
            />
            <input 
                type="number"
                placeholder="Max price"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('maxPrice', parseFloat(e.target.value) || undefined)}
            />
        </div>
    </>
);

interface HardwareGridProps {
    hardware: Hardware[];
    onSelect: (hardware: Hardware) => void;
    isLoading: boolean;
    hasMore: boolean;
    onLoadMore: () => void;
}

export const HardwareGrid: React.FC<HardwareGridProps> = ({ 
    hardware, 
    onSelect, 
    isLoading, 
    hasMore, 
    onLoadMore 
}) => (
    <>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {hardware.map((item, i) => (
                <motion.div 
                    key={item.id}
                    className="p-6 bg-light-terciary dark:bg-dark-terciary rounded-lg border-[3px] border-light-secondary dark:border-dark-secondary cursor-pointer"
                    variants={itemVariants}
                    custom={i}
                    initial="hidden"
                    animate="visible"
                    whileHover="hover"
                    whileTap="tap"
                    onClick={() => onSelect(item)}
                >
                    <div className="flex flex-col space-y-4">
                        {item.imageUrl && (
                            <Image 
                                src={item.imageUrl}
                                alt={item.name}
                                width={200}
                                height={200}
                                className="object-contain h-32 w-full"
                            />
                        )}
                        <h3 className="font-medium text-lg">{item.name}</h3>
                        <p className="text-xl font-bold">${item.price}</p>
                    </div>
                </motion.div>
            ))}
        </div>
        {hasMore && (
            <div className="mt-6 flex justify-center">
                <motion.button
                    className="px-6 py-2 bg-light-secondary dark:bg-dark-secondary rounded-lg font-medium hover:opacity-90"
                    whileHover={{ scale: 1.02 }}
                    whileTap={{ scale: 0.98 }}
                    onClick={onLoadMore}
                    disabled={isLoading}
                >
                    {isLoading ? (
                        <div className="animate-spin rounded-full h-6 w-6 border-b-2 border-light-primary dark:border-dark-primary"></div>
                    ) : (
                        'Load More'
                    )}
                </motion.button>
            </div>
        )}
    </>
);
