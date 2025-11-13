import axios from 'axios'

const api = axios.create({
	baseURL: "http://Localhost:8085/api"
})

export default api