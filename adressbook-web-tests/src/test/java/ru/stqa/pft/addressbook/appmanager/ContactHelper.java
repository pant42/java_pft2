package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactDate contactDate) {
    type(By.name("firstname"), contactDate.firstname());
    type(By.name("middlename"), contactDate.middlename());
    type(By.name("lastname"), contactDate.lastname());
    type(By.name("nickname"), contactDate.nickname());
    type(By.name("company"), contactDate.company());
    type(By.name("address"), contactDate.address());
    type(By.name("home"), contactDate.homeTel());
    type(By.name("mobile"), contactDate.mobileTel());
    type(By.name("work"), contactDate.workTel());
    type(By.name("fax"), contactDate.fax());
    type(By.name("email2"), contactDate.email2());
    type(By.name("email3"), contactDate.email3());
    type(By.name("homepage"), contactDate.homepage());
    type(By.name("phone2"), contactDate.phone2());
    type(By.name("notes"), contactDate.notes());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void gotoHomePage() {
   click(By.linkText("home"));
  }

  public void selectionContact() {
    click(By.id("1"));
  }

  public void deletionContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void alertAccept() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }
}
