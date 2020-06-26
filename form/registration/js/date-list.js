window.addEventListener('load', function() {
	var max = new Date().getFullYear(),
	min = max - 80,
	selectYear = document.getElementById('yearOfBirth'),
	selectDate = document.getElementById('dateOfBirth');
	
	for (var i = max; i>=min; i--){
		var opt = document.createElement('option');
		opt.value = i;
		opt.innerHTML = i;
		selectYear.appendChild(opt);
	}
	
	for (var i = 1; i<=31; i++){
		var opt = document.createElement('option');
		opt.value = i;
		opt.innerHTML = i;
		selectDate.appendChild(opt);
	}
});