import * as React from "react"
import { Accordion, Card, Col, Row } from "react-bootstrap";
import authHeader from "src/service/authHeader"

interface customer {
    name: string,
    email: string,
    phone: number,
    id: number,
    deliveryAddress: string
}

interface reservation{
    id: number,
    startTime: Date,
    endTime: Date,
    customer : customer;
    eventId: number;
    bookedItems: [];
  }

const now = new Date();

export const Reservations = () =>{
    const [reservations, setReservations] = React.useState([]);
    const url = "http://localhost:8080/reservation";

    const getReservations = async ()=>{
        try{
            const headers = new Headers();
            headers.append("Authorization", authHeader());
            const fetchReservations = await fetch(url, {method: "GET",
            headers: headers}); 
            const data = await fetchReservations.json();
            console.log(fetchReservations)
            setReservations(data)
        }
        catch(er){
            console.error(er)
        }
    }

    const handleDelete = async (item: number) =>{
        try{
            const headers = new Headers();
            headers.append("Authorization", authHeader());
            headers.append('Content-Type', 'application/json');
            const fetchReservations = await fetch(url + "/"+ item.toString(), {method: "DELETE",
            headers: headers,
            body: JSON.stringify({
                id: item
                })});
            console.log(fetchReservations)
            getReservations();
        }
        catch(er){
            console.error(er)
        }
    }


    React.useEffect(()=>{
        
        getReservations();
    },[])

    return (
        <Accordion  flush>
            
            {reservations//.filter((item:reservation)=>{return item.startTime>now})
                .map((item : reservation)=>{
                    return (
                    <Accordion.Item eventKey={item.id.toString()} key={item.id}>
                        <Accordion.Header>{item.startTime + " Zákazník:" +item.customer.name}</Accordion.Header>
                        <Accordion.Body>
                        <h3 className="accordeon">Čas rezervace</h3>
                        <p className="accordeon">{item.startTime.toString()}</p>
                        <h3 className="accordeon">Na jméno:</h3>
                        <p className="accordeon">{item.customer.name}</p>
                        <h3 className="accordeon">Email:</h3>
                        <p className="accordeon">{item.customer.email}</p>
                        <h3 className="accordeon">Telefonní číslo:</h3>
                        <p className="accordeon">{item.customer.phone}</p>
                        {/*<button type="button" className="btn btn-primary btn-block">Upravit</button>*/}
                        <button value={item.id} type="button" onClick={(e)=>handleDelete(item.id)} className="btn btn-primary btn-block">Odstranit</button>
                        </Accordion.Body>
                    </Accordion.Item>);
                })}
            </Accordion>
    );
}

export default Reservations;