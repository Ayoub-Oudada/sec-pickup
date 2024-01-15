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
import { Form, redirect, useActionData, useLoaderData } from "react-router-dom";

export async function action({ request, params }) {
  const formData = await request.formData();
  const updates = Object.fromEntries(formData);

  const requestOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(updates),
  };

  await fetch(import.meta.env.VITE_BACKEND_BASE_URL+ "/api/eleves/updates.id", requestOptions);

  return redirect(`/eleves/`);
}

export const Update = (request, params) => {

    const reponse = useLoaderData();
  const {eleve, parents, rues} = reponse;

  const data = useActionData();


  return (
    <>
      <div>
        <title>Eleve | Update</title>
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
              <Typography variant="h4">Eleve</Typography>
            </div>
            <div>
              <Grid container spacing={4}>
            <Grid xs={16} md={12} lg={12}>
                <Form autoComplete="off" noValidate method="POST">
                <Card>
                    <CardHeader
                    subheader="La information peut être modifiée."
                    title="Eleve informations"
                    />
                    <CardContent sx={{ pt: 0 }}>
                    <Box sx={{ m: -1.5 }}>
                        <Grid container spacing={3}>
                        <Grid xs={12} md={6}>
                            <TextField
                            error={data?.error && data?.error?.prenom}
                            helperText={data?.error?.prenom}
                            fullWidth
                            defaultValue={eleve.prenom}
                            label="Prenom"
                            name="prenom"
                            required
                            />
                        </Grid>
                        <Grid xs={12} md={6}>
                            <TextField
                            error={data?.error && data?.error?.nom}
                            helperText={data?.error?.nom}
                            defaultValue={eleve.nom}
                            fullWidth
                            label="Nom"
                            name="nom"
                            required
                            />
                        </Grid>
                        {/* <Grid xs={12} md={6}>
                            <TextField
                            error={data?.error && data?.error?.dateDeNaissance}
                            helperText={data?.error?.dateDeNaissance}
                            fullWidth
                            label="Date de naissance"
                            name="dateDeNaissance"
                            required
                            />
                        </Grid> */}
                        <Grid xs={12} md={6}>
                            <TextField
                            error={data?.error && data?.error?.cne}
                            helperText={data?.error?.cne}
                            defaultValue={eleve.cne}
                            fullWidth
                            label="Cne"
                            name="cne"
                            type="text"
                            />
                        </Grid>
                        <Grid xs={12} md={6}>
                            <TextField
                            error={data?.error && data?.error?.niveau}
                            helperText={data?.error?.niveau}
                            defaultValue={eleve.niveau}
                            fullWidth
                            label="Niveau"
                            name="niveau"
                            required
                            />
                        </Grid>
                    
                        <Grid
                            xs={12}
                            md={6}
                        >
                            <TextField
                            fullWidth
                            label="Sélectionner le parent..."
                            name="parentId"
                            required
                            select
                            error={data?.error && data?.error?.parent}
                            helperText={data?.error?.parent}
                            SelectProps={{ native: true }}
                            >

                            
                            {parents.map((option) => (

                                <option key={option.id} value={option.id}>
                                    {option.prenom} {option.nom}
                                </option>
                                
                            ))}
                                <option key="null" value="null">
                                Aucun parent
                                </option>
                            </TextField>
                        </Grid>
                        <Grid
                            xs={12}
                            md={6}
                        >
                            <TextField
                            fullWidth
                            label="Sélectionner la rue..."
                            name="rueId"
                            required
                            select
                            error={data?.error && data?.error?.rue}
                            helperText={data?.error?.rue}
                            SelectProps={{ native: true }}
                            >

                            
                            {rues.map((option) => (

                                <option key={option.id} value={option.id}>
                                    {option.lib_rue}
                                </option>
                                
                            ))}
                                <option key="null" value="null">
                                Aucune rue
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
  );
};
