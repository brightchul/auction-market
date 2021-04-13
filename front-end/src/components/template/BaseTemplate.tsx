import React from "react";
import { useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { Container, Menu, Divider, Button, Grid } from "semantic-ui-react";
import storage from '../../lib/storage';
import { logout } from "../../modules/auth";

const BaseTemplate: React.FC = ({ children }) => {


  const dispatch = useDispatch();

  const handleLogout = () => {
    storage.remove('loggedInfo');
    dispatch(logout());
  }


  return (
    <div style={{ flexDirection: "column" }}>
      <Menu text fixed="top" style={{ backgroundColor: "white" }}>
        <Container>
          <Menu.Item name="editorials">
            <Link to="/main">메인</Link>
          </Menu.Item>
          <Menu.Item name="upcomingEvents">
            <Link to="/main?type=LIKE">관심 상품</Link>
          </Menu.Item>
          <Menu.Item name="reviews">
            <Link to="/main?type=AUCTION">참여중인 상품</Link>
          </Menu.Item>
          <Menu.Item >
            <Link to="/main?type=FINISH">완료 상품</Link>
          </Menu.Item>

          <Menu.Item position="right">
            <Link to="/products/register">상품등록</Link>
          </Menu.Item>
          <Menu.Item>
            <Link to="/main?type=OWN">내가 등록한 상품</Link>
          </Menu.Item>
          <Menu.Item>
            <Link to="/profile">내정보</Link>
          </Menu.Item>
          <Menu.Item>
            <Link to="/login" onClick={handleLogout}>로그아웃</Link>
          </Menu.Item>
        </Container>
      </Menu>

      <Container style={{ height: "3.5em" }}></Container>

      <Container style={{paddingLeft: "0.5em"}}>{children}</Container>
    </div>
  );
};

export default BaseTemplate;
