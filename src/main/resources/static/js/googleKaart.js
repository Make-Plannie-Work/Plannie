var map;
var marker;
var geocoder;

// Meerdere mini-kaaartjes maken met marker.
function initMapsWithMarker() {
    $('.googleMapSmall').each(function(i, obj) {
    // Attributen ophalen van de <div class="googleMapSmall">kaart</div>
    var id = obj.getAttribute('data-id')
    var latitude = parseFloat(obj.getAttribute('data-latitude'))
    var longitude = parseFloat(obj.getAttribute('data-longitude'))

    // Kaart maken
    map = new google.maps.Map(document.getElementById('map'+id), {
        zoom: 12,
        center: {lat: latitude,lng: longitude}
    });

    // Marker maken
    marker = new google.maps.Marker({
        position: {lat: latitude,lng: longitude},
        map: map});
    });
}

// Grote kaart maken om een locatie op aan te geven.
function initMapAndGeo() {
    // Kaart maken
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 9,
        center: {lat: 53.187390112975216, lng: 6.563204526901245}
    });

    // Geolocator maken
    geocoder = new google.maps.Geocoder();

    // On click actie, die een marker maakt
    map.addListener('click', function(e) {
        addMarker(e.latLng, map);
    });
}

// Gebruiker plaatst een marker, deze wordt aangemaakt, en de locatie wordt opgeslagen
function addMarker(location, map) {
    // Voeg 1 marker toe aan de kaart, of verplaats de bestaande marker.
    if (marker == null) {
        marker = new google.maps.Marker({
            position: location,
            map: map
        });
    } else {
        marker.setPosition(location);
    }

    // Vind het adres onder de marker, en noteer deze in de JSP.
    geoLocatieVinden(geocoder, map, location);

    // Aangewezen coordinaten in JSP noteren.
    var inputLatitude = document.getElementById('locatieLatitude');
    var inputLongitude = document.getElementById('locatieLongitude')
    inputLatitude.value = marker.getPosition().lat();
    inputLongitude.value = marker.getPosition().lng();
}

// Adres vinden op een locatie, en noteren op de JSP.
function geoLocatieVinden(geocoder, map, location) {
    geocoder.geocode({'location': location}, function(results, status) {
    if (status === 'OK') {
        var adres;
        if (results[0]) {
            adres = results[0].formatted_address;
        } else {
            adres = 'Onbekend';
        }

        // Gevonden adres noteren in de JSP.
        var inputAdres = document.getElementById('locatieAdres')
        inputAdres.value = adres;
    } else {
        // Error message, voor het geval dat de developers wat vergeten zijn.
        window.alert('Geocoder gefaald, vanwege: ' + status);
    }
    });
}