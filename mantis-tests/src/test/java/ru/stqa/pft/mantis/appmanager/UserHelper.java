package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
    this.app = app;
  }

  public void selectUserFromResetPassword(int id, String username) throws InterruptedException {
    login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    goToControlUser();
    selectUser(username);
    resetPassword();
  }

  public void login(String username, String password) throws InterruptedException {
    wd.get(app.getProperty("web.baseURL") + "/login.php");
    type(By.name("username"), username);
    click(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]"));
    type(By.name("password"), password);
    click(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]"));
  }

  public void goToControlUser() {
    wd.get(app.getProperty("web.baseURL") + "/manage_user_page.php");
  }

  public void selectUser(String user) {
    By.linkText(user).findElement(wd).click();
  }

  public void resetPassword() {
    click(By.xpath("//*[@id=\"manage-user-reset-form\"]/fieldset/span/input"));
  }

  public void finish(String confirmationLink, String password) throws InterruptedException {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button"));
    Thread.sleep(2222);
  }
}
