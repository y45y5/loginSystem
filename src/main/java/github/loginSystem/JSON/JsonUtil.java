package github.loginSystem.JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import github.loginSystem.User.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class JsonUtil {
    private static File file;
    private static Gson gson;
    private static ObjectMapper objectMapper;

    public JsonUtil(String filePath){
        file = new File(filePath);
        gson = new Gson();
        objectMapper = new ObjectMapper();
    }

    public JsonUtil(){

    }

    public boolean checkUsername(String username){
        readFromFile();
        List<User> userList = getUserList();
        for (User user : userList)
            if (user.getUsername().equals(username)) return false;
        return true;
    }

    public void saveToFile(User user){
        JSONObject userJson = new JSONObject();
        userJson.put("username", user.getUsername());
        userJson.put("password", user.getPassword());
        userJson.put("email", user.getEmail());

        JSONArray userArray = readFromFile();
        userArray.add(userJson);

        try{ objectMapper.writeValue(file, userArray); }
        catch (Exception e){ System.out.println(e.getMessage()); }
    }

    public JSONArray readFromFile(){
        try{ return objectMapper.readValue(file, JSONArray.class);}
        catch (Exception e){ System.out.println(e.getMessage()); }
        return null;
    }

    public User loginToAccount(String username, String password){
        readFromFile();
        List<User> userList = getUserList();
        for (User user : userList)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) return user;
        return null;
    }

    private List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        readFromFile().forEach(u -> {
            User user = gson.fromJson(gson.toJson(u), User.class);
            userList.add(user);
        });
        return userList;
    }

    public void deleteUser(User user){
        JSONObject userJson = new JSONObject();
        userJson.put("username", user.getUsername());
        userJson.put("password", user.getPassword());
        userJson.put("email", user.getEmail());

        JSONArray userArray = readFromFile();
        userArray.remove(userJson);
        try{ objectMapper.writeValue(file, userArray); }
        catch (Exception e){ System.out.println(e.getMessage()); }
    }
}
