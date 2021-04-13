import axios from "axios";
import { AnchorHTMLAttributes } from "react";

export const loadProducts = ({type, page} : {type?: string, page?: number}) => {

  var query = [];
  if(page){
    query.push(["page",page]);
  }

  // type validation
  if(type){
    query.push(["type", type]);
  }

  
  var url = `/api/products?` + query.map(d=>d[0]+"="+d[1]).join('&')
  console.log(url)

  return axios.get(url);
}

export const getProduct = (id: number) => axios.get(`/api/products/${id}`);

export const saveProduct = ({
  categories,
  title,
  images,
  content,
  startPrice,
  startDateTime,
  endDateTime,
}: {
  categories: any;
  title: any;
  images: any;
  content: any;
  startPrice: any;
  startDateTime: string;
  endDateTime: string;
}) =>
  axios.post(`/api/products`, {
    categories,
    images,
    title,
    content,
    startPrice,
    startDateTime,
    endDateTime,
  });

export const updateProduct = () => axios.put(`/api/products`);

export const deleteProduct = (id: number) =>
  axios.delete(`/api/products/${id}`);
