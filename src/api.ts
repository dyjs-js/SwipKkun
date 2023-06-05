import axios from "axios";

const instance = axios.create({
  baseURL: "http://127.0.0.1:8080/api/auth",
});
//login
export interface IUserLogin {
  email: string;
  password: string;
}

//signup
export interface IUserSignup {
  email: string;
  password: string;
  nickname: string;
  phone: string;
}

export interface IUserCheckEmail {
  email: string;
}

export interface IUserCheckNickname {
  nickname: string;
}

// access token을 header에 전달하는 인터셉터
instance.interceptors.request.use(
  async (config) => {
    const token = localStorage.getItem("access_token");
    if (token) {
      console.log(token);
      config.headers["Authorization"] = `Bearer ${token}`;
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
}: IUserSignup) => {
  {
    const response = await instance.post(
      "/signup",
      { email, password, nickname, phone },
      {}
    );
    const access_token = response.data.access_token;
    const member_id = response.data.member_id;
    localStorage.setItem("access_token", access_token);
    localStorage.setItem("member_id", member_id);
    console.log("signup: " + access_token);
    return response.data;
  }
};
//로그인 후 access token 정보를 가져옴
export const userEmailLogIn = async ({ email, password }: IUserLogin) => {
  const response = await instance.post("/login", { email, password }, {});
  console.log(response.data);
  const access_token = response.data.access_token;
  const member_id = response.data.member_id;

  localStorage.setItem("access_token", access_token);
  localStorage.setItem("member_id", member_id);
  console.log("login " + access_token);
  return response.data;
};

// 로그인 후 멤버 정보를 가져옴
export const GetMember = () => {
  const memberId = localStorage.getItem("member_id");
  console.log("getmember" + memberId);
  return instance.get(`/member/${memberId}`).then((response) => response.data);
};

// 이메일 중복 체크
export const CheckEmailDuplication = async ({ email }: IUserCheckEmail) => {
  const response = await instance.post("/check-email", { email }, {});
  return response.data;
};

//닉네임 중복 체크
export const CheckNicknameDuplication = async ({
  nickname,
}: IUserCheckNickname) => {
  const response = await instance.post("/check-nickname", { nickname }, {});
  return response.data;
};
