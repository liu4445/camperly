<template></template>

<script setup>
const { VITE_VUE_INDEX_URL } = import.meta.env;

const code = new URLSearchParams(location.search).get("code");
if (code != null) {
  fetch("http://localhost:8080/member/KAKAO/login?code=" + code)
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      localStorage.setItem("token", data["accessToken"]);
      location.href = VITE_VUE_INDEX_URL;
    });
}
</script>

<style></style>
