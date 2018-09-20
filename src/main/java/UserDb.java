import java.util.HashMap;

public class UserDb {
    HashMap<String,User> users;

    public UserDb() {

        users= new HashMap<>();
        users.put("abebe",new User("abebe","123"));
        users.put("john",new User("john","123"));
        users.put("whiteny",new User("whiteny","123"));
    }



    public HashMap<String,User> getUsers(){

     return users;

    }


}
