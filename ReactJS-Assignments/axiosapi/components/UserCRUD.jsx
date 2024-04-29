import React from 'react';
import { useRef } from 'react';
import UserService from "../services/UserService.jsx";


class UserCRUD extends React.Component {



    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.delete = this.delete.bind(this);
        this.setName = this.setName.bind(this);
        this.setAge = this.setAge.bind(this);
        this.setId = this.setId.bind(this);
        this.saveInfo = this.saveInfo.bind(this);
        this.clear = this.clear.bind(this);
        this.state = {
            users: [],
            id: 0,
            name: "",
            age: 0
        }
    }

    componentDidMount() {
        UserService.getUsers().then((res) => {
            this.setState({users : res.data});
        })
        console.log(UserService.getUsers());
    }

    setId(e) {
        this.setState({ id: e.target.value });
    }

    setName(e) {
        this.setState({ name: e.target.value });
    }

    setAge(e) {
        this.setState({ age: e.target.value });
    }

    handleSubmit(e) {
        e.preventDefault();
        let user = {name :this.state.name, age:this.state.age};
        const formData = {};
        const temp = this.state.users;
        console.log(this.refs);
        for (const field in this.refs) {
            console.log(field);
            formData[field] = this.refs[field].value;
        }
        console.log('-->', formData);
        if (this.state.id == 0) {
            
            UserService.createUser(user);
        }
        else {
            UserService.updateUser(user, this.state.id);
        }
        this.clear();
        console.log(this.state);
        // this.setState(prevState => ({
        //     employees: [...prevState.data,
        //         formData]
        // }))
        UserService.getUsers().then((res) => {
            this.setState({users : res.data});
        })
    }

    saveInfo(user, i) {
        console.log(i);
        this.setState({ id: user.id });
        this.setState({ name: user.name });
        this.setState({ age: user.age });
    }

    delete(id) {
        UserService.deleteUser(id);
        UserService.getUsers().then((res) => {
            this.setState({users : res.data});
        })
    }

    clear() {
        this.setState({ id: 0 });
        this.setState({ name: "" });
        this.setState({ age: 0 });
    }


    render() {

        return (
            <div>
                <div>
                    <form onSubmit={this.handleSubmit}>
                        <input value={this.state.id} ref="id" className="id" type='number' name="id" onChange={(e) => this.setId(e)} />
                        <input value={this.state.name} ref="name" className="name" type='text' name="name" onChange={(e) => this.setName(e)} />
                        <input value={this.state.age} ref="age" className="age" type='number' name="age" onChange={(e) => this.setAge(e)} />
                        <input type="submit" value="Submit" />

                    </form>
                    <button onClick={() => this.clear()}>Cancel</button>
                </div>
                <div>
                    <h2>Employees</h2>
                    <table className="table table-stripped">
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>name</th>
                                <th>age</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.users.map((user, index) => (
                                    <tr key={index}>
                                        <td>{user.id}</td>
                                        <td>{user.name}</td>
                                        <td>{user.age}</td>
                                        <td><button onClick={() => this.delete(user.id)}>Delete</button>
                                            <button onClick={() => this.saveInfo(user)}>Update</button>
                                        </td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }





}

export default UserCRUD;

