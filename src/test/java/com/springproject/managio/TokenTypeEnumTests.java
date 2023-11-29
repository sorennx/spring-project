package com.springproject.managio;

import com.springproject.managio.enums.TokenTypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenTypeEnumTests {

    @Test
    public void testTokenTypeEnum() {
        assertEquals(1, TokenTypeEnum.values().length);
        assertTrue("BEARER".equals(TokenTypeEnum.BEARER.name()));
    }
}
