import org.openqa.selenium.WebDriver;

public class Main {
    public static void main(String[] args) {
        new Registration().run();
        WebDriver driver = new Login().run();
        new Checkout().run(driver);
    }
}
