function disableDropdown(){
	 $('select').find('option').prop('disabled', false);
	    
	    $('select').each(function() {
	       $('select').not(this).find('option[value="' + this.value + '"]').prop('disabled', 'disabled'); 
	    });

}
function addRow(tableID) {


	var table = document.getElementById(tableID);

	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);

	var colCount = table.rows[0].cells.length;

	for(var i=0; i<colCount; i++) {

		var newcell	= row.insertCell(i);

		newcell.innerHTML = table.rows[0].cells[i].innerHTML;
		// alert(newcell.childNodes);
		/*switch(newcell.childNodes[0].type) {
			case "text":
					newcell.childNodes[0].value = "";
					break;
			case "checkbox":
					newcell.childNodes[0].checked = false;
					break;
			case "select-one":
					newcell.childNodes[0].selectedIndex = 0;
					break;
		}*/
	} 
	return false;
	 
}

function deleteRow(tableID) {
	try {
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length-1;
	table.deleteRow(rowCount);
	
	/*for(var i=0; i<rowCount; i++) {
		
		
		var row = table.rows[i];
		var i=row.parentNode.rowIndex;
	    table.deleteRow(i);

		}
*/

	
	}catch(e) {
		alert(e);
	}
}


