package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.contact().gotoHomePage();

    List<ContactData> before = app.contact().contactList();

    ContactData contact = new ContactData().
            withFirstname("СоздИмяУдалить").
            withLastname("СоздФамилияУдалить").
            withAddress("СтранаУдалить, область, город, улица, дом 5 кв 1").
            withHomeTel("463456789").
            withEmail("46@in.ru").
            withGroup("[none]");

    app.contact().create(contact);

    List<ContactData> after = app.contact().contactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
