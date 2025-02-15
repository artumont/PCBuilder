'use client'

import { isTokenExpired, attemptRefresh } from '@/utils/auth'

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

    const removeToken = (tokenType: string) => {
        switch (tokenType) {
            case 'authToken':
                document.cookie = 'authToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;'
                break
            case 'refreshToken':
                document.cookie = 'refreshToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;'
                break
            default:
                break
        }
    }

    const handleTokenRefresh = async () => {
        let authToken = getToken('authToken')
        const refreshToken = getToken('refreshToken')

        if (!authToken || isTokenExpired(authToken)) {
            if (refreshToken && !isTokenExpired(refreshToken)) {
                const token = await attemptRefresh(refreshToken)
                if (token) {
                    authToken = token
                } else {
                    removeToken('authToken')
                    removeToken('refreshToken')
                    window.location.href = '/login'
                }
            } else {
                removeToken('authToken')
                removeToken('refreshToken')
                window.location.href = '/login'
            }
        }
        return authToken
    }

    // handleTokenRefresh()
    //! Uncomment the above line to run the function on page load

    return (
        <main className="flex flex-col items-center w-screen h-screen bg-light dark:bg-dark">

        </main>
    );
}