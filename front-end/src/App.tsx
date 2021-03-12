import React from 'react';
import { Route, Switch  } from 'react-router-dom';
import 'semantic-ui-css/semantic.min.css'

import styled from '@emotion/styled'


import IndexPage from './pages/IndexPage';
import LoginPage from './pages/LoginPage';
import MainPage from './pages/MainPage';


const AppWrapper = styled.div`
  height: 100%;
  display: flex;
  justify-content: center;
`;



const App: React.FC = () => {
  
  
  


  return (
    <AppWrapper>
      <Switch>
        <Route exact path="/intro" component={IndexPage}/>
        <Route path="/login" component={LoginPage}/>
        <Route path="/" component={MainPage}/>
        
      </Switch>
        
      
    </AppWrapper>
  );
}

export default App;
