package MakePlannieWork.Plannie.service;
import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.UUID;
@Service
public class PlannieGebruikersService implements UserDetailsService {
    @Autowired
    GebruikerRepository gebruikerRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return gebruikerRepository.findByEmail(s).orElseThrow(
                () -> new UsernameNotFoundException("User " + s + " was not found")
        );
    }
    public void voegGebruikerToe(Gebruiker gebruiker, Groep groep) {
        gebruiker.setIdentifier(UUID.randomUUID().toString());
        gebruiker.getGroepen().add(groep);
        gebruikerRepository.save(gebruiker);
    }
}