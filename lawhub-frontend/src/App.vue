<script setup>
import HelloWorld from "./components/HelloWorld.vue";
import {useAuthStore} from "./stores/authStore.js";
import {computed, ref} from "vue";
const authStore = useAuthStore();
const logout = () => {
  authStore.clearToken();
  router.go()
}
const username = ref('');
const password = ref('');
const errorMessage = ref('');
const isAuthenticated = computed(() => authStore.isAuthenticated); // 반응형으로 처리
const loginUser = async () => {
  const success = await authStore.login(username.value, password.value);
  if (success) {
    router.go()
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

<!--  {{isAuthenticated}}-->
  <template v-if="isAuthenticated">
    <div>
      <p v-if="isAuthenticated">You are logged in!</p>
      <p v-else>Please log in.</p>
      <button @click="logout">Logout</button>
    </div>
  </template>
  <template v-else>
    <div class="login">
      <h1>Login</h1>
      <form @submit.prevent="loginUser">
        <div>
          <label for="username">Username</label>
          <input type="text" v-model="username" required />
        </div>
        <div>
          <label for="password">Password</label>
          <input type="password" v-model="password" required />
        </div>
        <button type="submit">Login</button>
        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      </form>
    </div>
  </template>
  <div>
    <a target="_blank">
      <!-- Swagger UI로 이동하는 버튼 -->
      <button @click="openSwagger">Open API Documentation (Swagger UI)</button>
    </a>
    <a target="_blank">
      <!-- Swagger UI로 이동하는 버튼 -->
      <button @click="openSwagger">Open API Documentation (Swagger UI)</button>
    </a>
  </div>
</template>

<style scoped>
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
