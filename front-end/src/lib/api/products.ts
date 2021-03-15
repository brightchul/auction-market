import axios from "axios";
import { AnchorHTMLAttributes } from "react";

export const loadProducts = () => axios.get("/api/products");

export const getProduct = (id: number) => axios.get(`/api/products/${id}`);

export const saveProduct = ({
  categories,
  title,
  images,
  content,
  startPrice,
}: {
  categories: any;
  title: any;
  images: any;
  content: any;
  startPrice: any;
}) => axios.post(`/api/products`, { categories, images, title, content, startPrice });

export const updateProduct = () => axios.put(`/api/products`);

export const deleteProduct = (id: number) =>
  axios.delete(`/api/products/${id}`);
