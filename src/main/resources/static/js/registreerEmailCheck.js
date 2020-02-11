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

            var gebruiker = {"voornaam" : voornaam , "achternaam" : achternaam, "email" : email, "wachtwoord" : wachtwoord, "trancientWachtwoord" : trancientWachtwoord};
            console.log(gebruiker);

            document.getElementById("registratieMelding").style.display = "alert alert-danger";

        $.ajax({
               type: "POST",
               contentType : 'application/json; charset=utf-8',

               url: "/Plannie/registreren/controle",
               data: JSON.stringify(gebruiker),
               success :function(result) {


               if(result === "gebruikerBestaat") {
                    document.getElementById("registratieMelding").value = "Dit e-mail staat al geregistreerd. Bent u uw wachtwoord vergeten? Ga dan naar de login pagina en reset uw wachtwoord"}
               //TODO wat van de controller terug komt, afhankelijk van wat er terug komt, moet er een melding op scherm komen
               console.log(result)
               },           error : function(e) {
                                            console.log("ERROR: ", e);
                                        }

           });

        });
    });
//gebruikerBestaat
//tokenLooptNog
//tokenNieuw
//gebruikerGeregistreerd

// TODO De controller de waardes van het formulier sturen.

// TODO aan de hand van de reactie van de controller een melding weergeven op het scherm.