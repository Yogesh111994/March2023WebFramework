package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	protected WebDriver driver;
	protected Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	
	public static String highlight=null;
	
	public WebDriver initDriver(Properties prop) {

		String browserName=prop.getProperty("browser");
		
		highlight=prop.getProperty("highlight");

		System.out.println("The browser name is : " + browserName);

		optionsManager= new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Please pass the right browser name: " + browserName);
			throw new FrameworkException("Wrong Browser Name");

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}
	
	public static  WebDriver getDriver() {
		return tlDriver.get();
	}
	

	public Properties initProp() {
		prop= new Properties();
		FileInputStream  fis=null;
		String envName=System.getProperty("env");
		System.out.println("Test running on :"+ envName);
		try {
			if(envName==null) {
				fis= new FileInputStream("./src/test/resources/config/config.properties");
				System.out.println("Test are running on default enviounment");
			}
			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					fis= new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "stage":
					fis= new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "dev":
					fis= new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "prod":
					fis= new FileInputStream("./src/test/resources/config/config.prod.properties");
					break;
				default:
					System.out.println("Please pass the right enviournment "+envName);
					throw new FrameworkException("Wrong Env Name");

				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
