'use client';

import { useEffect, useState } from 'react';
import { useAppDispatch, useAppSelector } from '@/lib/store';
import { fetchDevices, createDevice } from '@/lib/devicesSlice';

export default function DevicesPage() {
  const dispatch = useAppDispatch();
  const devices = useAppSelector(state => state.devices.items);
  const loading = useAppSelector(state => state.devices.loading);
  const [form, setForm] = useState({ name: '', status: 'OK', location: '' });

  useEffect(() => {
    dispatch(fetchDevices());
  }, [dispatch]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    dispatch(createDevice(form));
    setForm({ name: '', status: 'OK', location: '' });
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Devices</h1>
      <form onSubmit={handleSubmit} className="mb-6 space-y-2">
        <input
          className="border p-2 rounded w-full"
          placeholder="Name"
          value={form.name}
          onChange={e => setForm({ ...form, name: e.target.value })}
          required
        />
        <input
          className="border p-2 rounded w-full"
          placeholder="Status"
          value={form.status}
          onChange={e => setForm({ ...form, status: e.target.value })}
        />
        <input
          className="border p-2 rounded w-full"
          placeholder="Location"
          value={form.location}
          onChange={e => setForm({ ...form, location: e.target.value })}
        />
        <button type="submit" className="bg-blue-600 text-white px-3 py-1 rounded">
          Add Device
        </button>
      </form>
      {loading && <p>Loading…</p>}
      <table className="min-w-full border rounded text-sm">
        <thead>
          <tr>
            <th className="p-2 text-left">ID</th>
            <th className="p-2 text-left">Name</th>
            <th className="p-2 text-left">Status</th>
            <th className="p-2 text-left">Location</th>
            <th className="p-2 text-left">Last Seen</th>
          </tr>
        </thead>
        <tbody>
          {devices.map(device => (
            <tr key={device.id} className="border-t">
              <td className="p-2">{device.id}</td>
              <td className="p-2">{device.name}</td>
              <td className="p-2">{device.status}</td>
              <td className="p-2">{device.location}</td>
              <td className="p-2">{device.lastSeen}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}