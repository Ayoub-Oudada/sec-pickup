import { redirect } from "react-router-dom";
import Create from "../../pages/parents/Create";
import Index from "../../pages/parents/Index";
import axios, { HttpStatusCode } from "axios";
import Update from "../../pages/parents/Update";

const baseBackendUrl = import.meta.env.VITE_BACKEND_BASE_URL;

export const parentsLoader = async () => {
  const response = await axios.get(`${baseBackendUrl}/api/parents`);
  return response.data;
};

export const storeParent = async ({ request }) => {
  const data = await request.formData();

  try {
    await axios.post(`${baseBackendUrl}/api/parents`, Object.fromEntries(data));
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
    axios.delete(`${baseBackendUrl}/api/parents/${params.parentId}`);
  } catch (error) {
    console.log(error);
  }
  return redirect("/parents");
};

const updateParentLoader = async ({ params }) => {
  const response = await axios.get(
    `${baseBackendUrl}/api/parents/${params.parentId}`
  );
  return response.data;
};

const updateParent = async ({ params, request }) => {
  const data = await request.formData();
  console.log(Object.fromEntries(data));

  try {
    await axios.put(
      `${baseBackendUrl}/api/parents/${params.parentId}`,
      Object.fromEntries(data)
    );
  } catch (error) {
    console.log(error);
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
    path: "/parents",
    element: <Index />,
    loader: parentsLoader,
  },
  {
    path: "/parents/create",
    element: <Create />,
    action: storeParent,
  },
  {
    path: "/parents/:parentId",
    element: <Update />,
    action: updateParent,
    loader: updateParentLoader,
  },
  {
    path: "/parents/:parentId/delete",
    action: deleteParent,
    errorElement: <div>Oops! There was an error.</div>,
  },
];
