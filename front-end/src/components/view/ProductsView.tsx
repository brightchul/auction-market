import React from "react";
import { Grid, Header } from "semantic-ui-react";
import AuctionsList from "./AuctionsList";
import Description from "./Description";


const ProductsView = () => {
  return (
    <>
      <Header
        as="h2"
        content="맥북 프로 16인치 (2021)"
        subheader="Manage your account settings and set email preferences"
      />
      <Grid>
        <Grid.Row columns="2">
          <Grid.Column>
            <Description />
          </Grid.Column>
          <Grid.Column>
            <AuctionsList />
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </>
  );
};

export default ProductsView;
