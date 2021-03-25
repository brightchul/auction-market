import axios from "axios";

export const loadComments = (id: number) =>
  axios.get(`/api/products/${id}/comments`);

// export const getComment = (id: number) =>
//   axios.get(`/api/products/${id}/comments/${id}`);

export const saveComment = ({ id, content }: { id: number; content: string }) =>
  axios.post(`/api/products/${id}/comments`, { content });

export const updateComment = ({
  id,
  cid,
  content,
}: {
  id: number;
  cid: number;
  content: string;
}) => axios.put(`/api/products/${id}/comments/${cid}`, { content });

export const deleteComment = ({ id, cid }: { id: number; cid: number }) =>
  axios.delete(`/api/products/${id}/comments/${cid}`);
