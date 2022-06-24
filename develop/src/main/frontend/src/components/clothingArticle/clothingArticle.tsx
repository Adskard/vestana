import * as React from "react";
import { useParams, useNavigate } from "react-router";
import "./clothingArticle.scss";
import {Formik, Form, Field, ErrorMessage} from "formik";
import authHeader from "src/service/authHeader"
import * as Yup from "yup";

interface article{
  id : number,
  name : string,
  description : string,
  size : number,
  price : number
}

type ArticleProps = {
  authenticated: string
}


export const ClothingArticle =({authenticated} : ArticleProps) =>{
    const url = "http://localhost:8080";
    const [Article, setArticle] = React.useState<article>({id : 0,
      name : "",
      description : "",
      size : 0,
      price : 0});
    let {ArticleId} = useParams();
    const [edit, setEdit] = React.useState(false);
    let navigate = useNavigate();
    const auth = authHeader();
    const handleDelete = async () =>{
      try{
        const headers = new Headers();
        headers.append("Authorization", authHeader());
        const postDelete = await fetch(url + "/item/"+ ArticleId +"/delete", {method: "DELETE",
        headers: headers}); 
        console.log(postDelete.status)
      }
      catch(er){
        console.error(er)
      }
      navigate("/Clothes")
    }

    const handleEdit = async (updated: article) =>{
      try{
        const headers = new Headers();
        headers.append("Authorization", authHeader());
        headers.append('Content-Type', 'application/json');
        const postDelete = await fetch(url + "/item/update", {method: "PUT",
        headers: headers,
        body: JSON.stringify(updated)}); 
        console.log(postDelete.status)
        fetchArticle();
      }
      catch(er){
        console.error(er)
      }
      setEdit(false);
    }

    const validationSchema = ()=> {
      return Yup.object().shape({
          name: Yup.string()
          .min(3, "Jméno musí obsahovat alespoň 3 znaky")
          .nullable(false),
          price: Yup.number()
          .typeError("velikost musí být číslo")
          .positive("Musí být kladné")
          .max(200000000, 'Moc velké číslo!'),
          description: Yup.string(),
          size: Yup.number()
          .typeError("velikost musí být číslo")
          .max(200000000, 'Moc velké číslo!')
          .positive("Musí být kladné")
      });
      }

      const fetchArticle =  async () => {
        try{
          const response = await fetch(url + "/item/"+ ArticleId, {method: "Get"});
          console.log(response);
          const data = await response.json();
          console.log(data);
          setArticle(data);
        }
        catch(er){
          console.error(er);
        }
      }  

    React.useEffect(()=>{
      fetchArticle();
    }, [])
    
    return (<>
        <button type="button" className="btn btn-primary btn-block" 
        onClick={()=>{navigate("/Clothes")} }>Zpět</button>
        <article>
          <h1 className="font-weight-light">{Article.name}</h1>
          <div className="flex-parent">
          <div className= "flex-child left">
              <img src="https://via.placeholder.com/500" alt="dress-image" />
            </div>
            <div className="flex-child right">
              {edit ? <>
              <Formik
              validateOnBlur
              initialValues = {Article}
              onSubmit={handleEdit}
              validationSchema={validationSchema}>
                <Form>
                  <div className="form-group">
                    <label>Změnit jméno:</label>
                    <Field name="name" type="text" className="form-control" />
                    <ErrorMessage
                      name="name"
                      component="div"
                      className="text-danger"
                    />
                  </div>
                  <div className="form-group">
                    <label>Popis produktu:</label>
                    <Field name="description" type="text" className="form-control" />
                    <ErrorMessage
                      name="description"
                      component="div"
                      className="text-danger"
                    />
                  </div>
                  <div className="form-group">
                    <label >Velikost:</label>
                    <Field name="size" type="text"  className="form-control"/>
                    <ErrorMessage
                      name="size"
                      component="div"
                      className="text-danger"
                    />
                  </div>
                  <div className="form-group">
                    <label >Cena na den:</label>
                    <Field name="price" type="number"  className="form-control"/>
                    <ErrorMessage
                      name="price"
                      component="div"
                      className="text-danger"
                    />
                  </div>
                  <div className="form-group">
                    <button type="submit"  className="btn btn-primary btn-block">
                      <span>Uložit</span>
                    </button>
                  </div>
                </Form>
              </Formik>
                </>:<>
              <h3 className="">Popis produktu:</h3>
              <p>
                {Article.description}
                + Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
              </p>
              <h3 className="">Velikost:</h3>
              <p>{Article.size}</p>
              <h3 className="">Cena na den: </h3>
              <p>{Article.price} Kč</p>
              {authenticated ? 
              <div className="flex-parent">
                <div className="flex-child">
                  <button onClick={()=>setEdit(true)} className="btn btn-primary btn-block">
                  Editovat
                  </button>
                </div>
                <div className="flex-child">
                  <button onClick={handleDelete} className="btn btn-primary btn-block">
                    Odstranit
                  </button>
                </div>
              </div>:<></>
             }
              
              </>
              }
            </div>
          </div>
        </article>
        </>);
};

export default ClothingArticle;