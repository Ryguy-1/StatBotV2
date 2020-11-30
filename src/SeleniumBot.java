
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sun.jna.FromNativeContext;

//creates new Selenium Monitoring Bots. Has a member variable for which site it is monitoring.
public class SeleniumBot {

	private ChromeDriver driver;
	private Thread t1;
	private boolean isActive;

	// user variables
	private File imageFile;
	private boolean isInUse = false;
	private boolean isDone = true;

	private String message = "";
	
	private JavascriptExecutor js;

	
	SeleniumBot(){
		isActive = true;
		// isAvailable = false;
		initializeBot();
		runBot();
	}
	
	public void runBot() {
			t1 = new Thread(() -> {
				while (true) {

					if (!message.equals("")) {
						
						driver.get("https://www.google.com/");
						WebElement searchBar = driver.findElement(By.name("q"));
						searchBar.sendKeys(message);
						searchBar.sendKeys(Keys.ENTER);
						pause(2);

						try {
							this.imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
						} catch (Exception e2) {
							System.out.println("image retrieval error");
						}
					}
					isDone = true;
					message = "";
					pause(1);
				}
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
	}

	public void quitBot() {
		isActive = false;
		driver.quit();
	}

	public void initializeBot() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications", "--headless"); // "--disable-notifications", "--start-maximized", "--headless"
		driver = new ChromeDriver(options);

		js = (JavascriptExecutor) driver;
		resizeWindow(driver, 1500, 1200);
		
		
		
		//setup bot for safe search
		
		driver.get("https://www.google.com/");
		WebElement searchBar = driver.findElement(By.name("q"));
		searchBar.sendKeys("google");
		searchBar.sendKeys(Keys.ENTER);
		pause(2);
		
		driver.findElementById("abar_button_opt").click();
		driver.findElementById("safesearch").click();
		

	}

	public void resizeWindow(WebDriver driver, int width, int height) {
		Dimension d = new Dimension(width, height);
		// Resize current window to the set dimension
		driver.manage().window().setSize(d);

	}

	public static void pause(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}