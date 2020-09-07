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

	<div class="jumbotron bg-success">
		<div class="d-flex justify-content-center">
			<h1 class="display-4 text-white" style="display: block">
				<p id="medPlantName">${medPlantsList.get(0).getMedicinalPlant()}</p>
			</h1>
			<input class="form-control w-auto h-25 mt-3" type="text"
				id="editMedPlantField" name="editMedPlantName" style="display: none"
				required>
			<button type="button" class="btn btn-outline-light btn-sm h-25 mt-3"
				onclick="editMedPlant()" data-toggle="tooltip" data-placement="top"
				title="edit entry" class="btn btn-primary" id="editMedPlantBtn">
				<i class="fa fa-pencil" aria-hidden="true" id="editMedPlantLogo"></i>
			</button>
			<button type="button" class="btn btn-outline-danger btn-sm h-25 mt-3"
				onclick="cancelEditMedPlant()" data-toggle="tooltip"
				data-placement="top" title="edit entry" class="btn btn-primary"
				id="cancelEditMedPlantBtn" style="display: none">
				<i class="fa fa-times" aria-hidden="true"></i>
			</button>
		</div>
		<h3 class="text-white text-center">Plant</h3>
	</div>
	<div class="d-flex flex-row list-group text-center ">
		<a
			class="list-group-item list-group-item-action list-group-item-success active"
			id="list-home-list" data-toggle="list" href="#taxInfo" role="tab"
			aria-controls="TaxonomicInformation">Taxonomic Information</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-profile-list" data-toggle="list" href="#plantName"
			role="tab" aria-controls="ScientificName">Scientific Name(s)</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-messages-list" data-toggle="list" href="#location"
			role="tab" aria-controls="Location">Location(s)</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-settings-list" data-toggle="list" href="#bioAct" role="tab"
			aria-controls="BiologicalActivities">Biological Activities</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-settings-list" data-toggle="list" href="#prep" role="tab"
			aria-controls="Preparation">Preparation(s)</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-settings-list" data-toggle="list" href="#chemComp"
			role="tab" aria-controls="ChemicalCompounds">Chemical Compound(s)</a>
		<a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-settings-list" data-toggle="list" href="#photos" role="tab"
			aria-controls="Photos">Photos</a>
	</div>
	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="taxInfo" role="tabpanel"
			aria-labelledby="list-home-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25">
					<thead>
						<tr>
							<td>
								<h4>Taxonomic Information</h4>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Family</th>
							<td class="center"><a
								href="ViewFamilyServlet?family=${medPlantsList.get(0).getSpecies().get(0).getFamily()}">${medPlantsList.get(0).getSpecies().get(0).getFamily()}</a></td>
						</tr>
						<tr>
							<th>Genus</th>
							<td class="center"><a
								href="ViewGenusServlet?genus=${medPlantsList.get(0).getSpecies().get(0).getGenus()}">${medPlantsList.get(0).getSpecies().get(0).getGenus()}</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="plantName" role="tabpanel"
			aria-labelledby="list-profile-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25 text-center">
					<thead>
						<tr>
							<td>
								<h4>Scientific Name(s)</h4>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach items="${medPlantsList.get(0).getSpecies()}"
								var="speciesList">
								<tr>
									<td><a
										href="ViewSciPlantServlet?specie=${speciesList.getSpecie()}"><i>${speciesList.getSpecie()}</i></a></td>
								</tr>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="location" role="tabpanel"
			aria-labelledby="list-messages-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25 text-center">
					<thead>
						<tr>
							<td>
								<h4>Location(s)</h4>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach items="${medPlantsList.get(0).getLocations()}"
								var="locationsList">
								<tr>
									<td>${locationsList}</td>
								</tr>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="bioAct" role="tabpanel"
			aria-labelledby="list-settings-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover">
					<thead>
						<tr>
							<th><h4>Biological Activities</h4></th>
						</tr>
						<tr>
							<th>Chemical Compound</th>
							<th>Biological Activity</th>
							<th>Cell Line</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${medPlantsList.get(0).getSpecies()}"
							var="speciesList">
							<c:forEach items="${speciesList.getSpeciesParts()}"
								var="speciesPartList">
								<c:forEach items="${speciesPartList.getCompounds()}"
									var="compoundsList">
									<c:forEach items="${compoundsList.getBioActs()}"
										var="bioActsList">
										<tr>
											<td><a
												href="ViewCompoundServlet?compound=${compoundsList.getCompoundName()}">${compoundsList.getCompoundNameHTML()}</a></td>
											<td>${bioActsList.getBiologicalActivity()}</td>
											<td>${bioActsList.getCellLine().getCellLine()}</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="prep" role="tabpanel"
			aria-labelledby="list-settings-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover">
					<thead>
						<tr>
							<th><h4>Preparation</h4></th>
						</tr>
						<tr>
							<th>Preparation</th>
							<th>Utilized Plant Part</th>
							<th>Illness</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${medPlantsList.get(0).getPreparations()}"
							var="prepList">
							<c:forEach items="${prepList.getIllness()}" var="illnessList">
								<tr>
									<td>${prepList.getPreparation()}</td>
									<td>${prepList.getUtilizedPlantPart()}</td>
									<td>${illnessList}</td>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="chemComp" role="tabpanel"
			aria-labelledby="list-settings-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover">
					<thead>
						<tr>
							<th><h4>Chemical Compounds</h4></th>
						</tr>
						<tr>
							<th>Plant Part</th>
							<th>Structure</th>
							<th>Name</th>
							<th>Formula</th>
							<th>Molecular Weight</th>
							<th>XLoGP</th>
							<th>TPSA</th>
							<th># HBA</th>
							<th># HDB</th>
							<th># Rotatable Bonds</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${medPlantsList.get(0).getSpecies()}"
							var="speciesList">
							<c:forEach items="${speciesList.getSpeciesParts()}"
								var="speciesPartList">
								<c:forEach items="${speciesPartList.getCompounds()}"
									var="compoundsList">
									<tr>
										<td>${speciesPartList.getPlantPart()}</td>
										<td></td>
										<td><a
											href="ViewCompoundServlet?compound=${compoundsList.getCompoundName()}">${compoundsList.getCompoundNameHTML()}</a></td>
										<td>${compoundsList.getMolForm()}</td>
										<td>${compoundsList.getMolWeight()}</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</c:forEach>
							</c:forEach>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="photos" role="tabpanel"
			aria-labelledby="list-settings-list">...</div>
	</div>
	<!-- INCLUDE FOOTER HTML -->
	<%@include file="_includeFooter.html"%>


	<script type="text/javascript">
	function editMedPlant(){
		console.log("edit");
		document.getElementById("medPlantName").style.display="none";
		document.getElementById("editMedPlantField").value = document.getElementById("medPlantName").innerHTML;
		document.getElementById("editMedPlantField").style.display="block";
		document.getElementById("editMedPlantBtn").onclick = function () { saveEditMedPlant(); };
		document.getElementById("editMedPlantLogo").classList.remove("fa-pencil");
		document.getElementById("editMedPlantLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditMedPlantBtn").style.display="block";
	}
	
	function cancelEditMedPlant(){
		console.log("cancel");
		document.getElementById("medPlantName").style.display="block";
		document.getElementById("editMedPlantField").style.display="none";   
		document.getElementById("editMedPlantLogo").classList.remove("fa-check");
		document.getElementById("editMedPlantLogo").classList.add("fa-pencil"); 
		document.getElementById("editMedPlantBtn").onclick = function () { editMedPlant(); };	
		document.getElementById("cancelEditMedPlantBtn").style.display="none";
	}
	
	function saveEditMedPlant(){
		console.log("save");
		var newMedPlantNameVal = document.getElementById("editMedPlantField").value;
		var oldMedPlantNameVal = document.getElementById("medPlantName").innerHTML;
	
		$.ajax({
			type : "GET",
			url : 'EditMedPlant',
			dataType: "text",
			data: { 
				    newMedPlantName: newMedPlantNameVal,
				    oldMedPlantName: oldMedPlantNameVal
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Plant Name Successfully Edited"){
					document.getElementById("editMedPlantField").style.display="none";
					document.getElementById("medPlantName").innerHTML = newMedPlantNameVal; 
					document.getElementById("medPlantName").style.display="block";
					document.getElementById("editMedPlantBtn").onclick = function () { editMedPlant(); };		
					document.getElementById("editMedPlantLogo").classList.remove("fa-check");
					document.getElementById("editMedPlantLogo").classList.add("fa-pencil"); 
					document.getElementById("cancelEditMedPlantBtn").style.display="none";
				}else{
					cancelEditMedPlant();
				}
			}
			});
		
	}
	
	</script>

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

</body>
</html>