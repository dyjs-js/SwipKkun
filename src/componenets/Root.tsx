import { Box } from "@chakra-ui/react";
import { Outlet, useLocation } from "react-router-dom";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import Header from "./Header";

export default function Root() {
  const location = useLocation();
  const shouldRenderheader = location.pathname !== "/chat";

  return (
    <Box>
      {shouldRenderheader && <Header />}
      <Outlet />
      <ReactQueryDevtools />
    </Box>
  );
}
