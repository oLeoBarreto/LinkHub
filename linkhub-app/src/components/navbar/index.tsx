import Image from "next/image";
import Link from "next/link";

export function Navbar() {
  return (
    <header className="w-full flex items-center px-2 py-4 bg-white h-28 shadow-sm">
      <div className="w-full flex items-center max-w-7xl mx-auto">
        <Link href="/" className="flex flex-col items-center justify-center">
          <Image src="logo.svg" alt="logo" width={80} height={80} />
          <h1 className="font-bold text-2xl pl-1 hover:tracking-widest duration-300">
            Link
            <span className="bg-gradient-to-t from-[#45caff] to-[#eca0ff] text-transparent bg-clip-text">
              Hub
            </span>
          </h1>
        </Link>
      </div>
    </header>
  );
}
