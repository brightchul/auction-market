import React from "react";
import { Breadcrumb, Image } from "semantic-ui-react";

const Description = () => {
  return (
    <div>
      <Breadcrumb>
        <Breadcrumb.Section link>가전제품</Breadcrumb.Section>
        <Breadcrumb.Divider />
        <Breadcrumb.Section link>노트북</Breadcrumb.Section>
        <Breadcrumb.Divider />
        <Breadcrumb.Section active>맥북</Breadcrumb.Section>
      </Breadcrumb>


      <Image src="https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/macbook-pro-13-og-202011?wid=600&hei=315&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1604347427000" />
    </div>
  );
};

export default Description;
