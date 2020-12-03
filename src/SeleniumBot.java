
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//creates new Selenium Bots
public class SeleniumBot {

	private ChromeDriver driver;
	private Thread t1;
	private boolean isActive;

	// user variables
	private File imageFile;
	private boolean isInUse = false;
	private boolean isDone = true;
	private ArrayList<String> linkTitles = new ArrayList<String>();
	private ArrayList<String> linkURLs = new ArrayList<String>();
	
	
	
	private String message = "";

	private JavascriptExecutor js;

	SeleniumBot() {
		isActive = true;
		// isAvailable = false;
		initializeBot();
	}

	public void runBot() {

		t1 = new Thread(() -> {
			if (!message.equals("")) {
				driver.get("https://www.google.com/");
				WebElement searchBar = driver.findElement(By.name("q"));
				searchBar.sendKeys(message);
				searchBar.sendKeys(Keys.ENTER);
//////////////////////////////////////////////////// Gets the Names of the different google links and main website on every other line -> Sometimes blank
				try {
					System.out.println(driver.findElementsByClassName("yuRUbf").size());
					for (int i = 0; i < driver.findElementsByClassName("yuRUbf").size(); i++) {
						this.linkTitles.add(driver.findElementsByClassName("yuRUbf").get(i).getText());
					}
				} catch (Exception e) {
					System.out.println("link title error");
				}
				
				try {
					for (int i = 0; i < driver.findElementsByClassName("yuRUbf").size(); i++) {
						this.linkURLs.add(driver.findElementsByClassName("yuRUbf").get(i).findElement(By.cssSelector("a")).getAttribute("href"));
					}
				}catch(Exception e) {
					System.out.println("href error");
				}
				
////////////////////////////////////////////////////

				try {
					this.imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				} catch (Exception e2) {
					System.out.println("Screenshot Error...");
				}
			}
			isDone = true;
			message = "";

		});
		t1.start();
	}

	public void setIsActiveTrue() {
		isActive = true;
	}

	public File getFileFile() {
		this.isInUse = false;
		File tempFile = this.imageFile;
		this.imageFile = null;
		return tempFile;
	}

	public ArrayList<String> getLinkTitles(){
		return this.linkTitles;
	}
	
	public ArrayList<String> getLinkURLs(){
		return this.linkURLs;
	}
	
	public void clearLinks(){
		linkTitles.clear();
		linkURLs.clear();
	}
	
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	public boolean isInUse() {
		return this.isInUse;
	}

	public boolean isDone() {
		return this.isDone;
	}

	public void setAndStart(String message) {
		this.isDone = false;
		this.isInUse = true;
		this.message = message;
		runBot();
	}

	public void quitBot() {
		isActive = false;
		driver.quit();
	}

	public void initializeBot() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications", "--headless"); // "--disable-notifications",
																		// "--start-maximized", "--headless"
		driver = new ChromeDriver(options);

		js = (JavascriptExecutor) driver;
		resizeWindow(driver, 1500, 1200);

		// set up bot for safe search

		driver.get("https://www.google.com/");
		WebElement searchBar = driver.findElement(By.name("q"));
		searchBar.sendKeys("google");
		searchBar.sendKeys(Keys.ENTER);
		// may have to change for different internet connections
		pauseSeconds(2);

		driver.findElementById("abar_button_opt").click();
		driver.findElementById("safesearch").click();

	}

	public void resizeWindow(WebDriver driver, int width, int height) {
		Dimension d = new Dimension(width, height);
		// Resize current window to the set dimension
		driver.manage().window().setSize(d);

	}

	public static void pauseSeconds(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void pauseMilliseconds(long milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}