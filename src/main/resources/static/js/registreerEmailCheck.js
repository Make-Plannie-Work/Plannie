// TODO Javascript aanroepen met een onclick actie, wanneer het registratie formulier verzonden wordt.
$(document).ready(function(){
        $('#registreer').on('click',function(event){
            var voornaam = $('#voornaam').val();
            var achternaam = $('#achternaam').val();
            var email = $('#email').val();
            var wachtwoord = $('#psw').val();
            var trancientWachtwoord = $('#trancientWachtwoord').val();

            event.preventDefault();

            gebruiker = {"voornaam" : voornaam , "achternaam" : achternaam, "email" : email, "wachtwoord" : wachtwoord, "trancientWachtwoord" : trancientWachtwoord};
            console.log(gebruiker);

        $.ajax({
               type: "POST",
               contentType : 'application/json; charset=utf-8',
               dataType : 'json',
               url: "Plannie/registreren/controle.htm",
               data: JSON.stringify(gebruiker), // Note it is important
               success :function(result) {
               // do what ever you want with data
               }
           });

        });
    });


// TODO De controller de waardes van het formulier sturen.

// TODO aan de hand van de reactie van de controller een melding weergeven op het scherm.