"use client";

import { CreateShortUrl } from "@/actions/createShortUlr";
import Link from "next/link";
import { FormEvent, useState } from "react";
import z from "zod";

const schema = z.object({
  originalUrl: z.string(),
});

export function ShoterUrlForm() {
  const [shortId, setShortId] = useState("");

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const formData = new FormData(event.target as HTMLFormElement);

    const validateSchema = schema.safeParse({
      originalUrl: formData.get("url"),
    });

    if (validateSchema.success) {
      const response = await CreateShortUrl({
        originalUrl: validateSchema.data.originalUrl,
      });

      if (response != undefined) setShortId(response);
    }
  };

  return (
    <div>
      <form className="max-w-3xl mx-auto" onSubmit={handleSubmit}>
        <input
          type="text"
          id="url"
          name="url"
          aria-describedby="helper-text-explanation"
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-[#45caff] focus:border-[#45caff] block w-full h-12 p-2.5"
          placeholder="Enter your link here"
        />
        <p id="helper-text-explanation" className="mt-2 text-sm text-gray-500">
          Type your url here to get short version.
        </p>
        <button
          type="submit"
          className="w-full mt-5 text-white bg-gradient-to-t from-[#45caff] to-[#eca0ff] hover:bg-gradient-to-b hover:cursor-pointer focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-3"
        >
          Short URL
        </button>
      </form>
      {shortId && (
        <div className="text-center mt-8">
          <hr className="text-gray-300" />
          <h2 className="text-xl font-semibold mt-2 mb-2">
            URL shortened successfully!
          </h2>

          <Link
            href={`/${shortId}`}
            className="text-blue-500 hover:underline break-all"
            target="_blank"
            rel="noopener noreferrer"
          >
            {`${
              typeof window !== "undefined" ? window.location.origin : ""
            }/${shortId}`}
          </Link>
        </div>
      )}
    </div>
  );
}
