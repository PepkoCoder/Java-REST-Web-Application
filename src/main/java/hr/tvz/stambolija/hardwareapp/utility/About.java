package hr.tvz.stambolija.hardwareapp.utility;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("about")
public class About {

    @GetMapping
    public String getInfo (){
        return "Created by Petar Stambolija \n JMBAG=0246090028";
    }

}
