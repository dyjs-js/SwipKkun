import { Box, Grid } from "@chakra-ui/react";
import Article from "../componenets/Article";
import ArticleSkeleton from "../componenets/ArticleSkeleton";

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
        <ArticleSkeleton />
      </Box>
      <Article />
    </Grid>
  );
}
