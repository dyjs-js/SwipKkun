import {
  Text,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalHeader,
  ModalOverlay,
  VStack,
  HStack,
  Divider,
  Heading,
} from "@chakra-ui/react";
import { FaPhone, FaUserAlt, FaUserSecret } from "react-icons/fa";
import { GetMember } from "../api";
import { useQuery } from "@tanstack/react-query";

interface MyinfoModalProps {
  isOpen: boolean;
  onClose: () => void;
}

interface IMyInfo {
  email: string;
  nickname: string;
  phone: string;
}

export default function MyinfoModal({ isOpen, onClose }: MyinfoModalProps) {
  const { data } = useQuery<IMyInfo>(["memberInfo"], GetMember);

  return (
    <Modal onClose={onClose} isOpen={isOpen}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>My Info</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Divider />
          <VStack my={10}>
            <HStack>
              <FaUserAlt size={"20px"} />
              <Heading size="sm" textTransform="uppercase">
                아이디 :
              </Heading>
              <Text> {data?.email} </Text>
            </HStack>
            <HStack>
              <FaUserSecret size={"20px"} />
              <Heading size="sm" textTransform="uppercase">
                닉네임 :
              </Heading>
              <Text>{data?.nickname}</Text>
            </HStack>
            <HStack>
              <FaPhone size={"20px"} />
              <Heading size="sm" textTransform="uppercase">
                연락처 :
              </Heading>
              <Text>{data?.phone}</Text>
            </HStack>
          </VStack>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
}
