package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут

  public void ensurePreconditions() {
    app.goTo().groupPage();

    if (!app.group().isThereAGroup()) {
      app.group().create(new GroupData("Тест1", null, null));
    }
  }

  @Test
  public void GroupModification() {

    List<GroupData> before = app.group().groupList();

    int index = before.size() - 1;

    GroupData group = new GroupData(
            before.get(index).getId(),
            "ТестUPD",
            "ТестUPD2",
            "ТестUPD3"
    );

    app.group().modify(index, group);

    List<GroupData> after = app.group().groupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);

    Comparator<? super GroupData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
