import React from "react";
import { Grid } from "semantic-ui-react";
import ProductsView from "../../components/view/ProductsView";




const ProductsViewContainer: React.FC = () => {
  return (
    <Grid>
      <Grid.Row columns="1">
        <Grid.Column>
          <ProductsView />
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
};

export default ProductsViewContainer;
