import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      // Proxy everything except frontend static assets, HTML, JS, CSS, etc.
      // This catches all /api or any other backend routes.
      // You can match all paths here, but be careful not to proxy frontend routes.

      // To proxy everything (all requests), use:
      // '/api': {
      //   target: 'http://localhost:8080',
      //   changeOrigin: true,
      //   secure: false,
      //   // Don't rewrite the path if backend expects the same path
      // },
      // '/income': {
      //   target: 'http://localhost:8080',
      //   changeOrigin: true,
      //   secure: false,
      // },
      // '/expense': {
      //   target: 'http://localhost:8080',
      //   changeOrigin: true,
      //   secure: false,
      // },
      // '/stats': {
      //   target: 'http://localhost:8080',
      //   changeOrigin: true,
      //   secure: false,
      // },
      
      
    },
  },
});
