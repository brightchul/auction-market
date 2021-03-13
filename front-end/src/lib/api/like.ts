import axios from "axios";

export const like = (id: number) => axios.patch(`/api/products/${id}/like`);

export const unlike = (id: number) => axios.patch(`/api/products/${id}/unlike`);
