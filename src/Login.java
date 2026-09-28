import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    public WebDriver run() {

        // Launch Chrome browser
        ChromeDriver driver = new ChromeDriver();

        // Block ad network requests before opening the site
        AdBlocker.blockAdRequests(driver);

        // Open the Website
        driver.get("https://www.automationexercise.com/");

        // Maximize the Window
        driver.manage().window().maximize();

        // Navigate to Login page
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")).click();

        // Click on the Email field and fill up email
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]")).sendKeys(TestData.email);

        // Click on the Password field and fill up password
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]")).sendKeys(TestData.password);

        // Click on the Login button
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button")).click();

        // Check the Login is Successful or Failed
        boolean isLoggedIn = !driver.findElements(By.xpath("//*[@id='header']/div/div/div/div[2]/div/ul/li[10]/a")).isEmpty();

        if (isLoggedIn) {
            System.out.println("Login PASSED: Logged in as " + TestData.name);
        } else {
            System.out.println("Login FAILED: User is not logged in");
        }

        return driver;
    }
}
