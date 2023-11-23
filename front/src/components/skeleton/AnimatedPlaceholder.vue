<template>
  <div class="placeholderdiv">
    &nbsp;
  </div>
</template>

<script setup>
defineProps({
  height: {
    type: String
  },
  width: {
    type: String
  }
})
</script>

<style scoped>
* {
  cursor: default;
}

@keyframes bgAnimate {
  0% {
    background-position: 50% 0;
  }
  100% {
    background-position: -150% 0;
  }
}

.placeholderdiv {
  width: v-bind(width);
  height: v-bind(height);
  background-image: linear-gradient(
      to right,
      #d6d7d8 0%,
      #e2e3e4 10%,
      #d6d7d8 20%,
      #d6d7d8 100%
  );
  background-size: 200% 100%;
  box-shadow: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);
  animation: bgAnimate 3s linear infinite;
}
</style>