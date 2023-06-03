import axios from "axios";

const instance = axios.create({
  baseURL: "http://127.0.0.1:8080/api/auth",
});
//login
export interface IUserEmailLoginReuqest {
  email: string;
  password: string;
}

//signup
export interface IUserSignupReuqest {
  email: string;
  password: string;
  nickname: string;
  phone: string;
}

//나중에 삭제할 수 있으면 삭제할거..
export interface IUserEmailLoginSuccess {
  ok: string;
}
export interface IUserEmailLoginError {
  error: string;
}

// access token을 header에 전달하는 인터셉터
instance.interceptors.request.use(
  async (config) => {
    const token = localStorage.getItem("access_token");
    if (token) {
      console.log("1" + token);
      const parsedToken = JSON.parse(token);
      const accessToken = parsedToken.access_token;
      console.log("2" + accessToken);
      config.headers["Authorization"] = `Bearer ${accessToken}`;
      console.log("3" + config.headers["Authorization"]);
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 회원가입 후 access token 정보를 가져옴
export const userMailSignup = async ({
  email,
  password,
  nickname,
  phone,
}: IUserSignupReuqest) => {
  {
    const response = await instance.post(
      "/signup",
      { email, password, nickname, phone },
      {}
    );
    const access_token = response.data;
    localStorage.setItem("access_token", JSON.stringify(access_token));
    console.log("signup: " + JSON.stringify(access_token));
    return response.data;
  }
};
//로그인 후 access token 정보를 가져옴
export const userEmailLogIn = async ({
  email,
  password,
}: IUserEmailLoginReuqest) => {
  const response = await instance.post("/login", { email, password }, {});
  const access_token = response.data;
  localStorage.setItem("access_token", JSON.stringify(access_token));
  console.log("login " + JSON.stringify(access_token));
  return response.data;
};

// 로그인 후 멤버 정보를 가져옴
export const GetMember = () => {
  return instance.get("/member/117").then((response) => response.data);
};

// 로그인 후 access token 정보를 가져옴
// export const userEmailLogIn = ({ email, password }: IUserEmailLoginReuqest) =>
//   instance.post("/login", { email, password }, {}).then((response) => {
//     const access_token = response.data;
//     localStorage.setItem("access_token", JSON.stringify(access_token));
//     console.log("4" + JSON.stringify(access_token));

//     return response.data;
//   });
// instance.interceptors.request.use(
//   async (config) => {
//     const token = localStorage.getItem("access_token"); // Retrieve access_token from localStorage as string
//     // Bearer 토큰 설정

//     if (token) {
//       console.log("1" + token);
//       const parsedToken = JSON.parse(token);
//       const accessToken = parsedToken.access_token;
//       console.log("2" + accessToken);
//       config.headers["Authorization"] = `Bearer ${accessToken}`;
//       console.log("3" + config.headers["Authorization"]);
//     }

//     return config;
//   },
//   (error) => {
//     return Promise.reject(error);
//   }
// );
