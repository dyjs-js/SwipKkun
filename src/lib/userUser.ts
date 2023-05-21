import { useQuery } from "@tanstack/react-query";
import { getMe } from "../api";

export default function useUser() {
  const { isLoading, data, error } = useQuery(["me"], getMe);
  console.log(error);
  return;
}
