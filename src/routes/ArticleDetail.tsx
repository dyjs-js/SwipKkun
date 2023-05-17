import {
    Avatar,
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
import {useParams} from "react-router-dom";
import {FaStar} from "react-icons/fa";

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
                    <Image rounded={"3xl"} src="https://images.samsung.com/kdp/goods/2020/12/08/95a16159-ec47-4b17-9552-7c4d92fac7a9.png?$PD_GALLERY_L_PNG$"></Image>
                </Grid>
                <Box textAlign={"center"}>
                    <Box >
                        <Heading fontSize="lg">삼성전자 갤럭시북2 프로360 NT950QEW-A51A</Heading>
                        <Text as="b">40,000 won</Text>/24hours
                    </Box>
                </Box>

            </HStack>
            <Divider/>

            <HStack justifyContent={"space-between"} alignItems={"flex-start"}>
                <VStack alignItems="flex-start">
                    <HStack>
                        <Heading fontSize={"2xl"}>지수님이 대여해줍니다!</Heading>
                        <Avatar name="yeon" size={"lg"} src={"dfdfdf"}/>
                    </HStack>
                    <VStack alignItems={"flex-start"}>
                        <Text>review detail</Text>
                        <Text>이 노트북은 13.3인치 크기의 터치스크린 디스플레이를 가지고 있으며, 해상도는 일반적으로 Full HD (1920 x 1080 픽셀)입니다.
                            또한, 프로360은 펜 입력을 지원하여 손쉽게 스케치, 그림, 메모 등을 작성할 수 있습니다.
                            사용에 이상 없고, 택배거래 직거래 둘 다 가능합니다.

                        </Text>
                        <Text color={"gray.600"} as='b'>#노트북 #삼성전자 #갤럭시북프로 #NT950QEW-A51A</Text>
                    </VStack>
                </VStack>
            </HStack>

            <Divider/>
            <Box mt={10} mb={40}>
                <Heading mb={10} fontSize={"2xl"}>
                    <HStack>
                        <FaStar/>
                        <Text>4.0</Text>
                        <Text>＊</Text>
                        <Text>2개의 후기가 존재합니다</Text>
                    </HStack>
                </Heading>
                <Container maxW={"container.lg"}>
                    {" "}
                    <Grid gap={5} templateColumns={"2 1fr"}>
                        <VStack alignItems={"flex-start"}>
                            <HStack>
                                <Avatar name="hi" size={"md"} src={"dfdfdf"}/>
                                <VStack alignItems={"flex-start"}>
                                    <Heading fontSize={"md"}>[review.user.name1]</Heading>
                                    <HStack spacing={1}>
                                        <FaStar size="12px"/>
                                        <Text>4.0[rating]</Text>
                                    </HStack>
                                </VStack>
                            </HStack>
                            <Text>[review data]
                                정말 좋아요</Text>
                        </VStack>
                        <VStack alignItems={"flex-start"}>
                            <HStack>
                                <Avatar name="js" size={"md"} src={"dfdfdf"}/>
                                <VStack alignItems={"flex-start"}>
                                    <Heading fontSize={"md"}>[review.user.name2]</Heading>
                                    <HStack spacing={1}>
                                        <FaStar size="12px"/>
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
    );
}
