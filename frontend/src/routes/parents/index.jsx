import { redirect } from "react-router-dom";
import Create from "../../pages/parents/Create";
import Index from "../../pages/parents/Index";
import { HttpStatusCode } from "axios";
import Update from "../../pages/parents/Update";
import api from "../../utils/api";
import { requireAuth } from "../../utils/require-auth";

export const parentsLoader = async () => {
  const response = await api.get("/api/parents");
  return response.data;
};

export const storeParent = async ({ request }) => {
  const data = await request.formData();

  try {
    await api.post("/api/parents", Object.fromEntries(data));
  } catch (error) {
    if (error.response.status === HttpStatusCode.BadRequest) {
      return {
        error: error.response.data.errors.reduce((obj, item) => {
          obj[item.field] = item.message;
          return obj;
        }, {}),
      };
    }
  }

  return redirect("/parents");
};

const deleteParent = ({ params }) => {
  try {
    api.delete(`/api/parents/${params.parentId}`);
  } catch (error) {
    console.log(error);
  }
  return redirect("/parents");
};

const updateParentLoader = async ({ params }) => {
  const response = await api.get(`/api/parents/${params.parentId}`);
  return response.data;
};

const updateParent = async ({ params, request }) => {
  const data = await request.formData();

  try {
    await api.put(`/api/parents/${params.parentId}`, Object.fromEntries(data));
  } catch (error) {
    if (error.response.status === HttpStatusCode.BadRequest) {
      return {
        error: error.response.data.errors.reduce((obj, item) => {
          obj[item.field] = item.message;
          return obj;
        }, {}),
      };
    }
  }
  return redirect("/parents");
};

export const parentRoutes = [
  {
    index: true,
    path: "/parents",
    element: <Index />,
    loader: requireAuth(parentsLoader),
  },
  {
    path: "/parents/create",
    element: <Create />,
    action: requireAuth(storeParent),
  },
  {
    path: "/parents/:parentId",
    element: <Update />,
    action: updateParent,
    loader: requireAuth(updateParentLoader),
  },
  {
    path: "/parents/:parentId/delete",
    action: requireAuth(deleteParent),
    errorElement: <div>Oops! There was an error.</div>,
  },
];
