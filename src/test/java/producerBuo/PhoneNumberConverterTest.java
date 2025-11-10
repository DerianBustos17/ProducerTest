package producerBuo;

import buoproducertest.PhoneNumberConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberConverterTest {

    @Test
    void testConvertLettersToNumbers() {
        String result = PhoneNumberConverter.convertToPhoneNumber("800-TEST");
        assertEquals("80083780000", result);
    }
    @Test
    void testConvertHandlesNull() {
        String result = PhoneNumberConverter.convertToPhoneNumber(null);
        assertEquals("00000000000", result);
    }

    @Test
    void testFormatPhoneNumber() {
        String formatted = PhoneNumberConverter.formatPhoneNumber("80083780000");
        assertEquals("800-8378-0000", formatted);
    }

    @Test
    void testConvertHandlesLowercase() {
        String result = PhoneNumberConverter.convertToPhoneNumber("800-code");
        assertEquals("80026330000", result);
    }



    @Test
    void testConvertTrimsAndPads() {
        String result = PhoneNumberConverter.convertToPhoneNumber("A");
        assertEquals("20000000000", result);
    }
}