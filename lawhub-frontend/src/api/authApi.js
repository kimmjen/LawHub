import axiosInstance from "./axios.js";

// 사용자 로그인 API
export const login = async (credentials) => {
    const response = await axiosInstance.post('/auth/login', credentials);
    return response.data;
};

// 사용자 회원가입 API
export const signup = async (userData) => {
    const response = await axiosInstance.post('/auth/signup', userData);
    return response.data;
};