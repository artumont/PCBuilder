'use client'

import { useState } from 'react';
import { CommonFilters, baseInputClasses } from './PickerComponents';
import { SearchParams } from './Types';

interface GPUFilterProps {
    updateSearchParam: (key: string, value: string | number | undefined) => void;
}

const GPUFilters: React.FC<GPUFilterProps> = ({ updateSearchParam }) => (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
        <CommonFilters updateSearchParam={updateSearchParam} />
        <input 
            type="text"
            placeholder="Chipset"
            className={baseInputClasses}
            onChange={(e) => updateSearchParam('chipset', e.target.value || undefined)}
        />
        <div className="grid grid-cols-2 gap-4">
            <input 
                type="number"
                placeholder="Min VRAM (GB)"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minVram', parseInt(e.target.value) || undefined)}
            />
            <input 
                type="number"
                placeholder="Max VRAM (GB)"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('maxVram', parseInt(e.target.value) || undefined)}
            />
        </div>
    </div>
);

export const useGPUFilters = () => {
    const [searchParams, setSearchParams] = useState<SearchParams>({ limit: 12, offset: 0 });
    
    const updateSearchParam = (key: string, value: string | number | undefined) => {
        setSearchParams(prev => ({ ...prev, offset: 0, [key]: value }));
    };

    return {
        searchParams,
        setSearchParams,
        updateSearchParam,
        Filters: <GPUFilters updateSearchParam={updateSearchParam} />
    };
};
