import { useContext } from "react";
import { AuthContext } from "../../contexts/auth-context";
import { useLocation, useNavigate } from "react-router-dom";

const Login = () => {
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <div>
      Login
      <button
        onClick={() => {
          login();
          navigate(location.state?.from ?? "/parents/1", {
            replace: true,
          });
        }}
      >
        login
      </button>
    </div>
  );
};

export default Login;
