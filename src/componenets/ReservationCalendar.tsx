import {
  Box,
  Button,
  Divider,
  HStack,
  Text,
  VStack,
  useDisclosure,
} from "@chakra-ui/react";
import { FaStar } from "react-icons/fa";
import CalendarModal from "./CalendarModal";
import { useState } from "react";

export default function ReservationCalendar() {
  const [selectedRentalDate, setSelectedRentalDate] = useState<string | null>(
    null
  );
  const [selectedReturnDate, setSelectedReturnDate] = useState<string | null>(
    null
  );

  const {
    isOpen: isDateOpen,
    onClose: onDateClose,
    onOpen: onDateOpen,
  } = useDisclosure();

  const {
    isOpen: isReturnDateOpen,
    onClose: onReturnDateClose,
    onOpen: onReturnDateOpen,
  } = useDisclosure();

  return (
    <Box pt={10}>
      <Box
        display="flex"
        flexDirection="column"
        justifyContent="center"
        alignItems="center"
        borderRadius="2xl"
        border="1px solid gray"
      >
        <Box my={15}>
          <HStack>
            {" "}
            <Text>72,000 won · 24hour </Text>
            <FaStar />
            <Text>4.0</Text>
          </HStack>
        </Box>
        <Divider />
        <Box
          my={15}
          textAlign={"center"}
          borderRadius="20px"
          border="1px solid gray"
        >
          <Box my={5} padding={3}>
            <Button
              onClick={onDateOpen}
              colorScheme="green.600"
              variant="ghost"
            >
              <VStack>
                <Text>대여일</Text>
                <Text>
                  {selectedRentalDate
                    ? selectedRentalDate
                    : "날짜를 선택하세요"}
                </Text>
              </VStack>
            </Button>{" "}
            <Button
              onClick={onReturnDateOpen}
              colorScheme="green.600"
              variant="ghost"
            >
              <VStack>
                <Text>반납일</Text>
                <Text>
                  {selectedReturnDate
                    ? selectedReturnDate
                    : "날짜를 선택하세요"}
                </Text>
              </VStack>
            </Button>
          </Box>
          <Divider />
          <Text>예약가능 V</Text>
        </Box>
        <Button w="100%" colorScheme="green.600" variant="outline">
          예약하기
        </Button>

        <Divider />
        <Text>72,000 X 2일 144,000원</Text>
        <Text>총 금액 144,000원</Text>
      </Box>
      <CalendarModal isOpen={isDateOpen} onClose={onDateClose} />
      <CalendarModal isOpen={isReturnDateOpen} onClose={onReturnDateClose} />
    </Box>
  );
}
