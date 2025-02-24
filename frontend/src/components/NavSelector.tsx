'use client'

import { useNavigation } from '@/context/NavContext';
import AccountContent from './pages/AccountContent';
import BuilderContent from './pages/BuilderContent';
import HomeContent from './pages/HomeContent';
import GuidesContent from './pages/GuidesContent';

export default function NavSelector() {
    const { activeButton } = useNavigation();

    const renderContent = () => {
        switch (activeButton) {
            case 'home':
                return <HomeContent />;
            case 'builder':
                return <BuilderContent />;
            case 'guides':
                return <GuidesContent />;
            case 'account':
                return <AccountContent />;
            default:
                return <HomeContent />;
        }
    };

    return (
        <div className="mx-auto h-auto w-[85%] lg:w-[90%] py-5 flex justify-center">
            {renderContent()}
        </div>
    );
}