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
import { Form, useActionData, useLoaderData } from "react-router-dom";

const Create = () => {
  const trajets = useLoaderData();
  const data = {};
  console.log(data);

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
              <Typography variant="h4">Rue</Typography>
            </div>
            <div>
              <Grid container spacing={4}>
                <Grid xs={16} md={12} lg={12}>
                  <Form autoComplete="off" noValidate method="POST">
                    <Card>
                      <CardHeader
                        subheader="Les informations peuvent être modifiée."
                        title="Les informations de la rue"
                      />
                      <CardContent sx={{ pt: 0 }}>
                        <Box sx={{ m: -1.5 }}>
                          <Grid container spacing={3}>
                            <Grid xs={12} md={6}>
                              <TextField
                                error={data?.error && data?.error?.lib_rue}
                                helperText={data?.error?.lib_rue}
                                fullWidth
                                label="libelle rue"
                                name="lib_rue"
                                required
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
                                // defaultValue={rue.trajet}
                                error={data?.error && data?.error?.trajet}
                                helperText={data?.error?.trajet}
                                SelectProps={{ native: true }}
                                required
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
  )
}

export default Create