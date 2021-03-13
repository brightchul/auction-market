import React from "react";
import BaseTemplate from "../components/template/BaseTemplate";
import ProfileContainer from "../containers/profile/ProfileContainer";

const ProfilePage: React.FC = () => {
  return (
    <BaseTemplate>
      <ProfileContainer />
    </BaseTemplate>
  );
};

export default ProfilePage;
