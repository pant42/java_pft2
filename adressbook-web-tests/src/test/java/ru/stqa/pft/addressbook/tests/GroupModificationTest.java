package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

  @Test
  public void GroupModification() {
    app.getGroupHelper().gotoGroupPage();

    if (!app.getGroupHelper().isThereAGroup() ) {
      app.getGroupHelper().createGroup (new GroupData("Тест1", null, null));
    }

    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("ТестUPD", "ТестUPD2", "ТестUPD3"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().returnToGroupPage();
  }
}
