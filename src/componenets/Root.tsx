import { Box } from "@chakra-ui/react";
import { Outlet, useLocation } from "react-router-dom";
import Header from "./Header";

export default function Root() {
  const location = useLocation();
  const shouldRenderheader = location.pathname !== "/chat";

  return (
    <Box>
      {shouldRenderheader && <Header />}
      <Outlet />
    </Box>
  );
}
