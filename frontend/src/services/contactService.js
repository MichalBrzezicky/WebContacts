import BaseEntityService from "@/services/baseService.js";

class ContactService extends BaseEntityService {
  constructor() {
    super('contact');
  }
}

export default new ContactService()
