package com.wc.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
//import org.openqa.selenium.support.events.WebDriverEventListener;

import com.wc.qa.util.TestUtil;
import com.wc.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties proprts;
	public static EventFiringWebDriver e_driver;
	//public static WebDriverEventListener eventListener;
	
	public static WebEventListener eventListener;
	
	
	public TestBase() {
		try {
			proprts = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "src/main/java/com/ap/qa/config/config.properties");
			                                                                          // change the double forward slash to single backword slash
			proprts.load(ip);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void initialization() {
		String browserName = proprts.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "src/main/resources/drive/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+ "src/main/resources/drive/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(proprts.getProperty("url"));
		
	}



}
