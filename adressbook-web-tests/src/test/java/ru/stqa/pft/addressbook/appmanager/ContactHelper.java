package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("home"), contactData.getHomeTel());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void gotoHomePage() {
    click(By.linkText("home"));
  }

  public void selectionContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deletionContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void alertAccept() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    gotoHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();

    //При прохождении цикла, который изымает данные для коллекции, есть проблема: путь до этемента "Фамилия"/"Имя" = ../tr[<t>]/td[2], где <t> - номер строки в таблице, вкл шапку табл..
    //Для этого, мы введем переменную t, которая будет нашим "счетчиком"- по роли в цикле, и номером строки таблицы контактов- по призванию.№1 строка табл (№1 контакт) = tr2.
    int t = 1;
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]"));
    for (WebElement element : elements) {
      t = t + 1;
      String id = element.findElement(By.tagName("input")).getAttribute("value");
      String firstname = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[" + t + "]/td[3]")).getText();
      String lastname = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[" + t + "]/td[2]")).getText();

      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }

}
