import * as React from "react";
import { Outlet } from "react-router";
import "./clientBody.scss";

export const ClientBody =({}) =>{
  return (
    <div className = "background" style={{ backgroundImage: `url(/background1.jpg)` }}>
      <div className="text-container">
          <Outlet />
      </div>
    </div>
  );
};