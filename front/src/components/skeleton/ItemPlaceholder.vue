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

<script>
</script>

<style>

</style>