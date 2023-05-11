import { Box, Image } from "@chakra-ui/react";

export default function MainImage() {
  return (
    <Box position="relative" overflow={"hidden"} mb={2}>
      <Image rounded={"3xl"} src="https://placehold.co/1200x280"></Image>
    </Box>
  );
}
