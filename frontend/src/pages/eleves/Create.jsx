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
  const reponse = useLoaderData();
  const {parents, rues} = reponse;

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
                            label="Prenom"
                            name="prenom"
                            required
                            />
                        </Grid>
                        <Grid xs={12} md={6}>
                            <TextField
                            error={data?.error && data?.error?.nom}
                            helperText={data?.error?.nom}
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

export default Create;
