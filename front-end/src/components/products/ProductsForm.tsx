import React, { useEffect, useState } from "react";
import { Button, Checkbox, Form, Grid, Header, List, Image, ButtonGroup } from "semantic-ui-react";
import ImageUploading, { ImageListType, ImageType, ImageUploadingPropsType } from "react-images-uploading";
import DatePicker from "tui-date-picker";
import moment from 'moment';

interface Props {
  handleChange: any;
  handleSetImages: any;
  handleDatePickerChange: any;
  handleSubmit: any;
  handleCancel: any;
  categories: any;
  form: any;
}

const ProductsForm: React.FC<Props> = ({
  handleChange,
  handleSetImages,
  handleDatePickerChange,
  handleSubmit,
  handleCancel,
  categories,
  form,
}) => {
  const [images, setImages] = useState<ImageListType>([]);


  const startWrapper = React.createRef<HTMLDivElement>();
  const startTarget = React.createRef<HTMLInputElement>();
  const endWrapper = React.createRef<HTMLDivElement>();
  const endTarget = React.createRef<HTMLInputElement>();
  
  useEffect(()=>{
    var today = new Date();
    var picker = DatePicker.createRangePicker({
      startpicker: {
        date: today,
        input: startTarget.current ? startTarget.current : "",
        container: startWrapper.current ? startWrapper.current : "",
      },
      endpicker: {
        date: today,
        input: endTarget.current ? endTarget.current : "",
        container: endWrapper.current ? endWrapper.current : "",
      },
      timePicker: true,
      format: "yyyy-MM-dd HH:mm A",
      selectableRanges: [
        [
          today,
          new Date(today.getFullYear() + 1, today.getMonth(), today.getDate()),
        ],
      ],
    });


    picker.on('change:start', () => {
      handleDatePickerChange({
        name: "startDateTime",
        value: moment(picker.getStartDate()).format('YYYY-MM-DD hh:mm A'),
      })
    })

    picker.on('change:end', () => {
      handleDatePickerChange({
        name: "endDateTime",
        value: moment(picker.getEndDate()).format('YYYY-MM-DD hh:mm A'),
      })
    })


  },[]);


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
            <>
              <div>
                <Button
                  type="button"
                  style={isDragging ? { color: "red" } : undefined}
                  onClick={onImageUpload}
                  {...dragProps}
                >
                  사진업로드
                </Button>
                &nbsp;
                <Button type="button" onClick={onImageRemoveAll}>
                  모든사진제거
                </Button>
              </div>

              <List horizontal>
                {imageList.map((image, index) => (
                  <List.Item key={index} className="image-item">
                    <List.Content>
                      <img
                        style={{
                          objectFit: "contain",
                          backgroundColor: "black",
                        }}
                        width="120px"
                        height="120px"
                        src={image.dataURL}
                      />
                      <div>
                        <Button
                          type="button"
                          onClick={() => onImageUpdate(index)}
                        >
                          변경
                        </Button>
                        <Button
                          type="button"
                          onClick={() => onImageRemove(index)}
                        >
                          삭제
                        </Button>
                      </div>
                    </List.Content>
                  </List.Item>
                ))}
              </List>
            </>
          )}
        </ImageUploading>
        <Form.Field>
          <label>상품명</label>
          <input
            name="title"
            value={form.title}
            onChange={handleChange}
            autoComplete="off"
          />
        </Form.Field>

        <Form.Field>
          <label>시작가격</label>
          <input
            name="startPrice"
            value={form.startPrice}
            onChange={handleChange}
            autoComplete="off"
          />
        </Form.Field>
        <Form.Group widths="equal">
          <Form.Field>
            <label>경매시작시간</label>
            <div className="tui-datepicker-input tui-datetime-input tui-has-focus">
              <input
                type="text"
                aria-label="Date-Time"
                ref={startTarget}
                name="startDateTime"
                value={form.startDateTime}
                autoComplete="off"
              />
            </div>
            <div ref={startWrapper}></div>

            {/* <input
              name="startDateTime"
              value={form.startDateTime}
              onChange={handleChange}
            /> */}
          </Form.Field>
          <Form.Field>
            <label>경매종료시간</label>
            <div className="tui-datepicker-input tui-datetime-input tui-has-focus">
              <input
                type="text"
                aria-label="Date-Time"
                ref={endTarget}
                name="endDateTime"
                value={form.endDateTime}
                autoComplete="off"
              />
            </div>
            <div ref={endWrapper}></div>

            {/* <input
              name="endDateTime"
              value={form.endDateTime}
              onChange={handleChange}
            /> */}
          </Form.Field>
        </Form.Group>
        <Form.Field>
          <label>상세설명</label>
          <textarea
            name="content"
            value={form.content}
            onChange={handleChange}
          />
        </Form.Field>
        <Form.Field>
          <Checkbox label="상품등록에 대한 약관에 동의합니다." />
        </Form.Field>

        <ButtonGroup floated="right">
          <Button>취소</Button>
          <Button type="submit" onClick={handleSubmit}>
            등록
          </Button>
        </ButtonGroup>
      </Form>
    </>
  );
};

export default ProductsForm;
