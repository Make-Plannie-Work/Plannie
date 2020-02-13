package MakePlannieWork.Plannie.model;

import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReisItemTest {

    // Deze test controleerd of de Entity ReisItem wordt aangemaakt, en of de benodidge getters en setters aanwezig zijn.
    @Test
    void entityGroepWerkt() {
        // Arrange
        ReisItem reisItem = new ReisItem();
        Integer reisItemId = 1;
        String naam = "testNaam";
        String startDatum = "10-06-2019";
        String eindDatum = "10-06-2020";
        String locatie = "America";
        Integer aanmaker = 1;

        // TODO De sets voor Notities & ReisItems worden nog niet getest bij de ReisItems, aangezien deze nu nog niet aangemaakt zijn.
        Set<ReisItem> reisItems;

        // Activate
        reisItem.setReisItemId(reisItemId);
        reisItem.setNaam(naam);
        reisItem.setStartDatum(startDatum);
        reisItem.setLocatie(locatie);
        reisItem.setAanmaker(aanmaker);

        // Assert
        assertEquals(reisItemId, reisItem.getReisItemId());
        assertEquals(naam, reisItem.getNaam());
        assertEquals(startDatum, reisItem.getStartDatum());
        assertEquals(eindDatum, reisItem.getEindDatum());
        assertEquals(locatie, reisItem.getLocatie());
        assertEquals(aanmaker, reisItem.getAanmaker());

    }
}
