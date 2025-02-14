'use client'

import { useState } from 'react'
import Image from 'next/image'

export default function NavBar() {
    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };

    return (
        <div className="flex flex-col lg:flex-row mx-auto w-[85%] lg:w-[90%] my-5 px-7 py-2 bg-light dark:bg-dark gl-1 rounded-lg">
            <div className="flex items-center justify-between w-full lg:w-auto">
                <div className="flex items-center">
                    <Image className='dark:hidden mr-5'
                        src="/assets/transparent-logo-light.svg"
                        alt="PCBuilder Logo"
                        width={65}
                        height={65}
                    />
                    <Image className='hidden dark:block mr-5'
                        src="/assets/transparent-logo-dark.svg"
                        alt="PCBuilder Logo"
                        width={65}
                        height={65}
                    />
                    <h1 className='text-lg md:text-xl lg:text-xl min-w-[100%]'>PC Builder</h1>
                </div>
                <button className="lg:hidden p-2" onClick={toggleMenu}>
                    <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16m-7 6h7"></path>
                    </svg>
                </button>
            </div>
            <div className={`flex-col lg:flex-row lg:flex ${isOpen ? 'flex' : 'hidden'} w-full `}>
                <div className='flex flex-col lg:flex-row lg:ml-[27%] mr-auto justify-center lg:justify-start'>
                    <button className='p-4 mx-3 text-left text-lg md:text-xl lg:text-xl'>Home</button>
                    <button className='p-4 mx-3 text-left text-lg md:text-xl lg:text-xl'>Builder</button>
                    <button className='p-4 mx-3 text-left text-lg md:text-xl lg:text-xl'>Help</button>
                </div>
                <div className='flex'>
                    <button className='p-4 mx-3 lg:mx-0 text-left text-lg md:text-xl lg:text-xl'>Credits</button>
                </div>
            </div>
        </div>
    );
}