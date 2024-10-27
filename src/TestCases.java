import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {
	WebDriver driver = new ChromeDriver();
	String theWebsite = "https://automationteststore.com/";
	String[] firstNames = { "ahmad", "ali", "omar", "ayat", "alla", "sawsan", "rama" };
	String[] lastNames = { "mohammad", "mustafa", "abdullah", "malek", "saleh", "akram", "zaid" };
	Random rand = new Random();
	String globalUserName = "";
	String globalUserNameforTheLogin = "";
	String password = "Ilovemymom123@";

	@BeforeTest
	public void setUp() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(theWebsite);
	}

	@Test(priority = 1)
	public void signUp() throws InterruptedException {
		int randomIndexForTheFirstName = rand.nextInt(firstNames.length);
		int randomIndexForTheLastName = rand.nextInt(lastNames.length);
		String userFirstName = firstNames[randomIndexForTheFirstName];
		String userLastName = lastNames[randomIndexForTheLastName];
		int randomNumberForTheEmail = rand.nextInt(564548);
		String domainName = "@gmail.com";
		String email = userFirstName + userLastName + randomNumberForTheEmail + domainName;
		driver.findElement(By.linkText("Login or register")).click();
		WebElement signUpButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		signUpButton.click();
		Thread.sleep(2000);
		WebElement firstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		firstNameInput.sendKeys(userFirstName);
		globalUserName = userFirstName;
		WebElement lastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		lastNameInput.sendKeys(userLastName);
		WebElement emailInput = driver.findElement(By.id("AccountFrm_email"));
		emailInput.sendKeys(email);
		WebElement addressInput = driver.findElement(By.id("AccountFrm_address_1"));
		addressInput.sendKeys("Amman");
		WebElement cityInput = driver.findElement(By.id("AccountFrm_city"));
		cityInput.sendKeys("Capital City");
		WebElement countryInput = driver.findElement(By.id("AccountFrm_country_id"));
		Select selector2 = new Select(countryInput);
		int randomCountry = rand.nextInt(1, 240);
		selector2.selectByIndex(randomCountry);
		Thread.sleep(3000);
		WebElement zoneIdInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector = new Select(zoneIdInput);
		int randomState = rand.nextInt(1, 5);
		selector.selectByIndex(randomState);
		WebElement postalCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		postalCodeInput.sendKeys("13310");
		WebElement loginNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		String localUserName = userFirstName + userLastName + randomNumberForTheEmail;
		loginNameInput.sendKeys(localUserName);
		globalUserNameforTheLogin = localUserName;
		WebElement passwordInput = driver.findElement(By.id("AccountFrm_password"));
		passwordInput.sendKeys(password);
		WebElement confirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		confirmPasswordInput.sendKeys(password);
		WebElement agreeCheckBox = driver.findElement(By.id("AccountFrm_agree"));
		agreeCheckBox.click();
		WebElement continueButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		continueButton.click();
	}

	@Test(priority = 2)
	public void logOut() throws InterruptedException {
//		Thread.sleep(5000);
//		String logOutUrl = "https://automationteststore.com/index.php?rt=account/logout";
//		driver.get(logOutUrl);
		Thread.sleep(2000);
		WebElement userNav = driver.findElement(By.id("customernav"));
		Actions action = new Actions(driver);
		action.moveToElement(userNav).perform();
		Thread.sleep(2000);
		WebElement logOutClick = driver.findElement(By.linkText("Not " + globalUserName + "? Logoff"));
		logOutClick.click();
	}

	@Test(priority = 3)
	public void logIn() {
		driver.findElement(By.linkText("Login or register")).click();
		WebElement loginInput = driver.findElement(By.id("loginFrm_loginname"));
		loginInput.sendKeys(globalUserNameforTheLogin);
		WebElement passwordInput = driver.findElement(By.id("loginFrm_password"));
		passwordInput.sendKeys(password);
		WebElement logIinButton = driver.findElement(By.xpath("//button[@title='Login']"));
		logIinButton.click();
	}
}
