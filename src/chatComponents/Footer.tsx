import { Button, Flex, Input } from "@chakra-ui/react";
import { ChangeEvent, KeyboardEvent } from "react";

interface FooterProps {
  inputMessage: string;
  setInputMessage: (message: string) => void;
  handleSendMessage: () => void;
}

export default function Footer({
  inputMessage,
  setInputMessage,
  handleSendMessage,
}: FooterProps) {
  //입력된 메세지 업데이트
  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    setInputMessage(e.target.value);
  };
  //Enter일 경우 handleSendMessage 호출
  const handleInputKeyPress = (e: KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") {
      handleSendMessage();
    }
  };
  return (
    <Flex w="100%" mt="5">
      <Input
        placeholder="질문을 입력하세요..."
        border="none"
        borderRadius="none"
        _focus={{
          border: "1px solid black",
        }}
        onKeyPress={handleInputKeyPress}
        value={inputMessage}
        onChange={handleInputChange}
      />
      <Button
        bg="black"
        color="white"
        borderRadius="none"
        _hover={{
          bg: "white",
          color: "black",
          border: "1px solid black",
        }}
      >
        Send
      </Button>
    </Flex>
  );
}
