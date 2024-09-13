<script setup>
import HelloWorld from "./components/HelloWorld.vue";
import {useAuthStore} from "./stores/authStore.js";
import {ref} from "vue";
const authStore = useAuthStore();
const logout = () => {
  alert('logout')
  authStore.clearToken();
}
const username = ref('');
const password = ref('');
const errorMessage = ref('');
const isAuthenticated = ref(authStore.isAuthenticated);
const loginUser = async () => {
  const success = await authStore.login(username.value, password.value);
  if (success) {
    router.push('/suits'); // 로그인 성공 시 사건 목록 페이지로 이동
  } else {
    errorMessage.value = 'Invalid username or password';
  }
};
</script>

<template>
  <div>
    <a href="https://vitejs.dev" target="_blank">
      <img src="/vite.svg" class="logo" alt="Vite logo" />
    </a>
    <a href="https://vuejs.org/" target="_blank">
      <img src="./assets/vue.svg" class="logo vue" alt="Vue logo" />
    </a>
  </div>
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

  <HelloWorld msg="Vite + Vue" />
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
