'use client'

import { useState } from 'react';
import { CommonFilters, baseInputClasses } from './PickerComponents';
import { SearchParams } from './Types';

interface RAMFilterProps {
    updateSearchParam: (key: string, value: string | number | undefined) => void;
}

const RAMFilters: React.FC<RAMFilterProps> = ({ updateSearchParam }) => (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
        <CommonFilters updateSearchParam={updateSearchParam} />
        <input 
            type="text"
            placeholder="RAM Type"
            className={baseInputClasses}
            onChange={(e) => updateSearchParam('ramType', e.target.value || undefined)}
        />
        <div className="grid grid-cols-2 gap-4">
            <input 
                type="number"
                placeholder="Min capacity (GB)"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minCapacity', parseInt(e.target.value) || undefined)}
            />
            <input 
                type="number"
                placeholder="Max capacity (GB)"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('maxCapacity', parseInt(e.target.value) || undefined)}
            />
        </div>
        <div className="grid grid-cols-2 gap-4">
            <input 
                type="number"
                placeholder="Min speed (MHz)"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minSpeed', parseInt(e.target.value) || undefined)}
            />
            <input 
                type="number"
                placeholder="Max speed (MHz)"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('maxSpeed', parseInt(e.target.value) || undefined)}
            />
        </div>
    </div>
);

export const useRAMFilters = () => {
    const [searchParams, setSearchParams] = useState<SearchParams>({ limit: 12, offset: 0 });
    
    const updateSearchParam = (key: string, value: string | number | undefined) => {
        setSearchParams(prev => ({ ...prev, offset: 0, [key]: value }));
    };

    return {
        searchParams,
        setSearchParams,
        updateSearchParam,
        Filters: <RAMFilters updateSearchParam={updateSearchParam} />
    };
};
