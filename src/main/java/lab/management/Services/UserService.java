package lab.management.Services;

import java.util.Dictionary;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;

import lab.management.Errors.*;
import lab.management.Middlewares.JWT_Helper;
import lab.management.Models.Users;

public class UserService {

    static Firestore db = FirestoreClient.getFirestore();

    static CollectionReference userRef = db.collection("users");

    public static String signin(Dictionary<Object, Object> user) throws NotFound, ServerError {

        String username = user.get("username").toString();

        try{
            Object[] query = userRef.whereEqualTo("username", username).get().get().getDocuments().toArray();
            if(query.length == 1){
                throw new NotFound();
            } else {
                Users foundUser = new ObjectMapper().convertValue(query[0], Users.class);

                System.out.println(foundUser);

                if(foundUser.password == user.get("password")){
                    return JWT_Helper.createUserToken(foundUser.username, foundUser.role);
                } else{ throw new Exception(new NotAllowed()); }
            }
        }
        catch (Exception error) {
            System.out.println(error);
            throw new ServerError();
        }
    }

    public static String signup(Users user) throws ServerError, NotAllowed {

        user.role = "ROLE_STUDENT";
        try{
            List<QueryDocumentSnapshot> test = userRef.whereEqualTo("username", user.username).get().get().getDocuments();

            if(test.size() > 0){
                throw new Exception(new NotAllowed());
            }
        } catch(Exception error){
            System.out.println(error);
            throw new ServerError();
        }
        userRef.add(user);
        return "Noted!!";
    }
    
}
