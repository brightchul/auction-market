import React from "react";
import { Link } from "react-router-dom";
import { Container, Menu, Divider, Button } from "semantic-ui-react";

const BaseTemplate: React.FC = ({ children }) => {
  return (
    <Container>
      <Menu text>
        <Menu.Item name="editorials"><Link to="/">메인</Link></Menu.Item>
        <Menu.Item name="reviews">Reviews</Menu.Item>
        <Menu.Item name="upcomingEvents" >Upcoming Events</Menu.Item>
        <Menu.Item position="right"><Link to="/login">로그인</Link></Menu.Item>
      </Menu>
      {children}
    </Container>
  );
};

export default BaseTemplate;
