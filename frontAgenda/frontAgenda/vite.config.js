import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  // CORREÇÃO CRÍTICA: Define o caminho base como '/'
  // Isso garante que todos os assets no index.html (como "assets/index-...") 
  // sejam gerados com um caminho absoluto: "/assets/index-..."
  
  base: './',
  
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
