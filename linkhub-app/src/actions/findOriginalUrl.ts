import { api } from "@/lib/api";

interface FindOriginalUrlProps {
    shortId: string;
}

export async function FindOriginalUrl({ shortId }: FindOriginalUrlProps): Promise<string | undefined> {
    try {
        const response = await api.get(`/shorterUrl?shortId=${shortId}`);
        return response.data.originalUrl;
    } catch (error) {
        console.error("Failed to find the original url! ", error);
    }
}