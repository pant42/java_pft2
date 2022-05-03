package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.contact().gotoHomePage();

    Set<ContactData> before = app.contact().all();

    ContactData contact = new ContactData().
            withFirstname("СоздИмяУдалить").
            withLastname("СоздФамилияУдалить").
            withAddress("СтранаУдалить, область, город, улица, дом 5 кв 1").
            withHomeTel("463456789").
            withEmail("46@in.ru").
            withGroup("[none]");

    app.contact().create(contact);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
