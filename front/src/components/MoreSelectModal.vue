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
          Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ullam inventore aliquid ipsum
          deleniti nesciunt rerum quas tempore eveniet eum repellendus? Ratione cupiditate ullam
          quae? Nobis ullam itaque corporis quis tenetur.
          <p>Modal body text goes here.</p>
          <hr />
          Lorem ipsum dolor, sit amet consectetur adipisicing elit. Animi in accusantium, quasi
          dolorum nostrum tempore autem. Totam sapiente omnis inventore velit, recusandae, corporis,
          facere consectetur soluta fugit quos maxime eum.
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
