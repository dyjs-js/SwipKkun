import {
  HStack,
  Text,
  Card,
  CardBody,
  Heading,
  Divider,
  CardFooter,
  ButtonGroup,
  Button,
  Box,
  useDisclosure,
} from "@chakra-ui/react";
import { FaCircle, FaCommentDots, FaHeart, FaUser } from "react-icons/fa";
import MyinfoModal from "./MyinfoModal";
import { useQuery } from "@tanstack/react-query";
import { GetMember } from "../api";

interface IMyInfo {
  email: string;
  nickname: string;
  phone: string;
}
export default function MypageCard() {
  const { data } = useQuery<IMyInfo>(["memberInfo"], GetMember);

  const {
    isOpen: isMyinfoOpen,
    onClose: onMyinfoClose,
    onOpen: onMyinfoOpen,
  } = useDisclosure();

  return (
    <>
      <HStack justifyContent={"space-between"}>
        <Card maxW="md">
          <CardBody mt={5}>
            <HStack ml={7} spacing={20}>
              <FaUser size={50} />
              <Box>
                <HStack>
                  <FaHeart size={"15"} />
                  <Text>관심목록 3개</Text>
                </HStack>
                <HStack>
                  <FaCircle size={"15"} />
                  <Text>내가 빌린 내역 3개</Text>
                </HStack>
                <HStack>
                  <FaCircle size={"15"} />
                  <Text>내가 빌려주는 내역 3개</Text>
                </HStack>
                <HStack>
                  <FaCommentDots size={"15"} />
                  <Text>문의 내역 0개</Text>
                </HStack>
              </Box>
            </HStack>

            <HStack mt="4" spacing="3" justifyContent={"space-between"}>
              <Heading ml={3} color={"teal.900"} size="sm">
                {data?.nickname}
              </Heading>
            </HStack>
          </CardBody>{" "}
          <Divider />
          <CardFooter>
            <ButtonGroup spacing="2">
              <Button onClick={onMyinfoOpen} variant="solid" colorScheme="teal">
                내 정보 확인
              </Button>
              <Button>내 정보 수정</Button>
            </ButtonGroup>
          </CardFooter>
        </Card>
        <MyinfoModal isOpen={isMyinfoOpen} onClose={onMyinfoClose} />
      </HStack>
    </>
  );
}
