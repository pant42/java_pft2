package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactModificationTest extends TestBase {
  @Test
  public void testContactModification() {

    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectionContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactDate("ИмяUPD", "ОтчествоUPD", "ФамилияUPD", "ПсевдонимUPD", "КомпанияUPD", "СтранаUPD, областьUPD, городUPD, улицаUPD, дом 5 кв 1", "226-766", "2222222", "1122334455667788", "112233", "aaUPD@ml.ru", "sq1@rr.ru", "1-00@tt.ee", "www.rr.rt", "17", "June", "1999", "Адресс", "Дом 6 корп 6", "UPDЗаметки и примечания"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
