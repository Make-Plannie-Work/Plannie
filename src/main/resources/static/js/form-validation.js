$(document).ready(function() {
  $("#registreren").validate({
    rules: {
      voornaam : {
        required: true,
        minlength: 3
      },
      achternaam : {
              required: true,
              minlength: 3
            },
      },
      email: {
        required: true,
        email: true
      },
      wachtwoord: {
        required: {
          depends: function(elem) {
            return $("#age").val() > 50
          }
        trancientWachtwoord: required;
      }
    },
    messages : {
      voornaam: {
        minlength: "Name should be at least 3 characters"
      },
      achternaam: {
          minlength: "Name should be at least 3 characters"
        },
      email: {
        email: "The email should be in the format: abc@domain.tld"
      },
      wachtwoord: {
        required: "People with age over 50 have to enter their weight",
        number: "Please enter your weight as a numerical value"
      }
    }
  });
});