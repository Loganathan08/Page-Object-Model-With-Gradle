package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DashboardObjects {

	WebDriver driver;

	public DashboardObjects(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//img[@src='/web/images/dashboard_empty_widget_watermark.png' and @class='orangehrm-dashboard-widget-img']/following::p[1]")

	public static WebElement employeessleaveontoday;

	public void checkDashboard() throws InterruptedException{

		Thread.sleep(5000);

		String actualMessage=DashboardObjects.employeessleaveontoday.getText();


		Assert.assertEquals(actualMessage, "No Employees are on Leave Today");

	}

}
