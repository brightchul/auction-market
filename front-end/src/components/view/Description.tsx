import React, { useEffect } from "react";
import { Breadcrumb, Header, Image, Loader } from "semantic-ui-react";
import Carousel, { Dots } from '@brainhubeu/react-carousel';
import '@brainhubeu/react-carousel/lib/style.css'; import { useState } from 'react';


interface Props {
  product: any;
}

const Description: React.FC<Props> = ({
  product
}) => {

  const [value, setValue] = useState(0);

  const [slides, setSlides] = useState(undefined);
  const [thumbnails, setThumbnails] = useState(undefined);


  useEffect(() => {
    if(product){
      setSlides(
        product.images.map((image: any) => (
          <img
            style={{ objectFit: "contain", backgroundColor: "black" }}
            width="100%"
            height="300px"
            src={"/api/file/" + image.filename}
          />
        ))
      );

      setThumbnails(
        product.images.map((image: any) => (
          <img
            style={{ objectFit: "contain", backgroundColor: "black" }}
            width="80px"
            height="80px"
            src={"/api/file/" + image.filename}
          />
        ))
      );
    }
  }, [product]);

  return (
    <>
      <Header as="h3" dividing>
        상품사진
      </Header>
      {!product && <Loader active inline='centered' />}
      {product && (
        <>
          <Carousel
            value={value}
            slides={slides}
            onChange={setValue}
          ></Carousel>
          <Dots
            // number={thumbnails?.length}
            thumbnails={thumbnails}
            value={value}
            onChange={setValue}
          />
        </>
      )}
    </>
  );
};

export default Description;
