import { Box, Image } from "@chakra-ui/react";

export default function MainImage() {
  return (
    <Box px={"40"} position="relative" overflow={"hidden"} mt={5} mb={2}>
      <Image rounded={"3xl"} src="https://placehold.co/1600x280"></Image>
    </Box>
  );
}
