import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_BACKEND_BASE_URL,
});

api.interceptors.request.use(async (config) => {
  const token = localStorage.getItem("token") ?? "";

  config.headers.Authorization = `Bearer ${token}`;
  return config;
});

export default api;
