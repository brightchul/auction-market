import React, { useEffect } from "react";
import { RouteComponentProps, withRouter } from "react-router-dom";
import { Button, Grid, Header } from "semantic-ui-react";
import queryString from "query-string";
import { useDispatch } from "react-redux";
import { loginKakao } from "../../modules/auth";

// import KakaoLogin from "react-kakao-login";
// import NaverLogin from "react-naver-login";


interface Props extends RouteComponentProps{

}

const LoginContainer: React.FC<Props> = ({ match, location }) => {

  const dispatch = useDispatch();

  
  useEffect(()=>{
    const { vendor } : { vendor? : string} = match.params;
    const query = queryString.parse(location.search);
    
    
    if(vendor && query.code){
      dispatch(loginKakao.request({ vendor, code: query.code }));
    }

  },[dispatch]);

  return (
    <Grid.Column style={{ maxWidth: 450 }}>
      <Header as="h1" textAlign="center">
        경매 서비스
      </Header>

      <Button
        basic
        color="yellow"
        href="https://kauth.kakao.com/oauth/authorize?client_id=&redirect_uri=http://localhost/login/kakao&response_type=code"
      >
        카카오 로그인
      </Button>

      {/* <KakaoLogin
        token={"7a6b383a3843fb59461d543f5094676f"}
        onSuccess={(result) => {
          
          const id = result.profile?.id;
          // id로 jwt요청

          // result.response
        }}
        onFail={console.error}
        onLogout={console.info}
        render={({ onClick }: { onClick: any }) => {
          return (
            <Button basic color="yellow" onClick={onClick}>
              카카오 로그인
            </Button>
          );
        }}
      /> */}
    </Grid.Column>
  );
};

export default withRouter(LoginContainer);
