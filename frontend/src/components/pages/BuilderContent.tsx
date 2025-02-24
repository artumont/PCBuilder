'use client'

import { motion } from "motion/react"
import { useEffect, useState } from 'react';
import { useSearchParams, useRouter } from 'next/navigation';
import { Cpu } from 'lucide-react'

type Build = {
    cpu: string,
    gpu: string,
    ram: string,
    storage: string,
    motherboard: string,
    psu: string,
    case: string
}

export default function BuilderContent() {
    const searchParams = useSearchParams();
    const router = useRouter();
    
        const containerVariants = {
            hidden: {
                opacity: 0
            },
            visible: {
                opacity: 1,
                transition: {
                    delayChildren: 0.3,
                    staggerChildren: 0.2
                }
            }
        }

    const buttonVariants = {
        hidden: { opacity: 0, y: 20 },
        visible: {
            opacity: 1,
            y: 0,
            transition: {
                duration: 0.5
            }
        },
        hover: {
            scale: 1.05,
            transition: {
                duration: 0.2
            }
        },
        tap: {
            scale: 0.95,
            transition: {
                duration: 0.2
            }
        }
    }

    const currentBuild: Build = {
        cpu: '',
        gpu: '',
        ram: '',
        storage: '',
        motherboard: '',
        psu: '',
        case: ''
    };

    useEffect(() => {
        let config = searchParams.get("config")
    })

    return (
        <div className="w-full mt-32 lg:mt-5">
            <motion.div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8"
                variants={containerVariants}
            >
                <motion.button 
                    className='flex p-2 rounded-lg h-64 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    whileHover="hover"
                    whileTap="tap"
                    initial="hidden"
                    animate="visible"
                >
                    <div className="flex items-center ml-10">
                        <Cpu className="w-24 h-24 mr-5" />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">CPU</h1>
                            <h2 className="text-sm">{currentBuild.cpu || "Select a CPU"}</h2>
                        </div>
                    </div>
                </motion.button>

                <motion.button 
                    className='flex p-2 rounded-lg h-64 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    whileHover="hover"
                    whileTap="tap"
                    initial="hidden"
                    animate="visible"
                >
                    <div className="flex items-center ml-10">
                        <Cpu className="w-24 h-24 mr-5" />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">GPU</h1>
                            <h2 className="text-sm">{currentBuild.gpu || "Select a GPU"}</h2>
                        </div>
                    </div>
                </motion.button>

                <motion.button 
                    className='flex p-2 rounded-lg h-64 items-center bg-light-terciary dark:bg-dark-terciary border-[7px] border-light-secondary dark:border-dark-secondary' 
                    variants={buttonVariants}
                    whileHover="hover"
                    whileTap="tap"
                    initial="hidden"
                    animate="visible"
                >
                    <div className="flex items-center ml-10">
                        <Cpu className="w-24 h-24 mr-5" />
                        <div className="flex flex-col text-start">
                            <h1 className="text-4xl">RAM</h1>
                            <h2 className="text-sm">{currentBuild.ram || "Select a RAM"}</h2>
                        </div>
                    </div>
                </motion.button>
            </motion.div>
        </div>
    )
}