import * as React from "react";
import { Formik, Field, Form, ErrorMessage, validateYupSchema } from "formik";
import * as Yup from "yup";
import {login} from "./service/authService";
import "bootstrap/dist/css/bootstrap.min.css";


export const Signin=()=>{

   const state = {
    username: "",
    password: "",
    loading: false,
    message: ""
    };
    const initialValues = {
        username: "",
        password: "",
      };

    const validationSchema = ()=> {
    return Yup.object().shape({
        username: Yup.string().required("This field is required!"),
        password: Yup.string().required("This field is required!"),
    });
    }

    const submit = (formValue: { username: string; password: string }) => {
        const { username, password } = formValue;
        login(username, password).then(
            (response)=>{
                console.log(response);
            }

          );};

    return <>
        <div className="login-wrapper">
      <h1>Please Log In</h1>
      <Formik 
      initialValues = {initialValues}
      validationSchema = {validationSchema}
      onSubmit= {submit}>
      <Form>
              <div className="form-group">
                <label htmlFor="username">Username</label>
                <Field name="username" type="text" className="form-control" />
                <ErrorMessage
                  name="username"
                  component="div"
                  className="alert alert-danger"
                />
              </div>
              <div className="form-group">
                <label htmlFor="password">Password</label>
                <Field name="password" type="password" className="form-control" />
                <ErrorMessage
                  name="password"
                  component="div"
                  className="alert alert-danger"
                />
              </div>
              <div className="form-group">
                <button type="submit" className="btn btn-primary btn-block">
                  <span>Login</span>
                </button>
              </div>
            </Form>
      </Formik>
    </div>
    </>;
}

export default Signin;