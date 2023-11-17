<script setup>
import { ref, defineProps, onUpdated, onMounted } from "vue";
const emit = defineEmits(["moreSelect"]);

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
});
</script>

<template>
  <div
    class="modal fade"
    id="moreSelect"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">상세검색</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          AND 버튼 이나 OR 버튼 넣어야됨. 각 항목들은 AND 로 엮어야하고 항목들마다 요소들은
          체크박스로 만들어서 OR이나 AND 로 조정할 수 있게<br />
          <hr />
          <h5>운영형태</h5>

          <br />
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1" />
            <label class="form-check-label" for="inlineCheckbox1">지자체</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2" />
            <label class="form-check-label" for="inlineCheckbox2">국립공원</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1" />
            <label class="form-check-label" for="inlineCheckbox1">자연휴양림</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1" />
            <label class="form-check-label" for="inlineCheckbox1">국민여가</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2" />
            <label class="form-check-label" for="inlineCheckbox2">민간</label>
          </div>

          <hr />
          주요시설 - 일반야영장, 자동차야영장, 카라반, 글램핑
          <hr />
          테마별 - 일출명소, 일몰명소, 수상레저, 항공레저, 스키, 낚시, 액티비티, 봄꽃여행,
          여름물놀이, 가을단풍명소, 겨울눈꽃명소, 걷기길
          <hr />
          부대시설 - 전기, 무선인터넷, 장작판매, 온수, 트렘폴린, 물놀이장, 놀이타, 산책로, 운동장,
          운동시설, 마트_편의점
          <hr />
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.and-or {
  display: inline;
}
input[type="range"] {
  width: 30px;
  background-color: black;
  accent-color: black;
}
</style>
