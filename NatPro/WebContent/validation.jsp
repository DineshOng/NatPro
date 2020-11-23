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
<link
	href="https://cdn.jsdelivr.net/npm/smartwizard@5/dist/css/smart_wizard_all.min.css"
	rel="stylesheet" type="text/css" />

<link href="css/autocomplete.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<title>NatPro : ${searchKey}</title>
</head>
<body>
	<!-- INCLUDE NAV BAR HTML -->
	<%@include file="navbarnix.html"%>

	<div class="jumbotron jumbotron-fluid" style="text-align: center">
		<div class="container">
			<h1 class="display-4">Validation</h1>
			<p class="lead">Validate the gathered data from the processed
				files here</p>
		</div>
	</div>

	<div id="firstDiv">
		<table id="table_id" class="table table-striped table-bordered"
			style="width: 100%">
			<thead>
				<tr>
					<th>Document Number</th>
					<th>Plant</th>
					<!-- <th>Compound</th> -->
				</tr>
			</thead>
			<tbody>
				<c:set var="docunum" value="1" scope="page" />
				<c:set var="modalId" value="1" scope="page" />
				<c:forEach items="${Validations}" var="Validations">
					<c:if test="${not empty Validations.getSynonyms()}">
						<c:forEach items="${Validations.getSynonyms()}" var="SynonymsList">
							<tr>
								<td><button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#docuModal"
										onclick="viewDocument('${Validations.getPdfFileName()}')">Document
										# ${docunum}</button></td>
								<td><button type="button" class="btn btn-success btn-sm"
										data-toggle="modal" data-target="#entryModal"
										onclick="getPlantEntity('${Validations.getPdfFileName()}',${modalId},'${SynonymsList.getSpecie()}')">${SynonymsList.getSpecie()}</button></td>
								<!-- <td><button type="button" class="btn btn-warning btn-sm"
										data-toggle="modal" data-target="" data-backdrop='static'
										data-keyboard='false'>Compounds</button></td> -->
							</tr>

							<c:set var="modalId" value="${modalId + 1}" scope="page" />
						</c:forEach>
					</c:if>
					<c:set var="docunum" value="${docunum + 1}" scope="page" />
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- Entry Modal -->
	<div class="modal fade" id="entryModal" tabindex="-1"
		aria-labelledby="entryModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false"
		onmouseenter="call_wizard()">
		<div class="modal-dialog modal-xl"
			style="overflow-y: initial !important">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="entryModalLabel">Validating
						Extracted Plant Entry</h5>
					<button type="button" class="btn btn-primary btn-sm"
						data-toggle="modal" data-target="#docuModal"
						id="entryViewDocument"
						onclick="viewDocument('935ef8690c1123ce7a9b13f82bc11f03.pdf')">View
						Document</button>
				</div>
				<div class="modal-body" style="height: 80vh; overflow-y: auto;">
					<div id="smartwizard">
						<ul class="nav">
							<li><a class="nav-link" href="#step-1"> General
									Information </a></li>
							<li><a class="nav-link" href="#step-2"> Preparation </a></li>
							<li><a class="nav-link" href="#step-3"> Compound </a></li>
							<!-- <li><a class="nav-link" href="#step-4"> Review Data </a></li> -->
						</ul>
						<form action="AddPlantServlet" method="POST" id="addPlant">
							<div class="tab-content overflow-auto">
								<div id="step-1" class="tab-pane" role="tabpanel">
									<div class="card">
										<div class="card-header text-center">
											<h4 class="mt-1">General Information</h4>
										</div>
										<div class="card-body">
											<div class="form-group form-row">
												<div class="col-4"></div>
												<div class="col-4">
													<label for="CommonPlantName"> <i
														class="fa fa-address-card" aria-hidden="true"></i> Common
														Plant Name
													</label> <input type="text" id="CommonPlantName"
														name="commonPlantName"
														class="form-control outline-warning" placeholder=""
														required>
												</div>
											</div>
											<div class="form-group form-row">
												<div class="col-2"></div>
												<div class="col-4">
													<label for="Genus"><i class="fa fa-group"
														aria-hidden="true"></i> Genus</label> <input type="text"
														class="form-control" name="genus" id="Genus"
														placeholder="">
												</div>
												<div class="col-4">
													<label for="Family"><i class="fa fa-group"
														aria-hidden="true"></i> Family</label> <input type="text"
														class="form-control" name="family" id="Family"
														placeholder="">
												</div>
											</div>

											<div class="form-group form-row">
												<div class="col-2"></div>
												<div class="col-4">
													<label for="ScientificName"><i
														class="fa fa-drivers-license" aria-hidden="true"></i>
														Scientific Name</label> <input type="text" class="form-control"
														name="scientificName" id="ScientificName"
														pattern="^([A-Z][a-z]+) +([a-z-]+).*$" placeholder=""
														required>
												</div>
												<div id="locationGroup" class="col-4">
													<div class="col-13">
														<label for="Location"><i class="fa fa-map-marker"
															aria-hidden="true"></i> Location</label> <input type="text"
															class="form-control" name="location" id="Location"
															placeholder="" style="display: none;">
													</div>
													<div>
														<button id="locationAdd" type="button"
															class="btn btn-outline-success btn-sm"
															onclick="addLFields(${modalId},'')"
															style="margin-top: 5px">
															<i class="fa fa-plus" aria-hidden="true"></i> Location
														</button>
													</div>
												</div>
											</div>
										</div>
										<div id="generalError" class="card-footer"></div>
									</div>
								</div>
								<div id="step-2" class="tab-pane" role="tabpanel">
									<div class="card">
										<div class="card-header text-center">
											<h4 class="mt-1">Preparation</h4>
										</div>
										<div class="card-body">
											<div id="preparationGroup">
												<div class="border-bottom border-secondary mb-3">
													<div class="form-group form-row">
														<div class="col-1"></div>
														<div class="col">
															<label for="Preparation"><i class="fa fa-cogs"
																aria-hidden="true"></i> Preparation</label> <input type="text"
																class="form-control" id="Preparation" placeholder=""
																name="preparation[0]"> <input type="hidden"
																name="prepCtr" value="0">
														</div>
														<div class="col">
															<label for="PlantPart"><i class="fa fa-leaf"
																aria-hidden="true"></i> Plant Part</label> <select
																name="prepPart[0]"
																class="custom-select custom-select-md mb-3"
																id="PlantPart" onchange="enablePPO(0)">
																<option value="" selected disabled>Open this
																	select menu</option>
																<option value="-1">Others (please write on the
																	right side)</option>
																<c:forEach items="${plantPartsList}"
																	var="plantPartsList">
																	<option value="${plantPartsList}">${plantPartsList}</option>
																</c:forEach>
															</select>
														</div>
														<div class="col">
															<label for="PlantPartOther[0]">Plant Part (Other)</label>
															<input type="text" class="form-control"
																id="PlantPartOther[0]" placeholder="" disabled>
														</div>
														<div class="col-1"></div>
													</div>

													<div class="form-group form-row" id="illnessGroup">
														<div class="col-4"></div>
														<div class="col-4">
															<i class="fa fa-viruses"></i> <label for="Illness"><i
																class="fa fa-thermometer" aria-hidden="true"></i>
																Illness</label> <input type="text" class="form-control"
																id="Illness" placeholder="" name="illness[0][0]">
															<input type="hidden" name="illnessCtr[0]" value="0">
															<button id="illnessAdd" type="button"
																class="btn btn-outline-success btn-sm"
																style="margin-top: 5px" onclick="addIFields(0)">
																<i class="fa fa-plus" aria-hidden="true"></i> Illness
															</button>
														</div>
														<div class="col-4"></div>
													</div>
												</div>
												<div class="d-flex justify-content-center">
													<div class="form-row" style="width: 50%">
														<button type="button"
															class="btn btn-outline-success btn-block"
															id="preparationAdd" onclick="addPFields(0)">Add
															Preparation</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div id="step-3" class="tab-pane" role="tabpanel">
									<div class="row" style="height: auto">
										<div class="col-2">
											<nav id="navbarScrollspy"
												class="navbar navbar-light bg-light">
												<span class="navbar-brand">Plant Parts</span>
												<nav class="nav nav-pills flex-column" id="plantPartsNav">
												</nav>
											</nav>
										</div>
										<div class="container" data-spy="scroll" data-offset="60"
											data-target="#navbarScrollspy"
											style="height: 60vh; width: 80%; overflow-y: scroll; float: right;">
											<div class="card">
												<div class="card-header text-center">
													<h4 class="mt-1">Compound</h4>
												</div>
												<div class="card-body">
													<div id="speciesGroup">
														<input type="hidden" name="speciesCtr" value="0">
														<!-- <div class="d-flex justify-content-center">
															<div class="form-row" style="width: 50%">
																<button type="button"
																	class="btn btn-outline-success btn-block"
																	id="speciesAdd" onclick="addSFields(0)">Add
																	Species Part</button>
															</div>
														</div> -->
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
								<!-- <div id="step-4" class="tab-pane" role="tabpanel"></div> -->
							</div>
						</form>
					</div>



				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline-danger"
						data-toggle="modal" data-target="#cancelModal">Cancel</button>
					<button type="button" class="btn btn-danger" data-toggle="modal"
						data-target="#rejectModal">X Reject Entry</button>
					<button type="submit" class="btn btn-success disabled"
						data-toggle="modal" id="approveEntry">&#10003 Approve
						Entry</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Document Modal -->
	<div class="modal fade" id="docuModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="docuModalLabel">Document</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="embed-responsive embed-responsive-16by9">
						<iframe id="documentIframe" class="embed-responsive-item" src=""></iframe>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Cancel Validation Modal -->
	<div class="modal fade" id="cancelModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-md modal-dialog-scrollable rounded border border-secondary">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="cancelModalLabel">Cancel
						Validation</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Are you sure you want to cancel
					validation? All edits and changes you've made on this entry will be
					lost.</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline-secondary"
						data-dismiss="modal">Go back</button>
					<button type="button" class="btn btn-danger"
						onclick="(function(){
							window.location.href = 'ValidationServlet';
				    return false;
				})();return false;">Cancel
						Validation</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Reject Validation Modal -->
	<div class="modal fade" id="rejectModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-md modal-dialog-scrollable rounded border border-danger">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmModalLabel">Reject
						Validation</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Are you sure you want to reject and
					discard this entry?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline-secondary"
						data-dismiss="modal">Go Back</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Reject</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Confirm Validation Modal -->
	<div class="modal fade" id="confirmModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-md modal-dialog-scrollable rounded border border-success">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmModalLabel">Approve
						Validation</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Are you sure you want to approve and
					add this entry to the ontology?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline-secondary"
						data-dismiss="modal">Go Back</button>

					<button type="submit" class="btn btn-success" form="addPlant">Add</button>

				</div>
			</div>
		</div>
	</div>

	<!-- INCLUDE FOOTER HTML -->
	<%@include file="_includeFooter.html"%>
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

	<script
		src="https://cdn.jsdelivr.net/npm/smartwizard@5/dist/js/jquery.smartWizard.min.js"
		type="text/javascript"></script>

	<script type="text/javascript" src="js/autocomplete.js"></script>

	<script> 
		$("#smartwizard").on("leaveStep", function(e, anchorObject, currentStepIndex, nextStepIndex, stepDirection) {
            if(anchorObject.prevObject.length - 1 == nextStepIndex){
    			document.getElementById("approveEntry").classList.remove("disabled");
    			$('#approveEntry').attr("data-target","#confirmModal");
                /* alert('this is the last step');  */
            }else{
            	document.getElementById("approveEntry").classList.add("disabled");
            	$('#approveEntry').attr("data-target","");
            }
        });
		
		$("#smartwizard").on("leaveStep", function(e, anchorObject, currentStepIndex, nextStepIndex, stepDirection) {
			if($("#ScientificName").css("border-color") == 	"rgb(255, 0, 0)")
				document.getElementById("ScientificName").style.borderColor = null;
			if($("#CommonPlantName").css("border-color") == 	"rgb(255, 0, 0)")
				document.getElementById("CommonPlantName").style.borderColor = null;
			if($("#Genus").css("border-color") == 	"rgb(255, 0, 0)")
				document.getElementById("Genus").style.borderColor = null;
			if($("#Family").css("border-color") == 	"rgb(255, 0, 0)")
				document.getElementById("Family").style.borderColor = null;
        	document.getElementById("generalError").innerHTML ="";
            console.log(currentStepIndex);
            var hasError = false;
            if(currentStepIndex==0){
            	if(document.getElementById("ScientificName").value == ""){
            		document.getElementById("ScientificName").style.borderColor  = "rgb(255, 0, 0)";
            		
            	}
            	if(document.getElementById("CommonPlantName").value == ""){
            		document.getElementById("CommonPlantName").style.borderColor  = "rgb(255, 0, 0)";
            		$('#generalError').append('<div class="alert alert-danger" role="alert">Please input a common name.</div>');
            		hasError = true;
            	}
            	if(document.getElementById("CommonPlantName").value == document.getElementById("ScientificName").value){
            		document.getElementById("CommonPlantName").style.borderColor  = "rgb(255, 0, 0)";
            		document.getElementById("ScientificName").style.borderColor  = "rgb(255, 0, 0)";
            		$('#generalError').append('<div class="alert alert-danger" role="alert">Same common and scientific name is not allowed.</div>');
            		hasError = true;
            	}
	            if(document.getElementById("Genus").value == document.getElementById("Family").value && document.getElementById("Genus").value!=""){
	        		document.getElementById("Genus").style.borderColor  = "rgb(255, 0, 0)";
	        		document.getElementById("Family").style.borderColor  = "rgb(255, 0, 0)";
	        		$('#generalError').append('<div class="alert alert-danger" role="alert">Same genus and family name is not allowed.</div>');
	        		hasError = true;
	        	}
	            
/* 	            for (var i = 0; i < sciNames.length; i++) {
	            	if(document.getElementById("ScientificName").value.toLowerCase() == sciNames[i].toLowerCase()){
	            		$('#generalError').append('<div class="alert alert-danger" role="alert">Scientific name already exists in the ontology.</div>');
	            		hasError = true;
	            	}
	            		
	            }
	            
	            for (var i = 0; i < medPlantNames.length; i++) {
	            	if(document.getElementById("CommonPlantName").value.toLowerCase() == medPlantNames[i].toLowerCase()){
	            		$('#generalError').append('<div class="alert alert-danger" role="alert">Common plant name is already exists in the ontology.</div>');
	            		hasError = true;
	            	}
	            		
	            } */
            }else if(currentStepIndex==1){

            }

	        return !hasError;
        });
		

	</script>

	<script>
	function call_wizard(){
		$('#smartwizard').smartWizard({
			  selected: 0, // Initial selected step, 0 = first step
			  theme: 'arrows', // theme for the wizard, related css need to include for other than default theme
			  justified: true, // Nav menu justification. true/false
			  darkMode: false, // Enable/disable Dark Mode if the theme supports. true/false
			  autoAdjustHeight: true, // Automatically adjust content height
			  cycleSteps: false, // Allows to cycle the navigation of steps
			  backButtonSupport: true, // Enable the back button support
			  enableURLhash: true, // Enable selection of the step based on url hash
			  transition: {
			      animation: 'fade', // Effect on navigation, none/fade/slide-horizontal/slide-vertical/slide-swing
			      speed: '400', // Transion animation speed
			      easing:'' // Transition animation easing. Not supported without a jQuery easing plugin
			  },
			  toolbarSettings: {
			      toolbarPosition: 'bottom', // none, top, bottom, both
			      toolbarButtonPosition: 'right', // left, right, center
			      showNextButton: true, // show/hide a Next button
			      showPreviousButton: true, // show/hide a Previous button
			      toolbarExtraButtons: [] // Extra buttons to show on toolbar, array of jQuery input/buttons elements
			  },
			  anchorSettings: {
			      anchorClickable: true, // Enable/Disable anchor navigation
			      enableAllAnchors: false, // Activates all anchors clickable all times
			      markDoneStep: true, // Add done state on navigation
			      markAllPreviousStepsAsDone: true, // When a step selected by url hash, all previous steps are marked done
			      removeDoneStepOnNavigateBack: true, // While navigate back done step after active step will be cleared
			      enableAnchorOnDoneStep: true // Enable/Disable the done steps navigation
			  },
			  keyboardSettings: {
			      keyNavigation: true, // Enable/Disable keyboard navigation(left and right keys are used if enabled)
			      keyLeft: [37], // Left key code
			      keyRight: [39] // Right key code
			  },
			  lang: { // Language variables for button
			      next: 'Next',
			      previous: 'Previous'
			  },
			  disabledSteps: [], // Array Steps disabled
			  errorSteps: [], // Highlight step with errors
			  hiddenSteps: [] // Hidden steps
			});
	}
	</script>
	<script type="text/javascript">
	    function viewDocument(docu) {
	    	var entryDocument = "\\NatPro\\Documents\\UploadedDocuments\\"+docu;
	    	var modalIframe = document.getElementById('documentIframe');
	    	
	    	if (entryDocument != null) {
	    		console.log(entryDocument);
	    		modalIframe.src = entryDocument;
	    	} else {
	    		modalIframe.src = 'https://www.youtube.com/embed/ZKYEQXN_nk0';
	    	}
	    }
	</script>

	<script type="text/javascript">
		function checkIfFieldChanged(data){
			$("#CommonPlantName").keyup(function () { 
				if($("#CommonPlantName").val()!=data.medicinalPlant)
					document.getElementById("CommonPlantName").style.borderColor  = "#ff9900";
				else
					document.getElementById("CommonPlantName").style.borderColor  = null;
			});
			
			$("#Genus").keyup(function () { 
				if($("#Genus").val()!=data.species[0].genus)
					document.getElementById("Genus").style.borderColor  = "#ff9900";
				else 
					document.getElementById("Genus").style.borderColor  = null;
			});
			
			$("#ScientificName").keyup(function () { 
				if($("#ScientificName").val()!=data.species[0].specie)
					document.getElementById("ScientificName").style.borderColor  = "#ff9900";
				else
					document.getElementById("ScientificName").style.borderColor  = null;
			});
			
		}
		function getPlantEntity(fileName, id, sciName){
			/* autocomplete(document.getElementById("SpeciesPart"), plantParts);  */
			console.log(fileName);
			console.log(sciName);
			console.log(id);
	 		$.ajax({
				type : "GET",
				url : 'GetPlantEntity',
				dataType: 'json',
				data: {
					fileName: fileName,
					sciName: sciName
					  },
				success : function(data) {
					console.log(data);
					/* console.log(); */
					document.getElementById("entryModalLabel").innerHTML =  "Validating "+data.species[0].specie+" Entry";					
					$("#entryViewDocument").attr("onclick","viewDocument('"+fileName+"')");
					document.getElementById("ScientificName").value=data.species[0].specie;
					if(data.species[0].specie == data.medicinalPlant){
						document.getElementById("CommonPlantName").value = "";
					}else{
						document.getElementById("CommonPlantName").value=data.medicinalPlant;
					}
					if(data.species[0].family!=null){
						document.getElementById("Family").value=data.species[0].family;
					}
					
					var locs = data.locations;
					if(locs!=null){
	 					locs.forEach(function(elem, index, array) {
						    addLFields(id,elem);
						});
					}
					
					var speciesParts = data.species[0].speciesParts;
					
					if(speciesParts!=null){
						var cCtr = 0;
						speciesParts.forEach(function(elem, spIndex, array) { 
							var plantPart = data.species[0].speciesParts[spIndex].plantPart;
							if(plantPart == "tempSp"){
								plantPart = "plant";
							}
						    addSFields(spIndex); 
						    document.getElementById("SpeciesPart"+spIndex).value = plantPart;	
						   
 						    var compounds = data.species[0].speciesParts[spIndex].compounds;
						    var compoundDropdown = '<li class="nav-item dropdown">'+
							'<a	class="nav-link dropdown-toggle" data-toggle="dropdown"	href="#" role="button" aria-haspopup="true"	aria-expanded="false">'+plantPart+'</a>'+
					    	'<div class="dropdown-menu">'+
					    	'<a class="dropdown-item" href="#SpeciesContainer'+spIndex+'">'+plantPart+'</a>'+
					    	'<div role="separator" class="dropdown-divider"></div>';
					    	var compoundDropdownEnd = '</div></li>';
						    if(compounds!=null){
								var compoundItems="";
						    	compounds.forEach(function(elem, cIndex, array) {
						    		var compound = data.species[0].speciesParts[spIndex].compounds[cIndex].compoundName;
						    		compoundItems = compoundItems+'<a class="dropdown-item" href="#ChemicalCompound['+spIndex+']['+cIndex+']">'+compound+'</a>';
						    		addCCFields(spIndex);
						    		document.getElementById("ChemicalCompound["+spIndex+"]["+cIndex+"]").value=compound;
						    		
						    		var bioActs = data.species[0].speciesParts[spIndex].compounds[cIndex].bioActs;
						    		bioActs.forEach(function(elem, bIndex, array) {
						    			addBAFields(cCtr,spIndex,cIndex);
						    			var bioAct = data.species[0].speciesParts[spIndex].compounds[cIndex].bioActs[bIndex].biologicalActivity;						    			
						    			document.getElementById("BiologicalActivity["+spIndex+"]["+cIndex+"]["+bIndex+"]").value=bioAct;
						    			try {
						    				var cellLine = data.species[0].speciesParts[spIndex].compounds[cIndex].bioActs[bIndex].cellLine.cellLine; 
						    				document.getElementById("CellLine["+spIndex+"]["+cIndex+"]["+bIndex+"]").value=cellLine;
						    			}catch(err) {}
						    		});		
						    		cCtr++;
						    	});
						    	compoundDropdown = compoundDropdown + compoundItems;
						    	
						    }
						    compoundDropdown = compoundDropdown + compoundDropdownEnd;
						    $('#plantPartsNav').append(compoundDropdown);
						});
					} 
					/* checkIfFieldChanged(data); */
				}
				}); 
		}
	</script>
	<script type="text/javascript">
	var lCtr = 0;
	var empty = "\'\'";
	function addLFields(id, locName) {
		/* console.log(id, locName); */
		$('#locationAdd').remove();
		lCtr++;		
		var inputField = '<div class="col-13"><input type="text" class="form-control" name="location" id="Location['+id +']['+lCtr+']" placeholder="" value="'+locName+'">';
		var buttonAdd = '<div><button id="locationAdd" type="button" class="btn btn-outline-success btn-sm" onclick="addLFields('+id+','+empty+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Location</button></div>';
		
		$('#locationGroup').append(inputField, buttonAdd);
	}
	</script>

	<script type="text/javascript">

	
	   $(document).on('hidden.bs.modal', '.modal', function () {
	        $('.modal:visible').length && $(document.body).addClass('modal-open');
	       
	   });
	   
	   $(document).ready(function() { 
		   $('#smartwizard').smartWizard("reset"); 
		   console.log(${JsonValidations});
	   });
	   		
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

	<!-- Script to Generate Autocomplete Fields -->
	<script>	
		var plantParts = [];
		var sciNames = [];
		var medPlantNames = [];
	</script>

	<c:forEach items="${plantPartsList}" var="plantPart">
		<script>	
			plantParts.push("${plantPart}")
		</script>
	</c:forEach>

	<c:forEach items="${medPlantNameList}" var="medPlantName">
		<script>	
		medPlantNames.push("${medPlantName}")
		</script>
	</c:forEach>

	<c:forEach items="${sciNameList}" var="sciName">
		<script>	
		sciNames.push("${sciName}")
		</script>
	</c:forEach>

	<script>
	
		var speciestPartsList = "${speciesPartsList}";
	
		var snCtr, lCtr, pbpCtr, iCtr, baclCtr, ccCtr, sCtr;
		snCtr = lCtr = pbpCtr = iCtr = baclCtr = 0;
		var sCtr = ccCtr = 0; //id's starts at 0;
		var speciesArr = [];
		var speciesArr2 = [ ]
		var compArr = [ ];
		var prepArr = [ 0 ];
		
		function removeFields(pCategory, pValue) {
			var field, field2, field3, button, label;
			
			switch (pCategory) {
				case 0: //Location
					field = '#Location' + pValue;
					button = '#locationDelete' + pValue;
					$(field).remove();
					$(button).remove();
					$('label[for=Location' + pValue +']').remove();
					console.log(field, button, 'label[for=Location' + pValue +']');
					break;
				case 1: // Illness
					field = '#Illness' + pValue;
					button = '#illnessDelete' + pValue;
					$(field).remove();
					$(button).remove();
					$('label[for=Illness' + pValue +']').remove();
					console.log(field, button, 'label[for=Illness' + pValue +']');
					break;
				case 2: // Preparation
					field = '#preparationContainer' + pValue;
					button = '#preparationDelete' + pValue;
					$(field).remove();
					$(button).remove();
					$('label[for=Preparation' + pValue +']').remove();
					console.log(field, button, 'label[for=PreparationGroup' + pValue +']');
					break;
				case 3: // Chemical Compound
					field = '#chemicalCompoundContainer' + pValue;
					field2 = '#biologicalActivityGroup' + pValue;
					field3 = '#chemicalCompoundButtonContainer' + pValue;
					button = '#chemicalCompoundDelete' + pValue;
					$(field).remove();
					$(field2).remove();
					//$(field3).remove();
					$(button).remove();
					$('label[for=ChemicalCompound' + pValue +']').remove();
					console.log(field, button, 'label[for=ChemicalCompound' + pValue +']');
					break;
				case 4: // Biological Activity
					field = '#biologicalActivityContainer' + pValue;
					button = '#biologicalActivityDelete' + pValue;
					$(field).remove();
					$(button).remove();
					$('label[for=BiologicalActivity' + pValue +']').remove();
					console.log(field, button, 'label[for=' + pValue +']');
					break;
				case 5: // Species Part
					field = '#SpeciesContainer' + pValue;
					button = '#speciesDelete' + pValue;
					$(field).remove();
					$(button).remove();
					//$('label[for=BiologicalActivity' + pValue +']').remove();
					//console.log(field, button, 'label[for=' + pValue +']');
					break;
			}
		}
				
		function addIFields(pValue) {
			prepArr[pValue]++;
			var buttonAddText = "#illnessAdd" + pValue;
	
			if (pValue == 0) {
				$('#illnessAdd').remove();
			} else {
				$(buttonAddText).remove();
			}
	
			iCtr++;
						   
			var inputField = '<div class="col-4"></div><div class="col-4"><label for="Illness'+ iCtr +'">Illness <i class="fa fa-viruses"></i></label><input type="text" class="form-control" id="Illness'+ iCtr +'" placeholder="" name="illness['+pValue+']['+prepArr[pValue]+']"><input type="hidden" name="illnessCtr['+pValue+']" value="'+prepArr[pValue]+'">';
			
			if (pValue == 0) {
				var buttonAdd = '<div>'+
									'<button id="illnessAdd" type="button" class="btn btn-outline-success btn-sm" style="margin-top:5px" onclick="addIFields('+pValue+')"><i class="fa fa-plus" aria-hidden="true"></i> Illness</button>'+
								'</div>'+
								'<div class="col-4"></div>'+
								'<button id="illnessDelete'+iCtr+'" type="button" class="btn btn-outline-danger btn-sm" style="margin-top:5px" onclick="removeFields(1,'+iCtr+')"><i class="fa fa-minus" aria-hidden="true"></i> Delete</button></div><div class="col-4"></div></div>';
			} else {
				var buttonAdd = '<div><button id="illnessAdd'+pValue+'" type="button" class="btn btn-outline-success btn-sm" style="margin-top:5px" onclick="addIFields('+pValue+')"><i class="fa fa-plus" aria-hidden="true"></i> Illness</button></div><div class="col-4"></div>'+
								'<button id="illnessDelete'+iCtr+'" type="button" class="btn btn-outline-danger btn-sm" style="margin-top:5px" onclick="removeFields(1,'+iCtr+')"><i class="fa fa-minus" aria-hidden="true"></i> Delete</button></div><div class="col-4"></div></div>';
			}
	
			var newIllness = inputField + buttonAdd;
			var groupAddText = "#illnessGroup" + pValue;
	
			if (pValue == 0) {
				$('#illnessGroup').append(newIllness);
			} else {
				$(groupAddText).append(newIllness);
			}
		}
		
		function addPFields(pValue) {
			prepArr.push(0);
			console.log(prepArr);
			var buttonAddText = "#preparationAdd" + pValue;
	
			if (pValue == 0) {
				$('#preparationAdd').remove();
			} else
				$(buttonAddText).remove(); 
	
			pbpCtr++;
			iCtr++;
	
			var openingDiv = '<div class="border-bottom border-secondary mb-3" id="preparationContainer'+pbpCtr+'"><div class="form-group form-row">';
			
			var inputPreparation = '<div class="col-1"></div>'+
									'<div class="col">'+
									   '<label for="Preparation'+ pbpCtr+'">Preparation</label>'+
									   '<input type="text" class="form-control" id="Preparation'+ pbpCtr+'" placeholder="" name="preparation['+ (pValue + 1) +']">'+
									   '<input type="hidden" name="prepCtr" value="'+(pValue+1)+'">'+
								   '</div>';		
			
			var inputUtilizedPlant = '<div class="col">'+
									  '<label for="PlantPart'+ pbpCtr+'">Plant Part</label>'+
										'<select name="prepPart['+(pValue + 1)+']" class="custom-select custom-select-md mb-3" id="PlantPart'+ pbpCtr+'" onchange="enablePPO('+ pbpCtr+')">'+
										  	'<option value="" selected disabled>Open this select menu</option>'+
										  	'<option value="-1">Others (please write on the right side)</option>'+
										  	'<c:forEach items="${plantPartsList}" var="plantPartsList">'+
												'<option value="${plantPartsList}">${plantPartsList}</option>'+
											'</c:forEach>'+
										'</select>'+
									'</div>';
									
			var inputPlantPartOther = '<div class="col">'+
										  '<label for="PlantPartOther['+ pbpCtr+']">Plant Part (Other)</label>'+
										  '<input type="text" class="form-control" id="PlantPartOther['+ pbpCtr+']" placeholder="" disabled>'+
									  '</div>'+
									  '<div class="col-1"> </div>';
									  
			var closingDiv = '</div>';
			
			
			var inputIllness = '<div class="form-group form-row" id="illnessGroup'+ pbpCtr +'">'+	
							   '<div class="col-4"> </div>'+
							   '<div class="col-4"><label for="Illness'+ iCtr +'">Illness <i class="fa fa-viruses"></i></label><input type="text" class="form-control" id="Illness'+ iCtr +'" placeholder="" name="illness['+(pValue + 1)+'][0]"><input type="hidden" name="illnessCtr['+(pValue + 1)+']" value="'+prepArr[pValue]+'">'+
							   '<button id="illnessAdd'+pbpCtr+'" type="button" class="btn btn-outline-success btn-sm" style="margin-top:5px" onclick="addIFields('+pbpCtr+')">Illness</button></div>'+
							   '<div class="col-4"></div>' +
							   '</div>';
							   
			var buttonAdd = '<div class="d-flex justify-content-center">'+
								'<div class="form-row" style="width: 50%">'+
									'<button type="button" class="btn btn-outline-success btn-block btn" id="preparationAdd'+pbpCtr+'" onclick="addPFields('+pbpCtr+')">Add Preparation</button>'
								'</div>'+
							'</div>';	
							
			var buttonDelete = '<div class="d-flex justify-content-center">'+
								   '<div class="form-row" style="width: 50%">'+
									   '<button type="button" class="btn btn-outline-danger btn-block" id="preparationDelete'+pbpCtr+'" onclick="removeFields(2,'+pbpCtr+')">Delete Preparation</button>'+
								   '</div>'+
							   '</div>';
			
			var newPrep = openingDiv + inputPreparation + inputUtilizedPlant + inputPlantPartOther + closingDiv + inputIllness + closingDiv  + buttonAdd + buttonDelete;
			
			$('#preparationGroup').append(newPrep);
	
		}
		
		function addBAFields(pValue, sVal, cVal) {
			var buttonAddText = "#biologicalActivityAdd\\["+sVal+"\\]\\["+cVal+"\\]";
			$(buttonAddText).remove();
			speciesArr2[sVal][cVal]++;
			/* if (pValue == 0) {
				$('#biologicalActivityAdd').remove();
			} else {
				$(buttonAddText).remove();
			} */
	
			baclCtr++;
			
			/* if (pValue == 0) {
				
				var newRow = '<div class="form-group form-row" id="biologicalActivityContainer'+baclCtr+'">';
				
				var inputBioAct = '<div class="col-3"> </div>'+
								  '<div class="col-4">'+
									  '<label for="BiologicalActivity'+ baclCtr +'">Biological Activity</label>'+
									  '<input type="text" class="form-control" id="BiologicalActivity'+ baclCtr +'" placeholder="" name="bioAct['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']">'+
								  '</div>';
					  		  
		  		var inputCellLine = '<div class="col-4">'+
											'<label for="CellLine'+ baclCtr +'">Cell Line</label>'+
											'<input type="text" class="form-control" id="CellLine'+ baclCtr +'" placeholder="" name="cellLine['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']">'+
											'<input type="hidden" name="bioCellCC0" value="0">'+ // NOT YET USED(?)
											'<input type="hidden" name="lengthBC['+sVal+']['+cVal+']" value="'+speciesArr2[sVal][cVal]+'">'+
									'</div>';
									
				var inputButtons = '<div class="form-row">'+
									   '<div class="col-3"></div>'+
									   '<div class="col-4">'+
										   '<button id="biologicalActivityDelete'+baclCtr+'" type="button"'+
											   'class="btn btn-outline-danger btn-sm"'+
											   'onclick="removeFields(4, '+ baclCtr +')" style="margin-top: -17px">'+
											   '<i class="fa fa-minus" aria-hidden="true"></i> Delete'+
										   '</button>'+
										   '<button id="biologicalActivityAdd" type="button"'+
											   'class="btn btn-outline-success btn-sm"'+
											   'onclick="addBAFields('+pValue+','+sVal+','+cVal+')" style="margin-top: -5px">'+
											   '<i class="fa fa-plus" aria-hidden="true"></i> Biological'+
											   'Activity'+
										   '</button>'+
									   '</div>'+
								   '</div>';
									
  				var endRow = '</div>';
				
				var newBioAct = newRow + inputBioAct + inputCellLine  + endRow + inputButtons;
	
				$('#biologicalActivityGroup').append(newBioAct);
			} else { */
				var newRow = '<div class="form-group form-row" id="biologicalActivityContainer'+baclCtr+'">';
				
				var inputBioAct = '<div class="col-3"> </div>'+
							  	  '<div class="col-4">'+
									  '<label for="BiologicalActivity'+ baclCtr +'">Biological Activity</label>'+
									  '<input type="text" class="form-control" id="BiologicalActivity['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']" placeholder="" name="bioAct['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']">'+
									  //'<button id="biologicalActivityAdd'+pValue+'" type="button" class="btn btn-outline-success btn-sm" onclick="addBAFields('+pValue+','+sVal+','+cVal+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Biological Activity</button>'+
									  //'<button id="biologicalActivityDelete'+ baclCtr +'" type="button" class="btn btn-outline-danger btn-sm" onclick="removeFields(4, '+ baclCtr +')" style="margin-top: 5px"><i class="fa fa-minus" aria-hidden="true"></i> Delete</button>'+
					  		       '</div>';
					  		  
		  		var inputCellLine = '<div class="col-4">'+
											'<label for="CellLine'+ baclCtr +'">Cell Line</label>'+
											'<input type="text" class="form-control" id="CellLine['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']" placeholder="" name="cellLine['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']">'+
											'<input type="hidden" name="bioCellCC0" value="0">'+ // NOT YET USED(?)
											'<input type="hidden" name="lengthBC['+sVal+']['+cVal+']" value="'+speciesArr2[sVal][cVal]+'">'+
									'</div>';
									
  				var endRow = '</div>';
  				
  				var inputButtons = '<div class="form-row">'+
									   '<div class="col-3"></div>'+
									   '<div class="col-4">'+
										   '<button id="biologicalActivityDelete'+baclCtr+'" type="button"'+
											   'class="btn btn-outline-danger btn-sm"'+
											   'onclick="removeFields(4, '+ baclCtr +')" style="margin-top: -17px">'+
											   '<i class="fa fa-minus" aria-hidden="true"></i> Delete'+
										   '</button>'+
										   '<button id="biologicalActivityAdd['+sVal+']['+cVal+']" type="button"'+
											   'class="btn btn-outline-success btn-sm"'+
											   'onclick="addBAFields('+pValue+','+sVal+','+cVal+')" style="margin-top: -5px">'+
											   '<i class="fa fa-plus" aria-hidden="true"></i> Biological'+
											   'Activity'+
										   '</button>'+
									   '</div>'+
								   '</div>';
				
				var newBioAct = newRow + inputBioAct + inputCellLine + endRow + inputButtons;				
				var inputGroupText = '#biologicalActivityGroup\\['+sVal+'\\]\\['+cVal+'\\]';
				
				$(inputGroupText).append(newBioAct);
			/* } */
		}
		
		function addCCFields(pValue) {
			speciesArr[pValue]++;
			speciesArr2[pValue].push(0);
			/* console.log(speciesArr2); */
			var buttonAddText = "#chemicalCompoundAdd" + pValue;
	
			$(buttonAddText).remove();
			if (pValue == 0) {
				$('#chemicalCompoundAdd').remove();
			} else {
				$(buttonAddText).remove();
			}
			ccCtr++;
			baclCtr++;

/* 			if (pValue == 0) {
				var chemComp = '<div class="form-group form-row" id="chemicalCompoundContainer'+ccCtr+'">'+
								   '<div class="col-2"> </div>'+
								   '<div class="col-5">'+
									   '<label for="ChemicalCompound'+ ccCtr +'">Chemical Compound</label>'+
			    					   '<input type="text" class="form-control" id="ChemicalCompound'+ ccCtr +'" placeholder="" name="compound['+pValue+']['+speciesArr[pValue]+']">'+
									   '<input type="hidden" name="compoundCtr" value="'+ccCtr+'">'+
									   '<input type="hidden" name="lengthCC'+pValue+'" value="'+speciesArr[pValue]+'">'+
								   '</div>'+
								   '<div class="w-100"></div>'+
							   '</div>';
							   
				var buttons = '<div class="form-group form-row" id="chemicalCompoundButtonContainer'+ccCtr+'">'+
								  '<div class="col-2"> </div>'+
							      '<div class="col-10">'+
								      '<button id="chemicalCompoundDelete'+ccCtr+'" type="button"'+
									      'class="btn btn-outline-danger btn-sm"'+
									      'onclick="removeFields(3,'+ccCtr+')" style="margin-top: 5px">'+
									      '<i class="fa fa-minus" aria-hidden="true"></i> Delete'+
								      '</button>'+
								      '<button id="chemicalCompoundAdd" type="button"'+
										      'class="btn btn-outline-success btn-sm"'+
										      'onclick="addCCFields('+pValue+')" style="margin-top: 5px">'+
										      '<i class="fa fa-plus" aria-hidden="true"></i> Chemical'+
										      'Compound'+
								      '</button>'+
							      '</div>'+
						      '</div>';
						      
				var bioAct = '<div id="biologicalActivityGroup'+ ccCtr+'">'+
							 	'<div class="form-group form-row">'+
									 '<div class="col-3"> </div>'+
									 '<div class="col-4">'+
										 '<label for="BiologicalActivity'+ baclCtr +'">Biological Activity</label>'+
					  					 '<input type="text" class="form-control" id="BiologicalActivity'+ baclCtr +'" placeholder="" name="bioAct['+pValue+']['+speciesArr[pValue]+'][0]">'+
					  				  	 '<button id="biologicalActivityAdd'+ ccCtr+'" type="button" class="btn btn-outline-success btn-sm" onclick="addBAFields('+ccCtr+','+pValue+','+speciesArr[pValue]+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Biological Activity</button>'+
					  				 '</div>'+
									 '<div class="col-4">'+
										 '<label for="CellLine">Cell Line</label>'+
					  						 '<input type="text" class="form-control" id="CellLine" placeholder="" name="cellLine['+pValue+']['+speciesArr[pValue]+'][0]">'+
					  						 '<input type="hidden" name="bioCellCC0" value="0">'+ // NOT YET USED(?)
					  						 '<input type="hidden" name="lengthBC['+pValue+']['+speciesArr[pValue]+']" value="'+speciesArr2[pValue][speciesArr[pValue]]+'">'+
									 '</div>'+
								 '</div>'+
							 '</div>';
					
				var chemCompFields = chemComp + buttons + bioAct;
				
				$('#chemicalCompoundGroup').append(chemCompFields);
			} else { */
				var chemComp = '<div class="form-group form-row" id="chemicalCompoundContainer'+ccCtr+'">'+
								   '<div class="col-2"> </div>'+
								   '<div class="col-5">'+
									   '<label for="ChemicalCompound'+ ccCtr +'">Chemical Compound</label>'+
									   '<input type="text" class="form-control" id="ChemicalCompound['+pValue+']['+speciesArr[pValue]+']" placeholder="" name="compound['+pValue+']['+speciesArr[pValue]+']">'+
									   //'<button id="chemicalCompoundAdd'+pValue+'" type="button" class="btn btn-outline-success btn-sm" onclick="addCCFields('+pValue+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Chemical Compound</button>'+
									   '<input type="hidden" name="compoundCtr" value="'+ccCtr+'">'+
									   '<input type="hidden" name="lengthCC'+pValue+'" value="'+speciesArr[pValue]+'">'+
								   '</div>'+
							   '</div>';
							   
			   var buttons = '<div class="form-group form-row" id="chemicalCompoundButtonContainer'+ccCtr+'">'+
								  '<div class="col-2"> </div>'+
							      '<div class="col-10">'+
								      '<button id="chemicalCompoundDelete'+ccCtr+'" type="button"'+
									      'class="btn btn-outline-danger btn-sm"'+
									      'onclick="removeFields(3,'+ccCtr+')" style="margin-top: 5px">'+
									      '<i class="fa fa-minus" aria-hidden="true"></i> Delete'+
								      '</button>'+
								      '<button id="chemicalCompoundAdd'+pValue+'" type="button"'+
										      'class="btn btn-outline-success btn-sm"'+
										      'onclick="addCCFields('+pValue+')" style="margin-top: 5px">'+
										      '<i class="fa fa-plus" aria-hidden="true"></i> Chemical'+
										      'Compound'+
								      '</button>'+
							      '</div>'+
						      '</div>';
							  
				var bioAct = '<div id="biologicalActivityGroup['+pValue+']['+speciesArr[pValue]+']">'+
							 	'<div class="form-group form-row">'+
									 '<div class="col-3"> </div>'+
									 '<div class="col-4">'+
										 '<label for="BiologicalActivity'+ baclCtr +'">Biological Activity</label>'+
					  					 '<input type="text" class="form-control" id="BiologicalActivity['+pValue+']['+speciesArr[pValue]+'][0]" placeholder="" name="bioAct['+pValue+']['+speciesArr[pValue]+'][0]">'+
					  					 '<button id="biologicalActivityAdd['+pValue+']['+speciesArr[pValue]+']" type="button" class="btn btn-outline-success btn-sm" onclick="addBAFields('+ccCtr+','+pValue+','+speciesArr[pValue]+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Biological Activity</button>'+
					  				 '</div>'+
									 '<div class="col-4">'+
										 '<label for="CellLine'+ baclCtr +'">Cell Line</label>'+
					  						 '<input type="text" class="form-control" id="CellLine['+pValue+']['+speciesArr[pValue]+'][0]" placeholder="" name="cellLine['+pValue+']['+speciesArr[pValue]+'][0]">'+
					  						 '<input type="hidden" name="bioCellCC0" value="0">'+ // NOT YET USED(?)
					  						 '<input type="hidden" name="lengthBC['+pValue+']['+speciesArr[pValue]+']" value="'+speciesArr2[pValue][speciesArr[pValue]]+'">'+
									 '</div>'+
								 '</div>'+
							 '</div>';							
							 /* console.log(speciesArr[pValue]); */
								
				var chemCompFields = chemComp + buttons + bioAct;
	
				var inputGroupText = "#chemicalCompoundGroup" + pValue;
	
				$(inputGroupText).append(chemCompFields);
			/* } */
	
		}
		
		function addSFields(pValue) {
			speciesArr.push(0);
			speciesArr2.push([ 0 ]);
			/* console.log(speciesArr2); */
			var buttonAddText = "#speciesAdd";
	
			if (pValue == 0) {
				$('#speciesAdd').remove();
			} else {
				$(buttonAddText).remove();
			}
	

			var speciesPPCluster = '<div class="border-bottom border-secondary mb-3" id="SpeciesContainer'+sCtr+'"><div class="form-group form-row">'+
									   '<div class="col-1"></div>'+
									   '<div class="autocomplete" id="plantPartInputDiv'+sCtr+'">'+
										   '<label for="SpeciesPlantPart"><i class="fa fa-leaf" aria-hidden="true"></i> Species Plant Part</label> '+
										   '<input type="text" id="SpeciesPart'+sCtr+'"	name="plantPart['+sCtr+']" class="form-control">'+
										   '<input type="hidden" name="speciesCtr" value="'+sCtr+'">'+
										'</div>'+
								   '</div>';
			
			var chemcompCluster = '<div id="chemicalCompoundGroup'+ sCtr+'">'+
									  '<div class="form-group form-row">'+
										  '<div class="col-2"> </div>'+
										  '<div class="col-5">'+
											  '<label for="ChemicalCompound'+ ccCtr +'">Chemical Compound</label>'+
											  '<input type="text" class="form-control" id="ChemicalCompound['+sCtr+'][0]" placeholder="" name="compound['+sCtr+'][0]">'+
											  '<button id="chemicalCompoundAdd'+sCtr+'" type="button" class="btn btn-outline-success btn-sm" onclick="addCCFields('+sCtr+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Chemical Compound</button>'+
											  '<input type="hidden" name="compoundCtr" value="'+ccCtr+'">'+
											  '<input type="hidden" name="lengthCC'+sCtr+'" value="'+speciesArr[pValue]+'">'+
										  '</div>'+
									  '</div>'+
								
									  '<div id="biologicalActivityGroup['+sCtr+']['+speciesArr[pValue]+']">'+
										  '<div class="form-group form-row">'+
											  '<div class="col-3"> </div>'+
											  '<div class="col-4">'+
												  '<label for="BiologicalActivity'+ baclCtr +'">Biological Activity</label>'+
							  					  '<input type="text" class="form-control" id="BiologicalActivity['+sCtr+'][0][0]" placeholder="" name="bioAct['+sCtr+'][0][0]">'+
							  					  '<button id="biologicalActivityAdd['+sCtr+']['+speciesArr[pValue]+']" type="button" class="btn btn-outline-success btn-sm" onclick="addBAFields('+ccCtr+','+sCtr+','+speciesArr[pValue]+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Biological Activity</button>'+
							  				  '</div>'+
											  '<div class="col-4">'+
												  '<label for="CellLine'+ baclCtr +'">Cell Line</label>'+
							  						  '<input type="text" class="form-control" id="CellLine['+sCtr+'][0][0]" placeholder="" name="cellLine['+sCtr+'][0][0]">'+
							  						  '<input type="hidden" name="bioCellCC0" value="0">'+ //NOT YET USED (?)
							  						  '<input type="hidden" name="lengthBC['+sCtr+']['+speciesArr[pValue]+']" value="'+speciesArr2[sCtr][speciesArr[pValue]]+'">'+
											  '</div>'+
										  '</div>'+
									  '</div>'+
								  '</div>'; 
									
			var buttonAddSpecies =  '<div class="d-flex justify-content-center" style="padding-top:5%; padding-bottom:5%;">'+
										'<div class="form-row">'+											
											'<button type="button" class="btn btn-outline-danger btn-block" id="speciesDelete'+sCtr+'" onclick="removeFields(5, '+sCtr+')">Delete Species Part</button>'+
										'</div>'+
									'</div>'+									
									'</div>'+
									'<button type="button" class="btn btn-outline-success btn-block" id="speciesAdd" onclick="addSFields(0)">Add Species Part</button>';

			var speciesComponent = speciesPPCluster + chemcompCluster + buttonAddSpecies +"</div>";
			
			$('#speciesGroup').append(speciesComponent);
			autocomplete(document.getElementById("SpeciesPart"+sCtr), plantParts);
			sCtr++;
			ccCtr++;
			baclCtr++;
		}
		
		function enablePPO(pValue) {
			var selectPlantPart = "#PlantPart" + pValue;
			var selectPlantPartOther = "#PlantPartOther[" + pValue + "]";			
			
			if (pValue == 0) {
				console.log($('#PlantPart').val());
				
				if ($('#PlantPart').val() == -1) {
					document.getElementById("PlantPartOther[0]").disabled = false;
				} else {
					document.getElementById("PlantPartOther[0]").disabled = true;
				}
			} else {
				console.log($(selectPlantPart).val());
				var selectPlantPartOtherTemp = "PlantPartOther[" + pValue + "]";
				
				if ($(selectPlantPart).val() == -1) {
					document.getElementById(selectPlantPartOtherTemp).disabled = false;
				} else {
					document.getElementById(selectPlantPartOtherTemp).disabled = true;
				}
			}
		}
		
		function enableSPPO(pValue) {
			var selectSpeciesPlantPart = "#SpeciesPlantPart" + pValue;
			var selectSpeciesPlantPartOther = "#SpeciesPlantPartOther[" + pValue + "]";			
			
			if (pValue == 0) {
				console.log($('#SpeciesPlantPart').val());
				
				if ($('#SpeciesPlantPart').val() == -1) {
					document.getElementById("SpeciesPlantPartOther[0]").disabled = false;
				} else {
					document.getElementById("SpeciesPlantPartOther[0]").disabled = true;
				}
			} else {
				console.log($(selectSpeciesPlantPart).val());
				var selectPlantPartOtherTemp = "SpeciesPlantPartOther[" + pValue + "]";
				
				if ($(selectSpeciesPlantPart).val() == -1) {
					document.getElementById(selectPlantPartOtherTemp).disabled = false;
				} else {
					document.getElementById(selectPlantPartOtherTemp).disabled = true;
				}
			}
		}
	
	</script>


</body>
</html>