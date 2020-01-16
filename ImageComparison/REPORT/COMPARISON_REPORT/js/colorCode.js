function doColor(){
var table = document.getElementById("t01"); 
var cells = table.getElementsByTagName("td"); 
for (var i = 0; i < cells.length; i++) { 
    if (cells[i].innerHTML == "Fail") { 
		cells[i].style.backgroundColor = "maroon";
		cells[i].style.color = "maroon";
    }
	else if(cells[i].innerHTML == "Pass"){
	cells[i].style.backgroundColor = "green";
	cells[i].style.color = "green";
	}
	else{
	
	}
}
}