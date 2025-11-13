<template>
  <div class="inicio-container">
    <h2>Agenda de Compromissos</h2>

    <form @submit.prevent="salvar">
      <input v-model="titulo" type="text" placeholder="Título" required />
      <input v-model="data" type="date" required />
      <input v-model="hora" type="time" />
      <textarea v-model="descricao" placeholder="Descrição (opcional)"></textarea>
      <button type="submit">Salvar</button>
    </form>

    <h3>Compromissos</h3>
    <ul>
      <li v-for="c in compromissos" :key="c.id">
        <strong>{{ c.titulo }}</strong> - {{ c.data }} {{ c.hora }}
        <p>{{ c.descricao }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import api from "../service/apiRestConfig"

export default {
  data() {
    return {
      titulo: "",
      data: "",
      hora: "",
      descricao: "",
      compromissos: []
    }
  },
  mounted() {
    this.buscarCompromissos()
  },
  methods: {
    async buscarCompromissos() {
      const response = await api.get("/compromissos")
      this.compromissos = response.data
    },
    async salvar() {
      const compromisso = {
        titulo: this.titulo,
        data: this.data,
        hora: this.hora,
        descricao: this.descricao
      }
      await api.post("/compromissos", compromisso)
      this.buscarCompromissos()
      this.titulo = this.data = this.hora = this.descricao = ""
    }
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Segoe UI", Roboto, Arial, sans-serif;
}

body {
  background: #f5f7fb;
  color: #333;
  min-height: 100vh;
}

.app {
  max-width: 1100px;
  margin: 0 auto;
  padding: 20px;
}

header {
  text-align: center;
  padding: 20px 0;
  background: linear-gradient(135deg, #2463eb, #5a8dee);
  color: white;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
  margin-bottom: 25px;
}

header h1 {
  font-size: 1.8rem;
  letter-spacing: 0.5px;
}

.layout {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 24px;
}

@media (max-width: 850px) {
  .layout {
    grid-template-columns: 1fr;
  }
}

.card {
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
}

.card h2 {
  font-size: 1.3rem;
  color: #2b2d42;
  margin-bottom: 12px;
}


form label {
  display: block;
  margin-bottom: 6px;
  font-weight: 500;
  color: #333;
}

input[type="text"],
input[type="date"],
input[type="time"],
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #cfd4da;
  border-radius: 6px;
  font-size: 0.95rem;
  margin-bottom: 14px;
  background: #fdfdfd;
  transition: border 0.2s ease;
}

input:focus,
textarea:focus {
  outline: none;
  border-color: #2463eb;
  box-shadow: 0 0 0 2px rgba(36, 99, 235, 0.15);
}

textarea {
  min-height: 80px;
  resize: vertical;
}

.row {
  display: flex;
  gap: 14px;
}

.actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 10px;
}


button {
  cursor: pointer;
  border: none;
  border-radius: 6px;
  font-weight: 500;
  transition: background 0.2s, color 0.2s;
}

button.small {
  padding: 8px 14px;
  font-size: 0.9rem;
}

.btn-primary {
  background: #2463eb;
  color: white;
}

.btn-primary:hover {
  background: #1c4ec0;
}

.btn-ghost {
  background: #f2f4f8;
  color: #333;
}

.btn-ghost:hover {
  background: #e4e7ed;
}

.list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.search {
  border: 1px solid #ccc;
  border-radius: 6px;
  padding: 6px 10px;
  width: 60%;
  font-size: 0.9rem;
}

.search:focus {
  border-color: #2463eb;
  box-shadow: 0 0 0 2px rgba(36, 99, 235, 0.1);
}

.comp-list {
  border-top: 1px solid #e5e7eb;
  margin-top: 10px;
  max-height: 350px;
  overflow-y: auto;
  padding-right: 6px;
}

/* Estilo de cada compromisso */
.comp-list div {
  padding: 10px 0;
  border-bottom: 1px solid #e9ecef;
}

.comp-list div:last-child {
  border-bottom: none;
}

.comp-list h3 {
  margin-bottom: 4px;
  font-size: 1rem;
  color: #222;
}

.comp-list p {
  font-size: 0.9rem;
  color: #555;
}

.comp-list .data-hora {
  font-size: 0.85rem;
  color: #888;
}

.muted {
  font-size: 0.85rem;
  color: #777;
}

/* Barra de rolagem discreta */
.comp-list::-webkit-scrollbar {
  width: 6px;
}
.comp-list::-webkit-scrollbar-thumb {
  background: #cfd4da;
  border-radius: 3px;
}
.comp-list::-webkit-scrollbar-thumb:hover {
  background: #a9afb7;
}
</style>