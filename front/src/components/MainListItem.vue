<script setup>
import { ref, defineProps } from "vue";
import { RouterLink } from "vue-router";
import LikeButton from "./LikeButton.vue";

const props = defineProps({ data: Object });
const contentId = ref(0);
contentId.value = props.data.contentId;
</script>

<template>
  <li>
    <div class="place-card">
      <router-link class="link" :to="`/${data.place.contentId}`">
        <div class="place-img" style="width: 310px">
          <img
            :src="data.place.firstImageUrl"
            onerror="this.src='src/assets/img/no-photo.jpg';"
            class="card-img-top"
            style="height: 285px"
          />
        </div>
        <div class="place-body">
          <h5 class="place-title">
            {{ data.place.campsiteName }}
          </h5>
          <div class="like">♥ {{ data.place.like }}</div>
          <div>
            <p class="place-text">{{ data.place.lineIntro }}</p>
            <p class="place-address">{{ data.place.doName }} {{ data.place.sigunguName }}</p>
          </div>
        </div>
      </router-link>
      <div class="likebtn"><LikeButton :contentId="data.place.contentId" /></div>
    </div>
  </li>
</template>

<style scoped>
li {
  display: inline-block;
  list-style: none;

  margin-right: 8px;
  margin-left: 8px;
  margin-bottom: 30px;
}

.place-card {
  position: relative;
  widows: 310px;
  height: 400px;
  margin-bottom: 30px;
  margin-right: 8.2px;
  margin-left: 8.2px;
}
.link {
  text-decoration: none;
  color: black;
}
.place-body {
  position: relative;
  width: 310px;
  height: 150px;
}
.place-img img {
  border-radius: 10px;
}
.place-title {
  margin-top: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  text-align: left;
  width: 250px;
}
.place-text {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  height: 50px;
  text-align: left;
  margin-bottom: 0px;
}
.place-address {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  height: 50px;
  text-align: left;
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
.likebtn {
  display: inline-block;
  position: absolute;
  top: 0;
  right: 0px;
}
</style>
