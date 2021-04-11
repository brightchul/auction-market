## 설명
개인적으로 진행한 사이드 프로젝트입니다. 백엔드와 프론트엔드를 모두 포함하지만 배포목적의 서비스가 아니다 보니 제가 백엔드 개발자인 만큼 UI보다는 기능적관점 위주의 개발을 진행하였습니다. 백엔드의 테스트코드, API 문서화에 중점을 두고 개발을 진행하였습니다. 

다음은 프로젝트 진행에 대한 글입니다.
1. [프로젝트선정](https://youngwonseo.github.io/%EA%B2%BD%EB%A7%A4-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%8C%8C%ED%8A%B8-1-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%84%A0%EC%A0%95%ED%95%98%EA%B8%B0)
2. [기능정의](https://youngwonseo.github.io/%EA%B2%BD%EB%A7%A4-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%8C%8C%ED%8A%B8-2-%EA%B8%B0%EB%8A%A5%EC%A0%95%EC%9D%98)
3. [시스템 아키텍처와 프로젝트 세팅](https://youngwonseo.github.io/%EA%B2%BD%EB%A7%A4-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%8C%8C%ED%8A%B8-3-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98%EC%99%80-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%84%B8%ED%8C%85)
4. [데이터베이스 설계](https://youngwonseo.github.io/%EA%B2%BD%EB%A7%A4-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%8C%8C%ED%8A%B8-4-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EC%84%A4%EA%B3%84)
5. [소셜 로그인 구현 (JWT)](https://youngwonseo.github.io/%EA%B2%BD%EB%A7%A4-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%8C%8C%ED%8A%B8-5-%EC%86%8C%EC%85%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84-jwt)
6. [경매 서비스 파트6 - 상품목록 (Querydsl, N+1)](https://youngwonseo.github.io/%EA%B2%BD%EB%A7%A4-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%8C%8C%ED%8A%B8-6-%EC%83%81%ED%92%88%EB%AA%A9%EB%A1%9D-querydsl-n-1)
7. [실시간 경매 (Websocket, STOMP, RabbitMQ)](https://youngwonseo.github.io/%EA%B2%BD%EB%A7%A4-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%8C%8C%ED%8A%B8-7-%EC%8B%A4%EC%8B%9C%EA%B0%84-%EA%B2%BD%EB%A7%A4-websocket-stomp-rabbit-mq)
8. [테스팅과 문서화 (JUnit5, Spring Docs Rest)](https://youngwonseo.github.io/%EA%B2%BD%EB%A7%A4-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%8C%8C%ED%8A%B8-8-%ED%85%8C%EC%8A%A4%ED%8C%85%EA%B3%BC-%EB%AC%B8%EC%84%9C%ED%99%94-j-unit-5-spring-docs-rest)
9. [결과 UI](https://youngwonseo.github.io/%EA%B2%BD%EB%A7%A4-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%8C%8C%ED%8A%B8-9-%EA%B2%B0%EA%B3%BC-ui)
10. [마무리](https://youngwonseo.github.io/%EA%B2%BD%EB%A7%A4-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%8C%8C%ED%8A%B8-10-%EB%A7%88%EB%AC%B4%EB%A6%AC)

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
  * Spring Rest Docs
  * Lombok
  * Querydsl
  * Java JWT

* 프론트엔드
  * Typescript
  * ReactJS
  * Redux-Saga
  * Typesafe Action
  * Axios
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

* 로그인
  * 네이버
  * 카카오
* 상품목록 (페이징)
  * 전체 상품
  * 카테고리별 상품목록
  * 경매 참여중인 상품목록
  * 경매 완료된 상품목록
  * 관심(좋아요 활성화) 상품목록
  * 내가 판매중인 상품목록
* 상품검색
  * 상품 이름
  * 상품 판매자
  * 태그 기반
* 상품상세정보
  * 상품 상세정보
  * 해당 상품의 경매관련 정보
* 실시간 경매기능
  * 경매참여
  * 종료시간에 대한 경매종료
* 상품등록
  * 기본 정보 등록
  * 경매 관련 정보 등록
    * 경매 시작/종료 시간
    * 경매 시작가격
  * 이미지 등록
    * 썸네일 저장

</div>
</details>



## 시스템구성
![](./docs/system.png)




## 데이터베이스
![](./docs/database.png)



## UI







## References
* [스프링 부트와 AWS로 혼자 구현하는 웹 서비스 - 이동욱지음](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788965402602&orderClick=LEa&Kc=)
* [https://github.com/cheese10yun/spring-jpa-best-practices](https://github.com/cheese10yun/spring-jpa-best-practices)