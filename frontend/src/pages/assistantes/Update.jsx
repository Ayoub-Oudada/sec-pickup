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

  await fetch(import.meta.env.VITE_BACKEND_BASE_URL+ "/api/assistantes/updates.id", requestOptions);

  return redirect(`/assistantes/`);
}

export const Update = (request, params) => {

  const assistante = useLoaderData();
  const data = useActionData();


  return (
    <>
      <div>
        <title>Assistante | Update</title>
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
              <Typography variant="h4">Assistante</Typography>
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
                                label="prenom"
                                name="prenom"
                                required
                                defaultValue={assistante.prenom}
                                error={data?.error && data?.error?.prenom}
                                helperText={data?.error?.prenom}
                              />
                            </Grid>
                            <Grid xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="nom"
                                name="nom"
                                required
                                defaultValue={assistante.nom}
                                error={data?.error && data?.error?.nom}
                                helperText={data?.error?.nom}
                              />
                            </Grid>
                            <Grid xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="Adresse email"
                                name="email"
                                required
                                defaultValue={assistante.email}
                                error={data?.error && data?.error?.email}
                                helperText={data?.error?.email}
                              />
                            </Grid>
                            <Grid xs={12} md={6}>
                              <TextField
                                defaultValue={assistante.cni}
                                error={data?.error && data?.error?.cni}
                                helperText={data?.error?.cni}
                                fullWidth
                                label="Cni"
                                name="cni"
                                type="text"
                              />
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
};
