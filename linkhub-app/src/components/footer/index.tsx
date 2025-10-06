import Link from "next/link";

export function Footer() {
  return (
    <footer className="bg-white rounded-lg shadow-sm m-4">
      <div className="w-full mx-auto max-w-screen-xl p-4 md:flex md:items-center md:justify-between">
        <span className="text-sm text-gray-500 sm:text-center">
          © 2025{" "}
          <Link href="/" className="hover:underline">
            LinkHub™
          </Link>
          . All Rights Reserved.
        </span>
      </div>
    </footer>
  );
}
