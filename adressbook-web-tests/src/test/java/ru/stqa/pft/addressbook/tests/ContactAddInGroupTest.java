package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddInGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.contact().all().size() == 0) {
      app.goTo().homePage();
      Groups groups = app.db().groups();
      app.contact().create(new ContactData().
              withFirstname("fn").
              withLastname("ln").
              withAddress("ads").
              withHomePhone("8800").
              withEmail("zz@mi.ru")
      );
    }
  }

  @Test
  public void testContactAddInGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();
    app.contact().contactInGroup(contact, group);
    assertThat(app.db().getContactInGroup(contact.getId()).getGroups().contains(group), equalTo(true));

    verifyContactListInUi();
  }
}
