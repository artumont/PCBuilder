export interface Hardware {
    name: string;
    id: number;
    price: number;
    imageUrl: string;
}

export interface HardwareResponse {
    status: string;
    message: string;
    hardwareType: string;
    hardwareData: Hardware;
}

export interface MultiHardwareResponse {
    status: string;
    message: string;
    hardwareType: string;
    hardwareList: Hardware[];
}

export type HardwareType = 'cpu' | 'gpu' | 'ram' | 'storage' | 'motherboard' | 'psu' | 'case' | 'cooler' | 'monitor';

export type SearchParams = {
    [key: string]: string | number | undefined;
    limit: number;
    offset?: number;
}
