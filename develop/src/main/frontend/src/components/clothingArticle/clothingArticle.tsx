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
  
    const [Article, fetchArticle] = React.useState<article>({id : 0,
      name : "",
      description : "",
      size : 0,
      price : 0});
    let {ArticleId} = useParams();

    const getData = async () => {
      console.log("Fetching clothing article data")
      await fetch("/item/"+ ArticleId, {method: "Get"})
        .then((result)=> result.json())
        .then((result)=>{
          console.log(result);
          fetchArticle(result);
        })
        .catch((error)=>
        console.error("Error :", error ))
    };

    React.useEffect(()=>{
      getData();
    }, [ArticleId])
    
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