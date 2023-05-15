import {
  IconButton,
  Input,
  InputGroup,
  InputLeftElement,
  InputRightElement,
} from "@chakra-ui/react";
import { FaMapMarkerAlt, FaMicrophone, FaSearch } from "react-icons/fa";

export default function Search() {
  return (
    <InputGroup marginY={25} width={600} size={"lg"}>
      <InputLeftElement width="3rem">
        <IconButton
          color={"gray.700"}
          variant={"ghost"}
          aria-label="Search"
          icon={<FaMapMarkerAlt size="25px" />}
        ></IconButton>
      </InputLeftElement>
      <Input
        focusBorderColor="green.600"
        variant={"filled"}
        placeholder="물품을 검색해보세요"
      />
      <InputRightElement width="6rem">
        <IconButton
          color={"gray.700"}
          variant={"ghost"}
          aria-label="Search"
          icon={<FaSearch size="25px" />}
        ></IconButton>
        <IconButton
          color={"gray.700"}
          variant={"ghost"}
          aria-label="Search"
          icon={<FaMicrophone size="25px" />}
        ></IconButton>
      </InputRightElement>
    </InputGroup>
  );
}
