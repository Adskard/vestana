import * as React from "react";
import Form from "react-bootstrap/Form";
import { FormikConsumer, withFormik } from "formik";

export const Contacts =({}) =>{

      
      const today = new Date();

      const initVal = {

      }

      const handleSubmit = async () =>{

      }



      return (
            <article>
            <h1 className="font-weight-light">Kontakty</h1>
            <div className= "flex-parent">
                  <div className="flex-child no margin">
                        <p>email: <a href = "mailto: vestana@mockmail.com">vestana@mockmail.com</a></p>
                        <p>Telefonní číslo:<a href = "tel: +420 400 000 000">+420 400 000 000</a></p>
                        <p>Karlovo náměstí 13</p>
                        <p>Praha 2 – Nové Město</p>
                        <p>121 35</p>
                  </div>
                  <div className="flex-child">
                  <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2560.550376440536!2d14.416557315717878!3d50.07598157942543!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x470b94f40aa1ac15%3A0x618dc17441af9a7f!2zS2FybG92byBuw6FtLiAxMywgMTIwIDAwIE5vdsOpIE3Em3N0bw!5e0!3m2!1sen!2scz!4v1655994207435!5m2!1sen!2scz"
                  style={{width:"600",
                  height:"450",
                  }}  loading="lazy" ></iframe>     
                  </div>
            </div>

      <h2>Rezervujte se!</h2>
      <p>Zde si můžete rezervovat čas na prodejně k vyzkoušení oděvů.</p>
      <div className = "form-wrapper">
            <Form onSubmit={handleSubmit}>
                  <Form.Group className="form-group">
                        <label>Datum rezervace:</label>
                        <Form.Control type="date" name='dateOfReservation'/>
                  </Form.Group>
                  <Form.Group className="form-group">
                        <label>E-mail:</label>
                        <Form.Control type="email" placeholder="email@email.cz" />
                  </Form.Group>
                  <Form.Group className="form-group">
                        <label>Jméno:</label>
                        <Form.Control type="username" placeholder="Petr Omáčka" />
                  </Form.Group>
                  <Form.Group className="form-group">
                        <label>Telefon:</label>
                        <Form.Control type="username" placeholder="" />
                  </Form.Group>
                  <Form.Group className="form-group">
                  <button type="submit" className="btn btn-primary btn-block">
                        <span>Rezervovat</span>
                  </button>
                  </Form.Group>
            </Form>
      </div>
      </article>);
};


export default Contacts;