import axios from "axios";

export const api = axios.create({
    baseURL: process.env.NEXT_PUBLIC_API_BASE_URL,
    timeout: 1000,
    validateStatus: (status: number): boolean => {
        return status >= 200 && status < 400;
    }
})