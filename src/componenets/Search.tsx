import {
  IconButton,
  Input,
  InputGroup,
  InputLeftElement,
  InputRightElement,
  useDisclosure,
} from "@chakra-ui/react";
import { FaMapMarkerAlt, FaMicrophone, FaSearch } from "react-icons/fa";
import RegionSelectorModal from "./RegionSelectorModal";

export default function Search() {
  const {
    isOpen: isRegionModalOpen,
    onClose: onRegionModalclose,
    onOpen: onRegionModalOpen,
  } = useDisclosure();

  const handleRegionSelect = (regison: string) => {};
  return (
    <InputGroup mb={25} width={600} size={"lg"}>
      <InputLeftElement width="3rem">
        <IconButton
          onClick={onRegionModalOpen}
          color={"gray.700"}
          variant={"ghost"}
          aria-label="Search"
          icon={<FaMapMarkerAlt size="26px" />}
        ></IconButton>
      </InputLeftElement>
      <Input
        focusBorderColor="teal.600"
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
      <RegionSelectorModal
        isOpen={isRegionModalOpen}
        onClose={onRegionModalclose}
        onRegionSelect={handleRegionSelect}
      />
    </InputGroup>
  );
}
