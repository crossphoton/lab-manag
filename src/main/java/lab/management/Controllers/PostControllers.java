package lab.management.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lab.management.Errors.NotAllowed;
import lab.management.Errors.ServerError;
import lab.management.Models.Announcement;
import lab.management.Models.Users;
import lab.management.Services.AnnouncementService;
import lab.management.Services.UserService;

@RestController
public class PostControllers {
	
	@PostMapping("/api/announcement")
	public String postAnnouncement(@RequestBody Announcement toSaveAnnouncement) {

		return AnnouncementService.saveAnnouncement(toSaveAnnouncement);
		
	}


	@PostMapping("/api/users")
	public String newUser(@RequestBody Users user){

		String result = null;

		try{
			UserService.signup(user);
		} catch(ServerError error){
			System.out.println(error);
			result = "Failed";
		} catch(NotAllowed error){
			result = "Some error occured";
			System.out.println(error);
		}


		return result;
	}

}
