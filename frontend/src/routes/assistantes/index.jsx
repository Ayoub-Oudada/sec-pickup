import axios from "axios";
import { redirect } from "react-router-dom";
import Index from "../../pages/assistantes/Index";
import {Update } from "../../pages/assistantes/Update";
import Create from "../../pages/assistantes/Create";


const backendUrl = import.meta.env.VITE_BACKEND_BASE_URL;

export const assistantesLoader = async () => {
  const response = await axios.get(`${backendUrl}/api/assistantes`);
  return response.data;
};

const assistanteUpdateLoader = async ({params}) => {
  const response = await axios.get(
    `${backendUrl}/api/assistantes/${params.assistanteId}`
  );

  console.log(response);
  return response.data;
  
};

const deleteAssistante = ({ params }) => {
  try {
    axios.delete(`${backendUrl}/api/assistantes/${params.assistanteId}`);
  } catch (error) {
    console.log(error);
  }
  return redirect("/assistantes");
};


const updateAssistante = async ({ params, request }) => {
  const data = await request.formData();
  console.log(Object.fromEntries(data));

  try {
    await axios.put(
      `${backendUrl}/api/assistantes/${params.assistanteId}`,
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
  return redirect("/assistantes");
};


export const storeAssistante = async ({ request }) => {
  const data = await request.formData();

  try {
    await axios.post(`${backendUrl}/api/assistantes`, Object.fromEntries(data));
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

  return redirect("/assistantes");
};


export const assistantesRoutes = [
  {
    path: "assistantes",
    element: <Index />,
    loader: assistantesLoader,

  },
  {
    path: "assistantes/:assistanteId/edit",
    element: <Update/>,
    loader: assistanteUpdateLoader,
    action: updateAssistante
  },
  {
    path: "/assistantes/create",
    element: <Create />,
    action: storeAssistante,
  },
  {
    path: "/assistantes/:assistanteId/delete",
    action: deleteAssistante,
    errorElement: <div>Oops! There was an error.</div>,
  },
];
