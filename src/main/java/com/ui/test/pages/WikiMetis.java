package com.ui.test.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.ui.test.TestUtils;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;

public class WikiMetis extends BasePage {

  private final Page page;
  private String url;

  public WikiMetis(Page wikipage, String url) {
    super(wikipage, url);
    page = wikipage;
    this.url = url;
  }

  public String nikePopup() throws InterruptedException {
    AtomicReference<String> test = new AtomicReference<>("");
    page.waitForResponse(
        response ->
            "https://en.wikipedia.org/api/rest_v1/page/summary/Nike_(mythology)"
                    .equals(response.url())
                && response.status() == 200,
        () -> {
          ElementHandle nikePopup =
             page.querySelector(
                 "#mw-content-text > div.mw-parser-output > table:nth-child(8) > tbody > tr:nth-child(4) > td > div > ul > li:nth-child(77) > a");
          page.pause();
          nikePopup.focus();
          nikePopup.evaluate("el=> el.onmouseover");
          test.set(
              page.waitForSelector(
                      "body > div.mwe-popups-type-page > div.mwe-popups-container > a.mwe-popups-extract >p")
                  .textContent());
        });
    return test.get();
  }

  public void navigateToNiki() {
    this.page.querySelector(TestUtils.NIKE_POPUP).click();
  }

  public HashSet<String> getMetisContentBoxElementsTexts() {
    return this.getContentBoxElementsTexts(TestUtils.PAGE_CONTENT_BOX_LIST);
  }

  public HashSet<String> getMetisH2Text() {
    return this.getPageH2Texts(TestUtils.METIS_PAGE_H2);
  }

  public void clickMetisContentBoxLinks() throws InterruptedException {
    this.clickContentBoxLinks(TestUtils.PAGE_CONTENT_BOX_LINKS);
  }
}
