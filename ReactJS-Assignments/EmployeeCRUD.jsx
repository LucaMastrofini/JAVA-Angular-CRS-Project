import React from 'react';
import { useRef } from 'react';


class EmployeeCRUD extends React.Component {



    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.delete = this.delete.bind(this);
        this.setName = this.setName.bind(this);
        this.setAge = this.setAge.bind(this);
        this.setAddress = this.setAddress.bind(this);
        this.saveInfo = this.saveInfo.bind(this);
        this.clear = this.clear.bind(this);
        this.state = {
            employees: [],
            name: "",
            age: "",
            address: "",
            index: -1
        }
    }

    setName(e) {
        this.setState({ name: e.target.value });
    }

    setAge(e) {
        this.setState({ age: e.target.value });
    }

    setAddress(e) {
        this.setState({ address: e.target.value });
    }

    handleSubmit(e) {
        e.preventDefault();

        const formData = {};
        const temp = this.state.employees;
        console.log(this.refs);
        for (const field in this.refs) {
            console.log(field);
            formData[field] = this.refs[field].value;
        }
        console.log('-->', formData);
        if (this.state.index == -1) {
            temp.push(formData)
            this.setState({
                temp
            })
        }
        else {
            temp[this.state.index] = formData;
            this.setState({
                temp
            })
        }
        this.clear();
        console.log(this.state);
        // this.setState(prevState => ({
        //     employees: [...prevState.data,
        //         formData]
        // }))
    }

    saveInfo(employee, i) {
        console.log(i);
        this.setState({ name: employee.name });
        this.setState({ age: employee.age });
        this.setState({ address: employee.address });
        this.setState({ index: i });
    }

    delete(index) {
        const temp = this.state.employees;
        temp.splice(index, 1);
        this.setState({
            temp
        })
    }

    clear() {
        this.setState({ name: "" });
        this.setState({ age: "" });
        this.setState({ address: "" });
        this.setState({ index: -1 });
    }


    render() {

        return (
            <div>
                <div>
                    <form onSubmit={this.handleSubmit}>
                        <input value={this.state.name} ref="name" className="name" type='text' name="name" onChange={(e) => this.setName(e)} />
                        <input value={this.state.age} ref="age" className="age" type='text' name="age" onChange={(e) => this.setAge(e)} />
                        <input value={this.state.address} ref="address" className="address" type='text' name="address" onChange={(e) => this.setAddress(e)} />
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
                                <th>address</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.employees.map((employee, index) => (
                                    <tr key={index}>
                                        <td>{index}</td>
                                        <td>{employee.name}</td>
                                        <td>{employee.age}</td>
                                        <td>{employee.address}
                                            
                                        </td>
                                        <td><button onClick={() => this.delete(index)}>Delete</button>
                                            <button onClick={() => this.saveInfo(employee, index)}>Update</button>
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

export default EmployeeCRUD;

