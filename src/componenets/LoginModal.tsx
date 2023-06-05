import {
  Box,
  Button,
  Input,
  InputGroup,
  InputLeftElement,
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
import { FaLock, FaUserAlt } from "react-icons/fa";
import SocialLogin from "./SocialLogin";
import { useForm } from "react-hook-form";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { userEmailLogIn } from "../api";

interface LoginModalProps {
  isOpen: boolean;
  onClose: () => void;
}
interface IForm {
  email: string;
  password: string;
}

export default function LoginModal({ isOpen, onClose }: LoginModalProps) {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<IForm>();
  const toast = useToast();
  const queryClient = useQueryClient();

  //email password mutation으로 전달
  const onSubmit = ({ email, password }: IForm) => {
    mutation.mutate({ email, password });
  };

  //로그인 여부를 체크하는 함수
  const mutation = useMutation(userEmailLogIn, {
    onMutate: () => {
      console.log("mutation starting");
    },
    onSuccess: (data) => {
      toast({
        title: "Welcome back",
        status: "success",
      });
      onClose();
      queryClient.refetchQueries(["me"]);
      reset();
    },
  });

  return (
    <Modal onClose={onClose} isOpen={isOpen}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Log in</ModalHeader>
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
                type="password"
                variant={"filled"}
                placeholder="Password"
              />
            </InputGroup>
          </VStack>
          {mutation.isError ? (
            <Text color="red.500" textAlign={"center"} fontSize="sm">
              username or password가 틀렸습니다.
            </Text>
          ) : null}
          <Button
            isLoading={mutation.isLoading}
            type="submit"
            marginTop={4}
            colorScheme="teal"
            w="100%"
          >
            Log in
          </Button>
          <SocialLogin />
        </ModalBody>
      </ModalContent>
    </Modal>
  );
}
