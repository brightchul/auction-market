import React, { useState } from "react";
import { Button, Checkbox, Form, Grid, Header } from "semantic-ui-react";
import ImageUploading, { ImageListType, ImageType, ImageUploadingPropsType } from "react-images-uploading";

interface Props {
  handleChange: any;
  handleSetImages: any;
  handleSubmit: any;
  categories: any;
  form: any;
}

const ProductsForm: React.FC<Props> = ({
  handleChange,
  handleSetImages,
  handleSubmit,
  categories,
  form,
}) => {
  const [images, setImages] = useState<ImageListType>([]);

  const get = (category: any) => {
    const result = <option value={category.id}>{category.title}</option>;

    return (
      <>
        {result} +{" "}
        {category.children && category.children.length > 0
          ? category.children.map((child: any) => get(child))
          : null}
      </>
    );
  };



  return (
    <>
      <Header
        as="h2"
        content="상품등록"
        subheader="Manage your account settings and set email preferences"
      />

      <Form onSubmit={handleSubmit}>
        <Form.Field
          label="카테고리"
          control="select"
          name="categories"
          onChange={handleChange}
        >
          {categories.map((category: any) => get(category))}
        </Form.Field>
        <ImageUploading
          multiple
          value={form.images}
          onChange={handleSetImages}
          maxNumber={10}
        >
          {({
            imageList,
            onImageUpload,
            onImageRemoveAll,
            onImageUpdate,
            onImageRemove,
            isDragging,
            dragProps,
          }) => (
            // write your building UI
            <div className="upload__image-wrapper">
              <button
                style={isDragging ? { color: "red" } : undefined}
                onClick={onImageUpload}
                {...dragProps}
              >
                Click or Drop here
              </button>
              &nbsp;
              <button onClick={onImageRemoveAll}>Remove all images</button>
              {imageList.map((image, index) => (
                <div key={index} className="image-item">
                  <img src={image.dataURL} alt="" width="100" />
                  <div className="image-item__btn-wrapper">
                    <button onClick={() => onImageUpdate(index)}>Update</button>
                    <button onClick={() => onImageRemove(index)}>Remove</button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </ImageUploading>
        <Form.Field>
          <label>상품명</label>
          <input name="title" value={form.title} onChange={handleChange} />
        </Form.Field>
        <Form.Field>
          <label>시작가격</label>
          <input
            name="startPrice"
            value={form.startPrice}
            onChange={handleChange}
          />
        </Form.Field>
        <Form.Field>
          <label>경매시작시간</label>
          <input
            name="startDateTime"
            value={form.startDateTime}
            onChange={handleChange}
          />
        </Form.Field>
        <Form.Field>
          <label>경매종료시간</label>
          <input
            name="endDateTime"
            value={form.endDateTime}
            onChange={handleChange}
          />
        </Form.Field>
        <Form.Field>
          <label>상세설명</label>
          <textarea
            name="content"
            value={form.content}
            onChange={handleChange}
          />
        </Form.Field>
        <Form.Field>
          <Checkbox label="I agree to the Terms and Conditions" />
        </Form.Field>

        <Button type="submit" onClick={handleSubmit}>
          등록
        </Button>
      </Form>
    </>
  );
};

export default ProductsForm;
