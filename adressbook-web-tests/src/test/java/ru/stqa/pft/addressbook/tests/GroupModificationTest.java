package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут

  public void ensurePreconditions() {
    app.getGroupHelper().gotoGroupPage();

    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Тест1", null, null));
    }
  }

  @Test
  public void GroupModification() {

    List<GroupData> before = app.getGroupHelper().getGroupList();

    int index = before.size() - 1;

    GroupData group = new GroupData(
            before.get(index).getId(),
            "ТестUPD",
            "ТестUPD2",
            "ТестUPD3"
    );

    app.getGroupHelper().modifyGroup(index, group);

    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);

    Comparator<? super GroupData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
