import axios from "axios";
import { redirect } from "react-router-dom";
import Create from "../../pages/rues/Create";
import { Index } from "../../pages/rues/Index";
import Update from "../../pages/rues/Update";
import api from "../../utils/api";


const backendUrl = import.meta.env.VITE_BACKEND_BASE_URL;

export const ruesLoader = async () => {
  const response = await axios.get(`${backendUrl}/api/rues`);
  return response.data;
};


const rueUpdateLoader = async ({params}) => {
  const response = await axios.get(
    `${backendUrl}/api/rues/${params.rueId}`
  );

  const response1 = await axios.get(
     `${backendUrl}/api/trajets`
  );

   return [response.data, response1.data];
};

const updateRue = async ({ params, request }) => {
  const data = await request.formData();
  console.log(Object.fromEntries(data));

  try {
    await axios.put(
      `${backendUrl}/api/rues/${params.rueId}`,
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
  return redirect("/rues/");
};


export const storeRue = async ({ request }) => {
  const data = await request.formData();

  try {
    await api.post("/api/rues", Object.fromEntries(data));
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

  return redirect("/rues");
};


const deleteRue = ({ params }) => {
  try {
    axios.delete(`${backendUrl}/api/rues/${params.rueId}`);
  } catch (error) {
    console.log(error);
  }
  return redirect("/rues");
};

const rueCreateLoader = async () => {
  const response = await axios.get(`${backendUrl}/api/trajets`);
  console.log(response);
  return response.data;
}


export const rueRoutes = [
  {
    path: "/rues",
    element: <Index />,
    loader: ruesLoader,

  },
    {
    path: "/rues/:rueId/edit",
    element: <Update />,
    action: updateRue,
    loader: rueUpdateLoader,
  },
  {
    path: "/rues/create",
    element: <Create />,
    action: storeRue,
    loader: rueCreateLoader,
  },
  {
    path: "/rues/:rueId/delete",
    action: deleteRue,
    errorElement: <div>Oops! There was an error.</div>,
  },
];