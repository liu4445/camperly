<script setup>
import LikeButton from "./LikeButton.vue";
import { onBeforeMount, onMounted, ref } from "vue";
const { VITE_VUE_API_URL } = import.meta.env;
import "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js";

import axios from "axios";
import { onBeforeRouteUpdate, useRoute } from "vue-router";
import WeatherCharts from "./WeatherCharts.vue";
import MapVue from "./Map.vue";
import mediumZoom from "medium-zoom";
const place = ref([]);
const imageList = ref([]);
const route = useRoute();
const imagePath = "/src/assets/img/pictogram/";
const subFacilities = new Map([
  ["전기", `${imagePath}subfaclt/light.png`],
  ["무선인터넷", `${imagePath}subfaclt/wifi.png`],
  ["장작판매", `${imagePath}subfaclt/firewood.png`],
  ["온수", `${imagePath}subfaclt/water.png`],
  ["트렘폴린", `${imagePath}subfaclt/tram.png`],
  ["물놀이장", `${imagePath}subfaclt/pool.png`],
  ["놀이터", `${imagePath}subfaclt/play.png`],
  ["산책로", `${imagePath}subfaclt/way.png`],
  ["운동장", `${imagePath}subfaclt/ground.png`],
  ["운동시설", `${imagePath}subfaclt/weight.png`],
  ["마트_편의점", `${imagePath}subfaclt/store.png`],
]);

const mainFacilities = new Map([
  ["일반야영장", `${imagePath}mainfaclt/camping.png`],
  ["자동차야영장", `${imagePath}mainfaclt/car.png`],
  ["카라반", `${imagePath}mainfaclt/caravan.png`],
  ["글램핑", `${imagePath}mainfaclt/glamping.png`],
]);

const locationTypes = new Map([
  ["섬", `${imagePath}location/island.png`],
  ["산", `${imagePath}location/moutain.png`],
  ["해변", `${imagePath}location/beach.png`],
  ["강", `${imagePath}location/river.png`],
  ["호수", `${imagePath}location/lake.png`],
  ["계곡", `${imagePath}location/valley.png`],
  ["숲", `${imagePath}location/forest.png`],
  ["도심", `${imagePath}location/city.png`],
]);
const themes = new Map([
  ["일출명소", `${imagePath}themes/sunrise.png`],
  ["일몰명소", `${imagePath}themes/sunset.png`],
  ["수상레저", `${imagePath}themes/boat.png`],
  ["항공레저", `${imagePath}themes/air.png`],
  ["스키", `${imagePath}themes/ski.png`],
  ["낚시", `${imagePath}themes/fish.png`],
  ["액티비티", `${imagePath}themes/activity.png`],
  ["봄꽃여행", `${imagePath}themes/spring.png`],
  ["여름물놀이", `${imagePath}themes/swim.png`],
  ["가을단풍명소", `${imagePath}themes/autumn.png`],
  ["겨울눈꽃명소", `${imagePath}themes/winter.png`],
  ["걷기길", `${imagePath}themes/walk.png`],
]);

const isMore = ref(false);
const pressMore = () => {
  isMore.value == false ? (isMore.value = true) : (isMore.value = false);
};
onMounted(() => {
  getDetail();
});

onBeforeMount(() => {
  window.scrollTo(0, 0);
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

//이미지 확대
mediumZoom("[data-zoomable]");
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
          onerror="this.src='src/assets/img/fail.png'; this.style.width='70%'; this.style.height='100%'; "
      /></span>
      <span class="map">
        <MapVue :x="place.mapX" :y="place.mapY"></MapVue>
      </span>
    </div>
    <hr />
    <div class="information-intro">
      <div class="left">
        <div><span style="font-weight: bold">운영계절 </span> | {{ place.oeprSeason }}</div>
        <div><span style="font-weight: bold">운영시간 </span> | {{ place.operDate }}</div>

        <div><span style="font-weight: bold">전화번호 </span>| {{ place.tel }}</div>
        <div class="text">
          <span style="font-weight: bold">예약방법 </span>| {{ place.reserveType }}
        </div>
        <div class="text">
          <span style="font-weight: bold">주　　소 </span>| {{ place.address }}
        </div>
        <div v-if="place.canTrailer == '가능'">※ 반려동물 가능</div>
        <div v-else>※ 반려동물 불가능</div>
        <div v-if="place.canTrailer == 'Y'">※ 개인 트레일러 가능</div>
        <div v-else>※ 개인 트레일러 불가능</div>
        <div v-if="place.canCaravan == 'Y'">※ 개인 카라반 가능</div>
        <div v-else>※ 개인 카라반 불가능</div>
        <div class="text" id="test">
          <br />
          <a :href="place.homepage"
            ><img class="home-img" src="@/assets/img/pictogram/home.png" />
            <p style="text-decoration: underline">홈페이지로 가기</p></a
          >
        </div>

        <!-- <div class="like">♥11</div> -->
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
        <div v-for="item in place.subFacilities" :key="subFacilities.keys">
          <img :src="subFacilities.get(item)" />
          <p>{{ item }}</p>
        </div>
      </div>
      <hr />
      <div v-if="place.mainFacilities != '' || place.locationTypes != '' || place.themes != ''">
        <div class="body-type-list">
          <div v-if="place.mainFacilities != ''">
            <div class="body-type-name">
              <p>주요시설</p>
            </div>
            <div class="body-type">
              <div v-for="item in place.mainFacilities" :key="mainFacilities.keys">
                <img :src="mainFacilities.get(item)" />
                <p>{{ item }}</p>
              </div>
            </div>
          </div>
          <div v-if="place.locationTypes != ''">
            <div class="body-type-name">
              <p>주변풍경</p>
            </div>
            <div class="body-type" v-if="place.locationTypes != ''">
              <div v-for="item in place.locationTypes" :key="locationTypes.keys">
                <img :src="locationTypes.get(item)" />
                <p style="margin-right: 10px">{{ item }}</p>
              </div>
            </div>
          </div>
          <div v-if="place.themes != ''">
            <div class="body-type-name">
              <p>테마</p>
            </div>
            <div class="body-type" v-if="place.themes != ''">
              <div v-for="item in place.themes" :key="themes.keys">
                <img :src="themes.get(item)" />
                <p>{{ item }}</p>
              </div>
            </div>
          </div>
        </div>
        <div><hr /></div>
      </div>
      <button class="btn-moreInfo" @click="pressMore">상세 정보</button>
      <div class="body-top" v-if="isMore == true">
        <div class="body-data"></div>
        <div class="body-text">
          <div>
            <p v-if="place.generalSiteCnt != 0">일반야영장( {{ place.generalSiteCnt }} 면)</p>
            <p v-if="place.autoSiteCnt != 0">자동차야영장( {{ place.autoSiteCnt }} 면)</p>
            <p v-if="place.caravanSiteCnt != 0">카라반( {{ place.caravanSiteCnt }} 면)</p>
            <p v-if="place.userCaravanSiteCnt != 0">개인카라반({{ place.userCaravanSiteCnt }})</p>
            <p v-if="place.glampSiteCnt != 0">글램핑( {{ place.glampSiteCnt }} 면)</p>
          </div>
          <div v-if="place.etcFacilities != ''">기타 부대 시설 : {{ place.etcFacilities }}</div>
          <div v-if="place.etcCaravanFacilities != ''">
            <p>카라반 내부 시설 : {{ place.etcCaravanFacilities }}</p>
          </div>
          <div>화로대 : {{ place.brazier }}</div>
          <div v-if="place.etcGlampingFacilities != ''">
            <p>글램핑 내부 시설 : {{ place.etcGlampingFacilities }}</p>
          </div>
          <div v-if="place.equipLend != ''">
            <p>캠핑 장비 대여 : {{ place.equipLend }}</p>
          </div>
          <div v-if="place.experience != ''">
            <p>체험 가능 : {{ place.experience }}</p>
          </div>
          <br />
          {{ place.intro }}
        </div>
      </div>
    </div>

    <div class="photo-list">
      <div class="photo" v-for="image in imageList" :key="imageList.contentId">
        <img data-zoomable :src="image.imgUrl" onerror="this.style.display='none';" />
      </div>
    </div>
  </div>
</template>

<style scoped>
* {
  /* border: 0.1px solid; */
}
li {
  margin-bottom: 5px;
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
  margin-top: 50px;
  margin-bottom: 30px;
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
  margin-top: 30px;
  margin-bottom: 30px;
  display: flex;
  justify-content: space-around;
  position: relative;
  width: 100%;
  line-height: 70px;
  height: 350px;
}

.information-intro > .left > * {
  height: 35px;
  line-height: 30px;
  padding-left: 10px;
}
.information-intro > .left {
  width: 49.5%;
  position: relative;
  display: inline-block;
  border-radius: 15px;
  /* border: 1px solid lightgray; */
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
  margin-left: auto;
  margin-right: auto;
  text-align: center;
}
.information-body .subfacltlist {
  display: flex;
  justify-content: space-evenly;
  margin-left: auto;
  margin-right: auto;
  flex-wrap: wrap;
  margin-bottom: 20px;
}
.information-body .subfacltlist div {
  text-align: center;
}
.information-body .subfacltlist div > p {
  text-align: center;
  display: block;
  margin: 0px;
}
.information-body .body-type-list {
  display: flex;
  justify-content: space-around;
  padding-left: 90px;
  padding-right: 90px;
}
.information-body .body-type-name {
  display: inline-block;
  margin-right: 5px;
  top: 0;
  position: relative;
  vertical-align: top;
}
.information-body .body-type-name p {
  display: inline-block;
  margin-right: 10px;
  top: 0;
  position: relative;
  font-size: 20pt;
}
.information-body .body-type {
  display: inline-block;
  width: 220px;
  border-radius: 15px;
  border: 1px solid lightgray;
  margin-right: 20px;
  text-align: left;
  padding: 10px;
  padding-left: 20px;
}

.information-body img {
  width: 40px;
  height: 40px;
  margin: 10px;
}

.information-body .body-type p {
  display: inline-block;
  height: 40px;
  line-height: 40px;
  margin-left: 10px;
}
.information-body .body-type div {
  display: inline-block;
  margin-left: auto;
  margin-right: auto;
}

.information-body .body-right {
  display: inline-block;
  border-radius: 15px;
  border: 1px solid lightgray;
  width: 39.5%;
  height: 100%;
}

.body-weather {
  display: inline-block;
  width: 100%;
  height: 100%;
  border-radius: 15px;
  /* border: 1px solid black; */
  overflow: hidden;
}
.information-body .body-data {
  display: flex;
  width: 100%;
  height: 100%;
  border-radius: 15px;
  justify-content: space-around;
}
.data-top {
  display: flex;
  justify-content: space-around;
}
.body-data .data-left {
  display: inline-block;
  width: 59.5%;
  border-radius: 15px;
  border: 1px solid lightgray;
  text-align: left;
  font-size: 14pt;
}
.body-data .data-right {
  display: inline-block;
  width: 39.5%;
  border-radius: 15px;
  font-size: 14pt;
  border: 1px solid lightgray;
  text-align: left;
}
.information-body .body-text {
  padding: 20px;
  padding-bottom: 30px;
  padding-right: 20px;
  width: 80%;
  border-radius: 15px;
  text-align: left;
  border: 1px solid lightgray;
  margin-left: auto;
  margin-right: auto;
}
.btn-moreInfo {
  border-radius: 15px;
  width: 120px;
  height: 50px;
  margin-bottom: 15px;
  background-color: white;
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

.home-img {
  width: 17px;
  height: 17px;
  padding-bottom: 3px;
  margin-right: 5px;
}
</style>
