import * as React from 'react';
import "./styles/global.scss"
import App from './App';
import { createRoot } from 'react-dom/client';


const container = document.getElementById("root");
const root = createRoot(container);
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
)