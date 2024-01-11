import { RouterProvider, createBrowserRouter } from "react-router-dom";
import { AuthProvider } from "./contexts/auth-context";
import { mainRoutes } from "./routes";
import { ThemeProvider } from "@emotion/react";
import { Backdrop, CircularProgress, CssBaseline } from "@mui/material";
import "simplebar-react/dist/simplebar.min.css";
import { createTheme } from "./theme";

const router = createBrowserRouter(mainRoutes);

const App = () => {
  const theme = createTheme();

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <AuthProvider>
        <RouterProvider router={router} />
      </AuthProvider>
    </ThemeProvider>
  );
};

// const CustomBackdrop = () => {
//   return (
//     <Backdrop
//       sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
//       open={open}
//       onClick={handleClose}
//     >
//       <CircularProgress color="inherit" />
//     </Backdrop>
//   );
// };

export default App;
