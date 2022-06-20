import * as React from "react";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";
import { useNavigate} from "react-router-dom";
import {register} from "../service/authService";
import { BsFillEyeFill, BsFillEyeSlashFill } from "react-icons/bs";

export const Registration=()=>{
    let navigate = useNavigate();
    const [message, setMessage] = React.useState("");
    const [loading, setLoading] = React.useState(false);
    const [showPassword, setShowPassword] = React.useState(false);
    
    const initialValues = {
        username: "",
        password: "",
      };

    const validationSchema = ()=> {
    return Yup.object().shape({
        username: Yup.string()
        .required("Povinné pole")
        .min(3, "Jméno musí obsahovat alespoň 3 znaky"),
        password: Yup.string()
        .required("Povinné pole")
        .min(8, "Heslo musí být alespoň 8 znaků dlouhé")
        .matches(/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$/, "Heslo musí obsahovat číslo, malé písmeno a velké písmeno")
    });
    }

    const handleSubmit = async (formValue: { username: string; password: string }) => {
        const { username, password } = formValue;
        setLoading(true);
        const [response, error] = await register(username, password);
        if(error){
            setMessage("Něco se pokazilo zkuste to znovu později");
        }
        if(response.ok){
            navigate("/");
        }
        else if(response){
            setMessage("Uživatelké jméno už existuje")
        }
      };
      
    return <>
        <div className="login-wrapper">
      <h1>Registrace</h1>
      <div> {message}</div>
      <Formik 
      validateOnMount
      initialErrors={{username: "Povinné pole",
      password: "Povinné pole"
        }}
      initialValues = {initialValues}
      validationSchema = {validationSchema}
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
            <Field name="password" type={showPassword ? "text" : "password"} className="form-control" />
            <button type="button" className="btn btn-outline-primary" onClick={()=>{showPassword ? setShowPassword(false) : setShowPassword(true)}}>
                 { showPassword ? <BsFillEyeFill/> : <BsFillEyeSlashFill/> }</button>
            <ErrorMessage
              name="password"
              component="div"
              className="text-danger"
            />
          </div>
          <div className="form-group">
            <button type="submit"  className="btn btn-primary btn-block">
              <span>Register</span>
            </button>
          </div>
        </Form>
      </Formik>
    </div>
    </>;
}
export default Registration;