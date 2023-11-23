import { createRouter, createWebHistory } from "vue-router";
import KakaoComponent from "@/components/KakaoComponent.vue";
import GoogleComponent from "@/components/GoogleComponent.vue";
import DetailView from "@/views/DetailView.vue";
import MainView from "@/views/MainView.vue";
import FavoriteView from "@/views/FavoriteView.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
    },
    {
      path: "/camping/place/:contentId",
      name: "detail",
      component: DetailView,
      props: true,
      scrollBehavior() {
        return { top: 0 };
      },
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
    {
      path: "/camping/favorite",
      name: "favorite",
      component: FavoriteView,
    },
  ],
});

export default router;
