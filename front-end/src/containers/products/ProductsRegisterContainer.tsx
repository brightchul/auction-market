import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Grid } from "semantic-ui-react";
import ProductsForm from "../../components/products/ProductsForm";
import { RootState } from "../../modules";
import { loadCategories } from "../../modules/main";


const ProductsRegisterContainer: React.FC = () => {

  const dispatch = useDispatch();
  const {
    categories,
    
    error,
  } = useSelector(
    (state: RootState) => ({
      categories: state.main.categories,
      
      error: state.main.error,
    })
  );



  useEffect(()=>{
    dispatch(loadCategories.request());
    
  },[dispatch]);



  const handleChange = () => {

  }
  
  const handleSubmit = () => {

  }




  return (
    <Grid>
      <Grid.Row columns="1">
        <Grid.Column>
          <ProductsForm categories={categories} />
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
};

export default ProductsRegisterContainer;
function dispatch(arg0: any) {
  throw new Error("Function not implemented.");
}

