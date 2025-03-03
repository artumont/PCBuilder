'use client'

import { motion } from "motion/react"
import Image from "next/image"
import { Github, Code2, Star, Trophy, Brain, Cpu } from "lucide-react"

const containerVariants = {
    hidden: { opacity: 0 },
    visible: {
        opacity: 1,
        transition: {
            delayChildren: 0.3,
            staggerChildren: 0.2,
            duration: 0.5
        }
    }
}

const itemVariants = {
    hidden: { opacity: 0, y: 20 },
    visible: {
        opacity: 1,
        y: 0,
        transition: { duration: 0.5 }
    }
}

export default function CreditsContent() {
    const teamMembers = [
        {
            name: "Artu (@artumont)",
            role: "Lead Developer & Backend Specialist",
            contributions: [
                "Developed the backend infrastructure",
                "Implemented core API functionality",
                "Developed the frontend application",
            ],
            tech: ["Next.js", "Java", "Photoshop"],
            image: "/assets/pfps/artu.png",
            github: "https://github.com/artumont",
            icon: <Code2 className="w-6 h-6" />
        },
        {
            name: "Luis (@prodanyboy)",
            role: "Database Specialist",
            contributions: [
                "Designed database schema",
                "Optimized query performance",
                "Implemented data validation"
            ],
            tech: ["SQL", "SSMS"],
            image: "/assets/pfps/luis.png",
            github: "https://github.com/prodanyboy",
            icon: <Brain className="w-6 h-6" />
        },
        {
            name: "Gilberto (@GilPeCa)",
            role: "Frontend Developer",
            contributions: [
                "Made guides for PCBuilder",
                "Created documentation for the project"
            ],
            tech: ["Word"],
            image: "/assets/pfps/gilberto.jpg",
            github: "https://github.com/GilPeCa",
            icon: <Star className="w-6 h-6" />
        },
        {
            name: "Gerardo (@SONRIXMX)",
            role: "Full Stack Developer",
            contributions: [
                "Made guides for PCBuilder",
                "Created documentation for the project"
            ],
            tech: ["Word"],
            image: "/assets/pfps/gerardo.png",
            github: "https://github.com/SONRIXMX",
            icon: <Trophy className="w-6 h-6" />
        },
        {
            name: "Emmanuel (@Ultimateknight143)",
            role: "Frontend Developer",
            contributions: [
                "Made guides for PCBuilder",
            ],
            tech: ["Word"],
            image: "/assets/pfps/emmanuel.png",
            github: "https://github.com/Ultimateknight143",
            icon: <Code2 className="w-6 h-6" />
        },
        {
            name: "Jesus (@Jesus-Mendoza21)",
            role: "Backend Developer",
            contributions: [
                "Made guides for PCBuilder",
            ],
            tech: ["Word"],
            image: "/assets/pfps/jesus.jpg",
            github: "https://github.com/Jesus-Mendoza21",
            icon: <Cpu className="w-6 h-6" />
        },
        {
            name: "Guajardo (@IngGuajardo)",
            role: "Full Stack Developer",
            contributions: [
                "Made guides for PCBuilder",
            ],
            tech: ["Word"],
            image: "/assets/pfps/guajardo.png",
            github: "https://github.com/IngGuajardo",
            icon: <Star className="w-6 h-6" />
        },
        {
            name: "Alan (@Alanhhdz)",
            role: "Frontend Developer",
            contributions: [
                "Made guides for PCBuilder",
            ],
            tech: ["Word"],
            image: "/assets/pfps/alan.png",
            github: "https://github.com/Alanhhdz",
            icon: <Code2 className="w-6 h-6" />
        }
    ]

    return (
        <div className="w-full mt-32 lg:mt-5 pb-10">
            <motion.div
                variants={containerVariants}
                initial="hidden"
                animate="visible"
                className="text-center mb-16"
            >
                <motion.h1 
                    variants={itemVariants}
                    className="text-4xl lg:text-5xl font-bold mb-4"
                >
                    Meet Our Team
                </motion.h1>
                <motion.p 
                    variants={itemVariants}
                    className="text-xl text-gray-600 dark:text-gray-400"
                >
                    The talented developers behind PC Builder
                </motion.p>
            </motion.div>

            <motion.div 
                className="grid grid-cols-1 md:grid-cols-2 gap-8 max-w-7xl mx-auto px-4"
                variants={containerVariants}
                initial="hidden"
                animate="visible"
            >
                {teamMembers.map((member) => (
                    <motion.div
                        key={member.name}
                        variants={itemVariants}
                        className="bg-light dark:bg-dark rounded-lg overflow-hidden gl-1 flex flex-col md:flex-row"
                        whileHover={{ y: -5, transition: { duration: 0.2 } }}
                    >
                        <div className="relative w-full md:w-48 h-48">
                            <Image
                                src={member.image}
                                alt={member.name}
                                fill
                                className="object-cover"
                            />
                            <div className="absolute inset-0 bg-gradient-to-t from-dark-secondary to-transparent opacity-70"></div>
                        </div>
                        <div className="p-6 flex-1 flex flex-col">
                            <div className="flex items-center gap-2 mb-2">
                                {member.icon}
                                <h3 className="text-xl font-bold">{member.name}</h3>
                            </div>
                            <p className="text-lg text-gray-600 dark:text-gray-400 mb-3">{member.role}</p>
                            
                            <div className="mb-4">
                                <h4 className="font-semibold mb-2">Key Contributions:</h4>
                                <ul className="list-disc list-inside text-sm space-y-1">
                                    {member.contributions.map((contribution, index) => (
                                        <li key={index} className="text-gray-600 dark:text-gray-400">{contribution}</li>
                                    ))}
                                </ul>
                            </div>
                            
                            <div className="mb-4">
                                <h4 className="font-semibold mb-2">Technologies:</h4>
                                <div className="flex flex-wrap gap-2">
                                    {member.tech.map((tech, index) => (
                                        <span 
                                            key={index}
                                            className="px-2 py-1 bg-light-secondary dark:bg-dark-secondary rounded-full text-xs"
                                        >
                                            {tech}
                                        </span>
                                    ))}
                                </div>
                            </div>
                            
                            <div className="mt-auto pt-4">
                                <a
                                    href={member.github}
                                    target="_blank"
                                    rel="noopener noreferrer"
                                    className="inline-flex items-center gap-2 hover:text-dark-secondary hover:dark:text-light-secondary transition-colors w-full p-2 rounded-lg"
                                >
                                    <Github className="w-5 h-5" />
                                    <span>View GitHub</span>
                                </a>
                            </div>
                        </div>
                    </motion.div>
                ))}
            </motion.div>
        </div>
    )
}
