export default function Home() {
  return (
    <div className="p-8 text-lg">
      <h1 className="text-3xl font-bold mb-4">VibraOps</h1>
      <p>This demo illustrates real‑time machine health monitoring using Spring Boot and Next.js.</p>
      <p className="mt-4">
        <a href="/devices" className="underline text-blue-600">Devices</a> — manage and create devices.
      </p>
      <p>
        <a href="/anomalies" className="underline text-blue-600">Anomalies</a> — view recent anomalies and watch live updates.
      </p>
    </div>
  );
}