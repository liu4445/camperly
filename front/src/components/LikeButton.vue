<script setup>
import { defineProps, ref, onMounted, defineEmits } from "vue";
import "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js";
import axios from "axios";
const { VITE_VUE_API_URL } = import.meta.env;
const props = defineProps({
  contentId: Number,
});
const contentId = ref(0);
const ischecked = ref(false);
const likeCnt = ref(0);
const emit = defineEmits(["likeCnt"]);
emit("likeCnt", likeCnt);

onMounted(() => {
  contentId.value = props.contentId;
  if (localStorage.getItem("token") != null) {
    getLike();
  }
  howManyLike();
});
const loginStatus = ref(false);
const okLogin = () => {
  if (ischecked.value) checkLike();
  else {
    uncheckLike();
  }
};
const popUpAlert = () => {
  alert("로그인이 필요합니다.");
};

const rejectClickLike = () => {
  popUpAlert();
  loginStatus.value = false;
  localStorage.removeItem("token");
  const id = "#checkbox" + contentId.value;
  $(id).prop("checked", false);
};

const loginCheck = () => {
  const token = localStorage.getItem("token");
  axios({
    method: "post",
    url: VITE_VUE_API_URL + "member/auth",
    data: { contentId: contentId.value },
    headers: { Authorization: `Bearer ${token}` },
  })
    .then((res) => {
      loginStatus.value = true;
      okLogin();
    })
    .catch((error) => {
      rejectClickLike();
    });
};

const checkLike = () => {
  const token = localStorage.getItem("token");
  const param = { contentId: contentId.value };
  axios({
    method: "post",
    url: VITE_VUE_API_URL + "favorite",
    data: param,
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": `application/json`,
    },
  })
    .then((res) => {
      howManyLike();
    })

    .catch((error) => {});
};

const uncheckLike = () => {
  const token = localStorage.getItem("token");
  const param = { contentId: contentId.value };
  axios({
    method: "delete",
    url: VITE_VUE_API_URL + "favorite",
    data: param,
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": `application/json`,
    },
  })
    .then((res) => {
      howManyLike();
    })

    .catch((error) => {});
};
const favoriteList = ref([]);
const getLike = () => {
  const token = localStorage.getItem("token");
  const param = { contentId: contentId.value };
  axios({
    method: "get",
    url: VITE_VUE_API_URL + "favorite/" + param.contentId,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((res) => {
      favoriteList.value = res.data;
      if (res.data) ischecked.value = true;
      else {
        ischecked.value = false;
      }
    })

    .catch((error) => {});
};
const howManyLike = () => {
  const param = { contentId: contentId.value };
  axios({
    method: "get",
    url: VITE_VUE_API_URL + "favorite/count/" + param.contentId,
  })
    .then((res) => {
      favoriteList.value = res.data;
      likeCnt.value = res.data;
    })

    .catch((error) => {});
};
</script>

<template>
  <input type="checkbox" :id="`checkbox${contentId}`" @click="loginCheck" v-model="ischecked" />
  <label :for="`checkbox${contentId}`">
    <svg id="heart-svg" viewBox="467 392 58 57" xmlns="http://www.w3.org/2000/svg">
      <g id="Group" fill="none" fill-rule="evenodd" transform="translate(467 392)">
        <path
          d="M29.144 20.773c-.063-.13-4.227-8.67-11.44-2.59C7.63 28.795 28.94 43.256 29.143 43.394c.204-.138 21.513-14.6 11.44-25.213-7.214-6.08-11.377 2.46-11.44 2.59z"
          id="heart"
          fill="#AAB8C2"
        />
        <circle id="main-circ" fill="#E2264D" opacity="0" cx="29.5" cy="29.5" r="1.5" />

        <g id="grp7" opacity="0" transform="translate(7 6)">
          <circle id="oval1" fill="#9CD8C3" cx="2" cy="6" r="2" />
          <circle id="oval2" fill="#8CE8C3" cx="5" cy="2" r="2" />
        </g>

        <g id="grp6" opacity="0" transform="translate(0 28)">
          <circle id="oval1" fill="#CC8EF5" cx="2" cy="7" r="2" />
          <circle id="oval2" fill="#91D2FA" cx="3" cy="2" r="2" />
        </g>

        <g id="grp3" opacity="0" transform="translate(52 28)">
          <circle id="oval2" fill="#9CD8C3" cx="2" cy="7" r="2" />
          <circle id="oval1" fill="#8CE8C3" cx="4" cy="2" r="2" />
        </g>

        <g id="grp2" opacity="0" transform="translate(44 6)">
          <circle id="oval2" fill="#CC8EF5" cx="5" cy="6" r="2" />
          <circle id="oval1" fill="#CC8EF5" cx="2" cy="2" r="2" />
        </g>

        <g id="grp5" opacity="0" transform="translate(14 50)">
          <circle id="oval1" fill="#91D2FA" cx="6" cy="5" r="2" />
          <circle id="oval2" fill="#91D2FA" cx="2" cy="2" r="2" />
        </g>

        <g id="grp4" opacity="0" transform="translate(35 50)">
          <circle id="oval1" fill="#F48EA7" cx="6" cy="5" r="2" />
          <circle id="oval2" fill="#F48EA7" cx="2" cy="2" r="2" />
        </g>

        <g id="grp1" opacity="0" transform="translate(24)">
          <circle id="oval1" fill="#9FC7FA" cx="2.5" cy="3" r="2" />
          <circle id="oval2" fill="#9FC7FA" cx="7.5" cy="2" r="2" />
        </g>
      </g>
    </svg>
  </label>
</template>

<style scoped>
/* 좋아요 버튼 */
p {
  color: red;
  font-weight: bold;
  font-size: 12px;
  display: inline;
}
svg {
  cursor: pointer;
  overflow: visible;
  width: 60px;
  #heart {
    transform-origin: center;
    animation: animateHeartOut 0.3s linear forwards;
  }
  #main-circ {
    transform-origin: 29.5px 29.5px;
  }
}

.likebtn input[type="checkbox"] {
  display: none;
}

.likebtn input[type="checkbox"]:checked + label svg {
  #heart {
    transform: scale(0.2);
    fill: #e2264d;
    animation: animateHeart 0.3s linear forwards 0.25s;
  }
  #main-circ {
    transition: all 2s;
    animation: animateCircle 0.3s linear forwards;
    opacity: 1;
  }
  #grp1 {
    opacity: 1;
    transition: 0.1s all 0.3s;
    #oval1 {
      transform: scale(0) translate(0, -30px);
      transform-origin: 0 0 0;
      transition: 0.5s transform 0.3s;
    }
    #oval2 {
      transform: scale(0) translate(10px, -50px);
      transform-origin: 0 0 0;
      transition: 1.5s transform 0.3s;
    }
  }
  #grp2 {
    opacity: 1;
    transition: 0.1s all 0.3s;
    #oval1 {
      transform: scale(0) translate(30px, -15px);
      transform-origin: 0 0 0;
      transition: 0.5s transform 0.3s;
    }
    #oval2 {
      transform: scale(0) translate(60px, -15px);
      transform-origin: 0 0 0;
      transition: 1.5s transform 0.3s;
    }
  }
  #grp3 {
    opacity: 1;
    transition: 0.1s all 0.3s;
    #oval1 {
      transform: scale(0) translate(30px, 0px);
      transform-origin: 0 0 0;
      transition: 0.5s transform 0.3s;
    }
    #oval2 {
      transform: scale(0) translate(60px, 10px);
      transform-origin: 0 0 0;
      transition: 1.5s transform 0.3s;
    }
  }
  #grp4 {
    opacity: 1;
    transition: 0.1s all 0.3s;
    #oval1 {
      transform: scale(0) translate(30px, 15px);
      transform-origin: 0 0 0;
      transition: 0.5s transform 0.3s;
    }
    #oval2 {
      transform: scale(0) translate(40px, 50px);
      transform-origin: 0 0 0;
      transition: 1.5s transform 0.3s;
    }
  }
  #grp5 {
    opacity: 1;
    transition: 0.1s all 0.3s;
    #oval1 {
      transform: scale(0) translate(-10px, 20px);
      transform-origin: 0 0 0;
      transition: 0.5s transform 0.3s;
    }
    #oval2 {
      transform: scale(0) translate(-60px, 30px);
      transform-origin: 0 0 0;
      transition: 1.5s transform 0.3s;
    }
  }
  #grp6 {
    opacity: 1;
    transition: 0.1s all 0.3s;
    #oval1 {
      transform: scale(0) translate(-30px, 0px);
      transform-origin: 0 0 0;
      transition: 0.5s transform 0.3s;
    }
    #oval2 {
      transform: scale(0) translate(-60px, -5px);
      transform-origin: 0 0 0;
      transition: 1.5s transform 0.3s;
    }
  }
  #grp7 {
    opacity: 1;
    transition: 0.1s all 0.3s;
    #oval1 {
      transform: scale(0) translate(-30px, -15px);
      transform-origin: 0 0 0;
      transition: 0.5s transform 0.3s;
    }
    #oval2 {
      transform: scale(0) translate(-55px, -30px);
      transform-origin: 0 0 0;
      transition: 1.5s transform 0.3s;
    }
  }
  #grp2 {
    opacity: 1;
    transition: 0.1s opacity 0.3s;
  }
  #grp3 {
    opacity: 1;
    transition: 0.1s opacity 0.3s;
  }
  #grp4 {
    opacity: 1;
    transition: 0.1s opacity 0.3s;
  }
  #grp5 {
    opacity: 1;
    transition: 0.1s opacity 0.3s;
  }
  #grp6 {
    opacity: 1;
    transition: 0.1s opacity 0.3s;
  }
  #grp7 {
    opacity: 1;
    transition: 0.1s opacity 0.3s;
  }
}

@keyframes animateCircle {
  40% {
    transform: scale(10);
    opacity: 1;
    fill: #dd4688;
  }
  55% {
    transform: scale(11);
    opacity: 1;
    fill: #d46abf;
  }
  65% {
    transform: scale(12);
    opacity: 1;
    fill: #cc8ef5;
  }
  75% {
    transform: scale(13);
    opacity: 1;
    fill: transparent;
    stroke: #cc8ef5;
    stroke-width: 0.5;
  }
  85% {
    transform: scale(17);
    opacity: 1;
    fill: transparent;
    stroke: #cc8ef5;
    stroke-width: 0.2;
  }
  95% {
    transform: scale(18);
    opacity: 1;
    fill: transparent;
    stroke: #cc8ef5;
    stroke-width: 0.1;
  }
  100% {
    transform: scale(19);
    opacity: 1;
    fill: transparent;
    stroke: #cc8ef5;
    stroke-width: 0;
  }
}

@keyframes animateHeart {
  0% {
    transform: scale(0.2);
  }
  40% {
    transform: scale(1.2);
  }
  100% {
    transform: scale(1);
  }
}

@keyframes animateHeartOut {
  0% {
    transform: scale(1.4);
  }
  100% {
    transform: scale(1);
  }
}
</style>
