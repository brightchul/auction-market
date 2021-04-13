import React, { useEffect } from "react";
import { Route, RouteComponentProps, Switch, withRouter } from "react-router-dom";
import "semantic-ui-css/semantic.min.css";
import axios from 'axios';

import styled from "@emotion/styled";

import IndexPage from "./pages/IndexPage";
import LoginPage from "./pages/LoginPage";
import MainPage from "./pages/MainPage";
import ProductsRegisterPage from "./pages/ProductsRegisterPage";
import ProductsViewPage from "./pages/ProductsViewPage";
import ProfilePage from "./pages/ProfilePage";
import { shallowEqual, useDispatch, useSelector } from "react-redux";
import storage from './lib/storage';
import { setLoggedInfo } from "./modules/auth";
  
const AppWrapper = styled.div`
  height: 100%;
  display: flex;
  justify-content: center;
`;


interface Props extends RouteComponentProps {

}

const App: React.FC<Props> = ({ match, history }) => {

  const dispatch = useDispatch();
  const {
    loggedInfo
  } = useSelector(({ auth }: { auth: any; }) => ({
    loggedInfo: auth.loggedInfo,
  }), shallowEqual);




  // 로그인 체크
  useEffect(()=>{    
    const loggedInfoInStorage = storage.get('loggedInfo');
    if(match.url !== '/login' && !loggedInfo && !loggedInfoInStorage){
      history.push('/login');
    }
  
    if(!loggedInfo){
      dispatch(setLoggedInfo(loggedInfoInStorage));
    }

    // const { UserActions } = this.props;
    // UserActions.setLoggedInfo(loggedInfo);
    // try {
    //     await UserActions.checkStatus();
    // } catch (e) {
    //     storage.remove('loggedInfo');
    //     window.location.href = '/auth/login?expired';
    // }
  
  },[]);






  return (
    <AppWrapper>
      <Switch>
        <Route exact path="/intro" component={IndexPage} />
        <Route exact path="/login" component={LoginPage} />
        <Route path="/login/:vendor" component={LoginPage} />
        <Route exact path="/main" component={MainPage} />
        <Route exact path="/main/:category" component={MainPage} />
        <Route
          exact
          path="/products/register"
          component={ProductsRegisterPage}
        />
        <Route exact path="/products/:id" component={ProductsViewPage} />
        <Route exact path="/products/:id/:mode" component={ProductsRegisterPage} />
        <Route exact path="/profile" component={ProfilePage} />
      </Switch>
    </AppWrapper>
  );
};

export default withRouter(App);
