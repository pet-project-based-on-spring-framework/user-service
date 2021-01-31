package com.trl.userservice.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class WebUtil {

    private WebUtil() {
    }

    public static String getFullRequestUri() {
        ServletUriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        return decode(uriBuilder.toUriString());
    }

    private static String decode(String uriString) {
        try {
            return URLDecoder.decode(uriString, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Error decoding URI string:[" + uriString + "]", e);
        }
    }
}

