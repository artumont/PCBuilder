'use client'

import { useState } from 'react';
import { CommonFilters, baseInputClasses } from './PickerComponents';
import { SearchParams } from './Types';

interface CoolerFilterProps {
    updateSearchParam: (key: string, value: string | number | undefined) => void;
}

const CoolerFilters: React.FC<CoolerFilterProps> = ({ updateSearchParam }) => (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
        <CommonFilters updateSearchParam={updateSearchParam} />
        <input 
            type="text"
            placeholder="Socket"
            className={baseInputClasses}
            onChange={(e) => updateSearchParam('socket', e.target.value || undefined)}
        />
    </div>
);

export const useCoolerFilters = () => {
    const [searchParams, setSearchParams] = useState<SearchParams>({ limit: 12, offset: 0 });
    
    const updateSearchParam = (key: string, value: string | number | undefined) => {
        setSearchParams(prev => ({ ...prev, offset: 0, [key]: value }));
    };

    return {
        searchParams,
        setSearchParams,
        updateSearchParam,
        Filters: <CoolerFilters updateSearchParam={updateSearchParam} />
    };
};
