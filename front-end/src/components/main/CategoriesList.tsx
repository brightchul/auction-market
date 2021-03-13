import React from "react";
import { Header, List } from "semantic-ui-react";

interface Props{
  categories: any;
}

const CategoriesList: React.FC<Props> = ({ categories }) => {


  const get = (category: any) => {
    const hasChildren = category.children && category.children.length > 0;

    return (
      <List.Item>
        <List.Icon name={hasChildren ? "folder" : "file"} />
        <List.Content>
          <List.Header>{category.title}</List.Header>
          {/* <List.Description>Source files for project</List.Description> */}
          {hasChildren > 0 ? (
            <List.List>
              {category.children.map((child: any) => get(child))}
            </List.List>
          ) : null}
        </List.Content>
      </List.Item>
    );
  }

  return (
    <>
      <Header as="h3">카테고리</Header>
      <List>{categories.map((category: any) => get(category))}</List>
    </>
  );
};

export default CategoriesList;
