package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

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

    Set<GroupData> before = app.group().all();

    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);

    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);
  }


}

