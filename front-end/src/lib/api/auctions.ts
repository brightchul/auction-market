import axios from "axios";

export const loadAuctions = (id: number) =>
  axios.get(`/api/products/${id}/auctions`);

export const getProduct = (id: number) => axios.get(`/api/products/${id}`);

export const saveAuctions = (id: number) =>
  axios.post(`/api/products/${id}/auctions`);

export const updateProduct = (id: number) =>
  axios.put(`/api/products/${id}/auctions`);

export const deleteProduct = (id: number) =>
  axios.delete(`/api/products/${id}/auctions`);
