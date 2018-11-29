import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Proba {
	private static WebDriver driver;
	private static String baseUrl;

	private static StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://secure.echosignpreview.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//blablabla
	}

	@Test
	public static void main() throws Exception {
		//glavna metoda
		driver.get(baseUrl + "/public/login?locale=en_US");
		driver.findElement(By.id("userEmail")).clear();
		driver.findElement(By.id("userEmail")).sendKeys("echosign.marko@gmail.com");
		driver.findElement(By.id("userPassword")).clear();
		driver.findElement(By.id("userPassword")).sendKeys("Adobe123!@#");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.linkText("Send")).click();
		driver.findElement(By.cssSelector("input.recipient-email")).clear();
		driver.findElement(By.cssSelector("input.recipient-email")).sendKeys("echosign.nenad@gmail.com");
		driver.findElement(By.id("add-files")).click();
		driver.findElement(By.id("selectFiles-view12-tab-0")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();
		driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[17]")).click();
		driver.findElement(By.linkText("Manage")).click();
		try {
			assertTrue(isElementPresent(By.cssSelector("#1-OUT_FOR_SIGNATURE-header-div > div")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(driver
					.findElement(By.cssSelector(
							"#1-CBJCHBCAABAAX7xfWZBEPvEMXGk7nzVEoA6t_z-uWk5q-agreementrow > div.agreement-info > div.name"))
					.getText(), "Nenad Biocanin");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		// Dokument je poslat, na redu je potpisivanje
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {

			return false;
		}
	}

	// private boolean isAlertPresent() {
	// try {
	// driver.switchTo().alert();
	// return true;
	// } catch (NoAlertPresentException e) {
	// return false;
	// }
	// }
	//
	// private String closeAlertAndGetItsText() {
	// try {
	// Alert alert = driver.switchTo().alert();
	// String alertText = alert.getText();
	// if (acceptNextAlert) {
	// alert.accept();
	// } else {
	// alert.dismiss();
	// }
	// return alertText;
	// } finally {
	// acceptNextAlert = true;
	// }
	// }
}
