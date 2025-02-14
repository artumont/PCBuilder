'use client'

import { createContext, useContext, useState, ReactNode } from 'react';

type NavigationContextType = {
    activeButton: string | null;
    setActiveButton: (button: string | null) => void;
};

const NavigationContext = createContext<NavigationContextType | undefined>(undefined);

export function NavigationProvider({ children }: { children: ReactNode }) {
    const [activeButton, setActiveButton] = useState<string | null>("home");

    return (
        <NavigationContext.Provider value={{ activeButton, setActiveButton }}>
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