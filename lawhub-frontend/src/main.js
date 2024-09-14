import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { createPinia } from "pinia";
import router from 'vue-router';


const app = createApp(App);
// createApp(App).mount('#app')

app.use(createPinia());
app.use(router);
app.mount('#app');