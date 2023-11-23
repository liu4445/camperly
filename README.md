# README.md

# 요구사항 정의서

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled.png)

---

# ERD

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%201.png)

---

# 클래스 다이어그램

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%202.png)

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%203.png)

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%204.png)

---

# 화면 설계서

---

## <첨부1> 메인 페이지

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%205.png)

## <첨부2> 로그인 모달

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%206.png)

## <첨부3> 캠핑장 상세 조회 모달

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%207.png)

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%208.png)

## <첨부4> 캠핑장 상세 조회 페이지

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%209.png)

---

# 기획 배경 및 목표

기획 배경

- 최근 차박 및 글램핑의 인기가 증가함에 따라 캠핑장의 정보를 쉽고 빠르게 찾을 수 있도록 안내가 필요했음
- 기존의 ‘고캠핑’ 서비스가 있었지만, 필터를 통한 검색의 용이성이 떨어짐
- ‘고캠핑’이 ‘찜하기’, ‘예상 날씨 조회’ 등의 기능을 제공하지 않음

목표

- ‘고캠핑’과 차별화된 사용자 친화적인 UI와 검색 필터링 기능을 제공
- 캠핑장 상세 정보 조회에서 시설에 대한 픽토그램을 활용해 사용자 친화적인 UI 제공
- 카카오맵을 활용해 캠핑장의 위치 정보를 제공
- ‘고캠핑’이 제공하지 않는 소셜 로그인 기능 제공
- ‘찜하기’를 통해 인기 캠핑장을 확인할 수 있고, 사용자가 찜한 캠핑장 모아보기 기능을 제공
- 캠핑장의 위치를 통해 해당 지역의 10년간 월별 날씨 데이터를 제공

---

# 일정

---

# 개발 결과

## 시스템 구조도

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%2010.png)

## 적용한 패턴과 알고리즘

- MVC

![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%2011.png)

- 전략 패턴
    - OauthService 인터페이스를 생성하고, 이를 구현하는 구현체들을 통해 여러개의 소셜 로그인 기능을 확장할 수 있도록 적용함.
    
    ![Untitled](README%20md%20eeb48a218cb842a6b267adc1cf3472dc/Untitled%2012.png)
    

---

# 기대 효과

- 사용자가 보다 편하게 캠핑장의 정보를 조회할 수 있도록 함
- 캠핑장의 다양한 시설 및 테마 정보를 조회할 수 있도록 함
- 10년 간의 날씨 데이터를 통해 캠핑장 예약에 참고할 수 있도록 함

---

# 개발 후기

정재엽: 한 학기 동안 배웠던 Vue, Spring, Mybatis를 활용해 실제 프로젝트에 적용하며 개발을 진행했던게 흥미로웠고, 2학기 시작 전 좋은 경험이라고 생각합니다.

김가빈: 직접 Vue를 통해 UI를 그리며 프로젝트를 진행하는 것이 흥미로웠고, Vue의 컴포넌트 특성을 통해 코드를 더욱 간편하게 짤 수 있었던 것이 흥미로웠습니다. 앞으로 2학기에도 이와 같은 프로젝트들을 통해 더욱 성장할 수 있을 것이라고 생각합니다.