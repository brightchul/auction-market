import React from "react";
import BaseTemplate from "../components/template/BaseTemplate";
import ProductsRegisterContainer from "../containers/products/ProductsRegisterContainer";

const ProductsRegisterPage: React.FC = () => {
  return (
    <BaseTemplate>
      <ProductsRegisterContainer />
    </BaseTemplate>
  );
};

export default ProductsRegisterPage;
