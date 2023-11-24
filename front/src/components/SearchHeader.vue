<script setup>
import { usePlaceStore } from "@/stores/places.js";
import { storeToRefs } from "pinia";
import { ref } from "vue";

const store = usePlaceStore();
const { json, isSearch } = storeToRefs(store);

const keyword = ref("");
const locationFlag = ref(false);
const nameFlag = ref(false);

const sendData = () => {
  if (isSearch.value == true) isSearch.value = false;
  else {
    isSearch.value = true;
  }

  console.log("sendData");
  json.value.keyword = keyword.value;
  json.value.locationFlag = locationFlag.value;
  json.value.nameFlag = nameFlag.value;
};

const prevent = (event) => {
  if (event.keyCode === 13) {
    event.preventDefault();
  }
};
</script>

<template>
  <form>
    <input
      type="checkbox"
      class="btn-check"
      id="camp-name"
      v-model="nameFlag"
      autocomplete="off"
      @keyup="sendData"
    />
    <label class="btn" for="camp-name">이름</label>

    <div class="vertical-line"></div>

    <input
      type="checkbox"
      class="btn-check"
      id="camp-location"
      v-model="locationFlag"
      autocomplete="off"
      @keyup="sendData"
    />
    <label class="btn" for="camp-location">지역</label>

    <div class="vertical-line"></div>

    <input
      type="text"
      class="form-control"
      id="keyword"
      :value="keyword"
      @input="keyword = $event.target.value"
      placeholder="검색어"
      @keyup="sendData"
      @keydown="prevent"
    />

    <svg
      @click="sendData"
      id="search-btn"
      xmlns="http://www.w3.org/2000/svg"
      width="16"
      height="16"
      fill="currentColor"
      class="bi bi-search"
      viewBox="0 0 16 16"
    >
      <path
        d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
      />
    </svg>
  </form>
</template>

<style scoped>
form {
  width: 20vw;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: left;
  border-radius: 50px;
  border: 1px solid gray;
  min-width: 100px;
  overflow: hidden;
}

.vertical-line {
  height: 30px;
  margin: 0px 7px 0px 7px;
  border-right: 1px solid;
}

.btn:first-of-type {
  margin-left: 10px;
}

.btn {
  align-content: center;
  justify-content: center;
  border-radius: 30px;
  min-width: 60px;
}

#keyword {
  margin-right: 5px;
  border-radius: 30px;
}

.bi-search {
  min-width: 25px;
  min-height: 25px;
  margin: 0px 15px 0px 2px;
  cursor: pointer;
}
</style>
