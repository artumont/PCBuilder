'use client'

import { useState } from 'react'
import Image from 'next/image'

export default function NavBar() {
    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => {
        setIsOpen(!isOpen);
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
                        <button className='p-5 mx-5 text-left text-lg md:text-xl lg:text-xl hover:opacity-80 transition-opacity'>Home</button>
                        <button className='p-5 mx-5 text-left text-lg md:text-xl lg:text-xl hover:opacity-80 transition-opacity'>Builder</button>
                        <button className='p-5 mx-5 text-left text-lg md:text-xl lg:text-xl hover:opacity-80 transition-opacity'>Help</button>
                    </div>
                    <div className='flex lg:hidden'>
                        <button className='p-5 mx-4 text-left text-lg md:text-xl lg:text-xl hover:opacity-80 transition-opacity'>Account</button>
                    </div>
                </div>
                <div className='hidden lg:flex lg:ml-auto'>
                    <button className='p-5 text-lg md:text-xl lg:text-xl hover:opacity-80 transition-opacity'>Account</button>
                </div>
            </div>
        </nav>
    );
}
