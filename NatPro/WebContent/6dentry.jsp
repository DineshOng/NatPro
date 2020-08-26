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
		<h1 class="display-4 text-white text-center">${medPlantsList.get(0).getMedicinalPlant()}</h1>
	</div>
	<div class="d-flex flex-row list-group text-center">
		<a class="list-group-item list-group-item-action active"
			id="list-home-list" data-toggle="list" href="#taxInfo" role="tab"
			aria-controls="TaxonomicInformation">Taxonomic Information</a> <a
			class="list-group-item list-group-item-action" id="list-profile-list"
			data-toggle="list" href="#plantName" role="tab"
			aria-controls="ScientificName">Scientific/Common Name(s)</a> <a
			class="list-group-item list-group-item-action"
			id="list-messages-list" data-toggle="list" href="#location"
			role="tab" aria-controls="Location">Location(s)</a> <a
			class="list-group-item list-group-item-action"
			id="list-settings-list" data-toggle="list" href="#bioAct" role="tab"
			aria-controls="BiologicalActivities">Biological Activities</a> <a
			class="list-group-item list-group-item-action"
			id="list-settings-list" data-toggle="list" href="#prep" role="tab"
			aria-controls="Preparation">Preparation(s)</a> <a
			class="list-group-item list-group-item-action"
			id="list-settings-list" data-toggle="list" href="#chemComp"
			role="tab" aria-controls="ChemicalCompounds">Chemical Compound(s)</a>
		<a class="list-group-item list-group-item-action"
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
							<td class="center">${medPlantsList.get(0).getSpecies().get(0).getFamily()}</td>
						</tr>
						<tr>
							<th>Genus</th>
							<td class="center">${medPlantsList.get(0).getSpecies().get(0).getGenus()}</td>
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
									<td><i>${speciesList.getSpecie()}</i></td>
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
			aria-labelledby="list-settings-list">...</div>
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
							<th>Part</th>
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