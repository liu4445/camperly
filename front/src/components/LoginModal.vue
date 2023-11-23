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
