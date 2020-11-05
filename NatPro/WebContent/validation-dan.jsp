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
					<th>Compound</th>
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
										data-toggle="modal" data-target="#entryModal${modalId}"
										onclick="getPlantEntity('${Validations.getPdfFileName()}',${modalId})">${SynonymsList}</button></td>
								<!-- data-backdrop='static' data-keyboard='false' -->
								<td><button type="button" class="btn btn-warning btn-sm"
										data-toggle="modal" data-target="" data-backdrop='static'
										data-keyboard='false'>Compounds</button></td>
							</tr>

							<!-- Modal -->
							<div class="modal fade" id="entryModal${modalId}" tabindex="-1"
								aria-labelledby="entryModalLabel${modalId}" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="entryModalLabel${modalId}">Validating
												Extracted Plant Entry</h5>
										</div>
										<div class="modal-body">
											<div class="card">
												<div class="card-header text-center">
													<h4 class="mt-1">General</h4>
												</div>
												<div class="card-body">
													<div class="form-group form-row">
														<div class="col-4"></div>
														<div class="col-4">
															<label for="CommonPlantName"> <i
																class="fa fa-address-card" aria-hidden="true"></i>
																Common Plant Name
															</label> <input type="text" id="CommonPlantName${modalId}"
																name="commonPlantName" class="form-control"
																placeholder="" required>
														</div>
													</div>
													<div class="form-group form-row">
														<div class="col-2"></div>
														<div class="col-4">
															<label for="Genus"><i class="fa fa-group"
																aria-hidden="true"></i> Genus</label> <input type="text"
																class="form-control" name="genus" id="Genus${modalId}"
																placeholder="">
														</div>
														<div class="col-4">
															<label for="Family"><i class="fa fa-group"
																aria-hidden="true"></i> Family</label> <input type="text"
																class="form-control" name="family" id="Family${modalId}"
																placeholder="">
														</div>
													</div>

													<div class="form-group form-row">
														<div class="col-2"></div>
														<div class="col-4">
															<label for="ScientificName"><i
																class="fa fa-drivers-license" aria-hidden="true"></i>
																Scientific Name</label> <input type="text" class="form-control"
																name="scientificName" id="ScientificName${modalId}"
																pattern="^([A-Z][a-z]+) +([a-z-]+).*$" placeholder=""
																required>
														</div>
														<div id="locationGroup" class="col-4">
															<div class="col-13">
																<label for="Location"><i
																	class="fa fa-map-marker" aria-hidden="true"></i>
																	Location</label>
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
											</div>
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
																		class="form-control" id="Preparation${modalId}"
																		placeholder="" name="preparation[0]"> <input
																		type="hidden" name="prepCtr" value="0">
																</div>
																<div class="col">
																	<label for="PlantPart"><i class="fa fa-leaf"
																		aria-hidden="true"></i> Plant Part</label> <select
																		name="prepPart[0]"
																		class="custom-select custom-select-md mb-3"
																		id="PlantPart${modalId}" onchange="enablePPO(0)">
																		<option value="" selected disabled>Open this
																			select menu</option>
																		<option value="-1">Others (please write on
																			the right side)</option>
																		<c:forEach items="${plantPartsList}"
																			var="plantPartsList">
																			<option value="${plantPartsList}">${plantPartsList}</option>
																		</c:forEach>
																	</select>
																</div>
																<div class="col">
																	<label for="PlantPartOther[0]">Plant Part
																		(Other)</label> <input type="text" class="form-control"
																		id="PlantPartOther[0]${modalId}" placeholder=""
																		disabled>
																</div>
																<div class="col-1"></div>
															</div>

															<div class="form-group form-row" id="illnessGroup">
																<div class="col-4"></div>
																<div class="col-4">
																	<i class="fa fa-viruses"></i> <label for="Illness"><i
																		class="fa fa-thermometer" aria-hidden="true"></i>
																		Illness</label> <input type="text" class="form-control"
																		id="Illness${modalId}" placeholder=""
																		name="illness[0][0]"> <input type="hidden"
																		name="illnessCtr[0]" value="0">
																	<button id="illnessAdd${modalId}" type="button"
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
																	id="preparationAdd${modalId}" onclick="addPFields(0)">Add
																	Preparation</button>
															</div>
														</div>
													</div>
												</div>
											</div>
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
																	<label for="SpeciesPlantPart"><i
																		class="fa fa-leaf" aria-hidden="true"></i> Species
																		Plant Part</label> <select name="plantPart[0]"
																		class="custom-select custom-select-md mb-3"
																		id="SpeciesPlantPart${modalId}"
																		onchange="enableSPPO(0)">
																		<option value="" selected disabled>Open this
																			select menu</option>
																		<option value="-1">Others (please write on
																			the right side)</option>
																		<c:forEach items="${plantPartsList}"
																			var="plantPartsList">
																			<option value="${plantPartsList}">${plantPartsList}</option>
																		</c:forEach>
																	</select> <input type="hidden" name="speciesCtr" value="0">
																</div>
																<div class="col-3">
																	<label for="SpeciesPlantPartOther[0]">Species
																		Plant Part (Other)</label> <input type="text"
																		class="form-control"
																		id="SpeciesPlantPartOther[0]${modalId}" placeholder=""
																		disabled>
																</div>
															</div>

															<div id="chemicalCompoundGroup">
																<div class="form-group form-row">
																	<div class="col-2"></div>
																	<div class="col-5">
																		<label for="ChemicalCompound"><i
																			class="fa fa-flask" aria-hidden="true"></i> Chemical
																			Compound</label> <input type="text" class="form-control"
																			id="ChemicalCompound${modalId}" placeholder=""
																			name="compound[0][0]">
																		<button id="chemicalCompoundAdd${modalId}"
																			type="button" class="btn btn-outline-success btn-sm"
																			onclick="addCCFields(0)" style="margin-top: 5px">
																			<i class="fa fa-plus" aria-hidden="true"></i>
																			Chemical Compound
																		</button>
																		<input type="hidden" name="lengthCC0" value="0">
																	</div>
																</div>

																<div id="biologicalActivityGroup">
																	<div class="form-group form-row">
																		<div class="col-3"></div>
																		<div class="col-4">
																			<label for="BiologicalActivity">Biological
																				Activity</label> <input type="text" class="form-control"
																				id="BiologicalActivity${modalId}" placeholder=""
																				name="bioAct[0][0][0]">
																			<button id="biologicalActivityAdd${modalId}"
																				type="button" class="btn btn-outline-success btn-sm"
																				onclick="addBAFields(0,0,0)" style="margin-top: 5px">
																				<i class="fa fa-plus" aria-hidden="true"></i>
																				Biological Activity
																			</button>
																		</div>
																		<div class="col-4">
																			<label for="CellLine">Cell Line</label> <input
																				type="text" class="form-control"
																				id="CellLine${modalId}" placeholder=""
																				name="cellLine[0][0][0]"> <input
																				type="hidden" name="bioCellCC0" value="0"> <input
																				type="hidden" name="lengthBC[0][0]" value="0">
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="d-flex justify-content-center">
															<div class="form-row" style="width: 50%">
																<button type="button"
																	class="btn btn-outline-success btn-block"
																	id="speciesAdd${modalId}" onclick="addSFields(0)">Add
																	Species Part</button>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-outline-danger"
												data-toggle="modal" data-target="#cancelModal">Cancel</button>
											<button type="button" class="btn btn-danger"
												data-toggle="modal" data-target="#rejectModal">X
												Reject Entry</button>
											<button type="submit" class="btn btn-success"
												data-toggle="modal" data-target="#confirmModal">&#10003
												Approve Entry</button>
										</div>
									</div>
								</div>
							</div>
							<c:set var="modalId" value="${modalId + 1}" scope="page" />
						</c:forEach>
					</c:if>
					<c:set var="docunum" value="${docunum + 1}" scope="page" />
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- Modal -->
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
						<iframe id="documentIframe1" class="embed-responsive-item" src=""></iframe>
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
					<button type="button" class="btn btn-success" data-dismiss="modal">Add</button>
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
	<script type="text/javascript">
	    function viewDocument(docu) {
	    	var entryDocument = "\\NatPro\\Documents\\UploadedDocuments\\"+docu;
	    	var modalIframe = document.getElementById('documentIframe1');
	    	
	    	if (entryDocument != null) {
	    		console.log(entryDocument);
	    		modalIframe.src = entryDocument;
	    	} else {
	    		modalIframe.src = 'https://www.youtube.com/embed/ZKYEQXN_nk0';
	    	}
	    }
	</script>
	 
	<script type="text/javascript">
		function getPlantEntity(fileName, id){
			console.log(fileName);
			console.log(id);
	 		$.ajax({
				type : "GET",
				url : 'GetPlantEntity',
				dataType: 'json',
				data: {
					fileName: fileName,
					  },
				success : function(data) {
					console.log(data);
					document.getElementById("ScientificName"+id).value=data[0].Synonyms[0];
					var locs = data[0].Location;
					console.log(locs);
					locs.forEach(function(elem, index, array) {
					    addLFields(id,elem);
					});
					document.getElementById("Illness"+id).value=data[0].Illness[0];
				}
				}); 
		}
	</script>
	<script type="text/javascript">
	var lCtr = 0;
	var empty = "\'\'";
	function addLFields(id, locName) {
		console.log(id, locName);
		$('#locationAdd').remove();
		lCtr++;		
		var inputField = '<div class="col-13"><input type="text" class="form-control" name="location" id="Location['+id +']['+lCtr+']" placeholder="Location" value="'+locName+'">';
		var buttonAdd = '<div><button id="locationAdd" type="button" class="btn btn-outline-success btn-sm" onclick="addLFields('+id+','+empty+')" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Location</button></div>';
		
		$('#locationGroup').append(inputField, buttonAdd);
	}
	</script>

	<script type="text/javascript">
	   $(document).on('hidden.bs.modal', '.modal', function () {
	        $('.modal:visible').length && $(document.body).addClass('modal-open');
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

</body>
</html>