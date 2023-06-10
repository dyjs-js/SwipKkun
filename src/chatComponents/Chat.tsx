import { Button, Divider, Flex } from "@chakra-ui/react";
import Header from "./Header";
import Footer from "./Footer";
import { useState } from "react";
import Messages from "./Messages";
import { getChatbotApi } from "../api";

interface Message {
  from: string;
  text: string;
}

export default function Chat() {
  const [messages, setMessages] = useState<Message[]>([
    { from: "chatbot", text: "안녕하세요 쉽꾼의 챗봇입니다." },
    {
      from: "chatbot",
      text: "원하시는 물건을 말씀해주시면 추천해드려요.",
    },
  ]);
  const [inputMessage, setInputMessage] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const handleSendMessage = async () => {
    //메시지 전송 처리 코드
    if (!inputMessage.trim().length) {
      return;
    }

    try {
      setIsLoading(true);
      const response = await getChatbotApi({ message: inputMessage });
      console.log(inputMessage);
      console.log(response);
      const { recommend_success, recommend_message, recommend_product } =
        response;
      console.log("1 " + recommend_success);
      if (recommend_success) {
        setMessages((old) => [...old, { from: "me", text: inputMessage }]);
        setInputMessage("");

        setTimeout(() => {
          setIsLoading(false);
          setMessages((old) => [
            ...old,
            {
              from: "computer",
              text: recommend_message.replace(/^'(.*)'$/, "$1"),
            },
            { from: "computer", text: "더 문의사항이 있으면 말씀해주세요." },
          ]);
        }, 10);
      } else {
        setMessages((old) => [
          ...old,
          { from: "computer", text: " 죄송합니다! 오류가 발생했어요." },
          { from: "computer", text: " 다시 한번 입력주세요. " },
        ]);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <Flex w="100%" h="100vh" justify="center" align="center">
      <Flex w="40%" h="90%" flexDir="column">
        <Header />
        <Divider />
        <Messages messages={messages} />
        {isLoading ? (
          <Button
            isLoading
            loadingText="Loading"
            colorScheme="green"
            variant="outline"
            spinnerPlacement="start"
          ></Button>
        ) : (
          <Footer
            inputMessage={inputMessage}
            setInputMessage={setInputMessage}
            handleSendMessage={handleSendMessage}
          />
        )}
      </Flex>
    </Flex>
  );
}
