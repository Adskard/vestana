import * as React from "react";
import { Formik, Field, Form, ErrorMessage } from "formik";
import { useNavigate} from "react-router-dom";
import { login } from "../service/authService";

type SigninProps ={
  setAuthenticated: Function
}

export const Signin=({setAuthenticated} : SigninProps)=>{

    let navigate = useNavigate();
    const [message, setMessage] = React.useState("");
    
    const initialValues = {
        username: "",
        password: "",
      };


    const handleSubmit = async (formValue: { username: string; password: string }) => {
        const { username, password } = formValue;
        const [response, error] = await login(username, password);
        if(error){
          setMessage("Něco se pokazilo zkuste to znovu později");
        }
        if(response.ok){
          setAuthenticated(username);
          navigate("/");
        }
        else{
          setMessage("Nesprávné jméno nebo heslo");
        }
      };
    return <>
        <div className="form-wrapper">
      <h1>Přihlašte se</h1>
      <div className= "text-danger"> {message}</div>
      <Formik 
      initialValues = {initialValues}
      onSubmit= {handleSubmit}>
        <Form>
          <div className="form-group">
            <label htmlFor="username">Uživatelské jméno:</label>
            <Field name="username" type="text" className="form-control" />
            <ErrorMessage
              name="username"
              component="div"
              className="text-danger"
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Heslo:</label>
            <Field name="password" type="password" className="form-control" />
            <ErrorMessage
              name="password"
              component="div"
              className="text-danger"
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