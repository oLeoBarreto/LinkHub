import { ShoterUrlForm } from "@/app/(home)/components/form";

export default function Home() {
  return (
    <main className="container mx-auto">
      <section className="flex items-center justify-center w-full h-full min-h-[calc(100vh-112px)]">
        <div className="max-w-3xl p-6 bg-white border border-gray-200 rounded-lg shadow-sm w-full">
          <div className="px-3 py-4 mb-4 text-center font-semibold text-3xl">
            <h1>
              Welcome to LinkHub! The website make to get your life easier.
            </h1>
          </div>
          <ShoterUrlForm />
        </div>
      </section>
    </main>
  );
}
