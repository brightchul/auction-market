import React, { FormEvent, useEffect } from "react";
import { ImageListType } from "react-images-uploading";
import { useDispatch, useSelector } from "react-redux";
import { Grid } from "semantic-ui-react";
import ProductsForm from "../../components/products/ProductsForm";
import { RootState } from "../../modules";
import { loadCategories } from "../../modules/main";
import {
  changeField,
  setImages,
  registerProduct,
} from "../../modules/register";


const ProductsRegisterContainer: React.FC = () => {

  const dispatch = useDispatch();
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
    dispatch(loadCategories.request());
    
  },[dispatch]);



  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    dispatch(changeField({ key: name, value }));
  }
  const handleSetImages = (
    imageList: ImageListType,
    addUpdateIndex: number[] | undefined
  ) => {
    dispatch(setImages(imageList));
  };


  const handleSubmit = (e: React.FormEvent<FormEvent>) => {
    e.preventDefault();
    const { categories, title, content, images, startPrice, startDateTime, endDateTime } = form;
    
    dispatch(registerProduct.request({ ...form }));
  }




  return (
    <Grid>
      <Grid.Row columns="1">
        <Grid.Column>
          <ProductsForm
            handleChange={handleChange}
            handleSetImages={handleSetImages}
            handleSubmit={handleSubmit}
            form={form}
            categories={categories}
          />
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
};

export default ProductsRegisterContainer;
function dispatch(arg0: any) {
  throw new Error("Function not implemented.");
}

