import * as React from "react";
import "./footer.scss";

export const Footer =({}) =>{
    return <footer className="flex">
        <div className = "left">
            <p>email: <a href = "mailto: vestana@mockmail.com">vestana@mockmail.com</a></p>
            <p>Telefonní číslo:<a href = "tel: +420 400 000 000">+420 400 000 000</a></p>
      </div>
      <div className = "right">
            <p>Karlovo náměstí 13</p>
            <p>Praha 2 – Nové Město</p>
            <p>121 35</p>
      </div>
  </footer>;
};

export default Footer;