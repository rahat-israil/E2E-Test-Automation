import org.openqa.selenium.WebDriver;

public class Main {
    public static void main(String[] args) {
        // Create a new user account
        new Registration().run(); 
       
        // Log in and keep the browser session open
        WebDriver driver = new Login().run();  

        // Reuse the same session to add products and place the order
        new Checkout().run(driver); 
    }
}
