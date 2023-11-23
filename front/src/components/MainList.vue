<script setup>
import { ref, onMounted, onUpdated, watch } from "vue";
import MainListItem from "@/components/MainListItem.vue";
import { usePlaceStore } from "@/stores/places";
import { storeToRefs } from "pinia";
import SkeletonItem from "@/components/skeleton/SkeletonItem.vue";

// import TempListFile from "./TempListFile.vue";
const store = usePlaceStore();
const { getList, putList } = store;
const { placeList, isLocationSelect, isMoreSelect, isSearch } = storeToRefs(store);
const places = ref([]);
const index = ref(10);

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

const validateZero = async () => {
  await getPlaceList();
  if (places.value.length === 0) {
    index.value = 0;
  }
}

watch(isLocationSelect, (newisLocationSelect, oldisLocationSelect) => {
  if (newisLocationSelect != oldisLocationSelect) {
    index.value = Math.min(places.value.length, 10);
    places.value.length = 0;
    validateZero();
  }
});

watch(isMoreSelect, (newisMoreSelect, oldisMoreSelect) => {
  if (newisMoreSelect != oldisMoreSelect) {
    index.value = Math.min(places.value.length, 10);
    places.value.length = 0;
    validateZero();
  }
});
watch(isSearch, (newisSearch, oldisSearch) => {
  if (newisSearch != oldisSearch) {
    index.value = Math.min(places.value.length, 10);
    places.value.length = 0;
    validateZero();
  }
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
