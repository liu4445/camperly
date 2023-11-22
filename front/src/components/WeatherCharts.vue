<script setup>
import Chart from "chart.js/auto";
import axios from "axios";
import "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js";
import { ref, defineProps, onMounted } from "vue";
const { VITE_VUE_API_URL } = import.meta.env;
const weather = ref([]);

onMounted(() => {
  getWeather();
});
const props = defineProps({
  contentId: String,
});
const ctx = "line-chart";
const getWeather = () => {
  const params = "contentId=" + props.contentId;
  console.log("날씨  로드.");
  axios({
    method: "get",
    url: VITE_VUE_API_URL + "weather?" + params,
  })
    .then((res) => {
      changeData(res.data);
      console.log("11aaa", weather.value);
    })

    .catch((error) => {
      console.log(error);
    })
    .finally(() => {
      dd();
    });
};

const changeData = (data) => {
  console.log("dddd", data.weather);
  weather.value = data.weather;
  console.log("1111 ", weather.value[1].minTemperature);
};

const dd = () => {
  new Chart(ctx, {
    type: "line",
    data: {
      labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
      datasets: [
        {
          data: [
            weather.value[1].minTemperature,
            weather.value[2].minTemperature,
            weather.value[3].minTemperature,
            weather.value[4].minTemperature,
            weather.value[5].minTemperature,
            weather.value[6].minTemperature,
            weather.value[7].minTemperature,
            weather.value[8].minTemperature,
            weather.value[9].minTemperature,
            weather.value[10].minTemperature,
            weather.value[11].minTemperature,
            weather.value[12].minTemperature,
          ],
          label: "최저기온",
          borderColor: "#3e95cd",
          fill: false,
        },
        {
          data: [
            weather.value[1].maxTemperature,
            weather.value[2].maxTemperature,
            weather.value[3].maxTemperature,
            weather.value[4].maxTemperature,
            weather.value[5].maxTemperature,
            weather.value[6].maxTemperature,
            weather.value[7].maxTemperature,
            weather.value[8].maxTemperature,
            weather.value[9].maxTemperature,
            weather.value[10].maxTemperature,
            weather.value[11].maxTemperature,
            weather.value[12].maxTemperature,
          ],
          label: "최고기온",
          borderColor: "#8e5ea2",
          fill: false,
        },
        {
          data: [
            weather.value[1].avgTemperature,
            weather.value[2].avgTemperature,
            weather.value[3].avgTemperature,
            weather.value[4].avgTemperature,
            weather.value[5].avgTemperature,
            weather.value[6].avgTemperature,
            weather.value[7].avgTemperature,
            weather.value[8].avgTemperature,
            weather.value[9].avgTemperature,
            weather.value[10].avgTemperature,
            weather.value[11].avgTemperature,
            weather.value[12].avgTemperature,
          ],
          label: "평균기온",
          borderColor: "#3cba9f",
          fill: false,
        },
      ],
    },
    options: {
      title: {
        display: true,
        text: "기온",
      },
      scales: {
        y: {
          beginAtZero: false, // 음수 값 표시를 위해 false로 설정
          ticks: {
            max: 40,
            min: -40,
            stepSize: 0.5,
          },
        },
      },
    },
  });
};
</script>

<template><canvas id="line-chart" width="300" height="250"></canvas></template>

<style scoped></style>
