import * as React from "react";
import { Outlet } from "react-router";
import "./main.scss";

export const Main =({}) =>{
  const image = "background1.jpg";
  return (
    <main className = "background" style={{ backgroundImage: "url(/" + image +")",
      backgroundRepeat: "no-repeat",
      backgroundSize: 'cover',}}>
      <div className="container">
          <Outlet />
      </div>
    </main>
  );
};

export default Main;