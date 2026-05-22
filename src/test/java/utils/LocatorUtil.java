package utils;

import org.openqa.selenium.By;

public class LocatorUtil {

    public static By getLocator(String locator) {

        // ✅ Check for null or empty locator
        if (locator == null || locator.trim().isEmpty()) {
            throw new RuntimeException(
                "❌ Locator is NULL or EMPTY. Check your locators.properties key."
            );
        }

        // ✅ Split locator into type and value
        String[] parts = locator.split(":", 2);

        // ✅ Validate format
        if (parts.length < 2) {
            throw new RuntimeException(
                "❌ Invalid locator format: " + locator +
                ". Expected format: 'type:value'"
            );
        }

        String locatorType = parts[0].trim().toLowerCase();
        String locatorValue = parts[1].trim();

        // ✅ Return corresponding By object
        switch (locatorType) {

            case "id":
                return By.id(locatorValue);

            case "name":
                return By.name(locatorValue);

            case "xpath":
                return By.xpath(locatorValue);

            case "css":
                return By.cssSelector(locatorValue);

            case "classname":
                return By.className(locatorValue);

            case "tagname":
                return By.tagName(locatorValue);

            case "linktext":
                return By.linkText(locatorValue);

            case "partiallinktext":
                return By.partialLinkText(locatorValue);

            default:
                throw new RuntimeException(
                    "❌ Invalid locator type: " + locatorType +
                    ". Supported: id, name, xpath, css, classname, tagname, linktext, partiallinktext"
                );
        }
    }
}