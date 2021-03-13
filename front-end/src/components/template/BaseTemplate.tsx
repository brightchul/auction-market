import React from "react";
import { Link } from "react-router-dom";
import { Container, Menu, Divider, Button } from "semantic-ui-react";

const BaseTemplate: React.FC = ({ children }) => {
  return (
    <Container>
      <Menu text>
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
      </Menu>
      {children}
    </Container>
  );
};

export default BaseTemplate;
