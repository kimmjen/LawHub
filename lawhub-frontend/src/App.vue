<script setup>
import HelloWorld from "./components/HelloWorld.vue";
import {useAuthStore} from "./stores/authStore.js";
import {computed, ref} from "vue";
import {useRouter} from "vue-router";

const authStore = useAuthStore();
const router = useRouter();

const logout = () => {
  authStore.clearToken();
  window.location.reload(); // 페이지 새로고침으로 변경
}
const username = ref('');
const password = ref('');
const errorMessage = ref('');
const isAuthenticated = computed(() => authStore.isAuthenticated); // 반응형으로 처리
const loginUser = async () => {
  const success = await authStore.login(username.value, password.value);
  if (success) {
    window.location.reload(); // 페이지 새로고침으로 변경
    // router.push('/suits'); // 로그인 성공 시 사건 목록 페이지로 이동
  } else {
    errorMessage.value = 'Invalid username or password';
  }
};
const openSwagger = () => {
  // Swagger UI 경로로 새로운 탭에서 이동
  window.open('http://localhost:8080/swagger-ui/index.html', '_blank');
};
</script>

<template>
  <div class="app-container">
    <!-- 로그인 후 상태 -->
    <div v-if="isAuthenticated" class="user-info">
      <h2>🏛️ LawHub</h2>
      <p>법률 관리 시스템에 오신 것을 환영합니다!</p>
      <p>현재 <strong>로그인</strong> 상태입니다.</p>
      <button @click="logout" class="logout-btn">로그아웃</button>
    </div>
    
    <!-- 로그인 폼 -->
    <div v-else class="login">
      <h1>🏛️ LawHub</h1>
      <p>법률 관리 시스템</p>
      <form @submit.prevent="loginUser">
        <div class="form-group">
          <label for="username">사용자명</label>
          <input 
            id="username"
            type="text" 
            v-model="username" 
            placeholder="사용자명을 입력하세요"
            required 
          />
        </div>
        <div class="form-group">
          <label for="password">비밀번호</label>
          <input 
            id="password"
            type="password" 
            v-model="password" 
            placeholder="비밀번호를 입력하세요"
            required 
          />
        </div>
        <button type="submit">로그인</button>
        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      </form>
    </div>
  </div>
  
  <!-- API 문서 버튼 -->
  <div class="api-docs">
    <button @click="openSwagger">
      📋 API 문서
    </button>
  </div>
</template>

<style scoped>
/* 전체 레이아웃 */
.app-container {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

/* 로그인 폼 컨테이너 */
.login {
  background: white;
  border-radius: 12px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  padding: 2.5rem;
  width: 100%;
  max-width: 400px;
  margin: 1rem;
}

.login h1 {
  color: #1e3a8a;
  font-size: 1.875rem;
  font-weight: 700;
  margin-bottom: 0.5rem;
  text-align: center;
}

.login p {
  color: #6b7280;
  text-align: center;
  margin-bottom: 2rem;
}

/* 폼 스타일 */
.login form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.login label {
  font-weight: 500;
  color: #374151;
  font-size: 0.875rem;
}

.login input {
  padding: 0.75rem 1rem;
  border: 1px solid #d1d5db;
  border-radius: 8px;
  font-size: 1rem;
  transition: border-color 0.2s ease, box-shadow 0.2s ease;
  background: white;
  color: #111827;
}

.login input:focus {
  outline: none;
  border-color: #2563eb;
  box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
}

.login button {
  background: linear-gradient(135deg, #1e3a8a 0%, #3b82f6 100%);
  color: white;
  border: none;
  padding: 0.875rem 1.5rem;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s ease;
  margin-top: 0.5rem;
}

.login button:hover {
  transform: translateY(-1px);
  box-shadow: 0 4px 12px rgba(30, 58, 138, 0.4);
}

.login button:active {
  transform: translateY(0);
}

/* 에러 메시지 */
.error {
  color: #ef4444;
  font-size: 0.875rem;
  text-align: center;
  margin-top: 0.5rem;
  padding: 0.5rem;
  background: #fef2f2;
  border: 1px solid #fecaca;
  border-radius: 6px;
}

/* 로그인 후 상태 */
.user-info {
  background: white;
  border-radius: 12px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  padding: 2rem;
  text-align: center;
  margin: 1rem;
  max-width: 500px;
}

.user-info h2 {
  color: #1e3a8a;
  margin-bottom: 1rem;
}

.user-info p {
  color: #6b7280;
  margin-bottom: 1.5rem;
}

.logout-btn {
  background: #ef4444;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s ease;
}

.logout-btn:hover {
  background: #dc2626;
  transform: translateY(-1px);
}

/* API 문서 버튼 */
.api-docs {
  position: fixed;
  bottom: 2rem;
  right: 2rem;
  display: flex;
  gap: 1rem;
}

.api-docs button {
  background: #059669;
  color: white;
  border: none;
  padding: 0.75rem 1rem;
  border-radius: 8px;
  font-size: 0.875rem;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s ease;
  box-shadow: 0 4px 12px rgba(5, 150, 105, 0.3);
}

.api-docs button:hover {
  background: #047857;
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(5, 150, 105, 0.4);
}

/* 반응형 디자인 */
@media (max-width: 640px) {
  .login, .user-info {
    padding: 1.5rem;
    margin: 0.5rem;
  }
  
  .api-docs {
    position: static;
    justify-content: center;
    margin-top: 2rem;
  }
}

.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
