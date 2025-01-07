package com_SCM_utilities_webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

  public class WebdriverHelper {

      private WebDriver driver;
      private WebDriverWait wait;
      private SimpleDateFormat dateFormat;

      // Default timeout value for waits
      private static final int DEFAULT_TIMEOUT = 20;

      public WebdriverHelper(WebDriver driver) {
          this.driver = driver;
          this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
          this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      }

      /**
       * Waits for the page to load completely.
       */
      public void waitForPageToLoad() {
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
      }

      /**
       * Waits until the specified element is visible on the page.
       * 
       * @param element The element to wait for
       */
      public void waitForElementPresent(WebElement element) {
          wait.until(ExpectedConditions.visibilityOf(element));
      }

      /**
       * Switches to a new browser tab based on a partial URL match.
       * 
       * @param partialUrl The partial URL to match
       */
      public void switchToNewBrowserTab(String partialUrl) {
          switchToNewTab(partialUrl, true);
      }

      /**
       * Switches to a new browser tab based on a partial title match.
       * 
       * @param partialTitle The partial title to match
       */
      public void switchToNewBrowserTabByTitle(String partialTitle) {
          switchToNewTab(partialTitle, false);
      }

      private void switchToNewTab(String partialMatch, boolean isUrlMatch) {
          Set<String> windowHandles = driver.getWindowHandles();
          Iterator<String> iterator = windowHandles.iterator();
          
          while (iterator.hasNext()) {
              String windowId = iterator.next();
              driver.switchTo().window(windowId);

              String currentWindowValue = isUrlMatch ? driver.getCurrentUrl() : driver.getTitle();
              if (currentWindowValue.contains(partialMatch)) {
                  break;
              }
          }
      }

      /**
       * Switches to an iframe by its index.
       * 
       * @param index The index of the iframe
       */
      public void switchToFrame(int index) {
          driver.switchTo().frame(index);
      }

      /**
       * Switches to an iframe by its name or ID.
       * 
       * @param nameOrId The name or ID of the iframe
       */
      public void switchToFrame(String nameOrId) {
          driver.switchTo().frame(nameOrId);
      }

      /**
       * Switches to an iframe by the element reference.
       * 
       * @param element The WebElement of the iframe
       */
      public void switchToFrame(WebElement element) {
          driver.switchTo().frame(element);
      }

      /**
       * Switches to the default content (main page).
       */
      public void switchToDefaultContent() {
          driver.switchTo().defaultContent();
      }

      /**
       * Switches to the parent frame.
       */
      public void switchToParentFrame() {
          driver.switchTo().parentFrame();
      }

      /**
       * Accepts an alert.
       */
      public void acceptAlert() {
          driver.switchTo().alert().accept();
      }

      /**
       * Dismisses an alert.
       */
      public void dismissAlert() {
          driver.switchTo().alert().dismiss();
      }

      /**
       * Selects an option in a dropdown by its visible text.
       * 
       * @param element The dropdown WebElement
       * @param text The visible text to select
       */
      public void selectByVisibleText(WebElement element, String text) {
          new Select(element).selectByVisibleText(text);
      }

      /**
       * Selects an option in a dropdown by its value.
       * 
       * @param element The dropdown WebElement
       * @param value The value to select
       */
      public void selectByValue(WebElement element, String value) {
          new Select(element).selectByValue(value);
      }

      /**
       * Moves the mouse to an element.
       * 
       * @param element The WebElement to move to
       */
      public void moveToElement(WebElement element) {
          new Actions(driver).moveToElement(element).perform();
      }

      /**
       * Double-clicks on the given element.
       * 
       * @param element The WebElement to double-click
       */
      public void doubleClick(WebElement element) {
          new Actions(driver).doubleClick(element).perform();
      }

      /**
       * Drags and drops one element onto another.
       * 
       * @param source The source WebElement to drag
       * @param destination The destination WebElement to drop onto
       */
      public void dragAndDrop(WebElement source, WebElement destination) {
          new Actions(driver).dragAndDrop(source, destination).perform();
      }

      /**
       * Scrolls to the specified element.
       * 
       * @param element The WebElement to scroll to
       */
      public void scrollToElement(WebElement element) {
          new Actions(driver).scrollToElement(element).perform();
      }

      /**
       * Right-clicks on the specified element.
       * 
       * @param element The WebElement to right-click on
       */
      public void rightClick(WebElement element) {
          new Actions(driver).contextClick(element).perform();
      }

      /**
       * Sends a sequence of keys to the browser.
       * 
       * @param value The keys to send
       */
      public void sendKeys(String value) {
          new Actions(driver).sendKeys(value).perform();
      }

      /**
       * Executes JavaScript on the page.
       * 
       * @param script The JavaScript code to execute
       */
      public void executeJavaScript(String script) {
          ((JavascriptExecutor) driver).executeScript(script);
      }

      /**
       * Returns the current date in the format yyyy-MM-dd.
       * 
       * @return The current date
       */
      public String getCurrentDate() {
          return dateFormat.format(new Date());
      }

      /**
       * Returns a date 30 days after the current date in the format yyyy-MM-dd.
       * 
       * @return The date 30 days later
       */
      public String getFutureDate() {
          Calendar calendar = Calendar.getInstance();
          calendar.add(Calendar.DAY_OF_MONTH, 30);
          return dateFormat.format(calendar.getTime());
      }
  }


