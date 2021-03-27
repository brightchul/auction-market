import React from "react";
import { Link } from "react-router-dom";
import { Container, Menu, Divider, Button, Grid } from "semantic-ui-react";

const BaseTemplate: React.FC = ({ children }) => {
  return (
    <div style={{ flexDirection: "column" }}>
      <Menu text fixed="top" style={{ backgroundColor: "white" }}>
        <Container>
          <Menu.Item name="editorials">
            <Link to="/">메인</Link>
          </Menu.Item>
          <Menu.Item name="reviews">
            <Link to="/">참여중인 경매</Link>
          </Menu.Item>
          <Menu.Item name="upcomingEvents">
            <Link to="/">완료된 경매</Link>
          </Menu.Item>

          <Menu.Item position="right">
            <Link to="/products/register">상품등록</Link>
          </Menu.Item>
          <Menu.Item>
            <Link to="/profile">내정보</Link>
          </Menu.Item>
          <Menu.Item>
            <Link to="/login">로그인</Link>
          </Menu.Item>
        </Container>
      </Menu>

      <Container style={{ height: "3.5em" }}></Container>

      <Container style={{paddingLeft: "0.5em"}}>{children}</Container>
    </div>
  );
};

export default BaseTemplate;
