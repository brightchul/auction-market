import React, { useState } from "react";
import { Button, Form, Header, Icon, Modal, Statistic, Tab } from "semantic-ui-react";

interface Props {}

const Dashboard: React.FC<Props> = () => {
  
  

  return (
    <>
      <Header as="h3" dividing>
        대시보드
      </Header>
      <Statistic.Group horizontal style={{ alignItems: "flex-end" }}>
        <Statistic>
          <Statistic.Label>가격</Statistic.Label>
          <Statistic.Value>31,200,000</Statistic.Value>
        </Statistic>
        <Statistic>
          <Statistic.Label>입찰수</Statistic.Label>
          <Statistic.Value>22</Statistic.Value>
        </Statistic>
        <Statistic>
          <Statistic.Label>참여자</Statistic.Label>
          <Statistic.Value>22</Statistic.Value>
        </Statistic>
        <Statistic>
          <Statistic.Label>좋아요</Statistic.Label>
          <Statistic.Value>22</Statistic.Value>
        </Statistic>
      </Statistic.Group>

      
    </>
  );
};

export default Dashboard;
