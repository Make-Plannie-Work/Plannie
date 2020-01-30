  let map;
  var marker;

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 9,
    center: {lat: 53.187390112975216, lng: 6.563204526901245}
  });

  map.addListener('click', function(e) {
    addMarker(e.latLng, map);
  });
}

// define function to add marker at given lat & lng
function addMarker(location, map) {
  if (marker == null) {
    marker = new google.maps.Marker({
    position: location,
    map: map});
  } else {
    marker.setPosition(location);
  }
}