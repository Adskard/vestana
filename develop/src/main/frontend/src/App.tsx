import * as React from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Menu from "./components/menu/menu";
import About from "./components/about";
import Home from "./components/home";
import Clothes from "./components/clothes/clothes";
import Main from "./components/main/main";
import Article from "./components/clothingArticle/clothingArticle";
import Contacts from "./components/contacts";
import Signin from "./components/signin";
import Register from "./components/registration";
import Footer from "./components/footer/footer";
import { UserContext } from "./context";
import { getCurrentUser } from "./service/authService";

const App = ()=>{
    const [authenticated, setAuthenticated] = React.useState()

    return(
        <Router>
            <Menu authenticated={authenticated} setAuthenticated={setAuthenticated}/>
            <Routes>
                <Route path="/" element={<Main />}>
                    <Route index element={<Home />} />
                    <Route path="About" element={<About/>}/>
                    <Route path="Clothes">
                        <Route index element = {<Clothes />}/>
                        <Route path=":ArticleId" element={<Article />} />
                    </Route>
                    <Route path="Contacts" element={<Contacts />}/>
                    <Route path="Signin" element={<Signin setAuthenticated={setAuthenticated}/>} />
                    <Route path="Registration" element={<Register/>}/>
                </Route> 
            </Routes>
            <Footer/>
        </Router>
    );
}
export default App;