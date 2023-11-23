<script setup>
import { ref, onMounted, onUpdated, watch } from "vue";
import MainListItem from "@/components/MainListItem.vue";
import SkeletonItem from "@/components/skeleton/SkeletonItem.vue";
import axios from "axios";
const { VITE_VUE_API_URL } = import.meta.env;

// import TempListFile from "./TempListFile.vue";
const index = ref(10);
const places = ref([]);
const getFavoriteList = () => {
  const token = localStorage.getItem("token");
  console.log("찜리스트  로드.", token);
  axios({
    method: "get",
    url: VITE_VUE_API_URL + "favorite/list",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((res) => {
      console.log("checkLike", res.data);
      places.value = res.data;
      if (places.value.length === 0) {
        index.value = 0;
      }
    })

    .catch((error) => {
      console.log(error);
    });
};

onMounted(() => {
  getFavoriteList();
});
</script>

<template>
  <div class="mainList">
    <ul>
      <template v-if="places.length === 0">
        <SkeletonItem v-for="i in index" />
      </template>
      <template v-else>
        <MainListItem v-for="place in places" :data="{ place }" :key="place.contentId">
        </MainListItem>
      </template>
    </ul>
  </div>
  <div class="more">
    <button class="btn mb-3" v-if="places.length >= 20" @click="readMore()">더 보기</button>
  </div>
</template>

<style scoped>
.mainList {
  padding-left: 5%;
  padding-right: 5%;
}
ul {
  padding: 0px;
  width: 100%;
  margin-top: 2%;

  text-align: center;
  align-items: center;
}
.more {
  display: block;
  text-align: center;
}
.more .btn {
  width: 100px;
  height: 50px;
  text-align: center;
  background-color: darkslateblue;
  color: white;
}
</style>
