var filterDropdown = document.getElementById('filters');
var filterValue;
var mealsCard = document.getElementById('meals-card');
var exerciseCard = document.getElementById('exercise-card');
var workCard = document.getElementById('work-card');
var sleepCard = document.getElementById('sleep-card');
var meditationCard = document.getElementById('meditation-card');

filterDropdown.addEventListener('change', function() {
	filterValue = filterDropdown.options[filterDropdown.selectedIndex].value;
	
	mealsCard.style.display = "none";
	exerciseCard.style.display = "none";
	workCard.style.display = "none";
	sleepCard.style.display = "none";
	meditationCard.style.display = "none";
	
	switch(filterValue) {
	  case 'meals':
	  	mealsCard.style.display = "block";
	    break;
	  case 'exercise':
	  	exerciseCard.style.display = "block";
	    break;
	  case 'work':
	  	workCard.style.display = "block";
	    break;
	  case 'sleep':
	  	sleepCard.style.display = "block";
	    break;
	  case 'meditation':
	  	meditationCard.style.display = "block";
	    break;
	  default:
	  	mealsCard.style.display = "block";
		exerciseCard.style.display = "block";
		workCard.style.display = "block";
		sleepCard.style.display = "block";
		meditationCard.style.display = "block";
	}
  	filterDashboard();
});

function filterDashboard() {
    var request = new XMLHttpRequest();
	request.open("POST", "/updateFilter", true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.onreadystatechange = function(){
		if (request.readyState == 4) {
			document.getElementById("filter-status").innerHTML = 'Dashboard display preference saved!';
		}
	};
	request.send("filter=" + filterValue);
}

