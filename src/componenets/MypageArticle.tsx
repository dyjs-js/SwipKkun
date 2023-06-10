import {
  Box,
  VStack,
  Image,
  Grid,
  Text,
  HStack,
  Stack,
  Button,
} from "@chakra-ui/react";
import { FaPen, FaRegHeart, FaStar, FaTrash } from "react-icons/fa";
import { Link } from "react-router-dom";

export default function MypageArticle() {
  return (
    <Link to={"/articles/1"}>
      <VStack alignItems={"flex-start"}>
        <Box position="relative" overflow={"hidden"} mb={2}>
          <Image
            minH="280"
            rounded={"3xl"}
            src="https://images.samsung.com/kdp/goods/2020/12/08/95a16159-ec47-4b17-9552-7c4d92fac7a9.png?$PD_GALLERY_L_PNG$"
          ></Image>
          <Box cursor={"pointer"} position="absolute" top={5} right={5}>
            <FaRegHeart size="20px" />
          </Box>
        </Box>

        <Box>
          <Grid gap={2} templateColumns={"6fr 1fr"}>
            <Text as="b" noOfLines={1} fontSize={"md"}>
              삼성전자 갤럭시북2 프로360 NT950QEW-A51A
            </Text>
            <HStack
              _hover={{
                color: "green.500",
              }}
            >
              <FaStar />
              <Text>5.0</Text>
            </HStack>
          </Grid>
        </Box>
      </VStack>
      <Stack mt={3} justifyContent={"center"} direction="row" spacing={10}>
        <Button leftIcon={<FaPen />} colorScheme="teal" variant="solid">
          리뷰작성
        </Button>
        <Button rightIcon={<FaTrash />} colorScheme="teal" variant="outline">
          리뷰삭제
        </Button>
      </Stack>
    </Link>
  );
}
