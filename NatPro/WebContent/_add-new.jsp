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
		<div class="container" style="padding-bottom: 30px">
			<h1 class="display-4">Add</h1>
			<p class="lead">Manually add a plant here</p>
		</div>
		
		<div class="container-fluid" style="text-align: left">
			<form action="AddPlantServlet" method="POST">
				<h4 style="padding-bottom: 20px; text-align:center">General</h4>
				<div class="form-group form-row">
					<div class="col-3"> </div>
					<div class="col-6">
						<label for="CommonPlantName">Common Plant Name</label>
    					<input type="text" id="CommonPlantName" class="form-control" placeholder="">
					</div>
				</div>
				<div class="form-group form-row">
					<div class="col-6">
						<label for="Genus">Genus</label>
    					<input type="text" class="form-control" id="Genus" placeholder="">
					</div>
					<div class="col-6">
						<label for="Family">Family</label>
    					<input type="text" class="form-control" id="Family" placeholder="">
					</div>
				</div>
				
				<div class="form-group form-row">
					<div class="col-6">
						<label for="ScientificName">Scientific Name</label>
    					<input type="text" class="form-control" id="ScientificName" placeholder="">
					</div>
					<div id="locationGroup" class="col-6">
						<div class="col-13">
							<label for="Location">Location</label>
	    					<input type="text" class="form-control" id="Location" placeholder="">
						</div>
						<div>
							<button id="locationAdd" type="button" class="btn btn-primary btn-sm" onclick="addLFields()" style="margin-top:5px">Location</button>
						</div>
					</div>
				</div>
				
				
				<h4 style="padding-bottom: 20px; text-align:center">Preparation</h4>
				<div id="preparationGroup">
					<div class="form-group form-row">
						<div class="col">
							<label for="Preparation">Preparation</label>
	    					<input type="text" class="form-control" id="Preparation" placeholder="" name="preparation[0]">
	    					<input type="hidden" name="prepCtr" value="0">
						</div>
						<div class="col">
							<label for="PlantPart">Plant Part</label>
							<select class="custom-select custom-select-md mb-3" id="PlantPart">
							  	<option selected disabled>Open this select menu</option>
							  	<option value="-1">Others (please write on the right side)</option>
							  	<c:forEach items="${plantPartsList}" var="plantPartsList">
									<option value="${plantPartsList}">${plantPartsList}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col">
							<fieldset disabled>
								<label for="PlantPartOther">Plant Part (Other)</label>
		    					<input type="text" class="form-control" id="Preparation" placeholder="">
							</fieldset>
						</div>
					</div>
				
					<div class="form-group form-row" id="illnessGroup">
						<div class="col-3"> </div>
						<div class="col-6">
								<label for="Illness">Illness</label>
		    					<input type="text" class="form-control" id="Illness" placeholder="" name="illness[0][0]">
		    					<input type="hidden" name="illnessCtr[0]" value="0">
						</div>
						<div class="col-3"> </div>
						<div class="col-3"> </div>
						<div class="col-6">
							<button id="illnessAdd" type="button" class="btn btn-primary btn-sm" style="margin-top:5px" onclick="addIFields(0)">Illness</button>
						</div>
						<div class="col-3"> </div>
					</div>
					
					<div class="form-group form-row">
						<div class="col-12" style="text-align: center">
							<button id="preparationAdd" type="button" class="btn btn-primary btn-lg" style="margin-top:10px" onclick="addPFields(0)">Preparation</button>
						</div>
					</div>
				</div>
				
				
				<h4 style="padding-bottom: 20px; text-align:center">Species</h4>
				<div class="form-group form-row">
					<div class="col-4">
						<label for="SpeciesPlantPart">Species Plant Part</label>
						<select class="custom-select custom-select-md mb-3" id="SpeciesPlantPart">
						  	<option selected disabled>Open this select menu</option>
						  	<option value="-1">Others (please write on the right side)</option>
						  	<c:forEach items="${plantPartsList}" var="plantPartsList">
								<option value="${plantPartsList}">${plantPartsList}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<fieldset disabled>
							<label for="SpeciesPlantPartOther">Other Plant Part</label>
    						<input type="text" class="form-control" id="SpeciesPlantPartOther" placeholder="">
						</fieldset>
					</div>
				</div>
				
				<div class="form-group form-row">
					<div class="col-2"> </div>
					<div class="col-6">
						<label for="ChemicalCompound">Chemical Compound</label>
    					<input type="text" class="form-control" id="ChemicalCompound" placeholder="">
					</div>
					<div class="w-100"></div>
					<div class="col-2"> </div>
					<div class="col justify-content-center">
						<button type="button" class="btn btn-primary btn-sm" style="margin-top:5px">Chemical Compound</button>
					</div>
				</div>
				
				<div class="form-group form-row">
					<div class="col-4"> </div>
					<div class="col-4">
						<label for="BiologicalActivity">Biological Activity</label>
   						<input type="text" class="form-control" id="BiologicalActivity" placeholder="">
					</div>
					<div class="col-4">
						<label for="CellLine">Cell Line</label>
   						<input type="text" class="form-control" id="CellLine" placeholder="">
					</div>
					<div class="w-100"></div>
					<div class="col-4"></div>
					<div class="col justify-content-center">
						<button type="button" class="btn btn-primary btn-sm" style="margin-top:5px">Biological Activity</button>
					</div>
					
					<div class="col-12" style="text-align: center">
						<button type="button" class="btn btn-primary btn-lg" style="margin-top:10px; margin-bottom: 25px">Species Part</button>
					</div>
				</div>
				
	
				
				<button type="button" class="btn btn-primary btn-lg btn-block">Submit</button>
			</form>
		</div>
	</div>
	
    
    <!-- INCLUDE FOOTER HTML -->
    <%@include file="_includeFooter.html" %>
	
    
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
	
	<!-- form functions -->
	<script>
	
		var speciestPartsList = "${speciesPartsList}";
	
		var snCtr, lCtr, pbpCtr, iCtr, baclCtr, ccCtr, sCtr;
		snCtr = lCtr = pbpCtr = iCtr = baclCtr = ccCtr = sCtr = 0;
	
		var speciesArr = [ 0 ];
		var speciesArr2 = [ [ 0 ] ]
		var compArr = [ 0 ];
		var prepArr = [ 0 ];
		
		function addLFields() {
			$('#locationAdd').remove();
			lCtr++;
						
			var inputField = '<div class="col-13"><label for="Location'+ lCtr +'">Location</label><input type="text" class="form-control" id="Location'+ lCtr +'" placeholder="">';
			var buttonAdd = '<div><button id="locationAdd" type="button" class="btn btn-primary btn-sm" onclick="addLFields()" style="margin-top:5px">Location</button></div>';
			
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
						
			var inputField = '<div class="col-3"> </div><div class="col-6"><label for="Illness'+ iCtr +'">Illness</label><input type="text" class="form-control" id="Illness'+ iCtr +'" placeholder="" name="illness['+pValue+']['+prepArr[pValue]+']"><input type="hidden" name="illnessCtr['+pValue+']" value="'+prepArr[pValue]+'"></div><div class="col-3"> </div>';
			
			if (pValue == 0) {
				var buttonAdd = '<div class="col-3"> </div><div class="col-6"><button id="illnessAdd" type="button" class="btn btn-primary btn-sm" style="margin-top:5px" onclick="addIFields('+pValue+')">Illness</button></div><div class="col-3"> </div>';
			} else {
				var buttonAdd = '<div class="col-3"> </div><div class="col-6"><button id="illnessAdd'+pValue+'" type="button" class="btn btn-primary btn-sm" style="margin-top:5px" onclick="addIFields('+pValue+')">Illness</button></div><div class="col-3"> </div>';
			}
	
			var groupAddText = "#illnessGroup" + pValue;
	
			if (pValue == 0) {
				console.log(inputField);
				$('#illnessGroup').append(inputField, buttonAdd);
			} else {
				console.log(inputField);
				$(groupAddText).append(inputField, buttonAdd);
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
			
			/*
				<div class="form-group form-row">
					<div class="col">
						<label for="Preparation">Preparation</label>
	   					<input type="text" class="form-control" id="Preparation" placeholder="" name="preparation[0]">
	   					<input type="hidden" name="prepCtr" value="0">
					</div>
					<div class="col">
						<label for="PlantPart">Plant Part</label>
						<select class="custom-select custom-select-md mb-3" id="PlantPart">
						  	<option selected disabled>Open this select menu</option>
						  	<option value="-1">Others (please write on the right side)</option>
						  	<c:forEach items="${plantPartsList}" var="plantPartsList">
								<option value="${plantPartsList}">${plantPartsList}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col">
						<fieldset disabled>
							<label for="PlantPartOther">Plant Part (Other)</label>
	    					<input type="text" class="form-control" id="Preparation" placeholder="">
						</fieldset>
					</div>
				</div>
			
			*/
			
			var inputPreparation
			var inputUtilizedPlant = 
			
			var inputUtilizedPlant = '<div class="form-group col-md-3">'
				+'<label for="inputplantpart'+ pbpCtr+'">Plant Part</label>'
				+'<select name="prepPart['+(pValue + 1)+']" class="form-control">'
				+'<option value="" selected disabled>Choose utilized plant part</option>'
				+'<option value="-1">Others (Please write on the right side)</option>'
				+'<c:forEach items="${plantPartsList}" var="plantPartsList">'
				+'<option value="${plantPartsList}">${plantPartsList}</option>'
				+'</c:forEach></select></div>';
				
			
			var inputIllness = '<div class="form-group form-row" id="illnessGroup'+ pbpCtr +'">'+	
							   '<div class="col-3"> </div><div class="col-6"><label for="Illness'+ iCtr +'">Illness</label><input type="text" class="form-control" id="Illness'+ iCtr +'" placeholder="" name="illness['+(pValue + 1)+'][0]"><input type="hidden" name="illnessCtr['+(pValue + 1)+']" value="'+prepArr[pValue]+'"></div><div class="col-3"> </div>' +
							   '<div class="col-3"> </div><div class="col-6"><button id="illnessAdd'+pbpCtr+'" type="button" class="btn btn-primary btn-sm" style="margin-top:5px" onclick="addIFields('+pbpCtr+')">Illness</button></div><div class="col-3"> </div>'
							   '</div>';	
							   
//		   var inputIllness = '<div class="form-group col-md-3" id="illnessGroup'+ pbpCtr +'">'
//			  +'<label for="inputill'+ pbpCtr+'">Illness</label>'
//			  +'<input type="text" class="form-control" id="illness'+iCtr+'" placeholder="Illness" name="illness['+(pValue + 1)+'][0]">'
//			  +'<input type="hidden" name="illnessCtr['+(pValue + 1)+']" value="'+prepArr[pValue]+'">'
//			  +'<button type="button" class="btn btn-outline-success btn-sm" id="illnessAdd'+pbpCtr+'" onclick="addIFields('+pbpCtr+')">+ Illness</button></div>';
							   
			var buttonAdd = '<div class="form-group form-row"><div class="col-12" style="text-align: center"><button id="preparationAdd'+pbpCtr+'" type="button" class="btn btn-primary btn-lg" style="margin-top:10px" onclick="addPFields('+pbpCtr+')">Preparation</button></div></div>';
			
			
			
			
			var newRow = '<div class="form-row">';
			var inputPreparation = '<div class="form-group col-md-6">'
								  +'<label for="inputprep'+ pbpCtr+'">Preparation</label>'
								  +'<input type="text" class="form-control" id="preparation'+ pbpCtr+'" placeholder="Preparation" name="preparation['+ (pValue + 1) +']">'
								  +'<input type="hidden" name="prepCtr" value="'+(pValue+1)+'"></div>';
			 
			
			var newPrep = inputPreparation + inputUtilizedPlant + inputIllness + buttonAdd;
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
				
				var newRow = '<div class="form-row" style="width: 50%; margin-left: 20%;">';
				
				var inputBioAct = '<div class="form-group col-md-6">'
								 +'<label for="inputbioact'+ baclCtr +'">Biological Activity</label>'
								 +'<input type="text" class="form-control" id="biologicalActivity'+ baclCtr +'" placeholder="Biological Activity" name="bioAct['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']">'
								 +'<button type="button" class="btn btn-outline-success btn-sm"	id="biologicalActivityAdd" onclick="addBAFields('+pValue+','+sVal+','+cVal+')">+ Biological Activity</button></div>';
				
				var inputCellLine = '<div class="form-group col-md-6">'
								   +'<label for="inputcellline'+ baclCtr +'">Cell Line</label>'
								   +'<input type="text" class="form-control" id="cellLine'+ baclCtr +'" placeholder="Cell Line" name="cellLine['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']"></div>';
				
				var hiddenLengthBC = '<input type="hidden" name="lengthBC['+sVal+']['+cVal+']" value="'+speciesArr2[sVal][cVal]+'">';	 
				
				var endRow = '</div>';
				
				var newBioAct = newRow + inputBioAct + inputCellLine + hiddenLengthBC + endRow;
	
				$('#biologicalActivityGroup').append(newBioAct);
			} else {
				var newRow = '<div class="form-row" style="width: 50%; margin-left: 20%;">';
				
				var inputBioAct = '<div class="form-group col-md-6">'
								 +'<label for="inputbioact'+ baclCtr +'">Biological Activity</label>'
								 +'<input type="text" class="form-control" id="biologicalActivity'+ baclCtr +'" placeholder="Biological Activity" name="bioAct['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']">'
								 +'<button type="button" class="btn btn-outline-success btn-sm"	id="biologicalActivityAdd'+pValue+'" onclick="addBAFields('+pValue+','+sVal+','+cVal+')">+ Biological Activity</button></div>';
				
				var inputCellLine = '<div class="form-group col-md-6">'
								   +'<label for="inputcellline'+ baclCtr +'">Cell Line</label>'
								   +'<input type="text" class="form-control" id="cellLine'+ baclCtr +'" placeholder="Cell Line" name="cellLine['+sVal+']['+cVal+']['+speciesArr2[sVal][cVal]+']"></div>';
				
				var hiddenLengthBC = '<input type="hidden" name="lengthBC['+sVal+']['+cVal+']" value="'+speciesArr2[sVal][cVal]+'">';	 
				
				var endRow = '</div>';
				
				var newBioAct = newRow + inputBioAct + inputCellLine + hiddenLengthBC + endRow;
	
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
			
			if (pValue == 0) {
				var newRowChemComp = '<div class="form-row" style="width: 50%; margin-left: 10%;">';
				var newRowBioAct = '<div id="biologicalActivityGroup'+ ccCtr+'">'
								  +'<div class="form-row" style="width: 50%; margin-left: 20%;">';				
				var endRow = '</div>';
				
				
				var inputChemComp = '<div class="form-group col-md-6">'
								   +'<label for="inputcomplant'+ ccCtr +'">Chemical Compound</label>'
								   +'<input type="text" class="form-control" id="chemicalCompound'+ ccCtr +'" placeholder="Chemical Compound" name="compound['+pValue+']['+speciesArr[pValue]+']">'
								   +'<button type="button" class="btn btn-outline-success btn-sm" id="chemicalCompoundAdd" onclick="addCCFields('+pValue+')">+ Chemical Compound</button></div>'
								   + "<input type='hidden' name='compoundCtr' value='"+ccCtr+"'>"
								   + "<input type='hidden' name='lengthCC"+pValue+"' value='"+speciesArr[pValue]+"'>";;
	
				var newChemComp = newRowChemComp + inputChemComp + endRow;
	
				
				var inputBioAct = '<div class="form-group col-md-6">'
					 +'<label for="inputbioact'+ baclCtr +'">Biological Activity</label>'
					 +'<input type="text" class="form-control" id="biologicalActivity'+ baclCtr +'" placeholder="Biological Activity" name="bioAct['+pValue+']['+speciesArr[pValue]+'][0]">'
					 +'<button type="button" class="btn btn-outline-success btn-sm"	id="biologicalActivityAdd" onclick="addBAFields('+ccCtr+','+pValue+','+speciesArr[pValue]+')">+ Biological Activity</button></div>';
	
				var inputCellLine = '<div class="form-group col-md-6">'
								   +'<label for="inputcellline'+ baclCtr +'">Cell Line</label>'
								   +'<input type="text" class="form-control" id="cellLine'+ baclCtr +'" placeholder="Cell Line" name="cellLine['+pValue+']['+speciesArr[pValue]+'][0]"></div>';
				
				var hiddenLengthBC = '<input type="hidden" name="lengthBC['+pValue+']['+speciesArr[pValue]+']" value="'+speciesArr2[pValue][speciesArr[pValue]]+'">';	 
								
				var newBioAct = newRowBioAct + inputBioAct + inputCellLine + hiddenLengthBC + endRow + endRow;
				
				$('#chemicalCompoundGroup').append(newChemComp, newBioAct);
			} else {
				var newRowChemComp = '<div class="form-row" style="width: 50%; margin-left: 10%;">';
				var newRowBioAct = '<div id="biologicalActivityGroup'+ ccCtr+'">'
								  +'<div class="form-row" style="width: 50%; margin-left: 20%;">';				
				var endRow = '</div>';
				
				
				var inputChemComp = '<div class="form-group col-md-6">'
								   +'<label for="inputcomplant'+ ccCtr +'">Chemical Compound</label>'
								   +'<input type="text" class="form-control" id="chemicalCompound'+ ccCtr +'" placeholder="Chemical Compound" name="compound['+pValue+']['+speciesArr[pValue]+']">'
								   +'<button type="button" class="btn btn-outline-success btn-sm" id="chemicalCompoundAdd" onclick="addCCFields('+pValue+')">+ Chemical Compound</button></div>'
								   + "<input type='hidden' name='compoundCtr' value='"+ccCtr+"'>"
								   + "<input type='hidden' name='lengthCC"+pValue+"' value='"+speciesArr[pValue]+"'>";;
	
				var newChemComp = newRowChemComp + inputChemComp + endRow;
	
				
				var inputBioAct = '<div class="form-group col-md-6">'
					 +'<label for="inputbioact'+ baclCtr +'">Biological Activity</label>'
					 +'<input type="text" class="form-control" id="biologicalActivity'+ baclCtr +'" placeholder="Biological Activity" name="bioAct['+pValue+']['+speciesArr[pValue]+'][0]">'
					 +'<button type="button" class="btn btn-outline-success btn-sm"	id="biologicalActivityAdd'+ccCtr+'" onclick="addBAFields('+ccCtr+','+pValue+','+speciesArr[pValue]+')">+ Biological Activity</button></div>';
	
				var inputCellLine = '<div class="form-group col-md-6">'
								   +'<label for="inputcellline'+ baclCtr +'">Cell Line</label>'
								   +'<input type="text" class="form-control" id="cellLine'+ baclCtr +'" placeholder="Cell Line" name="cellLine['+pValue+']['+speciesArr[pValue]+'][0]"></div>';
				
				var hiddenLengthBC = '<input type="hidden" name="lengthBC['+pValue+']['+speciesArr[pValue]+']" value="'+speciesArr2[pValue][speciesArr[pValue]]+'">';	 
								
				var newBioAct = newRowBioAct + inputBioAct + inputCellLine + hiddenLengthBC + endRow + endRow;
	
				var inputGroupText = "#chemicalCompoundGroup" + pValue;
	
				$(inputGroupText).append(newChemComp, newBioAct);
			}
	
		}
		
		function addSFields(pValue) {
			speciesArr.push(0);
			speciesArr2.push([ 0 ]);
			console.log(speciesArr2);
			var buttonAddText = "#speciesAdd" + pValue;
	
			if (pValue == 0) {
				$('#speciesAdd').remove();
			} else {
				$(buttonAddText).remove();
			}
	
			sCtr++;
			ccCtr++;
			baclCtr++;
		
			var newRowSpecies = '<div class="form-row" style="width: 50%">';
			var newRowChemComp = '<div id="chemicalCompoundGroup'+ sCtr+'">'
			 				  +'<div class="form-row" style="width: 50%; margin-left: 10%;">';
			var newRowBioAct = '<div id="biologicalActivityGroup'+ ccCtr+'">'
							  +'<div class="form-row" style="width: 50%; margin-left: 20%;">';				
			var endRow = '</div>';
			
			var inputSpecies = '<div class="input-group mb-3">'
				   			   +'<div class="input-group-prepend">'
							   +'<select class="custom-select" id="speciesPart'+sCtr+'"	onchange="speciesPartOtherEnable('+sCtr+')" name="plantPart['+sCtr+']">'
							   +'<option value="" selected disabled>Choose plant part</option>'
							   +'<option value="-1">Others (Please write on the right side)</option>'
							   +'<c:forEach items="${plantPartsList}" var="plantPartsList">'
							   +'<option value="${plantPartsList}">${plantPartsList}</option></c:forEach></select>'
							   +"<input type='hidden' name='speciesCtr' value='0'></div>"
							   +'<input type="text" class="form-control" id="speciesPartOther'+sCtr+'" placeholder="Other Plant Part" disabled required	name="plantPartOther"></div>'
			
			var newSpecies = newRowSpecies + inputSpecies + endRow;				   
							   
			var inputChemComp = '<div class="form-group col-md-6">'
							   +'<label for="inputcomplant'+ ccCtr +'">Chemical Compound</label>'
							   +'<input type="text" class="form-control" id="chemicalCompound'+ ccCtr +'" placeholder="Chemical Compound" name="compound['+sCtr+']['+speciesArr[pValue]+']">'
							   +'<button type="button" class="btn btn-outline-success btn-sm" id="chemicalCompoundAdd'+sCtr+'" onclick="addCCFields('+sCtr+')">+ Chemical Compound</button></div>'
							   + "<input type='hidden' name='compoundCtr' value='"+ccCtr+"'>"
							   + "<input type='hidden' name='lengthCC"+sCtr+"' value='"+speciesArr[pValue]+"'>";
	
			var newChemComp = newRowChemComp + inputChemComp + endRow + endRow;
	
			
			var inputBioAct = '<div class="form-group col-md-6">'
				 +'<label for="inputbioact'+ baclCtr +'">Biological Activity</label>'
				 +'<input type="text" class="form-control" id="biologicalActivity'+ baclCtr +'" placeholder="Biological Activity" name="bioAct['+sCtr+']['+speciesArr[pValue]+'][0]">'
				 +'<button type="button" class="btn btn-outline-success btn-sm"	id="biologicalActivityAdd'+ccCtr+'" onclick="addBAFields('+ccCtr+','+sCtr+','+speciesArr[pValue]+')">+ Biological Activity</button></div>';
	
			var inputCellLine = '<div class="form-group col-md-6">'
							   +'<label for="inputcellline'+ baclCtr +'">Cell Line</label>'
							   +'<input type="text" class="form-control" id="cellLine'+ baclCtr +'" placeholder="Cell Line" name="cellLine['+sCtr+']['+speciesArr[pValue]+'][0]"></div>';
			
			var hiddenLengthBC = '<input type="hidden" name="lengthBC['+sCtr+']['+speciesArr[pValue]+']" value="'+speciesArr2[sCtr][speciesArr[pValue]]+'">';	 
			
			var newBioAct = newRowBioAct + inputBioAct + inputCellLine + hiddenLengthBC + endRow + endRow;
	
			var newSpeciesBtn ='<button type="button" class="btn btn-outline-success btn-block" id="speciesAdd" onclick="addSFields('+sCtr+')">Add Species	Part</button>';
			
			$('#speciesGroup').append(newSpecies, newChemComp, newBioAct, newSpeciesBtn);
		}
	
	</script>
    
  </body>
</html>