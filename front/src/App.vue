<script setup>
import MainHeader from "@/components/MainHeader.vue";
import LocaTypeSelect from "@/components/LocaTypeSelect.vue";
import MainList from "./components/MainList.vue";
</script>

<template>
  <MainHeader />
  <LocaTypeSelect />
  <MainList />
</template>

<style scoped>
body {
  width: 90%;
}
</style>
