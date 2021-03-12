import React from "react";
import BaseTemplate from "../components/template/BaseTemplate";



import MainContainer from '../containers/main/MainContainer';

const MainPage: React.FC = () => {
  return (
    <BaseTemplate>
      <MainContainer />
    </BaseTemplate>
  );
};

export default MainPage;
