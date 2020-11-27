package lab.management.Services;

import lab.management.Errors.*;
import lab.management.Models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;

public class TaskService {

    static Firestore db = FirestoreClient.getFirestore();
    static CollectionReference taskRef = db.collection("tasks");

    public static List<Object> get() {

        List<Object> data = new ArrayList<>();
        try {
            List<QueryDocumentSnapshot> docs = taskRef.get().get().getDocuments();

            ObjectMapper mapper = new ObjectMapper();

            for (QueryDocumentSnapshot document : docs) {
                HashMap<String, Object> entry = new HashMap<String, Object>();
                entry.put(document.getId(), document.toObject(Object.class));

                data.add(mapper.convertValue(entry, Object.class));
            }
        } catch (Exception error) {
            System.out.println(error);
        }

        return data;

    }

    public static Object get(String document) {

        Object result;

        try {
            DocumentSnapshot doc = taskRef.document(document).get().get();
            if (doc.exists()) {
                result = doc.toObject(Object.class);
            } else {
                result = new NotFound();
            }

        } catch (Exception error) {
            System.out.println(error);
            result = new ServerError();
        }

        return result;
    }

    public static String save(Task toSave) throws ServerError {
        toSave.studentRecord = new HashMap<String, Integer>();

        try {

            List<QueryDocumentSnapshot> docs = UserService.userRef.whereEqualTo("role", "ROLE_STUDENT").get().get()
                    .getDocuments();
            for (QueryDocumentSnapshot document : docs) {
                System.out.println("sjvsob");
                toSave.studentRecord.put(document.get("username").toString(), 0);
                System.out.println("78643");
            }

        } catch (Exception err) {
            System.err.println(err);
            throw new ServerError();
        }

        taskRef.add(toSave);
        return "Saved!!";
    }

    public static String delete(String id) {

        taskRef.document(id).delete();
        return "Deleted!!";
    }

    public static String updateMarks(String taskId, String username, Integer marks)
            throws InterruptedException, ExecutionException {

        DocumentReference data = taskRef.document(taskId).get().get().getReference();
        
        data.update("studentRecord."+username, marks);

        return "Updated!!";
    }
}
