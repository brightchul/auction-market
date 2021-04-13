import numeral from "numeral";
import React, { AnchorHTMLAttributes, useEffect, useRef } from "react";
import { Link } from "react-router-dom";
import { Breadcrumb, Header, Icon, Item, Label, Loader, Pagination } from "semantic-ui-react";
import Countdown from 'react-countdown';


interface Props {
  loading: any;
  products: any;
  pageable: any;
  handleToggleLike: any;
  handlePaging: any;
}

const ProductsList: React.FC<Props> = ({
  loading,
  products,
  pageable,
  handleToggleLike,
  handlePaging,
}) => {

  return (
    <>
      <Header as="h3">상품목록</Header>
      {products && (
        <>
          <Item.Group>
            {products.map((product: any) => (
              <Item key={product.id}>
                <Item.Image
                  size="small"
                  style={{
                    width: "200px",
                    height: "154px",
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
                    <span className="stay">
                      {product.startDateTime} ~ {product.endDateTime}
                    </span>
                  </Item.Meta>
                  <Item.Description>{product.content}</Item.Description>
                  <Item.Extra>
                    <Breadcrumb>
                      {product.categories
                        .map((category: any) => (
                          <Breadcrumb.Section key={category.key}>
                            {category.title}
                          </Breadcrumb.Section>
                        ))
                        .reduce((prev: any, curr: any) => (
                          <>
                            {prev}
                            <Breadcrumb.Divider />
                            {curr}
                          </>
                        ))}
                    </Breadcrumb>
                  </Item.Extra>

                  <Item.Extra>
                    <Label color={product.price ? "blue" : undefined}>
                      <Icon name="won sign" />

                      {product.state === "WAITING" &&
                        numeral(product.startPrice).format("0,0")}
                      {product.state === "SELLING" && (
                        <>
                          {product.price &&
                            numeral(products.price).format("0,0")}
                          {!product.price &&
                            `${numeral(product.startPrice).format(
                              "0,0"
                            )} 시작가 참여가능`}
                        </>
                      )}
                      {product.state === "FINISH" && (
                        <>
                          {product.price &&
                            numeral(products.price).format("0,0")}
                          {!product.price && "참여자없음"}
                        </>
                      )}
                    </Label>
                    <Label
                      color={product.isLike ? "red" : undefined}
                      onClick={() => {
                        handleToggleLike(product.id);
                      }}
                    >
                      <Icon name="heart" /> {product.numOfLike}
                    </Label>
                    <Label>
                      <Icon name="eye" /> {product.viewCount}
                    </Label>

                    {product.state === "WAITING" && (
                      <Countdown
                        date={new Date(product.startDateTime)}
                        renderer={({
                          days,
                          hours,
                          minutes,
                          seconds,
                          completed,
                        }) => {
                          return (
                            <Label color={days === 0 ? "pink" : "blue"}>
                              시작전&nbsp;
                              {days > 0 && days + "일"}
                              {hours > 0 && hours + "시간"}
                              {minutes > 0 && minutes + "분"}
                              {seconds}초
                            </Label>
                          );
                        }}
                      />
                    )}

                    {product.state === "SELLING" && (
                      <Countdown
                        date={new Date(product.endDateTime)}
                        renderer={({
                          days,
                          hours,
                          minutes,
                          seconds,
                          completed,
                        }) => {
                          return (
                            <Label color={days === 0 ? "pink" : "blue"}>
                              <Icon name="hourglass end" />
                              {days > 0 && days + "일"}
                              {hours > 0 && hours + "시간"}
                              {minutes > 0 && minutes + "분"}
                              {seconds}초
                            </Label>
                          );
                        }}
                      />
                    )}
                    {product.state === "FINISH" && <Label>종료</Label>}
                  </Item.Extra>
                </Item.Content>
              </Item>
            ))}
          </Item.Group>
          <Pagination
            activePage={pageable.page + 1}
            totalPages={pageable.total}
            onPageChange={handlePaging}
          />
        </>
      )}

      {loading && <Loader active inline="centered" />}
    </>
  );
};

export default ProductsList;
