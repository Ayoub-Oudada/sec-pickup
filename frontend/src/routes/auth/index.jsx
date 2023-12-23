import ForgetPassword from "../../pages/auth/ForgetPassword";
import Login from "../../pages/auth/Login";

export const authRoutes = [
  {
    path: "login",
    element: <Login />,
  },
  {
    path: "forget-password",
    element: <ForgetPassword />,
  },
];
