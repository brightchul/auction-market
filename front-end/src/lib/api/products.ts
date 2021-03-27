import axios from "axios";
import { AnchorHTMLAttributes } from "react";

export const loadProducts = (id: number) => {
  if (id) {
    return axios.get(`/api/products/categories/${id}`);
  }
  return axios.get(`/api/products`);
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
