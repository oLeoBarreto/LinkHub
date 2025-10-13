import { FindOriginalUrl } from "@/actions/findOriginalUrl";
import Link from "next/link";

export default async function RedirectPage({
  params,
}: {
  params: Promise<{ id: string }>;
}) {
  const { id } = await params;

  const redirectTo = await FindOriginalUrl({ shortId: id });

  return (
    <main className="flex items-center justify-center w-full h-full min-h-[calc(100vh-112px)]">
      {redirectTo === undefined ? (
        <h1 className="font-medium text-2xl">Wait to be redirect...</h1>
      ) : (
        <Link
          href={redirectTo}
          className="font-medium text-2xl hover:underline hover:text-blue-400"
          replace
        >
          Click here to redirect...
        </Link>
      )}
    </main>
  );
}
