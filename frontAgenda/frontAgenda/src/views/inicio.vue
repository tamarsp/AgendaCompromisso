<template>	
	<div class="inicio-container">
		<h2>Agenda de Compromissos</h2>

		<div v-if="mensagem" :class="['alert', isError ? 'alert-error' : 'alert-success']">
			{{ mensagem }}
		</div>
		<form @submit.prevent="salvar">
			<input v-model="titulo" type="text" placeholder="Título" required />
			<input v-model="data" type="date" required />
			<input v-model="hora" type="time" />
			<textarea v-model="descricao" placeholder="Descrição (opcional)"></textarea>
			<button type="submit">Salvar</button>
		</form>

		<h3>Meus Compromissos</h3>
		<ul v-if="compromissos.length" class="comp-list">
			<li v-for="c in compromissos" :key="c.id">
				<div class="list-item">
					<strong>{{ c.titulo }}</strong>
					<span class="data-hora">{{ formatarData(c.dataHora) }}</span>
					<p v-if="c.descricao">{{ c.descricao }}</p>
				</div>
			</li>
		</ul>
		<p v-else class="muted">Nenhum compromisso encontrado.</p>
	</div>
</template>

<script>
import api from "../service/apiRestConfig"
import moment from "moment"

export default {
	data() {
		return {
			usuarioId: localStorage.getItem("usuarioId"),
			titulo: "",
			data: "",
			hora: "",
			descricao: "",
			compromissos: [],
			mensagem: null,
			isError: false,
		}
	},

	methods: {
		formatarData(dataHora) {
			return moment(dataHora).format("DD/MM/YYYY [às] HH:mm")
		},

		async buscarCompromissos() {
			if (!this.usuarioId) {
				this.mensagem = "Usuário não logado.";
				this.isError = true;
				return;
			}

			try {
				const response = await api.get(`/compromissos/usuario/${this.usuarioId}`);
				this.compromissos = response.data;
			} catch (error) {
				this.mensagem = "Erro ao carregar compromissos.";
				this.isError = true;
			}
		},

		async salvar() {
			if (!this.usuarioId) {
				this.mensagem = "Erro: usuário não identificado.";
				this.isError = true;
				return;
			}

			const dataHoraCompleta = `${this.data}T${this.hora}:00`;

			const compromissoData = {
				titulo: this.titulo,
				descricao: this.descricao,
				dataHora: dataHoraCompleta
			}

			try {
				const response = await api.post(
					`/compromissos/usuario/${this.usuarioId}`,
					compromissoData
				)

				this.mensagem = `Compromisso "${response.data.titulo}" criado!`;
				this.isError = false;

				this.buscarCompromissos();

				this.titulo = "";
				this.data = "";
				this.hora = "";
				this.descricao = "";

			} catch (error) {
				this.mensagem = "Erro ao salvar compromisso.";
				this.isError = true;
			}
		}
	},

	mounted() {
		this.buscarCompromissos();
	}
}
</script>
<style scoped>
:root {
  --primary-color: #007bff; /* Azul vibrante */
  --secondary-color: #6c757d; /* Cinza secundário */
  --success-color: #28a745; /* Verde para sucesso */
  --error-color: #dc3545; /* Vermelho para erro */
  --background-light: #f8f9fa; /* Fundo leve */
  --background-card: #ffffff; /* Fundo dos cartões */
  --text-dark: #212529; /* Texto principal mais visível */
  --border-radius: 8px;
  --shadow-subtle: 0 4px 12px rgba(0, 0, 0, 0.08);
}

.inicio-container {
  width: 100%;
  max-width: 720px;
  padding: 30px;
  background-color: var(--background-card);
  border-radius: var(--border-radius);
  box-shadow: var(--shadow-subtle);
  margin: 40px auto;
  font-family: Arial, sans-serif;
}

.logout-container {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 15px;
}

.logout-container button {
  background-color: var(--error-color);
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: var(--border-radius);
  cursor: pointer;
  font-weight: bold;
  transition: background 0.3s;
}

.logout-container button:hover {
  background-color: #a71d2a;
}

h2 {
  color: var(--primary-color);
  margin-top: 0;
  margin-bottom: 25px;
  font-size: 2em;
  border-bottom: 2px solid var(--primary-color);
  padding-bottom: 10px;
}

h3 {
  color: var(--text-dark);
  margin-top: 40px;
  margin-bottom: 20px;
  font-size: 1.5em;
}

.muted {
  color: var(--secondary-color);
  font-style: italic;
  text-align: center;
  padding: 15px;
  border: 1px dashed #ced4da;
  border-radius: var(--border-radius);
}

form {
  display: flex;
  flex-direction: column;
  gap: 15px;
  padding: 20px;
  border: 1px solid #dee2e6;
  border-radius: var(--border-radius);
  background-color: var(--background-light);
}

form input[type="text"],
form input[type="date"],
form input[type="time"],
form textarea {
  padding: 12px;
  border: 1px solid #ced4da;
  border-radius: var(--border-radius);
  font-size: 1em;
  width: 100%;
  box-sizing: border-box;
  transition: border-color 0.3s, box-shadow 0.3s;
}

form input:focus,
form textarea:focus {
  border-color: var(--primary-color);
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
  outline: none;
}

form textarea {
  resize: vertical;
  min-height: 100px;
}

form button[type="submit"] {
  padding: 12px 20px;
  background-color: var(--primary-color);
  color: white;
  border: none;
  border-radius: var(--border-radius);
  font-size: 1.1em;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.3s, transform 0.1s;
}

form button[type="submit"]:hover {
  background-color: #0056b3;
}

form button[type="submit"]:active {
  transform: scale(0.98);
}

.comp-list {
  list-style: none;
  padding: 0;
  margin-top: 0;
}

.comp-list li {
  background-color: var(--background-light);
  border: 1px solid #dee2e6;
  padding: 15px 20px;
  margin-bottom: 10px;
  border-radius: var(--border-radius);
  transition: transform 0.2s, box-shadow 0.2s;
}

.comp-list li:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.15);
}

.list-item {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.list-item strong {
  font-size: 1.2em;
  color: var(--primary-color);
}

.data-hora {
  font-size: 0.9em;
  color: var(--secondary-color);
  font-weight: 500;
}

.list-item p {
  margin: 0;
  color: var(--text-dark);
  border-left: 3px solid #ced4da;
  padding-left: 10px;
  font-size: 0.95em;
}

.alert {
  padding: 15px;
  margin-bottom: 20px;
  border-radius: var(--border-radius);
  font-weight: bold;
  text-align: center;
  animation: fadeIn 0.5s;
}

.alert-success {
  background-color: #d4edda;
  color: var(--success-color);
  border: 1px solid #c3e6cb;
}

.alert-error {
  background-color: #f8d7da;
  color: var(--error-color);
  border: 1px solid #f5c6cb;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@media (max-width: 600px) {
  .inicio-container {
    padding: 15px;
    box-shadow: none;
    border-radius: 0;
  }

  h2 {
    font-size: 1.5em;
    margin-bottom: 15px;
  }

  form {
    padding: 15px;
  }

  form button[type="submit"] {
    font-size: 1em;
  }

  .list-item strong {
    font-size: 1.1em;
  }
}
</style>