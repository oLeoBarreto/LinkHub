import { api } from "@/lib/api";

interface CreateShortUrlProps {
    originalUrl: string;
}

export async function CreateShortUrl({ originalUrl }: CreateShortUrlProps): Promise<string | undefined> {
    try {
        const response = await api.post("/shorterUrl/links", { originalUrl });
        return response.data.shortId as string;
    } catch (error) {
        console.error("Failed to create a shorter url! ", error);
    }
}