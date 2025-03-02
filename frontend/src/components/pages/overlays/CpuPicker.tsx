'use client'

import { useState } from 'react';
import { CommonFilters, baseInputClasses } from './PickerComponents';
import { SearchParams } from './Types';

interface CPUFilterProps {
    updateSearchParam: (key: string, value: string | number | undefined) => void;
}

const CPUFilters: React.FC<CPUFilterProps> = ({ updateSearchParam }) => (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
        <CommonFilters updateSearchParam={updateSearchParam} />
        <input 
            type="text"
            placeholder="Socket"
            className={baseInputClasses}
            onChange={(e) => updateSearchParam('socket', e.target.value || undefined)}
        />
        <div className="grid grid-cols-2 gap-4">
            <input 
                type="number"
                placeholder="Min cores"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minCores', parseInt(e.target.value) || undefined)}
            />
            <input 
                type="number"
                placeholder="Max cores"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('maxCores', parseInt(e.target.value) || undefined)}
            />
        </div>
        <div className="grid grid-cols-2 gap-4">
            <input 
                type="number"
                placeholder="Min clock (GHz)"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minClockSpeed', parseFloat(e.target.value) || undefined)}
            />
            <input 
                type="number"
                placeholder="Max clock (GHz)"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('maxClockSpeed', parseFloat(e.target.value) || undefined)}
            />
        </div>
    </div>
);

export const useCPUFilters = () => {
    const [searchParams, setSearchParams] = useState<SearchParams>({ limit: 12, offset: 0 });
    
    const updateSearchParam = (key: string, value: string | number | undefined) => {
        setSearchParams(prev => ({ ...prev, offset: 0, [key]: value }));
    };

    return {
        searchParams,
        setSearchParams,
        updateSearchParam,
        Filters: <CPUFilters updateSearchParam={updateSearchParam} />
    };
};
