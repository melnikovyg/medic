package com.github.itavgur;

import com.github.itavgur.model.Role;
import com.github.itavgur.model.User;

public class TestDataTemplate {

    private static int SequenceNumber = 1000;

    public static final User USER_ADMIN = new User(SequenceNumber++, "admin", "adminp", "Full Admin Name", "+79148959482", true, Role.ROLE_ADMIN);
    public static final User USER_REGISTRATOR = new User(SequenceNumber++, "reg", "regp", "Full Registrator Name", "+79148959482", true, Role.ROLE_REGISTRATOR);
    public static final User USER_REGISTRATOR_UPDATED = new User(USER_REGISTRATOR.getId(), "regUpd", "regUpd", "Full Registrator Name Updated", "+78000000000", false, Role.ROLE_REGISTRATOR);
    public static final User USER_CREATED = new User("new", "newp", "Full New User Name", "+79148959482", true, Role.ROLE_REGISTRATOR);

    public static final int INVALID_ID = SequenceNumber - 10;
    public static final String INVALID_STRING = "invalid string";


}
