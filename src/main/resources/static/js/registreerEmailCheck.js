// TODO Javascript aanroepen met een onclick actie, wanneer het registratie formulier verzonden wordt.
$.ajaxSetup({
    beforeSend: function(xhr) {
        xhr.setRequestHeader('X-CSRF-TOKEN', $('#csrfToken').attr('data-csrfToken'));
    }
});

$(document).ready(function(){
    $('#registreer').on('click',function(event){
        var voornaam = $('#voornaam').val();
        var achternaam = $('#achternaam').val();
        var email = $('#email').val();
        var wachtwoord = $('#psw').val();
        var trancientWachtwoord = $('#trancientWachtwoord').val();

        event.preventDefault();

        var gebruiker = {
        "voornaam" : voornaam ,
         "achternaam" : achternaam,
          "email" : email,
           "wachtwoord" : wachtwoord,
            "trancientWachtwoord" : trancientWachtwoord};





        $.ajax({
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        url: "/Plannie/registreren/controle",
        data: JSON.stringify(gebruiker),
        success :function(result) {
            //TODO wat van de controller terug komt, afhankelijk van wat er terug komt, moet er een melding op scherm komen
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
    });
});

$('.close').click(function () {
  $(this).parent().addClass('fade'); // hides alert with Bootstrap CSS3 implem
});

function setWaarschuwingType(type) {
    $('#registratieMelding').removeClass('alert alert-danger');
    $('#registratieMelding').removeClass('alert alert-success');

    $('#registratieMelding').addClass(type);
}