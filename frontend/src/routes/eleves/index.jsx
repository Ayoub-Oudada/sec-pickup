import axios from "axios";
import { redirect } from "react-router-dom";
import Index from "../../pages/eleves/Index";
import Create from "../../pages/eleves/Create";
import { requireAuth } from "../../utils/require-auth";
import api from "../../utils/api";
import { Update } from "../../pages/eleves/Update";


const backendUrl = import.meta.env.VITE_BACKEND_BASE_URL;

export const elevesLoader = async () => {
  const response = await axios.get(`${backendUrl}/api/eleves`);
  return response.data;
};

const eleveUpdateLoader = async ({params}) => {

  const [eleveResponse, parentsResponse, ruesResponse] = await Promise.all([
      axios.get(
      `${backendUrl}/api/eleves/${params.eleveId}`
      ),
      axios.get(`${backendUrl}/api/parents`),
      axios.get(`${backendUrl}/api/rues`)
    ]);

   return {
      eleve: eleveResponse.data,
      parents: parentsResponse.data,
      rues: ruesResponse.data,
    };  
};

const deleteEleve = ({ params }) => {
  try {
    axios.delete(`${backendUrl}/api/eleves/delete/${params.eleveId}`);
  } catch (error) {
    console.log(error);
  }
  return redirect("/eleves");
};

export const storeEleve = async ({ request }) => {
    console.log(request)
  const data = await request.formData();

  console.log(Object.fromEntries(data));

  try {
    await api.post("/api/eleves/save", Object.fromEntries(data));
  } catch (error) {
    console.log(error);
    // if (error.response.status === HttpStatusCode.BadRequest) {
    //   return {
    //     error: error.response.data.errors.reduce((obj, item) => {
    //       obj[item.field] = item.message;
    //       return obj;
    //     }, {}),
    //   };
    // }
  }

  return redirect("/eleves");
};

const eleveCreateLoader = async () => {
  try {
    const [parentsResponse, ruesResponse] = await Promise.all([
      axios.get(`${backendUrl}/api/parents`),
      axios.get(`${backendUrl}/api/rues`),
    ]);

    return {
      parents: parentsResponse.data,
      rues: ruesResponse.data,
    };
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
};

const updateEleve = async ({ params, request }) => {
  const data = await request.formData();
  console.log(Object.fromEntries(data));

  try {
    await axios.put(
      `${backendUrl}/api/eleves/update/${params.eleveId}`,
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
  return redirect("/eleves");
};




export const eleveRoutes = [
  {
    index: true,
    path: "/eleves",
    element: <Index />,
    loader: requireAuth(elevesLoader),
  },
  {
    path: "/eleves/create",
    element: <Create />,
    loader: eleveCreateLoader,
    action: requireAuth(storeEleve),
  },
  {
    path: "/eleves/:eleveId",
    element: <Update />,
    action: updateEleve,
    loader: requireAuth(eleveUpdateLoader),
  },
  {
    path: "/eleves/:eleveId/delete",
    action: requireAuth(deleteEleve),
    errorElement: <div>Oops! There was an error.</div>,
  },
];
