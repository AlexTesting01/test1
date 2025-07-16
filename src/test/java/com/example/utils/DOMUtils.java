package com.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class DOMUtils {

    /**
     * Retrieves a list of attribute values from all elements matching the given selector.
     *
     * @param driver The WebDriver instance.
     * @param selector The CSS selector to locate the elements.
     * @param attribute The name of the attribute whose value is to be retrieved (e.g., "href", "src", "class").
     * @return A List of String containing the attribute values. Returns an empty list if no elements are found or
     * if the attribute is not present on any of the matched elements.
     */
    public static List<String> getAllAttributes(WebDriver driver, String selector, String attribute) {
        // Find all elements matching the CSS selector
        List<WebElement> elements = driver.findElements(By.cssSelector(selector));

        // Stream through the WebElements, get the attribute, filter out nulls, and collect to a list
        return elements.stream()
                .map(element -> element.getAttribute(attribute))
                .filter(attr -> attr != null && !attr.isEmpty()) // Also filter out empty strings if attribute is present but empty
                .collect(Collectors.toList());
    }
}