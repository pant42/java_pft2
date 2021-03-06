package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут

  public void ensurePreconditions() {
    app.contact().gotoHomePage();

    if (app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      app.contact().create(new ContactData().
              withFirstname("CreatedToDelete").
              withLastname("CreatedToDelete")
      );
    }
  }

  @Test
  public void testContactDelete() throws Exception {

    Contacts before = app.db().contacts();

    ContactData deletedContact = before.iterator().next();

    app.contact().delete(deletedContact);

    Assert.assertEquals(app.contact().count(), before.size() - 1);

    Contacts after = app.db().contacts();

    before.remove(deletedContact);
    assertThat(after, equalTo(before.without(deletedContact)));

    verifyContactListInUi();
  }


}
