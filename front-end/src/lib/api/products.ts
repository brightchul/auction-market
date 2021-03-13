import axios from "axios";

export const loadProducts = () => axios.get("/api/products");

export const getProduct = (id: number) => axios.get(`/api/products/${id}`);

export const saveProduct = () => axios.post(`/api/products`);

export const updateProduct = () => axios.put(`/api/products`);

export const deleteProduct = (id: number) =>
  axios.delete(`/api/products/${id}`);
