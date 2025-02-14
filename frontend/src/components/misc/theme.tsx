'use client'

import { useState, useEffect } from 'react'
import { useTheme } from 'next-themes'
import { Sun, Moon } from './icons'

export default function ThemeToggle() {
    const [mounted, setMounted] = useState(false)
    const { theme, systemTheme, setTheme } = useTheme()

    useEffect(() => setMounted(true), [])

    if (!mounted) return null

    const currentTheme = theme === 'system' ? systemTheme : theme

    return (
        <button
            className="fixed bottom-4 right-4 p-2 rounded-full bg-default border-2 border-light-secondary dark:border-dark-secondary shadow-lg text-black dark:text-white"
            onClick={() => {
                const newTheme = currentTheme === 'dark' ? 'light' : 'dark'
                setTheme(newTheme)
            }}
        >
            {currentTheme === 'dark' ? <Sun /> : <Moon />}
        </button>
    )
}