package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestData {

  @Test
  public void testContactCreation() throws Exception {

    gotoHomePage();
    initContactCreation();
    fillContactForm(new ContactDate("Имя", "Отчество", "Фамилия", "Псевдоним", "Компания", "Страна, область, город, улица, дом 5 кв 1", "556-766", "1234567890", "1122334455667788", "112233", "aa@ml.ru", "sq1@rr.ru", "1-00@tt.ee", "www.rr.rt", "17", "June", "1999", "Адресс", "Дом 6 корп 6", "Заметки и примечания"));
    submitContactCreation();
    returnToHomePage();
  }

}
