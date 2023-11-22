<script setup>
import LikeButton from "./LikeButton.vue";
import { onMounted, ref } from "vue";
const { VITE_VUE_API_URL } = import.meta.env;
import axios from "axios";
import { useRoute } from "vue-router";
import WeatherCharts from "./WeatherCharts.vue";
import Map from "./Map.vue";
const place = ref([]);
const imageList = ref([]);
const route = useRoute();
onMounted(() => {
  getDetail();
});
const contentId = ref(0);
contentId.value = route.params.contentId;

function getDetail() {
  const params = contentId.value;
  console.log("디테일 로드.", params);
  axios({
    method: "get",
    url: VITE_VUE_API_URL + "camping/place/" + params,
  })
    .then((res) => {
      place.value = res.data.campingPlaceDto;
      imageList.value = res.data.imageUrls;

      console.log(place.value);
      console.log(imageList.value);
      console.log("성공");
    })
    .catch((error) => {
      console.log(error);
    });
}
</script>

<template>
  <div class="main">
    <div class="title">
      <h1>{{ place.campsiteName }}</h1>

      <div class="likebtn">
        <LikeButton :content-id="contentId" />
      </div>
      <hr style="border-width: 2px" />
    </div>
    <div class="graphic">
      <span class="image"
        ><img
          :src="place.firstImageUrl"
          onerror="this.src='src/assets/img/no-photo.jpg'; this.style.width='80%';"
      /></span>
      <span class="map">
        <Map :x="place.mapX" :y="place.mapY"></Map>
      </span>
    </div>
    <hr />
    <div class="information-intro">
      <div>tel {{ place.tel }}</div>
      <div>
        <a :href="place.homepage">홈페이지로 가기</a>
      </div>
      <div>{{ place.reserveType }}</div>
      <div>{{ place.address }}</div>
      <div class="like">♥ 11</div>
    </div>
    <hr />
    <div class="information-body">
      <div class="subfacltlist">
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia aspernatur quaerat nulla
        voluptates beatae odio consectetur alias sapiente, dicta recusandae cupiditate aut illo
        voluptatibus suscipit fugiat! Neque esse aperiam commodi?
      </div>
      <hr />
      <div class="body-top">
        <div class="body-data">
          <div class="data-top">
            <div class="data-top-left">
              <ul>
                <li>{{ place.induty }}</li>
                <li>{{ place.facltDivName }}</li>
                <li>{{ place.mangeDivName }}</li>
                <li>{{ place.locationType }}</li>
              </ul>
            </div>
            <div class="data-top-right">
              <ul>
                <li>운영계절{{ place.oeprSeason }}</li>
                <li>운영시간{{ place.operDate }}</li>
                <li>동물{{ place.animal }}</li>
              </ul>
            </div>
          </div>
          <div class="data-bottom">
            <ul>
              <li>카라반({{ place.caravanSiteCnt }})</li>
              <li>기타 부대 시설 : {{ place.etcFacilities }}</li>
              <li>카라반 내부 시설(vif) : {{ place.etcCaravanFacilities }}</li>
              <li>화로대 : {{ place.brazier }}</li>
              <li>안전시설현황 :</li>
              <li>{{ place.etcGlampingFacilities }}</li>
              <li>{{ place.experience }}</li>
              <li>이것저것추가</li>
            </ul>
          </div>
        </div>
        <div class="body-weather">
          <WeatherCharts :content-id="contentId" />
        </div>
      </div>
      <hr />
      <div class="body-text">
        {{ place.intro }}
      </div>
    </div>
    <div class="photo-list">
      <div class="photo" v-for="image in imageList" :key="imageList.contentId">
        <img :src="image.imgUrl" onerror="this.style.display='none';" />
      </div>
    </div>
  </div>
</template>

<style scoped>
* {
  /* border: 0.1px solid; */
}
ul {
  list-style: none;
  padding: 10px;
}

a {
  text-decoration: none;
  color: black;
  font-weight: bolder;
}
.main {
  margin-top: 3%;
  width: 60%;

  margin-left: auto;
  margin-right: auto;
}
.title {
  position: relative;
  width: 100%;
  height: 58.95px;

  margin-bottom: 25px;
  padding-left: 5px;
}
.title > h1 {
  height: 58.95px;
}
.graphic {
  margin-top: 40px;
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.image {
  display: inline-block;
  width: 59.5%;
  height: 400px;
  border-radius: 15px;
  overflow: hidden;
  text-align: center;
}
img {
  width: 100%;
  height: 100%;
}
.map {
  display: inline-block;
  width: 39.5%;
  height: 400px;
  border-radius: 15px;
  overflow: hidden;
}
.information-intro {
  margin-top: 20px;
  display: inline-block;
  position: relative;
  width: 100%;
  line-height: 70px;
}
.information-intro > * {
  height: 35px;
  line-height: 30px;
  padding-left: 10px;
}
.likebtn {
  display: inline-block;
  position: absolute;
  top: 0px;
  right: 0px;
}
.information-body {
  margin-top: 20px;
  width: 100%;
}
.information-body .body-top {
  height: 350px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  margin-top: 10px;
}
.information-body .body-weather {
  display: inline-block;
  width: 39.5%;
  height: 100%;
  border-radius: 15px;
  border: 0.1px solid;
  padding-left: 30px;
}
.information-body .body-data {
  display: inline-block;
  width: 59.5%;
  height: 100%;
  border-radius: 15px;
}
.data-top {
  display: flex;
  justify-content: space-around;
}
.data-top .data-top-left {
  display: inline-block;
  width: 49.5%;
}
.data-top .data-top-right {
  display: inline-block;
  width: 49.5%;
}
.information-body .body-text {
  padding: 10px;
  width: 99%;
  border-radius: 15px;
}
.photo-list {
  position: relative;
  display: inline-flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  width: 100%;
  border-radius: 15px;
  margin-top: 20px;
  overflow: hidden;
  margin-bottom: 40px;
}
.photo {
  display: inline-block;
  border: 2px solid white;
  width: 20%;
  height: 180px;
}

.like {
  display: inline-block;
  position: absolute;
  color: red;
  margin-right: 5px;
  margin-bottom: 5px;
  right: 0;
  top: 0;
}
</style>
