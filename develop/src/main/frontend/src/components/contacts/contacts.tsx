import * as React from "react";
import {Form as FormBoot} from "react-bootstrap";
import * as Yup from "yup";
import { Formik, ErrorMessage, Form, Field } from "formik";

export const Contacts =({}) =>{

      
      const today = new Date();

      const initVal = {
            email:"",
            date:"",
            tel:"",
            name:""
      }

      type reservation = {
            email: string,
            date: string,
            tel: string,
            name: string
      }

      const handleSubmit = async (formData : reservation) =>{
            const uri = "http://localhost:8080/reservation/";
            try{
                  const submit = await fetch(uri + "new", {
                        method: "POST",
                        headers: {
                            'Content-Type': 'application/json'
                            },
                        body: JSON.stringify({
                            customer: {
                                    name: formData.name,
                                    email: formData.email,
                                    phone: formData.tel,
                              // in future optional
                                    "deliveryAddress": "mockAdresa",
                            },
                            startTime: formData.date,
                            "endTime": formData.date,
                            "bookedItems": []
                         })
                    });
                  console.log(submit);
            }
            catch(er){
                  console.error(er);
            }
      }

      const validationSchema = ()=> {
            return Yup.object().shape({
                email: Yup.string()
                .required("Povinné pole")
                .matches(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/, "Nevalidní email"),
                tel: Yup.string()
                .required("Povinné pole")
                .matches(/^[0-9]*$/, "Telefoní čísla obsahují jen číslice")
                .test('len', 'Délka číslo musí mít 9 číslic',  val  =>  val.length === 9),
                name: Yup.string()
                .required("Povinné pole")
                .min(3, "Jméno musí obsahovat alespoň 3 znaky"),
                date: Yup.date()
                .required("Povinné pole")
                .min(today, "Datum musí být v budouctnosti!")
            });
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
            <Formik
            validateOnMount
            initialValues={initVal}
            onSubmit = {handleSubmit}
            validationSchema = {validationSchema}>
                  <Form>
                        <div className="form-group">
                              <label>Datum rezervace:</label>
                              <Field name="date" type="datetime-local" className="form-control" />
                        </div>
                        <ErrorMessage
                        name="date"
                        component="div"
                        className="text-danger"
                        />
                        <div className="form-group">
                              <label>E-mail:</label>
                              <Field name="email" type="email" placeholder="email@email.cz" className="form-control" />
                        </div>
                        <ErrorMessage
                        name="email"
                        component="div"
                        className="text-danger"
                        />
                        <div className="form-group">
                              <label>Příjmení:</label>
                              <Field name="name" type="string" placeholder="Petr Omáčka" className="form-control" />
                        </div>
                        <ErrorMessage
                        name="name"
                        component="div"
                        className="text-danger"
                        />
                        <div className="form-group">
                              <label>Telefon:</label>
                              <Field name="tel" type="tel" placeholder="400 000 000" className="form-control"/>
                        </div>
                        <ErrorMessage
                        name="tel"
                        component="div"
                        className="text-danger"
                        />
                        <div className="form-group">
                        <button type="submit" className="btn btn-primary btn-block">
                              <span>Rezervovat</span>
                        </button>
                        </div>
                  </Form>
            </Formik>
      </div>
      </article>);
};


export default Contacts;