import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
const { VITE_VUE_API_URL } = import.meta.env;
export const usePlaceStore = defineStore("place", () => {
  //받은 캠핑장소들
  const placeList = ref([]);
  const isLocationSelect = ref(false);
  const isMoreSelect = ref(false);
  const isSearch = ref(false);
  let pageNum = 1;

  const json = ref({
    locationFlag: false,
    nameFlag: false,
    keyword: "",
    locationTypes: [],
    operationType: "",
    mainFacilities: [],
    themes: [],
    subFacilities: [],
    page: pageNum,
  });
  const load = computed(() => {});
  //axios 로 요청보냄
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
  async function getList() {
    console.log("피니아에서 리스트 로드.");
    json.page = 1;
    const params = convertToQueryParameter(json.value);
    console.log(convertToQueryParameter(json.value));
    await axios({
      method: "get",
      url: VITE_VUE_API_URL + "camping/place?" + params,
    })
      .then((res) => {
        placeList.value = res.data.campingPlaceDtos;
        console.log(placeList.value);
        console.log("성공");
      })
      .catch((error) => {
        console.log(error);
      });
  }
  async function putList() {
    console.log("추가 리스트 로드.");
    pageNum = pageNum + 1;
    json.value.page = pageNum;
    console.log(json.value.page);
    const params = convertToQueryParameter(json.value);
    console.log(convertToQueryParameter(json.value));
    await axios({
      method: "get",
      url: VITE_VUE_API_URL + "camping/place?" + params,
    })
      .then((res) => {
        placeList.value.push(...res.data.campingPlaceDtos);
        console.log("성공");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return { placeList, json, getList, load, isLocationSelect, isMoreSelect, putList, isSearch };
});
