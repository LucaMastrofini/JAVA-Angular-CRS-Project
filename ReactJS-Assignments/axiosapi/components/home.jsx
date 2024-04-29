import React from "react";

import { Link } from 'react-router';

class Home extends React.Component {


    render() {

        return (

            <div>
                <h1>LTI Home page</h1>
                <Link to="/home/users">User Server</Link>||
                <Link to="/home/employees">Employee</Link>
                <div>
                    { this.props.children }
                </div>
                
            </div>



        );
    }

}

export default Home;

