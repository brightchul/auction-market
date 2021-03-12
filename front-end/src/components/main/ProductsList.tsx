import React from "react";
import { Card, Grid, Header, Item } from "semantic-ui-react";

const items = [
  {
    childKey: 0,
    image: "https://react.semantic-ui.com/images/wireframe/image.png",
    header: "Header",
    description: "Description",
    meta: "Metadata",
    extra: "Extra",
  },
  {
    childKey: 1,
    image: "https://react.semantic-ui.com/images/wireframe/image.png",
    header: "Header",
    description: "Description",
    meta: "Metadata",
    extra: "Extra",
  },
  {
    childKey: 2,
    image: "https://react.semantic-ui.com/images/wireframe/image.png",
    header: "Header",
    description: "Description",
    meta: "Metadata",
    extra: "Extra",
  },
  {
    childKey: 3,
    image: "https://react.semantic-ui.com/images/wireframe/image.png",
    header: "Header",
    description: "Description",
    meta: "Metadata",
    extra: "Extra",
  },
  {
    childKey: 4,
    image: "https://react.semantic-ui.com/images/wireframe/image.png",
    header: "Header",
    description: "Description",
    meta: "Metadata",
    extra: "Extra",
  },
  {
    childKey: 5,
    image: "https://react.semantic-ui.com/images/wireframe/image.png",
    header: "Header",
    description: "Description",
    meta: "Metadata",
    extra: "Extra",
    style: { border: "1px solid black" },
  },
];

const ProductsList: React.FC = () => {
  return (
    <>
      <Header as="h3">
        상품목록
      </Header>
      <Item.Group items={items} />
    </>
  );
};

export default ProductsList;
