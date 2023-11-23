<template>
  <li>
    <div class="place-card">
      <AnimatedPlaceholder class="place-img"/>
      <div class="place-body">
        <AnimatedPlaceholder class="place-title" width="250px" height="24px"/>
        <AnimatedPlaceholder class="place-text" width="310px" height="50px"/>
        <AnimatedPlaceholder class="place-address" width="250px" height="30px"/>
      </div>
    </div>
  </li>
</template>

<script>
import {defineComponent} from "vue";
import AnimatedPlaceholder from "@/components/skeleton/AnimatedPlaceholder.vue";

export default defineComponent({
  components: {AnimatedPlaceholder}
})
</script>

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
  width: 310px;
  height: 400px;
  margin-bottom: 30px;
  margin-right: 8.2px;
  margin-left: 8.2px;
}

.place-img {
  width: 310px;
  height: 285px;
  border-radius: 10px;
}

.place-title {
  margin: 10px 0px 8px 0px
}

.place-text {
  margin: 0px 0px 5px 0px
}

.place-address {
  margin: 0px 0px 20px 0px
}

</style>