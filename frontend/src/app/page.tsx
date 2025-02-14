'use client'

import NavBar from '@/components/navigation/navbar'

export default function Home() {
    return (
        <main className="flex flex-col items-center w-screen h-screen bg-light dark:bg-dark">
            <NavBar />
        </main>
    );
}
