'use client'

import { useState } from 'react'
import { useAuth } from '@/context/AuthContext'
import DashboardPage from './account/DashboardPage'
import LoginPage from './account/LoginPage'
import RegisterPage from './account/RegisterPage'

export default function AccountContent() {
    const [currentPage, setCurrentPage] = useState<'login' | 'register' | 'dashboard'>('login')
    const { isAuthenticated } = useAuth()

    const handleLoginSuccess = () => {
        setCurrentPage('dashboard')
    }

    const handleRegisterSuccess = () => {
        setCurrentPage('login')
    }

    const handleLogout = () => {
        setCurrentPage('login')
    }

    if (!isAuthenticated) {
        if (currentPage === 'login') {
            return (
                <LoginPage
                    onLoginSuccess={handleLoginSuccess}
                    onRegisterClick={() => setCurrentPage('register')}
                />
            )
        }
        return (
            <RegisterPage
                onRegisterSuccess={handleRegisterSuccess}
                onLoginClick={() => setCurrentPage('login')}
            />
        )
    }

    return <DashboardPage onLogout={handleLogout} />
}
