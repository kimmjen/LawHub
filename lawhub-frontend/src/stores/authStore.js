import { defineStore} from "pinia";
import {ref} from "vue";

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

    // 인증 여부 확인 (computed처럼 활용)
    const isAuthenticated = () => !!token.value;

    return {
        token,
        setToken,
        clearToken,
        isAuthenticated
    };
})