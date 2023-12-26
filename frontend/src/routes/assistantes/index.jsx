import Index from "../../pages/assistantes/Index";
import Show from "../../pages/assistantes/Show";
import { action as editAssistanteAction, Update } from "../../pages/assistantes/Update";


const assistanteUpdateLoader = async (params) => {
  const response = await fetch(import.meta.env.VITE_BACKEND_BASE_URL+ "/api/assistantes");
  const data = await response.json();
  return data;
};



export const assistantesRoutes = [
  {
    path: "assistantes",
    element: <Index />,
  },
  {
    path: "assistantes/:assistanteId",
    element: <Show/>,
  },
  {
    path: "assistantes/:assistanteId/edit",
    element: <Update/>,
    loader: assistanteUpdateLoader,
    action: editAssistanteAction
  }
];
