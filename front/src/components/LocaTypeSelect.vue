<script setup>
import "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js";
import { ref } from "vue";
import MoreSelectModal from "@/components/MoreSelectModal.vue";
const BeachBtnColor = ref("transparent");
const IslandBtnColor = ref("transparent");
const MoutainBtnColor = ref("transparent");
const ForestBtnColor = ref("transparent");
const ValleyBtnColor = ref("transparent");
const RiverBtnColor = ref("transparent");
const LakeBtnColor = ref("transparent");
const CityBtnColor = ref("transparent");

const clickBeachBtn = () => {
  BeachBtnColor.value = "rgba(0, 0, 0, 0.05)";
  IslandBtnColor.value = "transparent";
  MoutainBtnColor.value = "transparent";
  ForestBtnColor.value = "transparent";
  ValleyBtnColor.value = "transparent";
  RiverBtnColor.value = "transparent";
  LakeBtnColor.value = "transparent";
  CityBtnColor.value = "transparent";
};
const clickIslandBtn = () => {
  BeachBtnColor.value = "transparent";
  IslandBtnColor.value = "rgba(0, 0, 0, 0.05)";
  MoutainBtnColor.value = "transparent";
  ForestBtnColor.value = "transparent";
  ValleyBtnColor.value = "transparent";
  RiverBtnColor.value = "transparent";
  LakeBtnColor.value = "transparent";
  CityBtnColor.value = "transparent";
};
const clickMoutainBtn = () => {
  BeachBtnColor.value = "transparent";
  IslandBtnColor.value = "transparent";
  MoutainBtnColor.value = "rgba(0, 0, 0, 0.05)";
  ForestBtnColor.value = "transparent";
  ValleyBtnColor.value = "transparent";
  RiverBtnColor.value = "transparent";
  LakeBtnColor.value = "transparent";
  CityBtnColor.value = "transparent";
};
const clickForestBtn = () => {
  BeachBtnColor.value = "transparent";
  IslandBtnColor.value = "transparent";
  MoutainBtnColor.value = "transparent";
  ForestBtnColor.value = "rgba(0, 0, 0, 0.05)";
  ValleyBtnColor.value = "transparent";
  RiverBtnColor.value = "transparent";
  LakeBtnColor.value = "transparent";
  CityBtnColor.value = "transparent";
};
const clickValleyBtn = () => {
  BeachBtnColor.value = "transparent";
  IslandBtnColor.value = "transparent";
  MoutainBtnColor.value = "transparent";
  ForestBtnColor.value = "transparent";
  ValleyBtnColor.value = "rgba(0, 0, 0, 0.05)";
  RiverBtnColor.value = "transparent";
  LakeBtnColor.value = "transparent";
  CityBtnColor.value = "transparent";
};
const clickRiverBtn = () => {
  BeachBtnColor.value = "transparent";
  IslandBtnColor.value = "transparent";
  MoutainBtnColor.value = "transparent";
  ForestBtnColor.value = "transparent";
  ValleyBtnColor.value = "transparent";
  RiverBtnColor.value = "rgba(0, 0, 0, 0.05)";
  LakeBtnColor.value = "transparent";
  CityBtnColor.value = "transparent";
};
const clickLakeBtn = () => {
  BeachBtnColor.value = "transparent";
  IslandBtnColor.value = "transparent";
  MoutainBtnColor.value = "transparent";
  ForestBtnColor.value = "transparent";
  ValleyBtnColor.value = "transparent";
  RiverBtnColor.value = "transparent";
  LakeBtnColor.value = "rgba(0, 0, 0, 0.05)";
  CityBtnColor.value = "transparent";
};
const clickCityBtn = () => {
  BeachBtnColor.value = "transparent";
  IslandBtnColor.value = "transparent";
  MoutainBtnColor.value = "transparent";
  ForestBtnColor.value = "transparent";
  ValleyBtnColor.value = "transparent";
  RiverBtnColor.value = "transparent";
  LakeBtnColor.value = "transparent";
  CityBtnColor.value = "rgba(0, 0, 0, 0.05)";
};

const MoreSelectOpen = ref(false);
const changeMoreSelect = () => {
  console.log(MoreSelectOpen.value);
  if (MoreSelectOpen.value) MoreSelectOpen.value = false;
  else MoreSelectOpen.value = true;
};
</script>

<template>
  <div class="selectArea">
    <ul>
      <li class="type">
        <button
          type="button"
          id="beach"
          class="type-btn btn-1 rounded-circle w-100 h-100"
          :style="{ backgroundColor: BeachBtnColor }"
          @click="clickBeachBtn()"
        >
          <img
            src="@/assets/img/pictogram/free-icon-sunbed-7759411.png"
            alt
            width="40"
            height="40"
          />
          <div>해변</div>
        </button>
      </li>
      <li>
        <button
          type="button"
          id="island"
          class="type-btn btn-1 rounded-circle w-100 h-100"
          :style="{ backgroundColor: IslandBtnColor }"
          @click="clickIslandBtn()"
        >
          <img
            src="@/assets/img/pictogram/free-icon-island-11076490.png"
            alt
            width="40"
            height="40"
          />
          <div>섬</div>
        </button>
      </li>
      <li>
        <button
          type="button"
          class="type-btn rounded-circle border-2 w-100 h-100"
          :style="{ backgroundColor: MoutainBtnColor }"
          @click="clickMoutainBtn()"
        >
          <img
            src="@/assets/img/pictogram/free-icon-mountain-7894097.png"
            alt
            width="40"
            height="40"
          />
          <div>산</div>
        </button>
      </li>
      <li>
        <button
          type="button"
          class="type-btn rounded-circle border-2 w-100 h-100"
          :style="{ backgroundColor: ForestBtnColor }"
          @click="clickForestBtn()"
        >
          <img
            src="@/assets/img/pictogram/free-icon-pine-tree-10415456.png"
            alt
            width="40"
            height="40"
          />
          <div>숲</div>
        </button>
      </li>
      <li>
        <button
          type="button"
          class="type-btn rounded-circle border-2 w-100 h-100"
          :style="{ backgroundColor: ValleyBtnColor }"
          @click="clickValleyBtn()"
        >
          <img
            src="@/assets/img/pictogram/free-icon-waterfall-7441627.png"
            width="40"
            height="40"
          />
          <div>계곡</div>
        </button>
      </li>
      <li>
        <button
          type="button"
          class="type-btn rounded-circle border-2 w-100 h-100"
          :style="{ backgroundColor: RiverBtnColor }"
          @click="clickRiverBtn()"
        >
          <img src="@/assets/img/pictogram/free-icon-river-9997808.png" width="40" height="40" />
          <div>강</div>
        </button>
      </li>
      <li>
        <button
          type="button"
          class="type-btn rounded-circle border-2 w-100 h-100"
          :style="{ backgroundColor: LakeBtnColor }"
          @click="clickLakeBtn()"
        >
          <img src="@/assets/img/pictogram/free-icon-lake-3105252.png" width="40" height="40" />
          <div>호수</div>
        </button>
      </li>
      <li>
        <button
          type="button"
          class="type-btn rounded-circle border-2 w-100 h-100"
          :style="{ backgroundColor: CityBtnColor }"
          @click="clickCityBtn()"
        >
          <img
            src="@/assets/img/pictogram/free-icon-cityscape-618859.png"
            alt
            width="40"
            height="40"
          />
          <div>도심</div>
        </button>
      </li>
      <li></li>
      <li>
        <button type="button" class="more-select-btn btn rounded-5" @click="changeMoreSelect">
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
  <MoreSelectModal :data="MoreSelectOpen" @more-select="changeMoreSelect" />
</template>

<style scoped>
.selectArea {
  margin-top: 10px;
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
}
li {
  display: inline-block;
  margin-top: 20px;
  margin-bottom: 10px;
  margin-left: 11px;
  margin-right: 18px;
  vertical-align: middle;
  text-align: center;
  width: 70px;
  height: 70px;
}
img {
  margin-bottom: 5px;
}
a {
  text-decoration: none;
  color: black;
}
.type-btn {
  border: none;
}

.type-btn:hover {
  box-shadow: 200px 0 0 0 rgba(0, 0, 0, 0.05) inset, -200px 0 0 0 rgba(0, 0, 0, 0.05) inset;
}
.type-btn:active {
  box-shadow: 200px 0 0 0 rgba(0, 0, 0, 0.05) inset, -200px 0 0 0 rgba(0, 0, 0, 0.05) inset;
}
.more-select-btn {
  background-color: rgba(135, 206, 235, 0.35);

  width: 60px;
  margin-top: 15px;
  margin-bottom: auto;
  display: flex;
  justify-content: center;
  font-size: smaller;
  font-weight: bold;
}
.more-select-btn:hover {
  box-shadow: 200px 0 0 0 rgba(135, 206, 235, 0.15) inset,
    -200px 0 0 0 rgba(135, 206, 235, 0.15) inset;
}
</style>
