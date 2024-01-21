import BaseEntityService from "@/services/baseService.js";

class FolderService extends BaseEntityService {
  constructor() {
    super('folder')
  }
}

export default new FolderService()
