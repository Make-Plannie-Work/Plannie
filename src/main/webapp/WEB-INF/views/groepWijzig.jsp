<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<style>
      img {
      max-width: 100%;
    }

    .cropper-view-box,
    .cropper-face {
      border-radius: 50%;
    }
  </style>
<div class="container mt-4">
    <div class="row">
        <form:form id="groepsNaamWijzigenForm" action= "${pageContext.request.contextPath}/groepDetail/${groep.groepId}/groepWijzig" method="post" modelAttribute="groepsNaamWijzigingsFormulier">
            <input type="text" name="groepsNaam" required="required" value="${groep.groepsNaam}">
            <input id="groepsNaamWijzigen" type="submit" class="btn btn-primary" value="Wijzig groepsnaam">
        </form:form>

    </div>
    <hr class="my-4">
    <div class="row">
        <form:form id="groepBeheerderWijzigenForm" action = "${pageContext.request.contextPath}/groepDetail/${groep.groepId}/groepBeheerderWijzig" method="post">
            <select name="beheerderEmail" size="5" style="width: 100%">
                <c:forEach items="${groepsLedenLijst}" var="groepslid">
                    <c:if test="${groepslid.gebruikersId != groep.aanmaker}">
                        <option value="${groepslid.email}">${groepslid.voornaam} ${groepslid.achternaam}</option>
                    </c:if>
                </c:forEach>
            </select>
            <br>
            <input id="groepBeheerderWijzigen" type="submit" class="btn btn-primary" value="Wijzig groep beheerder">
        </form:form>

    </div>
    <hr class="my-4">

    <div class="img-container">
        <img id="image" src="${pageContext.request.contextPath}/images/groep/${groep.imagePath}" alt="Picture">
        <div id="result"></div>
    </div>


    <p>
        <button type="button" id="button">Crop</button>
        <button type="button" onclick="saveImage()" id="save" style="visibility: hidden;">Sla op</button>
    </p>


    <div class="row">
        <form:form id="groepsAfbeeldingWijzigenForm" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/${groep.groepId}/uploadImage" >

                    <td><input type="file" name="imageFile" accept="image/*"/></td>

                    <td><input type="submit" name="uploadImage" value="Upload" /></td>

        </form:form>
    </div>

    <a href="${pageContext.request.contextPath}/groepDetail/${groep.groepId}"><button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button></a>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/cropperjs/1.5.6/cropper.js"></script>
<script>
var croppedCanvas;
var roundedCanvas;
var roundedImage;


    function getRoundedCanvas(sourceCanvas) {

      var canvas = document.createElement('canvas');
      var context = canvas.getContext('2d');
      var width = sourceCanvas.width;
      var height = sourceCanvas.height;

      canvas.width = width;
      canvas.height = height;
      context.imageSmoothingEnabled = true;
      context.drawImage(sourceCanvas, 0, 0, width, height);
      context.globalCompositeOperation = 'destination-in';
      context.beginPath();
      context.arc(width / 2, height / 2, Math.min(width, height) / 2, 0, 2 * Math.PI, true);
      context.fill();
      return canvas;
    }

    window.addEventListener('DOMContentLoaded', function () {
      var image = document.getElementById('image');
      var button = document.getElementById('button');
      var save = document.getElementById('save');
      var result = document.getElementById('result');
      result.innerHTML = '<img src="http://localhost:8080/Plannie/images/groep/${groep.imagePath}"/>';
      var croppable = false;
      var cropper = new Cropper(image, {
        aspectRatio: 1,
        viewMode: 1,
        ready: function () {
          croppable = true;
        },

      });


      button.onclick = function () {

        if (!croppable) {
          return;
        }

        // Crop
        croppedCanvas = cropper.getCroppedCanvas();

        // Round
        roundedCanvas = getRoundedCanvas(croppedCanvas);

        // Show
        roundedImage = document.createElement('img');
        roundedImage.src = roundedCanvas.toDataURL();

        result.innerHTML = '';
        result.appendChild(roundedImage);
        save.style.visibility= 'visible';

      };
    });


     function saveImage() {
        var ourRequest = new XMLHttpRequest();
           ourRequest.open('POST', contextPath + '/' +  groepId + '/saveCroppedImage/' + roundedImage.src)
           ourRequest.send();
           ourRequest.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
             location.reload();
             }
             };
          }

  </script>