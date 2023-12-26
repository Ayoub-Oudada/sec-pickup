import { HttpStatusCode } from "axios";
import Index from "../../pages/trajets/Index";
import Update from "../../pages/trajets/Update";
import api from "../../utils/api";
import { redirect } from "react-router-dom";
import { requireAuth } from "../../utils/require-auth";
import Create from "../../pages/trajets/Create";

const trajetsLoader = async () => {
  const response = await api.get("/api/trajets");
  return response.data;
};

const updateTrajetLoader = async ({ params }) => {
  const response = await api.get(`/api/trajets/${params.trajetId}`);
  return response.data;
};

const updateTrajetAction = async ({ params, request }) => {
  const data = await request.formData();
  try {
    await api.put("/api/trajets/" + params.trajetId, Object.fromEntries(data));
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
  return redirect("/trajets");
};

const deleteTrajetAction = async ({ params }) => {
  await api.delete("/api/trajets/" + params.trajetId);
  return redirect("/trajets");
};

const storeTrajet = async ({ request }) => {
  const data = await request.formData();

  try {
    await api.post("/api/trajets", Object.fromEntries(data));
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
  return redirect("/trajets");
};

export const trajetsRoutes = [
  {
    path: "/trajets",
    element: <Index />,
    loader: requireAuth(trajetsLoader),
  },
  {
    path: "/trajets/create",
    element: <Create />,
    loader: requireAuth(trajetsLoader),
    action: requireAuth(storeTrajet),
  },
  {
    path: "/trajets/:trajetId",
    element: <Update />,
    loader: requireAuth(updateTrajetLoader),
    action: requireAuth(updateTrajetAction),
  },
  {
    path: "/trajets/:trajetId/delete",
    action: requireAuth(deleteTrajetAction),
  },
];
