package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

  @Test
  public void GroupModification() {
    app.getGroupHelper().gotoGroupPage();

    int before = app.getGroupHelper().getGroupCount();

    if (!app.getGroupHelper().isThereAGroup() ) {
      app.getGroupHelper().createGroup (new GroupData("Тест1", null, null));
    }

    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("ТестUPD", "ТестUPD2", "ТестUPD3"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().returnToGroupPage();

    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }
}
