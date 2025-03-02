'use client'

import { useState } from 'react';
import { CommonFilters, baseInputClasses } from './PickerComponents';
import { SearchParams } from './Types';

interface StorageFilterProps {
    updateSearchParam: (key: string, value: string | number | undefined) => void;
}

const StorageFilters: React.FC<StorageFilterProps> = ({ updateSearchParam }) => (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
        <CommonFilters updateSearchParam={updateSearchParam} />
        <input 
            type="text"
            placeholder="Storage Format"
            className={baseInputClasses}
            onChange={(e) => updateSearchParam('storageFormat', e.target.value || undefined)}
        />
        <input 
            type="text"
            placeholder="Storage Protocol"
            className={baseInputClasses}
            onChange={(e) => updateSearchParam('storageProtocol', e.target.value || undefined)}
        />
        <div className="grid grid-cols-2 gap-4">
            <input 
                type="number"
                placeholder="Min size (GB)"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('minSize', parseInt(e.target.value) || undefined)}
            />
            <input 
                type="number"
                placeholder="Max size (GB)"
                className={baseInputClasses}
                onChange={(e) => updateSearchParam('maxSize', parseInt(e.target.value) || undefined)}
            />
        </div>
    </div>
);

export const useStorageFilters = () => {
    const [searchParams, setSearchParams] = useState<SearchParams>({ limit: 12, offset: 0 });
    
    const updateSearchParam = (key: string, value: string | number | undefined) => {
        setSearchParams(prev => ({ ...prev, offset: 0, [key]: value }));
    };

    return {
        searchParams,
        setSearchParams,
        updateSearchParam,
        Filters: <StorageFilters updateSearchParam={updateSearchParam} />
    };
};
