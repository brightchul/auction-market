import React from 'react';
import { Comment, Header, Item, Tab } from 'semantic-ui-react';
import AuctionsList from './AuctionsList';
import ProductsDescription from './ProductsDescription';



const fixedOverlayStyle = {
  
  position: 'fixed',
  top: '80px',
  zIndex: 10,
}

const ProductsDetail : React.FC = () => {
  const panes = [
    { menuItem: "제품상세정보", render: () => <Tab.Pane><ProductsDescription /></Tab.Pane> },
    { menuItem: "경매참여", render: () => <Tab.Pane><AuctionsList /></Tab.Pane> },
  ];

  return (
    <>
      <Header
        as='h2'
        content='Account Settings'
        subheader='Manage your account settings and set email preferences'
      />
      <Tab panes={panes}/>
    </>
    
  );
}

export default ProductsDetail;