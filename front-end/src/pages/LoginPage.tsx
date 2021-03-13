import React from "react";
import AuthTemplate from "../components/template/AuthTemplate";
import LoginContainer from "../containers/auth/LoginContainer";

const LoginPage: React.FC = () => {


  return (
    <AuthTemplate>
      <LoginContainer/>
    </AuthTemplate>
  );
};

export default LoginPage;
