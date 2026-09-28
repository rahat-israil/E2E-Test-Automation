import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AdBlocker {

    // Block ad network requests before they load (call once after creating the driver)
    public static void blockAdRequests(ChromeDriver driver) {
        driver.executeCdpCommand("Network.enable", new HashMap<>());

        Map<String, Object> params = new HashMap<>();
        params.put("urls", Arrays.asList(
                "*googlesyndication.com*",
                "*doubleclick.net*",
                "*googleadservices.com*",
                "*adservice.google.com*",
                "*pagead2.googlesyndication.com*"
        ));
        driver.executeCdpCommand("Network.setBlockedURLs", params);
    }
}