import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Grid } from "semantic-ui-react";
import CategoriesList from "../../components/main/CategoriesList";
import ProductsList from "../../components/main/ProductsList";
import { RootState } from "../../modules";
import { loadCategories, loadProducts } from "../../modules/main";





const MainContainer: React.FC = () => {

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
    dispatch(loadProducts.request());
  },[dispatch]);
  

  return (
    <Grid>
      <Grid.Row columns="2">
        <Grid.Column><CategoriesList categories={categories}/></Grid.Column>
        <Grid.Column><ProductsList products={products}/></Grid.Column>
      </Grid.Row>
    </Grid>
  );
};

export default MainContainer;
