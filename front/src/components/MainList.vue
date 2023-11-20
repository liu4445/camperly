<script setup>
import axios from "axios";
import { ref, onMounted } from "vue";
import MainListItem from "./MainListItem.vue";
// import TempListFile from "./TempListFile.vue";

const places = ref([]);
const visiblePlaces = ref([]);
const limit = 20;
const offset = ref(0);
const curNum = ref(0);

const readMore = () => {
  const startIdx = visiblePlaces.value.length;
  const endIdx = startIdx + limit;
  visiblePlaces.value = visiblePlaces.value.concat(places.value.slice(startIdx, endIdx));
  offset.value = endIdx;
};

const getPlaceList = () => {
  console.log("캠핑장리스트 얻기");

  axios({
    method: "get",
    url: "http://localhost:8080/" + "trip/place/list",
  })
    .then((res) => {
      console.log(res.data);
      console.log("받아오기성공");
      places.value = res.data;
      console.log(places.value);
      visiblePlaces.value = places.value.slice(0, limit);
      offset.value = limit;
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
  <div class="mainList">
    <ul>
      <MainListItem
        v-for="place in visiblePlaces"
        :data="{ place }"
        v-model="curNum"
        :key="place.contentId"
      >
      </MainListItem>
    </ul>
  </div>
  <div class="more">
    <button class="btn mb-3" v-if="offset < places.length" @click="readMore()">더 보기</button>
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
