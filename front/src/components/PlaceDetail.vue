<script setup>
import LikeButton from "./LikeButton.vue";
import { ref } from "vue";
import TempListFile from "./TempListFile.vue";

const data = ref([]);
data.value = TempListFile;
</script>

<template>
  <div class="main">
    <div class="title">
      <h1>{{ data.facltNm }}</h1>

      <div class="likebtn">
        <LikeButton />
      </div>
      <hr style="border-width: 2px" />
    </div>
    <div class="graphic">
      <span class="image"><img :src="data.firstImageUrl" /></span>
      <span class="map"></span>
    </div>
    <hr />
    <div class="information-intro">
      <div>tel {{ data.tel }}</div>
      <div>
        <a :href="data.homepage">홈페이지로 가기</a>
        <span style="margin-left: 10px">{{ data.resveCl }}</span>
      </div>

      <div class="like">♥ 11</div>
    </div>
    <hr />
    <div class="information-body">
      <div class="subfacltlist">
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Amet exercitationem sed optio cum
        iusto dicta doloremque itaque quisquam libero voluptatum est hic iure vel ex architecto
        beatae, error ad perspiciatis?Lorem ipsum dolor sit amet consectetur, adipisicing elit.
        Cumque exercitationem voluptatibus aut nesciunt ipsam, iste ad maxime ipsum! Beatae possimus
        explicabo, soluta dicta unde eos aliquam voluptatibus quas voluptas eaque?
      </div>
      <hr />
      <div class="body-top">
        <div class="body-data">
          <div class="data-top">
            <div class="data-top-left">
              <ul>
                <li>{{ data.induty }}</li>
                <li>{{ data.facltDivNm }}</li>
                <li>{{ data.mangeDivNm }}</li>
                <li>{{ data.lctCl }}</li>
                <li>ㅇㅇㅇㅇㅇㅇㅇㅇ</li>
              </ul>
            </div>
            <div class="data-top-right">
              <ul>
                <li>{{ data.operPdCl }}</li>
                <li>{{ data.operDeCl }}</li>
                <li>{{ data.posblFcltyCl }}</li>
                <li>{{ data.animalCmgCl }}</li>
                <li>ㅇㅇㅇㅇㅇㅇ</li>
              </ul>
            </div>
          </div>
          <div class="data-bottom">
            <ul>
              <li>카라반 몇면 뭐 몇면(vif)</li>
              <li>기타 부대 시설 : {{ data.sbrsEtc }}</li>
              <li>카라반 내부 시설(vif) : {{ data.caravInnerFclty }}</li>
              <li>화로대 : {{ data.brazierCl }}</li>
              <li>안전시설현황 :</li>
              <li>이것저것추가</li>
            </ul>
          </div>
        </div>
        <div class="body-weather"></div>
      </div>
      <hr />
      <div class="body-text">
        {{ data.intro }}
      </div>
    </div>
    <div class="photo-list">
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
      <div class="photo"></div>
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
  width: 49.5%;
  height: 400px;
  border: 1px solid black;
  border-radius: 15px;
  overflow: hidden;
}
img {
  width: 100%;
  height: 100%;
}
.map {
  display: inline-block;
  width: 49.5%;
  height: 400px;
  border: 1px solid black;
  border-radius: 15px;
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
}
.photo-list > * {
  display: inline-block;
  border: 3px solid red;
  width: 20%;
  height: 150px;
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
