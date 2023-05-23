import { Avatar, AvatarBadge, Flex, Text } from "@chakra-ui/react";

export default function Header() {
  return (
    <Flex w="100%">
      <Avatar bg="blue.300">
        <AvatarBadge boxSize="1.25em" bg="green.500" />
      </Avatar>
      <Flex flexDirection="column" mx="5" justify="center">
        <Text fontSize="lg" fontWeight="bold">
          Chatbot
        </Text>
        <Text color="green.500">대화가능</Text>
      </Flex>
    </Flex>
  );
}
