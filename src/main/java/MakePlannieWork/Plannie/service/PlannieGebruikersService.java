package MakePlannieWork.Plannie.service;
import MakePlannieWork.Plannie.model.*;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.RolRepository;
import MakePlannieWork.Plannie.repository.WachtwoordResetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlannieGebruikersService implements UserDetailsService {
    @Autowired
    GebruikerRepository gebruikerRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    WachtwoordResetRepository wachtwoordResetRepository;

    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(email);
        if (gebruiker == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(Arrays.asList(rolRepository.findByRolNaam("ROLE_USER").get()))
            );
        }
        List<GrantedAuthority> authList = new ArrayList<>();
        Collection<Rol> rollen = gebruiker.getRollen();

        for (Rol rol : rollen)
            authList.add(new SimpleGrantedAuthority(rol.getRolNaam()));

        return new org.springframework.security.core.userdetails.User(gebruiker.getEmail(), gebruiker.getPassword(), gebruiker.
                isEnabled(), true, true, true, authList);

    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Rol> rollen) {

        return getGrantedAuthorities(getPrivileges(rollen));
    }

    private List<String> getPrivileges(Collection<Rol> rollen) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Rol rol : rollen) {
            collection.addAll(rol.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getPrivilegenaam());
        }
        return privileges;
    }

    public void voegGebruikerToe(Gebruiker gebruiker, Groep groep) {

        gebruiker.getGroepen().add(groep);
        gebruikerRepository.save(gebruiker);
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    // Hier staan alle handelingen voor wachtwoordreset tokens

    public void maakWachtWoordResetTokenVoorGebruiker(final Gebruiker gebruiker, final String token) {
        final WachtwoordResetToken mijnToken = new WachtwoordResetToken(token, gebruiker);
        wachtwoordResetRepository.save(mijnToken);
    }

    public WachtwoordResetToken getWachtwoordResetToken(final String token) {
        return wachtwoordResetRepository.findByToken(token);
    }

    public Gebruiker krijgGebruikerBijWachtwoordResetToken(final String token) {
        return wachtwoordResetRepository.findByToken(token).getGebruiker();
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}