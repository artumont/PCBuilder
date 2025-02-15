export const isTokenExpired = (token: string): boolean => {
    if (!token) return true;

    try {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        const { exp } = JSON.parse(jsonPayload);

        return exp * 1000 < Date.now();
    } catch (error) {
        return true;
    }
};

export const attemptRefresh = async (refreshToken: string): Promise<string | null> => {
    return ""
};