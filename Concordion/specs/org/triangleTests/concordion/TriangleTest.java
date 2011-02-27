package org.triangleTests.concordion;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@RunWith(ConcordionRunner.class)
public class TriangleTest {

	private static WebDriver driver;
	private TrianglePage page;

	@BeforeClass
	public static void suiteSetUp() {
		driver = firefoxDriver();
		driver.get("http://practice.agilistry.com/triangle/");
	}

	private static WebDriver firefoxDriver() {
		FirefoxDriver firefoxDriver = new FirefoxDriver();
		return firefoxDriver;
	}

	@Before
	public void setUp() {
		page = PageFactory.initElements(driver, TrianglePage.class);
	}

	@AfterClass
	public static void suiteTearDown() {
		driver.close();
	}

	public String triangleFor(String side1, String side2, String side3)
			throws Exception {
		page.enterSideLengths(side1, side2, side3);
		return page.triangleType();
	}

	public static class TrianglePage {

		@FindBy(id = "triangle_side1")
		private WebElement side1Element;

		@FindBy(id = "triangle_side2")
		private WebElement side2Element;

		@FindBy(id = "triangle_side3")
		private WebElement side3Element;

		@FindBy(id = "triangle_type")
		private WebElement triangleType;

		private WebDriver driver;

		public TrianglePage(WebDriver driver) {
			this.driver = driver;
		}

		public void enterSideLengths(String side1length, String side2length, String side3length)
				throws Exception {
			enterValueToElement(side1Element, side1length);
			enterValueToElement(side2Element, side2length);
			enterValueToElement(side3Element, side3length);
			waitUntilNewResultAppears(side1length, side2length, side3length);
		}

		private void waitUntilNewResultAppears(String side1, String side2,
				String side3) throws Exception {
			long pollIntervall = 500;
			int timeout = 30;

			String xpath = String.format("//div[contains(@class, 'triangle_row') and div[1] = '%s' and div[2] = '%s' and div[3] = '%s']", side1, side2, side3);

			int foundElements = driver.findElements(By.xpath(xpath)).size();
			while (timeout > 0 && foundElements < 1) {
				Thread.sleep(pollIntervall);
				timeout--;
				foundElements = driver.findElements(By.xpath(xpath)).size();
			}
		}

		private void enterValueToElement(WebElement element, String value) {
			element.clear();
			element.sendKeys(value, Keys.ENTER);
		}

		public String triangleType() {
			return triangleType.getText();
		}
	}
}
