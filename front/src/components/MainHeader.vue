<script setup>
import { ref } from "vue";
import LoginModal from "./LoginModal.vue";
import SignupModal from "./SignupModal.vue";
const loginOpen = ref(false);

const changeloginOpen = () => {
  if (loginOpen.value == true) loginOpen.value = false;
  else {
    loginOpen.value = true;
  }
};

const signupOpen = ref(false);

const changesignupOpen = () => {
  if (signupOpen.value == true) signupOpen.value = false;
  else {
    signupOpen.value = true;
  }
};
</script>

<template>
  <nav class="navbar static-top">
    <div class="container">
      <a class="navbar-brand fs-2" href="/">CAMPING</a>

      <!-- 여기부터 로그인/회원가입 드롭다운  -->
      <div class="btn-group">
        <button
          type="button"
          class="btn btn-secondary rounded-5"
          data-bs-toggle="dropdown"
          data-bs-display="static"
          aria-expanded="false"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 32 32"
            aria-hidden="true"
            role="presentation"
            focusable="false"
            style="
              display: inline;
              fill: none;
              height: 16px;
              width: 16px;
              stroke: black;
              stroke-width: 3;
              overflow: visible;
            "
          >
            <path d="M2 16h28M2 24h28M2 8h28"></path>
          </svg>
          <span> &nbsp <img src="@/assets/img/user.png" style="width: 33px; height: 33px" /></span>
        </button>
        <ul class="dropdown-menu dropdown-menu-lg-end">
          <li><a class="dropdown-item">이벤트 정보</a></li>
          <li><hr class="dropdown-divider" /></li>
          <li><a class="dropdown-item" @click="changesignupOpen">회원가입</a></li>
          <li><a class="dropdown-item" @click="changeloginOpen">로그인</a></li>
          <li><hr class="dropdown-divider" /></li>
          <li><a class="dropdown-item">Menu item</a></li>
          <li><a class="dropdown-item">Menu item</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <LoginModal :data="loginOpen" @login-close="changeloginOpen" />
  <SignupModal :data="signupOpen" @signup-close="changesignupOpen" />
</template>

<style scoped>
.dropdown-menu {
  width: 230px;
  height: auto;
}
.dropdown-item {
  cursor: pointer;
}
</style>
