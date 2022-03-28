package ru.stqa.pft.addressbook.model;

public record ContactData(String firstname, String middlename, String lastname, String nickname, String company,
                          String address, String homeTel, String mobileTel, String workTel, String fax, String email,
                          String email2, String email3, String homepage, String bday, String bmonth, String byear,
                          String group, String address2, String phone2, String notes) {
}