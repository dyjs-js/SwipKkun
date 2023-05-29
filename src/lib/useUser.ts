import { useQuery } from "@tanstack/react-query";
import { getMe } from "../api";

//getMe호출
export default function useUser() {
  const { isLoading, data, isError } = useQuery(["me"], getMe, {
    retry: false,
  });

  return {
    userLoading: isLoading,
    user: data,
    isLoggedIn: !isError,
  };
}
