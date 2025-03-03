'use client'

import { motion } from "motion/react"
import Image from "next/image"
import { useNavigation } from "@/context/NavContext"
import { Cpu, HardDrive, Microchip, Component, Fingerprint, Github, MemoryStickIcon as Memory, NotebookText as Notebook } from "lucide-react"

const containerVariants = {
    hidden: {
        opacity: 0
    },
    visible: {
        opacity: 1,
        transition: {
            delayChildren: 0.3,
            staggerChildren: 0.2,
            duration: 0.5
        }
    }
}

const featureVariants = {
    hidden: { opacity: 0, y: 20 },
    visible: {
        opacity: 1,
        y: 0,
        transition: {
            duration: 0.5
        }
    }
};

export default function HomeContent() {
    // @note: This is super duper messy but im kinda running out of time soooooooooo 

    const { setActiveButton } = useNavigation();

    const handleBuilderClick = () => {
        setActiveButton('builder');
    };

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
        <div className="w-full h-auto mt-32 lg:mt-5">
            <motion.section className="relative text-center py-20 px-4 overflow-hidden rounded-lg gl-1"
                variants={containerVariants}
                initial="hidden"
                animate="visible"
            >
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
                        className="absolute left-10 top-10"
                        animate={{ rotate: 360 }}
                        transition={{ duration: 20, repeat: Number.POSITIVE_INFINITY, ease: "linear" }}
                    >
                        <Cpu className="w-16 h-16 opacity-20" />
                    </motion.div>
                    <motion.div
                        className="absolute left-1/4 bottom-1/4"
                        animate={{ rotate: -360 }}
                        transition={{ duration: 35, repeat: Number.POSITIVE_INFINITY, ease: "linear" }}
                    >
                        <Microchip className="w-16 h-16 opacity-20" />
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
            </motion.section>

            <section className="relative py-20 px-4">
                <motion.h2 
                    initial={{opacity: 0, y: 20}}
                    animate={{opacity: 1, y: 0}}
                    transition={{duration: 0.5}}
                    className="text-3xl font-bold text-center mb-10"
                >
                    Features
                </motion.h2>
                <motion.div
                    className="max-w-7xl mx-auto grid grid-cols-1 lg:grid-cols-3 gap-8"
                    initial="hidden"
                    whileInView="visible"
                    viewport={{ once: true }}
                    variants={containerVariants}
                >
                    <motion.div
                        className="p-6 rounded-lg gl-1"
                        variants={featureVariants}
                        whileHover={{ y: -10, transition: { duration: 0.2 } }}
                    >
                        <div className="mb-4 ">
                            <Component className="w-10 h-10" />
                        </div>
                        <h3 className="text-xl font-bold mb-2">Component Selection</h3>
                        <p className="text-gray-600 dark:text-gray-400">
                            Choose from a wide range of PC components with real-time compatibility checking
                        </p>
                    </motion.div>

                    <motion.div
                        className="p-6 rounded-lg gl-1"
                        variants={featureVariants}
                        whileHover={{ y: -10, transition: { duration: 0.2 } }}
                    >
                        <div className="mb-4 ">
                            <Fingerprint className="w-10 h-10" />
                        </div>
                        <h3 className="text-xl font-bold mb-2">Detailed Specs</h3>
                        <p className="text-gray-600 dark:text-gray-400">
                            View detailed specifications for each component and build
                        </p>
                    </motion.div>

                    <motion.div
                        className="p-6 rounded-lg gl-1"
                        variants={featureVariants}
                        whileHover={{ y: -10, transition: { duration: 0.2 } }}
                    >
                        <div className="mb-4 ">
                            <Notebook className="w-10 h-10" />
                        </div>
                        <h3 className="text-xl font-bold mb-2">Build Guides</h3>
                        <p className="text-gray-600 dark:text-gray-400">
                            Access curated build guides for different budgets and use cases
                        </p>
                    </motion.div>
                </motion.div>
            </section>

            <section className="relative py-5 px-4">
                <motion.h2 
                    initial={{opacity: 0, y: 20}}
                    animate={{opacity: 1, y: 0}}
                    transition={{duration: 0.5}}
                    className="text-3xl font-bold text-center mb-10"
                >
                    Why Choose PC Builder?
                </motion.h2>
                <motion.div 
                    className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 max-w-7xl mx-auto"
                    variants={containerVariants}
                    initial="hidden"
                    whileInView="visible"
                    viewport={{ once: true }}
                >
                    <motion.div
                        className="p-6 rounded-lg gl-1 text-center"
                        variants={featureVariants}
                        whileHover={{ y: -10, transition: { duration: 0.2 } }}
                    >
                        <div className="w-16 h-16 mx-auto mb-6 bg-light-secondary dark:bg-dark-secondary rounded-full flex items-center justify-center">
                            <Cpu className="w-8 h-8" />
                        </div>
                        <h3 className="text-xl font-bold mb-4">Smart Compatibility</h3>
                        <p className="text-gray-600 dark:text-gray-400">
                            Our intelligent system ensures all your chosen components work perfectly together, eliminating compatibility concerns.
                        </p>
                    </motion.div>

                    <motion.div
                        className="p-6 rounded-lg gl-1 text-center"
                        variants={featureVariants}
                        whileHover={{ y: -10, transition: { duration: 0.2 } }}
                    >
                        <div className="w-16 h-16 mx-auto mb-6 bg-light-secondary dark:bg-dark-secondary rounded-full flex items-center justify-center">
                            <Notebook className="w-8 h-8" />
                        </div>
                        <h3 className="text-xl font-bold mb-4">Expert Guidance</h3>
                        <p className="text-gray-600 dark:text-gray-400">
                            Access detailed build guides and expert recommendations to create the perfect PC for your needs and budget.
                        </p>
                    </motion.div>

                    <motion.div
                        className="p-6 rounded-lg gl-1 text-center"
                        variants={featureVariants}
                        whileHover={{ y: -10, transition: { duration: 0.2 } }}
                    >
                        <div className="w-16 h-16 mx-auto mb-6 bg-light-secondary dark:bg-dark-secondary rounded-full flex items-center justify-center">
                            <Memory className="w-8 h-8" />
                        </div>
                        <h3 className="text-xl font-bold mb-4">Real-time Updates</h3>
                        <p className="text-gray-600 dark:text-gray-400">
                            Stay informed with live pricing and availability updates for all components in your build.
                        </p>
                    </motion.div>
                </motion.div>
            </section>
        </div>
    )
}
