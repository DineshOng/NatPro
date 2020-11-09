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

<title>NatPro : ${searchKey}</title>
</head>
<body>
	<!-- INCLUDE NAV BAR HTML -->
	<%@include file="navbarnix.html"%>

	<!-- HTML CODE -->
	<div class="jumbotron jumbotron-fluid bg-success"
		style="text-align: center">
		<div class="container text-white" style="padding-bottom: 30px">
			<h1 class="display-4">Add</h1>
			<p class="lead">Manually add a plant here</p>
		</div>

		<div class="container-fluid " style="text-align: left">
			<form action="AddPlantServlet" method="POST">
				<div class="card">
					<div class="card-header text-center">
						<h4 class="mt-1">General</h4>
					</div>
					<div class="card-body">
						<div class="form-group form-row">
							<div class="col-4"></div>
							<div class="col-4">
								<label for="CommonPlantName"> <i
									class="fa fa-address-card" aria-hidden="true"></i> Common Plant
									Name
								</label> <input type="text" id="CommonPlantName" name="commonPlantName"
									class="form-control" placeholder="" required>
							</div>
						</div>
						<div class="form-group form-row">
							<div class="col-2"></div>
							<div class="col-4">
								<label for="Genus"><i class="fa fa-group"
									aria-hidden="true"></i> Genus</label> <input type="text"
									class="form-control" name="genus" id="Genus" placeholder="">
							</div>
							<div class="col-4">
								<label for="Family"><i class="fa fa-group"
									aria-hidden="true"></i> Family</label> <input type="text"
									class="form-control" name="family" id="Family" placeholder="">
							</div>
						</div>

						<div class="form-group form-row">
							<div class="col-2"></div>
							<div class="col-4">
								<label for="ScientificName"><i
									class="fa fa-drivers-license" aria-hidden="true"></i>
									Scientific Name</label> <input type="text" class="form-control"
									name="scientificName" id="ScientificName" pattern="^([A-Z][a-z]+) +([a-z-]+).*$" placeholder="" required>
							</div>
							<div id="locationGroup" class="col-4">
								<div class="col-13">
									<label for="Location"><i class="fa fa-map-marker" aria-hidden="true"></i>Location</label> 
									<input type="text" class="form-control" name="location" id="Location" placeholder="">
								</div>								
								<div>
									<button id="locationAdd" type="button"
										class="btn btn-outline-success btn-sm" onclick="addLFields()"
										style="margin-top: 5px">
										<i class="fa fa-plus" aria-hidden="true"></i> Location
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<br>
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
											aria-hidden="true"></i> Plant Part</label> <select name="prepPart[0]"
											class="custom-select custom-select-md mb-3" id="PlantPart"
											onchange="enablePPO(0)">
											<option value="" selected disabled>Open this select
												menu</option>
											<option value="-1">Others (please write on the right
												side)</option>
											<c:forEach items="${plantPartsList}" var="plantPartsList">
												<option value="${plantPartsList}">${plantPartsList}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col">
										<label for="PlantPartOther[0]">Plant Part (Other)</label> <input
											type="text" class="form-control" id="PlantPartOther[0]"
											placeholder="" disabled>
									</div>
									<div class="col-1"></div>
								</div>

								<div class="form-group form-row" id="illnessGroup">
									<div class="col-4"></div>
									<div class="col-4">
										<i class="fa fa-viruses"></i> <label for="Illness"><i
											class="fa fa-thermometer" aria-hidden="true"></i> Illness</label> <input
											type="text" class="form-control" id="Illness" placeholder=""
											name="illness[0][0]"> <input type="hidden"
											name="illnessCtr[0]" value="0">
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
									<button type="button" class="btn btn-outline-success btn-block"
										id="preparationAdd" onclick="addPFields(0)">Add
										Preparation</button>
								</div>
							</div>
						</div>
					</div>
				</div>


				<br>
				<div class="card">
					<div class="card-header text-center">
						<h4 class="mt-1">Species</h4>
					</div>
					<div class="card-body">
						<div id="speciesGroup">
							<div class="border-bottom border-secondary mb-3">
								<div class="form-group form-row">
									<div class="col-1"></div>
									<div class="col-3">
										<label for="SpeciesPlantPart"><i class="fa fa-leaf"
											aria-hidden="true"></i> Species Plant Part</label> <select
											name="plantPart[0]"
											class="custom-select custom-select-md mb-3"
											id="SpeciesPlantPart" onchange="enableSPPO(0)">
											<option value="" selected disabled>Open this select
												menu</option>
											<option value="-1">Others (please write on the right
												side)</option>
											<c:forEach items="${plantPartsList}" var="plantPartsList">
												<option value="${plantPartsList}">${plantPartsList}</option>
											</c:forEach>
										</select> <input type="hidden" name="speciesCtr" value="0">
									</div>
									<div class="col-3">
										<label for="SpeciesPlantPartOther[0]">Species Plant
											Part (Other)</label> <input type="text" class="form-control"
											id="SpeciesPlantPartOther[0]" placeholder="" disabled>
									</div>
								</div>

								<div id="chemicalCompoundGroup">
									<div class="form-group form-row">
										<div class="col-2"></div>
										<div class="col-5">
											<label for="ChemicalCompound"><i class="fa fa-flask"
												aria-hidden="true"></i> Chemical Compound</label> <input type="text"
												class="form-control" id="ChemicalCompound" placeholder=""
												name="compound[0][0]">
											
											<input type="hidden" name="lengthCC0" value="0">
										</div>
										<div class="w-100"></div>
										<div class="col-2"> </div>
										<div class="col-10">
											<button id="chemicalCompoundAdd" type="button"
													class="btn btn-outline-success btn-sm"
													onclick="addCCFields(0)" style="margin-top: 5px">
													<i class="fa fa-plus" aria-hidden="true"></i> Chemical
													Compound
											</button>
										</div>
									</div>

									<div id="biologicalActivityGroup">
										<div class="form-group form-row">
											<div class="col-3"></div>
											<div class="col-4">
												<label for="BiologicalActivity">Biological Activity</label>
												<input type="text" class="form-control"
													id="BiologicalActivity" placeholder=""
													name="bioAct[0][0][0]">
											</div>
											<div class="col-4">
												<label for="CellLine">Cell Line</label> <input type="text"
													class="form-control" id="CellLine" placeholder=""
													name="cellLine[0][0][0]"> <input type="hidden"
													name="bioCellCC0" value="0"> <input type="hidden"
													name="lengthBC[0][0]" value="0">
											</div>
										</div>
										
										<div class="form-row">
											<div class="col-3"></div>
											<div class="col-4">
												<button id="biologicalActivityAdd" type="button"
													class="btn btn-outline-success btn-sm"
													onclick="addBAFields(0,0,0)" style="margin-top: -17px">
													<i class="fa fa-plus" aria-hidden="true"></i> Biological
													Activity
												</button>
											</div>
											<div class="w-100"></div>
										</div>
										
									</div>
								</div>
							</div>
							<div class="d-flex justify-content-center">
								<div class="form-row" style="width: 50%">
									<button type="button" class="btn btn-outline-success btn-block"
										id="speciesAdd" onclick="addSFields(0)">Add Species
										Part</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<br>
				<button type="button" class="btn btn-outline-light btn-lg btn-block" data-toggle="modal"
					data-target="#exampleModal">
					<h3 class="mt-2">
						<b>Submit</b>
					</h3>
				</button>
				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Adding New Plant Entry</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">Are you sure you want to proceed and add this plant?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-outline-danger"
									data-dismiss="modal">X  Go Back</button>
								<button type="submit" class="btn btn-success">&#10003 Add</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>


	<!-- INCLUDE FOOTER HTML -->
	<%@include file="_includeFooter.html"%>


	<script type="text/javascript"
		src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
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

	<!-- form functions -->
	<script>
	
		var speciestPartsList = "${speciesPartsList}";
	
		var snCtr, lCtr, pbpCtr, iCtr, baclCtr, ccCtr, sCtr;
		snCtr = lCtr = pbpCtr = iCtr = baclCtr = ccCtr = sCtr = 0;
	
		var speciesArr = [ 0 ];
		var speciesArr2 = [ [ 0 ] ]
		var compArr = [ 0 ];
		var prepArr = [ 0 ];
		
		function removeFields(pCategory, pValue) {
			var field, button, label;
			
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
					break;
			}
		}
		
		function addLFields() {
			$('#locationAdd').remove();
			lCtr++;
						
			var inputField = '<div class="col-13"><label for="Location'+ lCtr +'">Location</label><input type="text" class="form-control" name="location" id="Location'+ lCtr +'" placeholder="">';
			var buttonAdd = '<div><button id="locationAdd" type="button" class="btn btn-outline-success btn-sm" onclick="addLFields()" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Location</button>' +
							'<button id="locationDelete'+ lCtr +'" type="button" class="btn btn-outline-danger btn-sm" onclick="removeFields(0,'+ lCtr +')" style="margin-top: 5px"><i class="fa fa-minus" aria-hidden="true"></i> Delete</button></div>';
			
			$('#locationGroup').append(inputField, buttonAdd);
			console.log(inputField);
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
			var buttonAddText = "#biologicalActivityAdd" + pValue;
			speciesArr2[sVal][cVal]++;
			console.log(speciesArr2);
			if (pValue == 0) {
				$('#biologicalActivityAdd').remove();
			} else {
				$(buttonAddText).remove();
			}
	
			baclCtr++;
			
			if (pValue == 0) {
				
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
			} else {
				var newRow = '<div class="form-group form-row" id="biologicalActivityContainer'+baclCtr+'>';
				
				var inputBioAct = '<div class="col-3"> </div>'+
							  	  '<div class="col-4">'+
									  '<label for="BiologicalActivity'+ baclCtr +'">Biological Activity</label>'+
									  '<input type="text" class="form-control" id="BiologicalActivity'+ baclCtr +'" placeholder="" name="bioAct['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']">'+
									  '<button id="biologicalActivityAdd'+pValue+'" type="button" class="btn btn-outline-success btn-sm" onclick="addBAFields('+pValue+','+sVal+','+cVal+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Biological Activity</button>'+
									  '<button id="biologicalActivityDelete'+ baclCtr +'" type="button" class="btn btn-outline-danger btn-sm" onclick="removeFields(4, '+ baclCtr +')" style="margin-top: 5px"><i class="fa fa-minus" aria-hidden="true"></i> Delete</button>'+
					  		       '</div>';
					  		  
		  		var inputCellLine = '<div class="col-4">'+
											'<label for="CellLine'+ baclCtr +'">Cell Line</label>'+
											'<input type="text" class="form-control" id="CellLine'+ baclCtr +'" placeholder="" name="cellLine['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']">'+
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
										   '<button id="biologicalActivityAdd" type="button"'+
											   'class="btn btn-outline-success btn-sm"'+
											   'onclick="addBAFields('+pValue+','+sVal+','+cVal+')" style="margin-top: -5px">'+
											   '<i class="fa fa-plus" aria-hidden="true"></i> Biological'+
											   'Activity'+
										   '</button>'+
									   '</div>'+
								   '</div>';
				
				var newBioAct = newRow + inputBioAct + inputCellLine + endRow + inputButtons;
					
				var inputGroupText = "#biologicalActivityGroup" + pValue;
	
				$(inputGroupText).append(newBioAct);
			}
		}
		
		function addCCFields(pValue) {
			speciesArr[pValue]++;
			speciesArr2[pValue].push(0);
			console.log(speciesArr2);
			var buttonAddText = "#chemicalCompoundAdd" + pValue;
	
			if (pValue == 0) {
				$('#chemicalCompoundAdd').remove();
			} else {
				$(buttonAddText).remove();
			}
			ccCtr++;
			baclCtr++;
			
			/*
				<div class="w-100"></div>
				<div class="col-2"> </div>
				<div class="col-10">
					<button id="chemicalCompoundAdd" type="button"
							class="btn btn-outline-success btn-sm"
							onclick="addCCFields(0)" style="margin-top: 5px">
							<i class="fa fa-plus" aria-hidden="true"></i> Chemical
							Compound
					</button>
				</div>
				
				
				
				
				'<div class="form-group form-row" id="biologicalActivityContainer'+baclCtr+'">';
			*/
			
			if (pValue == 0) {
				var chemComp = '<div class="form-group form-row" id="chemicalCompoundContainer'+ccCtr+'">'+
								   '<div class="col-2"> </div>'+
								   '<div class="col-5">'+
									   '<label for="ChemicalCompound'+ ccCtr +'">Chemical Compound</label>'+
			    					   '<input type="text" class="form-control" id="ChemicalCompound'+ ccCtr +'" placeholder="" name="compound['+pValue+']['+speciesArr[pValue]+']">'+
									   '<input type="hidden" name="compoundCtr" value="'+ccCtr+'">'+
									   '<input type="hidden" name="lengthCC'+pValue+'" value="'+speciesArr[pValue]+'">'+
								   '</div>'+
								   '<div class="w-100"></div>'+
								   '<div class="col-2"> </div>'+
								   '<div class="col-10">'+
									   '<button id="chemicalCompoundAdd" type="button"'+
											   'class="btn btn-outline-success btn-sm"'+
											   'onclick="addCCFields('+pValue+')" style="margin-top: 5px">'+
											   '<i class="fa fa-plus" aria-hidden="true"></i> Chemical'+
											   'Compound'+
									   '</button>'+
									   '<button id="chemicalCompoundDelete'+ccCtr+'" type="button"'+
										   'class="btn btn-outline-danger btn-sm"'+
										   'onclick="removeFields(3,'+ccCtr+')" style="margin-top: 5px">'+
										   '<i class="fa fa-minus" aria-hidden="true"></i> Delete'+
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
					
				var chemCompFields = chemComp + bioAct;
				
				$('#chemicalCompoundGroup').append(chemCompFields);
			} else {
				var chemComp = '<div class="form-group form-row">'+
								   '<div class="col-2"> </div>'+
								   '<div class="col-5">'+
									   '<label for="ChemicalCompound'+ ccCtr +'">Chemical Compound</label>'+
									   '<input type="text" class="form-control" id="ChemicalCompound'+ ccCtr +'" placeholder="" name="compound['+pValue+']['+speciesArr[pValue]+']">'+
									   '<button id="chemicalCompoundAdd'+pValue+'" type="button" class="btn btn-outline-success btn-sm" onclick="addCCFields('+pValue+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Chemical Compound</button>'+
									   '<input type="hidden" name="compoundCtr" value="'+ccCtr+'">'+
									   '<input type="hidden" name="lengthCC'+pValue+'" value="'+speciesArr[pValue]+'">'+
								   '</div>'+
							   '</div>';
							  
				var bioAct = '<div id="biologicalActivityGroup'+ ccCtr+'">'+
							 	'<div class="form-group form-row">'+
									 '<div class="col-3"> </div>'+
									 '<div class="col-4">'+
										 '<label for="BiologicalActivity'+ baclCtr +'">Biological Activity</label>'+
					  					 '<input type="text" class="form-control" id="BiologicalActivity'+ baclCtr +'" placeholder="" name="bioAct['+pValue+']['+speciesArr[pValue]+'][0]">'+
					  					 '<button id="biologicalActivityAdd'+ baclCtr +'" type="button" class="btn btn-outline-success btn-sm" onclick="addBAFields('+ccCtr+','+pValue+','+speciesArr[pValue]+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Biological Activity</button>'+
					  				 '</div>'+
									 '<div class="col-4">'+
										 '<label for="CellLine'+ baclCtr +'">Cell Line</label>'+
					  						 '<input type="text" class="form-control" id="CellLine'+ baclCtr +'" placeholder="" name="cellLine['+pValue+']['+speciesArr[pValue]+'][0]">'+
					  						 '<input type="hidden" name="bioCellCC0" value="0">'+ // NOT YET USED(?)
					  						 '<input type="hidden" name="lengthBC['+pValue+']['+speciesArr[pValue]+']" value="'+speciesArr2[pValue][speciesArr[pValue]]+'">'+
									 '</div>'+
								 '</div>'+
							 '</div>';							
							 console.log(speciesArr[pValue]);
								
				var chemCompFields = chemComp + bioAct;
	
				var inputGroupText = "#chemicalCompoundGroup" + pValue;
	
				$(inputGroupText).append(chemCompFields);
			}
	
		}
		
		function addSFields(pValue) {
			speciesArr.push(0);
			speciesArr2.push([ 0 ]);
			console.log(speciesArr2);
			var buttonAddText = "#speciesAdd";
	
			if (pValue == 0) {
				$('#speciesAdd').remove();
			} else {
				$(buttonAddText).remove();
			}
	
			sCtr++;
			ccCtr++;
			baclCtr++;

			
			var speciesPPCluster = '<div class="border-bottom border-secondary mb-3"><div class="form-group form-row">'+
									   '<div class="col-1"></div>'+
									   '<div class="col-3">'+
										   '<label for="SpeciesPlantPart'+sCtr+'">Species Plant Part</label>'+
										   '<select name="plantPart['+sCtr+']" class="custom-select custom-select-md mb-3" id="SpeciesPlantPart'+sCtr+'" onchange="enableSPPO('+sCtr+')">'+
										  	   '<option value="" selected disabled>Open this select menu</option>'+
										  	   '<option value="-1">Others (please write on the right side)</option>'+
										  	   '<c:forEach items="${plantPartsList}" var="plantPartsList">'+
												   '<option value="${plantPartsList}">${plantPartsList}</option>'+
											   '</c:forEach>'+
										   '</select>'+
										   '<input type="hidden" name="speciesCtr" value="'+sCtr+'">'+
									   '</div>'+
									   '<div class="col-3">'+
											   '<label for="SpeciesPlantPartOther['+sCtr+']">Species Plant Part (Other)</label>'+
											   '<input type="text" class="form-control" id="SpeciesPlantPartOther['+sCtr+']" placeholder="" disabled>'+
									   '</div>'+
								   '</div>';
			
			var chemcompCluster = '<div id="chemicalCompoundGroup'+ sCtr+'">'+
									  '<div class="form-group form-row">'+
										  '<div class="col-2"> </div>'+
										  '<div class="col-5">'+
											  '<label for="ChemicalCompound'+ ccCtr +'">Chemical Compound</label>'+
											  '<input type="text" class="form-control" id="ChemicalCompound'+ ccCtr +'" placeholder="" name="compound['+sCtr+']['+speciesArr[pValue]+']">'+
											  '<button id="chemicalCompoundAdd'+sCtr+'" type="button" class="btn btn-outline-success btn-sm" onclick="addCCFields('+sCtr+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Chemical Compound</button>'+
											  '<input type="hidden" name="compoundCtr" value="'+ccCtr+'">'+
											  '<input type="hidden" name="lengthCC'+sCtr+'" value="'+speciesArr[pValue]+'">'+
										  '</div>'+
									  '</div>'+
								
									  '<div id="biologicalActivityGroup'+ ccCtr+'">'+
										  '<div class="form-group form-row">'+
											  '<div class="col-3"> </div>'+
											  '<div class="col-4">'+
												  '<label for="BiologicalActivity'+ baclCtr +'">Biological Activity</label>'+
							  					  '<input type="text" class="form-control" id="BiologicalActivity'+ baclCtr +'" placeholder="" name="bioAct['+sCtr+']['+speciesArr[pValue]+'][0]">'+
							  					  '<button id="biologicalActivityAdd'+ccCtr+'" type="button" class="btn btn-outline-success btn-sm" onclick="addBAFields('+ccCtr+','+sCtr+','+speciesArr[pValue]+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Biological Activity</button>'+
							  				  '</div>'+
											  '<div class="col-4">'+
												  '<label for="CellLine'+ baclCtr +'">Cell Line</label>'+
							  						  '<input type="text" class="form-control" id="CellLine'+ baclCtr +'" placeholder="" name="cellLine['+sCtr+']['+speciesArr[pValue]+'][0]">'+
							  						  '<input type="hidden" name="bioCellCC0" value="0">'+ //NOT YET USED (?)
							  						  '<input type="hidden" name="lengthBC['+sCtr+']['+speciesArr[pValue]+']" value="'+speciesArr2[sCtr][speciesArr[pValue]]+'">'+
											  '</div>'+
										  '</div>'+
									  '</div>'+
								  '</div>'+
								'</div>'; 
									
			var buttonAddSpecies =  '<div class="d-flex justify-content-center">'+
										'<div class="form-row" style="width: 50%">'+
											'<button type="button" class="btn btn-outline-success btn-block" id="speciesAdd" onclick="addSFields(0)">Add Species Part</button>'+
										'</div>'+
									'</div>';

			var speciesComponent = speciesPPCluster + chemcompCluster + buttonAddSpecies +"</div>";
			
			$('#speciesGroup').append(speciesComponent);
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