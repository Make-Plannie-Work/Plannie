package MakePlannieWork.Plannie.component;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Privilege;
import MakePlannieWork.Plannie.model.Rol;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.PrivilegeRepository;
import MakePlannieWork.Plannie.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${default.admin.password:admin}")
    private String defaultAdminPassword;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(alreadySetup)
            return;

        Privilege readPrivilege = maakPrivilegeAlsDezeNietBestaat("READ_PRIVILEGE");
        Privilege writePrivilege = maakPrivilegeAlsDezeNietBestaat("WRITE_PRIVILEGE");
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);

        addRol("ROLE_ADMIN", adminPrivileges);
        addRol("ROLE_USER", Arrays.asList(readPrivilege));

        addGebruiker("admin@admin.nl", defaultAdminPassword, "ROLE_USER", "ROLE_ADMIN");
        addGebruiker("user@user.nl", "user", "ROLE_USER");

        alreadySetup = true;
    }

    private void addGebruiker(String email, String wachtwoord, String... rollen) {
        if (!gebruikerRepository.existsByEmail(email)) {
            Gebruiker gebruiker = new Gebruiker();

            gebruiker.setEmail(email);
            gebruiker.setWachtwoord(passwordEncoder.encode(wachtwoord));
            gebruiker.setVoornaam(email);
            Set<Rol> gebruikersRollen = findRollen(rollen);
            gebruiker.setRollen(gebruikersRollen);

            gebruikerRepository.save(gebruiker);
        }
    }

    private Set<Rol> findRollen(String[] rollen) {
        Set<Rol> gebruikersRollen = new HashSet<>();
        for (String rol: rollen) {
            rolRepository.findByRolNaam(rol).ifPresent(gebruikersRollen::add);
        }
        return gebruikersRollen;
    }

    private void addRol(String rolAanTeMaken, Collection<Privilege> privileges) {
        if (!rolRepository.existsByRolNaam(rolAanTeMaken)) {
            Rol rol = new Rol();
            rol.setRolNaam(rolAanTeMaken);
            rol.setPrivileges(privileges);
            rolRepository.save(rol);
        }
    }

    private Privilege maakPrivilegeAlsDezeNietBestaat(String naam) {

        Privilege privilege = privilegeRepository.findByPrivilegenaam(naam);
        if(privilege == null) {
            privilege = new Privilege();
            privilege.setPrivilegenaam(naam);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }


}
