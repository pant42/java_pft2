package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTest extends TestBase {

  @Test
  public void GroupModification() {
    app.getGroupHelper().gotoGroupPage();

    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Тест1", null, null));
    }

    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("ТестUPD", "ТестUPD2", "ТестUPD3"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().returnToGroupPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());
  }
}
