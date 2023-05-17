import { Box, VStack, Image, Grid, Text, HStack } from "@chakra-ui/react";
import { FaRegHeart, FaStar } from "react-icons/fa";
import { Link } from "react-router-dom";

export default function Article() {
  return (
    <Link to={"/articles/1"}>
      <VStack alignItems={"flex-start"}>
        <Box position="relative" overflow={"hidden"} mb={2}>
          <Image
            minH="280"
            rounded={"3xl"}
            src="https://placehold.co/280x280"
          ></Image>
          <Box cursor={"pointer"} position="absolute" top={5} right={5}>
            <FaRegHeart size="20px" />
          </Box>
        </Box>

        <Box>
          <Grid gap={2} templateColumns={"6fr 1fr"}>
            <Text as="b" noOfLines={1} fontSize={"md"}>
              삼성전자 갤럭시북2 프로360 NT950QEW-A51A NT950QEW-A51A
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
        <Text fontSize={"sm"} color="gray.500">
          <Text as="b">[price] won</Text>/24hours
        </Text>
      </VStack>
    </Link>
  );
}
