package lab.management.Models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Users {
    public String username;
    public String name;
    public String password;
    public String role;
    public String lastLogin = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(Calendar.getInstance().getTime());;

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
