import React from "react";
import { Breadcrumb, Header, Image } from "semantic-ui-react";
import Carousel, { Dots } from '@brainhubeu/react-carousel';
import '@brainhubeu/react-carousel/lib/style.css'; import { useState } from 'react';


interface Props {
  products: any;
}

const Description: React.FC<Props> = ({
  products
}) => {

  const [value, setValue] = useState(0);

  const slides = [
    (<img style={{objectFit: 'contain', backgroundColor: 'black'}} width="100%" height="300px" src="https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/macbook-pro-13-og-202011?wid=600&hei=315&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1604347427000" />),
    (<img style={{objectFit: 'contain', backgroundColor: 'black'}} width="100%" height="300px" src="https://cdn.pocket-lint.com/r/s/970x/assets/images/152137-laptops-review-apple-macbook-pro-2020-review-image1-pbzm4ejvvs.jpg" />),     
  ]

  const thumbnails = [
    (<img style={{objectFit: 'contain', backgroundColor: 'black'}} width="80px" height="80px" src="https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/macbook-pro-13-og-202011?wid=600&hei=315&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1604347427000" />),
    (<img style={{objectFit: 'contain', backgroundColor: 'black'}} width="80px" height="80px" src="https://cdn.pocket-lint.com/r/s/970x/assets/images/152137-laptops-review-apple-macbook-pro-2020-review-image1-pbzm4ejvvs.jpg" />),     
  ]
  return (
    <div>
      <Header as="h3" dividing>
        상품사진
      </Header>

      <Carousel value={value} slides={slides} onChange={setValue}></Carousel>
      <Dots
        number={slides.length}
        thumbnails={thumbnails}
        value={value}
        onChange={setValue}
      />
    </div>
  );
};

export default Description;
