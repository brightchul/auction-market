import React from "react";
import { Link } from 'react-router-dom';
import { Checkbox, Dimmer, Header, List, Loader } from "semantic-ui-react";

interface Props{
  categories: any;
}

const CategoriesList: React.FC<Props> = ({ categories }) => {


  const get = (category: any) => {
    const hasChildren = category.children && category.children.length > 0;

    return (
      <List.Item key={category.id}>
        <List.Icon name={hasChildren ? "folder" : "file"} />
        <List.Content>
          <List.Header>
            <Link to={`/main/${category.id}`}>{category.title}</Link>
          </List.Header>
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
      {!categories && <Loader active inline='centered' />}
      {categories && <List>{categories.map((category: any) => get(category))}</List>}
    </>
  );
};

export default CategoriesList;
