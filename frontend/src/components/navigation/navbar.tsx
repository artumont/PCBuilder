'use client'

import { useState } from 'react'
import Image from 'next/image'
import { motion } from "motion/react"
import { useNavigation } from '@/context/navcontext';

export default function NavBar() {
    const [isOpen, setIsOpen] = useState(false);
    const { activeButton, setActiveButton } = useNavigation();

    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };

    const buttonVariants = {
        initial: {
            scale: 1,
            opacity: 1
        },
        hover: {
            opacity: 0.8,
            transition: {
                duration: 0.2
            }
        },
        active: {
            opacity: 0.8
        }
    };

    const underlineVariants = {
        initial: {
            width: "0%",
            left: "50%",
            x: "-50%"
        },
        hover: {
            width: "100%",
            transition: {
                duration: 0.3,
                ease: "easeInOut"
            }
        },
        active: {
            width: "100%",
            left: "50%",
            x: "-50%"
        },
        tap: {
            width: "110%",
            transition: {
                duration: 0.1
            }
        }
    };

    return (
        <nav className="mx-auto w-[85%] lg:w-[90%] my-5 px-7 py-2 bg-light dark:bg-dark gl-1 rounded-lg">
            <div className="flex flex-col lg:flex-row lg:items-center">
                <div className="flex items-center justify-between lg:w-auto">
                    <div className="flex items-center">
                        <Image
                            className='dark:hidden mr-5'
                            src="/assets/transparent-logo-light.svg"
                            alt="PCBuilder Logo"
                            width={65}
                            height={65}
                        />
                        <Image
                            className='hidden dark:block mr-5'
                            src="/assets/transparent-logo-dark.svg"
                            alt="PCBuilder Logo"
                            width={65}
                            height={65}
                        />
                        <h1 className='text-lg md:text-xl lg:text-xl min-w-[100%]'>PC Builder</h1>
                    </div>
                    <button
                        className="lg:hidden p-2"
                        onClick={toggleMenu}
                        aria-label="Toggle menu"
                    >
                        <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16m-7 6h7"></path>
                        </svg>
                    </button>
                </div>

                <div className={`${isOpen ? 'flex' : 'hidden'} lg:flex flex-col lg:flex-row w-screen lg:absolute lg:left-0 lg:top-7 lg:justify-center`}>
                    <div className="flex flex-col lg:flex-row">
                        <motion.button
                            variants={buttonVariants}
                            animate={activeButton === 'home' ? 'active' : 'initial'}
                            onClick={() => setActiveButton(activeButton === 'home' ? null : 'home')}
                            initial="initial"
                            whileHover={activeButton === 'home' ? undefined : "initial"}
                            whileTap="tap"
                            className='relative p-5 py-1 pt-5 mx-5 text-left text-lg md:text-xl lg:text-xl'
                        >
                            <span>Home</span>
                            <motion.div
                                variants={underlineVariants}
                                className='hidden lg:block'
                                style={{
                                    position: 'absolute',
                                    bottom: '-2px',
                                    height: '2px',
                                    backgroundColor: 'currentColor',
                                }}
                            />
                        </motion.button>
                        
                        <motion.button
                            variants={buttonVariants}
                            animate={activeButton === 'builder' ? 'active' : 'initial'}
                            onClick={() => setActiveButton(activeButton === 'builder' ? null : 'builder')}
                            initial="initial"
                            whileHover={activeButton === 'builder' ? undefined : "initial"}
                            whileTap="tap"
                            className='relative p-5 py-1 pt-5 mx-5 text-left text-lg md:text-xl lg:text-xl'
                        >
                            <span>Builder</span>
                            <motion.div
                                variants={underlineVariants}
                                className='hidden lg:block'
                                style={{
                                    position: 'absolute',
                                    bottom: '-2px',
                                    height: '2px',
                                    backgroundColor: 'currentColor',
                                }}
                            />
                        </motion.button>

                        <motion.button
                            variants={buttonVariants}
                            animate={activeButton === 'help' ? 'active' : 'initial'}
                            onClick={() => setActiveButton(activeButton === 'help' ? null : 'help')}
                            initial="initial"
                            whileHover={activeButton === 'help' ? undefined : "initial"}
                            whileTap="tap"
                            className='relative p-5 py-1 pt-5 mx-5 text-left text-lg md:text-xl lg:text-xl'
                        >
                            <span>Help</span>
                            <motion.div
                                variants={underlineVariants}
                                className='hidden lg:block'
                                style={{
                                    position: 'absolute',
                                    bottom: '-2px',
                                    height: '2px',
                                    backgroundColor: 'currentColor',
                                }}
                            />
                        </motion.button>
                    </div>
                    <div className='flex lg:hidden'>
                        <motion.button
                            variants={buttonVariants}
                            animate={activeButton === 'account' ? 'active' : 'initial'}
                            onClick={() => setActiveButton(activeButton === 'account' ? null : 'account')}
                            initial="initial"
                            whileHover={activeButton === 'account' ? undefined : "initial"}
                            whileTap="tap"
                            className='relative p-5 py-1 pt-5 mx-5 text-left text-lg md:text-xl lg:text-xl'
                        >
                            <span>Account</span>
                            <motion.div
                                variants={underlineVariants}
                                className='hidden lg:block'
                                style={{
                                    position: 'absolute',
                                    bottom: '-2px',
                                    height: '2px',
                                    backgroundColor: 'currentColor',
                                }}
                            />
                        </motion.button>
                    </div>
                </div>
                <div className='hidden lg:flex lg:ml-auto z-[1]'>
                    <motion.button
                        variants={buttonVariants}
                        animate={activeButton === 'account' ? 'active' : 'initial'}
                        onClick={() => setActiveButton(activeButton === 'account' ? null : 'account')}
                        initial="initial"
                        whileHover={activeButton === 'account' ? undefined : "initial"}
                        whileTap="tap"
                        className='relative p-5 mx-5 text-left text-lg md:text-xl lg:text-xl'
                    >
                        <span>Account</span>
                        <motion.div
                            variants={underlineVariants}
                            className='hidden lg:block'
                            style={{
                                position: 'absolute',
                                bottom: '12px',
                                height: '2px',
                                backgroundColor: 'currentColor',
                            }}
                        />
                    </motion.button>
                </div>
            </div>
        </nav>
    );
}
