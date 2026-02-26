import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { CategoryContext } from "../../App";
import { isLoggedIn } from "../auth/Auth";
function Header() {
    let {categoryList,setCategoryList} = useContext(CategoryContext);
    const handleLogout = () => {
  sessionStorage.clear(); // Or sessionStorage.removeItem("token");
    // If you are using Toastify:
    toast.success("Logged out successfully");
    navigate("/sign-in");
};
    return <>
        <nav className="navbar navbar-expand-sm bg-dark navbar-dark">
            <div className="container-fluid">
                <a className="navbar-brand" href="#"><b className="text-danger">E-</b>commerce</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="collapsibleNavbar">
                    <ul className="navbar-nav">
                        <li className="nav-item dropdown">
                            <Link className="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown">Categories</Link>
                            <ul className="dropdown-menu">   
                              {categoryList.map((category,index)=>{return <li key={category.id}><Link className="dropdown-item">{category.name}</Link></li>
                            })}
                            </ul>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">About us</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Contact us</a>
                        </li>
                        {!isLoggedIn() && <li className="nav-item">
                            <Link className="nav-link" to="/sign-in">Sign in</Link>
                        </li>}

                        {!isLoggedIn() && <li className="nav-item">
                            <Link className="nav-link" to="/sign-up">Sign up</Link>
                        </li>}
                        {isLoggedIn() && <li className="nav-item">
                            <Link onClick = {()=>handleLogout()}className="nav-link" >Sign out</Link>
                        </li>}
                    </ul>
                </div>
            </div>
        </nav>
    </>
}

export default Header;