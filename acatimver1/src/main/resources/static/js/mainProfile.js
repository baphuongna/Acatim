jQuery( document ).ready(function( $ ) {
"use strict"
/*-----------------------------------------------------------------------------------*/
/*    PORTFOLIO FILTER
/*-----------------------------------------------------------------------------------*/
$('#Container').mixItUp();
});



/*js for ggapi*/


var placeSearch, autocomplete;

var componentForm = {
	street_number : 'short_name',
	route : 'long_name',
	locality : 'long_name',
	administrative_area_level_1 : 'short_name',
	country : 'long_name',
	postal_code : 'short_name'
};

function initAutocomplete() {
	// Create the autocomplete object, restricting the search predictions to
	// geographical location types.
	autocomplete = new google.maps.places.Autocomplete(document
			.getElementById('autocomplete'), {
		types : [ 'geocode' ]
	});

	// Avoid paying for data that you don't need by restricting the set of
	// place fields that are returned to just the address components.
	autocomplete.setFields('address_components');

	// When the user selects an address from the drop-down, populate the
	// address fields in the form.
	autocomplete.addListener('place_changed', fillInAddress);
}

function fillInAddress() {
	// Get the place details from the autocomplete object.
	var place = autocomplete.getPlace();

	for ( var component in componentForm) {
		document.getElementById(component).value = '';
		document.getElementById(component).disabled = false;
	}

	// Get each component of the address from the place details,
	// and then fill-in the corresponding field on the form.
	for (var i = 0; i < place.address_components.length; i++) {
		var addressType = place.address_components[i].types[0];
		if (componentForm[addressType]) {
			var val = place.address_components[i][componentForm[addressType]];
			document.getElementById(addressType).value = val;
		}
	}
}

// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var geolocation = {
				lat : position.coords.latitude,
				lng : position.coords.longitude
			};
			var circle = new google.maps.Circle({
				center : geolocation,
				radius : position.coords.accuracy
			});
			autocomplete.setBounds(circle.getBounds());
		});
	}

}

$('.modal-child').on('show.bs.modal', function() {
	var modalParent = $(this).attr('data-modal-parent');
	$(modalParent).css('opacity', 0);
});

$('.modal-child').on('hidden.bs.modal', function() {
	var modalParent = $(this).attr('data-modal-parent');
	$(modalParent).css('opacity', 1);
});



function myStudent() {
	var x = document.getElementById("inputRoll").value;
	console.log(x + "hieu")
	if (x == 1) {
		console.log("hieu  hs")
		document.getElementById("description").style.display = "none";
		document.getElementById("inputGender").style.display = "block";
		document.getElementById("inputLabel").style.display = "block";
		document.getElementById("dob1").style.display = "block";
	}
	if (x == 2) {
		console.log("hieu  gv")
		document.getElementById("description").style.display = "block";
		document.getElementById("inputGender").style.display = "block";
		document.getElementById("inputLabel").style.display = "block";
		document.getElementById("dob1").style.display = "block";
	}
	if (x == 3) {
		console.log("hieu  tt")
		document.getElementById("inputGender").style.display = "none";
		document.getElementById("inputLabel").style.display = "none";
		document.getElementById("description").style.display = "block";
		document.getElementById("dob1").style.display = "none";
	}
}



function textwrite() {
	thetext = document.getElementById("description").value;
	var myLineBreak = thetext.replace(/\r\n|\r|\n/g, "</br>");

	document.getElementById('descriptionSubmit').value = myLineBreak;
}
var check = function() {
	  if (document.getElementById('pass').value ==
	    document.getElementById('passConfirm').value) {
	    document.getElementById('message').style.color = 'green';
	    document.getElementById('message').innerHTML = 'Đã trùng khớp với mật khẩu';
	  } else {
	    document.getElementById('message').style.color = 'red';
	    document.getElementById('message').innerHTML = 'Chưa trùng khớp với mật khẩu';
	  }
	}





