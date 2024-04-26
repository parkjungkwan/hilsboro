//package philoarte.jaemin.api.crawling.main;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import java.io.*;
//import java.util.List;
//
//public class SeleniumCrawl {
//
//    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
//    public static final String WEB_DRIVER_PATH = "C:\\Users\\w\\Desktop\\Crawling\\chromedriver.exe"; //드라이버 경로
//    public static void main(String[] args) throws IOException {
//        String filePath = "C:\\Users\\w\\Desktop\\Crawling\\result.csv";
//        try {
//            System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //크롬 설정을 담은 객체 생성
//        ChromeOptions options = new ChromeOptions();
//        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
//        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
//        options.addArguments("headless");
//
//        //위에서 설정한 옵션은 담은 드라이버 객체 생성
//        //옵션을 설정하지 않았을 때에는 생략 가능하다.
//        //WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
//        WebDriver driver = new ChromeDriver(options);
//
//        //이동을 원하는 url
//        String url = "http://www.uneedjob.co.kr/talent/freelancer_list.do";
//
//        //WebDriver을 해당 url로 이동한다.
//        driver.get(url);
//
//        //브라우저 이동시 생기는 로드시간을 기다린다.
//        //HTTP응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//        }
//
//        //class="nav" 인 모든 태그를 가진 WebElement리스트를 받아온다.
//        //WebElement는 html의 태그를 가지는 클래스이다.
////        List<WebElement> el1 = driver.findElements(By.className("u_cbox_area"));
////
////        for (int i = 0; i < el1.size(); i++) {
////            //nav 클래스의 객체 중 "뉴스"라는 텍스트를 가진 WebElement를 클릭한다.
////            if(el1.get(i).getText().equals("")) {
////                el1.get(i).click();
////                break;
////            }
////        }
//
//        //1초 대기
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//        }
//
//        //버튼을 클릭했기 때문에 브라우저는 뉴스창으로 이동돼 있다.
//        //이동한 뉴스 창의 IT/과학 뉴스 헤드라인을 가져온다.
//
//        //iT/과학뉴스를 담은 div
////        WebElement el2 = driver.findElement(By.className("u_cbox_text_wrap"));
//
//        //div속에서 strong태그를 가진 모든 element를 받아온다.
//        List<WebElement> el3 = driver.findElements(By.cssSelector("tbody tr td"));
//        try{
//        BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "euc-kr"));
//        for (int i = 0; i < el3.size(); i++) {
//            //뉴스의 제목을 모두 출력한다.
//            fw.write(i + ",");
//            fw.newLine();
//            System.out.println("번 뉴스: " + el3.get(i).getText());
//        }
//        fw.flush();
//        fw.close();
//    }
////            for (int i = 0; i < elements.size(); i++) {
////                Funding funding = new Funding();
////                funding.setTitle(elements.get(i).text());
////                funding.setAddress(elements.get(i).attr("href"));
////                funding.setCategory(crawler.getCategory());
////                System.out.println(funding.toString());
////                list.add(funding);
////            }
//
////        try {
////            //드라이버가 null이 아니라면
////            if(driver != null) {
////                //드라이버 연결 종료
////                driver.close(); //드라이버 연결 해제
////
////                //프로세스 종료
////                driver.quit();
////            }
////        } catch (Exception e) {
////            throw new RuntimeException(e.getMessage());
////        }
//
//    }
//}
