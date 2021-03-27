import axios from "axios";

export const loadAuctions = (id: number) =>
  axios.get(`/api/products/${id}/auctions`);

export const enterAuctions = ({ id, price }: { id: number; price: number }) =>
  axios.patch(`/api/products/${id}/auctions/enter`, { price });

export const cancelProduct = ({ pid, aid }: { pid: number; aid: number }) =>
  axios.patch(`/api/products/${pid}/auctions/${aid}/cancel`);

