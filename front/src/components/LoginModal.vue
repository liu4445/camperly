<script setup>
import axios from "axios";
import "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js";
import { ref, defineProps, onUpdated } from "vue";
const { VITE_VUE_API_URL } = import.meta.env;

const props = defineProps({
  data: Boolean,
});

const LoginUser = ref({
  loginId: "",
  pw: "",
});

onUpdated(() => {
  console.log(props.data);
  console.log("온업데이트");
  if (props.data == true) $("#login").modal("show");
  else $("#login").modal("hide");
});
const loginForm = () => {
  console.log("로그인폼들어왓다.");
  axios({
    method: "post",
    url: VITE_VUE_API_URL + "user/login",
    data: {
      loginId: LoginUser.value.loginId,
      pw: LoginUser.value.pw,
    },
  })
    .then((res) => {
      console.log(res);
      console.log("성공");
      if (res.data == "") {
        alert("아이디와 비밀번호를 확인해 주세요.");
      } else {
      }
    })
    .catch((error) => {
      console.log(LoginUser.value);
      console.log(error);
    });
};
</script>

<template>
  <div
    class="modal fade"
    id="login"
    tabindex="-1"
    aria-labelledby="loginModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form class="form-box py-2" @submit.prevent="loginForm">
            <h1 class="h3 mb-3 fw-normal">로그인</h1>
            <div class="form-floating">
              <input
                type="text"
                class="form-control mb-3"
                id="floatingInput"
                name="loginId"
                placeholder="ID"
                v-model="LoginUser.loginId"
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
                v-model="LoginUser.pw"
              />
              <label for="floatingPassword">Password</label>
            </div>

            <div class="form-check text-start my-3 w-75">
              <input
                class="form-check-input mb-3"
                type="checkbox"
                value="remember-me"
                id="flexCheckDefault"
              />
              <label class="form-check-label" for="flexCheckDefault"> 아이디 기억하기 </label>
            </div>
            <button class="btn btn-primary w-75 py-2" type="submit">Sign in</button>
          </form>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-primary"
            data-bs-toggle="modal"
            data-bs-target="#signupModal"
          >
            회원가입
          </button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
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
