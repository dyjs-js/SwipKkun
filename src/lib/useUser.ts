import { useQuery } from "@tanstack/react-query";
import { GetMember } from "../api";

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
