package tclark.demo.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class SummaryController {

    @Autowired
    SummaryRepository summaryRepository;

    @GetMapping("/summary")
    public List<SummaryEntity> getSummary(){
        return summaryRepository.findAll();
    }
}
