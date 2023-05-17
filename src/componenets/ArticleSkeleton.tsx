import { Box, HStack, Skeleton } from "@chakra-ui/react";

export default function ArticleSkeleton() {
  return (
    <Box>
      <Skeleton rounded="2xl" width={"280px"} height={"280px"} mb={7} />
      <HStack justifyContent={"space-between"}>
        <Skeleton rounded="lg" width="60%" height={5} mb={1} />
        <Skeleton rounded="lg" width="15%" height={5} />
      </HStack>
      <Skeleton rounded="lg" width="40%" height={5} mb={3} />
    </Box>
  );
}
