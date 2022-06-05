package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    type(By.name("home"), contactData.getHomePhone());

    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroup() != null) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
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

  public void selectionContactById(int id) {
    wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
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

  public void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }


  //Святая троица функций для создания/модификации/удаления, которые были сформированы для красивого внешнего вида тестов
  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    gotoHomePage();
  }

  public void modify(ContactData contact) {
    selectionContactById(contact.getId());
    initContactModification();
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    gotoHomePage();
  }

  public void delete(ContactData contact) {
    selectionContactById(contact.getId());
    deletionContact();
    alertAccept();
    contactCache = null;
    gotoHomePage();
  }
//-------------------------------------------------------

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  private Contacts contactCache = null;

  public Contacts allCache() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();

    //При прохождении цикла, который изымает данные для коллекции, есть проблема: путь до этемента "Фамилия"/"Имя" = ../tr[<t>]/td[2], где <t> - номер строки в таблице, вкл шапку табл..
    //Для этого, мы введем переменную t, которая будет нашим "счетчиком"- по роли в цикле, и номером строки таблицы контактов- по призванию.№1 строка контактов (№1 контакт) = tr2.
    int t = 1;
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]"));
    for (WebElement element : elements) {
      t = t + 1;
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[" + t + "]/td[3]")).getText();
      String lastname = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[" + t + "]/td[2]")).getText();

      contactCache.add(new ContactData().
              withId(id).
              withFirstname(firstname).
              withLastname(lastname));
    }
    return new Contacts(contactCache);
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));

    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));

      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));

      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();

      String address = cells.get(3).getText();

      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();

      contacts.add(new ContactData().
              withId(id).

              withFirstname(firstname).
              withLastname(lastname).

              withAddress(address).

              withAllPhones(allPhones).
              withAllEmails(allEmails)
      );
    }
    return contacts;
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public ContactData InfoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());

    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");

    String address = wd.findElement(By.name("address")).getAttribute("value");

    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");

    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();

    return new ContactData().withId(

                    contact.getId()).

            withFirstname(firstname).
            withLastname(lastname).

            withAddress(address).

            withHomePhone(home).
            withMobilePhone(mobile).
            withWorkPhone(work).
            withPhone2(phone2).

            withEmail(email).
            withEmail2(email2).
            withEmail3(email3)
            ;
  }


}
