import { Outlet } from "react-router-dom";
import { Box, Typography, Unstable_Grid2 as Grid } from "@mui/material";

const AuthLayout = () => {
  return (
    <Box
      component="main"
      sx={{
        display: "flex",
        flex: "1 1 auto",
      }}
    >
      <Grid container sx={{ flex: "1 1 auto", minHeight: "100vh" }}>
        <Grid
          xs={12}
          lg={6}
          sx={{
            backgroundColor: "background.paper",
            display: "flex",
            flexDirection: "column",
            position: "relative",
          }}
        >
          <Box
            component="header"
            sx={{
              left: 0,
              p: 3,
              position: "fixed",
              top: 0,
              // width: "100%",
            }}
          >
            <Box
              href="/"
              sx={{
                display: "inline-flex",
                height: 100,
              }}
            >
              <img src="/logo.png" />
            </Box>
          </Box>
          <Outlet />
        </Grid>
        <Grid
          xs={12}
          lg={6}
          sx={{
            alignItems: "center",
            backgroundImage: `url("http://localhost:5173/pexels-lil-artsy-3427702.jpg")`,
            display: "flex",
            justifyContent: "center",
            backgroundSize: "cover",
          }}
        >
          <Box sx={{ p: 3 }}>
            <Typography
              align="center"
              color="inherit"
              sx={{
                fontSize: "24px",
                lineHeight: "32px",
                color: "white",
                mb: 1,
              }}
              variant="h1"
            >
              Welcome to{" "}
              <Box component="a" sx={{ color: "#" }} target="_blank">
                Sec pickup
              </Box>
            </Typography>
            <Typography
              align="center"
              sx={{ mb: 3, color: "white" }}
              variant="subtitle1"
            >
              The plateform to guarante your kids safety
            </Typography>
            {/* <img alt="" src="/pexels-lil-artsy-3427702.jpg" /> */}
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default AuthLayout;
