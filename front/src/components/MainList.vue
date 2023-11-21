<script setup>
import axios from "axios";
import { ref, onMounted, onUpdated, watch } from "vue";
import MainListItem from "./MainListItem.vue";
import { usePlaceStore } from "@/stores/places";
import { storeToRefs } from "pinia";

// import TempListFile from "./TempListFile.vue";
const store = usePlaceStore();
const { getList } = store;
const { placeList, isLocationSelect } = storeToRefs(store);
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

const getPlaceList = async () => {
  await getList();
  console.log("getPlaceList");
  console.log("result");
  places.value = placeList.value;
  visiblePlaces.value = places.value.slice(0, limit);
  offset.value = limit;
};
getPlaceList();

watch(isLocationSelect, (newisLocationSelect, oldisLocationSelect) => {
  if (newisLocationSelect != oldisLocationSelect) {
    getPlaceList();
  }
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
