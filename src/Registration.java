import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Registration {
    public void run() {
        ChromeDriver driver = new ChromeDriver(); // Launch Chrome browser
        AdBlocker.blockAdRequests(driver);   // block ads before opening the site
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait Until Element Visible

        // Open the Website
        driver.get("https://www.automationexercise.com/");

        // Maximize the Window
        driver.manage().window().maximize();

        // Navigate to Signup/Login page
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")).click();

        // Click on the Name field and fill up Name
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]")).sendKeys(TestData.name);

        // Click on the Email field and fill up Email
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).sendKeys(TestData.email);

        // Click on the Signup button
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();

        // Account information
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1"))).click();
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestData.password);
        // For Select Element
        new Select(driver.findElement(By.xpath("//*[@id=\"days\"]"))).selectByValue(TestData.birthDay);
        new Select(driver.findElement(By.xpath("//*[@id=\"months\"]"))).selectByVisibleText(TestData.birthMonth);
        new Select(driver.findElement(By.xpath("//*[@id=\"years\"]"))).selectByValue(TestData.birthYear);

        // Address information
        driver.findElement(By.xpath("//*[@id=\"first_name\"]")).sendKeys(TestData.firstName);
        driver.findElement(By.xpath("//*[@id=\"last_name\"]")).sendKeys(TestData.lastName);
        driver.findElement(By.xpath("//*[@id=\"company\"]")).sendKeys(TestData.company);
        driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys(TestData.address);
        new Select(driver.findElement(By.xpath("//*[@id=\"country\"]"))).selectByVisibleText(TestData.country);
        driver.findElement(By.xpath("//*[@id=\"state\"]")).sendKeys(TestData.state);
        driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys(TestData.city);
        driver.findElement(By.xpath("//*[@id=\"zipcode\"]")).sendKeys(TestData.zipcode);
        driver.findElement(By.xpath("//*[@id=\"mobile_number\"]")).sendKeys(TestData.mobile);
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/form/button")).click();

        // Click Continue
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();

        // Wait until page load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a")));


        // Check the Registration is Successful or Failed
        boolean isLoggedIn = !driver.findElements(By.xpath("//*[@id='header']/div/div/div/div[2]/div/ul/li[10]/a")).isEmpty();

        if (isLoggedIn) {
            System.out.println("Registration PASSED: Logged in as " + TestData.name);
        } else {
            System.out.println("Registration FAILED: User is not logged in");
        }


        // Quit Chrome
        driver.quit();
    }
}
