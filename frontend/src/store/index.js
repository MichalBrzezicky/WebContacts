import { createStore } from "vuex";
import { auth } from "@/store/authModule.js"

const store = createStore({
  modules: {
    auth,
  }
});

export default store;
