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
    // page.pause();
    // document.querySelector('body > div.mwe-popups-type-page > div.mwe-popups-container > a
    // >p').textContent;

    AtomicReference<String> test = new AtomicReference<>("");
    page.waitForResponse(
        response ->
            "https://en.wikipedia.org/api/rest_v1/page/summary/Nike_(mythology)"
                    .equals(response.url())
                && response.status() == 200,
        () -> {
          ElementHandle nikeLink =
              this.page.querySelector(
                  "//*[@id=\"mw-content-text\"]/div[1]/table[4]/tbody/tr[4]/td/div/ul/li[77]");
          nikeLink.hover(new ElementHandle.HoverOptions().setForce(true));
          nikeLink.focus();
          page.waitForTimeout(1000);
          test.set(
              page.waitForSelector(
                      "body > div.mwe-popups-type-page > div.mwe-popups-container > a.mwe-popups-extract >p")
                  .textContent());
        });
    System.out.println(test);
    return test.get();
    //     this.page
    // .waitForSelector(
    //     "body > div.mwe-popups-type-page > div.mwe-popups-container > a.mwe-popups-extract >p")
    // .textContent();
    // System.out.println(nikeString);

    // page.pause();
    // Page popup =
    //    page.waitForPopup(
    //        () -> {
    //          ElementHandle handle = this.page.querySelector(com.ui.test.TestUtils.NIKE_POPUP);
    //          page.pause();
    //          handle.hover(new ElementHandle.HoverOptions().setForce(true));
    //        });
    // page.pause();
    // System.out.println(popup.content());

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
