package lab.management.Models;



public class Announcement {
	private String title;
	private String body;
	private String owner;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Announcement(String title, String body, String owner) {
		super();
		this.title = title;
		this.body = body;
		this.owner = owner;
	}
	
	
}
