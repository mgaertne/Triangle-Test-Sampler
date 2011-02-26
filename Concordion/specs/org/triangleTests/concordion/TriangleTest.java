package org.triangleTests.concordion;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
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
	
	public String triangleFor(String side1, String side2, String side3) throws Exception {
		page.enterSideLengths(side1, side2, side3);
		return page.triangleType();
	}
	
	public static class TrianglePage {
		
		@FindBy(id = "triangle_side1")
		private WebElement side1;
		
		@FindBy(id = "triangle_side2")
		private WebElement side2;
		
		@FindBy(id = "triangle_side3")
		private WebElement side3;
		
		@FindBy(id = "triangle_type")
		private WebElement triangleType;

		private WebDriver driver;
		
		public TrianglePage(WebDriver driver) {
			this.driver = driver;
		}
		
		public void enterSideLengths(String side1, String side2, String side3) throws Exception {
			enterValueToElement(this.side1, side1);
			enterValueToElement(this.side2, side2);
			enterValueToElement(this.side3, side3);
		}

		private void enterValueToElement(WebElement element, String value) {
			element.clear();
			element.sendKeys(value);
			element.submit();
		}

		public String triangleType() {
			return triangleType.getText();
		}
	}
}
