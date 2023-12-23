import Index from "../../pages/parents/Index";
import Show from "../../pages/parents/Show";

export const parentRoutes = [
  {
    index: true,
    path: "/parents",
    element: <Index />,
  },
  {
    path: "/parents/:parentId",
    element: <Show />,
  },
];
