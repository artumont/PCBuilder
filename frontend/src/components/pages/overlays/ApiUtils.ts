import { MultiHardwareResponse, SearchParams, HardwareType, Hardware, HardwareResponse } from './Types';

export const fetchHardware = async (type: HardwareType, params: SearchParams): Promise<MultiHardwareResponse> => {
    return fetchFromApi(`hardware/${type}/search`, params);
};

export const fetchHardwareByName = async (type: HardwareType, name: string): Promise<Hardware> => {
    const params = { name, limit: 1 };
    const response = await fetchFromApi(`hardware/${type}/search`, params) as MultiHardwareResponse;
    const hardware = response.hardwareList[0];
    if (!hardware) {
        throw new Error(`No hardware found for ${type} with name: ${name}`);
    }
    return hardware;
};

const fetchFromApi = async (endpoint: string, params: Record<string, any>) => {
    const queryString = new URLSearchParams();
    Object.entries(params).forEach(([key, value]) => {
        if (value !== undefined) {
            queryString.append(key, value.toString());
        }
    });

    const response = await fetch(
        `https://api-pcbuilder-black.loca.lt/${endpoint}?${queryString}`,
        {
            headers: {
                'bypass-tunnel-reminder': '1'
            }
        }
    );
    
    try {
        if (!response.ok) {
            const errorData = await response.json().catch(() => null);
            throw new Error(
                errorData?.message || 
                `Failed to fetch hardware: ${response.status} ${response.statusText}`
            );
        }
        
        return response.json();
    } catch (error) {
        if (error instanceof TypeError && error.message.includes('Failed to fetch')) {
            throw new Error('Network error: Please check your connection to the API server');
        }
        throw error;
    }
};
