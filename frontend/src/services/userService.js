import BaseEntityService from "@/services/baseService.js";

class UserService extends BaseEntityService {
  constructor() {
    super('user');
  }
}

export default new UserService();
