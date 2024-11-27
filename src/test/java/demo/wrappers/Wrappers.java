package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bsh.commands.dir;

import java.text.NumberFormat;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    public static void enterText(WebDriver driver, By locator, String text){
        Boolean result;
        try{
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            WebElement inputBox=driver.findElement(locator);
            inputBox.clear();
            inputBox.sendKeys(text);
            inputBox.sendKeys(Keys.ENTER);
            result=true;
        }catch(Exception e){
            System.out.println("Exception! "+e.getMessage());
            result=false;
        }
    }
    
    public static void clickOnElement(WebDriver driver, By locator){
        Boolean result;
        try{
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            WebElement clickableElement=driver.findElement(locator);
            clickableElement.click();
            result=true;
        }catch(Exception e){
            System.out.println("Exception! "+e.getMessage());
            result=false;
        }
    }

    public static boolean searchStarRatingAndPrintCount(WebDriver driver, By locator, double starRating){
        Boolean result;
        int WashingMachineCount=0;
        try{
            Thread.sleep(3000);
           List<WebElement> starRatingElements=driver.findElements(locator);
           for(WebElement starRatingElement:starRatingElements){
            if(Double.parseDouble(starRatingElement.getText()) <= starRating){
                WashingMachineCount++;
            }
           }
            System.out.println("Washing Machine count less than or equal to the given rating: "+WashingMachineCount);
            result=true;
        }catch(Exception e){
            System.out.println("Exception! "+e.getMessage());
            result=false;
        }
        return result;
    }
    public static Boolean iPhoneTitleAndDiscount(WebDriver driver, By locator, int discount){
        boolean result;
        try{
            Thread.sleep(3000);
            List<WebElement> iphoneTile=driver.findElements(locator);
            HashMap<String, String> iPhoneTitleDiscountMap= new HashMap<>();
            for(WebElement iphoneEachTile: iphoneTile){
                String discountPercent=iphoneEachTile.findElement(By.xpath(".//div[@class='UkUFwK']/span")).getText();
                int discountValue=Integer.parseInt(discountPercent.replaceAll("[^\\d]", ""));
                if(discountValue > discount){
                    String iphoneTitle=iphoneEachTile.findElement(By.xpath(".//div[contains(@class,'KzDlHZ')]")).getText();
                    iPhoneTitleDiscountMap.put(discountPercent, iphoneTitle);
                }
            }
            for(Map.Entry<String, String> iphoneTitleDiscountEachMap: iPhoneTitleDiscountMap.entrySet()){
                System.out.println("Title of the iPhone: "+iphoneTitleDiscountEachMap.getValue()+"Discount of the iPhone is: "+iphoneTitleDiscountEachMap.getKey());
            }
            result=true;
        }catch(Exception e){
            System.out.println("Exception! "+e.getMessage());
            result=false;
        }
        return result;
    }

    public static Boolean coffeeMugTitleAndImageURL(WebDriver driver, By locator){
        Boolean result;
        try{
            List<WebElement> userReviewList=driver.findElements(locator);
            Set<Integer> userReviewSet=new HashSet<>();
            for(WebElement userReviewElement: userReviewList){
                int userReview=Integer.parseInt(userReviewElement.getText().replaceAll("[^\\d]", ""));
                userReviewSet.add(userReview);
            }
            List<Integer> userReviewCountList=new ArrayList<>(userReviewSet);
            Collections.sort(userReviewCountList, Collections.reverseOrder());
            System.out.println("Sorted user review list: "+userReviewCountList);
            NumberFormat numberformat=NumberFormat.getInstance(Locale.US);
            LinkedHashMap<String, String> productDetailsMap=new LinkedHashMap<>();
            for(int i=0;i<5;i++){
                String FormattedUserReviewCount="("+numberformat.format(userReviewCountList.get(i))+")";
                String productTitle=driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'"+FormattedUserReviewCount+"')]/../../a[@class='wjcEIp']")).getText();
                String productImageURL=driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'"+FormattedUserReviewCount+"')]/../..//img[@class='DByuf4']")).getAttribute("src");
                String highestReviewCountAndProductTitle=String.valueOf(i+1)+" highest review count: "+FormattedUserReviewCount+" Title: "+productTitle;
                productDetailsMap.put(productImageURL, highestReviewCountAndProductTitle);
            }
            for(Map.Entry<String, String> productDetails: productDetailsMap.entrySet()){
                System.out.println(productDetails.getValue()+" Product image URL: "+productDetails.getKey());
            }
            result=true;
        }catch(Exception e){
            System.out.println("Exception! "+e.getMessage());
            result=false;
        }
        return result;
    }

}
