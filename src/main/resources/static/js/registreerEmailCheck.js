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

        $.ajax({
               type: "POST",
               contentType : 'application/json; charset=utf-8',

               url: "/Plannie/registreren/controle",
               data: JSON.stringify(gebruiker), // Note it is important
               success :function(result) {
               // do what ever you want with data
               console.log(result)
               },           error : function(e) {
                                            console.log("ERROR: ", e);
                                        }

           });

        });
    });


// TODO De controller de waardes van het formulier sturen.

// TODO aan de hand van de reactie van de controller een melding weergeven op het scherm.