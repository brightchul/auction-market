import React from 'react';
import { Grid } from 'semantic-ui-react';



const AuthTemplate : React.FC = ({ children }) => {
  return (
    <Grid textAlign="center" style={{ height: "100vh" }} verticalAlign="middle">
      {children}
    </Grid>
  );
}

export default AuthTemplate;