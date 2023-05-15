import {
  Avatar,
  AvatarBadge,
  Box,
  Container,
  Divider,
  Grid,
  HStack,
  Heading,
  Image,
  Text,
  VStack,
} from "@chakra-ui/react";
import { useParams } from "react-router-dom";
import { FaStar } from "react-icons/fa";

export default function ArticlDetail() {
  const params = useParams();
  console.log(params);
  return (
    <Box
      mt={10}
      px={{
        base: 10,
        lg: 40,
      }}
    >
      <HStack mb={10}>
        <Grid>
          <Image rounded={"3xl"} src="https://placehold.co/600x400"></Image>
        </Grid>
        <Box>
          <Heading fontSize={"lg"}>
            [삼성전자 갤럭시북2 프로360 NT950QEW-A51A NT950QEW-A51A]
          </Heading>
          <Text as="b">[price] won</Text>/24hours
        </Box>
      </HStack>
      <Divider />

      <HStack justifyContent={"space-between"}>
        <VStack>
          <HStack>
            <Heading fontSize={"2xl"}>[nickname]님이 대여해주는 물품</Heading>
            <Avatar name="nickname" size={"lg"} src={"dfdfdf"} />
            <Avatar size={"lg"}>
              <AvatarBadge boxSize="1.25em" bg="green.500" />
            </Avatar>
          </HStack>
          <VStack>
            <Text>[Detail]상품설명</Text>
            <Text>[category hashtag]</Text>
          </VStack>
        </VStack>
      </HStack>
      <Divider />
      <Box mt={10}>
        <Heading mb={10} fontSize={"2xl"}>
          <HStack>
            <FaStar />
            <Text>4.0[rating]</Text>
            <Text>·</Text>
            <Text>[rating.length]개의 review</Text>
          </HStack>
        </Heading>
        <Container maxW={"container.lg"}>
          {" "}
          <Grid gap={5} templateColumns={"1fr 1fr"}>
            <VStack alignItems={"flex-start"}>
              <HStack>
                <Avatar name="name" size={"md"} src={"dfdfdf"} />
                <VStack alignItems={"flex-start"}>
                  <Heading fontSize={"md"}>[review.user.name]</Heading>
                  <HStack spacing={1}>
                    <FaStar size="12px" />
                    <Text>4.0[rating]</Text>
                  </HStack>
                </VStack>
              </HStack>
              <Text>[review data]</Text>
            </VStack>
            <VStack alignItems={"flex-start"}>
              <HStack>
                <Avatar name="name" size={"md"} src={"dfdfdf"} />
                <VStack alignItems={"flex-start"}>
                  <Heading fontSize={"md"}>[review.user.name]</Heading>
                  <HStack spacing={1}>
                    <FaStar size="12px" />
                    <Text>4.0[rating]</Text>
                  </HStack>
                </VStack>
              </HStack>
              <Text>[review data]</Text>
            </VStack>
          </Grid>
        </Container>
      </Box>
    </Box>
  );
}
