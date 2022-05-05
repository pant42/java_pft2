package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут
  public void ensurePreconditions() {
    app.group().gotoGroupPage();

    if (!app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("Тест2"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {

    Groups before = app.group().all();

    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);

    assertEquals(app.group().count(), before.size() - 1);

    Groups after = app.group().all();

    assertThat(after, equalTo(before.without(deletedGroup)));
  }


}

