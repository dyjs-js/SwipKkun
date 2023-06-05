import { useQuery } from "@tanstack/react-query";
import { GetMember } from "../api";

//회원가입이나 로그인 후 로그인상태를 유지 시켜줌
export default function useUser() {
  const { isLoading, data, isError } = useQuery(["me"], GetMember, {
    retry: false,
    refetchOnWindowFocus: false,
  });

  return {
    userLoading: isLoading,
    user: data,
    isLoggedIn: !isError,
  };
}

// const getMember = () => {
//   queryClient.refetchQueries(["me"]);
// };
