import BaseEntityService from "@/services/baseService.js";

class PhoneNumberService extends BaseEntityService {
  constructor() {
    super('phone_number');
  }
}

export default new PhoneNumberService()
