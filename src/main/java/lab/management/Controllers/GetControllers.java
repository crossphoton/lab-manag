package lab.management.Controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lab.management.Errors.HTTPError;
import lab.management.Errors.NotAllowed;
import lab.management.Errors.NotFound;
import lab.management.Errors.ServerError;
import lab.management.Services.AnnouncementService;
import lab.management.Services.UserService;



@RestController
public class GetControllers {
	
	
	@GetMapping("/")
	public String home() {
		return "Test";
	}
	
	@GetMapping("/error")
	public String error() {
		return "Some error occured";
	}
	
	@GetMapping("/api/announcement/all")
	public List<Object> getAllAnnouncements() {
		
		return AnnouncementService.getAll();
	}

	@GetMapping("/api/announcement")
	public Object getAnnouncement(@RequestParam String id){
		Object result = AnnouncementService.getAnnoucement(id);
		if(result.equals(new HTTPError(500, "Server Error")) | result.equals(new HTTPError(404, "Not found"))){
			System.out.println("Error");
		}

		return result;
	}


	@GetMapping(value="/api/users")
	public String getMethodName(@RequestBody HashMap<Object, Object> user) {
		String result = null;

		try{
			result = UserService.signin(user);
		} catch(NotFound error){
			result = "User not found";
		} catch(ServerError error){
			result = "Server Error";
		} catch(NotAllowed error){
			result = "Wrong Credentials";
		}

		return result;
	}
	

}









