import { useNavigate } from "react-router-dom";
import { useAuth } from "../../hooks/use-auth";
import { Box, Button, Stack, TextField, Typography } from "@mui/material";
import { useForm } from "react-hook-form";

const Login = () => {
  const { login, errors } = useAuth();
  const navigate = useNavigate();
  const { register, handleSubmit } = useForm({
    defaultValues: {
      email: "",
      password: "",
    },
  });
  const onSubmit = async (data) => {
    if (await login(data)) navigate("/parents");
  };

  return (
    <>
      <div>
        <title>Login | Devias Kit</title>
      </div>
      <Box
        sx={{
          backgroundColor: "background.paper",
          flex: "1 1 auto",
          alignItems: "center",
          display: "flex",
          justifyContent: "center",
        }}
      >
        <Box
          sx={{
            maxWidth: 550,
            px: 3,
            py: "100px",
            width: "100%",
          }}
        >
          <div>
            <Stack spacing={1} sx={{ mb: 3 }}>
              <Typography variant="h4">Login</Typography>
            </Stack>
            {errors && (
              <Stack spacing={1} sx={{ mb: 3 }}>
                <Typography color="error">{errors}</Typography>
              </Stack>
            )}
            <form noValidate onSubmit={handleSubmit(onSubmit)}>
              <Stack spacing={3}>
                <TextField
                  fullWidth
                  label="Email Address"
                  type="email"
                  {...register("email")}
                />
                <TextField
                  fullWidth
                  label="Password"
                  type="password"
                  {...register("password")}
                />
              </Stack>
              <Button
                fullWidth
                size="large"
                sx={{ mt: 3 }}
                type="submit"
                variant="contained"
              >
                Continue
              </Button>
            </form>
          </div>
        </Box>
      </Box>
    </>
  );
};

export default Login;
