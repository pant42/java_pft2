package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().gotoHomePage();
    app.getContactHelper().createContact(new ContactData(
            "Имя",
            "Фамилия",
            "Страна, область, город, улица, дом 5 кв 1",
            "123456789",
            "aa@in.ru",
            "Тест1"));
  }

}
