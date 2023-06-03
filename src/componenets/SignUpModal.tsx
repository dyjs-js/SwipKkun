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
  useToast,
} from "@chakra-ui/react";
import { FaLock, FaPhone, FaUserAlt, FaUserSecret } from "react-icons/fa";
import SocialLogin from "./SocialLogin";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { useMutation } from "@tanstack/react-query";
import { userMailSignup } from "../api";

interface SignUpModalProps {
  isOpen: boolean;
  onClose: () => void;
}

interface IForm {
  email: string;
  password: string;
  nickname: string;
  phone: string;
}

export default function SignUpModal({ isOpen, onClose }: SignUpModalProps) {
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setshowConfirmPassword] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<IForm>();
  const toast = useToast();
  const mutation = useMutation(userMailSignup, {
    onMutate: () => {
      console.log("mutation starting");
    },
    onSuccess: (data) => {
      toast({
        title: "Welcome SwipKkun",
        status: "success",
      });
      onClose();
      reset();
    },
  });
  const onSubmit = ({ email, password, nickname, phone }: IForm) => {
    // console.log(email, password, nickname, phone);
    mutation.mutate({ email, password, nickname, phone });
  };

  const handlePasswordClick = () => setShowPassword(!showPassword);
  const handleConfirmPasswordClick = () =>
    setshowConfirmPassword(!showConfirmPassword);

  return (
    <Modal onClose={onClose} isOpen={isOpen}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Sign up</ModalHeader>
        <ModalCloseButton />
        <ModalBody as="form" onSubmit={handleSubmit(onSubmit)}>
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
              <Input
                required
                isInvalid={Boolean(errors.email?.message)}
                {...register("email", {
                  required: "Please write a email",
                })}
                variant={"filled"}
                placeholder="Email address"
              />
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
                isInvalid={Boolean(errors.password?.message)}
                required
                {...register("password", {
                  required: "Plase write a Password",
                })}
                type={showPassword ? "text" : "password"}
                variant={"filled"}
                placeholder="Password"
              />
              <InputRightElement width="4.5rem">
                <Button
                  color="gray.600"
                  h="1.75rem"
                  size="sm"
                  onClick={handlePasswordClick}
                >
                  {showPassword ? "Hide" : "Show"}
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
                isInvalid={Boolean(errors.password?.message)}
                required
                {...register("password", {
                  required: "Plase write a Password",
                })}
                type={showConfirmPassword ? "text" : "password"}
                variant={"filled"}
                placeholder="Password confirm"
              />
              <InputRightElement width="4.5rem">
                <Button
                  color="gray.600"
                  h="1.75rem"
                  size="sm"
                  onClick={handleConfirmPasswordClick}
                >
                  {showConfirmPassword ? "Hide" : "Show"}
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
              <Input
                isInvalid={Boolean(errors.nickname?.message)}
                required
                {...register("nickname", {
                  required: "Plase write a Nickname",
                })}
                variant={"filled"}
                placeholder="Nickname"
              />
            </InputGroup>
            <InputGroup>
              <InputLeftElement
                children={
                  <Box color="gray.500">
                    <FaPhone />
                  </Box>
                }
              />
              <Input
                isInvalid={Boolean(errors.phone?.message)}
                required
                {...register("phone", {
                  required: "Plase write a phone number",
                })}
                variant={"filled"}
                placeholder="Phone number"
              />
              <InputRightElement width="4.5rem">
                <Button color="gray.600" h="1.75rem" size="sm">
                  Verify
                </Button>
              </InputRightElement>
            </InputGroup>
          </VStack>
          <Button
            isLoading={mutation.isLoading}
            type="submit"
            marginTop={4}
            colorScheme="teal"
            w="100%"
          >
            Sign up
          </Button>

          <SocialLogin />
        </ModalBody>
      </ModalContent>
    </Modal>
  );
}
