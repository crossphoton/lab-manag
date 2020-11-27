package lab.management.Models;

import java.util.Map;

public class Task extends Announcement {
    public Map<String, Integer> studentRecord;
    public int totalMarks;
    Task(String title, String body, String owner, int totalMarks, Map<String, Integer> studentRecord){
        setTitle(title);
        setBody(body);
        setOwner(owner);
        this.studentRecord = studentRecord;
        this.totalMarks = totalMarks;
    }
}
