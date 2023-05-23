import { Divider, Flex } from "@chakra-ui/react";
import Header from "./Header";
import Footer from "./Footer";
import { useState } from "react";
import Messages from "./Messages";
interface Message {
  from: string;
  text: string;
}
export default function Chat() {
  const [messages, setMessages] = useState<Message[]>([
    { from: "chatbot", text: "챗봇입니다" },
    { from: "me", text: "안녕하세요" },
    { from: "me", text: "반가워요" },
    {
      from: "chatbot",
      text: "궁금하신 점을 물어보세요",
    },
  ]);
  const [inputMessage, setInputMessage] = useState("");
  const handleSendMessage = () => {
    if (!inputMessage.trim().length) {
      return;
    }
    const data = inputMessage;
    setMessages((old) => [...old, { from: "me", text: data }]);
    setInputMessage("");

    setTimeout(() => {
      setMessages((old) => [...old, { from: "computer", text: data }]);
    }, 1000);
  };

  return (
    <Flex w="100%" h="100vh" justify="center" align="center">
      <Flex w="40%" h="90%" flexDir="column">
        <Header />
        <Divider />
        <Messages messages={messages} />
        <Footer
          inputMessage={inputMessage}
          setInputMessage={setInputMessage}
          handleSendMessage={handleSendMessage}
        />
      </Flex>
    </Flex>
  );
}
