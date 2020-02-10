var ledenLijst = document.getElementById("groepsLeden")
var groepId = $('#groepId').val();

var ourRequest = new XMLHttpRequest();
ourRequest.open('GET', 'http://localhost:8080/' + groepId + '/groepsLeden');
ourRequest.onload= function() {
    var alleLeden = JSON.parse(ourRequest.responseText);
    var htmlString = "";
    for (i = 0; i < alleLeden.length; i++) {
        htmlString += '<tr><td id="groepsledenNamen">' + alleLeden[i].voornaam + ' ' + alleLeden[i].achternaam + '</td><td><button id=\"groepsledenButton\" onClick="verwijderUitGroep(' + alleLeden[i].gebruikersId + ')"><i class="fas fa-trash-alt"></i></button></td></tr>';
    }
    ledenLijst.insertAdjacentHTML('beforeend', htmlString);
};
ourRequest.send();

 $(document).ready(function() {
 var groepId = $('#groepId').val();
    $("#Zoek_Gebruikers").autocomplete({
        source:  "/" + groepId + "/zoekGebruikers",
        minLength: 2
     }).data("ui-autocomplete")._renderItem = function( tr, item ) {
     var inner_html = '<input id="groepsledenButtonAutocomplete" type="button" onClick="voegToeAanGroep(' + item.gebruikersId + ')\" value=\"'+ item.voornaam + ' ' + item.achternaam + '\"></input>';
     return $( "<td></td>" )
     .data( "item.autocomplete", item )
     .append(inner_html)
     .appendTo( tr );
    };
});

        function verwijderUitGroep(gebruikerId){
            var ourRequest = new XMLHttpRequest();
            ourRequest.open('GET', 'http://localhost:8080/' + groepId + '/VerwijderLedenUitGroep/' + gebruikerId);
            ourRequest.send();
            ourRequest.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                $( "#groepsLeden tr" ).remove();
                RefreshTable(groepId);
                }
            };
        }

        function voegToeAanGroep(gebruikerId){
            var ourRequest = new XMLHttpRequest();
            ourRequest.open('GET', 'http://localhost:8080/groepDetail/' + groepId + '/voegGebruikerToeAanGroep/' + gebruikerId);
            ourRequest.send();
            ourRequest.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                $( "#groepsLeden td" ).remove();
                RefreshTable(groepId);
                }
            };
        }

function RefreshTable(groepId) {
   ourRequest.open('GET', 'http://localhost:8080/' + groepId + '/groepsLeden');
   ourRequest.onload= function() {
       var alleLeden = JSON.parse(ourRequest.responseText);
       var htmlString = "";
       for (i = 0; i < alleLeden.length; i++) {
           htmlString += '<tr><td id="groepsledenNamen">' + alleLeden[i].voornaam + ' ' + alleLeden[i].achternaam + '</td><td><button id=\"groepsledenButton\" onClick="verwijderUitGroep(' + alleLeden[i].gebruikersId + ')"><i class="fas fa-trash-alt"></i></button></td></tr>';
       }
       ledenLijst.insertAdjacentHTML('beforeend', htmlString);
   };
   ourRequest.send();
}