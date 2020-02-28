<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<input type="hidden" id="imagePath" value="${groep.imagePath}"/>

<div class="col" style="width: 100%;">
<div class="row mx-auto" style="width: 100%;">
    <div class="row mx-auto" style="width: 100%;">
        <div class="img-container mx-auto" style="width: 100%;">
            <div class="col col-3">
                <div class="preview"></div>
            </div>
            <div id="result" class="mx-auto"></div>
            <button class="btn btn-primary mt-3 mb-3" type="button" data-toggle="collapse" data-target="#wijzigAfbeelding" aria-expanded="false" aria-controls="collapseExample">
                Wijzig afbeelding
            </button> <button class="btn btn-primary mt-3 mb-3" type="button" onclick="saveImage('${groep.imagePath}')" id="save" style="visibility: hidden;">Sla op</button>

            <div class="collapse" id="wijzigAfbeelding">
                <div style="max-height: 400px">
                    <img id="image" class="cropperPlannieImg" src="${pageContext.request.contextPath}/images/groep/${groep.imagePath}" style="height:200px"  alt="Picture">
                </div>
                <p>${groep.imagePath}</p>
                <button type="button" id="button">Crop</button>

                <form:form id="groepsAfbeeldingWijzigenForm" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/${groep.groepId}/uploadImage" >
                    <td><input type="file" name="imageFile" accept="image/*"/></td>
                    <td><input type="submit" name="uploadImage" value="Upload" /></td>
                </form:form>
            </div>

            
        </div>
    </div>


</div>
</div>

<hr class="my-4">

<div class="col">
    <div class="row" style="width: 100%;">
        <form:form id="groepsNaamWijzigenForm" action= "${pageContext.request.contextPath}/groepDetail/${groep.groepId}/groepWijzig" method="post" style="width: 100%;" modelAttribute="groepsNaamWijzigingsFormulier">
            <input type="text" name="groepsNaam" required="required" value="${groep.groepsNaam}">
            <input id="groepsNaamWijzigen" type="submit" class="btn btn-primary" value="Wijzig groepsnaam">
        </form:form>
    </div>

    <hr class="my-4">
    <div class="row">
        <form:form id="groepBeheerderWijzigenForm" action = "${pageContext.request.contextPath}/groepDetail/${groep.groepId}/groepBeheerderWijzig" method="post">
            <select name="beheerderEmail" size="5" style="width: 100%" required="required">
                <c:forEach items="${groepsLedenLijst}" var="groepslid">
                    <c:if test="${groepslid.gebruikersId != groep.aanmaker}">
                        <option value="${groepslid.email}">${groepslid.voornaam} ${groepslid.achternaam}</option>
                    </c:if>
                </c:forEach>
            </select>
            <br>
            <input id="groepBeheerderWijzigen" type="submit" class="btn-primary" value="Wijzig groep beheerder">
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
      context.arc(width / 1, height / 1, Math.min(width, height) / 2, 0, 0, true);
      context.fill();
      return canvas;
    }

    window.addEventListener('DOMContentLoaded', function () {
      var image = document.getElementById('image');
      var button = document.getElementById('button');
      var save = document.getElementById('save');
      var result = document.getElementById('result');

      result.innerHTML = '<img src="http://localhost:8080/Plannie/images/groep/${groep.imagePath}" style="width: 80%; border-radius: 99em;  border: 5px solid #eee; box-shadow: 0 3px 2px rgba(0, 0, 0, 0.3);"/>';
      var croppable = false;
      var cropper = new Cropper(image, {
            display: 'block',
            viewMode: 1,
            dragMode: 'move',
            aspectRatio: 1,
            autoCropArea: 0.5,
            minContainerWidth: 300,
            minContainerHeight: 300,
            minCropBoxWidth: 50,
            minCropBoxHeight: 50,
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

        // Show
        croppedImage = document.createElement('img');
        croppedImage.src = croppedCanvas.toDataURL();

        result.innerHTML = '';
        result.style.width= '80%';
        result.appendChild(croppedImage);
        save.style.visibility= 'visible';

      };
    });


     function saveImage(imagePath) {
     console.log(imagePath)
        croppedCanvas.toBlob(function (blob) {
        var ourRequest = new XMLHttpRequest();
           ourRequest.open('POST', contextPath + '/' +  groepId + '/uploadImage');
           var formData = new FormData();
           formData.append('_csrf',  $('input[name="_csrf"]').attr('value'));
           formData.append('imageFile', blob, imagePath);
           ourRequest.send(formData);
           ourRequest.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
             location.reload();
             }
             };});
          }

  </script>