import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RouteComponentProps, withRouter } from 'react-router-dom';
import { Grid } from "semantic-ui-react";
import CategoriesList from "../../components/main/CategoriesList";
import ProductsList from "../../components/main/ProductsList";
import { RootState } from "../../modules";
import { loadCategories, loadProducts } from "../../modules/main";



interface Props extends RouteComponentProps{

}
const MainContainer: React.FC<Props> = ({ match }) => {

  const { id }: { id?: string } = match.params;
  const dispatch = useDispatch();
  const {
    categories,
    products,
    error,
  } = useSelector(
    (state: RootState) => ({
      categories: state.main.categories,
      products: state.main.products,
      error: state.main.error,
    })
  );



  useEffect(()=>{
    dispatch(loadCategories.request());
    // dispatch(loadProducts.request(id));
  },[dispatch]);
  

  useEffect(()=>{
    dispatch(loadProducts.request(id));
  },[id]);

  return (
    <Grid>
      <Grid.Row>
        <Grid.Column width={4}>
          <CategoriesList categories={categories} />
        </Grid.Column>
        <Grid.Column width={12}>
          <ProductsList products={products} />
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
};

export default withRouter(MainContainer);
