import { createRouter, createWebHistory } from "vue-router";
import KakaoComponent from "@/components/KakaoComponent.vue";
import GoogleComponent from "@/components/GoogleComponent.vue";
import DetailView from "@/views/DetailView.vue";
import MainView from "@/views/MainView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
    },
    {
      path: "/:contentId",
      name: "detail",
      component: DetailView,
      props: true,
    },
    {
      path: "/kakao",
      name: "kakao",
      component: KakaoComponent,
    },
    {
      path: "/google",
      name: "google",
      component: GoogleComponent,
    },
  ],
});

export default router;
