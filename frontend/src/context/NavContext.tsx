'use client'

import { createContext, useContext, useState, ReactNode, useEffect } from 'react';
import { useSearchParams, useRouter } from 'next/navigation';

type NavigationContextType = {
    activeButton: string | null;
    setActiveButton: (button: string | null) => void;
};

const NavigationContext = createContext<NavigationContextType | undefined>(undefined);

export function NavigationProvider({ children }: { children: ReactNode }) {
    const searchParams = useSearchParams();
    const router = useRouter();
    const [activeButton, setActiveButton] = useState<string | null>(() => {
        return searchParams.get('tab') || "home";
    });

    const handleSetActiveButton = (button: string | null) => {
        setActiveButton(button);
        if (button) {
            router.push(`?tab=${button}`);
        }
    };

    useEffect(() => {
        const tabParam = searchParams.get('tab');
        if (tabParam && tabParam !== activeButton) {
            setActiveButton(tabParam);
        }
    }, [searchParams, activeButton]);

    return (
        <NavigationContext.Provider value={{ 
            activeButton, 
            setActiveButton: handleSetActiveButton 
        }}>
            {children}
        </NavigationContext.Provider>
    );
}

export function useNavigation() {
    const context = useContext(NavigationContext);
    if (context === undefined) {
        throw new Error('useNavigation must be used within a NavigationProvider');
    }
    return context;
}