<script setup>
import axios from "axios";
import "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js";
import { ref, defineProps, defineEmits, onUpdated, onMounted } from "vue";
const { VITE_VUE_API_URL } = import.meta.env;

// const onClickOutSide = () => {
//   console.log("밖클릭");
//   $("#login").modal("hide");
//   emit("loginClose");
// };
const props = defineProps({
  data: Boolean,
});
const emit = defineEmits(["loginClose"]);

onMounted(() => {
  $("#login").on("hidden.bs.modal", () => {
    console.log("click outer");
    $("#login").modal("hide");
    emit("loginClose");
  });
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
    data-bs-dismiss="model"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @close="clickClose"
          ></button>
        </div>
        <div class="modal-body">
          <!-- <form class="form-box py-2" @submit.prevent="loginForm">
            <h1 class="h3 mb-3 fw-normal">로그인</h1>
            <div class="form-floating">
              <input
                type="text"
                class="form-control mb-3"
                id="floatingLoginId"
                name="loginId"
                v-model="LoginUser.loginId"
              />
              <label for="floatingLoginId">ID</label>
            </div>
            <div class="form-floating">
              <input
                type="password"
                class="form-control mb-3"
                id="floatingLoginPassword"
                name="pw"
                v-model="LoginUser.pw"
              />
              <label for="floatingLogin Password">Password</label>
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
          </form> -->
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal-outside {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
}
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
