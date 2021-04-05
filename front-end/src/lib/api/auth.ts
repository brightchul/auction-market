import axios from "axios";


export const loginKakao = ({ vendor, code }: { vendor: string; code: string }) =>
  axios.post(`/api/auth/login`, {
    vendor,
    code,
  });

