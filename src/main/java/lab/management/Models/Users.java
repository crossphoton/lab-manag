package lab.management.Models;

public class Users {
    public String username;
    public String name;
    public String password;
    public String role;

    Users(String name, String username, String password, String role){
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}
