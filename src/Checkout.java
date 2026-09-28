import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Checkout {

    public void run(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Navigate to the Products page
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a")).click();

        // Add the first product to the cart
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/a"))).click();

        // Close the "Added to cart" confirmation modal
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cartModal\"]/div/div/div[3]/button"))).click();

        // Add the second product to the cart
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[4]/div/div[1]/div[1]/a"))).click();

        // Close the confirmation modal again
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cartModal\"]/div/div/div[3]/button"))).click();

        // 4. Open the cart page
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a")).click();


        // 5. Click "Proceed To Checkout"
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a"))).click();

        // 6. Enter an order comment and click "Place Order"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ordermsg\"]/textarea"))).sendKeys(TestData.orderComment);
        driver.findElement(By.xpath("//*[@id=\"cart_items\"]/div/div[7]/a")).click();

        // 7. Fill in the payment details and submit
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"payment-form\"]/div[1]/div/input"))).sendKeys(TestData.cardName);
        driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[2]/div/input")).sendKeys(TestData.cardNumber);
        driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[3]/div[1]/input")).sendKeys(TestData.cvc);
        driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[3]/div[2]/input")).sendKeys(TestData.expiryMonth);
        driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[3]/div[3]/input")).sendKeys(TestData.expiryYear);
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();


        // 8. Verify that the order was placed successfully
        boolean isOrderPlaced = false;
        try {
            String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-qa='order-placed']"))).getText();
            isOrderPlaced = msg.equalsIgnoreCase("ORDER PLACED!");
        } catch (Exception e) {
            // Success message did not appear within the timeout
            isOrderPlaced = false;
        }

        if (isOrderPlaced) {
            System.out.println("Checkout PASSED: Order placed successfully");
        } else {
            System.out.println("Checkout FAILED: Order was not placed");
        }

        // Close the browser
        driver.quit();
    }
}