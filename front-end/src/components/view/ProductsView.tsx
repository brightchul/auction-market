import React, { useEffect, useState } from "react";
import { Breadcrumb, Button, Form, Grid, Header, Icon, Label, Loader, Modal, Statistic } from "semantic-ui-react";
import AuctionsList from "./AuctionsList";
import CommentsList from "./CommentsList";
import Dashboard from "./Dashboard";
import Description from "./Description";
import ReactECharts, { EChartsOption } from 'echarts-for-react';
interface Props {
  product: any;
  form: any;
  comments: any;
  handleChangeComment: any;
  handleToggleCommentMode: any;
  handleRegisterComment: any;
  handleUpdateComment: any;
  handleDeleteComment: any;
  handleToggleLike: any;
  handleChangeAuction: any;
  handleEnterAuction: any;
  handleCancelAuction: any;
}

const ProductsView: React.FC<Props> = ({
  product,
  form,
  comments,
  handleChangeComment,
  handleToggleCommentMode,
  handleRegisterComment,
  handleUpdateComment,
  handleDeleteComment,
  handleToggleLike,
  handleChangeAuction,
  handleEnterAuction,
  handleCancelAuction,
}) => {
  const [open, setOpen] = useState<boolean>(false);
  const [options, setOptions] = useState<EChartsOption>({
    grid: { top: 8, right: 8, bottom: 24, left: 80 },
    dataZoom: {
      type: 'inside'
      
    },
    xAxis: {
      type: "time",
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        data: [],
        type: "line",
        smooth: true,
      },
    ],
    tooltip: {
      trigger: "axis",
    },
  });


  useEffect(()=>{
    if(product){

      const data = [
        [new Date(product?.startDateTime), null],
        ...product?.auctions.map((auction: any) => [
          auction.createdAt,
          auction.price,
        ]),
        [new Date(product?.endDateTime), null]
      ];


      setOptions({
        ...options,
        dataZoom: {
          type: 'inside',
          // startValue: '',
          endValue: new Date(),
        },
        series : [
          {
            data: data ,
            type: "line",
            smooth: true,
          },
        ]
      })
    }

  },[product]);
  
  return (
    <>
      {/* <Breadcrumb>
        <Breadcrumb.Section link>가전제품</Breadcrumb.Section>
        <Breadcrumb.Divider />
        <Breadcrumb.Section link>노트북</Breadcrumb.Section>
        <Breadcrumb.Divider />
        <Breadcrumb.Section active>맥북</Breadcrumb.Section>
      </Breadcrumb>
       */}

      <Grid>
        <Grid.Row columns="2">
          <Grid.Column>
            {product && (
              <Header
                as="h2"
                content={product.title}
                subheader="Manage your account settings and set email preferences"
              />
            )}
          </Grid.Column>
          <Grid.Column textAlign="right">
            <Button
              loading={!product}
              type="button"
              color={product?.isLike ? "red" : undefined}
              content="Like"
              icon="heart"
              // label={{
              //   basic: true,
              //   color: product?.isLike ? "red" : undefined,
              //   pointing: "left",
              //   content: `${product ? product.numOfLike : <Loader active inline />}`,
              // }}
              onClick={handleToggleLike}
            />
            <Modal
              basic
              onClose={() => setOpen(false)}
              onOpen={() => setOpen(true)}
              open={open}
              size="small"
              trigger={
                <Button basic color="green" loading={!product}>
                  입찰하기
                </Button>
              }
            >
              <Header icon>
                <Icon name="archive" />
                새로운 가격을 제시해주세요
              </Header>
              <Modal.Content>
                {/* <p>
                Your inbox is getting full, would you like us to enable
                automatic archiving of old messages?
              </p> */}
                <Form>
                  <Form.Field>
                    <input name="price" onChange={handleChangeAuction} />
                  </Form.Field>
                </Form>
              </Modal.Content>
              <Modal.Actions>
                <Button
                  basic
                  color="red"
                  inverted
                  onClick={() => setOpen(false)}
                >
                  <Icon name="remove" /> No
                </Button>
                <Button color="green" inverted onClick={handleEnterAuction}>
                  <Icon name="checkmark" /> Yes
                </Button>
              </Modal.Actions>
            </Modal>
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
          <Grid.Column width={10}>
            <Description product={product} />
          </Grid.Column>
          <Grid.Column width={6}>
            <Dashboard product={product} />
          </Grid.Column>
        </Grid.Row>

        <Grid.Row columns="1">
          <Grid.Column>
            <Header as="h3" dividing>
              상세설명
            </Header>
            {!product && <Loader active inline="centered" />}
            {product && product.content}
          </Grid.Column>
        </Grid.Row>
        <Grid.Row columns="1">
          <Grid.Column>
            <Header as="h3" dividing>
              분석
            </Header>
            {!product && <Loader active inline="centered" />}
            {product && <ReactECharts option={options} />}
          </Grid.Column>
        </Grid.Row>
        <Grid.Row columns="1">
          <Grid.Column>
            {!comments && <Loader active inline="centered" />}
            {comments && (
              <CommentsList
                form={form.comments}
                comments={comments}
                handleChange={handleChangeComment}
                handleToggleCommentMode={handleToggleCommentMode}
                handleRegisterComment={handleRegisterComment}
                handleUpdateComment={handleUpdateComment}
                handleDeleteComment={handleDeleteComment}
              />
            )}
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </>
  );
};

export default ProductsView;
