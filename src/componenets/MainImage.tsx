import { Box, Image } from "@chakra-ui/react";

export default function MainImage() {
  return (
    <Box px={"40"} position="relative" overflow={"hidden"} mt={5} mb={2}>
      <Image
        maxHeight={200}
        minWidth={1600}
        rounded={"3xl"}
        src="https://github.com/dyjs-js/moreFront/blob/main/image/Untitled.png?raw=true"
      ></Image>
    </Box>
  );
}
