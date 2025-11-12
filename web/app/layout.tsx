import '@/styles/globals.css';
import { store } from '@/lib/store';
import { Provider } from 'react-redux';

export const metadata = {
  title: 'VibraOps',
  description: 'Real‑time asset health demo with Spring Boot and Next.js',
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body>
        <Provider store={store}>{children}</Provider>
      </body>
    </html>
  );
}