import AppLayout from "../components/layout/AppLayout";
import AuthLayout from "../components/layout/AuthLayout";
import ErrorPage from "../error-page";
import { assistantesRoutes } from "./assistantes";
import { authRoutes } from "./auth";
import { parentRoutes } from "./parents";
import { trajetsRoutes } from "./trajets";

export const mainRoutes = [
  {
    errorElement: <ErrorPage />,
    children: [
      {
        path: "/",
        element: <AppLayout />,
        children: [...parentRoutes, ...trajetsRoutes, ...assistantesRoutes],
      },
      {
        path: "/auth",
        element: <AuthLayout />,
        children: [...authRoutes],
      },
    ],
  },
];
