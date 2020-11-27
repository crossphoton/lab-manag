package lab.management.Services;

import lab.management.Errors.*;
import lab.management.Models.Announcement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

public class AnnouncementService {

    static Firestore db = FirestoreClient.getFirestore();
    static CollectionReference announcementReference = db.collection("announcement");
    

    public static List<Object>get(){        

        ApiFuture<QuerySnapshot> future =  announcementReference.get();
        List<Object> data = new ArrayList<>();
        List<QueryDocumentSnapshot> docs;

        try{
            docs = future.get().getDocuments();
            
            ObjectMapper mapper = new ObjectMapper();
            
            for (QueryDocumentSnapshot document : docs) {
                HashMap<String, Object> entry = new HashMap<String, Object>();
                entry.put(document.getId(), document.toObject(Object.class));

                data.add(mapper.convertValue(entry, Object.class));
            }
        } catch(Exception error){System.out.println(error);}



        return data;

    }


    public static Object get(String document){

        DocumentReference docRef = announcementReference.document(document);

        ApiFuture<DocumentSnapshot> future = docRef.get();

        Object result;

        try{

        DocumentSnapshot doc = future.get();
        if(doc.exists()){
            result = doc.toObject(Object.class);
        }
        else{
            result = new NotFound();
        }

        } catch(Exception error){System.out.println(error); result = new ServerError();}

        return result;
    }


    public static String save(Announcement toSave){
        
        announcementReference.add(toSave);
        return "Noted!!";
    }
    

    public static String delete(String id){

        announcementReference.document(id).delete();
        return "Noted!!";
    }
}
