
     function saveImage() {
           var ourRequest = new XMLHttpRequest();
           ourRequest.open('GET', contextPath + '/' +  groepId + '/saveCroppedImage/' + roundedImage.src)
           ourRequest.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
             location.reload();
             }
             };
          }