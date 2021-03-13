import React from "react";
import { Button, Grid, Header } from "semantic-ui-react";

const LoginContainer: React.FC = () => {
  return (
    <Grid.Column style={{ maxWidth: 450 }}>
      <Header as="h1" textAlign="center">
        경매마켓
      </Header>

      <Button basic color="green" href="/oauth2/authorization/naver">
        네이버 로그인
      </Button>
      <Button basic color="yellow" href="/oauth2/authorization/kakao">
        카카오 로그인
      </Button>
    </Grid.Column>
  );
};

export default LoginContainer;
