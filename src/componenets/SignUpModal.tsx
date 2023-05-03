import {
  Box,
  Button,
  Input,
  InputGroup,
  InputLeftElement,
  InputRightElement,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalHeader,
  ModalOverlay,
  Text,
  VStack,
} from "@chakra-ui/react";
import {
  FaEnvelope,
  FaLock,
  FaPhone,
  FaUserAlt,
  FaUserSecret,
} from "react-icons/fa";
import SocialLogin from "./SocialLogin";
import { useState } from "react";

interface SignUpModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export default function SignUpModal({ isOpen, onClose }: SignUpModalProps) {
  const [show, setShow] = useState(false);
  const handleClick = () => setShow(!show);
  return (
    <Modal onClose={onClose} isOpen={isOpen}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Sign up</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <VStack>
            <Text>Enjoy Swipkkun</Text>
            <InputGroup>
              <InputLeftElement
                children={
                  <Box color="gray.500">
                    <FaUserAlt />
                  </Box>
                }
              />
              <Input variant={"filled"} placeholder="Email adress" />
              <InputRightElement width="4.5rem">
                <Button color="gray.600" h="1.75rem" size="sm">
                  Verify
                </Button>
              </InputRightElement>
            </InputGroup>
            <InputGroup>
              <InputLeftElement
                children={
                  <Box color="gray.500">
                    <FaLock />
                  </Box>
                }
              />
              <Input
                type={show ? "text" : "password"}
                variant={"filled"}
                placeholder="Password"
              />
              <InputRightElement width="4.5rem">
                <Button
                  color="gray.600"
                  h="1.75rem"
                  size="sm"
                  onClick={handleClick}
                >
                  {show ? "Hide" : "Show"}
                </Button>
              </InputRightElement>
            </InputGroup>
            <InputGroup>
              <InputLeftElement
                children={
                  <Box color="gray.500">
                    <FaLock />
                  </Box>
                }
              />
              <Input
                type={show ? "text" : "password"}
                variant={"filled"}
                placeholder="Password confirm"
                minLength={5}
              />
              <InputRightElement width="4.5rem">
                <Button
                  color="gray.600"
                  h="1.75rem"
                  size="sm"
                  onClick={handleClick}
                >
                  {show ? "Hide" : "Show"}
                </Button>
              </InputRightElement>
            </InputGroup>
            <InputGroup>
              <InputLeftElement
                children={
                  <Box color="gray.500">
                    <FaUserSecret />
                  </Box>
                }
              />
              <Input variant={"filled"} placeholder="Username" />
            </InputGroup>
            <InputGroup>
              <InputLeftElement
                children={
                  <Box color="gray.500">
                    <FaPhone />
                  </Box>
                }
              />
              <Input variant={"filled"} placeholder="Phone number" />
              <InputRightElement width="4.5rem">
                <Button color="gray.600" h="1.75rem" size="sm">
                  Verify
                </Button>
              </InputRightElement>
            </InputGroup>
          </VStack>
          <Button marginTop={4} colorScheme="teal" w="100%">
            Sign up
          </Button>

          <SocialLogin />
        </ModalBody>
      </ModalContent>
    </Modal>
  );
}
