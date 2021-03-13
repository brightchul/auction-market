import axios from "axios";

export const loadComments = (id: number) =>
  axios.get(`/api/products/${id}/comments`);

export const getComment = (id: number) =>
  axios.get(`/api/products/${id}/comments/${id}`);

export const saveComment = (id: number) =>
  axios.post(`/api/products/${id}/comments`);

export const updateComment = (id: number, cid: number) =>
  axios.put(`/api/products/${id}/comments/${cid}`);

export const deleteComment = (id: number, cid: number) =>
  axios.delete(`/api/products/${id}/comments/${cid}`);
