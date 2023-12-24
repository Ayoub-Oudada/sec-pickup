import { Form, useActionData, useLoaderData } from "react-router-dom";
import {
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  Container,
  Divider,
  Unstable_Grid2 as Grid,
  Stack,
  TextField,
  Typography,
} from "@mui/material";

const Update = () => {
  const parent = useLoaderData();
  const data = useActionData();

  return (
    <>
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
              <Typography variant="h4">Parent</Typography>
            </div>
            <div>
              <Grid container spacing={4}>
                <Grid xs={16} md={12} lg={12}>
                  <Form autoComplete="off" noValidate method="POST">
                    <Card>
                      <CardHeader
                        subheader="La information peut être modifiée."
                        title="Parent informations"
                      />
                      <CardContent sx={{ pt: 0 }}>
                        <Box sx={{ m: -1.5 }}>
                          <Grid container spacing={3}>
                            <Grid xs={12} md={6}>
                              <TextField
                                defaultValue={parent.prenom}
                                error={data?.error && data?.error?.prenom}
                                helperText={data?.error?.prenom}
                                fullWidth
                                label="Prenom"
                                name="prenom"
                                required
                              />
                            </Grid>
                            <Grid xs={12} md={6}>
                              <TextField
                                defaultValue={parent.nom}
                                error={data?.error && data?.error?.nom}
                                helperText={data?.error?.nom}
                                fullWidth
                                label="Nom"
                                name="nom"
                                required
                              />
                            </Grid>
                            <Grid xs={12} md={6}>
                              <TextField
                                defaultValue={parent.email}
                                error={data?.error && data?.error?.email}
                                helperText={data?.error?.email}
                                fullWidth
                                label="Adresse email"
                                name="email"
                                required
                              />
                            </Grid>
                            <Grid xs={12} md={6}>
                              <TextField
                                defaultValue={parent.cni}
                                error={data?.error && data?.error?.cni}
                                helperText={data?.error?.cni}
                                fullWidth
                                label="Cni"
                                name="cni"
                                type="text"
                              />
                            </Grid>
                            <Grid xs={12} md={6}>
                              <TextField
                                defaultValue={parent.tph}
                                error={data?.error && data?.error?.tph}
                                helperText={data?.error?.tph}
                                fullWidth
                                label="Tph"
                                name="tph"
                                required
                              />
                            </Grid>
                          </Grid>
                        </Box>
                      </CardContent>
                      <Divider />
                      <CardActions sx={{ justifyContent: "flex-end" }}>
                        <Button variant="contained" type="submit">
                          Save details
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

export default Update;
