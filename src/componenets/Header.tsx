import {
  Box,
  Button,
  HStack,
  IconButton,
  useDisclosure,
} from "@chakra-ui/react";
import { FaMoon, FaSyncAlt } from "react-icons/fa";
import { Link } from "react-router-dom";
import LoginModal from "./LoginModal";
import SignUpModal from "./SignUpModal";

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
    <HStack
      justifyContent={"space-between"}
      py={"5"}
      px={"10"}
      borderBottomWidth={1}
    >
      <Box color={"teal.500"}>
        <Link to="/">
          <FaSyncAlt size={"40"} />
        </Link>
      </Box>

      <HStack>
        <IconButton
          variant={"ghost"}
          aria-label="dark mode"
          icon={<FaMoon />}
        ></IconButton>
        <Button onClick={onLoginOpen}>Log In</Button>
        <Button onClick={onSignUpOpen} colorScheme="teal">
          Sign Up
        </Button>
      </HStack>
      <LoginModal isOpen={isLoginOpen} onClose={onLoginClose} />
      <SignUpModal isOpen={isSignUpOpen} onClose={onSignUpClose} />
    </HStack>
  );
}
