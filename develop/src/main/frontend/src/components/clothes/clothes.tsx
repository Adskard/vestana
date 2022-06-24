import * as React from "react";
import { Link, useNavigate } from "react-router-dom";
import {Row, Card, Button, CardGroup, Col} from 'react-bootstrap';
import "./clothes.scss";


interface clothingArticle{
  id : number,
  name : string,
  description : string,
  size : number,
  deleted: boolean,
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
    return (<article>
      <h1 className="font-weight-light">Clothes</h1>
      <CardGroup>
        <Row className="container" xs={1} md={2} lg={3} xl={4}  >
        {clothes.filter((item:clothingArticle)=>{return !item.deleted})
        .map((item : clothingArticle)=>{
            return (<Col key={item.id}>
              <Card border="primary" >
                <Card.Img variant="top" src="https://via.placeholder.com/150" alt="dress-image"/>
                <Card.Body>
                  <Card.Title>{item.name}</Card.Title>
                  <Card.Text>
                    {item.description}
                  </Card.Text>
                  <Button variant="primary" onClick={()=>navigate(item.id.toString())}>Bližší informace</Button>
                </Card.Body>
              </Card>
              </Col>);
          })}
        </Row>

      </CardGroup>
      </article>);
};

export default Clothes;