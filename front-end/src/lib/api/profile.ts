import axios from "axios";

// 내정보
export const getProfile = () => axios.get("/api/profile");

export const updateProfile = () => axios.put("/api/profile");
