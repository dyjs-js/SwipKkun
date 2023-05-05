import {
  Box,
  Button,
  HStack,
  IconButton,
  Stack,
  useDisclosure,
} from "@chakra-ui/react";
import { FaShoppingCart, FaSyncAlt, FaUser } from "react-icons/fa";
import { Link } from "react-router-dom";
import LoginModal from "./LoginModal";
import SignUpModal from "./SignUpModal";
import { GoOctoface } from "react-icons/go";

export default function Header() {
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
      <Box color={"teal.500"}>
        <Link to="/">
          <FaSyncAlt size={"40"} />
        </Link>
      </Box>

      <HStack>
        <IconButton
          color={"gray.700"}
          variant={"ghost"}
          aria-label="Cart"
          icon={<GoOctoface size="30px" />}
        ></IconButton>
        <Button onClick={onLoginOpen}>Log In</Button>
        <Button onClick={onSignUpOpen} colorScheme="teal">
          Sign Up
        </Button>
        <IconButton
          color={"gray.700"}
          variant={"ghost"}
          aria-label="myPage"
          icon={<FaUser size="25px" />}
        ></IconButton>
        <IconButton
          color={"gray.700"}
          variant={"ghost"}
          aria-label="Cart"
          icon={<FaShoppingCart size="25px" />}
        ></IconButton>
      </HStack>
      <LoginModal isOpen={isLoginOpen} onClose={onLoginClose} />
      <SignUpModal isOpen={isSignUpOpen} onClose={onSignUpClose} />
    </Stack>
  );
}
