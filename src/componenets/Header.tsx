import {
  Box,
  Button,
  HStack,
  IconButton,
  Menu,
  MenuButton,
  MenuItem,
  MenuList,
  Stack,
  useDisclosure,
} from "@chakra-ui/react";
import { FaHome, FaShoppingCart, FaUser } from "react-icons/fa";
import { Link, useNavigate } from "react-router-dom";
import LoginModal from "./LoginModal";
import SignUpModal from "./SignUpModal";
import { GoOctoface } from "react-icons/go";
import useUser from "../lib/useUser";
import { useQueryClient } from "@tanstack/react-query";

export default function Header() {
  const { userLoading, isLoggedIn, user } = useUser();
  const {
    isOpen: isLoginOpen,
    onClose: onLoginClose,
    onOpen: onLoginOpen,
  } = useDisclosure();

  const {
    isOpen: isSignUpOpen,
    onClose: onSignUpClose,
    onOpen: onSignUpOpen,
  } = useDisclosure();

  //로그아웃 시 로컬스토리지 삭제 후 메인페이지로 이동
  const navigate = useNavigate();
  const queryClient = useQueryClient();
  const handleLogout = () => {
    localStorage.removeItem("access_token");
    localStorage.removeItem("member_id");

    navigate("/");
    queryClient.refetchQueries(["me"]);
  };

  return (
    <Stack
      alignItems={"center"}
      justifyContent={"space-between"}
      py={"5"}
      px={"40"}
      direction={{
        sm: "column",
        md: "row",
      }}
      spacing={{
        sm: 4,
        md: 0,
      }}
      borderBottomWidth={1}
    >
      <Box
        color={"gray.700"}
        _hover={{
          color: "teal.700",
        }}
      >
        <Link to="/">
          <FaHome size={"40"} />
        </Link>
      </Box>

      <HStack>
        <Link to="/chat" target="_blank" rel="noopener noreferrer">
          <IconButton
            color={"gray.700"}
            variant={"ghost"}
            aria-label="Ai chat"
            icon={<GoOctoface size="30px" />}
          ></IconButton>
        </Link>
        {!userLoading ? (
          !isLoggedIn ? (
            <>
              <Button onClick={onLoginOpen}>Log In</Button>
              <Button onClick={onSignUpOpen} colorScheme="teal">
                Sign Up
              </Button>
            </>
          ) : (
            <>
              <Box>
                <Menu>
                  <MenuButton
                    as={IconButton}
                    color={"gray.700"}
                    variant={"ghost"}
                    aria-label="myPage"
                    icon={<FaUser size="25px" />}
                  />

                  <MenuList>
                    <Link to="/mypage">
                      <MenuItem>마이페이지</MenuItem>
                    </Link>
                    <Link to="/articles/upload">
                      <MenuItem>대여글 올리기</MenuItem>
                    </Link>

                    <MenuItem onClick={handleLogout}>로그아웃</MenuItem>
                  </MenuList>
                </Menu>
              </Box>
              <IconButton
                color={"gray.700"}
                variant={"ghost"}
                aria-label="Cart"
                icon={<FaShoppingCart size="25px" />}
              ></IconButton>
            </>
          )
        ) : null}
      </HStack>
      <LoginModal isOpen={isLoginOpen} onClose={onLoginClose} />
      <SignUpModal isOpen={isSignUpOpen} onClose={onSignUpClose} />
    </Stack>
  );
}
