package com.ui.test.pages;

import com.microsoft.playwright.Page;
import com.ui.test.TestUtils;

public class WikiNike extends BasePage {

  private final Page page;
  private String url;

  public WikiNike(Page wikipage, String url) {
    super(wikipage, url);
    page = wikipage;
    this.url = url;
  }

  public boolean isFamilyTreeVisible() {
    return this.isElementVisible(TestUtils.NIKE_FAMILY_TREE);
  }
}
