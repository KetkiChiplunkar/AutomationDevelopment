package com.test.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.Initialization.ParallelExcecution;

public class parallelExecutionBrowserTest extends ParallelExcecution {

	@Test(dataProvider = "Authentication")
	public void openBrowser(String username, String password)throws Exception {
		//setWebDriver(driver);
		startBrowser("chrome", "https://www.gmail.com/");
		getWebDriver().findElement(By.xpath("//input[@type='email']")).sendKeys(
				username);
		getWebDriver().findElement(By.xpath("//span[contains(.,'Next')]")).click();
		WebDriverWait wait = new WebDriverWait(getWebDriver(), 20);
		WebElement password1 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@name='password']")));
		password1.sendKeys(password);
		getWebDriver().findElement(By.xpath("//span[contains(.,'Next')]")).click();
		Thread.sleep(1000);
		WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//span[@class='gb_cb gbii']")));
		logo.click();
		getWebDriver().findElement(By.xpath("//a[contains(text(),'Sign out')]"))
				.click();
		tearDown();
	}

	@Test(dataProvider = "Authentication")
	public void openBrowser1(String username, String password)throws Exception {
		startBrowser(
				"chrome",
				"https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		getWebDriver().findElement(By.xpath("//input[@type='email']")).sendKeys(
				username);
		getWebDriver().findElement(By.xpath("//span[contains(.,'Next')]")).click();
		WebDriverWait wait = new WebDriverWait(getWebDriver(), 20);
		WebElement password1 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@name='password']")));
		password1.sendKeys(password);
		Thread.sleep(1000);
		getWebDriver().findElement(By.xpath("//span[contains(.,'Next')]")).click();
		WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//span[@class='gb_cb gbii']")));
		logo.click();
		getWebDriver().findElement(By.xpath("//a[contains(text(),'Sign out')]"))
				.click();
		tearDown();
	}

	@DataProvider(name = "Authentication", parallel = true)
	public static Object[][] credentials() {

		return new Object[][] { { "chiplunkar.k1", "ket190785" } };

	}

}