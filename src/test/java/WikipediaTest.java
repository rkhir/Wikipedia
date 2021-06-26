import static org.junit.Assert.fail;

import com.ui.test.TestUtils;
import com.ui.test.pages.WikiMetis;
import com.ui.test.pages.WikiNike;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WikipediaTest extends BaseTest {
  WikiMetis chWikiMetis;
  WikiNike chWikiNike;

  @Before
  public void pageSetup() {
    chWikiMetis = new WikiMetis(chPage, TestUtils.METIS_PAGE_URL);
    chWikiNike = new WikiNike(chPage, TestUtils.NIKE_PAGE_URL);
    chWikiMetis.navigate();
  }

  @Test
  public void validateContentHeadersH2Headers() {
    // get all elements in the content box
    HashSet<String> elementsTexts = chWikiMetis.getMetisContentBoxElementsTexts();
    // get all H2 headers in the page
    Set<String> pageH2 = this.chWikiMetis.getMetisH2Text();
    Assert.assertTrue(
        "Not All Content Box Elements are H2 Headers ", pageH2.containsAll(elementsTexts));
  }

  @Test
  public void validateContextBoxLinks() {
    try {
      chWikiMetis.clickMetisContentBoxLinks();
    } catch (Exception e) {
      System.out.println(e.getCause());
      System.out.println(e.getStackTrace());
      chWikiMetis.screenshot();
      fail("One or more Element(s) didn't have a valid link");
    }
  }

   @Test
  public void validateNikePopup() {
    String expectedString =
        "In ancient Greek civilization, Nike was a goddess who personified victory. Her Roman equivalent was Victoria.";
    String nikeString = "";
    try {
      nikeString = chWikiMetis.nikePopup();

    } catch (InterruptedException e) {
      chWikiMetis.screenshot();
      fail();
    }
    Assert.assertEquals("Nike popup paragraph content is incorrect", expectedString, nikeString);
  }

  @Test
  public void validateNikeFamilyTree() {
    chWikiMetis.navigateToNiki();
    Assert.assertTrue(chWikiNike.isFamilyTreeVisible());
  }
}
