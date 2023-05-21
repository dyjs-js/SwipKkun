import { Box, Button } from "@chakra-ui/react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";

export default function CalendarModal() {
  return (
    <Box>
      <Calendar
        selectRange
        minDate={new Date()}
        maxDate={new Date(Date.now() + 60 * 60 * 24 * 7 * 4 * 6 * 1000)}
        minDetail="year"
        next2Label={null}
        prev2Label={null}
        locale="en-US"
      />
    </Box>
  );
}
