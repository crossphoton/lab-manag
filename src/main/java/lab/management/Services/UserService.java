package lab.management.Services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;

import lab.management.Errors.*;
import lab.management.Middlewares.JWT_Helper;
import lab.management.Models.Users;

public class UserService {

    static Firestore db = FirestoreClient.getFirestore();

    static CollectionReference userRef = db.collection("users");

    public static String signin(HashMap<Object, Object> user) throws NotFound, ServerError, NotAllowed {

        String username = user.get("username").toString();

        try{
            List<QueryDocumentSnapshot> query = userRef.whereEqualTo("username", username).get().get().getDocuments();
            if(query.size() != 1){
                throw new NotFound();
            } else {

                String foundPassword = query.get(0).getReference().get().get().getData().get("password").toString();
                String role = query.get(0).getReference().get().get().getData().get("role").toString();

                if(foundPassword.equals(user.get("password"))){
                    DocumentReference docRef = query.get(0).getReference();
                    docRef.update("lastLogin", new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
                    return JWT_Helper.createUserToken(user.get("username").toString(), role);
                }
                else throw new NotAllowed();
            }
        }
        catch (Exception error) {
            System.out.println(error);
            throw new ServerError();
        }
    }

    public static String signup(Users user) throws ServerError, NotAllowed {

        if(!user.check()) throw new NotAllowed();

        user.role = "ROLE_STUDENT";
        try{
            List<QueryDocumentSnapshot> test = userRef.whereEqualTo("username", user.username).get().get().getDocuments();
            if(test.size() > 0){
                throw new NotAllowed();
            }
        } catch(Exception error){
            System.out.println(error);
            throw new ServerError();
        }
        userRef.add(user);
        return JWT_Helper.createUserToken(user.username, user.role);
    }
    
}
