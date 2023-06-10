import axios from "axios";
//일반 api instance 설정
const generalInstance = axios.create({
  baseURL: "http://127.0.0.1:8080/api/auth",
});
//챗봇 api instance 설정
const chatInstance = axios.create({
  baseURL: "http://127.0.0.1:8000/api/chat",
});

//타입스크립트 인터페이스 설정
export interface IUserLogin {
  email: string;
  password: string;
}

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
export interface IChatbotP {
  message: string;
}

// access token을 header에 전달하는 인터셉터
generalInstance.interceptors.request.use(
  async (config) => {
    const token = localStorage.getItem("access_token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
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
    const response = await generalInstance.post(
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
  const response = await generalInstance.post(
    "/login",
    { email, password },
    {}
  );
  const access_token = response.data.access_token;
  const member_id = response.data.member_id;

  localStorage.setItem("access_token", access_token);
  localStorage.setItem("member_id", member_id);
  console.log("login " + access_token);
  return response.data;
};

// 로그인 후 멤버 정보를 가져와서 useUser함수를 통해 로그인에 성공처리함
export const GetMember = () => {
  const memberId = localStorage.getItem("member_id");
  return generalInstance
    .get(`/member/${memberId}`)
    .then((response) => response.data);
};

// 이메일 중복 체크
export const CheckEmailDuplication = async ({ email }: IUserCheckEmail) => {
  const response = await generalInstance.post("/check-email", { email }, {});
  return response.data;
};

//닉네임 중복 체크
export const CheckNicknameDuplication = async ({
  nickname,
}: IUserCheckNickname) => {
  const response = await generalInstance.post(
    "/check-nickname",
    { nickname },
    {}
  );
  return response.data;
};
//chatgpt 챗봇을 위한 api 연결
export const getChatbotApi = async ({ message }: IChatbotP) => {
  const response = await chatInstance.post("/send-message", { message }, {});
  return response.data;
};
