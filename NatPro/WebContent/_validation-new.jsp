<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Based from template.jsp -->


<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="https://fonts.googleapis.com/css?family=Varela+Round"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="DataTables/datatables.min.css" />
<link rel="stylesheet" type="text/css" href="css/navbar.css" />

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

<title>NatPro : ${searchKey}</title>
</head>
<body>
	<!-- INCLUDE NAV BAR HTML -->
	<%@include file="navbarnix.html"%>

	<%-- 	<a href="file:///C://Users//eduar//Documents//GitHub//NatPro//NatPro//Documents//UploadedDocuments//${pdfFileName}.pdf">${pdfFileName}</a> --%>

	<!-- HTML CODE -->
	<div class="jumbotron jumbotron-fluid" style="text-align: center">
		<div class="container">
			<h1 class="display-4">Validation</h1>
			<p class="lead">Validate the gathered data from the processed
				files here</p>
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
		<a href="file:///SERVER/directory/file.ext">file.ext</a>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<span>*debugging only pls hide*<br></span>
					<button type="button" class="btn btn-primary btn-sm"
						onclick="addEntry(0)">Compound</button>
					<button type="button" class="btn btn-primary btn-sm"
						onclick="addEntry(1)">Common Plant Name</button>
					<button type="button" class="btn btn-primary btn-sm"
						onclick="addEntry(2)">Illness</button>
					<button type="button" class="btn btn-primary btn-sm"
						onclick="addEntry(3)">Location</button>
					<button type="button" class="btn btn-primary btn-sm"
						onclick="addEntry(4)">Synonym</button>
					<button type="button" class="btn btn-primary btn-sm"
						onclick="addEntry(5)">Preparation</button>
					<button type="button" class="btn btn-primary btn-sm"
						onclick="addEntry(6)">Genus</button>
					<button type="button" class="btn btn-primary btn-sm"
						onclick="addEntry(7)">Family</button>
				</div>
			</div>


			<form>
				<h3 style="padding-top: 50px; padding-bottom: 20px">Compound(s)</h3>
				<div class="row" id="substituteCompound" value="1">
					<div class="col-12">empty.</div>
				</div>
				<div class="row" id="toggleCompound" value="0" hidden>
					<div class="col-12" style="text-align: left">
						<button id="ccSelect" type="button" class="btn btn-primary btn-sm"
							onclick="selectAll(0)" value="0">Select All</button>
						<button id="ccApprove" type="button"
							class="btn btn-success btn-sm" onclick="approveSelected(0)">Approve
							Selected</button>
						<button id="ccReject" type="button" class="btn btn-danger btn-sm"
							onclick="rejectSelected(0)">Reject Selected</button>
					</div>
				</div>

				<div id="CompoundGroup"></div>


				<h3 style="padding-top: 50px; padding-bottom: 20px">Common
					Plant Name(s)</h3>
				<div class="row" id="substituteCommonPlantName" value="1">
					<div class="col-12">empty.</div>
				</div>
				<div class="row" id="toggleCommonPlantName" value="0" hidden>
					<div class="col-12" style="text-align: left">
						<button id="cpnSelect" type="button"
							class="btn btn-primary btn-sm" onclick="selectAll(1)" value="0">Select
							All</button>
						<button id="cpnApprove" type="button"
							class="btn btn-success btn-sm" onclick="approveSelected(1)">Approve
							Selected</button>
						<button id="cpnReject" type="button" class="btn btn-danger btn-sm"
							onclick="rejectSelected(1)">Reject Selected</button>
					</div>
				</div>

				<div id="CommonPlantNameGroup"></div>

				<h3 style="padding-top: 50px; padding-bottom: 20px">Illness(es)</h3>
				<div class="row" id="substituteIllness" value="1">
					<div class="col-12">empty.</div>
				</div>
				<div class="row" id="toggleIllness" value="0" hidden>
					<div class="col-12" style="text-align: left">
						<button id="illSelect" type="button"
							class="btn btn-primary btn-sm" onclick="selectAll(2)" value="0">Select
							All</button>
						<button id="illApprove" type="button"
							class="btn btn-success btn-sm" onclick="approveSelected(2)">Approve
							Selected</button>
						<button id="illReject" type="button" class="btn btn-danger btn-sm"
							onclick="rejectSelected(2)">Reject Selected</button>
					</div>
				</div>

				<div id="IllnessGroup"></div>

				<h3 style="padding-top: 50px; padding-bottom: 20px">Location(s)</h3>
				<div class="row" id="substituteLocation" value="1">
					<div class="col-12">empty.</div>
				</div>
				<div class="row" id="toggleLocation" value="0" hidden>
					<div class="col-12" style="text-align: left">
						<button id="locSelect" type="button"
							class="btn btn-primary btn-sm" onclick="selectAll(3)" value="0">Select
							All</button>
						<button id="locApprove" type="button"
							class="btn btn-success btn-sm" onclick="approveSelected(3)">Approve
							Selected</button>
						<button id="locReject" type="button" class="btn btn-danger btn-sm"
							onclick="rejectSelected(3)">Reject Selected</button>
					</div>
				</div>

				<div id="LocationGroup"></div>

				<h3 style="padding-top: 50px; padding-bottom: 20px">Synonyms(s)</h3>
				<div class="row" id="substituteSynonym" value="1">
					<div class="col-12">empty.</div>
				</div>
				<div class="row" id="toggleSynonym" value="0" hidden>
					<div class="col-12" style="text-align: left">
						<button id="synSelect" type="button"
							class="btn btn-primary btn-sm" onclick="selectAll(4)" value="0">Select
							All</button>
						<button id="synApprove" type="button"
							class="btn btn-success btn-sm" onclick="approveSelected(4)">Approve
							Selected</button>
						<button id="synReject" type="button" class="btn btn-danger btn-sm"
							onclick="rejectSelected(4)">Reject Selected</button>
					</div>
				</div>

				<div id="SynonymGroup"></div>


				<h3 style="padding-top: 50px; padding-bottom: 20px">Preparation(s)</h3>
				<div class="row" id="substitutePreparation" value="1">
					<div class="col-12">empty.</div>
				</div>
				<div class="row" id="togglePreparation" value="0" hidden>
					<div class="col-12" style="text-align: left">
						<button id="prepSelect" type="button"
							class="btn btn-primary btn-sm" onclick="selectAll(5)" value="0">Select
							All</button>
						<button id="prepApprove" type="button"
							class="btn btn-success btn-sm" onclick="approveSelected(5)">Approve
							Selected</button>
						<button id="prepReject" type="button"
							class="btn btn-danger btn-sm" onclick="rejectSelected(5)">Reject
							Selected</button>
					</div>
				</div>

				<div id="PreparationGroup"></div>

				<h3 style="padding-top: 50px; padding-bottom: 20px">Genus</h3>
				<div class="row" id="substituteGenus" value="1">
					<div class="col-12">empty.</div>
				</div>
				<div class="row" id="toggleGenus" value="0" hidden>
					<div class="col-12" style="text-align: left">
						<button id="genSelect" type="button"
							class="btn btn-primary btn-sm" onclick="selectAll(6)" value="0">Select
							All</button>
						<button id="genApprove" type="button"
							class="btn btn-success btn-sm" onclick="approveSelected(6)">Approve
							Selected</button>
						<button id="genReject" type="button" class="btn btn-danger btn-sm"
							onclick="rejectSelected(6)">Reject Selected</button>
					</div>
				</div>

				<div id="GenusGroup"></div>

				<h3 style="padding-top: 50px; padding-bottom: 20px">Family</h3>
				<div class="row" id="substituteFamily" value="1">
					<div class="col-12">empty.</div>
				</div>
				<div class="row" id="toggleFamily" value="0" hidden>
					<div class="col-12" style="text-align: left">
						<button id="famSelect" type="button"
							class="btn btn-primary btn-sm" onclick="selectAll(7)" value="0">Select
							All</button>
						<button id="famApprove" type="button"
							class="btn btn-success btn-sm" onclick="approveSelected(7)">Approve
							Selected</button>
						<button id="famReject" type="button" class="btn btn-danger btn-sm"
							onclick="rejectSelected(7)">Reject Selected</button>
					</div>
				</div>

				<div id="FamilyGroup"></div>

			</form>


			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#exampleModal" hidden="true">Document Modal
				Tester</button>

			<!-- Modal -->
			<div class="modal fade" id="documentModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-xl modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Document</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="embed-responsive embed-responsive-16by9">
								<iframe id="documentIframe" class="embed-responsive-item"
									src="https://www.youtube.com/embed/zpOULjyy-n8?rel=0"
									allowfullscreen></iframe>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>


		</div>
	</div>


	<!-- INCLUDE FOOTER HTML -->
	<%@include file="_includeFooter.html"%>

	<script>
    	/* -- GLOBAL VARIABLES -- */
    	
    	//values for the counter
    	var ccCtr, cpnCtr, illCtr, locCtr, synCtr, prepCtr, genCtr, famCtr;
    	
    	// initialize the counters at -1 (because when calling the addEntry(), it automatically increments +1)
    	// TODO: If ctr == -1, the category is empty and can be invisible from the user
    	ccCtr = cpnCtr = illCtr = locCtr = synCtr = prepCtr = genCtr = famCtr = -1; 
    	
    
	    /*
	    	CHEATSHEET
	    
	    	tValue = table Value
	    	0 = cc = compound
	    	1 = cpn = common plant name
	    	2 = ill = illness
	    	3 = loc = location
	    	4 = syn = synonym
	    	5 = prep = preparation
	    	6 = gen = genus
	    	7 = fam = family
	    	
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
	    	
	    	items = document.getElementsByName('prep');
	    	subComponent = document.getElementById('substitutePreparation');
	    	toggleComponent = document.getElementById('togglePreparation');
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
	    	
	    	items = document.getElementsByName('gen');
	    	subComponent = document.getElementById('substituteGenus');
	    	toggleComponent = document.getElementById('toggleGenus');
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
	    	
	    	items = document.getElementsByName('fam');
	    	subComponent = document.getElementById('substituteFamily');
	    	toggleComponent = document.getElementById('toggleFamily');
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
	    	case 5:
	    		button = document.getElementById('prepSelect');
	    		items = document.getElementsByName('prep');
	    		console.log('prep');
	    		break;
	    	case 6:
	    		button = document.getElementById('genSelect');
	    		items = document.getElementsByName('gen');
	    		console.log('gen');
	    		break;
	    	case 7:
	    		button = document.getElementById('famSelect');
	    		items = document.getElementsByName('fam');
	    		console.log('fam');
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
	    	case 5:
	    		button = document.getElementById('prepSelect');
	    		items = document.getElementsByName('prep');
	    		console.log('prep');
	    		break;
	    	case 6:
	    		button = document.getElementById('genSelect');
	    		items = document.getElementsByName('gen');
	    		console.log('gen');
	    		break;
	    	case 7:
	    		button = document.getElementById('famSelect');
	    		items = document.getElementsByName('fam');
	    		console.log('fam');
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
	    	case 5:
	    		items = document.getElementsByName('prep');
	    		console.log('prep');
	    		break;
	    	case 6:
	    		items = document.getElementsByName('gen');
	    		console.log('gen');
	    		break;
	    	case 7:
	    		items = document.getElementsByName('fam');
	    		console.log('fam');
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
	    	case 5:
	    		items = document.getElementsByName('prep');
	    		console.log('prep');
	    		break;
	    	case 6:
	    		items = document.getElementsByName('gen');
	    		console.log('gen');
	    		break;
	    	case 7:
	    		items = document.getElementsByName('fam');
	    		console.log('fam');
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
	    	case 5:
	    		items = document.getElementsByName('prep');
	    		console.log('prep');
	    		break;
	    	case 6:
	    		items = document.getElementsByName('gen');
	    		console.log('gen');
	    		break;
	    	case 7:
	    		items = document.getElementsByName('fam');
	    		console.log('fam');
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
	    	case 5:
	    		items = document.getElementsByName('prep');
	    		console.log('prep');
	    		break;
	    	case 6:
	    		items = document.getElementsByName('gen');
	    		console.log('gen');
	    		break;
	    	case 7:
	    		items = document.getElementsByName('fam');
	    		console.log('fam');
	    		break;
	    	}
	    	
	    	/*
	    		code to reject the specific entry using tValue and cValue
	    	*/
	    	
	    }
	    
	    function viewEntry(tValue,cValue,docu) {
	    	console.log(tValue + ', ' + cValue);
	    	
	    	var entry;
	    	var entryDocument = "\\NatPro\\Documents\\UploadedDocuments\\"+docu;
	    	var modalIframe = document.getElementById('documentIframe');
	    	
	    	// finding a specific category using the tValue
	    	// getting the source of the document using the entry's src
	    	switch (tValue) {
	    	case 0:
	    		entry = document.getElementById('CompoundLabel' + cValue);
	    		break;
	    	case 1:
	    		entry = document.getElementById('CommonPlantNameLabel' + cValue);
	    		break;
	    	case 2:
	    		entry = document.getElementById('IllnessLabel' + cValue);
	    		break;
	    	case 3:
	    		entry = document.getElementById('LocationLabel' + cValue);
	    		break;
	    	case 4:
	    		entry = document.getElementById('SynonymLabel' + cValue);
	    		break;
	    	case 5:
	    		entry = document.getElementById('PreparationLabel' + cValue);
	    		break;
	    	case 6:
	    		entry = document.getElementById('GenusLabel' + cValue);
	    		break;
	    	case 7:
	    		entry = document.getElementById('FamilyLabel' + cValue);
	    		break;
	    	}
	    	
	    	/*
	    		code to view the specific entry using tValue and cValue	    		
	    	*/
	    	
	    	if (entryDocument != null) {
	    		console.log(entryDocument);
	    		modalIframe.src = entryDocument;
	    	} else {
	    		modalIframe.src = 'https://www.youtube.com/embed/ZKYEQXN_nk0';
	    	}
	    	$('#documentModal').modal('show');
	    	
	    }
	    
	    function editEntry(tValue,cValue) {
	    	console.log(tValue + ', ' + cValue);
	    	    	

	    	// finding a specific category using the tValue
	    	switch (tValue) {
	    	case 0:
		    	var name = 'ccRelationObjectName'+cValue;
		    	var name2 = 'ccRelationObjectEdit'+cValue;
			 	document.getElementById(name).style.display="none"; 
			 	console.log(document.getElementById(name).innerHTML); 
	    		document.getElementById(name2).value = document.getElementById(name).innerHTML;
	    		document.getElementById(name2).style.display="block"; 
	    		/*var element = document.getElementById("fname1");
	    	    element.parentNode.removeChild(element);  */
	    		break;
	    	case 1:
	    		break;
	    	case 2:
	    		break;
	    	case 3:
	    		break;
	    	case 4:
	    		break;
	    	case 5:
	    		break;
	    	case 6:
	    		break;
	    	case 7:
	    		break;
	    	}
	    	
	    	/*
	    		code to edit the specific entry using tValue and cValue
	    	*/
	    	
	    }
	    
	    function addEntry(tValue, docu, docunum, val2) {
    		console.log('addEntry(' + tValue + ')');
    		var pdfFile = "'"+docu+"'";
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
	    		
	    		var selectPlant = '<div class="col-2" id="'+ entryCategory +'Plant'+ entryNum +'">' +
				'<select class="custom-select mr-sm-2" id="'+ entryCategory +'selectPlant'+ entryNum +'">'+
		      '</select>'+
		      '</div>'; 
		      var selectPlantPart = '<div class="col-1" id="'+ entryCategory +'PlantPart'+ entryNum +'">' +
				'<select class="custom-select mr-sm-2" id="'+ entryCategory +'selectPart'+ entryNum +'">'+
		      '</select>'+
		      '</div>'; 
		      
		      var options = selectPlant + selectPlantPart;
		      
		      var relation = "contains";
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
	    		
	    		var selectPlant = '<div class="col-2" id="'+ entryCategory +'Plant'+ entryNum +'">' +
				'<select class="custom-select mr-sm-2" id="'+ entryCategory +'selectPlant'+ entryNum +'">'+
		      '</select>'+
		      '</div>'; 
		      var options = selectPlant;
		      var relation = "located in";
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
	    	case 5:
	    		prepCtr++;
	    		
	    		var entryCategory 		= 'prep';
	    		var entryNum 			= prepCtr;
	    		var nameEntry 			= 'PreparationEntry';
	    		var nameLabel 			= 'PreparationLabel';
	    		var nameGroup 			= 'PreparationGroup';

	    		var namePlant 			= 'prepPlant';
	    		var nameRelation 		= 'prepRelation';
	    		var nameRelationObject 	= 'prepRelationObject';
	    		break;
	    	case 6:
	    		genCtr++;
	    		
	    		var entryCategory 		= 'gen';
	    		var entryNum 			= genCtr;
	    		var nameEntry 			= 'GenusEntry';
	    		var nameLabel 			= 'GenusLabel';
	    		var nameGroup 			= 'GenusGroup';

	    		var namePlant 			= 'genPlant';
	    		var nameRelation 		= 'genRelation';
	    		var nameRelationObject 	= 'genRelationObject';
	    		break;
	    	case 7:
	    		famCtr++;
	    		
	    		var entryCategory 		= 'fam';
	    		var entryNum 			= famCtr;
	    		var nameEntry 			= 'FamilyEntry';
	    		var nameLabel 			= 'FamilyLabel';
	    		var nameGroup 			= 'FamilyGroup';

	    		var namePlant 			= 'famPlant';
	    		var nameRelation 		= 'famRelation';
	    		var nameRelationObject 	= 'famRelationObject';
	    		break;
	    	}
	    	
	    	var entryContainerOpening 	= '<div id="'+ nameEntry + entryNum +'" class="form-row" style="padding-top:5px">';
	    	
			var entryDocument 			= '<div class="col-2" style="text-align:left">' +
											  '<input class="form-check-input" type="checkbox" id="'+ nameLabel + entryNum +'" name="'+ entryCategory +'" value="'+ entryNum +'" onchange="changeSelect('+ tValue +')">' +
											  '<label class="form-check-label" for="'+ nameLabel + entryNum +'">Document'+docunum+'</label>' +
										  '</div>';
										  
 			
/* 			//'plant' is a PLACEHOLDER; should be retrieved from backend							  
			var entryPlant 				= '<div class="col-3" id="'+ entryCategory +'Plant'+ entryNum +'">' +
											 val1+
										  '</div>'; */
			
			//'relation' is a PLACEHOLDER; should be retrieved from backend							  
			var entryRelation 			= '<div class="col-2" id="'+ entryCategory +'Relation'+ entryNum +'">' +
											  relation +
										  '</div>';
										  
			//'object' is a PLACEHOLDER; should be retrieved from backend							  
			var entryRelationObject 	= '<div class="col-2" id="'+ entryCategory +'RelationObject'+ entryNum +'" style="display:block;">' +
											  '<p id="'+ entryCategory +'RelationObjectName'+ entryNum +'">'+val2 +'</p>'+
										  '<input type="text" id="'+ entryCategory +'RelationObjectEdit'+ entryNum +'" name="object" style="display: none"></div>';
	    	
			var entryCommands 			= '<div class="col-3" style="text-align:right">' +
											  '<div id="entryCommands'+ tValue + '-' + entryNum +'">' +
												  '<button type="button" class="btn btn-success btn-sm" onclick="approveEntry('+ tValue +','+ entryNum +')" data-toggle="tooltip" data-placement="top" title="approve entry"><i class="fa fa-check" aria-hidden="true"></i></button>' +
												  '<button type="button" class="btn btn-primary btn-sm" onclick="viewEntry('+ tValue +','+ entryNum +','+pdfFile +')" data-toggle="" data-placement="top" title="view entry" data-target="#documentModal"><i class="fa fa-eye" aria-hidden="true"></i></button>' +
												  '<button type="button" class="btn btn-secondary btn-sm" onclick="editEntry('+ tValue +','+ entryNum +')" data-toggle="tooltip" data-placement="top" title="edit entry"class="btn btn-primary" data-target="#exampleModal"><i class="fa fa-pencil" aria-hidden="true"></i></button>' +
												  '<button type="button" class="btn btn-danger btn-sm" onclick="rejectEntry('+ tValue +','+ entryNum +')" data-toggle="tooltip" data-placement="top" title="reject entry"><i class="fa fa-times" aria-hidden="true"></i></button>' +
											  '</div>'+
											  '<div id="entryCommandsEdit'+ tValue + '-' + entryNum +'" style="text-align:right" hidden>'+
												  '<button type="button" class="btn btn-success btn-sm" onclick="confirmEntry('+ tValue +','+ entryNum +')">ConfirmEntry</button>'+
											  '</div>'+
										  '</div>';
	    	
    		var entryContainerClosing	= '</div>';
    		
    		var combinedEntry = entryContainerOpening + entryDocument +  options+ entryRelation + entryRelationObject + entryCommands + entryContainerClosing;
    		
    		$('#' + nameGroup).append(combinedEntry);
    		
    		checkItems();
	    }
    </script>



	<script type="text/javascript"
		src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>

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

	<c:set var="docunum" value="1" scope="page" />
	<c:forEach items="${Validations}" var="Validations">
		<c:set var="count" value="0" scope="page" />
		<c:forEach items="${Validations.getCompounds()}" var="CompoundList">
			<script type="text/javascript">
				  addEntry(0, "${Validations.getPdfFileName()}", "${docunum}", "${CompoundList}");  
			</script>

			<c:if test="${not empty Validations.getSynonyms()}">
				<c:forEach items="${Validations.getSynonyms()}" var="SynonymsList">
					<script type="text/javascript">
						  var x = document.getElementById("ccselectPlant${count}");
						  var option = document.createElement("option");
						  option.text = "${SynonymsList}";
						  x.appendChild(option);	  
					</script>
				</c:forEach>
			</c:if>

			<c:if test="${not empty Validations.getPlantParts()}">
				<c:forEach items="${Validations.getPlantParts()}"
					var="PlantPartsList">
					<script type="text/javascript">
						  var x = document.getElementById("ccselectPart${count}");
						  var option = document.createElement("option");
						  option.text = "${PlantPartsList}";
						  x.appendChild(option);	  
					</script>
				</c:forEach>
			</c:if>
			<c:set var="count" value="${count + 1}" scope="page" />
		</c:forEach>

		<c:set var="count" value="0" scope="page" />
		<c:forEach items="${Validations.getLocation()}" var="CompoundList">
			<script type="text/javascript">
				  addEntry(3, "${Validations.getPdfFileName()}", "smth", "${CompoundList}");  
			</script>

			<c:if test="${not empty Validations.getSynonyms()}">
				<c:forEach items="${Validations.getSynonyms()}" var="SynonymsList">
					<script type="text/javascript">
						  var x = document.getElementById("locselectPlant${count}");
						  var option = document.createElement("option");
						  option.text = "${SynonymsList}";
						  x.appendChild(option);	  
					</script>
				</c:forEach>
			</c:if>
			<c:set var="count" value="${count + 1}" scope="page" />
		</c:forEach>

		<c:set var="docunum" value="${docunum + 1}" scope="page" />
	</c:forEach>
</body>
</html>