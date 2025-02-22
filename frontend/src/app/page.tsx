'use client'

import NavBar from '@/components/NavBar'
import NavSelector from '@/components/NavSelector';

export default function Home() {
    return (
        <main className="flex flex-col items-center w-screen min-h-screen bg-light dark:bg-dark">
            <NavBar />
            <NavSelector />
        </main>
    );
}
