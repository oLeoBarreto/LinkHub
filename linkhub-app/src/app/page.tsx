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
          <form className="max-w-3xl mx-auto">
            <input
              type="text"
              id="url"
              aria-describedby="helper-text-explanation"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-[#45caff] focus:border-[#45caff] block w-full h-12 p-2.5"
              placeholder="Enter your link here"
            />
            <p
              id="helper-text-explanation"
              className="mt-2 text-sm text-gray-500"
            >
              Type your url here to get short version.
            </p>
            <button
              type="button"
              className="w-full mt-5 text-white bg-gradient-to-t from-[#45caff] to-[#eca0ff] hover:bg-gradient-to-b hover:cursor-pointer focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-3"
            >
              Short URL
            </button>
          </form>
        </div>
      </section>
    </main>
  );
}
