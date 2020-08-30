<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Based from template.jsp -->


<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="DataTables/datatables.min.css"/>
	<link rel="stylesheet" type="text/css" href="css/navbar.css"/>
	
    <title>NatPro : ${searchKey}</title>
  </head>
  <body>
  	<!-- INCLUDE NAV BAR HTML -->
 	<%@include file="navbarnix.html"%>

    <!-- HTML CODE -->
    <div class="jumbotron jumbotron-fluid" style="text-align:center">
		<div class="container">
			<h1 class="display-4">Validation</h1>
			<p class="lead">Validate the gathered data from the processed files here</p>
		</div>
		
		<!-- TEMPLATE FOR THE TABLE -->
			
		<!--
			<h3 style="padding-top:50px; padding-bottom:20px">Compound(s)</h3>
				<div class="row" id="toggleCompound" value="0">
					<div class="col-12" style="text-align:left">
						<button id="ccSelect" type="button" class="btn btn-primary btn-sm" onclick="selectAll(0)" value="0">Select All</button>
						<button id="ccApprove" type="button" class="btn btn-success btn-sm" onclick="approveSelected(0)">Approve Selected</button>
						<button id="ccReject" type="button" class="btn btn-danger btn-sm" onclick="rejectSelected(0)">Reject Selected</button>
					</div>
				</div>
				
				<div id="CompoundGroup">
					<div id="CompoundEntry0" class="form-row" style="padding-top:5px">
						<div class="col-2" style="text-align:left">
							<input class="form-check-input" type="checkbox" id="CompoundLabel0" name="cc" value="0" onchange="changeSelect(0)">
		  					<label class="form-check-label" for="CompoundLabel0">Document</label>
						</div>
						<div class="col-3" id="ccPlant0">
						plant
						</div>
						<div class="col-2" id="ccRelation0">
						contains
						</div>
						<div class="col-3" id="ccRelationObject0">
						chemical
						</div>
						<div class="col-2" style="text-align:right">
							<button type="button" class="btn btn-success btn-sm" onclick="approveEntry(0,0)" data-toggle="tooltip" data-placement="top" title="approve entry"><i class="fa fa-check" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-danger btn-sm" onclick="rejectEntry(0,0)" data-toggle="tooltip" data-placement="top" title="reject entry"><i class="fa fa-times" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-primary btn-sm" onclick="viewEntry(0,0)" data-toggle="tooltip" data-placement="top" title="view entry"><i class="fa fa-eye" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-secondary btn-sm" onclick="editEntry(0,0)" data-toggle="tooltip" data-placement="top" title="edit entry"><i class="fa fa-pencil" aria-hidden="true"></i></button>
						</div>
					</div>
				</div>
		 -->
		
		<div class="container">
			<div class="row">
				<div class="col-12">
					<span>debugging only *pls hide*</span>
					<button type="button" class="btn btn-primary btn-sm" onclick="addEntry(0)">Compound</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="addEntry(1)">Common Plant Name</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="addEntry(2)">Illness</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="addEntry(3)">Location</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="addEntry(4)">Synonym</button>
				</div>
			</div>
	
			<form>
				<h3 style="padding-top:50px; padding-bottom:20px">Compound(s)</h3>
				<div class="row" id="substituteCompound" value="1">
					<div class="col-12">
						empty.
					</div>
				</div>
				<div class="row" id="toggleCompound" value="0" hidden>
					<div class="col-12" style="text-align:left">
						<button id="ccSelect" type="button" class="btn btn-primary btn-sm" onclick="selectAll(0)" value="0">Select All</button>
						<button id="ccApprove" type="button" class="btn btn-success btn-sm" onclick="approveSelected(0)">Approve Selected</button>
						<button id="ccReject" type="button" class="btn btn-danger btn-sm" onclick="rejectSelected(0)">Reject Selected</button>
					</div>
				</div>
				
				<div id="CompoundGroup">
					
				</div>
				
				
				<h3 style="padding-top:50px; padding-bottom:20px">Common Plant Name(s)</h3>
				<div class="row" id="substituteCommonPlantName" value="1">
					<div class="col-12">
						empty.
					</div>
				</div>
				<div class="row" id="toggleCommonPlantName" value="0" hidden>
					<div class="col-12" style="text-align:left">
						<button id="cpnSelect" type="button" class="btn btn-primary btn-sm" onclick="selectAll(1)" value="0">Select All</button>
						<button id="cpnApprove" type="button" class="btn btn-success btn-sm" onclick="approveSelected(1)">Approve Selected</button>
						<button id="cpnReject" type="button" class="btn btn-danger btn-sm" onclick="rejectSelected(1)">Reject Selected</button>
					</div>
				</div>
				
				<div id="CommonPlantNameGroup">
					
				</div>
				
				<h3 style="padding-top:50px; padding-bottom:20px">Illness(es)</h3>
				<div class="row" id="substituteIllness" value="1">
					<div class="col-12">
						empty.
					</div>
				</div>
				<div class="row" id="toggleIllness" value="0" hidden>
					<div class="col-12" style="text-align:left">
						<button id="illSelect" type="button" class="btn btn-primary btn-sm" onclick="selectAll(2)" value="0">Select All</button>
						<button id="illApprove" type="button" class="btn btn-success btn-sm" onclick="approveSelected(2)">Approve Selected</button>
						<button id="illReject" type="button" class="btn btn-danger btn-sm" onclick="rejectSelected(2)">Reject Selected</button>
					</div>
				</div>
				
				<div id="IllnessGroup">
					
				</div>
				
				<h3 style="padding-top:50px; padding-bottom:20px">Location(s)</h3>
				<div class="row" id="substituteLocation" value="1">
					<div class="col-12">
						empty.
					</div>
				</div>
				<div class="row" id="toggleLocation" value="0" hidden>
					<div class="col-12" style="text-align:left">
						<button id="locSelect" type="button" class="btn btn-primary btn-sm" onclick="selectAll(3)" value="0">Select All</button>
						<button id="locApprove" type="button" class="btn btn-success btn-sm" onclick="approveSelected(3)">Approve Selected</button>
						<button id="locReject" type="button" class="btn btn-danger btn-sm" onclick="rejectSelected(3)">Reject Selected</button>
					</div>
				</div>
				
				<div id="LocationGroup">
					
				</div>
				
				<h3 style="padding-top:50px; padding-bottom:20px">Synonyms(s)</h3>
				<div class="row" id="substituteSynonym" value="1">
					<div class="col-12">
						empty.
					</div>
				</div>
				<div class="row" id="toggleSynonym" value="0" hidden>
					<div class="col-12" style="text-align:left">
						<button id="illSelect" type="button" class="btn btn-primary btn-sm" onclick="selectAll(4)" value="0">Select All</button>
						<button id="illApprove" type="button" class="btn btn-success btn-sm" onclick="approveSelected(4)">Approve Selected</button>
						<button id="illReject" type="button" class="btn btn-danger btn-sm" onclick="rejectSelected(4)">Reject Selected</button>
					</div>
				</div>
				
				<div id="SynonymGroup">
					
				</div>
				
			</form>

		</div>
	</div>
	
    
    <!-- INCLUDE FOOTER HTML -->
    <%@include file="_includeFooter.html" %>
       
    <script>
    	/* -- GLOBAL VARIABLES -- */
    	
    	//values for the counter
    	var ccCtr, cpnCtr, illCtr, locCtr, synCtr;
    	
    	// initialize the counters at -1 (because when calling the addEntry(), it automatically increments +1)
    	// TODO: If ctr == -1, the category is empty and can be invisible from the user
    	ccCtr = cpnCtr = illCtr = locCtr = synCtr = -1; 
    	
    
	    /*
	    	CHEATSHEET
	    
	    	tValue = table Value
	    	0 = cc = compound
	    	1 = cpn = common plant name
	    	2 = ill = illness
	    	3 = loc = location
	    	4 = syn = synonym
	    	
	    	cValue = counter Value (nakalimutan ko kasi kung ganito rin yung pValue)
	    */
	    
	    function checkItems() {
	    	var items;
	    	var subComponent;
	    	var toggleComponent;
	    	
	    	items = document.getElementsByName('cc');
	    	subComponent = document.getElementById('substituteCompound');
	    	toggleComponent = document.getElementById('toggleCompound');
	    	if (items.length != 0) { // list not empty
	    		subComponent.value = 0;
	    		subComponent.hidden = true;
	    		toggleComponent.value = 1;
	    		toggleComponent.hidden = false;
	    	} else if (items.length == 0) { // list empty
	    		subComponent.value = 1;
	    		subComponent.hidden = false;
	    		toggleComponent.value = 0;
	    		toggleComponent.hidden = true;  		
	    	}
	    	
	    	items = document.getElementsByName('cpn');
	    	subComponent = document.getElementById('substituteCommonPlantName');
	    	toggleComponent = document.getElementById('toggleCommonPlantName');
	    	if (items.length != 0) { // list not empty
	    		subComponent.value = 0;
	    		subComponent.hidden = true;
	    		toggleComponent.value = 1;
	    		toggleComponent.hidden = false;
	    	} else if (items.length == 0) { // list empty
	    		subComponent.value = 1;
	    		subComponent.hidden = false;
	    		toggleComponent.value = 0;
	    		toggleComponent.hidden = true;  		
	    	}
	    	
	    	items = document.getElementsByName('ill');
	    	subComponent = document.getElementById('substituteIllness');
	    	toggleComponent = document.getElementById('toggleIllness');
	    	if (items.length != 0) { // list not empty
	    		subComponent.value = 0;
	    		subComponent.hidden = true;
	    		toggleComponent.value = 1;
	    		toggleComponent.hidden = false;
	    	} else if (items.length == 0) { // list empty
	    		subComponent.value = 1;
	    		subComponent.hidden = false;
	    		toggleComponent.value = 0;
	    		toggleComponent.hidden = true;  		
	    	}
	    	
	    	items = document.getElementsByName('loc');
	    	subComponent = document.getElementById('substituteLocation');
	    	toggleComponent = document.getElementById('toggleLocation');
	    	if (items.length != 0) { // list not empty
	    		subComponent.value = 0;
	    		subComponent.hidden = true;
	    		toggleComponent.value = 1;
	    		toggleComponent.hidden = false;
	    	} else if (items.length == 0) { // list empty
	    		subComponent.value = 1;
	    		subComponent.hidden = false;
	    		toggleComponent.value = 0;
	    		toggleComponent.hidden = true;  		
	    	}
	    	
	    	items = document.getElementsByName('syn');
	    	subComponent = document.getElementById('substituteSynonym');
	    	toggleComponent = document.getElementById('toggleSynonym');
	    	if (items.length != 0) { // list not empty
	    		subComponent.value = 0;
	    		subComponent.hidden = true;
	    		toggleComponent.value = 1;
	    		toggleComponent.hidden = false;
	    	} else if (items.length == 0) { // list empty
	    		subComponent.value = 1;
	    		subComponent.hidden = false;
	    		toggleComponent.value = 0;
	    		toggleComponent.hidden = true;  		
	    	}
	    }
	    
	    function changeSelect(tValue) {
    		console.log(tValue);
    		
    		var items;
    		var isComplete = true;
    		var button
    		
    		switch (tValue) {
	    	case 0:
	    		button = document.getElementById('ccSelect');
	    		items = document.getElementsByName('cc');
	    		console.log('cc');
	    		break;
	    	case 1:
	    		button = document.getElementById('cpnSelect');
	    		items = document.getElementsByName('cpn');
	    		console.log('cpn');
	    		break;
	    	case 2:
	    		button = document.getElementById('illSelect');
	    		items = document.getElementsByName('ill');
	    		console.log('ill');
	    		break;
	    	case 3:
	    		button = document.getElementById('locSelect');
	    		items = document.getElementsByName('loc');
	    		console.log('loc');
	    		break;
	    	case 4:
	    		button = document.getElementById('synSelect');
	    		items = document.getElementsByName('syn');
	    		console.log('syn');
	    		break;
	    	}
    		
    		
    		var checkedCtr = items.length; //for debugging
    		console.log('max checkboxes : ' + checkedCtr);
    		
    		for (var i = 0; i < items.length; i++) {
    			if(items[i].checked == false) {
    				// if at least one isn't checked
    				isComplete = false;
    				checkedCtr--;
    			}
    		}
    		
    		console.log(checkedCtr + ' out of ' + items.length);
    		
    		if (isComplete) {
    			button.innerHTML="Unselect All";
    			button.value = 1;
    		}
    		else if (!isComplete){
    			button.innerHTML="Select All";
    			button.value = 0;
    		}
    		
    	}
	    
	    function selectAll(tValue) {
	    	console.log(tValue);
	    	
	    	var items;
	    	var button;
	    	
	    	switch (tValue) {
	    	case 0:
	    		button = document.getElementById('ccSelect');
	    		items = document.getElementsByName('cc');
	    		console.log('cc');
	    		break;
	    	case 1:
	    		button = document.getElementById('cpnSelect');
	    		items = document.getElementsByName('cpn');
	    		console.log('cpn');
	    		break;
	    	case 2:
	    		button = document.getElementById('illSelect');
	    		items = document.getElementsByName('ill');
	    		console.log('ill');
	    		break;
	    	case 3:
	    		button = document.getElementById('locSelect');
	    		items = document.getElementsByName('loc');
	    		console.log('loc');
	    		break;
	    	case 4:
	    		button = document.getElementById('synSelect');
	    		items = document.getElementsByName('syn');
	    		console.log('syn');
	    		break;
	    	}
	    	
	    	if (button.value == 0) { //if select all function
	    		for (var i = 0; i < items.length; i++) {
		    		items[i].checked = true;
		    		console.log (i + ' is checked');
		    	}
	    		button.value = 1;
	    	} else if (button.value == 1) { //if unselect all function
	    		for (var i = 0; i < items.length; i++) {
	    			if (items[i].checked == true) {
	    				items[i].checked = false;
			    		console.log (i + ' is unchecked');
	    			}
		    	}
	    		button.value = 0;
	    	}
	    	
	    	
	    	changeSelect(tValue);
	    }
	    
	    function approveSelected(tValue) {
	    	console.log(tValue);
	    	
	    	var items;
	    	
	    	switch (tValue) {
	    	case 0:
	    		items = document.getElementsByName('cc');
	    		console.log('cc');
	    		break;
	    	case 1:
	    		items = document.getElementsByName('cpn');
	    		console.log('cpn');
	    		break;
	    	case 2:
	    		items = document.getElementsByName('ill');
	    		console.log('ill');
	    		break;
	    	case 3:
	    		items = document.getElementsByName('loc');
	    		console.log('loc');
	    		break;
	    	case 4:
	    		items = document.getElementsByName('syn');
	    		console.log('syn');
	    		break;
	    	}
	    	
	    	var selectedItems = []; // used to filter the selected items from unselected ones
	    	var selectedItems_tValues = []; // array used to contain the selected items' tValues;
	    	
	    	for (var i in items) {
	    		if (items[i].checked == true) {
	    			selectedItems.push(items[i]);
	    			selectedItems_tValues.push(i);
	    		}
	    	}
	    	
	    	// getting all selected entries
	    	for (var i in selectedItems_tValues) {
	    		/*
	    			code for accepting entries
	    		*/
	    	}
	    	
	    	
	    }
	    
	    function rejectSelected(tValue) {
	    	console.log(tValue);
	    	
	    	var items;
	    	
	    	switch (tValue) {
	    	case 0:
	    		items = document.getElementsByName('cc');
	    		console.log('cc');
	    		break;
	    	case 1:
	    		items = document.getElementsByName('cpn');
	    		console.log('cpn');
	    		break;
	    	case 2:
	    		items = document.getElementsByName('ill');
	    		console.log('ill');
	    		break;
	    	case 3:
	    		items = document.getElementsByName('loc');
	    		console.log('loc');
	    		break;
	    	case 4:
	    		items = document.getElementsByName('syn');
	    		console.log('syn');
	    		break;
	    	}
	    	
	    	var selectedItems = []; // used to filter the selected items from unselected ones
	    	var selectedItems_tValues = []; // array used to contain the selected items' tValues;
	    	
	    	
	    	// getting all selected entries
	    	for (var i = 0; i < items.length; i++) {
	    		if (items[i].checked == true) {
	    			selectedItems.push(items[i]);
	    			selectedItems_tValues.push(i);
	    		}
	    	}
	    	
	    	for (var i in selectedItems_tValues) {
	    		/*
	    			code for rejecting entries
	    		*/
	    	}
	    }
	    
	    function approveEntry(tValue,cValue) {
	    	console.log(tValue + ', ' + cValue);
	    	
	    	// finding a specific category using the tValue
	    	switch (tValue) {
	    	case 0:
	    		break;
	    	case 1:
	    		break;
	    	case 2:
	    		break;
	    	case 3:
	    		break;
	    	case 4:
	    		break;
	    	}
	    	
	    	/*
	    		code to approve the specific entry using tValue and cValue
	    	*/
	    	
	    }
	    
	    function rejectEntry(tValue,cValue) {
	    	console.log(tValue + ', ' + cValue);
	    	
	    	
	    	// finding a specific category using the tValue
	    	switch (tValue) {
	    	case 0:
	    		break;
	    	case 1:
	    		break;
	    	case 2:
	    		break;
	    	case 3:
	    		break;
	    	case 4:
	    		break;
	    	}
	    	
	    	/*
	    		code to reject the specific entry using tValue and cValue
	    	*/
	    	
	    }
	    
	    function viewEntry(tValue,cValue) {
	    	console.log(tValue + ', ' + cValue);
	    	
	    	
	    	// finding a specific category using the tValue
	    	switch (tValue) {
	    	case 0:
	    		break;
	    	case 1:
	    		break;
	    	case 2:
	    		break;
	    	case 3:
	    		break;
	    	case 4:
	    		break;
	    	}
	    	
	    	/*
	    		code to view the specific entry using tValue and cValue
	    	*/
	    	
	    }
	    
	    function editEntry(tValue,cValue) {
	    	console.log(tValue + ', ' + cValue);
	    	
	    	
	    	// finding a specific category using the tValue
	    	switch (tValue) {
	    	case 0:
	    		break;
	    	case 1:
	    		break;
	    	case 2:
	    		break;
	    	case 3:
	    		break;
	    	case 4:
	    		break;
	    	}
	    	
	    	/*
	    		code to edit the specific entry using tValue and cValue
	    	*/
	    	
	    }
	    
	    function addEntry(tValue) {
    		console.log('addEntry(' + tValue + ')');
	    	
	    	// finding which category should the entry be in
	    	switch(tValue) {
	    	case 0:
	    		ccCtr++;
	    		
	    		var entryCategory 		= 'cc';
	    		var entryNum  			= ccCtr;
	    		var nameEntry 			= 'CompoundEntry';
	    		var nameLabel 			= 'CompoundLabel';
	    		var nameGroup 			= 'CompoundGroup';
	    		
	    		var namePlant 			= 'ccPlant';
	    		var nameRelation 		= 'ccRelation';
	    		var nameRelationObject	= 'ccRelationObject';
	    		break;
	    	case 1:
	    		cpnCtr++;
	    		
	    		var entryCategory 		= 'cpn';
	    		var entryNum 			= cpnCtr;
	    		var nameEntry 			= 'CommonPlantNameEntry';
	    		var nameLabel 			= 'CommonPlantNameLabel';
	    		var nameGroup 			= 'CommonPlantNameGroup';

	    		var namePlant 			= 'cpnPlant';
	    		var nameRelation 		= 'cpnRelation';
	    		var nameRelationObject 	= 'cpnRelationObject';
	    		break;
	    	case 2:
	    		illCtr++;
	    		
	    		var entryCategory 		= 'ill';
	    		var entryNum 			= illCtr;
	    		var nameEntry 			= 'IllnessEntry';
	    		var nameLabel 			= 'IllnessLabel';
	    		var nameGroup 			= 'IllnessGroup';

	    		var namePlant 			= 'illPlant';
	    		var nameRelation 		= 'illRelation';
	    		var nameRelationObject 	= 'illRelationObject';
	    		break;
	    	case 3:
	    		locCtr++;
	    		
	    		var entryCategory 		= 'loc';
	    		var entryNum 			= locCtr;
	    		var nameEntry 			= 'LocationEntry';
	    		var nameLabel 			= 'LocationLabel';
	    		var nameGroup 			= 'LocationGroup';

	    		var namePlant 			= 'locPlant';
	    		var nameRelation 		= 'locRelation';
	    		var nameRelationObject 	= 'locRelationObject';
	    		break;
	    	case 4:
	    		synCtr++;
	    		
	    		var entryCategory 		= 'syn';
	    		var entryNum 			= synCtr;
	    		var nameEntry 			= 'SynonymEntry';
	    		var nameLabel 			= 'SynonymLabel';
	    		var nameGroup 			= 'SynonymGroup';

	    		var namePlant 			= 'synPlant';
	    		var nameRelation 		= 'synRelation';
	    		var nameRelationObject 	= 'synRelationObject';
	    		break;
	    	}
	    	
	    	var entryContainerOpening 	= '<div id="'+ nameEntry + entryNum +'" class="form-row" style="padding-top:5px">';
	    	
			var entryDocument 			= '<div class="col-2" style="text-align:left">' +
											  '<input class="form-check-input" type="checkbox" id="'+ nameLabel + entryNum +'" name="'+ entryCategory +'" value="'+ entryNum +'" onchange="changeSelect('+ tValue +')">' +
											  '<label class="form-check-label" for="'+ nameLabel + entryNum +'">Document</label>' +
										  '</div>';
										  
			//'plant' is a PLACEHOLDER; should be retrieved from backend							  
			var entryPlant 				= '<div class="col-3" id="'+ entryCategory +'Plant'+ entryNum +'">' +
											  'plant' +
										  '</div>';
			
			//'relation' is a PLACEHOLDER; should be retrieved from backend							  
			var entryRelation 			= '<div class="col-2" id="'+ entryCategory +'Relation'+ entryNum +'">' +
											  'relation' +
										  '</div>';
										  
			//'object' is a PLACEHOLDER; should be retrieved from backend							  
			var entryRelationObject 	= '<div class="col-3" id="'+ entryCategory +'RelationObject'+ entryNum +'">' +
											  'object' +
										  '</div>';
	    	
			var entryCommands 			= '<div class="col-2" style="text-align:right">' +
											  '<button type="button" class="btn btn-success btn-sm" onclick="approveEntry('+ tValue +','+ entryNum +')" data-toggle="tooltip" data-placement="top" title="approve entry"><i class="fa fa-check" aria-hidden="true"></i></button>' +
											  '<button type="button" class="btn btn-danger btn-sm" onclick="rejectEntry('+ tValue +','+ entryNum +')" data-toggle="tooltip" data-placement="top" title="reject entry"><i class="fa fa-times" aria-hidden="true"></i></button>' +
											  '<button type="button" class="btn btn-primary btn-sm" onclick="viewEntry('+ tValue +','+ entryNum +')" data-toggle="tooltip" data-placement="top" title="view entry"><i class="fa fa-eye" aria-hidden="true"></i></button>' +
											  '<button type="button" class="btn btn-secondary btn-sm" onclick="editEntry('+ tValue +','+ entryNum +')" data-toggle="tooltip" data-placement="top" title="edit entry"><i class="fa fa-pencil" aria-hidden="true"></i></button>' +
										  '</div>';
	    	
    		var entryContainerClosing	= '</div>';
    		
    		var combinedEntry = entryContainerOpening + entryDocument + entryPlant + entryRelation + entryRelationObject + entryCommands + entryContainerClosing;
    		
    		$('#' + nameGroup).append(combinedEntry);
    		
    		checkItems();
	    }
    </script>
	
    
	<script type="text/javascript" src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="DataTables/datatables.min.js"></script>
    
    <script type="text/javascript">
	    $(document).ready(function() {
	        $('#table_id').DataTable();
	        //$('.hid').css('display', 'none');
	    });
	    
	    function func_submit() {
	    	$("#searchForm").submit();
	    }
	    
		$(document).ready(function(){
			var dropdown = $(".navbar-right .dropdown");
			var toogleBtn = $(".navbar-right .dropdown-toggle");
			
			// Toggle search and close icon for search dropdown
			dropdown.on("show.bs.dropdown", function(e){
				toogleBtn.toggleClass("hide");
			});
			dropdown.on("hide.bs.dropdown", function(e){
				toogleBtn.addClass("hide");
				toogleBtn.first().removeClass("hide");
			});
		});
		
		function ddfunc(x){
			if(x==1) {
				$("#ddtext").html("Plant Name <b class='caret'></b>");
				$('input[name="searchCategory"]').val(1);
			} else if(x==2) {
				$("#ddtext").html("Scientific Name <b class='caret'></b>");
				$('input[name="searchCategory"]').val(2);
			} else if(x==3) {
				$("#ddtext").html("Genus <b class='caret'></b>");
				$('input[name="searchCategory"]').val(3);
			} else if(x==4) {
				$("#ddtext").html("Family <b class='caret'></b>");
				$('input[name="searchCategory"]').val(4);
			} else if(x==5) {
				$("#ddtext").html("Compound <b class='caret'></b>");
				$('input[name="searchCategory"]').val(5);
			} else if(x==6) {
				$("#ddtext").html("Location <b class='caret'></b>");
				$('input[name="searchCategory"]').val(6);
			} else if(x==7) {
				$("#ddtext").html("Biological Activites <b class='caret'></b>");
				$('input[name="searchCategory"]').val(7);
			}
		}
		
		$("#search").val('${searchKey}');
		ddfunc(${searchCategory});
	</script>
    
  </body>
</html>