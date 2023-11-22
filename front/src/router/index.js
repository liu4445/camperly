import { createRouter, createWebHistory } from "vue-router";
import MainView from "@/views/MainView.vue";
import KakaoComponent from "@/components/KakaoComponent.vue";
import GoogleComponent from "@/components/GoogleComponent.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
    },
    {
      path: "/kakao",
      name: "kakao",
      component: KakaoComponent
    },
    {
      path: "/google",
      name: "google",
      component: GoogleComponent
    }
  ],
});

export default router;
