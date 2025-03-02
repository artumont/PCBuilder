'use client'

import { useState } from 'react';
import { CommonFilters, baseInputClasses } from './PickerComponents';
import { SearchParams } from './Types';

interface PSUFilterProps {
    updateSearchParam: (key: string, value: string | number | undefined) => void;
}

const PSUFilters: React.FC<PSUFilterProps> = ({ updateSearchParam }) => (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
        <CommonFilters updateSearchParam={updateSearchParam} />
        <input 
            type="text"
            placeholder="Size"
            className={baseInputClasses}
            onChange={(e) => updateSearchParam('size', e.target.value || undefined)}
        />
        <div className="grid grid-cols-2 gap-4">
            <input 
                type="number"
                placeholder="Min wattage"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minWattage', parseInt(e.target.value) || undefined)}
            />
            <input 
                type="number"
                placeholder="Max wattage"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('maxWattage', parseInt(e.target.value) || undefined)}
            />
        </div>
    </div>
);

export const usePSUFilters = () => {
    const [searchParams, setSearchParams] = useState<SearchParams>({ limit: 12, offset: 0 });
    
    const updateSearchParam = (key: string, value: string | number | undefined) => {
        setSearchParams(prev => ({ ...prev, offset: 0, [key]: value }));
    };

    return {
        searchParams,
        setSearchParams,
        updateSearchParam,
        Filters: <PSUFilters updateSearchParam={updateSearchParam} />
    };
};
