package lab.management.Controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lab.management.Models.Announcement;
import lab.management.Services.AnnouncementService;

@RestController
public class PutController {
    

    @PutMapping("/api/announcement/{id}")
    public String updateAnnouncement(@PathVariable String id, @RequestBody Announcement toUpdate){
        return AnnouncementService.updateAnnouncement(id, toUpdate);
    }
}
