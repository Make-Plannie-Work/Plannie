package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.ReisItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReisItemController {

    @Autowired
    private GroepRepository groepRepository;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private ReisItemRepository reisItemRepository;


}
