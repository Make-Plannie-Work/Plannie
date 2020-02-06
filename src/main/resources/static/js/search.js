 $(document).ready(function() {
 var groepId = $('#groepId').val();
    $("#Zoek_Gebruikers").autocomplete({
        source:  "/" + groepId + "/zoekGebruikers",
        minLength: 2,
            focus: function( event, ui ) {
            return false;
        },
        select: function ( event, ui ) {
        window.location.href = ui.item.url;
        }
     }).data("ui-autocomplete")._renderItem = function( ul, item ) {

     var inner_html = '<a id="voeg${{' + item.voornaam +'}}ToeAanGroep" href="/groepDetail/' + groepId + '/voegGebruikerToeAanGroep/' + item.gebruikersId + '"><p><i class="fas fa-plus"></i> ' + item.voornaam + ' ' + item.achternaam + '</p></a>';

     return $( "<li></li>" )
     .data( "item.autocomplete", item )
     .append(inner_html)
     .appendTo( ul );
    };
});

