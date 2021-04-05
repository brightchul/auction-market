## 설명
개인적으로 진행한 사이드 프로젝트입니다. 백엔드와 프론트엔드를 모두 포함하지만 배포목적의 서비스가 아니다 보니 제가 백엔드 개발자인 만큼 UI보다는 기능적관점 위주의 개발을 진행하였습니다. 백엔드의 테스트코드, API 문서화에 중점을 두고 개발을 진행하였습니다. 


<details>
<summary>기술스택</summary>
<div markdown="1">

* 백엔드
  * Java8
  * Spring boot
  * Spring Data JPA
  * Spring Validation
  * Spring AMQP
  * Spring Websocket
  * Spring Security
  * Spring OAuth2 Client
  * Spring Rest Docs

* 프론트엔드
  * Typescript
  * ReactJS
  * Redux-Saga
  * Typesafe Action
  * Socket.io
  * Sementic UI
  * Echart

* 데이터베이스
  * MySql

* Etc
  * Docker
  * Nginx

</div>
</details>


<details>
<summary>기능</summary>
<div markdown="1">

* 소셜 로그인
  * 네이버
  * 카카오
* 상품목록
  * 카테고리별 전체 상품목록
  * 내가 경매 참여중인 상품목록
  * 경매 완료된 상품목록
  * 내가 판매중인 상품목록
* 상품검색
  * 상품 이름
  * 상품 판매자
  * 태그 기반
* 상품등록
  * 기본 정보 등록
  * 경매 관련 정보 등록
    * 경매 시작/종료 시간
    * 경매 시작가격
  * 이미지 등록
    * 썸네일 저장
* 실시간 경매기능
  * 가격제시
  * 종료시간에 대한 경매종료

</div>
</details>



## 시스템구성
![](./docs/system.png)




## 데이터베이스





## UI



## 기타 산출물
* 스프링부트, 리액트 기반 소셜 로그인(카카오, 네이버) [blog][github]




## References
* [스프링 부트와 AWS로 혼자 구현하는 웹 서비스 - 이동욱지음](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788965402602&orderClick=LEa&Kc=)
* [https://github.com/cheese10yun/spring-jpa-best-practices](https://github.com/cheese10yun/spring-jpa-best-practices)