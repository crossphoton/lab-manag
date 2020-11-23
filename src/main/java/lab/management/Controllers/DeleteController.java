package lab.management.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lab.management.Services.AnnouncementService;

@RestController
public class DeleteController {
    


    @DeleteMapping("/api/announcement/{id}")
    public String deleteAnnouncement(@PathVariable String id){

        return AnnouncementService.deleteAnnouncement(id);
    }

}
