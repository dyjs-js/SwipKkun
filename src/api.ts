import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080/api/auth/",
  withCredentials: true,
});

//api test
export const getTest = () =>
  instance.get(`signup/`).then((response) => response.data);

//나의 로그인 정보 가져오기
export const getMe = () =>
  instance.get(`user/me`).then((response) => response.data);

//로그아웃
export const logOut = () =>
  instance.post(`user/log-out`).then((Response) => Response.data);

// const BASE_URL = "http://localhost:8080/api/auth";
// export async function getId() {
//   const response = await fetch(`${BASE_URL}/signup`);
//   const json = await response.json();
//   return json;
// }
