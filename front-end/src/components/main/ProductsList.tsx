import React from "react";
import { Link } from "react-router-dom";
import { Card, Grid, Header, Item } from "semantic-ui-react";


interface Props {
  products: any;
}

const ProductsList: React.FC<Props> = ({
  products
}) => {
  return (
    <>
      <Header as="h3">상품목록</Header>
      <Item.Group>
        {products.map((product: any) => (
          <Item key={product.id}>
            <Item.Image
              size="small"
              style={{
                width: "150px",
                height: "124px",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                backgroundColor: 'black'
              }}
              src={"https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/macbook-pro-13-og-202011?wid=600&hei=315&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1604347427000"}
            />
            <Item.Content>
              <Item.Header>
                <Link to="/products/1">{product.title}</Link>
              </Item.Header>
              <Item.Meta>
                <span className="price">$1200</span>
                <span className="stay">1 Month</span>
              </Item.Meta>
              <Item.Description>{product.content}</Item.Description>
            </Item.Content>
          </Item>
        ))}
      </Item.Group>
    </>
  );
};

export default ProductsList;
