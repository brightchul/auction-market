import React from 'react';
import { Route, Switch  } from 'react-router-dom';
import styled from '@emotion/styled'

import LoginPage from './pages/LoginPage';



const AppWrapper = styled.div`
  height: 100%;
  display: flex;
  justify-content: center;
`;



const App: React.FC = () => {
  
  
  


  return (
    <AppWrapper>
      <Switch>
        <Route path="/login" component={LoginPage}/>
        
      </Switch>
        
      
    </AppWrapper>
  );
}

export default App;
