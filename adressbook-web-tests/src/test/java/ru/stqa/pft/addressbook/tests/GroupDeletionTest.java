package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {

    app.getGroupHelper().gotoGroupPage();

    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Тест1", null, null));
    }

    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getNavigationHelper().returnToGroupPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }


}

