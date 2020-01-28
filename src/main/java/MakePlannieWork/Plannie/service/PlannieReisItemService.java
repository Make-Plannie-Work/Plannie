package MakePlannieWork.Plannie.service;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.ReisItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Service
public class PlannieReisItemService {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private GroepRepository groepRepository;

    @Autowired
    private PlannieGroepService plannieGroepService;

    @Autowired
    private ReisItemRepository reisItemRepository;

    public void voegReisItemToe(Groep groep, ReisItem reisItem, Principal principal) {
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        groep.getReisItem().add(reisItem);
        reisItem.setAanmaker(gebruiker.getGebruikersId());
        reisItemRepository.save(reisItem);
    }

    public Optional<ReisItem> findById(Integer Id) {
        return reisItemRepository.findById(Id);
    }

    public Set<ReisItem> getLijstMetReisItemsOpGroepId(Integer groepId) {
        Groep groep = groepRepository.findByGroepId(groepId);
        return groep.getReisItem();
    }

    public void saveImage(MultipartFile imageFile, ReisItem reisItem) throws Exception{
        String folder = "src/main/webapp/images/";
        byte[] bytes = imageFile.getBytes();
        Path imagesPath = Paths.get(folder);
        Path huidigPath = Paths.get(folder + reisItem.getImagePath());
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Path rootPath = Paths.get(folder + "static/placeholder.png");
        if (!rootPath.equals(huidigPath)) {
            Files.delete(huidigPath);
        }
        if (!Files.exists(imagesPath)) {
            Files.createDirectory(imagesPath);
        }
        if (Files.exists(path)) {
            path = Paths.get(folder+ "1" + imageFile.getOriginalFilename());
        }
        reisItem.setImagePath(imageFile.getOriginalFilename());
        reisItemRepository.save(reisItem);
        Files.write(path, bytes);
    }

}
