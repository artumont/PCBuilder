'use client'

import { useState } from 'react';
import { CommonFilters, baseInputClasses } from './PickerComponents';
import { SearchParams } from './Types';

interface MotherboardFilterProps {
    updateSearchParam: (key: string, value: string | number | undefined) => void;
}

const MotherboardFilters: React.FC<MotherboardFilterProps> = ({ updateSearchParam }) => (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
        <CommonFilters updateSearchParam={updateSearchParam} />
        <div className="grid grid-cols-2 gap-4">
            <input 
                type="text"
                placeholder="Socket"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('socket', e.target.value || undefined)}
            />
            <input 
                type="text"
                placeholder="Chipset"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('chipset', e.target.value || undefined)}
            />
        </div>
        <div className="grid grid-cols-2 gap-4">
            <input 
                type="text"
                placeholder="RAM Type"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('ramType', e.target.value || undefined)}
            />
            <input 
                type="text"
                placeholder="Size"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('size', e.target.value || undefined)}
            />
        </div>
        <div className="grid grid-cols-3 gap-4">
            <input 
                type="number"
                placeholder="SATA"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minSataSlots', parseInt(e.target.value) || undefined)}
            />
            <input 
                type="number"
                placeholder="M.2"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minM2Slots', parseInt(e.target.value) || undefined)}
            />
            <input 
                type="number"
                placeholder="RAM slots"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minRamSlots', parseInt(e.target.value) || undefined)}
            />
        </div>
    </div>
);

export const useMotherboardFilters = () => {
    const [searchParams, setSearchParams] = useState<SearchParams>({ limit: 12, offset: 0 });
    
    const updateSearchParam = (key: string, value: string | number | undefined) => {
        setSearchParams(prev => ({ ...prev, offset: 0, [key]: value }));
    };

    return {
        searchParams,
        setSearchParams,
        updateSearchParam,
        Filters: <MotherboardFilters updateSearchParam={updateSearchParam} />
    };
};
