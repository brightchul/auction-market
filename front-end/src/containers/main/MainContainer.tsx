import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RouteComponentProps, withRouter } from 'react-router-dom';
import { Grid } from "semantic-ui-react";
import CategoriesList from "../../components/main/CategoriesList";
import ProductsList from "../../components/main/ProductsList";
import { RootState } from "../../modules";
import { loadCategories, loadProducts, like, unLike } from "../../modules/main";



interface Props extends RouteComponentProps{

}
const MainContainer: React.FC<Props> = ({ match }) => {

  const { id }: { id?: string } = match.params;
  const dispatch = useDispatch();
  const {
    // loading,
    categories,
    products,
    error,
  } = useSelector(
    (state: RootState) => ({
      
      // loading: state.loading["main/LOAD_PRODUCTS"],
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


  const handleToggleLike = (id: number) => {
    const idx = products ? products.findIndex(product=>product.id === id) : -1;
    if(products && idx > -1 && !products[idx].isLike){
      dispatch(like.request(id));
    }else{
      dispatch(unLike.request(id));
    }
  }

  return (
    <Grid stackable>
      <Grid.Row>
        <Grid.Column width={4}>
          <CategoriesList categories={categories} />
        </Grid.Column>
        <Grid.Column width={12}>
          <ProductsList products={products} handleToggleLike={handleToggleLike} />
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
};

export default withRouter(MainContainer);
