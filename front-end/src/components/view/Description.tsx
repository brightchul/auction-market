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

  const slides = products.images.map((image:any)=> 
    (<img style={{objectFit: 'contain', backgroundColor: 'black'}} width="100%" height="300px" src={'/api/file/' + image.filename} />)
  )

    
   
  

  const thumbnails = products.images.map((image:any)=> 
  (<img style={{objectFit: 'contain', backgroundColor: 'black'}} width="80px" height="80px"  src={'/api/file/' + image.filename} />)
)


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
