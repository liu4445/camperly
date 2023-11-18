<script setup>
import axios from "axios";
import { ref, defineProps, onUpdated, onMounted } from "vue";
const { VITE_VUE_API_URL } = import.meta.env;
const emit = defineEmits(["moreSelect"]);
const operType = ref("");
const mainfacility = ref([]);
const thema = ref([]);
const subfacility = ref([]);
const newLocationType = ref([]);
let json = "";
const toJson = () => {
  newLocationType.value = props.locationType;
  json = JSON.stringify({
    locationType: newLocationType.value,
    operType: operType.value,
    mainfacility: mainfacility.value,
    thema: thema.value,
    subfacility: subfacility.value,
  });
};
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
const props = defineProps({
  data: Boolean,
  locationType: String,
});

const moreSelectForm = () => {
  console.log("상세폼들어왓다.");
  toJson();
  console.log(json);
  axios({
    method: "post",
    url: "http://localhost:8080/" + "trip/place/list",
    data: json,
    headers: { "Content-Type": `application/json` },
  })
    .then((res) => {
      console.log(res);
      console.log("성공");
      if (res.data == "") {
        alert("아이디와 비밀번호를 확인해 주세요.");
        console.log(JSON.stringify(operType.value));
        console.log(JSON.stringify(mainfacility.value));
      } else {
      }
    })
    .catch((error) => {
      console.log(data);
      console.log(error);
    });
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
                    v-model="operType"
                    name="operType"
                    value="지자체"
                  />
                  <label for="radio-1">지자체</label>
                </div>

                <div class="form_radio_btn">
                  <input
                    id="radio-2"
                    type="radio"
                    v-model="operType"
                    name="operType"
                    value="국립공원"
                  />
                  <label for="radio-2">국립공원</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="radio-3"
                    type="radio"
                    v-model="operType"
                    name="operType"
                    value="자연휴양림"
                  />
                  <label for="radio-3">자연휴양림</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="radio-4"
                    type="radio"
                    v-model="operType"
                    name="operType"
                    value="국민여가"
                  />
                  <label for="radio-4">국민여가</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="radio-5"
                    type="radio"
                    v-model="operType"
                    name="operType"
                    value="민간"
                  />
                  <label for="radio-5">민간</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="radio-6"
                    type="radio"
                    name="operType"
                    v-model="operType"
                    value="상관없음"
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
                    v-model="mainfacility"
                    name="operType"
                    value="일반야영장"
                  />
                  <label for="checkbox-mf-1">일반야영장</label>
                </div>

                <div class="form_radio_btn">
                  <input
                    id="checkbox-mf-2"
                    type="checkbox"
                    v-model="mainfacility"
                    name="operType"
                    value="자동차야영장"
                  />
                  <label for="checkbox-mf-2">자동차야영장</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-mf-3"
                    type="checkbox"
                    v-model="mainfacility"
                    name="operType"
                    value="카라반"
                  />
                  <label for="checkbox-mf-3">카라반</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-mf-4"
                    type="checkbox"
                    v-model="mainfacility"
                    name="operType"
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
                    id="checkbox-thema-1"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="일출명소"
                  />
                  <label for="checkbox-thema-1">일출명소</label>
                </div>

                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-2"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="일몰명소"
                  />
                  <label for="checkbox-thema-2">일몰명소</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-3"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="수상레저"
                  />
                  <label for="checkbox-thema-3">수상레저</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-4"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="항공레저"
                  />
                  <label for="checkbox-thema-4">항공레저</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-5"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="스키"
                  />
                  <label for="checkbox-thema-5">스키</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-6"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="낚시"
                  />
                  <label for="checkbox-thema-6">낚시</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-7"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="액티비티"
                  />
                  <label for="checkbox-thema-7">액티비티</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-8"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="봄꽃여행"
                  />
                  <label for="checkbox-thema-8">봄꽃여행</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-9"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="여름물놀이"
                  />
                  <label for="checkbox-thema-9">여름물놀이</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-10"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="가을단풍명소"
                  />
                  <label for="checkbox-thema-10">가을단풍명소</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-11"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="겨울눈꽃명소"
                  />
                  <label for="checkbox-thema-11">겨울눈꽃명소</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-thema-12"
                    type="checkbox"
                    v-model="thema"
                    name="thema"
                    value="걷기길"
                  />
                  <label for="checkbox-thema-12">걷기길</label>
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
                    v-model="subfacility"
                    name="subfacility"
                    value="전기"
                  />
                  <label for="checkbox-sub-1">전기</label>
                </div>

                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-2"
                    type="checkbox"
                    v-model="subfacility"
                    name="subfacility"
                    value="무선인터넷"
                  />
                  <label for="checkbox-sub-2">무선인터넷</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-3"
                    type="checkbox"
                    v-model="subfacility"
                    name="subfacility"
                    value="장작판매"
                  />
                  <label for="checkbox-sub-3">장작판매</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-4"
                    type="checkbox"
                    v-model="subfacility"
                    name="subfacility"
                    value="온수"
                  />
                  <label for="checkbox-sub-4">온수</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-5"
                    type="checkbox"
                    v-model="subfacility"
                    name="subfacility"
                    value="트렘폴린"
                  />
                  <label for="checkbox-sub-5">트렘폴린</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-6"
                    type="checkbox"
                    v-model="subfacility"
                    name="subfacility"
                    value="물놀이장"
                  />
                  <label for="checkbox-sub-6">물놀이장</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-7"
                    type="checkbox"
                    v-model="subfacility"
                    name="subfacility"
                    value="놀이터"
                  />
                  <label for="checkbox-sub-7">놀이터</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-8"
                    type="checkbox"
                    v-model="subfacility"
                    name="subfacility"
                    value="산책로"
                  />
                  <label for="checkbox-sub-8">산책로</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-9"
                    type="checkbox"
                    v-model="subfacility"
                    name="subfacility"
                    value="운동장"
                  />
                  <label for="checkbox-sub-9">운동장</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-10"
                    type="checkbox"
                    v-model="subfacility"
                    name="subfacility"
                    value="운동시설"
                  />
                  <label for="checkbox-sub-10">운동시설</label>
                </div>
                <div class="form_radio_btn">
                  <input
                    id="checkbox-sub-11"
                    type="checkbox"
                    v-model="subfacility"
                    name="subfacility"
                    value="마트_편의점"
                  />
                  <label for="checkbox-sub-11">마트/편의점</label>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <input type="submit" class="btn btn-primary" data-bs-dismiss="modal" value="확인" />
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
