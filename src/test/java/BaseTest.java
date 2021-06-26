import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.ui.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseTest {
  static Playwright playwright;
  static Browser chromeBrowser;
  protected Page chromePage;
  protected Page chPage;
  protected BrowserContext chContext;
  protected static TestUtils testUtils = new TestUtils();

  @BeforeClass
  public static void setUpBrowser() {
    playwright = Playwright.create();
    chromeBrowser = TestUtils.getChromeBrowser(playwright);
  }

  @Before
  public void setup() {
    chContext = testUtils.getContext(chromeBrowser);
    chromePage = testUtils.getPage(chContext);
    chPage = testUtils.getPage(chContext);
  }

  @After
  public void teardown() {
    testUtils.closeContext(chContext);
  }

  @AfterClass
  public static void closeBrowser() {
    playwright.close();
  }
}
