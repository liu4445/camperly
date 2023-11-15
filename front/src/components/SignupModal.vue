<script setup>
import axios from "axios";
import "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js";
import { ref, defineProps, defineEmits, onUpdated } from "vue";

const { VITE_VUE_API_URL } = import.meta.env;

const props = defineProps({
  data: Boolean,
});
const emit = defineEmits(["signupClose"]);

const SignUpUser = ref({
  loginId: "",
  pw: "",
  name: "",
  tel: "",
});
const clickClose = () => {
  console.log("hi");
  $("#signup").modal("hide");
  emit("signupClose");
};

onUpdated(() => {
  console.log(props.data);
  console.log("온업데이트");
  if (props.data == true) $("#signup").modal("show");
  else $("#signup").modal("hide");
});

const signupForm = () => {
  console.log("회원가입폼들어왓다.");
  axios({
    method: "post",
    url: VITE_VUE_API_URL + "user/signup",
    data: {
      loginId: SignUpUser.value.loginId,
      pw: SignUpUser.value.pw,
      name: SignUpUser.value.name,
      tel: SignUpUser.value.tel,
    },
  })
    .then((res) => {
      console.log(res);
      console.log("성공");
      if (res.data == "success") {
        alert("회원가입에 성공하였습니다.");
      }
      //메세지에 따라서 나오는 알림 다르게.
    })
    .catch((error) => {
      console.log(SignUpUser.value);
      console.log(error);
    })
    .finally(() => {
      $("#signupModal").modal("hide");
    });
};
</script>

<template>
  <div
    class="modal fade"
    id="signup"
    tabindex="-1"
    aria-labelledby="signupModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="clickClose"
          ></button>
        </div>
        <div class="modal-body">
          <form class="form-box py-2" @submit.prevent="signupForm">
            <h1 class="h3 mb-3 fw-normal">회원가입</h1>
            <div class="form-floating">
              <input
                type="text"
                class="form-control mb-3"
                id="floatingInput"
                name="loginId"
                placeholder="ID"
                v-model="SignUpUser.loginId"
              />
              <label for="floatingInput">ID</label>
            </div>
            <div class="form-floating">
              <input
                type="password"
                class="form-control mb-3"
                id="floatingPassword"
                name="pw"
                placeholder="Password"
                v-model="SignUpUser.pw"
              />
              <label for="floatingPassword mb-3">Password</label>
            </div>
            <div class="form-floating">
              <input
                type="text"
                class="form-control mb-3"
                id="floatingInput"
                name="loginName"
                placeholder="Name"
                v-model="SignUpUser.name"
              />
              <label for="floatingInput">Name</label>
            </div>
            <div class="form-floating">
              <input
                type="number"
                class="form-control mb-3"
                id="floatingTel"
                name="pw"
                placeholder="Tel"
                v-model="SignUpUser.tel"
              />
              <label class="w-75" for="floatingInput">Tel</label>
            </div>

            <button class="btn btn-primary w-75 py-2" type="submit">회원 가입</button>
          </form>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
            @click="clickClose"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal-body {
  text-align: center;
}

.form-check {
  margin-left: auto;
  margin-right: auto;
}
.form-control {
  margin-left: auto;
  margin-right: auto;
}
.form-floating {
  width: 75%;
  margin-left: auto;
  margin-right: auto;
}

.navbar {
  min-height: 10%;
}
span > a {
  margin-left: 10px;
}
</style>
