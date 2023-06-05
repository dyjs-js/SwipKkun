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
import { useMutation, useQueryClient } from "@tanstack/react-query";
import {
  CheckEmailDuplication,
  CheckNicknameDuplication,
  userMailSignup,
} from "../api";

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
  //회원가입 후 이상없으면 -> 로그인 성공처리
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<IForm>();
  const toast = useToast();
  const queryClient = useQueryClient();
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
      queryClient.refetchQueries(["me"]);
      reset();
    },
  });
  const onSubmited = ({ email, password, nickname, phone }: IForm) => {
    mutation.mutate({ email, password, nickname, phone });
  };
  //패스워드 보여주기
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setshowConfirmPassword] = useState(false);
  const handlePasswordClick = () => setShowPassword(!showPassword);
  const handleConfirmPasswordClick = () =>
    setshowConfirmPassword(!showConfirmPassword);

  //이메일 중복확인
  const [isEmailDuplicated, SetIsEmailDuplicated] = useState("");
  const verifyEmail = async () => {
    try {
      const response = await CheckEmailDuplication({
        email: isEmailDuplicated,
      });
      console.log(response); // response 확인
      alert(response);
    } catch (error) {
      console.error(error);
    }
  };
  //닉네임 중복확인
  const [isNicknameDuplicated, SetIsNicknameDuplicated] = useState("");
  const verifyNickname = async () => {
    try {
      const response = await CheckNicknameDuplication({
        nickname: isNicknameDuplicated,
      });
      alert(response);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <Modal onClose={onClose} isOpen={isOpen}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Sign up</ModalHeader>
        <ModalCloseButton />
        <ModalBody as="form" onSubmit={handleSubmit(onSubmited)}>
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
                {...register("email", {
                  required: "Please write a email",
                })}
                variant={"filled"}
                placeholder="Email address"
                onChange={(e) => SetIsEmailDuplicated(e.target.value)}
              />
              <InputRightElement width="4.5rem">
                <Button
                  onClick={verifyEmail}
                  color="gray.600"
                  h="1.75rem"
                  size="sm"
                >
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
                onChange={(e) => SetIsNicknameDuplicated(e.target.value)}
              />
              <InputRightElement width="4.5rem">
                <Button
                  onClick={verifyNickname}
                  color="gray.600"
                  h="1.75rem"
                  size="sm"
                >
                  Verify
                </Button>
              </InputRightElement>
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
