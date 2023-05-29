import {
  Button,
  FormControl,
  FormHelperText,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalHeader,
  ModalOverlay,
  Radio,
  RadioGroup,
  Stack,
} from "@chakra-ui/react";
import { useState } from "react";

interface RegionSelectorModalProps {
  isOpen: boolean;
  onClose: () => void;
  onRegionSelect: (regison: string) => void;
}

export default function RegionSelectorModal({
  isOpen,
  onClose,
  onRegionSelect,
}: RegionSelectorModalProps) {
  const [selectedRegion, setSelectedRegion] = useState("");
  const handleRegionChange = (value: string) => {
    setSelectedRegion(value);
  };

  const handleSubmit = () => {
    onRegionSelect(selectedRegion);
    onClose();
  };
  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>지역을 선택해주세요</ModalHeader>
        <ModalCloseButton />
        <ModalBody textAlign="center">
          <FormControl as="fieldset">
            <RadioGroup defaultValue="Itachi" onChange={handleRegionChange}>
              <Stack>
                <Radio value="서울">서울</Radio>
                <Radio value="경기도">경기도</Radio>
                <Radio value="강원도">강원도</Radio>
                <Radio value="충청도">충청도</Radio>
                <Radio value="전라도">전라도</Radio>
                <Radio value="경상도">경상도</Radio>
                <Radio value="제주도">제주도</Radio>
                <Radio value="전지역">전지역</Radio>
              </Stack>
            </RadioGroup>
            <FormHelperText>현재 당신의 지역을 알려주세요</FormHelperText>
            <Button
              mt={4}
              colorScheme="teal"
              type="submit"
              onClick={handleSubmit}
            >
              Submit
            </Button>
          </FormControl>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
}
