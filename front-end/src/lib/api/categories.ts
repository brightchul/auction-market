import axios from "axios";

export const loadCategories = () => axios.get("/api/categories");
