package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class OutGroupContactTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      Groups groups = app.db().groups();
      app.contact().create(new ContactData().

              withFirstname("dfn").
              withLastname("dln").
              withAddress("dads").
              withHomePhone("8800").
              withEmail("dzz@mi.ru")

              .inGroup(groups.iterator().next())
      );
    }

  }

  @Test
  public void testContactRemoveGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    int i = contact.getId();

    if (contact.getGroups().size() == 0) {

      GroupData new_group = app.db().groups().iterator().next();
      app.contact().gotoHomePage();
      app.contact().contactInGroup(contact, new_group);

    }

    ContactData new_contact = app.db().getContactById(i);

    Groups groupDelete = new_contact.getGroups();
    app.goTo().homePage();
    app.contact().contactRemoveGroup(new_contact);

    assertThat(app.db().getContactById(contact.getId()).getGroups().contains(groupDelete), equalTo(false));
  }

}


