<script setup>
import { ref , onMounted} from "vue";
import LoginModal from "./LoginModal.vue";
import SignupModal from "./SignupModal.vue";
import SearchHeader from "@/components/SearchHeader.vue";
import { useRouter } from "vue-router"
import axios from "axios"
const { VITE_VUE_API_URL, VITE_VUE_INDEX_URL } = import.meta.env;
const loginStatus = ref(false);
const router = useRouter();
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
const popUpAlert = () => {
  alert("로그인이 필요합니다.");
};
const clickLikeList = () => {
  const token = localStorage.getItem("token");
  axios({
    method: "post",
    url: VITE_VUE_API_URL + "member/auth",
    headers: { Authorization: `Bearer ${token}` },
  })
    .then((res) => {
      console.log("logincheck", res);
      loginStatus.value = true;
      router.push("camping/favorite")
    })

    .catch((error) => {
      popUpAlert();
      loginStatus.value = false;
      localStorage.removeItem("token");
      console.log(error);
    });
}

const logout = () => {
  localStorage.removeItem("token");
  loginStatus.value = false;
  console.log("로그아웃함")
  alert("로그아웃 되었습니다.");
  router.replace("/");
  location.href = VITE_VUE_INDEX_URL;
}

onMounted(() => {
  if (localStorage.getItem("token") != null) {
  loginStatus.value = true;
}
})
const logincheck = () => {
  const token = localStorage.getItem("token");
  axios({
    method: "post",
    url: VITE_VUE_API_URL + "member/auth",
    headers: { Authorization: `Bearer ${token}` },
  })
    .then((res) => {
      console.log("logincheck", res);
      loginStatus.value = true;
    
    })

    .catch((error) => {
      loginStatus.value = false;
      localStorage.removeItem("token");
    });
}
</script>

<template>
  <nav class="navbar">
    <div class="header">
      <div class ="header-brand">
      <a class="navbar-brand fs-2" href="/"><img src="@/assets/img/logo.png"></a>
    </div>
      <!-- 가운데 검색 헤더     -->
      <div class="searchHeader">
      <SearchHeader />
    </div>
      <!-- 여기부터 로그인/회원가입 드롭다운  -->
      <div class="btn-group">
        <button
          type="button"
          class="btn rounded-5"
          data-bs-toggle="dropdown"
          data-bs-display="static"
          aria-expanded="false"
          @click="logincheck"
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
          <li v-if="loginStatus == true">
            <li><hr class="dropdown-divider" /></li>
          </li>
          <li v-if="loginStatus == false">
            <a class="dropdown-item" @click="changeloginOpen">로그인</a>
          </li>
          <li v-else>
            <a class="dropdown-item" @click="logout" >로그아웃 </a>
          </li>
          <li v-if="loginStatus == true">
            <li><hr class="dropdown-divider" /></li>
            <a class="dropdown-item" @click="clickLikeList" >찜목록</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <LoginModal :data="loginOpen" @login-close="changeloginOpen" />
  <SignupModal :data="signupOpen" @signup-close="changesignupOpen" />
  <hr />
</template>

<style scoped>
.header-brand{
  display: inline-block;
  line-height: 50px;
  height: 50px;
  position: absolute;
  left: 0;
  
}
.searchHeader{
  display: inline-block;
  position: absolute;
  left: 38.5%;

}
.navbar {
  padding: 0px;
  width: 100%;
  margin-top: 1%;
  height: 51px;
  
}
.header {
  width: 90%;
  margin-left: auto;
  margin-right: auto;
  height: 51px;
  position: relative;
  

}
.header-brand img{
  width: auto;
  height: 50px;
}
.navbar-brand {
  margin-right: 0;
  padding: 0px;
  height: 50px;
  line-height: 50 px;
}
* {
  padding: 0px;
}
.btn-group{
  display: inline-block;
  position: absolute;
  height: 50px;
  right: 0;
  
  
}
.btn{
  display: inline-block;
  height: 50px;
  
  
}
.dropdown-menu {
  width: 230px;
  height: auto;
}
.dropdown-item {
  cursor: pointer;
}
</style>
