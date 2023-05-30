package test_cases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import page_objects.DashboardObjects;
import page_objects.LoginObjects;
import page_objects.UserRoleObjects;

public class UserRoleTest {
	

	WebDriver driver;

	@BeforeMethod
	public void setupBeforeMethod(){

		WebDriverManager.chromedriver().setup();

		driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

	}

	@Test(priority=1)
	public void verifyChkUserRole() throws Exception  {

		LoginObjects login=new LoginObjects(driver);
		login.loginAdminUser();

		DashboardObjects verify=new DashboardObjects(driver);
		verify.checkDashboard();


		UserRoleObjects check=new UserRoleObjects(driver);

		check.checkUserRole();


	}
	@AfterTest
	public void afterTest(){

		driver.quit();	

	}


}
