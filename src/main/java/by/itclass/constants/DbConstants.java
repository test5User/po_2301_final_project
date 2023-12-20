package by.itclass.constants;

public class DbConstants {
    public static final String ID_COL = "id";
    public static final String NAME_COL = "name";
    public static final String EMAIL_COL = "email";
    public static final String LOGIN_COL = "login";
    public static final String PASS_COL = "password";

    public static final String SELECT_USER = "SELECT id, name, email FROM user WHERE login = ? AND password = ?";
}
