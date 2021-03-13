import React from "react";
import BaseTemplate from "../components/template/BaseTemplate";
import ProductsViewContainer from "../containers/products/ProductsViewContainer";

const ProductsViewPage: React.FC = () => {
  return (
    <BaseTemplate>
      <ProductsViewContainer />
    </BaseTemplate>
  );
};

export default ProductsViewPage;
