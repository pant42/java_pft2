package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigatorHelper().gotoHomePage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Имя", "Отчество", "Фамилия", "Псевдоним", "Компания", "Страна, область, город, улица, дом 5 кв 1", "556-766", "1234567890", "1122334455667788", "112233", "aa@ml.ru", "sq1@rr.ru", "1-00@tt.ee", "www.rr.rt", "17", "June", "1999", "Адресс", "Дом 6 корп 6", "Заметки и примечания"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }

}
