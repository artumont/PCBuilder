'use client'

import { useNavigation } from '@/context/NavContext';
import CreditsContent from './pages/CreditsContent';
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
            case 'credits':
                return <CreditsContent />;
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