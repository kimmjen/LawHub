import { defineStore} from "pinia";
import {computed, ref} from "vue";
import axios from "axios";

export const useAuthStore = defineStore("authStore", () =>{
    const token = ref(localStorage.getItem('token'|| null));

    // 로그인 시 토큰 저장
    const setToken = (newToken) => {
        token.value = newToken;
        localStorage.setItem('token', newToken); // localStorage에 토큰 저장
    };

    // 로그아웃 시 토큰 제거
    const clearToken = () => {
        token.value = null;
        localStorage.removeItem('token'); // localStorage에서 토큰 제거
    };

    // 인증 여부 확인
    const isAuthenticated = computed(() => !!token.value);

    // 로그인 api 호출
    const login = async (username, password) => {
        try {
            const response = await axios.post('http://localhost:8080/auth/login', {
                username,
                password
            });

            setToken(response.data.token)
            return true;
        } catch (error) {
            console.log(error);
            return false;
        }
    }

    return {
        token,
        login,
        setToken,
        clearToken,
        isAuthenticated
    };
})