import numeral from "numeral";
import React, { AnchorHTMLAttributes, useState } from "react";
import { Button, Form, Header, Icon, Loader, Modal, Statistic, Tab } from "semantic-ui-react";
import Countdown from 'react-countdown';

interface Props {
  product: any;
}



const Dashboard: React.FC<Props> = ({
  product
}) => {
  
  

  return (
    <>
      <Header as="h3" dividing>
        대시보드
      </Header>
      {!product && <Loader active inline="centered" />}
      {product && (
        <>
          <Statistic.Group
            size="mini"
            horizontal
            style={{ alignItems: "center" }}
          >
            <Statistic>
              <Statistic.Label></Statistic.Label>
              <Statistic.Value>{product.startDateTime}</Statistic.Value>
            </Statistic>
            <Statistic>
              <Statistic.Label></Statistic.Label>
              <Statistic.Value>{product.endDateTime}</Statistic.Value>
            </Statistic>
            <Statistic>
              <Statistic.Label>남은시간</Statistic.Label>
              <Statistic.Value>
                <Countdown
                  date={new Date(product.endDateTime)}
                  renderer={({ days, hours, minutes, seconds, completed }) => {
                    return <span>{days}:{hours}:{minutes}:{seconds}</span>;
                  }}
                />
              </Statistic.Value>
            </Statistic>
          </Statistic.Group>
          <Statistic.Group horizontal style={{ alignItems: "flex-end" }}>
            <Statistic>
              <Statistic.Label>가격</Statistic.Label>
              <Statistic.Value>
                {numeral(product.price).format("0,0")}
              </Statistic.Value>
            </Statistic>
            <Statistic>
              <Statistic.Label>입찰수</Statistic.Label>
              <Statistic.Value>
                {numeral(product.numOfAuctions).format("0,0")}
              </Statistic.Value>
            </Statistic>
            <Statistic>
              <Statistic.Label>참여자</Statistic.Label>
              <Statistic.Value>
                {numeral(product.numOfParticipant).format("0,0")}
              </Statistic.Value>
            </Statistic>
            <Statistic>
              <Statistic.Label>좋아요</Statistic.Label>
              <Statistic.Value>
                {numeral(product.numOfLike).format("0,0")}
              </Statistic.Value>
            </Statistic>
          </Statistic.Group>
        </>
      )}
    </>
  );
};

export default Dashboard;
