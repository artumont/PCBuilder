'use client'

import { useState, useEffect } from 'react'
import { useAuth } from '@/context/AuthContext'

interface DashboardPageProps {
    onLogout: () => void;
}

interface UserData {
    username: string;
    email: string;
    joinDate: string;
    builds: number;
}

export default function DashboardPage({ onLogout }: DashboardPageProps) {
    const { authToken, logout } = useAuth()
    const [userData, setUserData] = useState<UserData | null>(null)
    const [isLoading, setIsLoading] = useState(true)
    const [error, setError] = useState('')

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                // @todo: Replace with actual API endpoint
                const response = await fetch('/user/profile', {
                    headers: {
                        'Authorization': `Bearer ${authToken}`,
                    },
                })

                if (!response.ok) {
                    throw new Error('Failed to fetch user data')
                }

                const data = await response.json()
                setUserData(data)
            } catch (err) {
                setError('Failed to load user data')
                console.error('Profile fetch error:', err)
            } finally {
                setIsLoading(false)
            }
        }

        fetchUserData()
    }, [authToken])

    const handleLogout = () => {
        logout()
        onLogout()
    }

    if (isLoading) {
        return <div className="flex min-h-screen items-center justify-center">Loading...</div>
    }

    if (error) {
        return (
            <div className="flex min-h-screen items-center justify-center">
                <div className="text-center">
                    <p className="text-red-600">{error}</p>
                    <button
                        onClick={handleLogout}
                        className="mt-4 text-sm text-indigo-600 hover:text-indigo-500"
                    >
                        Return to login
                    </button>
                </div>
            </div>
        )
    }

    return (
        <div className="min-h-screen bg-background p-8">
            <div className="mx-auto max-w-7xl">
                <div className="flex items-center justify-between mb-8">
                    <h1 className="text-3xl font-bold">Dashboard</h1>
                    <button
                        onClick={handleLogout}
                        className="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                    >
                        Sign out
                    </button>
                </div>
                
                <div className="grid gap-6 md:grid-cols-2">

                    <div className="rounded-lg bg-card p-6 shadow dark:bg-gray-800">
                        <h2 className="mb-4 text-xl font-semibold">Profile Information</h2>
                        <div className="space-y-4">
                            <div>
                                <label className="text-sm font-medium text-muted-foreground">Username</label>
                                <p className="text-lg">{userData?.username}</p>
                            </div>
                            <div>
                                <label className="text-sm font-medium text-muted-foreground">Email</label>
                                <p className="text-lg">{userData?.email}</p>
                            </div>
                            <div>
                                <label className="text-sm font-medium text-muted-foreground">Member Since</label>
                                <p className="text-lg">{userData?.joinDate}</p>
                            </div>
                        </div>
                    </div>

                    <div className="rounded-lg bg-card p-6 shadow dark:bg-gray-800">
                        <h2 className="mb-4 text-xl font-semibold">Activity Overview</h2>
                        <div className="space-y-4">
                            <div>
                                <label className="text-sm font-medium text-muted-foreground">Total PC Builds</label>
                                <p className="text-lg">{userData?.builds || 0}</p>
                            </div>
                        </div>
                    </div>

                    <div className="md:col-span-2">
                        <div className="rounded-lg bg-card p-6 shadow dark:bg-gray-800">
                            <h2 className="mb-4 text-xl font-semibold">Recent Builds</h2>
                            <div className="space-y-4">
                                <div className="rounded-md bg-muted/50 p-4">
                                    <p className="text-muted-foreground">No recent builds to display.</p>
                                    <button className="mt-2 text-sm font-medium text-primary hover:underline">
                                        Start your first build
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
