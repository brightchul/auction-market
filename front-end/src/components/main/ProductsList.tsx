import React from "react";
import { Link } from "react-router-dom";
import { Breadcrumb, Card, Grid, Header, Item } from "semantic-ui-react";


interface Props {
  products: any;
}

const ProductsList: React.FC<Props> = ({
  products
}) => {




  const getCategories: any = (category: any) => {
      
      return (
        <>
          {category.parent ? (
            <>
              {getCategories(category.parent)}
              <Breadcrumb.Divider />
            </>
          ) : (
            <></>
          )}

          <Breadcrumb>{category.title}</Breadcrumb>
        </>
      );
  }

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
                backgroundColor: "black",
              }}
              src={
                product.images.length > 0
                  ? "/api/file/" + product.images[0].filename
                  : ""
              }
            />
            <Item.Content>
              <Item.Header>
                <Link to={`/products/${product.id}`}>{product.title}</Link>
              </Item.Header>
              <Item.Meta>
                <span className="price">$1200</span>
                <span className="stay">{product.endDate}</span>
              </Item.Meta>
              <Item.Description>{product.content}</Item.Description>
              <Item.Extra>
                <Breadcrumb>
                  {getCategories(product.categories)}
                </Breadcrumb>
              </Item.Extra>
            </Item.Content>
          </Item>
        ))}
      </Item.Group>
    </>
  );
};

export default ProductsList;
