import { Box, Divider, Grid, HStack, Heading } from "@chakra-ui/react";
import MypageCard from "./MypageCard";
import { FaGift, FaHeart } from "react-icons/fa";
import Article from "./Article";

export default function Mypage() {
  return (
    <Box
      mt={10}
      px={{
        base: 10,
        lg: 40,
      }}
    >
      <Heading mb={10}>Nickname님 환영합니다!</Heading>
      <Box paddingX={10} mb={10}>
        <MypageCard />
      </Box>
      <Divider />
      <Box my={10}>
        <HStack>
          <FaHeart size={"30"} />
          <Heading size="lg">관심목록</Heading>
        </HStack>
        <Grid paddingX={10} columnGap={8} templateColumns={"repeat(3,1fr)"}>
          <Article />
          <Article />
          <Article />{" "}
        </Grid>
      </Box>
      <Divider />
      <Box my={10}>
        <HStack>
          <FaGift size={"30"} />
          <Heading size="lg">내가 빌린 내역</Heading>
        </HStack>
        <Grid paddingX={10} columnGap={8} templateColumns={"repeat(3,1fr)"}>
          <Article />
          <Article />
          <Article />
        </Grid>
      </Box>{" "}
      <Box mt={10} mb={200}>
        <HStack>
          <FaGift size={"30"} />
          <Heading size="lg">내가 빌려주는 내역</Heading>
        </HStack>
        <Grid paddingX={10} columnGap={8} templateColumns={"repeat(3,1fr)"}>
          <Article />
          <Article />
          <Article />
        </Grid>
      </Box>
    </Box>
  );
}
