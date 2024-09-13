import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:5173',
    // baseURL: process.env.VUE_APP_BASE_URL,
    timeout: 10000,
});

axiosInstance.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;

}, (error) => {
    return Promise.reject(error);
});

// 응답 인터셉터
axiosInstance.interceptors.response.use((response) => {
    return response;
}, (error) => {
    if (error.response.status === 401) {
        // 인증 실패 시 처리 로직
        localStorage.removeItem('token');
    }
    return Promise.reject(error);
});

export default axiosInstance;