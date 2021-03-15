import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { withRouter } from 'react-router-dom';
import { Grid } from "semantic-ui-react";
import ProductsView from "../../components/view/ProductsView";
import { RootState } from "../../modules";
import { loadProduct } from "../../modules/register";



interface Props {
  match: any;
  history: any;
}

const ProductsViewContainer: React.FC<Props> = ({
  match,
  history
}) => {

  const dispatch = useDispatch();
  const { id } = match.params;
  

  const {
    categories,
    form,
    error,
  } = useSelector(
    (state: RootState) => ({
      categories: state.main.categories,
      form: state.register.form,
      error: state.register.error,
    })
  );
  

  useEffect(()=>{
    dispatch(loadProduct.request(id));
  },[dispatch]);

  return (
    <Grid>
      <Grid.Row columns="1">
        <Grid.Column>
          <ProductsView products={form}/>
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
};

export default withRouter(ProductsViewContainer);
