package com.ui.test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TestUtils {

  public static String PAGE_MAIN_HEADER = "//*[@id=\"firstHeading\"]";
  public static String PAGE_CONTENT_BOX = "//*[@id=\"toc\"]";
  public static String PAGE_CONTENT_BOX_LIST = ".toc > ul > li > a > span.toctext";
  public static String METIS_PAGE_URL = "https://en.wikipedia.org/wiki/Metis_(mythology)";
  public static String METIS_PAGE_H2 = ".mw-parser-output> h2 > span.mw-headline";
  public static String NIKE_PAGE_URL = "https://en.wikipedia.org/wiki/Nike_(mythology)";
  public static String NIKE_FAMILY_TREE =
      "#mw-content-text > div.mw-parser-output > table.toccolours";
  public static String PAGE_CONTENT_BOX_LINKS = "#toc >ul>li.toclevel-1>a>span.toctext";
  public static String NIKE_POPUP =
      "#mw-content-text > div.mw-parser-output > table:nth-child(6) > tbody > tr:nth-child(6) > td > div > ul > li:nth-child(13) > a";

  public TestUtils() {}

  public static Browser getChromeBrowser(Playwright playwright) {
    BrowserType chromium = playwright.chromium();
    return chromium.launch(new BrowserType.LaunchOptions().setHeadless(false));
  }

  public static Browser getFirefoxBrowser(Playwright playwright) {
    BrowserType firefox = playwright.firefox();
    return firefox.launch(new BrowserType.LaunchOptions().setHeadless(false));
  }

  public BrowserContext getContext(Browser browser) {
    return browser.newContext();
  }

  public void closeContext(BrowserContext context) {
    context.close();
  }

  public Page getPage(BrowserContext context) {
    return context.newPage();
  }

  public void closeBrowser(Browser browser) {
    browser.close();
  }
}
