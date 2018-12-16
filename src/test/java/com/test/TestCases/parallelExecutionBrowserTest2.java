package com.test.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.Initialization.ParallelExcecution;

public class parallelExecutionBrowserTest2 extends ParallelExcecution {

	@Test(dataProvider = "Authentication2")
	public void openBrowser(String username, String password) {
		startBrowser("firefox", "https://www.gmail.com/");
		getWebDriver().findElement(By.xpath("//input[@type='email']")).sendKeys(
				username);
		getWebDriver().findElement(By.xpath("//span[contains(.,'Next')]")).click();
		WebDriverWait wait = new WebDriverWait(getWebDriver(), 20);
		WebElement password1 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@name='password']")));
		password1.sendKeys(password);
		getWebDriver().findElement(By.xpath("//span[contains(.,'Next')]")).click();
		WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//span[@class='gb_cb gbii']")));
		logo.click();
		getWebDriver().findElement(By.xpath("//a[contains(text(),'Sign out')]"))
				.click();
		tearDown();
	}

	@DataProvider(name = "Authentication2", parallel = true)
	public static Object[][] credentials() {

		return new Object[][] { { "ketki.palherkar", "K*" } };

	}

}
