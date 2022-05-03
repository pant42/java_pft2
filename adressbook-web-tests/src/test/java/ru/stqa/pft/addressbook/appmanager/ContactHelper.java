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


  //Святая троица функций для создания/модификации/удаления , которые были сформированы для красивого внешнего вида тестов
  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    gotoHomePage();
  }
  public void modify(int index, ContactData contact) {
    selectionContact(index);
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
    gotoHomePage();
  }
  public void delete(int index) {
    selectionContact(index);
    deletionContact();
    alertAccept();
    gotoHomePage();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> contactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();

    //При прохождении цикла, который изымает данные для коллекции, есть проблема: путь до этемента "Фамилия"/"Имя" = ../tr[<t>]/td[2], где <t> - номер строки в таблице, вкл шапку табл..
    //Для этого, мы введем переменную t, которая будет нашим "счетчиком"- по роли в цикле, и номером строки таблицы контактов- по призванию.№1 строка контактов (№1 контакт) = tr2.
    int t = 1;
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]"));
    for (WebElement element : elements) {
      t = t + 1;
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[" + t + "]/td[3]")).getText();
      String lastname = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[" + t + "]/td[2]")).getText();

      ContactData contact = new ContactData().
              withId(id).
              withFirstname(firstname).
              withLastname(lastname);

      contacts.add(contact);
    }
    return contacts;
  }

}
