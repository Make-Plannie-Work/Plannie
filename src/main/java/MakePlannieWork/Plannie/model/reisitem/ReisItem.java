package MakePlannieWork.Plannie.model.reisitem;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * ReisItem class aangemaakt. Is composite. Heeft 1 op veel relatie met Groep.
 */

@Entity
@Table(name = "reis_item")
public class ReisItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reisItemId;

    private String naam;
    private String startDatum;
    private String locatie;
    private Integer aanmaker;
    private String imagePath = "static/placeholder.png";

    @Column(nullable = true)
    private Double budget;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gekoppeldeReisItem")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ReisItem> reisItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reis_item")
    private ReisItem gekoppeldeReisItem;

    public double berekenTotaalBudget() {
        if (budget == null) {
            budget = 0.0;
        }

        Double totaalBudget = budget;
        for (ReisItem item : reisItems) {
            if (reisItems.isEmpty()) {
                return totaalBudget;
            } else {
                totaalBudget += item.berekenTotaalBudget();
            }
        }
        return totaalBudget;
    }

    // Methode om de overkoepelende reis te geven, inplaats van het eerstvolgende reisItem.
    public Integer vindHoofdReisId() {
        if (gekoppeldeReisItem == null) {
            return reisItemId;
        } else {
            return gekoppeldeReisItem.vindHoofdReisId();
        }
    }

    public Locatie vindLocatie() {
        for (ReisItem item : geefReisGesorteerdDatum()) {
            if (item instanceof Locatie) {
                return (Locatie) item;
            }
        }
        return null;
    }

    private void test() {
        String test = geefGeformatteerdEersteAdresVanReis();
    }

    public String geefGeformatteerdEersteAdresVanReis() {
        for (ReisItem item : geefReisGesorteerdDatum()) {
            if (item instanceof Locatie) {
                return ((Locatie) item).geefGeformatteerdAdres();
            } else {
                String gevondenAdres = item.geefGeformatteerdEersteAdresVanReis();
                if (!gevondenAdres.equals("nvt")) {
                    return gevondenAdres;
                }
            }
        }
        return "nvt";
    }

    public String geefTypeReisItem() {
        return this.getClass().getName();
    }

    // Methode om de set van ReisItems gesorteerd terug te geven.
    public ArrayList<ReisItem> geefReisGesorteerdDatum() {
        ArrayList<ReisItem> itemsGesorteerd = new ArrayList<>(reisItems);
        itemsGesorteerd.sort(Comparator.comparing(ReisItem::getStartDatum).thenComparing(ReisItem::getNaam).thenComparing(ReisItem::geefTypeReisItem));
        return itemsGesorteerd;
    }

    // Methode om alle subItems van dit item in dagen te rangschikken.
    // Dag 0 bevat alle niet-activiteiten.
    public ArrayList<Dag> geefDagenOverzicht() {
        ArrayList<Dag> dagen = new ArrayList<>();
        dagen.add(new Dag(0));
        ArrayList<ReisItem> reisItem = geefReisGesorteerdDatum();
        String laatsteDatum = "Een waardeloze datum";

        // Alle reisItems bijlangs
        for (ReisItem item : reisItem) {
            if (!(item instanceof Activiteit)) {
                // Alle niet-Activiteit items worden op dag 0 ingedeeld.
                dagen.get(0).voegReisItemToe(item);
            } else {
                // Alle Activiteiten worden in dagen opgedeeld.
                if (item.getStartDatum().substring(0, 10).equals(laatsteDatum.substring(0, 10))) {
                    // Als de datum hetzelfde is als die van het vorige item, wordt deze activiteit bij de nieuwste dag ingevoegd.
                    dagen.get(dagen.size() - 1).voegReisItemToe(item);
                } else {
                    // Als de datum een nieuwe is, wordt er een nieuwe dag aangemaakt, met bijhorend dagnummer.
                    try {
                        long dagNummer = berekenDagenTussenDatums(this.startDatum.replace("T", " "), item.getStartDatum().replace("T", " "));
                        Dag dag = new Dag(dagNummer + 1, item);
                        dagen.add(dag);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                laatsteDatum = item.getStartDatum();
            }
        }
        return dagen;
    }

    // Methode om de datum van dit, en alle onderliggende reisItems te wijzigen.
    public void wijzigCompleteReisDatum(String nieuweDatum) {
        if (!this.startDatum.equals(nieuweDatum)) {
            SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                // Verschil in dagen uitrekenen.
                Date nieuw = timeDateFormat.parse(nieuweDatum.replace("T", " "));
                Date oud = timeDateFormat.parse(this.startDatum.replace("T", " "));
                long verschil = nieuw.getTime() - oud.getTime();

                // De datum van dit reisItem, en alle onderliggende reisItems aanpassen.
                wijzigDatum(verschil);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    // De datum van dit reisItem, en alle onderliggende reisItems aanpassen.
    public void wijzigDatum(long verschil) throws ParseException {
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date datumTijd = timeDateFormat.parse(startDatum.replace("T", " "));
        // Het verschil in dagen bij de startDatum optellen.
        datumTijd.setTime(datumTijd.getTime() + verschil);
        this.startDatum = timeDateFormat.format(datumTijd).replace(" ", "T");

        for (ReisItem item : getReisItems()) {
            item.wijzigDatum(verschil);
        }
    }

    public String getEindDatum() {
        String eindDatum = startDatum;
        if (reisItems != null) {
            // Anders vind het de laatste datum van zijn subItems.
            SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                // Vind de laatste datum van alle reisItems.
                Date laatste = timeDateFormat.parse(this.startDatum.replace("T", " "));
                for (ReisItem item : reisItems) {
                    Date checkDatum = timeDateFormat.parse(item.getEindDatum().replace("T", " "));
                    if (checkDatum.compareTo(laatste) > 0) {
                        laatste = checkDatum;
                    }
                }
                eindDatum = timeDateFormat.format(laatste).replace(" ", "T");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return eindDatum;
    }

    // Deze methode geeft een leesbare reisDuratie terug voor gebruik op de frontend.
    public String geefGeformatteerdeReisDuratie() throws ParseException {
        String duratie;
        long aantalDagen = berekenDagenTussenDatums(this.startDatum.replace("T", " "),
                getEindDatum().replace("T", " "));
        if (aantalDagen <= 0) {
            if (!reisItems.isEmpty()) {
                // Als er reisItems aan de reis gekoppeld zijn, worden de waardes van de chronoligisch eerste item teruggeggeven.
                duratie = geefReisGesorteerdDatum().get(0).geefGeformatteerdeStartDatumEnTijd();
            } else {
                // Als er nog geen reisItems aan de reis gekoppeld zijn, word de startdatum van de reis teruggeggeven.
                duratie = geefGeformatteerdeStartDatum();
            }
        } else {
            // Als de reis meerdere dagen duurt, worden de start, en einddatum teruggeggeven.
            duratie = geefGeformatteerdeStartDatum() + " t/m " + geefGeformatteerdeEindDatum();
        }
        return duratie;
    }

    public String geefGeformatteerdeStartDatum() {
        return this.startDatum.substring(0, 10);
    }

    public String geefGeformatteerdeEindDatum() {
        return getEindDatum().substring(0, 10);
    }

    public String geefGeformatteerdeTijd() {
        return this.startDatum.substring(11, 16);
    }

    public String geefGeformatteerdeStartDatumEnTijd() {
        return geefGeformatteerdeStartDatum() + " " + geefGeformatteerdeTijd();
    }

    private long berekenDagenTussenDatums(String beginDatumyyyyMMddHHmm, String eindDatumyyyyMMddHHmm) throws ParseException {
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = timeDateFormat.parse(beginDatumyyyyMMddHHmm.substring(0, 10));
        Date eind = timeDateFormat.parse(eindDatumyyyyMMddHHmm.substring(0, 10));
        long verschil = eind.getTime() - start.getTime();
        verschil = TimeUnit.DAYS.convert(verschil, TimeUnit.MILLISECONDS);
        return verschil;
    }

    // Methode om een startdatum voor een nieuw reisItem te geven.
    public String geefNieuwStartDatum() {
        if (this.startDatum == null) {
            // Als dit reisItem zelf geen startdatum heeft, wordt er gecontroleerd of er een gekoppeldReisItem is.
            if (this.gekoppeldeReisItem != null) {
                // De gekoppeldeReisItem wordt om de startDatum gevraagd.
                return this.gekoppeldeReisItem.geefNieuwStartDatum();
            } else {
                // Als er geen gekoppeldeReisItem is, wordt de startdatum op de datum van vandaag gezet.
                DateTimeFormatter datumFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime vandaag = LocalDateTime.now();
                return datumFormatter.format(vandaag) + "T12:00";
            }
        } else {
            // Als dit reisItem een startdatum heeft, geeft hij deze terug, om klaar te zetten in de JSP's, voor een nieuw ReisItem.
            return this.startDatum;
        }
    }

    public void voegReisItemToe(ReisItem reisItem) {
        if (reisItems == null) {
            reisItems = new HashSet<>();
        }
        reisItems.add(reisItem);
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public ReisItem getGekoppeldeReisItem() {
        return gekoppeldeReisItem;
    }

    public void setGekoppeldeReisItem(ReisItem gekoppeldeReisItem) {
        this.gekoppeldeReisItem = gekoppeldeReisItem;
    }

    public Integer getReisItemId() {
        return reisItemId;
    }

    public void setReisItemId(Integer reisItemId) {
        this.reisItemId = reisItemId;
    }

    public Integer getId() {
        return this.reisItemId;
    }

    public String getNaam() {
        if (naam != null) {
            return naam;
        } else {
            return "";
        }
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Integer getAanmaker() {
        return aanmaker;
    }

    public void setAanmaker(Integer aanmaker) {
        this.aanmaker = aanmaker;
    }

    public String getStartDatum() {
        return geefNieuwStartDatum();
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Set<ReisItem> getReisItems() {
        return reisItems;
    }

    public void setReisItems(Set<ReisItem> reisItems) {
        this.reisItems = reisItems;
    }

    public ReisItem getGekoppeldeReisItemId() {
        return gekoppeldeReisItem;
    }

    public void setGekoppeldeReisItemId(ReisItem gekoppeldeReisItemId) {
        this.gekoppeldeReisItem = gekoppeldeReisItemId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Met deze methode haal je het reisItemId op van het hoogste reisItem.
    public Integer getHoofdReisItemId() {
        if (gekoppeldeReisItem == null) {
            return this.reisItemId;
        }
        return gekoppeldeReisItem.getHoofdReisItemId();
    }
}
