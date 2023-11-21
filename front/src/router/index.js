import { createRouter, createWebHistory } from "vue-router";
import MainView from "@/views/MainView.vue";
import DetailView from "@/views/DetailView.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
    },
    {
      path: "/:{contentId}",
      name: "detail",
      component: DetailView,
    },
  ],
});

export default router;
