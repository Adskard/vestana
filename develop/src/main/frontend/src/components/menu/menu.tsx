import * as React from "react";
import {Navbar, Container, Nav} from 'react-bootstrap';
import {NavLink } from "react-router-dom";
import "./menu.scss";
import { logout } from "../../service/authService";

type MenuProps = {
  authenticated : string
  setAuthenticated : Function
}

export const Menu =({authenticated, setAuthenticated} : MenuProps) =>{
  const [expanded, setExpanded] = React.useState(false);

  return <header>
    <Navbar collapseOnSelect sticky="top" expand="sm" expanded = {expanded} variant="dark">
      <Container className = "container-fluid">
        <Navbar.Toggle onClick={() => setExpanded(expanded ? false : true)} label="Toggle menu" aria-controls="responsive-navbar-nav"/>
        <Nav>
          <Nav.Link as={NavLink} to="/">Vestana</Nav.Link>
        </Nav>
        <Navbar.Collapse id="responsive-navbar-nav" role="">
          <Nav className="d-flex">
            <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/About">O nás</Nav.Link>
            <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/Clothes">Oděvy</Nav.Link>
            <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/Contacts">Kontatky</Nav.Link>
            {authenticated ? <>
            <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/Loans">Výpůjčky</Nav.Link>
            <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/Reservations">Rezervace</Nav.Link>
            </>: <></>}
          </Nav>
          {authenticated ? <div className ="flex-parent right">
            <Container className ="flex-child"><p>{authenticated}</p></Container>
            <Container className ="flex-child"><button className ="btn btn-primary btn-block" onClick={()=>{
              setAuthenticated("");
              logout();
            }}>Odhlásit se</button></Container>
            </div> 
            :
            <div  className="justify-content-end">
            <Nav>
              <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/Signin">Přihlásit se</Nav.Link>
              <Nav.Link as={NavLink} onClick={() => setExpanded(false)} to="/Registration">Registrovat</Nav.Link>
            </Nav>
          </div>}
        </Navbar.Collapse>
      </Container>
    </Navbar>
  </header>;
};

export default Menu;