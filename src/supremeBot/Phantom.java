/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeBot;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import java.util.concurrent.ThreadLocalRandom;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Function;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

public class Phantom {

    File srcFile;
    JavascriptExecutor js;
    WebDriver driver;

    public void quit() {
        driver.quit();
    }

    public void runPhantom(String category, By path, String sizeWanted, Account person) throws InterruptedException, IOException, ParseException {

        String item = person.getItem();
        String color = person.getColor();

        person.setStatus("Started");

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs();

        desiredCapabilities.setCapability(
                PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[]{"--load-images=no"});

        driver = new PhantomJSDriver();
        //driver = new ChromeDriver();
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        }

//        while(!driver.getCurrentUrl().equals("http://www.supremenewyork.com/")){
//                person.setStatus("Cookies");
//                driver.navigate().refresh();
//                driver.get("http://www.supremenewyork.com/");        
//                Thread.sleep(1000);
//        }
        //driver.get("http://www.supremenewyork.com/shop");
        
        //setCookie(constructCookieValue(person));
        

        driver.get(category);

        int i = 0;
        String[] test = new String[3];
        test[0] = ".";
        test[1] = "..";
        test[2] = "...";

        person.setStatus("Waiting for notification");
        PhantomFXML.sem.acquire();
       
        int count =0;
        

        while (!isElementExists(path)&&PhantomFXML.sem.availablePermits()<2) {
       // while (count<10&&PhantomFXML.sem.availablePermits()<2) {
           person.setStatus("Waiting for Update" + test[i % 3]);
           
           if (!driver.getCurrentUrl().equals(category)) 
               driver.get(category);
           

           Thread.sleep(ThreadLocalRandom.current().nextInt(200, 800));
           driver.navigate().refresh();
           i++;
           count++;
        }
        
       PhantomFXML.sem.release(5);

       
                
        
        WebElement link = driver.findElement(path);
        driver.get(link.getAttribute("href"));
        WebElement submitBtn;
       long tStart = System.currentTimeMillis();

        if (!sizeWanted.equalsIgnoreCase("Free")) {

            try {
                Select size = new Select(waitElement((By.id("size"))));
                size.selectByVisibleText(sizeWanted);
     
            } catch (NoSuchElementException e) {
                person.setStatus("Size is S/O");
                driver.quit();
                return;
            }
        }
        
        
         try {
           submitBtn = driver.findElement(By.xpath("//*[@id=\"add-remove-buttons\"]/input"));
        } catch (NoSuchElementException e) {
            person.setStatus("S/O");
            driver.quit();
            return;
        }

        submitBtn.submit();
        

        
        person.setStatus("Added to cart");

//        long tStart = System.currentTimeMillis();
//        long tEnd = tStart;

        long  tEnd = System.currentTimeMillis();
        while (!isElementExists(By.linkText("checkout now"))) {

            if (((tEnd - tStart) / 1000.0) > 5) {
                tStart = System.currentTimeMillis();
                driver.navigate().refresh();
                person.setStatus("Retrying");
                if (isElementExists(By.xpath("//*[@id=\"add-remove-buttons\"]/input"))) {

                    if (!sizeWanted.equalsIgnoreCase("Free")) {

                        Select size = new Select(waitElement(By.id("size")));

                        try {
                            size.selectByVisibleText(sizeWanted);
                        } catch (NoSuchElementException e) {
                            person.setStatus("Size is S/O");

                            return;
                        }
                    }
                    driver.findElement(By.xpath("//*[@id=\"add-remove-buttons\"]/input")).submit();
                }
            }

            tEnd = System.currentTimeMillis();
            Thread.sleep(200);
        }
       
        
        driver.get("https://www.supremenewyork.com/checkout");
//        tEnd = System.currentTimeMillis();
//        long tDelta = tEnd - tStart;
//        double elapsedSeconds = tDelta / 1000.0;
//
//        System.out.println(elapsedSeconds);

        person.setStatus("Filling Form");
        
//        srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//        FileUtils.copyFile(srcFile, new File(new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(Calendar.getInstance().getTime()) + "item.png"));
        // WebElement credit = waitElement(driver, By.name("credit_card[cnb]"));
        // setAttribute(credit, person.getCredit());
        long ttStart = System.currentTimeMillis();
        
        Thread.sleep(1200);
        
        
        //waitElement(By.id("order_billing_name"));
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.id("order_billing_name")), person.getName());
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.id("order_email")), person.getEmail());
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.id("order_tel")), person.getPhone());
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.id("bo")), person.getAddress1());
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.id("order_billing_zip")), person.getZip());
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.id("order_billing_city")),person.getCity());        
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.id("cnb")), person.getCredit());
        js.executeScript("document.getElementById(arguments[0]).value=arguments[1];", "order_billing_state", person.getState());
        // waitElement(driver, By.id("credit_card_month"));
        // selectAttribute("credit_card_month", person.getCreditmonth());
        js.executeScript("document.getElementById(arguments[0]).value=arguments[1];", "credit_card_month", person.getCreditmonth());

        //  waitElement(driver, By.id("credit_card_year"));
        // selectAttribute("credit_card_year", person.getCredityear());  
        js.executeScript("document.getElementById(arguments[0]).value=arguments[1];", "credit_card_year", person.getCredityear());

        // WebElement cvv = waitElement(driver, By.name("credit_card[vval]"));
        //  setAttribute(cvv, person.getCvv());
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.id("vval")), person.getCvv());

        driver.findElement(By.xpath("//*[@id=\"cart-cc\"]/fieldset/p[2]/label/div/ins")).click();

//        srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(srcFile, new File(new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(Calendar.getInstance().getTime()) + person.getName() + " info.png"));
        js.executeScript(" document.getElementsByClassName(\"g-recaptcha\")[0].remove()");

        waitElement(By.xpath("//*[@id=\"pay\"]/input")).submit();

        tEnd = System.currentTimeMillis();
        long tDelta = tEnd - ttStart;
        double elapsedSeconds = tDelta / 1000.0;

        System.out.println(elapsedSeconds);

        person.setStatus("Waiting for Confirmation");

        TimeUnit.SECONDS.sleep(5);

        srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(srcFile, new File(new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(Calendar.getInstance().getTime()) + person.getName() + ThreadLocalRandom.current().nextInt(1, 100) + "confirmation.png"));

    
        
         try {
          WebElement t = driver.findElement(By.xpath("//*[@id=\"confirmation\"]"));
          
            if (t.getText().startsWith("Unfortunately")) {
                person.setStatus("Unfortunately");
            } else {
                person.setStatus("Succeeded");
            }
          
          
          
        } catch (NoSuchElementException e) {
            person.setStatus("S/O");
            driver.quit();
            return;
        }
         
         
        driver.quit();
    }

    public boolean isFinished(By by) {

        boolean isExists = true;

        try {

            driver.findElement(by);

        } catch (NoSuchElementException e) {

            isExists = false;
        }
        return isExists;
    }

    public boolean isElementExists(By by) {
        boolean isExists = true;
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {

            isExists = false;
        }
        return isExists;
    }

    private WebElement waitElement(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(locator);
            }
        });

    }

    public void setCookie(String value) {
        js.executeScript("document.cookie = \"address= " + value + ",domain = 'www.supremenewyork.com',path='/',expires= '2017-12-13T03:48:20.712Z';\"");
    }

    public void setAttribute(WebElement element, String value) {
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", element, value);
    }

    public void selectAttribute(String target, String value) {
        js.executeScript("document.getElementById(arguments[0]).value=arguments[1];", target, value);
    }

    private String constructCookieValue(Account person) {
        //Michael+Chen%7C6425+Bell+Boluevard%7C%7COakland+Gardens%7CNY%7C11364%7CUSA%7Cmichael71034%40aol.com%7C7187102401
        String name = person.getName().replace(' ', '+');
        String addr = person.getAddress1().replace(' ', '+');
        String city = person.getCity().replace(' ', '+');
        String email = person.getEmail().replace("+", "%2B");

        String result = name + "%7C" + addr + "%7C%7C" + city + "%7C" + person.getCity() + "%7C" + person.getZip() + "%7CUSA%7C" + email + "%7C" + person.getPhone();

        //System.out.println(result);
        return result;

    }

}
