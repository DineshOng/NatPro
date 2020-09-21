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
				<p id="specieName">${specie}</p>
			</h1>
			<input class="form-control w-auto h-25 mt-3" type="text"
				id="editSpecieField" name="editSpecieName" style="display: none"
				required>
			<button type="button" class="btn btn-outline-light btn-sm h-25 mt-3"
				onclick="editSpecie()" data-toggle="tooltip" data-placement="top"
				title="edit entry" class="btn btn-primary" id="editSpecieBtn">
				<i class="fa fa-pencil" aria-hidden="true" id="editSpecieLogo"></i>
			</button>
			<button type="button" class="btn btn-outline-danger btn-sm h-25 mt-3"
				onclick="cancelEditSpecie()" data-toggle="tooltip"
				data-placement="top" title="edit entry" class="btn btn-primary"
				id="cancelEditSpecieBtn" style="display: none">
				<i class="fa fa-times" aria-hidden="true"></i>
			</button>
		</div>
		<h3 class="text-white text-center">Scientific Name</h3>
	</div>
	<div class="d-flex flex-row list-group text-center ">
		<a
			class="list-group-item list-group-item-action list-group-item-success active"
			id="list-home-list" data-toggle="list" href="#taxInfo" role="tab"
			aria-controls="TaxonomicInformation">Taxonomic Information</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-profile-list" data-toggle="list" href="#plantName"
			role="tab" aria-controls="CommonName">Common Name</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-settings-list" data-toggle="list" href="#bioAct" role="tab"
			aria-controls="BiologicalActivities">Biological Activities</a> <a
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
								href="ViewFamilyServlet?family=${SpecieObject.getFamily()}">${SpecieObject.getFamily()}</a></td>
						</tr>
						<tr>
							<th>Genus</th>
							<td class="center"><a
								href="ViewGenusServlet?genus=${SpecieObject.getGenus()}">${SpecieObject.getGenus()}</a></td>
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
								<h4>Common Name</h4>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
						<tr>
							<td><a href="ViewPlantServlet?medPlant=${medPlantName}">${medPlantName}</a></td>
						</tr>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="bioAct" role="tabpanel"
			aria-labelledby="list-settings-list">
			<div class="d-flex justify-content-center mt-5 pb-5">
				<table id="table_id_bioact"
					class="table table-striped table-bordered" style="width: 100%">
					<thead>
						<tr>
							<th colspan="3"><h4>
									Biological Activities <i id="editBioActTip"
										style="display: inline;" class="fa fa-info-circle"
										data-toggle="popover" data-placement="top"
										data-trigger="hover"
										data-content="You may edit the biological activity/s in the compound page."></i>
								</h4></th>
						</tr>
						<tr>
							<th>Chemical Compound</th>
							<th>Biological Activity</th>
							<th>Cell Line</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${SpecieObject.getSpeciesParts()}"
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
					</tbody>
				</table>
			</div>
		</div>
		<%-- 		<div class="tab-pane fade" id="prep" role="tabpanel"
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
		</div> --%>
		<div class="tab-pane fade" id="chemComp" role="tabpanel"
			aria-labelledby="list-settings-list">
			<div class="d-flex justify-content-center mt-5 pb-5">
				<table id="table_id_chem" class="table table-striped table-bordered"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="11"><h4>Chemical Compounds <i id="editChemTip"
										style="display: inline;" class="fa fa-info-circle"
										data-toggle="popover" data-placement="top"
										data-trigger="hover"
										data-content="You may edit the set of compounds in the plant (common name) page."></i></h4></th>
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
						<c:forEach items="${SpecieObject.getSpeciesParts()}"
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
	function editSpecie(){
		console.log("edit");
		document.getElementById("specieName").style.display="none";
		document.getElementById("editSpecieField").value = document.getElementById("specieName").innerHTML;
		document.getElementById("editSpecieField").style.display="block";
		document.getElementById("editSpecieBtn").onclick = function () { saveEditSpecie(); };
		document.getElementById("editSpecieLogo").classList.remove("fa-pencil");
		document.getElementById("editSpecieLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditSpecieBtn").style.display="block";
	}
	
	function cancelEditSpecie(){
		console.log("cancel");
		document.getElementById("specieName").style.display="block";
		document.getElementById("editSpecieField").style.display="none";   
		document.getElementById("editSpecieLogo").classList.remove("fa-check");
		document.getElementById("editSpecieLogo").classList.add("fa-pencil"); 
		document.getElementById("editSpecieBtn").onclick = function () { editSpecie(); };	
		document.getElementById("cancelEditSpecieBtn").style.display="none";
	}
	
	function saveEditSpecie(){
		console.log("save");
		var newSpecieNameVal = document.getElementById("editSpecieField").value;
		var oldSpecieNameVal = document.getElementById("specieName").innerHTML;
	
		$.ajax({
			type : "GET",
			url : 'EditSciName',
			dataType: "text",
			data: { 
				    newSpecieName: newSpecieNameVal,
				    oldSpecieName: oldSpecieNameVal
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Scientific Name Successfully Edited"){
					document.getElementById("editSpecieField").style.display="none";
					document.getElementById("specieName").innerHTML = newSpecieNameVal; 
					document.getElementById("specieName").style.display="block";
					document.getElementById("editSpecieBtn").onclick = function () { editSpecie(); };		
					document.getElementById("editSpecieLogo").classList.remove("fa-check");
					document.getElementById("editSpecieLogo").classList.add("fa-pencil");
					document.getElementById("cancelEditSpecieBtn").style.display="none";
				}else{
					cancelEditSpecie();
				}
			}
			});
		
	}
	
	</script>

	<script type="text/javascript"
		src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

	<script>
	$(function () {
		  $('[data-toggle="tooltip"]').tooltip();
		  $('[data-toggle="popover"]').popover();
		})
	</script>

	<script type="text/javascript">
	    $(document).ready(function() {
	        $('#table_id_bioact').DataTable();
	        $('#table_id_chem').DataTable();
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