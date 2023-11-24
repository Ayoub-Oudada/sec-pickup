import { useState } from "react";
import "./App.css";

const App = () => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [firstRender, setFirstRender] = useState(true);

  const getData = async () => {
    let response;
    let dataJson;
    try {
      response = await fetch("http://localhost:8080/api/users");
      dataJson = await response.json();
    } catch ({ name, message }) {
      setError(message);
    } finally {
      setData(dataJson);
      setFirstRender(false);
    }
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "15px",
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
          <div style={{ display: "block", color: "green", fontSize: "20px" }}>
            your app is ok! and this is the result from the backend :
          </div>
          <div>{JSON.stringify(data)}</div>
        </>
      )}

      {!data && !firstRender && (
        <div style={{ display: "block", color: "red" }}>
          your app isn't ok! it has failed for this problem : {error}
        </div>
      )}
    </div>
  );
};

export default App;
