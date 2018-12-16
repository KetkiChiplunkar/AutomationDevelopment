package com.test.Initialization;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class ParallelExcecution {

//public static WebDriver driver;
	//private WebDriver driver;
	
	private RemoteWebDriver driver;
	
	/*
	 * Set the Thread local for parallel execution as Webdriver is not thread safe. WebDriver should not be static
	 */

	//private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>(); 
	/**
	 * 
	 * When working with grid we need to give remote webdriver instead of WebDriver in ThreadLocal
	 * @return
	 */
	
	private static ThreadLocal<RemoteWebDriver> threadLocal = new ThreadLocal<RemoteWebDriver>();
	
	public WebDriver getWebDriver(){
		
		return threadLocal.get();
	}
	
	public void setWebDriver(WebDriver wd){
		threadLocal.set(driver);
	}
	
	public void startBrowser(String browserName, String url){
		/**
		 * Following code is for parallel Execution 
		 */
//		if (browserName.equalsIgnoreCase("chrome")){
//		
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver.exe");
//		driver= new ChromeDriver();
//	}
//	else if(browserName.equalsIgnoreCase("fireFox")){
//	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\geckodriver.exe");	
//	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//	capabilities.setCapability("marionette",true);
//	driver = new FirefoxDriver();
//	}
//	else if(browserName.equalsIgnoreCase("ie")){
//		System.out.println("Internet Explorer is selected");
//		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
//		}
//	setWebDriver(driver);
//	driver.get(url);
//	driver.manage().window().maximize();
//	

	/**
	 * To Run Grid and Hub we need to set Desired capabilities to the driver. Following code is for the execution of 
	 * Grid and Hub configuration
	 */
	DesiredCapabilities cap = null;
	if(browserName.equalsIgnoreCase("fireFox")){
		cap= DesiredCapabilities.firefox();
		cap.setBrowserName("firefox");
		cap.setPlatform(Platform.ANY);
		
	}else if (browserName.equalsIgnoreCase("chrome")){
		cap= DesiredCapabilities.chrome();
	cap.setBrowserName("chrome");
	cap.setPlatform(Platform.ANY);
	
	} else if (browserName.equalsIgnoreCase("ie")){
		cap= DesiredCapabilities.internetExplorer();
	cap.setBrowserName("iexplore");
	cap.setPlatform(Platform.WIN8);
	}
	try {
		driver = new RemoteWebDriver(new URL("http://localhost:5786/wd/hub"), cap);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		setWebDriver(driver);
		driver.get(url);
		driver.manage().window().maximize();
	
	
	}

public void tearDown(){
	
	//driver.quit();
	driver.close();
}







}
