'use client'

import { isTokenExpired } from '@/utils/auth'

export default function Account() {

    const getToken = (tokenType: string) => {
        let value = null
        switch (tokenType) {
            case 'authToken':
                value = document.cookie.match('(^|;)\\s*authToken\\s*=\\s*([^;]+)')?.pop() || null
                return value
            case 'refreshToken':
                value = document.cookie.match('(^|;)\\s*refreshToken\\s*=\\s*([^;]+)')?.pop() || null
                return value
            default:
                return value
        }
    }

    const authToken = getToken('authToken')
    const refreshToken = getToken('refreshToken')

    if (!authToken || isTokenExpired(authToken)) {
        if (refreshToken && !isTokenExpired(refreshToken)) {
            // @todo: refresh token
        }
        else {
            window.location.href = '/login'
        }
    }

    return (
        <main className="flex flex-col items-center w-screen h-screen bg-light dark:bg-dark">
            
        </main>
    );
}