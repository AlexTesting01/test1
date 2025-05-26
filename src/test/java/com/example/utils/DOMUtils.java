package com.example.utils;

import com.microsoft.playwright.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class DOMUtils {

    public static List<String> getAllAttributes(Page page, String selector, String attribute) {
        Locator elements = page.locator(selector);
        return IntStream.range(0, elements.count())
                .mapToObj(i -> elements.nth(i).getAttribute(attribute))
                .filter(attr -> attr != null)
                .collect(Collectors.toList());
    }

    public static List<String> getAllText(Page page, String selector) {
        Locator elements = page.locator(selector);
        return IntStream.range(0, elements.count())
                .mapToObj(i -> elements.nth(i).textContent())
                .filter(attr -> attr != null)
                .collect(Collectors.toList());
    }
}
