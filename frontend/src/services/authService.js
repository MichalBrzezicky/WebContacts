import axios from 'axios';
import {API_URL} from "@/helpers";


class AuthService {
  login (user) {
    const params = {
      email: user.email,
      password: user.password
    };
    return axios.get(
      API_URL + 'login', {
        params,
        headers: { 'Content-Type': 'application/json'}
      }).then(response => {
        console.log('RESPONSE', response)
      if (response?.data) {
        const userStore = {...response.data};
        userStore.authData = window.btoa(userStore.email + ':' + userStore.password);
        localStorage.setItem('user', JSON.stringify(userStore));
        return Promise.resolve(userStore);
      }
      return Promise.reject("Něco se pokazilo!");
    }).catch((e) => {
      return Promise.reject(e);
    })
  }

  register(user) {
    return axios.post(API_URL + 'signup',
      {
        email: user.email,
        password: user.password
      });
  }

  logout () {
    localStorage.removeItem('user');
  }

  refreshAuthData (user) {
    const userStore = {...user};
    userStore.authData = window.btoa(user.email + ':' + user.password);
    localStorage.setItem('user', JSON.stringify(userStore));
    return Promise.resolve(userStore);
  }
}

export default new AuthService();
