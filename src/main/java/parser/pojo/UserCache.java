package parser.pojo;

public class UserCache {

    private String login;
    private String password;
    private String dateFrom;
    private int userID;

    public UserCache() {
    }

    public UserCache(String login, String password, String dateFrom, int userID) {
        this.login = login;
        this.password = password;
        this.dateFrom = dateFrom;
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
