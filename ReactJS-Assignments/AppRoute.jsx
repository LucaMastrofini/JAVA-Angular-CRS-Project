import React from "react";

import { Link } from 'react-router';


class AppRouter extends React.Component {


    render() {

        return (

            <div>
                <h1>Navigation of the Web site </h1>
                <Link to="/home">Home</Link>||
                <Link to="/contact">Contact Us</Link>||
                <Link to="/about">About us</Link>
                
                {this.props.children}


            </div>

        );



    }

}

export default AppRouter;