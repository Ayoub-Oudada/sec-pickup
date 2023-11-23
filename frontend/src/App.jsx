import { useState } from "react";
import "./App.css";

const App = () => {
  const [data, setData] = useState();
  const [firstRender, setFirstRender] = useState(true);

  const getData = async () => {
    const response = await fetch("http://localhost:8080/api/users");
    const data = await response.json();

    setData(data);
    setFirstRender(false);
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "10px",
        alignItems: "center",
      }}
    >
      <button
        style={{
          width: "fit-content",
        }}
        onClick={getData}
      >
        test my app
      </button>
      {firstRender && (
        <div style={{ display: "block" }}>
          click on the button to test your app connection with your backend
        </div>
      )}
      {data && (
        <>
          <div style={{ display: "block", color: "green" }}>
            your app is ok! and this is the result from the backend :
          </div>
          <div>{JSON.stringify(data)}</div>
        </>
      )}
      {!data && !firstRender && (
        <div style={{ display: "block", color: "red" }}>your app isn't ok!</div>
      )}
    </div>
  );
};

export default App;
