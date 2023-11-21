import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
const { VITE_VUE_API_URL } = import.meta.env;
export const usePlaceStore = defineStore("place", () => {
  //받은 캠핑장소들
  const placeList = ref([]);

  const json = ref({
    locationTypes: [],
    operationType: "",
    mainFacilities: [],
    themes: [],
    subFacilities: [],
  });
  const load = computed(() => {
    getList();
  });
  //axios 로 요청보냄
  async function getList() {
    const convertToQueryParameter = (json) => {
      let res = "";
      Object.keys(json).forEach((key) => {
        if (Array.isArray(json[key])) {
          json[key].forEach((value) => {
            res += `${key}=${value}&`;
          });
        } else {
          res += `${key}=${json[key]}&`;
        }
      });
      return res.substring(0, res.length - 1);
    };
    console.log("피니아에서 리스트 로드.");
    const params = convertToQueryParameter(json.value);
    console.log(convertToQueryParameter(json.value));
    await axios({
      method: "get",
      url: VITE_VUE_API_URL + "camping/place?" + params,
    })
      .then((res) => {
        console.log(res);
        placeList.value = res.data;
        console.log("성공");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return { placeList, json, getList, load };
});
