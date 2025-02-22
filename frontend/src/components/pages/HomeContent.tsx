'use client'

import { motion } from "motion/react"
import { Cpu, HardDrive, MemoryStickIcon as Memory } from "lucide-react"
import { delay } from "motion"
import { useNavigation } from "@/context/NavContext"

export default function HomeContent() {
    const { setActiveButton } = useNavigation();

    const handleBuilderClick = () => {
        setActiveButton('builder');
    };
    
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

    const itemVariants = {
        hidden: {
            opacity: 0,
            y: -20
        },
        visible: {
            opacity: 1,
            y: 0,
            transition: {
                duration: 0.5
            }
        }
    }

    const buttonVariants = {
        initial: { opacity: 0 },
        visible: { 
            opacity: 1, 
            transition: {
                delay: 0.9,
                duration: 0.5
            }
        },
        hover: { 
            scale: 1.05,
            transition: {
                duration: 0.001,
                ease: "easeInOut"
            }
        },
        tap: { 
            scale: 0.95,
            transition: {
                duration: 0.001,
                ease: "easeInOut"
            }
        }
    };

    return (
        <div className="w-full mt-32 lg:mt-5">
            <section className="relative text-center py-20 px-4 overflow-hidden rounded-lg gl-1">
                <div className="absolute inset-0 bg-gradient-to-br from-light-secondary via-light to-light-terciary dark:from-dark-secondary dark:via-dark dark:to-dark-terciary opacity-50 z-0"></div>
                <motion.div 
                    className="relative z-10"
                    variants={containerVariants}
                    initial="hidden"
                    animate="visible"
                >
                    <motion.h1
                        className="text-5xl lg:text-6xl font-bold mb-6"
                        variants={itemVariants}
                    >
                        Build Your Dream PC
                    </motion.h1>
                    <motion.p
                        className="text-xl lg:text-2xl mb-8"
                        variants={itemVariants}
                    >
                        Create custom PC builds with our easy-to-use builder tool
                    </motion.p>
                    <motion.button
                        variants={buttonVariants}
                        initial="initial"
                        animate="visible"
                        whileHover="hover"
                        whileTap="tap"
                        onClick={handleBuilderClick}
                        className="bg-light-secondary dark:bg-dark-secondary px-10 py-3 rounded-full font-bold text-lg shadow-lg hover:shadow-xl transition-all duration-300"
                    >
                        Get Started
                    </motion.button>
                </motion.div>
                <div className="absolute inset-0 z-0 overflow-hidden">
                    <motion.div
                        className="absolute -left-4 top-1/4"
                        animate={{ rotate: 360 }}
                        transition={{ duration: 20, repeat: Number.POSITIVE_INFINITY, ease: "linear" }}
                    >
                        <Cpu className="w-16 h-16 opacity-20" />
                    </motion.div>
                    <motion.div
                        className="absolute right-1/4 bottom-1/4"
                        animate={{ rotate: -360 }}
                        transition={{ duration: 25, repeat: Number.POSITIVE_INFINITY, ease: "linear" }}
                    >
                        <HardDrive className="w-20 h-20 opacity-20" />
                    </motion.div>
                    <motion.div
                        className="absolute right-10 top-10"
                        animate={{ rotate: 360 }}
                        transition={{ duration: 30, repeat: Number.POSITIVE_INFINITY, ease: "linear" }}
                    >
                        <Memory className="w-24 h-24 opacity-20" />
                    </motion.div>
                </div>
            </section>
        </div>
    )
}