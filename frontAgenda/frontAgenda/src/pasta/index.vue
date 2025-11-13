<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="login">
      <input v-model="email" type="email" placeholder="E-mail" required />
      <input v-model="senha" type="password" placeholder="Senha" required />
      <button type="submit">Entrar</button>
    </form>

    <p>
      Não tem conta?
      <router-link to="/cadastro">Cadastrar-se</router-link>
    </p>
  </div>
</template>

<script>
import api from "../service/apiRestConfig"

export default {
  data() {
    return {
      email: "",
      senha: ""
    }
  },
  methods: {
    async login() {
      try {
        const response = await api.post("/usuarios/login", {
          email: this.email,
          senha: this.senha
        })
        alert("Login realizado com sucesso!")
        this.$router.push("/inicio")
      } catch (error) {
        alert("Erro ao fazer login!")
      }
    }
  }
}
</script>
<style scoped>
.login-container {
  max-width: 400px;
  margin: 80px auto;
  padding: 40px;
  border-radius: 12px;
  background: #ffffff;
  box-shadow: 0 0 12px rgba(0, 0, 0, 0.1);
  text-align: center;
  font-family: Arial, sans-serif;
}

h2 {
  color: #333;
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

input {
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 15px;
}

input:focus {
  border-color: #42b883; /* cor do Vue */
  outline: none;
  box-shadow: 0 0 4px #42b88366;
}

button {
  background-color: #42b883;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  padding: 12px;
  cursor: pointer;
  transition: background 0.3s;
}

button:hover {
  background-color: #36976c;
}

p {
  margin-top: 15px;
  color: #555;
}

a {
  color: #42b883;
  text-decoration: none;
  font-weight: bold;
}

a:hover {
  text-decoration: underline;
}
</style>
