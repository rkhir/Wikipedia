package com.ui.test.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.ui.test.TestUtils;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BasePage {

  private Page page;
  private String url;

  public BasePage(Page page, String url) {
    this.url = url;
    this.page = page;
  }

  public Boolean navigate() {
    page.navigate(url);
    ElementHandle handle = page.waitForSelector(TestUtils.PAGE_CONTENT_BOX);
    return handle.isVisible();
  }

  public String getPageTitle() {
    return page.title();
  }

  public String getPageH1Header() {
    return page.textContent(TestUtils.PAGE_MAIN_HEADER);
  }

  public String getPageContentHeader() {
    return page.textContent(TestUtils.PAGE_MAIN_HEADER);
  }

  public List<ElementHandle> getPageH2Elements(String selector) {
    return this.page.querySelectorAll(selector);
  }

  public List<ElementHandle> getContentBoxOrderedListElements(String selector) {
    return page.querySelectorAll(selector);
  }

  public String getElementText(ElementHandle elementHandle) {
    return elementHandle.textContent();
  }

  public Set<String> getElementsText(List<ElementHandle> elementHandlers) {
    Set<String> elementsTexts = new HashSet<>();
    System.out.println();
    elementHandlers.forEach(handle -> elementsTexts.add(handle.textContent()));
    return elementsTexts;
  }

  public HashSet<String> getContentBoxElementsTexts(String selector) {
    List<ElementHandle> elementHandles = this.getContentBoxOrderedListElements(selector);
    HashSet<String> elementsTexts = new HashSet<>();
    elementHandles.forEach(handle -> elementsTexts.add(handle.textContent()));
    return elementsTexts;
  }

  public HashSet<String> getPageH2Texts(String selector) {
    List<ElementHandle> elementHandles = this.getPageH2Elements(selector);
    HashSet<String> elementsTexts = new HashSet<>();
    elementHandles.forEach(handle -> elementsTexts.add(handle.textContent()));
    return elementsTexts;
  }

  public void clickContentBoxLinks(String selector) throws InterruptedException {
    this.getContentBoxOrderedListElements(selector)
        .forEach(
            elementHandle -> {
              elementHandle.click();
            });
  }

  public boolean isElementVisible(String selector) {
    return this.page.querySelector(selector).isVisible();
  }

  public void clickElement(String selector) {
    page.click(selector);
  }

  public void screenshot() {
    page.screenshot(
        new Page.ScreenshotOptions()
            .setPath(Paths.get("screenshots/" + new Date().toString() + ".png")));
  }
}
