<script setup>
import { ref, onMounted, onUpdated, watch } from "vue";
import MainListItem from "@/components/MainListItem.vue";
import { usePlaceStore } from "@/stores/places";
import { storeToRefs } from "pinia";

// import TempListFile from "./TempListFile.vue";
const store = usePlaceStore();
const { getList, putList } = store;
const { placeList, isLocationSelect, isMoreSelect, isSearch } = storeToRefs(store);
const places = ref([]);

const readMore = () => {
  putList();
};

const getPlaceList = async () => {
  await getList();
  console.log("getPlaceList");
  console.log("result");
  places.value = placeList.value;
};
getPlaceList();

watch(isLocationSelect, (newisLocationSelect, oldisLocationSelect) => {
  if (newisLocationSelect != oldisLocationSelect) {
    getPlaceList();
  }
});

watch(isMoreSelect, (newisMoreSelect, oldisMoreSelect) => {
  if (newisMoreSelect != oldisMoreSelect) {
    getPlaceList();
  }
});
watch(isSearch, (newisSearch, oldisSearch) => {
  if (newisSearch != oldisSearch) {
    getPlaceList();
  }
});
</script>

<template>
  <div class="mainList">
    <ul>
      <MainListItem v-for="place in places" :data="{ place }" :key="place.contentId">
      </MainListItem>
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
