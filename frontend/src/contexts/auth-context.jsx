import axios, { HttpStatusCode } from "axios";
import { useCallback, useMemo } from "react";
import { useEffect } from "react";
import { useState } from "react";
import { createContext } from "react";

export const AuthContext = createContext(null);

// eslint-disable-next-line react/prop-types
export const AuthProvider = ({ children }) => {
  const [{ user, token, loading, errors }, setUserData] = useState({
    user: null,
    loading: true,
    token: null,
    errors: null,
  });

  const storeTokenInLocalStorage = useCallback((token) => {
    localStorage.setItem("token", token);
  }, []);

  const getTokenFromLocalStorage = () => {
    setUserData((prevData) => ({
      ...prevData,
      token: localStorage.getItem("token"),
      user: localStorage.getItem("user"),
    }));
  };

  const login = useCallback(
    async ({ email: username, password }) => {
      try {
        const response = await axios.post(
          import.meta.env.VITE_BACKEND_BASE_URL + "/api/auth/login",
          {
            username,
            password,
            type: "ADMIN",
          }
        );

        await setUserData((prevData) => ({
          ...prevData,
          user: "user",
          loading: false,
          token: response?.data?.token,
          errors: null,
        }));

        storeTokenInLocalStorage(response.data.token);
        return true;
      } catch (e) {
        if (e.response.status == HttpStatusCode.Forbidden) {
          setUserData((prevData) => ({
            ...prevData,
            user: null,
            loading: false,
            token: null,
            errors: e.response.data.errors,
          }));
        }
        return false;
      }
    },
    [storeTokenInLocalStorage]
  );

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    setUserData((prevData) => ({
      ...prevData,
      user: null,
      loading: false,
      token: null,
      errors: null,
    }));
  };

  const value = useMemo(() => {
    return { user, token, loading, login, logout, errors };
  }, [user, token, loading, login, errors]);

  useEffect(() => {
    if (!token) getTokenFromLocalStorage();
  }, [token, user]);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
