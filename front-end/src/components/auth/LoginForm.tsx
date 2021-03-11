import React from 'react';

import styled from '@emotion/styled';

const LoginFormWrapper = styled.div`
  display: flex;
	flex-direction: column;
	align-items: center;
	justify-items: center;
`;

const KakaoButton = styled.button`
  background-color: #FEE500;
  color: #000000;
  outline: none;
  border: none;
  box-sizing: border-box;
  border-radius: 0.25rem;
  height: 2rem;
  font-size: 0.875rem;
  padding: 0.5rem 1rem;
  &:focus {
    box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.2);
  }
  &:hover {
    /* background: #38d9a9; */
  }
  &:active {
    /* background: #12b886; */
  }
`;

interface Props {
  handleLogin: any;
}

const LoginForm: React.FC<Props> = ({
  handleLogin
}) => {
  return (
    <LoginFormWrapper>
      <KakaoButton onClick={handleLogin}>Login</KakaoButton>
    </LoginFormWrapper>
  );
};


export default LoginForm;