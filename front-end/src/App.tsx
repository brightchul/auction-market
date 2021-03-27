import React from "react";
import { Route, Switch } from "react-router-dom";
import "semantic-ui-css/semantic.min.css";

import styled from "@emotion/styled";

import IndexPage from "./pages/IndexPage";
import LoginPage from "./pages/LoginPage";
import MainPage from "./pages/MainPage";
import ProductsRegisterPage from "./pages/ProductsRegisterPage";
import ProductsViewPage from "./pages/ProductsViewPage";
import ProfilePage from "./pages/ProfilePage";

const AppWrapper = styled.div`
  height: 100%;
  display: flex;
  justify-content: center;
`;

const App: React.FC = () => {
  return (
    <AppWrapper>
      <Switch>
        <Route exact path="/intro" component={IndexPage} />
        <Route path="/login" component={LoginPage} />
        <Route exact path="/" component={MainPage} />
        <Route exact path="/category/:id" component={MainPage} />
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

export default App;
