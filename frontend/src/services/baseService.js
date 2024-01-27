import axios from "axios";
import {API_URL, requestHeaders} from "@/helpers/index.js";

export default class BaseEntityService {
  constructor(apiSubPath) {
    this.apiPath = API_URL + apiSubPath
  }

  getAll(params) {
    return axios.get(this.apiPath + '/all', {
      headers: requestHeaders(),
      params
    }).catch(e => console.log(e));
  }

  getById(id) {
    return axios.get(this.apiPath, {
      headers: requestHeaders(),
      params: {id}
    }).catch(e => console.log(e))
  }

  add(data) {
    return axios.post(this.apiPath + '/add', data, {
      headers: requestHeaders()
    }).catch(e => console.log(e))
  }

  update(id, data) {
    console.log('ID DATA', id, data)
    return axios.post(this.apiPath + '/update', data, {
      params: {id},
      headers: requestHeaders(),
    }).catch(e => console.log(e))
  }

  delete(id) {
    return axios.delete(this.apiPath + '/delete', {
      params: {id},
      headers: requestHeaders()
    }).catch(e => console.log(e))
  }
}
