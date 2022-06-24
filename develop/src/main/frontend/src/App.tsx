import * as React from "react";
import {BrowserRouter as Router, Route, Routes, useNavigate, Navigate} from "react-router-dom";
import Menu from "./components/menu/menu";
import About from "./components/about";
import Home from "./components/home";
import Clothes from "./components/clothes/clothes";
import Main from "./components/main/main";
import Article from "./components/clothingArticle/clothingArticle";
import Contacts from "./components/contacts/contacts";
import Signin from "./components/signin";
import Register from "./components/registration";
import Footer from "./components/footer/footer";
import Loans from "./components/loans/loans";
import Reservations from "./components/reservations/reservations";
import { getCurrentUser } from "./service/authService";

const App = ()=>{
    const [authenticated, setAuthenticated] = React.useState(getCurrentUser());

    const protectedRoutes = <>
    <Route path="Loans"  element ={<Loans/>}/>
    <Route path="Reservations"  element ={<Reservations/>}/>
    </>;

    React.useEffect(()=>{
        setAuthenticated(getCurrentUser())
    },[])

    return(
        <Router>
            <Menu authenticated={authenticated} setAuthenticated={setAuthenticated}/>
            <Routes>
                <Route path="/" element={<Main />}>
                    <Route index element={<Home />} />
                    <Route path="About" element={<About/>}/>
                    <Route path="Clothes">
                        <Route index element = {<Clothes />}/>
                        <Route path=":ArticleId" element={<Article authenticated={authenticated} />} />
                    </Route>
                    <Route path="Contacts" element={<Contacts />}/>
                    <Route path="Signin" element={<Signin setAuthenticated={setAuthenticated}/>} />
                    <Route path="Registration" element={<Register/>}/>
                    {authenticated ? protectedRoutes : <></>}
                    <Route path="*" element={<Navigate to="/" replace />} />
                </Route> 
            </Routes>
            <Footer/>
        </Router>
    );
}
export default App;