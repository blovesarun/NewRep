package SeleniumLearning;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class saucedemo {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/b.kumar.senapati/Documents/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.saucedemo.com/");
		
		driver.findElement(By.xpath("//*[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//*[@id='login-button']")).click();
		
		/*String text=driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
		System.out.println(text);
		String text1= text.substring(1);
		System.out.println(text1);
		double price=Double.parseDouble(text1);
		System.out.println(price);*/
		boolean homepage1=driver.findElement(By.xpath("//*[text()='Products']")).isDisplayed();
		if (homepage1==true) {
			System.out.println("In Product homepage now");
			Thread.sleep(5000);
		}
		double max=0.00;
		int num=0;
		//String priceofprodmaxText="";
		List<WebElement> prodlist=driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		List<WebElement> AddTocart=driver.findElements(By.xpath("//div[@class='pricebar']/button[text()='Add to cart']"));
		//List<WebElement> cart1=driver.findElements(By.xpath("//div[@class='pricebar']/button"));
		int totalproduct=prodlist.size();
		System.out.println("total number of products:  "+totalproduct);
		
		for(int i=0;i<prodlist.size();i++) {
			String PriceText=prodlist.get(i).getText().substring(1);
			double actualprice= Double.parseDouble(PriceText);
			//System.out.println(max);
			if(actualprice>max) {
				max=actualprice;
				num=i;
					}
			
		}
		String priceofprodmaxText=prodlist.get(num).getText().substring(1);
		System.out.println("max price: "+priceofprodmaxText);
		WebElement element1=AddTocart.get(num);
		element1.click();
		int m=0;
		List<WebElement> removebutton=driver.findElements(By.xpath("//button[contains(text(),'Remove')]/parent::div/div[@class='inventory_item_price']"));
		for(int i=0;i<removebutton.size();i++) {
			String text2=removebutton.get(i).getText();
			if(text2.contains(priceofprodmaxText)) {
				System.out.println("clicked on the add to cart for product having max price");
			m=i;
				
			}
			//checks if after clicking on add to cart , remove button is enabled or not
		}
		List<WebElement> ProductsAdded=driver.findElements(By.xpath("//button[contains(text(),'Remove')]/parent::div/parent::div/div[1]/a/div"));
		
		String ProductName=ProductsAdded.get(m).getText();
		driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a")).click();
		//finds the product name for which remove button is enabled
		
		String ProdNameCheckInCart=driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
		if(ProdNameCheckInCart.equals(ProductName)) {
			System.out.println("correct product added in the cart : "+ProdNameCheckInCart);
		}
		
		
		
		
		//button[contains(text(),'Remove')]/parent::div/parent::div/a/div[contains(text(),'"+ProdNameCheckInCart+"')]
if(driver.findElement(By.xpath("//button[contains(text(),'Remove')]/parent::div/parent::div/a/div[contains(text(),'"+ProdNameCheckInCart+"')]")).isDisplayed()==true){
	
	Thread.sleep(2000);
	//div[contains(text(),'Sauce Labs Fleece Jacket')]/parent::a/parent::div/div[2]/child::button
	//div[contains(text(),'"+ProdNameCheckInCart+"')]/parent::a/parent::div/div[2]/child::button
	//driver.findElement(By.xpath("//button[contains(text(),'Remove')]/parent::div/parent::div/a/div[contains(text(),'"+ProdNameCheckInCart+"')]")).click();
	driver.findElement(By.xpath("//div[contains(text(),'"+ProdNameCheckInCart+"')]/parent::a/parent::div/div[2]/child::button")).click();
	
	
	System.out.println("Product removed from cart successfully!!!!!!");
	Thread.sleep(2000);
	/*
	 * WebElement element2=driver.findElement(By.xpath(
	 * "//button[contains(text(),'Remove')]/parent::div/parent::div/a/div[contains(text(),'"
	 * +ProdNameCheckInCart+"')]")); element2.click(); if
	 * (element2.isDisplayed()!=true) { System.out.
	 * println("Item removed from cart . Click on continue shopping to explore more product"
	 * ); }
	 */


}else {
		WebElement removefromcart=driver.findElement(By.xpath("//button[contains(text(),'Remove')]/parent::div/div[contains(text(),'"+ProdNameCheckInCart+"')]"));
		
		//button[contains(text(),'Remove')]/parent::div/div[contains(text(),'"+ProdNameCheckInCart+"')]
		removefromcart.click();
		/*
		 * if (removefromcart.isDisplayed()!=true) { System.out.
		 * println("Item removed from cart . Click on continue shopping to explore more product"
		 * ); }
		 */
		
		System.out.println("Product removed from cart successfully......");
		}
		
if(driver.findElement(By.xpath("//*[@id='continue-shopping']")).isDisplayed()==true)		{
	driver.findElement(By.xpath("//*[@id='continue-shopping']")).click();
	System.out.println("...............continue.");
}else {
		//button[contains(text(),'Remove')]/parent::div/parent::div/a/div[contains(text(),'Sauce Labs Fleece Jacket')]
		driver.findElement(By.xpath("//button[@name='back-to-products']")).click();
		}


		System.out.println("clicked on continue shopping successfully");
		boolean homepage=driver.findElement(By.xpath("//*[text()='Products']")).isDisplayed();
		if (homepage==true) {
			System.out.println("In Product homepage now");
			Thread.sleep(5000);
		}
		driver.quit();
		
	
		
		
	}

}

