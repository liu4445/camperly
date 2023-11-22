<script setup>
import "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js";
import { ref, onUpdated, watch } from "vue";

import MoreSelectModal from "@/components/MoreSelectModal.vue";
import { usePlaceStore } from "@/stores/places.js";
import { storeToRefs } from "pinia";
const store = usePlaceStore();
const { json, isLocationSelect } = storeToRefs(store);

const MoreSelectOpen = ref(false);
const LocationData = ref([]);
const changeMoreSelect = () => {
  console.log(MoreSelectOpen.value);
  if (MoreSelectOpen.value) MoreSelectOpen.value = false;
  else MoreSelectOpen.value = true;
};

watch(LocationData, (newLocationData, oldLocationData) => {
  if (newLocationData != oldLocationData) {
    json.value.locationTypes = LocationData.value;
  }
  if (isLocationSelect.value == true) isLocationSelect.value = false;
  else {
    isLocationSelect.value = true;
  }
});
</script>

<template>
  <div class="selectArea">
    <ul>
      <li class="type">
        <input
          type="checkbox"
          id="beach"
          name="locationTypes"
          value="해변"
          class="rounded-circle w-100 h-100"
          v-model="LocationData"
        />
        <label for="beach" class="rounded-circle">
          <img
            src="@/assets/img/pictogram/location/free-icon-sunbed-7759411.png"
            alt
            width="40"
            height="40"
          />
          <div>해변</div>
        </label>
      </li>
      <li>
        <input
          type="checkbox"
          id="island"
          name="locationTypes"
          value="섬"
          class="rounded-circle w-100 h-100"
          v-model="LocationData"
        />
        <label for="island" class="rounded-circle">
          <img
            src="@/assets/img/pictogram/location/free-icon-island-11076490.png"
            alt
            width="40"
            height="40"
          />
          <div>섬</div>
        </label>
      </li>
      <li>
        <input
          type="checkbox"
          id="moutain"
          name="locationTypes"
          value="산"
          class="rounded-circle w-100 h-100"
          v-model="LocationData"
        />
        <label for="moutain" class="rounded-circle">
          <img
            src="@/assets/img/pictogram/location/free-icon-mountain-7894097.png"
            alt
            width="40"
            height="40"
          />
          <div>산</div>
        </label>
      </li>
      <li>
        <input
          type="checkbox"
          id="forest"
          name="locationTypes"
          value="숲"
          class="rounded-circle w-100 h-100"
          v-model="LocationData"
        />
        <label for="forest" class="rounded-circle">
          <img
            src="@/assets/img/pictogram/location/free-icon-pine-tree-10415456.png"
            alt
            width="40"
            height="40"
          />
          <div>숲</div>
        </label>
      </li>
      <li>
        <input
          type="checkbox"
          id="valley"
          name="locationTypes"
          value="계곡"
          class="rounded-circle w-100 h-100"
          v-model="LocationData"
        />
        <label for="valley" class="rounded-circle">
          <img
            src="@/assets/img/pictogram/location/free-icon-waterfall-7441627.png"
            width="40"
            height="40"
          />
          <div>계곡</div>
        </label>
      </li>
      <li>
        <input
          type="checkbox"
          id="river"
          name="locationTypes"
          value="강"
          class="rounded-circle w-100 h-100"
          v-model="LocationData"
        />
        <label for="river" class="rounded-circle">
          <img
            src="@/assets/img/pictogram/location/free-icon-river-9997808.png"
            width="40"
            height="40"
          />
          <div>강</div>
        </label>
      </li>
      <li>
        <input
          type="checkbox"
          id="lake"
          name="locationTypes"
          value="호수"
          class="rounded-circle w-100 h-100"
          v-model="LocationData"
        />
        <label for="lake" class="rounded-circle">
          <img
            src="@/assets/img/pictogram/location/free-icon-lake-3105252.png"
            width="40"
            height="40"
          />
          <div>호수</div>
        </label>
      </li>
      <li>
        <input
          type="checkbox"
          id="city"
          name="locationTypes"
          value="도심"
          class="rounded-circle w-100 h-100"
          v-model="LocationData"
        />
        <label for="city" class="rounded-circle">
          <img
            src="@/assets/img/pictogram/location/free-icon-cityscape-618859.png"
            alt
            width="40"
            height="40"
          />
          <div>도심</div>
        </label>
      </li>

      <li>
        <button
          type="button"
          class="btn more-select-btn rounded-5 border-1"
          @click="changeMoreSelect"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 32 32"
            aria-hidden="true"
            role="presentation"
            focusable="false"
            style="
              display: block;
              fill: none;
              height: 22px;
              width: 22px;
              stroke: currentcolor;
              stroke-width: 4;
              overflow: visible;
            "
          >
            <path
              fill="none"
              d="M7 16H3m26 0H15M29 6h-4m-8 0H3m26 20h-4M7 16a4 4 0 1 0 8 0 4 4 0 0 0-8 0zM17 6a4 4 0 1 0 8 0 4 4 0 0 0-8 0zm0 20a4 4 0 1 0 8 0 4 4 0 0 0-8 0zm0 0H3"
            ></path>
          </svg>
        </button>
      </li>
    </ul>
  </div>
  <MoreSelectModal
    :data="MoreSelectOpen"
    :location-types="LocationData"
    @more-select="changeMoreSelect"
  />
</template>

<style scoped>
.selectArea {
  margin-top: 50px;
  list-style: none;
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  width: 100%;
  height: auto;
}
ul {
  width: 60%;
  list-style: none;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
  padding: 0px;
  vertical-align: middle;
}
li {
  display: inline-block;

  margin-left: 14.5px;
  margin-right: 14.5px;
  vertical-align: middle;
  text-align: center;
  width: 70px;
  height: 70px;
}
li .btn {
  margin-top: 16px;
}
li img {
  margin-bottom: 5px;
}
a {
  text-decoration: none;
  color: black;
}
.more-select-btn {
  border: 1px solid;
}
input[type="checkbox"]:hover {
  box-shadow: 200px 0 0 0 rgba(0, 0, 0, 0.05) inset, -200px 0 0 0 rgba(0, 0, 0, 0.05) inset;
}
input[type="checkbox"] {
  background-color: rgba(70, 84, 90, 0.35);

  width: 60px;
  margin-top: 15px;
  margin-bottom: auto;
  display: flex;
  justify-content: center;
  font-size: smaller;
  font-weight: bold;
  cursor: pointer;
}
input[type="checkbox"]:hover + label {
  width: 70px;
  height: 70px;
  box-shadow: 200px 0 0 0 rgba(135, 206, 235, 0.15) inset,
    -200px 0 0 0 rgba(135, 206, 235, 0.15) inset;
}
input[type="checkbox"] {
  display: none;
  cursor: pointer;
}
input[type="checkbox"] + label {
  cursor: pointer;
}
input[type="checkbox"]:checked + label {
  background: rgba(163, 179, 185, 0.15);
  width: 70px;
  height: 70px;
}
</style>
