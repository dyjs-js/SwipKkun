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
} from "@chakra-ui/react";
import { FaCircle, FaCommentDots, FaHeart, FaUser } from "react-icons/fa";
export default function MypageCard() {
  return (
    <Card maxW="sm">
      <CardBody>
        <HStack justifyContent={"space-between"}>
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
          <Heading color={"green.900"} size="sm">
            Nickname
          </Heading>
        </HStack>
      </CardBody>
      <Divider />
      <CardFooter>
        <ButtonGroup spacing="2">
          <Button variant="solid" colorScheme="green">
            내 정보 확인하기
          </Button>
        </ButtonGroup>
      </CardFooter>
    </Card>
  );
}
