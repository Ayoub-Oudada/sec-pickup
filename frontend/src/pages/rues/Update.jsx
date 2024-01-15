import {
  Box,
  Container,
  Stack,
  Typography,
  Unstable_Grid2 as Grid,
  Button,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  Divider,
  TextField,
  MenuItem,
} from "@mui/material";
import { useCallback, useState } from "react";
import { Form, redirect, useActionData, useLoaderData } from "react-router-dom";


export async function action({ request, params }) {
  console.log("test");
  const formData = await request.formData();
  const updates = Object.fromEntries(formData);

  const requestOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(updates),
  };

  await fetch(import.meta.env.VITE_BACKEND_BASE_URL+ "/api/rues/updates.id", requestOptions);

  return redirect(`/rues/`);
}

const Update = () => {

  const  [rue, originalTrajets] = useLoaderData();

  const trajets = originalTrajets.map((item) => ({
  ...item,
  value: item.libTrajet,
  }));

  console.log(trajets);
  
  const data = useActionData();


  return (
    <>
      <div>
        <title>Rue | Update</title>
      </div>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          py: 8,
        }}
      >
        <Container maxWidth="lg">
          <Stack spacing={3}>
            <div>
              <Typography variant="h4">Rue</Typography>
            </div>
            <div>
              <Grid container spacing={4}>
                <Grid xs={16} md={12} lg={12}>
                  <Form autoComplete="off" noValidate method="post">
                    <Card>
                      <CardHeader
                        subheader="La information peut être modifiée."
                        title="Profile"
                      />
                      <CardContent sx={{ pt: 0 }}>
                        <Box sx={{ m: -1.5 }}>
                          <Grid container spacing={3}>
                            <Grid xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="libellé rue"
                                name="lib_rue"
                                required
                                defaultValue={rue.lib_rue}
                                error={data?.error && data?.error?.lib_rue}
                                helperText={data?.error?.lib_rue }
                              />
                            </Grid>
                            <Grid
                              xs={12}
                              md={6}
                            >
                              <TextField
                                fullWidth
                                label="Sélectionner le trajet..."
                                name="trajetId"
                                required
                                select
                                defaultValue={rue.trajet}
                                error={data?.error && data?.error?.trajet}
                                helperText={data?.error?.trajet}
                                SelectProps={{ native: true }}
                              >

                               
                                {trajets.map((option) => (

                                   <option key={option.id} value={option.id}>
                                      {option.libTrajet}
                                    </option>
                                 
                                ))}
                                  <option key="null" value="null">
                                   Aucun trajet
                                 </option>
                              </TextField>
                            </Grid>
                          </Grid>
                        </Box>
                      </CardContent>
                      <Divider />
                      <CardActions sx={{ justifyContent: "flex-end" }}>
                        <Button variant="contained" type="submit">
                          Sauvegarder
                        </Button>
                      </CardActions>
                    </Card>
                  </Form>
                </Grid>
              </Grid>
            </div>
          </Stack>
        </Container>
      </Box>
    </>
  );
}

export default Update