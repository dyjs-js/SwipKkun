import { Box, Grid, VStack } from "@chakra-ui/react";
import Article from "../componenets/Article";
import ArticleSkeleton from "../componenets/ArticleSkeleton";
import Search from "../componenets/Search";
import MainImage from "../componenets/MainImage";

export default function Home() {
  return (
    <VStack>
      <Box>
        <MainImage />
      </Box>
      <Box>
        <Search />
      </Box>
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
        <Article /> <Article /> <Article /> <Article />
      </Grid>
    </VStack>
  );
}
