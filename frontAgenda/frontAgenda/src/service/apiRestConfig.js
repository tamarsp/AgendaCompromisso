import axios from "axios"

const api = axios.create({
  baseURL: "https://agendacompromisso-production.up.railway.app", 
  headers: {
    "Content-Type": "application/json"
  }
})

api.interceptors.request.use((config) => {
  const usuarioId = localStorage.getItem("usuarioId")
  if (usuarioId) {
    config.headers["Usuario-Id"] = usuarioId
  }
  return config
})

export default api