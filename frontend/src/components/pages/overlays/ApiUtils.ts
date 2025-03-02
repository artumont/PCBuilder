import { MultiHardwareResponse, SearchParams, HardwareType } from './Types';

export const fetchHardware = async (type: HardwareType, params: SearchParams): Promise<MultiHardwareResponse> => {
    const queryString = new URLSearchParams();
    Object.entries(params).forEach(([key, value]) => {
        if (value !== undefined) {
            queryString.append(key, value.toString());
        }
    });

    const response = await fetch(
        `https://api-pcbuilder-black.loca.lt/hardware/${type}/search?${queryString}`,
        {
            headers: {
                'bypass-tunnel-reminder': '1'
            }
        }
    );
    
    if (!response.ok) {
        throw new Error('Failed to fetch hardware');
    }
    
    return response.json();
};
