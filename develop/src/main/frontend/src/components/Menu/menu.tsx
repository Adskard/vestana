import * as React from "react";
import { useState } from "react";
import {Navbar, Container, Nav} from 'react-bootstrap';
import {NavLink } from "react-router-dom";
import "./menu.scss";

export const Menu =({}) =>{
  const [expanded, setExpanded] = React.useState(false);
  const [token, setToken] = useState();
    return <header>
    <Navbar collapseOnSelect sticky="top" expand="sm" expanded = {expanded} variant="dark" bg="blue">
      <Container>
        
        <Navbar.Toggle onClick={() => setExpanded(expanded ? false : true)} label="Toggle menu" aria-controls="responsive-navbar-nav"/>
        <Nav>
          <Nav.Link as={NavLink} to="/">Vestana</Nav.Link>
        </Nav>
        
        <Navbar.Collapse id="responsive-navbar-nav" role="">
          <Nav className="left-side">
            
            <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/About">O nás</Nav.Link>
            <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/Clothes">Oděvy</Nav.Link>
            <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/Contacts">Kontatky</Nav.Link>
          </Nav>
          <Nav className="d-flex justify-content-end">
            <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/Signin">Přihlásit se</Nav.Link>
            <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/Registration">Registrovat</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  </header>;
};

export default Menu;