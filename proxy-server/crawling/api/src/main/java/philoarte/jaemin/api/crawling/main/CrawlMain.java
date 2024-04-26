//package philoarte.jaemin.api.crawling.main;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import philoarte.jaemin.api.crawling.domain.Review;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
public class CrawlMain {
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
    public static final String WEB_DRIVER_PATH = "C:\\Users\\w\\Desktop\\philoarte\\workspace\\crawling\\chromedriver.exe"; //드라이버 경로

    public static void main(String[] args) throws IOException {

        try {
            System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);
        String url = "https://www.globing.co.kr/hash.html";
        driver.get(url);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        String filePath = "C:\\Users\\w\\Desktop\\philoarte\\workspace\\crawling\\hashtag.csv";
        List<Review> list = new ArrayList<>();
        List<WebElement> el1 = driver.findElements(By.cssSelector(".position-center"));
//        List<WebElement> el2 = driver.findElements(By.cssSelector(".u_cbox_contents"));


        try {
            DataOutputStream fw = new DataOutputStream(new FileOutputStream(filePath, true));

            for (int i = 0; i < el1.size(); i++) {
                Review review = new Review();
                review.setHashtag(el1.get(i).g());
//                review.setContent(el2.get(i).getText());


                System.out.println(review.getHashtag());
//                System.out.println(review.getContent());


                list.add(review);
            }
            if (list.isEmpty()) {
                System.out.println("크롤링 된 값이 없습니다. !");
            } else {
                for (Review f : list) {
                    byte[] arr = f.toString().getBytes("UTF-8");
                    fw.write(Integer.parseInt(arr +"/n"));

            }

            fw.flush();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


