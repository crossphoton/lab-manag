package lab.management.Controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lab.management.Errors.HTTPError;
import lab.management.Errors.NotAllowed;
import lab.management.Errors.NotFound;
import lab.management.Errors.ServerError;
import lab.management.Middlewares.JWT_Helper;
import lab.management.Services.AnnouncementService;
import lab.management.Services.TaskService;
import lab.management.Services.UserService;



@RestController
public class GetControllers {
	
	
	@GetMapping("/")
	public String home() {
		return "This is home. Go to https://www.github.com/crossphoton/lab-management for API usage.";
	}
	
	@GetMapping("/api/announcement")
	public List<Object> getAllAnnouncements(@CookieValue(name = "token", defaultValue = "") String token, HttpServletResponse response) {
		if(!JWT_Helper.checkStudent(token)){
			response.setStatus(401);
			return null;
		}
		return AnnouncementService.get();
	}

	@GetMapping("/api/announcement/{id}")
	public Object getAnnouncement(@PathVariable String id, @CookieValue(name = "token", defaultValue = "") String token, HttpServletResponse response) {
		if(!JWT_Helper.checkStudent(token)){
			response.setStatus(401);
			return null;
		}
		Object result = AnnouncementService.get(id);
		if(result.equals(new HTTPError(500, "Server Error")) | result.equals(new HTTPError(404, "Not found"))){
			response.setStatus(500);
			System.out.println("Error");
		}

		return result;
	}

	@GetMapping(value="/api/users")
	public String getMethodName(@RequestBody HashMap<Object, Object> user, HttpServletResponse response) {
		String result = null;

		try{
			result = UserService.signin(user);
			response.setStatus(200);
		} catch(NotFound error){
			response.setStatus(404);
			result = "User not found";
		} catch(ServerError error){
			response.setStatus(500);
			result = "Server Error";
		} catch(NotAllowed error){
			response.setStatus(405);
			result = "Wrong Credentials";
		}

		if(result != null) response.addHeader("Set-Cookie", "token="+result+"; Path=/;HttpOnly;Max-Age=36000");

		return result;
	}

	@GetMapping("/api/users/logout")
	public String logout(HttpServletResponse response){
		response.addHeader("Set-Cookie", "token="+null+"; Path=/;HttpOnly;Max-Age=0");
		return "Logged out";
	}
	
	@GetMapping("/api/task")
	public List<Object> getAllTasks(@CookieValue(name = "token", defaultValue = "") String token, HttpServletResponse response) {
		if(!JWT_Helper.checkStudent(token)){
			response.setStatus(401);
			return null;
		}
		return TaskService.get();
	}

	@GetMapping("/api/task/{id}")
	public Object getTask(@PathVariable String id, @CookieValue(name = "token", defaultValue = "") String token, HttpServletResponse response) {
		if(!JWT_Helper.checkStudent(token)){
			response.setStatus(401);
			return null;
		}
		Object result = TaskService.get(id);
		if(result.equals(new HTTPError(500, "Server Error")) | result.equals(new HTTPError(404, "Not found"))){
			System.out.println("Error");
			response.setStatus(500);
		}

		return result;
	}

	@GetMapping("/api/task/{task}/notice")
	public List<Object> getAllNotice(@CookieValue(name = "token", defaultValue = "") String token, @PathVariable String task, HttpServletResponse response) {
		if(!JWT_Helper.checkStudent(token)){
			response.setStatus(401);
			return null;
		}
		return TaskService.getNotice(task);
	}

	@GetMapping("/api/task/{task}/notice/{notice}")
	public Object getNotice(@CookieValue(name = "token", defaultValue = "") String token, @PathVariable String task, @PathVariable String notice, HttpServletResponse response) {
		if(!JWT_Helper.checkStudent(token)){
			response.setStatus(401);
			return null;
		}
		return TaskService.getNotice(task, notice);
	}
	
}









