import BaseEntityService from "@/services/baseService.js";
import axios from "axios";
import {requestHeaders} from "@/helpers/index.js";

class PhoneNumberService extends BaseEntityService {
  constructor() {
    super('phone_number');
  }

  addAll(data, id = null) {
    return axios.post(this.apiPath + '/add_all', data, {
      params: {id},
      headers: requestHeaders(),
    }).catch(e => console.error(e))
  }
}

export default new PhoneNumberService()
