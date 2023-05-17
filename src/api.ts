import axios from "axios"

const instance = axios.create({
    baseURL: "http://localhost:8080/api/auth/"

})

export const getTest = () =>
    instance.get("signup/").then((response) => response.data);


