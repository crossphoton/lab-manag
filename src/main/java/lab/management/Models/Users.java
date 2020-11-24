package lab.management.Models;

public class Users {
    public String username;
    public String name;
    public String password;
    public String role;

    public Users(){}

    Users(String name, String username, String password, String role){
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }


    public boolean check(){
        boolean result = true;

        if(this.username == null) result = false;
        if(this.name == null) result = false;
        if(this.password == null) result = false;
        if(this.role == null) result = false;
        
        return result;
    }
}
