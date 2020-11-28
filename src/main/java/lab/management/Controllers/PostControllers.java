package lab.management.Controllers;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lab.management.Errors.NotAllowed;
import lab.management.Errors.ServerError;
import lab.management.Middlewares.JWT_Helper;
import lab.management.Models.Announcement;
import lab.management.Models.Task;
import lab.management.Models.Users;
import lab.management.Services.AnnouncementService;
import lab.management.Services.TaskService;
import lab.management.Services.UserService;

@RestController
public class PostControllers {

	@PostMapping("/api/announcement")
	public String postAnnouncement(@RequestBody Announcement toSave,
			@CookieValue(name = "token", defaultValue = "") String token) throws NotAllowed {

		if (token.equals(""))
			return "Not logged in";

		if (!JWT_Helper.checkTeacher(token))
			throw new NotAllowed();

		toSave.setOwner(JWT_Helper.getUsername(token));

		return AnnouncementService.save(toSave);

	}

	@PostMapping("/api/users")
	public String newUser(@RequestBody Users user, HttpServletResponse response) {

		String result = null;

		try {
			result = UserService.signup(user);
		} catch (ServerError error) {
			System.out.println(error);
			result = "Failed";
		} catch (NotAllowed error) {
			result = "User already exists with the username: " + user.username;
		}

		if (result != null)
			response.addHeader("Set-Cookie", "token=" + result + "; Path=/;HttpOnly;Max-Age=36000");
		return result;
	}

	@PostMapping("/api/task")
	public String postTask(@RequestBody Task toSave, @CookieValue(name = "token", defaultValue = "") String token)
			throws NotAllowed {

		if (token.equals(""))
			return "Not logged in";

		if (!JWT_Helper.checkTeacher(token))
			throw new NotAllowed();

		toSave.setOwner(JWT_Helper.getUsername(token));
		try {
			return TaskService.save(toSave);
		} catch (ServerError err) {
			return "Some Error Occured!!";
		}
	}

	@PostMapping("/api/task/{id}/updateMarks")
	public String updateMarksInTask(@CookieValue(name = "token", defaultValue = "") String token,
			@RequestBody UpdateMarks_HelperClass updates, @PathVariable String id)
			throws NotAllowed, InterruptedException, ExecutionException {

		if(token.equals("")) return "Not logged in";
		if(!JWT_Helper.checkTeacher(token)) throw new NotAllowed();

		return TaskService.updateMarks(id, updates.username, updates.marks);
	}


	static private class UpdateMarks_HelperClass{
		public String username;
		public int marks;
	}

	@PostMapping("/api/task/{task}/notice")
	public String addNotice(@CookieValue(name = "token", defaultValue = "") String token,
			@RequestBody Announcement notice, @PathVariable String task)
			throws NotAllowed, InterruptedException, ExecutionException {

		if(token.equals("")) return "Not logged in";
		if(!JWT_Helper.checkTeacher(token)) throw new NotAllowed();

		notice.setOwner(JWT_Helper.getUsername(token));

		return TaskService.addNotice(notice, task);
	}

}
