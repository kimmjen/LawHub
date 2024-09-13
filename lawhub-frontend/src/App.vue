<script setup>
import HelloWorld from "./components/HelloWorld.vue";
import {useAuthStore} from "./stores/authStore.js";
import {ref} from "vue";
const authStore = useAuthStore();
const logout = () => {
  alert('logout')
  authStore.clearToken();
}
const isAuthenticated = ref(authStore.isAuthenticated());

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
  <div>
    <p v-if="isAuthenticated">You are logged in!</p>
    <p v-else>Please log in.</p>
    <button @click="logout">Logout</button>
  </div>
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
