import React from 'react';
import { Breadcrumb, Comment, Header, Item } from 'semantic-ui-react';
import AuctionsList from './AuctionsList';





const ProductsDescription : React.FC = () => {

  const sections = [
    { key: 'Home', content: 'Home', link: true },
    { key: 'Store', content: 'Store', link: true },
    { key: 'Shirt', content: 'T-Shirt', active: true },
  ]


  return (
    <div>
      <Breadcrumb icon="right angle" sections={sections} />
    </div>
  );
}

export default ProductsDescription;