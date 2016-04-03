package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;







import org.openqa.selenium.support.ui.Select;

import utils.Reporter;

public class GenericWrappers {

	protected static RemoteWebDriver driver;
	protected static Properties prop;
	public String sUrl,primaryWindowHandle,sHubUrl,sHubPort;

	public GenericWrappers() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will launch only firefox and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Babu - TestLeaf
	 * @param url - The url with http or https
	 * 
	 */
	public boolean invokeApp(String browser) {
		boolean bReturn = false;
		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);

			driver = new RemoteWebDriver(new URL("http://"+sHubUrl+":"+sHubPort+"/wd/hub"), dc);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(sUrl);

			primaryWindowHandle = driver.getWindowHandle();		
			Reporter.reportStep("The browser:" + browser + " launched successfully", "PASS");
			bReturn = true;

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The browser:" + browser + " could not be launched", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Babu - TestLeaf
	 */
	public boolean enterById(String idValue, String data) {
		boolean bReturn = false;
		try {
			driver.findElement(By.id(idValue)).clear();
			driver.findElement(By.id(idValue)).sendKeys(data);	
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+idValue, "PASS");
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+idValue, "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 * @author Babu - TestLeaf

	 */
	public boolean verifyTitle(String title){
		boolean bReturn = false;
		try{
			if (driver.getTitle().equalsIgnoreCase(title)){
				Reporter.reportStep("The title of the page matches with the value :"+title, "PASS");
				bReturn = true;
			}else
				Reporter.reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "SUCCESS");

		}catch (Exception e) {
			Reporter.reportStep("The title did not match", "FAIL");
		}

		return bReturn;
	}

	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTextByXpath(String xpath, String text){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().equalsIgnoreCase(text)){
			Reporter.reportStep("The text: "+sText+" matches with the value :"+text, "PASS");
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not match with the value :"+text, "FAIL");
		}


		return bReturn;
	}

	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTextContainsByXpath(String xpath, String text){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().contains(text)){
			Reporter.reportStep("The text: "+sText+" contains the value :"+text, "PASS");
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not contain the value :"+text, "FAIL");
		}


		return bReturn;
	}

	/**
	 * This method will close all the browsers
	 * @author Babu - TestLeaf
	 */
	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			Reporter.reportStep("The browser:"+driver.getCapabilities().getBrowserName()+" could not be closed.", "FAIL");
		}

	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickById(String id) {
		boolean bReturn = false;
		try{
			driver.findElement(By.id(id)).click();
			Reporter.reportStep("The element with id: "+id+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with id: "+id+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByClassName(String classVal) {
		boolean bReturn = false;
		try{
			driver.findElement(By.className(classVal)).click();
			Reporter.reportStep("The element with class Name: "+classVal+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with class Name: "+classVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}
	/**
	 * This method will click the element using name as locator
	 * @param name  The name (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByName(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.name(name)).click();
			Reporter.reportStep("The element with name: "+name+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with name: "+name+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using link name as locator
	 * @param name  The link name (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByLink(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.linkText(name)).click();
			Reporter.reportStep("The element with link name: "+name+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with link name: "+name+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByXpath(String xpathVal) {
		boolean bReturn = false;
		try{
			driver.findElement(By.xpath(xpathVal)).click();
			Reporter.reportStep("The element : "+xpathVal+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be moused over
	 * @author Babu - TestLeaf
	 */
	public boolean mouseOverByXpath(String xpathVal) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.xpath(xpathVal))).build().perform();
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" is performed.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" could not be performed.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using link name as locator
	 * @param xpathVal  The link name (locator) of the element to be moused over
	 * @author Babu - TestLeaf
	 */
	public boolean mouseOverByLinkText(String linkName) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.linkText(linkName))).build().perform();
			Reporter.reportStep("The mouse over by link : "+linkName+" is performed.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by link : "+linkName+" could not be performed.", "FAIL");
		}
		return bReturn;
	}

	public String getTextByXpath(String xpathVal){
		String bReturn = "";
		try{
			return driver.findElement(By.xpath(xpathVal)).getText();
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be found.", "FAIL");
		}
		return bReturn; 
	}

	/**
	 * This method will select the drop down value using id as locator
	 * @param id The id (locator) of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Babu - TestLeaf
	 */
	public boolean selectById(String id, String value) {
		boolean bReturn = false;
		try{
			new Select(driver.findElement(By.id(id))).selectByVisibleText(value);;
			Reporter.reportStep("The element with id: "+id+" is selected with value :"+value, "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The value: "+value+" could not be selected.", "FAIL");
		}
		return bReturn;
	}

	public void loadObjects() throws FileNotFoundException, IOException{
		prop = new Properties();
		prop.load(new FileInputStream(new File("./object.properties")));

	}
	
	
	int i=1;

	public void launchApp(String browserName, String url) {
		// TODO Auto-generated method stub
		try {
			if(browserName.equalsIgnoreCase("firefoxdriver")){
				driver=new FirefoxDriver();
			}else if(browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:/February/TestLeaf/drivers/chromedriver.exe");
				driver=new ChromeDriver();
			}
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			System.out.println("The browser is successfully launched");
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.out.println("The browser is not launched");	}
		finally{
			takeSnap();
		}
	}
	
	public void launchApp(String url) {
		// TODO Auto-generated method stub
		try {
			driver=new FirefoxDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			System.out.println("Firefox browser is successfully launched");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Firefox browser is not launched");		
			}
		finally{
				takeSnap();
		}
	}

	public void enterById1(String id, String dataToEnter) {
		// TODO Auto-generated method stub
		try {
			driver.findElementById(id).clear();
			driver.findElementById(id).sendKeys(dataToEnter);
			System.out.println("Data is successfully entered");
		} catch (NoSuchElementException e) {
			System.out.println("There is no webelement");
			// TODO Auto-generated catch block

		}finally{
			takeSnap();
		}
	}

	public void clickByClassName1(String className) {
		// TODO Auto-generated method stub
		try {
			driver.findElement(By.className(className)).click();
			System.out.println("Clicked Successfully");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("There is no webelement found");
		}finally{
			takeSnap();
		}
	}

	public void verifyTitle1(String expectedTitle) {
		// TODO Auto-generated method stub
		try {
			if(driver.getTitle().equals(expectedTitle)) {
				System.out.println("The title matches the " + expectedTitle);

			}else {
				System.out.println("The title does not match the " + expectedTitle);
			}
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.out.println("The Browser is closed");
		}finally{
			takeSnap();
		}
	}
	
	public void verifyLinks(String linktext) {
		// TODO Auto-generated method stub
		try {
			if(!driver.findElementByLinkText(linktext).isDisplayed()) {
				System.out.println("The link is available");

			}else {
				System.out.println("The link is not available");
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("The link is not available");
		}finally{
			takeSnap();
		}
		
		
		
	}


	public void closeAllBrowsers() {
		// TODO Auto-generated method stub
		try {
			driver.quit();
			System.out.println("Browsers Closed");
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.out.println("Browser is not available");		
		}
	}

	public void takeSnap() {
		// TODO Auto-generated method stub
		try {
			File src=driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src,new File("C:/Users/suresh kumar/Desktop/Project/snap/snap"+i+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to take screens");			
		}
		i++;
	}

	public void switchToPrimaryWindow(String parentWindowHandle) {
		// TODO Auto-generated method stub
		try {
			driver.switchTo().window(parentWindowHandle);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.out.println("Window/Browwser is not available");
		}finally{
			takeSnap();
		}
	}
	
	
	public void switchToLastWindow() {
		// TODO Auto-generated method stub
		try {
			Set<String> allWindows= driver.getWindowHandles();
			for (String winHandles : allWindows) {
				driver.switchTo().window(winHandles);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Window/Browwser is not available");
		}finally{
			takeSnap();
		}
	}

	public void switchToFrameByElement(String frameElement) {
		// TODO Auto-generated method stub
		try {
			driver.switchTo().frame(frameElement);
		} catch (NoSuchFrameException e) {
			// TODO Auto-generated catch block
			System.out.println("Frame is not available to switch");
		}finally{
			takeSnap();
		}
	}

	public void switchToFirstFrame() {
		// TODO Auto-generated method stub
		try {
			driver.switchTo().frame(0);
			System.out.println("Dismissed Alert successfully");
		} catch (NoSuchFrameException e) {
			// TODO Auto-generated catch block
			System.out.println("Frame is not available to switch");
		}finally{
			takeSnap();
		}
	}

	public void acceptAlert() {
		// TODO Auto-generated method stub
		try {
			Alert prompt = driver.switchTo().alert();
			prompt.accept();
			System.out.println("Accepted Alert successfully");
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			System.out.println("Alert is not available");
		}
	}

	public void dismissAlert() {
		// TODO Auto-generated method stub
		try {
			Alert prompt = driver.switchTo().alert();
			prompt.dismiss();
			System.out.println("Dismissed Alert successfully");
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			System.out.println("Alert is not available");
		}
	}

	public void verifyCurrentUrl(String expectedURL) {
		// TODO Auto-generated method stub
		try {
			if (driver.getCurrentUrl().equals(expectedURL))
				System.out.println("URL matches with expectedURL: "+ expectedURL);
			else
				System.out.println("URL does not with expectedURL "+ expectedURL);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.out.println("Browser is not available");		
		}finally{
			takeSnap();
		}

	}
	
	public void verifyTextById(String textID,String expectedText) {
		// TODO Auto-generated method stub
		try {
			if (driver.findElementById(textID).getText().equals(expectedText))
				System.out.println("Text matches with" +expectedText);
			else
				System.out.println("Text do not match with" +expectedText);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Webelement not found" +textID);		
		}
		finally{
			takeSnap();
		}
	}
	
	public void verifyTextByXpath1(String textXpath,String expectedText) {
		// TODO Auto-generated method stub
		try {
			if(driver.findElementByXPath(textXpath).getText().equals(expectedText))
				System.out.println("Text matches with " +expectedText);
			else
				System.out.println("Text do not match with" +expectedText);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Webelement not found" +textXpath);		
		}finally{
			takeSnap();
		}

	}

	public void clickByLinkText(String linkText) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByLinkText(linkText).click();
			System.out.println("Link" +linkText +"Clicked successfully");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Webelement not found:" +linkText);		
		}
	}
	
	public void clickByName1(String name) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByName(name).click();
			System.out.println(name +"Clicked successfully");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Webelement not found:" +name);		
		}finally{
			takeSnap();
		}
	}
	public void clickByXpath1(String clickableXpath) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByXPath(clickableXpath).click();
			System.out.println("Webelement Clicked successfully");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Webelement not found" +clickableXpath);		

		}finally{
			takeSnap();
		}
	}

	public void enterByName(String name, String datatoEnter) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByName(name).sendKeys(datatoEnter);
			System.out.println("Data" +datatoEnter+ "entered successfully");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Webelement not found" +name);		
		}finally{
			takeSnap();
		}

	}
	public void enterByXpath(String xpath,String datatoEnter) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByXPath(xpath).sendKeys(datatoEnter);
			System.out.println("Data" +datatoEnter+ "entered successfully");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Webelement not found" +xpath);		
		}finally{
			takeSnap();
		}
	}

	public void selectByIndex(WebElement webelement,int i){
		try {
			Select dropdown=new Select(webelement);
			dropdown.selectByIndex(i);
			System.out.println("Dropdown value with index" +i+" is selected");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Dropdown is not available");		}
		catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.out.println("Browser is not available");
		}
		finally{
			takeSnap();
		}
	}
	public void selectByValue(WebElement webelement,String value){
		try {
			Select dropdown=new Select(webelement);
			dropdown.selectByValue(value);
			System.out.println("Dropdown value with value" +value+" is selected");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Dropdown is not available");		}
		catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.out.println("Browser is not available");
		}
		finally{
			takeSnap();
		}
	}

	public void selectByVisibleText(WebElement webelement,String visibleText){
		try {
			Select dropdown=new Select(webelement);
			dropdown.selectByVisibleText(visibleText);
			System.out.println("Dropdown value with Visibletext" +visibleText+" is selected");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Dropdown is not available");		}
		catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.out.println("Browser is not available");
		}
		finally{
			takeSnap();
		}
	}
	
	public String excelRead(String sheetpath,int rownum, int column) throws IOException
    {
		   
		String cell= "";
        try {
            File xlf = new File(sheetpath);
            FileInputStream fis = new FileInputStream(xlf);
            XSSFWorkbook wb= new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            
            
            cell=sheet.getRow(rownum).getCell(column).getStringCellValue();
        } catch (WebDriverException e) {
            // TODO Auto-generated catch block
            System.out.println("page not loaded successfully");
        }
        
        return cell;
    }

	
	public String[] GetExcelRowData(int rownumber, String InputDataSheet) {

		String[] arraval = null;

		try {

			File filename = new File(InputDataSheet);

			FileInputStream fis = new FileInputStream(filename);

			XSSFWorkbook Workbook = new XSSFWorkbook(fis);

			XSSFRow currow = Workbook.getSheetAt(0).getRow(rownumber);

			arraval = new String[currow.getLastCellNum()];
			for (int j = 0; j < currow.getLastCellNum(); j++) {

				String value = currow.getCell(j).toString();
				arraval[j] = value;
			}
			Workbook.close();	
		} catch (IOException e) {
			System.out.println("unable to get data from excel");
		}
		return arraval;
	}
	
	public String[] GetExcelRowData(int rownumber, String InputDataSheetName, int InputDataSheetNumber) {

		String[] arraval = null;

		try {

			File filename = new File(InputDataSheetName);

			FileInputStream fis = new FileInputStream(filename);

			XSSFWorkbook Workbook = new XSSFWorkbook(fis);

			XSSFRow currow = Workbook.getSheetAt(InputDataSheetNumber).getRow(rownumber);

			arraval = new String[currow.getLastCellNum()];
			for (int j = 0; j < currow.getLastCellNum(); j++) {

				String value = currow.getCell(j).toString();
				arraval[j] = value;
			}
			Workbook.close();	
		} catch (IOException e) {
			System.out.println("unable to get data from excel");
		}
		return arraval;
	}


	public void splitString(String id) {
		try {
			String text = driver.findElementById(id).getText();
			System.out.println(text.substring(text.indexOf("(")+1, text.indexOf(")")));
		} catch (NoSuchElementException e) {
			System.out.println("Unable to get text");
		}finally{
            takeSnap();
	}
	}
	
	public void selectByVisibleTextusingId(String id,String visibleText){
        try {
            Select dropdown=new Select(driver.findElement(By.id(id)));
            dropdown.selectByVisibleText(visibleText);
            System.out.println("Dropdown value with Visibletext" +visibleText+" is selected");
        } catch (NoSuchElementException e) {
            // TODO Auto-generated catch block
            System.out.println("Dropdown is not available");        }
        catch (WebDriverException e) {
            // TODO Auto-generated catch block
            System.out.println("Browser is not available");
        }
        finally{
            takeSnap();
        }
	}
	
	public void clickByLinkTextWithoutSnap(String linkText) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByLinkText(linkText).click();
			Thread.sleep(3000);
			System.out.println("Link " +linkText +" Clicked successfully");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Webelement not found:" +linkText);		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clickByXpathWithoutSnap(String clickableXpath) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByXPath(clickableXpath).click();
			System.out.println("Webelement Clicked successfully");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Webelement not found" +clickableXpath);		

		}
	}


	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

