import React, { useEffect } from "react";
import { RouteComponentProps, withRouter } from "react-router-dom";
import { Button, Grid, Header } from "semantic-ui-react";
import { Image } from 'semantic-ui-react'
import queryString from "query-string";
import { shallowEqual, useDispatch, useSelector } from "react-redux";
import { loginKakao } from "../../modules/auth";
import axios from 'axios';
import { RootState } from "../../modules";
// import KakaoLogin from "react-kakao-login";
// import NaverLogin from "react-naver-login";
import storage from '../../lib/storage';

interface Props extends RouteComponentProps{

}

const LoginContainer: React.FC<Props> = ({ history, match, location }) => {

  const dispatch = useDispatch();
  const {
    loading,
    loggedInfo,
    // user
  } = useSelector(
    (state: RootState) => ({
      loading: state.loading['auth/LOGIN_KAKAO'],
      loggedInfo: state.auth.loggedInfo,
      // user: user.user
    }),
    shallowEqual
  );
  
  useEffect(()=>{
    const { vendor } : { vendor? : string} = match.params;
    const query = queryString.parse(location.search);
    
    
    if(vendor && query.code){
      dispatch(loginKakao.request({ vendor, code: query.code }));
    }

  },[dispatch]);


  useEffect(() => {
    if (loggedInfo && loggedInfo.token) {
      // Http Header
      axios.defaults.withCredentials = true;
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${loggedInfo.token}`;
      history.push("/main");
      // Storage
      storage.set('loggedInfo', loggedInfo);
      console.log(storage.get('loggedInfo'));
    }
  }, [loggedInfo]);



  return (
    <Grid.Row style={{ maxWidth: "450px" }}>
      <Grid.Column>
        <Grid.Row style={{ marginBottom: "2rem" }}>
          <Grid.Column>
            <Header as="h1" textAlign="center">
              경매 서비스
            </Header>
            <Image src="/login.svg" style={{ padding: "1rem" }} />
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
          <Grid.Column>
            <Button
              color="yellow"
              href="https://kauth.kakao.com/oauth/authorize?client_id=&redirect_uri=http://localhost/login/kakao&response_type=code"
              style={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                color: "rgb(58, 58, 58)",
                backgroundColor: "rgb(255, 232, 18)",
              }}
              loading={loading}
            >
              <img
                src="/ic-kakaotalk-symbol-brown.png"
                alt=""
                style={{ 
                  visibility: loading? "hidden" : "visible",
                  width: "24px", height: "24px", marginRight: "4px" }}
                
              />
              <span>카카오 아이디로 로그인</span>
            </Button>
          </Grid.Column>
        </Grid.Row>
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
    </Grid.Row>
  );
};

export default withRouter(LoginContainer);
