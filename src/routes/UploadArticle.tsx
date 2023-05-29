import {
  Box,
  Button,
  Checkbox,
  Container,
  FormControl,
  FormHelperText,
  FormLabel,
  Text,
  Heading,
  Input,
  InputGroup,
  InputLeftAddon,
  Textarea,
  VStack,
  useDisclosure,
  Select,
} from "@chakra-ui/react";
import ProtectedPage from "../componenets/ProtectedPage";
import { FaMoneyBill } from "react-icons/fa";
import RegionSelectorModal from "../componenets/RegionSelectorModal";
import { useState } from "react";

export default function UploadArticle() {
  const {
    isOpen: isRegionSelectorOpen,
    onClose: onRegionSelectorClose,
    onOpen: onRegionSelectorOpen,
  } = useDisclosure();
  const [selectedRegion, setSelectedRegion] = useState("");
  const handleRegionSelect = (regison: string) => {
    setSelectedRegion(regison);
  };

  return (
    <ProtectedPage>
      <Box
        pb={40}
        mt={10}
        px={{
          base: 10,
          lg: 40,
        }}
      >
        <Container>
          <Heading textAlign={"center"}>대여글을 올려보세요!</Heading>
          <VStack spacing={5} as="form" mt={5}>
            <FormControl>
              <FormLabel>제목</FormLabel>
              <Input required type="text" />
              <FormHelperText>
                상품명을 입력하세요 (ex 삼성노트북)
              </FormHelperText>
            </FormControl>
            <FormControl>
              <FormLabel>₩ 가격 / 24시간 기준</FormLabel>
              <InputGroup>
                <InputLeftAddon children={<FaMoneyBill />} />
                <Input type="number" min={0} />
              </InputGroup>
            </FormControl>
            <FormControl>
              <FormLabel>제품 설명</FormLabel>
              <Textarea />
            </FormControl>
            <FormControl>
              <Button onClick={onRegionSelectorOpen}>
                지역을 선택해주세요
              </Button>
            </FormControl>
            {selectedRegion && (
              <FormControl>
                <FormLabel>선택한 지역</FormLabel>
                <Input value={selectedRegion} readOnly />
              </FormControl>
            )}
            <FormControl>
              <FormLabel>거래 방식</FormLabel>
              <Select placeholder="Choose a kind">
                <option value="entire_place">직거래</option>
                <option value="private_room">택배거래</option>
                <option value="shared_room">직거래/택배거래</option>
              </Select>
              <FormHelperText>거래 방식을 선택해주세요</FormHelperText>
            </FormControl>
            <FormControl>
              <FormLabel>유의사항</FormLabel>
              <Input required type="text" />
            </FormControl>

            <FormControl>
              <FormLabel>해쉬태그</FormLabel>
              <Input required type="text" />
              <FormHelperText>예시)) #노트북 #삼성전자 #품번</FormHelperText>
            </FormControl>
          </VStack>
        </Container>
        <RegionSelectorModal
          isOpen={isRegionSelectorOpen}
          onClose={onRegionSelectorClose}
          onRegionSelect={handleRegionSelect}
        />
      </Box>
    </ProtectedPage>
  );
}
