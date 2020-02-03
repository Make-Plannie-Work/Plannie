package MakePlannieWork.Plannie.service;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.GebruikerVerificatieToken;
import MakePlannieWork.Plannie.model.WachtwoordResetToken;
import MakePlannieWork.Plannie.repository.GebruikerVerificatieRepository;
import MakePlannieWork.Plannie.repository.WachtwoordResetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Calendar;

@Service
@Transactional
public class PlannieGebruikerSecurityService {

    @Autowired
    private WachtwoordResetRepository wachtwoordResetRepository;

    @Autowired
    private GebruikerVerificatieRepository gebruikerVerificatieRepository;

    public String valideerWachtwoordResetToken(String id, String token) {
        final WachtwoordResetToken wachtwoordResetToken = wachtwoordResetRepository.findByToken(token);
        if ((wachtwoordResetToken == null) || !(wachtwoordResetToken.getGebruiker().getIdentifier().equals(id))) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((wachtwoordResetToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0)) {
            return "expired";
        }

        final Gebruiker gebruiker = wachtwoordResetToken.getGebruiker();
        final Authentication auth = new UsernamePasswordAuthenticationToken(gebruiker, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
    }

    public String valideerGebruikerVerificatieToken(String id, String token) {
        final GebruikerVerificatieToken gebruikerVerificatieToken = gebruikerVerificatieRepository.findByToken(token);
        if ((gebruikerVerificatieToken == null) || !(gebruikerVerificatieToken.getGebruiker().getIdentifier().equals(id))) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((gebruikerVerificatieToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0)) {
            return "expired";
        }

        final Gebruiker gebruiker = gebruikerVerificatieToken.getGebruiker();
        final Authentication auth = new UsernamePasswordAuthenticationToken(gebruiker, null, Arrays.asList(new SimpleGrantedAuthority("NEW_USER_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
    }

}
