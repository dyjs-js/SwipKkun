import { Box, Grid, HStack, Heading, Image, Text } from "@chakra-ui/react";
import { useParams } from "react-router-dom";

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
      <HStack>
        <Grid>
          <Image rounded={"3xl"} src="https://placehold.co/600x400"></Image>
        </Grid>
        <Box>
          <Heading fontSize={"lg"}>
            삼성전자 갤럭시북2 프로360 NT950QEW-A51A NT950QEW-A51A
          </Heading>
          <Text as="b">price won</Text>/24hours
        </Box>
      </HStack>

      <Box mt={10}>
        <HStack>
          <Box bg={"green.200"}>
            <Heading>Detail</Heading>
          </Box>
          <Box bg={"green.400"}>
            <Heading>Reservation Calendar</Heading>
          </Box>
        </HStack>
      </Box>
      <Box>Review</Box>
    </Box>
  );
}
