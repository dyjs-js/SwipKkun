import {
  Avatar,
  Box,
  Button,
  Container,
  Divider,
  Grid,
  HStack,
  Heading,
  Image,
  Text,
  VStack,
} from "@chakra-ui/react";
// import { useParams } from "react-router-dom";
import { FaStar } from "react-icons/fa";
import { useState } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";

export default function ArticleDetail() {
  const [dates, setDates] = useState();
  const handleDateChange = (value: any) => {
    setDates(value);
  };
  console.log(dates);
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
          <Image
            rounded={"3xl"}
            src="https://images.samsung.com/kdp/goods/2020/12/08/95a16159-ec47-4b17-9552-7c4d92fac7a9.png?$PD_GALLERY_L_PNG$"
          ></Image>
        </Grid>
        <Box textAlign={"center"}>
          <Box>
            <Heading fontSize="lg">
              삼성전자 갤럭시북2 프로360 NT950QEW-A51A
            </Heading>
            <Text as="b">40,000 won</Text>/24hours
          </Box>
        </Box>
      </HStack>
      <Divider />
      <Grid mt={10} gap={20} templateColumns={"2fr 1fr"} maxW={"container.lg"}>
        <Box>
          <HStack justifyContent={"space-between"} alignItems={"flex-start"}>
            <VStack alignItems="flex-start">
              <HStack>
                <Heading fontSize={"2xl"}>지수 님이 대여해줍니다!</Heading>
                <Avatar name="yeon" size={"lg"} src={"dfdfdf"} />
              </HStack>
              <VStack alignItems={"flex-start"}>
                <Text>review detail</Text>
                <Text>
                  이 노트북은 인텔의 최신 프로세서를 탑재하고 있어 고성능을
                  제공합니다. 사용자들은 일상적인 작업부터 중간 정도의 그래픽
                  작업까지 수행할 수 있으며, 멀티태스킹이나 멀티미디어 재생에도
                  우수한 성능을 발휘할 수 있습니다. 택배거래 직거래 둘다
                  가능합니다.
                </Text>
                <Text color={"gray.600"} as="b">
                  #노트북 #삼성전자 #갤럭시북프로 #NT950QEW-A51A
                </Text>
              </VStack>
            </VStack>
          </HStack>
          <Box mt={10} mb={40}>
            <Heading mb={10} fontSize={"2xl"}>
              <HStack>
                <FaStar />
                <Text>4.0</Text>
                <Text>·</Text>
                <Text>2개의 후기가 존재합니다</Text>
              </HStack>
            </Heading>
            <Container maxW={"container.lg"}>
              {" "}
              <Grid gap={5} templateColumns={"2 1fr"}>
                <VStack alignItems={"flex-start"}>
                  <HStack>
                    <Avatar name="hi" size={"md"} src={"dfdfdf"} />
                    <VStack alignItems={"flex-start"}>
                      <Heading fontSize={"md"}>[review.user.name1]</Heading>
                      <HStack spacing={1}>
                        <FaStar size="12px" />
                        <Text>4.0[rating]</Text>
                      </HStack>
                    </VStack>
                  </HStack>
                  <Text>[review data] 정말 좋아요</Text>
                </VStack>
                <VStack alignItems={"flex-start"}>
                  <HStack>
                    <Avatar name="js" size={"md"} src={"dfdfdf"} />
                    <VStack alignItems={"flex-start"}>
                      <Heading fontSize={"md"}>[review.user.name2]</Heading>
                      <HStack spacing={1}>
                        <FaStar size="12px" />
                        <Text>4.0[rating]</Text>
                      </HStack>
                    </VStack>
                  </HStack>
                  <Text>[review data]</Text>
                  <Text>[review data] 대여자분이 친절하고 성능이 좋아요</Text>
                </VStack>
              </Grid>
            </Container>
          </Box>
        </Box>

        <Box mt={10}>
          {" "}
          <Calendar
            minDate={new Date()}
            maxDate={new Date(Date.now() + 60 * 60 * 24 * 7 * 4 * 6 * 1000)} //6개월동안만 검색가능
            minDetail="year"
            next2Label={null}
            prev2Label={null}
            locale="en-US"
            onChange={handleDateChange}
            selectRange
          />
          <Button>Reservation</Button>
          <Text></Text>
        </Box>
      </Grid>
    </Box>
  );
}
