'use client'

import { motion } from "motion/react"
import { useState } from 'react'
import { Wrench, Box } from "lucide-react"
import BuildGuide from "./guides/BuildGuide"
import PrebuiltGuide from "./guides/PrebuiltGuide"

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

const cardVariants = {
    hidden: { opacity: 0, y: 20 },
    visible: {
        opacity: 1,
        y: 0,
        transition: {
            duration: 0.5
        }
    }
}

export default function GuidesContent() {
    const [selectedGuide, setSelectedGuide] = useState<string | null>(null)

    if (selectedGuide === 'build') {
        return (
            <motion.div 
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                className="w-full mt-32 lg:mt-5 max-w-6xl mx-auto px-4"
            >
                <button 
                    onClick={() => setSelectedGuide(null)}
                    className="mb-6 text-gray-600 dark:text-gray-400 hover:text-gray-900 dark:hover:text-gray-100 transition-colors"
                >
                    ← Back to Guides
                </button>
                <BuildGuide />
            </motion.div>
        )
    }

    if (selectedGuide === 'prebuilt') {
        return (
            <motion.div 
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                className="w-full mt-32 lg:mt-5 max-w-6xl mx-auto px-4"
            >
                <button 
                    onClick={() => setSelectedGuide(null)}
                    className="mb-6 text-gray-600 dark:text-gray-400 hover:text-gray-900 dark:hover:text-gray-100 transition-colors"
                >
                    ← Back to Guides
                </button>
                <PrebuiltGuide />
            </motion.div>
        )
    }

    return (
        <div className="w-full mt-32 lg:mt-5">
            <motion.div 
                className="max-w-6xl mx-auto px-4"
                variants={containerVariants}
                initial="hidden"
                animate="visible"
            >
                <h2 className="text-3xl font-bold text-center mb-12">PC Building Guides</h2>
                
                <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
                    <motion.div
                        className="p-6 rounded-lg gl-1 cursor-pointer"
                        variants={cardVariants}
                        whileHover={{ y: -10, transition: { duration: 0.2 } }}
                        onClick={() => setSelectedGuide('build')}
                    >
                        <div className="w-16 h-16 mx-auto mb-6 bg-light-secondary dark:bg-dark-secondary rounded-full flex items-center justify-center">
                            <Wrench className="w-8 h-8" />
                        </div>
                        <h3 className="text-xl font-bold mb-4 text-center">How to Build a PC</h3>
                        <p className="text-gray-600 dark:text-gray-400 text-center">
                            Step-by-step guide to building your own PC, from component selection to final assembly.
                        </p>
                    </motion.div>

                    <motion.div
                        className="p-6 rounded-lg gl-1 cursor-pointer"
                        variants={cardVariants}
                        whileHover={{ y: -10, transition: { duration: 0.2 } }}
                        onClick={() => setSelectedGuide('prebuilt')}
                    >
                        <div className="w-16 h-16 mx-auto mb-6 bg-light-secondary dark:bg-dark-secondary rounded-full flex items-center justify-center">
                            <Box className="w-8 h-8" />
                        </div>
                        <h3 className="text-xl font-bold mb-4 text-center">Prebuilt PC Recommendations</h3>
                        <p className="text-gray-600 dark:text-gray-400 text-center">
                            Curated selection of prebuilt PCs for different budgets with gaming performance metrics.
                        </p>
                    </motion.div>
                </div>
            </motion.div>
        </div>
    )
}
