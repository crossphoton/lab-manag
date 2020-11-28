package lab.management.Models;

import java.util.Map;

public class Task extends Announcement{
    public Map<String, Integer> studentRecord;
    private int totalMarks;
    Task(String title, String owner, String body, int totalMarks, Map<String, Integer> studentRecord){
        this.setTitle(title);
        this.setOwner(owner);
        this.setBody(body);
        this.studentRecord = studentRecord;
        setTotalMarks(totalMarks);
    }

    public void setTotalMarks(int marks) {this.totalMarks = marks;}

    public int getTotalMarks() {return this.totalMarks;}
}
