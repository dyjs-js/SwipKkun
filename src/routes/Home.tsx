import { Box, Grid, Skeleton, SkeletonText } from "@chakra-ui/react";
import Article from "../componenets/Article";

export default function Home() {
  return (
    <Grid
      mt={10}
      px={{
        base: 10,
        lg: 40,
      }}
      rowGap={4}
      columnGap={8}
      templateColumns={{
        sm: "1fr",
        md: "1fr 1fr",
        lg: "repeat(3, 1fr)",
        xl: "repeat(4, 1fr)",
      }}
    >
      <Box>
        <Skeleton height={280} rounded={"3xl"} mb={7} />
        <SkeletonText w="50%" noOfLines={2} mb={6} />
        <SkeletonText w="30%" noOfLines={1} />
      </Box>
      <Article />
    </Grid>
  );
}
