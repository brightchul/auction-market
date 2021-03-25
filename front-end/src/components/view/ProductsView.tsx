import React, { useState } from "react";
import { Breadcrumb, Button, Form, Grid, Header, Icon, Label, Modal, Statistic } from "semantic-ui-react";
import AuctionsList from "./AuctionsList";
import CommentsList from "./CommentsList";
import Dashboard from "./Dashboard";
import Description from "./Description";
import ReactECharts from 'echarts-for-react';
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
  handleEnterAuction,
  handleCancelAuction,
}) => {
  const [open, setOpen] = useState<boolean>(false);
  const options = {
    grid: { top: 8, right: 8, bottom: 24, left: 36 },
    xAxis: {
      type: 'category',
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        data: [820, 932, 901, 934, 1290, 1330, 1320],
        type: 'line',
        smooth: true,
      },
    ],
    tooltip: {
      trigger: 'axis',
    },
  };

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
            <Header
              as="h2"
              content={product.title}
              subheader="Manage your account settings and set email preferences"
            />
          </Grid.Column>
          <Grid.Column textAlign="right">
            <Button
              color="red"
              content="Like"
              icon="heart"
              label={{
                basic: true,
                color: "red",
                pointing: "left",
                content: "2,048",
              }}
            />
            <Modal
              basic
              onClose={() => setOpen(false)}
              onOpen={() => setOpen(true)}
              open={open}
              size="small"
              trigger={
                <Button basic color="green">
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
                    <input placeholder="First Name" />
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
                <Button color="green" inverted onClick={() => setOpen(false)}>
                  <Icon name="checkmark" /> Yes
                </Button>
              </Modal.Actions>
            </Modal>
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
          <Grid.Column width={10}>
            <Description products={product} />
          </Grid.Column>
          <Grid.Column width={6}>
            <Dashboard />
          </Grid.Column>
        </Grid.Row>

        <Grid.Row columns="1">
          <Grid.Column>
            <Header as="h3" dividing>
              상세설명
            </Header>
          </Grid.Column>
        </Grid.Row>
        <Grid.Row columns="1">
          <Grid.Column>
            <Header as="h3" dividing>
              분석
            </Header>
            <ReactECharts option={options} />
          </Grid.Column>
        </Grid.Row>
        <Grid.Row columns="1">
          <Grid.Column>
            <CommentsList 
              form={form.comments}
              comments={comments}
              handleChange={handleChangeComment}
              handleToggleCommentMode={handleToggleCommentMode}
              handleRegisterComment={handleRegisterComment}
              handleUpdateComment={handleUpdateComment}
              handleDeleteComment={handleDeleteComment}
            />
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </>
  );
};

export default ProductsView;
