<script setup>
import axios from "axios";
import { ref, onMounted } from "vue";
import MainListItem from "./MainListItem.vue";

const places = ref([]);

const curNum = ref(0);

const loadList = () => {
  curNum.value++;
  console.log(curNum.value);
};
const getPlaceList = () => {
  console.log("캠핑장리스트 얻기");

  axios({
    method: "get",
    url: "http://localhost:8080/" + "trip/place/list",
  })
    .then((res) => {
      console.log(res);
      console.log("받아오기성공");
      places.value = res.data;
    })
    .catch((error) => {
      console.log(error);
    });
};
onMounted(() => {
  getPlaceList();
});
</script>

<template>
  <ul>
    <MainListItem v-for="place in places" @vue:mounted="loadList" :data="{ place, curNum }">
    </MainListItem>
  </ul>
</template>

<style scoped>
ul {
  width: 90%;
  margin-top: 5%;
  margin-left: 5%;
  margin-right: 5%;
  text-align: center;
}
</style>
