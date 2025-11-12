'use client';

import { useEffect } from 'react';
import { Client } from '@stomp/stompjs';
import { useAppDispatch, useAppSelector } from '@/lib/store';
import { fetchAnomalies, pushLive } from '@/lib/anomalySlice';

export default function AnomaliesPage() {
  const dispatch = useAppDispatch();
  const anomalies = useAppSelector(state => state.anomalies.items);
  const live = useAppSelector(state => state.anomalies.live);
  const loading = useAppSelector(state => state.anomalies.loading);

  useEffect(() => {
    dispatch(fetchAnomalies());
  }, [dispatch]);

  useEffect(() => {
    const client = new Client({ brokerURL: `${process.env.NEXT_PUBLIC_WS_URL}` });
    client.onConnect = () => {
      client.subscribe('/topic/anomalies', message => {
        const payload = JSON.parse(message.body);
        dispatch(pushLive(payload));
      });
    };
    client.activate();
    return () => client.deactivate();
  }, [dispatch]);

  return (
    <div className="p-6 grid md:grid-cols-2 gap-6">
      <div>
        <h1 className="text-2xl font-bold mb-4">Recent anomalies</h1>
        {loading && <p>Loading…</p>}
        <ul className="space-y-2">
          {anomalies.slice(0, 20).map((a: any) => (
            <li key={a.id} className="border p-3 rounded">
              <b>Device {a.device.id}</b> • {a.rule} • score {a.score.toFixed(2)} •
              {new Date(a.ts).toLocaleString()}
            </li>
          ))}
        </ul>
      </div>
      <div>
        <h1 className="text-2xl font-bold mb-4">Live feed</h1>
        <ul className="space-y-2">
          {live.slice(0, 20).map((x: any, i: number) => (
            <li key={i} className="border p-3 rounded bg-yellow-50">
              🔴 Device {x.device.id} • {x.rule} • {x.score.toFixed(2)} at
              {new Date(x.ts).toLocaleTimeString()}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}