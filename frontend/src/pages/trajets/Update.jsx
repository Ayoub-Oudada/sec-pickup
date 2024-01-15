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
  const trajet = useLoaderData();
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
              <Typography variant="h4"></Typography>
            </div>
            <div>
              <Grid container spacing={4}>
                <Grid xs={16} md={12} lg={12}>
                  <Form autoComplete="off" noValidate method="POST">
                    <Card>
                      <CardHeader
                        subheader="La information peut être modifiée."
                        title="Trajet informations"
                      />
                      <CardContent sx={{ pt: 0 }}>
                        <Box sx={{ m: -1.5 }}>
                          <Grid container spacing={3}>
                            <Grid xs={12} md={6}>
                              <TextField
                                defaultValue={trajet.libTrajet}
                                error={data?.error && data?.error?.libTrajet}
                                helperText={data?.error?.libTrajet}
                                fullWidth
                                label="Libelle trajet"
                                name="libTrajet"
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
