package es.iespuertodelacruz.xptrade.shared.utils;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import jakarta.persistence.AttributeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateToLongConverterTest extends TestUtilities {
    private AttributeConverter<Date, Long> converter;

    @BeforeEach
    void setUp() {
        converter = new DateToLongConverter();
    }

    @Test
    void convertToDatabaseColumn_NullDateTest() {
        Assertions.assertNull(converter.convertToDatabaseColumn(null), MESSAGE_ERROR);
    }

    @Test
    void convertToDatabaseColumn_ValidDateTest() {
        Date date = new Date(1710206400000L);
        Long expected = 1710206400000L;
        Assertions.assertEquals(expected, converter.convertToDatabaseColumn(date), MESSAGE_ERROR);
    }

    @Test
    void convertToEntityAttribute_NullLongTest() {
        Assertions.assertNull(converter.convertToEntityAttribute(null), MESSAGE_ERROR);
    }

    @Test
    void convertToEntityAttribute_ValidLongTest() {
        Long timestamp = 1710206400000L;
        Date expectedDate = new Date(timestamp);
        Assertions.assertEquals(expectedDate, converter.convertToEntityAttribute(timestamp), MESSAGE_ERROR);
    }
}
