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
      <div class="left">
        <div>전화번호 | {{ place.tel }}</div>
        <div class="text">
          <a :href="place.homepage">홈페이지로 가기</a>
        </div>
        <div class="text">예약방법 | {{ place.reserveType }}</div>
        <div class="text">주　　소 | {{ place.address }}</div>
        <div class="like">♥11</div>
      </div>
      <div class="right">
        <div class="body-weather">
          <WeatherCharts :content-id="contentId" />
        </div>
      </div>
    </div>
    <hr />
    <div class="information-body">
      <div class="subfacltlist">
        {{ place.subFacilities }}
      </div>
      <hr />
      <div class="body-top">
        <div class="body-data">
          <div class="data-top">
            <div class="data-top-left">
              <ul>
                <li>운영계절 : {{ place.oeprSeason }}</li>
                <li>운영시간 : {{ place.operDate }}</li>
                <li>반려동물 : {{ place.animal }}</li>
                <li v-if="place.canTrailer == 'Y'">개인 트레일러 가능</li>
                <li v-else>개인 트레일러 불가능</li>
              </ul>
            </div>
            <!-- <div class="data-top-right"></div> -->
          </div>
          <div class="data-bottom">
            <ul>
              <li>
                <p v-if="place.generalSiteCnt != 0">일반야영장({{ place.generalSiteCnt }})</p>
                <p v-if="place.autoSiteCnt != 0">자동차야영장({{ place.autoSiteCnt }})</p>
                <p v-if="place.caravanSiteCnt != 0">카라반({{ place.caravanSiteCnt }})</p>
                <p v-if="place.userCaravanSiteCnt != 0">
                  개인카라반({{ place.userCaravanSiteCnt }})
                </p>
                <p v-if="place.glampSiteCnt != 0">글램핑({{ place.glampSiteCnt }})</p>
              </li>
              <li>기타 부대 시설 : {{ place.etcFacilities }}</li>
              <li>
                <p v-if="place.etcCaravanFacilities != ''">
                  카라반 내부 시설 : {{ place.etcCaravanFacilities }}
                </p>
              </li>
              <li>화로대 : {{ place.brazier }}</li>
              <li>
                <p v-if="place.etcGlampingFacilities != ''">
                  글램핑 내부 시설 : {{ place.etcGlampingFacilities }}
                </p>
              </li>
              <li>
                <p v-if="place.equipLend != ''">캠핑 장비 대여 : {{ place.equipLend }}</p>
              </li>
              <li>
                <p v-if="place.experience != ''">체험 가능 : {{ place.experience }}</p>
              </li>
            </ul>
          </div>
        </div>
        <div class="body-right">
          {{ place.locationTypes }}
          {{ place.operationTypes }}

          {{ place.mainFacilities }}
          {{ place.themes }}
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
li {
  margin-bottom: 2px;
}
ul {
  list-style: none;
  padding: 10px;
  margin: 0;
}
p {
  display: inline;
  margin-right: 10px;
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
  border: 0.1px solid lightgray;
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
  display: flex;
  justify-content: space-around;
  position: relative;
  width: 100%;
  line-height: 70px;
  height: 160px;
}
.information-intro > .left > * {
  height: 35px;
  line-height: 30px;
  padding-left: 10px;
}
.information-intro > .left {
  padding-top: 8px;
  width: 49.5%;
  position: relative;
  display: inline-block;
  border-radius: 15px;
  border: 1px solid lightgray;
}
.information-intro > .right {
  width: 49.5%;
  height: 100%;
  display: inline-block;
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
.information-body .body-right {
  display: inline-block;
  border-radius: 15px;
  border: 1px solid lightgray;
  width: 39.5%;
  height: 100%;
}
.information-body .body-top {
  height: 350px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  margin-top: 10px;
}
.body-weather {
  display: inline-block;
  width: 100%;
  height: 100%;
  border-radius: 15px;
  border: 0.1px solid;
  overflow: hidden;
}
.information-body .body-data {
  display: inline-block;
  width: 59.5%;
  height: 100%;
  border-radius: 15px;
  border: 1px solid lightgray;
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
  width: 100%;
  border-radius: 15px;
  border: 1px solid lightgray;
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
  border: 1.5px solid white;
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
