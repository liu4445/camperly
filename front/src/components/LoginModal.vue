<script setup>
import axios from "axios";
import "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js";
import { ref, defineProps, defineEmits, onUpdated, onMounted } from "vue";
const { VITE_VUE_API_URL, VITE_GOOGLE_LOGIN_URL, VITE_KAKAO_LOGIN_URL } = import.meta.env;

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

const kakaoLogin = () => {
  location.href = VITE_KAKAO_LOGIN_URL;
};
const googleLogin = () => {
  location.href = VITE_GOOGLE_LOGIN_URL;
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
          <h4>로그인</h4>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @close="clickClose"
          ></button>
        </div>
        <div class="modal-body">
          <button type="button" class="login-button">
            <img
              id="kakao-button-img"
              src="@/assets/img/login/kakao_login_medium_narrow.png"
              alt="카카오 로그인"
              @click="kakaoLogin"
            />
          </button>
          <!--          <Google/>-->
          <button type="button" class="login-button">
            <img
              id="google-button-img"
              src="@/assets/img/login/web_light_sq_SI@4x.png"
              alt="구글 로그인"
              @click="googleLogin"
            />
          </button>

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

.modal-header > h4 {
  height: 20px;
}

.modal-body {
  display: flex;
  align-items: center;
  justify-content: center;
}

.login-button {
  border: 0px;
  background-color: white;
  padding: 0px;
  margin: 6px;
}

#kakao-button-img {
  width: 200px;
  height: 50px;
}

#google-button-img {
  width: 200px;
  height: 50px;
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
