import { useMemo } from "react";
import { useEffect } from "react";
import { useState } from "react";
import { createContext } from "react";

export const AuthContext = createContext(null);

// eslint-disable-next-line react/prop-types
export const AuthProvider = ({ children }) => {
  const [{ user, token, loading }, setUserData] = useState({
    user: null,
    loading: true,
    token: null,
  });

  const getTokenFromLocalStorage = () => {
    setUserData((prevData) => ({
      ...prevData,
      user: null,
      loading: false,
      token: null,
    }));
  };

  const login = () => {
    setUserData((prevData) => ({
      ...prevData,
      user: "user",
      loading: false,
      token: "token",
    }));
  };

  const logout = () => {
    setUserData((prevData) => ({
      ...prevData,
      user: null,
      loading: false,
      token: null,
    }));
  };

  const value = useMemo(() => {
    return { user, token, loading, login, logout };
  }, [user, token, loading]);

  useEffect(() => {
    if (!token) getTokenFromLocalStorage();
  }, [token, user]);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
