import * as React from "react";
import { useParams } from "react-router";
import "./clothingArticle.scss";

export interface article{
  id : number,
  name : string,
  description : string,
  size : number,
  price : number
}

export const ClothingArticle =({}) =>{
  
    const [Article, setArticle] = React.useState<article>({id : 0,
      name : "",
      description : "",
      size : 0,
      price : 0});
    let {ArticleId} = useParams();


    React.useEffect(()=>{
      const fetchArticle =  async () => {
        try{
          const url = "http://localhost:8080";
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
      fetchArticle();
    }, [])
    
    return (
        <div className="about">
          <h1 className="font-weight-light">{Article.name}</h1>
          <p>
            {Article.description}
          </p>
          <p>
            Velikost: {Article.size}
          </p>
          <p>
            Cena: {Article.price} Kč
          </p>
        </div>
      );
};

export default ClothingArticle;