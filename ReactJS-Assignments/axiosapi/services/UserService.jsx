

import axios from 'axios';

const USER_API_BASE_URL = "http://localhost:7000/users/";

class UserService {

    getUsers(){
        return axios.get(USER_API_BASE_URL + "list");
    }

    // getUsers(){
    //     let users;
    //      axios.get(USER_API_BASE_URL).then(res => {
            
    //         users = res;
            
            
    //      });
    //      console.log(users);
    //      return users;
    // }

    createUser(user){
        return axios.post(USER_API_BASE_URL, user);
    }

    getUserById(userId){
        return axios.get(USER_API_BASE_URL + '/' + userId);
    }

    updateUser(user, userId){
        return axios.put(USER_API_BASE_URL + '/' + userId, user);
    }

    deleteUser(userId){
        return axios.delete(USER_API_BASE_URL + '/' + userId);
    }
}

export default new UserService()

