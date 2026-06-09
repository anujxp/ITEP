import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

export default defineConfig({
  plugins: [react(), tailwindcss()],
  server: {
    port: 5173,
    // Proxy API calls to the gateway — avoids CORS preflight issues in dev
    proxy: {
      '/auth': { target: 'http://localhost:8080', changeOrigin: true },
      '/users': { target: 'http://localhost:8080', changeOrigin: true },
      '/admin': { target: 'http://localhost:8080', changeOrigin: true },
      '/properties': { target: 'http://localhost:8080', changeOrigin: true },
      '/bookings': { target: 'http://localhost:8080', changeOrigin: true },
    },
  },
})
