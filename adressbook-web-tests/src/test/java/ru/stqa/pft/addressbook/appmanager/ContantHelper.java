package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContantHelper extends HelperBase{

 private WebDriver wd;

  public ContantHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void submitContactCreation() {
   click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactDate) {
    type(By.name("firstname"),contactDate.firstname());
    type(By.name("middlename"),contactDate.middlename());
    type(By.name("lastname"),contactDate.lastname());
    type(By.name("nickname"),contactDate.nickname());
    type(By.name("company"),contactDate.company());
    type(By.name("address"),contactDate.address());
    type(By.name("home"),contactDate.homeTel());
    type(By.name("mobile"),contactDate.mobileTel());
    type(By.name("work"),contactDate.workTel());
    type(By.name("fax"),contactDate.fax());
    type(By.name("email"),contactDate.email());
    type(By.name("email2"),contactDate.email2());
    type(By.name("email3"),contactDate.email3());
    type(By.name("homepage"),contactDate.homepage());
    type(By.name("byear"),contactDate.byear());
    type(By.name("address2"),contactDate.address2());
    type(By.name("phone2"),contactDate.phone2());
    type(By.name("notes"),contactDate.notes());

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }
}
