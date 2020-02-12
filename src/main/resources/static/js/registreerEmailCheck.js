$.ajaxSetup({
    beforeSend: function(xhr) {
        xhr.setRequestHeader('X-CSRF-TOKEN', $('#csrfToken').attr('data-csrfToken'));
    }
});

$('#registreren').on('submit', function () {
    // De standaard 'submit' van het registratie formulier wordt uitgeschakeld, aangezien we dit met AJAX oplossen.
    event.preventDefault();

    // Er wordt een JSON gemaakt van de waarden op de JSP.
    var gebruiker = {
    "voornaam" : $('#voornaam').val(),
     "achternaam" : $('#achternaam').val(),
      "email" : $('#email').val(),
       "wachtwoord" : $('#psw').val(),
        "trancientWachtwoord" : $('#trancientWachtwoord').val()};

    // Er wordt bijgehouden hoeveel validatie fouten er zijn op het formulier.
    var fouten = 0;
    // Er wordt gekeken welke formulieren op de pagina validatie nodig hebben.
    var forms = document.getElementsByClassName('needs-validation');
    // Van elk invoerveld op de gevonden formulieren wordt gekeken of de invoer klopt.
    var validation = Array.prototype.filter.call(forms, function(form) {
        if (form.checkValidity() === false) {
            // Als er een foute invoer gevonden is, wordt deze bijgehouden.
            event.stopPropagation();
            fouten ++;
        }
        form.classList.add('was-validated');
    });

    // Als er geen validatie fouten gevonden zijn, wordt de onderstaande AJAX uitgevoerd.
    if (fouten <= 0) {
        // De JSON wordt naar de controller in GebruikerController gestuurd.
        $.ajax({
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        url: "/Plannie/registreren/controle",
        data: JSON.stringify(gebruiker),
        success :function(result) {
            // Afhankelijk van het bericht wat uit de controller in GebruikerController bevat, wordt er een melding op het scherm getoond.
            if(result === "gebruikerBestaat") {
                setWaarschuwingType('alert alert-danger');
                $("#alertTitel").text("Waarschuwing")
                $("#alertTekst").text("Dit e-mail staat al geregistreerd. Bent u uw wachtwoord vergeten? Ga dan naar de login pagina en reset uw wachtwoord");
            } else if (result === "gebruikerGeregistreerd") {
                setWaarschuwingType('alert alert-success');
                $("#alertTitel").text("Melding")
                $("#alertTekst").text("Er is een e-mail verstuurd naar het opgegeven e-mail adres. Klik in de e-mail op de link om de registratie compleet te maken.");
            } else if(result === "tokenLooptNog") {
                setWaarschuwingType('alert alert-danger');
                $("#alertTitel").text("Waarschuwing")
                $("#alertTekst").text("Er is al een registratie e-mail naar dit email adres verstuurd. Controleer of de e-mail in uw spamfolder terechtgekomen is.");
            } else {
                setWaarschuwingType('alert alert-success');
                $("#alertTitel").text("Melding")
                $("#alertTekst").text("Er is een nieuwe token naar uw e-mail adres gestuurd. Klik in de e-mail op de link om de registratie compleet te maken.");
            }
            $('#registratieMelding').removeClass('fade');
        },
            error : function(e) {
            console.log("ERROR: ", e);
            }
        });
    }
});

// Methode om de waarschuwing te sluiten.
$('.close').click(function () {
  $(this).parent().addClass('fade'); // hides alert with Bootstrap CSS3 implem
});

// Methode om de waarschuwing van type te veranderen.
function setWaarschuwingType(type) {
    $('#registratieMelding').removeClass('alert alert-danger');
    $('#registratieMelding').removeClass('alert alert-success');

    $('#registratieMelding').addClass(type);
}