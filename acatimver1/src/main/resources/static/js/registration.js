function changeTab(evt, tabName) {
  var i, tabPane, tablinks;
  tabPane = document.getElementsByClassName("tab-pane");
  for (i = 0; i < tabPane.length; i++) {
	  tabPane[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("nav-link");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
}
