package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут
  public void ensurePreconditions() {
    app.group().gotoGroupPage();

    if (!app.group().isThereAGroup()) {
      app.group().create(new GroupData("Тест1", null, null));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {

    List<GroupData> before = app.group().groupList();

    int index = before.size() - 1;

    app.group().delete(index);

    List<GroupData> after = app.group().groupList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}

