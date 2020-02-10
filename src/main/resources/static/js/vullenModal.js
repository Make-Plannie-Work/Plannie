function vullenModal (titel, bodyText, buttonText, href) {
    $("#waarschuwingsModalTitel").text(titel)
    $("#waarschuwingsModalBody").text(bodyText)
    $('#doorgaan').text(buttonText)
    $('#doorgaan').attr("href", href);
}