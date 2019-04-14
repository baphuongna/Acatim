var getValue5 = document.getElementById("valueProgress5").innerHTML;
var getValue4 = document.getElementById("valueProgress4").innerHTML;
var getValue3 = document.getElementById("valueProgress3").innerHTML;
var getValue2 = document.getElementById("valueProgress2").innerHTML;
var getValue1 = document.getElementById("valueProgress1").innerHTML;
document.getElementById("progressMake5").style.width = getValue5 + "%";
document.getElementById("progressMake4").style.width = getValue4 + "%";
document.getElementById("progressMake3").style.width = getValue3 + "%";
document.getElementById("progressMake2").style.width = getValue2 + "%";
document.getElementById("progressMake1").style.width = getValue1 + "%";

jQuery(document).ready(function($){
    
	$(".btnrating").on('click',(function(e) {
	
	var previous_value = $("#selected_rating").val();
	
	var selected_value = $(this).attr("data-attr");
	$("#selected_rating").val(selected_value);
	
	$(".selected-rating").empty();
	$(".selected-rating").html(selected_value);
	
	for (i = 1; i <= selected_value; ++i) {
	$("#rating-star-"+i).toggleClass('btn-warning');
	$("#rating-star-"+i).toggleClass('btn-default');
	}
	
	for (ix = 1; ix <= previous_value; ++ix) {
	$("#rating-star-"+ix).toggleClass('btn-warning');
	$("#rating-star-"+ix).toggleClass('btn-default');
	}
	
	}));
	
		
});

$(document).ready(function() {
	$("#myModal").modal('show');
        $("#page3").show();
        $("#page4").hide();
        $("#main_popup").hide();
});
$("#ok2,#arr2").click(function() {
    $("#page3").hide();
    $("#page4").show();
    $("#main_popup").hide();
});

new WOW().init();

$(function() {
    $('.mat-input-outer label').click(function() {
        $(this).prev('input').focus();
    });
    $('.mat-input-outer input').focusin(function() {
        $(this).next('label').addClass('active');
    });
    $('.mat-input-outer input').focusout(function() {
        if (!$(this).val()) {
            $(this).next('label').removeClass('active');
        } else {
            $(this).next('label').addClass('active');
        }
    });
});

$(document).ready(function() {
    $('.rating').magicRatingInit({
        success: function(magicRatingWidget, rating) {
            alert(rating);
        },
        iconGood: "fa-bicycle",
        iconBad: "fa-car",
    });
    $(".rating2").magicRatingInit({
        success: function(magicRatingWidget, rating) {
            alert(rating);
        }
    })
});

var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-36251023-1']);
_gaq.push(['_setDomainName', 'jqueryscript.net']);
_gaq.push(['_trackPageview']);
(function() {
    var ga = document.createElement('script');
    ga.type = 'text/javascript';
    ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(ga, s);
})();

//Init function
var nameOfIcon;		
function myFunction(name) {
 nameOfIcon=name.getAttribute("name");
 }	 

$.fn.magicRatingInit = function(config) {
// Init each widget return by the selector
for (widget of $(this)) {
var magicRatingWidget = $(widget);
//// Get datas ////
// Icon +
if (magicRatingWidget.data("iconGood") == null) {
magicRatingWidget.data("iconGood", config.iconGood != null ? config.iconGood : "fa-star");
};
// Icon -
if (magicRatingWidget.data("iconBad") == null) {
magicRatingWidget.data("iconBad", config.iconBad != null ? config.iconBad : "fa-star-o");
};
// Max mark
if (magicRatingWidget.data("maxMark") == null) {
magicRatingWidget.data("maxMark", config.maxMark != null ? config.maxMark : 5);
};
/*
console.log(magicRatingWidget.data("iconGood"));
console.log(magicRatingWidget.data("iconBad"));
*/
// Clear the widget
magicRatingWidget.html("");
// Init icons
for (i = 1; i <= magicRatingWidget.data("maxMark"); i++) {
if (i <= magicRatingWidget.data("currentRating")) {
    magicRatingWidget.append('<i class=" ' + magicRatingWidget.data("iconGood") + ' magic-rating-icon" aria-hidden="true" data-default=true data-rating=' + i + '></i>');
} else {
    magicRatingWidget.append('<i class=" ' + magicRatingWidget.data("iconBad") + ' magic-rating-icon" aria-hidden="true" data-default=false data-rating=' + i + '></i>');
}
}
// Init reset handler
magicRatingWidget.on("mouseleave", function() {
var widget = $(this);

// console.log("mouseleave");
// console.log(widget.data("iconGood"));
// console.log(widget.data("iconBad"));

widget.children().each(function() {
    var icon = $(this);
    if (icon.data("default") && !icon.hasClass("fa-star")) {
        icon.removeClass(widget.data("iconBad"));
        icon.addClass(widget.data("iconGood"));
    } else if (!icon.data("default") && !icon.hasClass("fa-star-o")) {
        icon.removeClass(widget.data("iconGood"));
        icon.addClass(widget.data("iconBad"));
    }
});
});
	
	var sumFeedback=0;
	var hash = {};
	hash['Teaching quality'] = 1; 
	hash['Location'] = 1;
	hash['Internet'] = 1;
	hash['safety'] = 1;
	hash['Happiness'] = 1;
	hash['Reputation'] = 1;
// Init click handler
magicRatingWidget.on("click", ".magic-rating-icon", function() {
// Get rating
var icon = $(this);
var widget = icon.parent();
var rating1=icon.data("rating");
var rating = parseInt(rating1);
var nameIcon = widget.data("iconGood");
         if("Teaching quality" === nameOfIcon){
		 hash['Teaching quality'] = rating;
		 }
		 if("Location" === nameOfIcon){
		 hash['Location'] = rating;
		 }
		 if("Internet" === nameOfIcon){
		 hash['Internet'] = rating;
		 }
		 if("safety" === nameOfIcon){
		 hash['safety'] = rating;
		 }
		 if("Happiness" === nameOfIcon){
		 hash['Happiness'] = rating;
		 }
		 if("Reputation" === nameOfIcon){
		 hash['Reputation'] = rating;
		 }
	console.log(hash)
	sumFeedback= hash['Teaching quality']+ hash['Location']+hash['Internet']+hash['safety']+hash['Happiness']+hash['Reputation'];
		
 document.getElementById("demo").innerHTML = parseFloat(sumFeedback / 6).toFixed(2);


// Update rating
widget.children().each(function() {
    if ($(this).data("rating") <= rating) {
        if (!$(this).hasClass(widget.data("iconGood"))) {
            $(this).removeClass(widget.data("iconBad"));
            $(this).addClass(widget.data("iconGood"));
        };
        $(this).data("default", true);
    } else {
        if (!$(this).hasClass(widget.data("iconBad"))) {
            $(this).removeClass(widget.data("iconGood"));
            $(this).addClass(widget.data("iconBad"));
        }
        $(this).data("default", false);
    }
});
//var callbackSuccess = config.success.bind(null, widget, rating);//get data-current-rating
//callbackSuccess();
});
// Init hover icons
magicRatingWidget.on("mouseenter", ".magic-rating-icon", function() {
var icon = $(this);
var rating = icon.data("rating");
var widget = icon.parent();
/*
console.log("mouseenter");
console.log(widget.data("iconGood"));
console.log(widget.data("iconBad"));
*/
widget.children().each(function() {
    if ($(this).data("rating") <= rating) {
        if (!$(this).hasClass(widget.data("iconGood"))) {
            $(this).removeClass(widget.data("iconBad"));
            $(this).addClass(widget.data("iconGood"));
        };
    } else {
        if (!$(this).hasClass(widget.data("iconBad"))) {
            $(this).removeClass(widget.data("iconGood"));
            $(this).addClass(widget.data("iconBad"));
        }
    }
});
});
}
};