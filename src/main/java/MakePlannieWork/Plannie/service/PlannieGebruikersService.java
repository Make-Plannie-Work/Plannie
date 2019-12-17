package MakePlannieWork.Plannie.service;

import MakePlannieWork.Plannie.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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


}
