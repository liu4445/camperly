<script setup>
import { ref, defineProps, onUpdated, onMounted } from "vue";
import { usePlaceStore } from "@/stores/places.js";
import { storeToRefs } from "pinia";
const store = usePlaceStore();
const { json } = storeToRefs(store);

const emit = defineEmits(["moreSelect"]);
const props = defineProps({
  data: Boolean,
  locationTypes: Object,
});

const operationType = ref("");
const mainFacilities = ref([]);
const themes = ref([]);
const subFacilities = ref([]);
const locationTypes = ref([]);
locationTypes.value = props.locationTypes;
onMounted(() => {
  $("#moreSelect").on("hidden.bs.modal", () => {
    $("#moreSelect").modal("hide");
    emit("moreSelect");
  });
});
onUpdated(() => {
  console.log(props.data);
  console.log("select 온업데이트");
  if (props.data == true) $("#moreSelect").modal("show");
  else $("#moreSelect").modal("hide");
});
const sendData = () => {
  json.value.operationType = operationType.value;
  json.value.mainFacilities = mainFacilities.value;
  json.value.themes = themes.value;
  json.value.subFacilities = subFacilities.value;
  json.value.locationTypes = locationTypes.value;
};
</script>

<template>
  <div
    class="modal fade"
    id="moreSelect"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h3 class="modal-title">상세검색</h3>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <form class="form-box py-2" @submit.prevent="moreSelectForm">
          <div class="modal-body">
            <h5>운영형태</h5>
            <div class="mb-3">
              <div class="form_toggle">
                <div class="form_radio_btn radio_male">
                  <input
                    id="radio-1"
                    type="radio"
                    v-model="operationType"
                    name="operationType"
                    value="지자체"
                  />
                  <label for="radio-1">지자체</label>
                </div>

                <div class="form_radio_btn">
                  <input
                    id="radio-2"
                    type="radio"
                    v-model="operationType"
                    name="operationType"
                    value="국립공원"
                  />
                  <label for="radio-2">국립공원</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="radio-3"
                    type="radio"
                    v-model="operationType"
                    name="operationType"
                    value="자연휴양림"
                  />
                  <label for="radio-3">자연휴양림</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="radio-4"
                    type="radio"
                    v-model="operationType"
                    name="operationType"
                    value="국민여가"
                  />
                  <label for="radio-4">국민여가</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="radio-5"
                    type="radio"
                    v-model="operationType"
                    name="operationType"
                    value="민간"
                  />
                  <label for="radio-5">민간</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="radio-6"
                    type="radio"
                    name="operationType"
                    v-model="operationType"
                    value=""
                    checked
                  />
                  <label for="radio-6">상관없음</label>
                </div>
              </div>
            </div>

            <hr />
            <h5>주요시설</h5>
            <div class="mb-3">
              <div class="form_toggle">
                <div class="form_radio_btn">
                  <input
                    id="checkbox-mf-1"
                    type="checkbox"
                    v-model="mainFacilities"
                    name="operationType"
                    value="일반야영장"
                  />
                  <label for="checkbox-mf-1">일반야영장</label>
                </div>

                <div class="form_radio_btn">
                  <input
                    id="checkbox-mf-2"
                    type="checkbox"
                    v-model="mainFacilities"
                    name="operationType"
                    value="자동차야영장"
                  />
                  <label for="checkbox-mf-2">자동차야영장</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-mf-3"
                    type="checkbox"
                    v-model="mainFacilities"
                    name="operationType"
                    value="카라반"
                  />
                  <label for="checkbox-mf-3">카라반</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-mf-4"
                    type="checkbox"
                    v-model="mainFacilities"
                    name="operationType"
                    value="글램핑"
                  />
                  <label for="checkbox-mf-4">글램핑</label>
                </div>
              </div>
            </div>
            <hr />
            <h5>테마별</h5>
            <div class="mb-3">
              <div class="form_toggle">
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-1"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="일출명소"
                  />
                  <label for="checkbox-themes-1">일출명소</label>
                </div>

                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-2"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="일몰명소"
                  />
                  <label for="checkbox-themes-2">일몰명소</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-3"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="수상레저"
                  />
                  <label for="checkbox-themes-3">수상레저</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-4"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="항공레저"
                  />
                  <label for="checkbox-themes-4">항공레저</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-5"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="스키"
                  />
                  <label for="checkbox-themes-5">스키</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-6"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="낚시"
                  />
                  <label for="checkbox-themes-6">낚시</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-7"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="액티비티"
                  />
                  <label for="checkbox-themes-7">액티비티</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-8"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="봄꽃여행"
                  />
                  <label for="checkbox-themes-8">봄꽃여행</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-9"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="여름물놀이"
                  />
                  <label for="checkbox-themes-9">여름물놀이</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-10"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="가을단풍명소"
                  />
                  <label for="checkbox-themes-10">가을단풍명소</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-11"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="겨울눈꽃명소"
                  />
                  <label for="checkbox-themes-11">겨울눈꽃명소</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-themes-12"
                    type="checkbox"
                    v-model="themes"
                    name="themes"
                    value="걷기길"
                  />
                  <label for="checkbox-themes-12">걷기길</label>
                </div>
              </div>
            </div>
            <hr />
            <h5>부대시설</h5>
            <div class="mb-3">
              <div class="form_toggle">
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-1"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="전기"
                  />
                  <label for="checkbox-sub-1">전기</label>
                </div>

                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-2"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="무선인터넷"
                  />
                  <label for="checkbox-sub-2">무선인터넷</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-3"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="장작판매"
                  />
                  <label for="checkbox-sub-3">장작판매</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-4"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="온수"
                  />
                  <label for="checkbox-sub-4">온수</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-5"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="트렘폴린"
                  />
                  <label for="checkbox-sub-5">트렘폴린</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-6"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="물놀이장"
                  />
                  <label for="checkbox-sub-6">물놀이장</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-7"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="놀이터"
                  />
                  <label for="checkbox-sub-7">놀이터</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-8"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="산책로"
                  />
                  <label for="checkbox-sub-8">산책로</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-9"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="운동장"
                  />
                  <label for="checkbox-sub-9">운동장</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-10"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="운동시설"
                  />
                  <label for="checkbox-sub-10">운동시설</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-11"
                    type="checkbox"
                    v-model="subFacilities"
                    name="subFacilities"
                    value="마트_편의점"
                  />
                  <label for="checkbox-sub-11">마트/편의점</label>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <input
              type="submit"
              class="btn"
              @click="sendData"
              data-bs-dismiss="modal"
              value="확인"
            />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.form_radio_btn {
  width: 70px;
  height: 25px;
  margin-top: 5px;
  margin-bottom: 5px;
  border-radius: 10px;
  display: inline-block;
  text-align: center;
  font-size: 7pt;
  margin-right: 10px;
  cursor: pointer;
}

.form_radio_btn input[type="radio"] {
  display: none;
}
.form_radio_btn input[type="checkbox"] {
  display: none;
}
.form_radio_btn label {
  line-height: 30px;
  display: block;
  border-radius: 10px;
  border: 1px solid black;
  text-align: center;
  height: 30px;
  justify-content: flex-start;
  cursor: pointer;
}

/* Checked */
.form_radio_btn input[type="radio"]:checked + label {
  background: darkslateblue;
  color: #fff;
}
.form_radio_btn input[type="checkbox"]:checked + label {
  background: darkslateblue;
  color: #fff;
}

/* Hover */
.form_radio_btn label:hover {
  color: #666;
}

/* Disabled */
.form_radio_btn input[type="button"] + label {
  background: white;
  color: #666;
}

.form_btn {
  width: 60px;
  height: 25px;
  margin-top: 5px;
  margin-bottom: 5px;
  border-radius: 10px;
  display: inline-block;
  text-align: center;
  font-size: smaller;
  margin-right: 10px;
  margin-left: 50px;
}

.form_btn button {
  display: block;
  border-radius: 10px;
  border: 1px solid black;
  text-align: center;
  height: 30px;
  width: 100%;
  font-weight: bold;
  font-size: small;
}
</style>
