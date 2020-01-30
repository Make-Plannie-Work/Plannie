  var map;
  var marker;
  var geocoder;

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 9,
    center: {lat: 53.187390112975216, lng: 6.563204526901245}
  });

  geocoder = new google.maps.Geocoder();

  map.addListener('click', function(e) {
    addMarker(e.latLng, map);
  });
}

function addMarker(location, map) {
// Voeg 1 marker toe aan de kaart, of verplaats de bestaande marker.
  if (marker == null) {
    marker = new google.maps.Marker({
    position: location,
    map: map});
  } else {
    marker.setPosition(location);
  }

// Vind het adres onder de marker.
    geoLocatieVinden(geocoder, map, location);

    // Aangewezen coordinaten en adres in JSP noteren.
    var inputLatitude = document.getElementById('locatieLatitude');
    var inputLongitude = document.getElementById('locatieLongitude')
    inputLatitude.value = marker.getPosition().lat();
    inputLongitude.value = marker.getPosition().lng();
}

function geoLocatieVinden(geocoder, map, location) {
    geocoder.geocode({'location': location}, function(results, status) {
        if (status === 'OK') {
        var adres;
            if (results[0]) {
                adres = results[0].formatted_address;
            }else{
                adres = 'Onbekend';
            }


    var inputAdres = document.getElementById('locatieAdres')
    inputAdres.value = adres;

        } else {
            window.alert('Geocoder gefaald, vanwege: ' + status);
        }
    });
}