import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import Layout from "./components/Layout.jsx";
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router-dom";
import Home from "./components/Home.jsx";
import About from "./components/About.jsx";
import Github, { githubInfoLoader } from "./components/Github.jsx";

const router = createBrowserRouter(
  createRoutesFromElements(
    // The parent route uses the Layout
    <Route path="/" element={<Layout />}>
      <Route path="home" element={<Home/>} />
      <Route path="about" element={<About/>} />
      <Route path="github" element={<Github/>} loader={githubInfoLoader} />
    </Route>,
  ),
);
createRoot(document.getElementById("root")).render(
  <StrictMode>
    <RouterProvider router={router} />
    
  </StrictMode>,
);
