'use client'

import { createContext, useContext, useState, ReactNode } from 'react'

interface AuthContextType {
    isAuthenticated: boolean
    authToken: string | null
    refreshToken: string | null
    login: (authToken: string, refreshToken: string) => void
    logout: () => void
}

const AuthContext = createContext<AuthContextType | undefined>(undefined)

interface AuthProviderProps {
    children: ReactNode
}

export function AuthProvider({ children }: AuthProviderProps) {
    const [authToken, setAuthToken] = useState<string | null>(null)
    const [refreshToken, setRefreshToken] = useState<string | null>(null)

    const login = (authToken: string, refreshToken: string) => {
        setAuthToken(authToken)
        setRefreshToken(refreshToken)
        localStorage.setItem('authToken', authToken)
        localStorage.setItem('refreshToken', refreshToken)
    }

    const logout = () => {
        setAuthToken(null)
        setRefreshToken(null)
        localStorage.removeItem('authToken')
        localStorage.removeItem('refreshToken')
    }

    return (
        <AuthContext.Provider 
            value={{
                isAuthenticated: !!authToken,
                authToken,
                refreshToken,
                login,
                logout
            }}
        >
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = () => {
    const context = useContext(AuthContext)
    if (context === undefined) {
        throw new Error('useAuth must be used within an AuthProvider')
    }
    return context
}
