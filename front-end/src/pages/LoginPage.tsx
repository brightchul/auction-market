import React from "react";

const LoginPage: React.FC = () => {


  return (
    <div>
      <a href="/oauth2/authorization/naver">네이버 로그인</a>
      <a href="/oauth2/authorization/kakao">카카오 로그인</a>
    </div>
  );
};

export default LoginPage;
