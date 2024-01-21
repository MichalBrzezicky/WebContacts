import axios from 'axios';
import { API_URL } from "@/helpers";

const AUTH_API_URL = API_URL + 'auth/'

class AuthService {
  login (user) {
    const params = {
      email: user.email,
      password: user.password
    };
    return axios.get(
      AUTH_API_URL + 'login', {
        params,
        headers: { 'Content-Type': 'application/json'}
      }).then(response => {
      if (response?.data) {
        return this.refreshAuthData({...response.data, password: user.password})
      }
      return Promise.reject("Něco se pokazilo!");
    }).catch((e) => {
      return Promise.reject(e);
    })
  }

  register(user) {
    return axios.post(AUTH_API_URL + 'signup', user).then((response) => {
      return this.refreshAuthData(response.data?.data)
    }).catch((e) => {
      return Promise.reject("Něco se pokazilo!")
    })
  }

  logout () {
    localStorage.removeItem('user')
  }

  refreshAuthData (user) {
    const userStore = {...user}
    console.log('EMAIL, PASSWORD AUTH', user.email, user.password)
    userStore.authData = window.btoa(user.email + ':' + user.password)
    localStorage.setItem('user', JSON.stringify(userStore))
    return Promise.resolve(userStore)
  }
}

export default new AuthService()
