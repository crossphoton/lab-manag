package lab.management.Controllers;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lab.management.Errors.NotAllowed;
import lab.management.Errors.ServerError;
import lab.management.Middlewares.JWT_Helper;
import lab.management.Models.Announcement;
import lab.management.Models.Users;
import lab.management.Services.AnnouncementService;
import lab.management.Services.UserService;

@RestController
public class PostControllers {
	
	@PostMapping("/api/announcement")
	public Object postAnnouncement(@RequestBody Announcement toSaveAnnouncement, @CookieValue (name = "token", defaultValue = "") String token)
			throws NotAllowed {

		if(token.equals("")) return "Not logged in";

		if(!JWT_Helper.checkTeacher(token)) throw new NotAllowed();

		return AnnouncementService.saveAnnouncement(toSaveAnnouncement);
		
	}


	@PostMapping("/api/users")
	public String newUser(@RequestBody Users user){

		String result = null;

		try{
			result = UserService.signup(user);
		} catch(ServerError error){
			System.out.println(error);
			result = "Failed";
		} catch(NotAllowed error){
			result = "User already exists with the username: "+user.username;
		}


		return result;
	}

}
