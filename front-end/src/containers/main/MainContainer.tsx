import React from "react";
import { Grid } from "semantic-ui-react";
import ProductsDetail from "../../components/main/ProductsDetail";
import ProductsList from "../../components/main/ProductsList";





const MainContainer: React.FC = () => {
  return (
    <Grid>
      <Grid.Row columns="2">
        <Grid.Column><ProductsList/></Grid.Column>
        <Grid.Column><ProductsDetail/></Grid.Column>
      </Grid.Row>
    </Grid>
  );
};

export default MainContainer;
