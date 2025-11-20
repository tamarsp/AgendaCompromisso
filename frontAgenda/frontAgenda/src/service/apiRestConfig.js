import axios from 'axios'

const api = axios.create({
    baseURL: "https://agendacompromisso-production.up.railway.app"
})

export default api