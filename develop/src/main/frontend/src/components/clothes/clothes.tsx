import * as React from "react";
import { Link, useNavigate } from "react-router-dom";
import {Container, Card, Button, CardGroup} from 'react-bootstrap';
import "./clothes.scss";


interface clothingArticle{
  id : number,
  name : string,
  description : string,
  size : number,
  price : number
}


export const Clothes =({}) =>{
  const [clothes, setClothes] = React.useState([]);
  const navigate = useNavigate();
  React.useEffect(()=>{
    const fetchClothes =  async () => {
      try{
        const url = "http://localhost:8080";
        const response = await fetch(url + "/item", {method: "Get"});
        console.log(response);
        const data = await response.json();
        console.log(data);
        setClothes(data);
      }
      catch(er){
        console.error(er);
      }
    }  
    fetchClothes();
  }, [])
    return (<>
      <h1 className="font-weight-light">Clothes</h1>
      <CardGroup>
      {clothes.map((item : clothingArticle)=>{
          return <Card border="primary" style={{ maxWidth: '22rem'}} key={item.id}>
              <Card.Img variant="top" src="holder.js/100px180" />
              <Card.Body>
                <Card.Title>{item.name}</Card.Title>
                <Button variant="primary" onClick={()=>navigate(item.id.toString())}>Go somewhere</Button>
              </Card.Body>
            </Card>;
        })}
      </CardGroup>
      </>);
};

export default Clothes;