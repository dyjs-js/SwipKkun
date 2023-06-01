import axios from "axios";
const instance = axios.create({
  baseURL: "http://127.0.0.1:8080/api/auth",
});
//login
export interface IUserEmailLoginVariables {
  email: string;
  password: string;
}

export interface IUserEmailLoginResponse {
  token: {
    access_token: string;
  };
  member_info: {
    email: string;
    nickname: string;
    phone: string;
  };
}
//나중에 삭제할 수 있으면 삭제할거..
export interface IUserEmailLoginSuccess {
  ok: string;
}
export interface IUserEailLoginError {
  error: string;
}

//Login user정보를 가져옴
export const userEmailLogIn = ({ email, password }: IUserEmailLoginVariables) =>
  instance.post("/login", { email, password }).then((response) => {
    const member_info = response.data.member_info;
    const access_token = response.data.token.access_token;
    console.log(access_token);
    return member_info;
  });
// Access token 정보를 가져옴
export const GetUserAccessToken = ({
  email,
  password,
}: IUserEmailLoginVariables) =>
  instance.post("/login", { email, password }).then((response) => {
    const access_token = response.data.token.access_token;
    console.log(access_token);
    return access_token;
  });

// 나의 정보 가져오기
export const getMe = () => {
  return instance
    .get("/member/109", {
      headers: {
        Authorization: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6ImV4YW1wbGUxQGV4YW1wbGUuY29tIiwiaWF0IjoxNjg1NjM5MTY3LCJleHAiOjE2ODU2NDA5Njd9.BVJqgv8qMNqQ8PlWsmTkkeJuhxFA6VOuP7GRJUIrpbs


      `,
      },
    })
    .then((response) => response.data);
};

/*
const email = 'example1@example.com';
const password = 'mypassword';

getMe(email, password)
  .then((data) => {
    console.log(data);
  })
  .catch((error) => {
    console.error(error);
  }); */
// api test
// export const getTest = () =>
//   instance.get(`signup/`).then((response) => response.data);

// //로그아웃
// export const logOut = () =>
//   instance.post(`user/log-out`).then((Response) => Response.data);

// export const getSome=(something)=>{
//   console.log(something);
//   return instance.get(`rooms/11`).then((response)=>response.data);
// }
