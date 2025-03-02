'use client'

import { useState } from 'react';
import { CommonFilters, baseInputClasses } from './PickerComponents';
import { SearchParams } from './Types';

interface MonitorFilterProps {
    updateSearchParam: (key: string, value: string | number | undefined) => void;
}

const MonitorFilters: React.FC<MonitorFilterProps> = ({ updateSearchParam }) => (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
        <CommonFilters updateSearchParam={updateSearchParam} />
        <input 
            type="text"
            placeholder="Resolution"
            className={baseInputClasses}
            onChange={(e) => updateSearchParam('resolution', e.target.value || undefined)}
        />
        <input 
            type="number"
            placeholder="Refresh Rate (Hz)"
            className={baseInputClasses}
            onChange={(e) => updateSearchParam('refreshRate', parseInt(e.target.value) || undefined)}
        />
    </div>
);

export const useMonitorFilters = () => {
    const [searchParams, setSearchParams] = useState<SearchParams>({ limit: 12, offset: 0 });
    
    const updateSearchParam = (key: string, value: string | number | undefined) => {
        setSearchParams(prev => ({ ...prev, offset: 0, [key]: value }));
    };

    return {
        searchParams,
        setSearchParams,
        updateSearchParam,
        Filters: <MonitorFilters updateSearchParam={updateSearchParam} />
    };
};
