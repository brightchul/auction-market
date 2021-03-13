import React from "react";
import { Button, Checkbox, Form, Grid, Header } from "semantic-ui-react";

interface Props{
  categories: any;
}

const ProductsForm: React.FC<Props> = ({
  categories
}) => {


  const get = (category: any) => {
    const result = <option value={category.id}>{category.title}</option>;

    return <>{result} + {category.children && category.children.length > 0 ? 
      category.children.map((child: any) => get(child)): null}</>;
  }

  return (
    <>
      <Header
        as="h2"
        content="상품등록"
        subheader="Manage your account settings and set email preferences"
      />

      <Form>
        <Form.Field label="카테고리" control="select">
          {categories.map((category: any) => (
            get(category)
          ))}
        </Form.Field>
        <Form.Field>
          <label>상품명</label>
          <input />
        </Form.Field>
        <Form.Field>
          <label>시작가격</label>
          <input />
        </Form.Field>
        <Form.Field>
          <label>경매시작시간</label>
          <input />
        </Form.Field>
        <Form.Field>
          <label>경매종료시간</label>
          <input />
        </Form.Field>
        <Form.Field>
          <label>상세설명</label>
          <textarea />
        </Form.Field>
        <Form.Field>
          <Checkbox label="I agree to the Terms and Conditions" />
        </Form.Field>

        <Button type="submit">등록</Button>
      </Form>
    </>
  );
};

export default ProductsForm;
