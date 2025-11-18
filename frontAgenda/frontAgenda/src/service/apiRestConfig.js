import axios from 'axios'

const api = axios.create({
	baseURL: "https://agendacompromisso-production.up.railway.app"
	/*http://Localhost:8085/api*/
	
})

export default api