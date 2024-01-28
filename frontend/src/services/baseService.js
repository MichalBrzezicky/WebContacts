import axios from "axios";
import {API_URL, requestHeaders} from "@/helpers/index.js";

export default class BaseEntityService {
  constructor(apiSubPath) {
    this.apiPath = API_URL + apiSubPath
  }

  getAll(params) {
    return axios.get(this.apiPath + '/all', {
      headers: requestHeaders(),
      params: params
    }).catch(e => console.error(e));
  }

  getById(id) {
    return axios.get(this.apiPath, {
      headers: requestHeaders(),
      params: {id}
    }).catch(e => console.error(e))
  }

  add(data, id = null) {
    return axios.post(this.apiPath + '/add', data, {
      params: {id},
      headers: requestHeaders(),
    }).catch(e => console.error(e))
  }

  update(id, data) {
    return axios.post(this.apiPath + '/update', data, {
      params: {id},
      headers: requestHeaders(),
    }).catch(e => console.error(e))
  }

  delete(id) {
    return axios.delete(this.apiPath + '/delete', {
      params: {id},
      headers: requestHeaders()
    }).catch(e => console.error(e))
  }
}
