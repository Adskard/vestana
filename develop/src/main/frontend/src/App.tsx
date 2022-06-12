import * as React from "react";
import {Menu} from "./components/Menu/menu";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import { About } from "./components/about";
import { Home } from "./components/home";
import { Clothes } from "./components/Clothes/clothes";
import { ClientBody } from "./components/ClientBody/clientBody";
import Article from "./components/Article/article";
import { Contacts } from "./components/contacts";
import { Signin } from "./components/signin";
import { Register } from "./components/register";

const App = ()=>{

    return<>
        <Router>
            <Menu/>
            <Routes>
                <Route path="/" element={<ClientBody />}>
                    <Route index element={<Home />} />
                    <Route path="About" element={<About/>}/>
                    <Route path="Clothes">
                        <Route index element = {<Clothes />}/>
                        <Route path=":ArticleId" element={<Article />} />
                    </Route>
                    <Route path="Contacts" element={<Contacts />}/>
                    <Route path="Signin" element={<Signin/>} />
                    <Route path="Registration" element={<Register/>}/>
                </Route> 
            </Routes>
        </Router>
    </>;
}
export default App;