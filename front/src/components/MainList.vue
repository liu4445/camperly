<script setup>
import axios from "axios";
import { ref, onMounted } from "vue";
import MainListItem from "./MainListItem.vue";
import TempListFile from "./TempListFile.vue";
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
      console.log(res);
      console.log("받아오기성공");
      places.value = res.data;
    })
    .catch((error) => {
      console.log(error);
    });
};

const tempPlaceList = () => {
  places.value = TempListFile;
};

onMounted(() => {
  // getPlaceList();
  tempPlaceList();
  visiblePlaces.value = places.value.slice(0, limit);
  offset.value = limit;
});
</script>

<template>
  <ul>
    <MainListItem
      v-for="place in visiblePlaces"
      :data="{ place }"
      v-model="curNum"
      :key="place.contentId"
    >
    </MainListItem>
    <div class="more">
      <button class="btn mb-3" v-if="offset < places.length" @click="readMore()">더 보기</button>
    </div>
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
.more {
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
