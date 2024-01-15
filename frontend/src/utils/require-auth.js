import { redirect } from "react-router-dom";

const token = localStorage.getItem("token");

export const requireAuth = (callback) => {
  if (!isLoggedIn()) return () => redirect("/auth/login");
  else return callback;
};

const isLoggedIn = () => {
  return token && token != null && token != "";
};
