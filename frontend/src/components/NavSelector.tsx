'use client'

import { useNavigation } from '@/context/NavContext';
import AccountContent from './pages/AccountContent';
import BuilderContent from './pages/BuilderContent';
import HelpContent from './pages/HelpContent';
import HomeContent from './pages/HomeContent';

export default function NavSelector() {
    const { activeButton } = useNavigation();

    const renderContent = () => {
        switch (activeButton) {
            case 'home':
                return <HomeContent />;
            case 'builder':
                return <BuilderContent />;
            case 'help':
                return <HelpContent />;
            case 'account':
                return <AccountContent />;
            default:
                return <HomeContent />;
        }
    };

    return (
        <div className="mx-auto h-full w-[85%] lg:w-[90%] py-5 flex justify-center">
            {renderContent()}
        </div>
    );
}