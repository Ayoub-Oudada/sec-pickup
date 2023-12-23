import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../../hooks/use-auth";

// eslint-disable-next-line react/prop-types
function RequireAuth({ children }) {
  const { token, loading } = useAuth();
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    if (!loading && !token)
      navigate("/auth/login", { state: { from: location.pathname } });
  }, [token, loading, navigate, location]);

  if (loading) return <div>loading...</div>;

  if (!loading && token) return <>{children}</>;
}

export default RequireAuth;
