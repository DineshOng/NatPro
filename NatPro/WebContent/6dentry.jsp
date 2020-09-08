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

<link href="css/autocomplete.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
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
				<table class="table table-hover w-auto">
					<thead>
						<tr>
							<td>
								<h4>Taxonomic Information</h4>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Genus</th>
							<td class="center"><a id="genus" style="display: inline"
								href="ViewGenusServlet?genus=${medPlantsList.get(0).getSpecies().get(0).getGenus()}">
									<p style="display: inline;" id="genusName">${medPlantsList.get(0).getSpecies().get(0).getGenus()}</p>
							</a>
								<div class="autocomplete" style="width: 300px; display: none"
									id="genusInputDiv">
									<input type="text" id="genusInput" name="genus"
										placeholder="Genus">
								</div>
								<button type="button" class="btn btn-outline-dark btn-sm "
									onclick="editGenus()" data-toggle="tooltip"
									data-placement="top" title="edit entry" class="btn btn-primary"
									id="editGenusBtn">
									<i class="fa fa-pencil" aria-hidden="true" id="editGenusLogo"></i>
								</button>
								<button type="button" class="btn btn-outline-danger btn-sm"
									onclick="cancelEditGenus()" data-toggle="tooltip"
									data-placement="top" title="edit entry" class="btn btn-primary"
									id="cancelEditGenusBtn" style="visibility: hidden">
									<i class="fa fa-times" aria-hidden="true"></i>
								</button></td>
						</tr>
						<tr>
							<th>Family</th>
							<td class="center"><a id="family" style="display: inline"
								href="ViewFamilyServlet?family=${medPlantsList.get(0).getSpecies().get(0).getFamily()}">
									<p style="display: inline;" id="familyName">${medPlantsList.get(0).getSpecies().get(0).getFamily()}
									</p>
							</a> <!-- <div class="autocomplete" style="width: 300px; display: none"
									id="familyInputDiv">
									<input type="text" id="familyInput" name="family"
										placeholder="Family">
								</div>
								<button type="button" class="btn btn-outline-dark btn-sm "
									onclick="editFamily()" data-toggle="tooltip"
									data-placement="top" title="edit entry" class="btn btn-primary"
									id="editFamilyBtn">
									<i class="fa fa-pencil" aria-hidden="true" id="editFamilyLogo"></i>
								</button>
								<button type="button" class="btn btn-outline-danger btn-sm"
									onclick="cancelEditFamily()" data-toggle="tooltip"
									data-placement="top" title="edit entry" class="btn btn-primary"
									id="cancelEditFamilyBtn" style="visibility: hidden">
									<i class="fa fa-times" aria-hidden="true"></i>
								</button> --></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="plantName" role="tabpanel"
			aria-labelledby="list-profile-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-auto">
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
									<td><a id="specie" style="display: inline"
										href="ViewSciPlantServlet?specie=${speciesList.getSpecie()}">
											<i><p style="display: inline;" id="specieName">${speciesList.getSpecie()}</p></i>
									</a>
										<div class="autocomplete" style="width: 300px; display: none"
											id="specieInputDiv">
											<input type="text" id="specieInput" name="specie"
												placeholder="Scientific Name">
										</div>
										<button type="button" class="btn btn-outline-dark btn-sm "
											onclick="editSpecie()" data-toggle="tooltip"
											data-placement="top" title="edit entry"
											class="btn btn-primary" id="editSpecieBtn">
											<i class="fa fa-pencil" aria-hidden="true"
												id="editSpecieLogo"></i>
										</button>
										<button type="button" class="btn btn-outline-danger btn-sm"
											onclick="cancelEditSpecie()" data-toggle="tooltip"
											data-placement="top" title="edit entry"
											class="btn btn-primary" id="cancelEditSpecieBtn"
											style="visibility: hidden">
											<i class="fa fa-times" aria-hidden="true"></i>
										</button></td>
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

	<script type="text/javascript" src="js/autocomplete.js"></script>


	<script>	
		var families = [];
		var genus = [];
		var syns = [];
		/* autocomplete(document.getElementById("familyInput"), families);  */
		autocomplete(document.getElementById("genusInput"), genus); 
		autocomplete(document.getElementById("specieInput"), syns); 
	</script>

	<c:forEach items="${familyList}" var="family">
		<script>
			families.push("${family}")
		</script>
	</c:forEach>

	<c:forEach items="${genusList}" var="genus">
		<script>
			genus.push("${genus}")
		</script>
	</c:forEach>

	<c:forEach items="${synList}" var="syn">
		<script>
			syns.push("${syn}")
		</script>
	</c:forEach>

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

	<script type="text/javascript">
	function editFamily(){
		document.getElementById("family").style.display="none";
		document.getElementById("familyInput").value = document.getElementById("familyName").innerHTML.trim();
		document.getElementById("familyInputDiv").style.display= "inline";
		document.getElementById("editFamilyBtn").onclick = function () { saveFamily(); };
		document.getElementById("editFamilyBtn").classList.remove("btn-outline-dark");
		document.getElementById("editFamilyBtn").classList.add("btn-success");
		document.getElementById("editFamilyLogo").classList.remove("fa-pencil");
		document.getElementById("editFamilyLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditFamilyBtn").style.visibility="visible";
	}
	
	function cancelEditFamily(){
		document.getElementById("family").style.display="inline";
		document.getElementById("familyInputDiv").style.display="none";   
		document.getElementById("editFamilyLogo").classList.remove("fa-check");
		document.getElementById("editFamilyLogo").classList.add("fa-pencil"); 
		document.getElementById("editFamilyBtn").onclick = function () { editFamily(); };
		document.getElementById("editFamilyBtn").classList.remove("btn-success");
		document.getElementById("editFamilyBtn").classList.add("btn-outline-dark");
		document.getElementById("cancelEditFamilyBtn").style.visibility="hidden";
	}
	
	function saveFamily(){
		var newFamilyVal = document.getElementById("familyInput").value;
		var oldFamilyVal = document.getElementById("familyName").innerHTML.trim();
	
 		$.ajax({
			type : "GET",
			url : 'EditFamily',
			dataType: "text",
			data: { 
				newFamilyVal: newFamilyVal,
				oldFamilyVal: oldFamilyVal
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Family Successfully Edited"){
					document.getElementById("familyInputDiv").style.display="none";
					document.getElementById("familyName").innerHTML = newFamilyVal; 
					document.getElementById("family").style.display="inline";
					document.getElementById("editFamilyBtn").onclick = function () { editFamily(); };	
					document.getElementById("editFamilyBtn").classList.remove("btn-success");
					document.getElementById("editFamilyBtn").classList.add("btn-outline-dark");
					document.getElementById("editFamilyLogo").classList.remove("fa-check");
					document.getElementById("editFamilyLogo").classList.add("fa-pencil"); 
					document.getElementById("cancelEditFamilyBtn").style.visibility="hidden";
				}else{
					cancelEditFamily();
				}
			}
			}); 
		
	}
	</script>

	<script type="text/javascript">
	function editGenus(){
		document.getElementById("genus").style.display="none";
		document.getElementById("genusInput").value = document.getElementById("genusName").innerHTML.trim();
		document.getElementById("genusInputDiv").style.display= "inline";
		document.getElementById("editGenusBtn").onclick = function () { saveGenus(); };
		document.getElementById("editGenusBtn").classList.remove("btn-outline-dark");
		document.getElementById("editGenusBtn").classList.add("btn-success");
		document.getElementById("editGenusLogo").classList.remove("fa-pencil");
		document.getElementById("editGenusLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditGenusBtn").style.visibility="visible";
	}
	
	function cancelEditGenus(){
		document.getElementById("genus").style.display="inline";
		document.getElementById("genusInputDiv").style.display="none";   
		document.getElementById("editGenusLogo").classList.remove("fa-check");
		document.getElementById("editGenusLogo").classList.add("fa-pencil"); 
		document.getElementById("editGenusBtn").onclick = function () { editGenus(); };	
		document.getElementById("editGenusBtn").classList.remove("btn-success");
		document.getElementById("editGenusBtn").classList.add("btn-outline-dark");
		document.getElementById("cancelEditGenusBtn").style.visibility="hidden";
	}
	
	function saveGenus(){
		var newGenusVal = document.getElementById("genusInput").value;
		var oldGenusVal = document.getElementById("genusName").innerHTML.trim();
		var sciNameVal = document.getElementById("specieName").innerHTML.trim();
	
		console.log(newGenusVal);
		console.log(oldGenusVal);
 		$.ajax({
			type : "GET",
			url : 'EditGenus',
			dataType: "text",
			data: {
				newGenusVal: newGenusVal,
				oldGenusVal: oldGenusVal,
				sciNameVal: sciNameVal
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Genus Successfully Edited"){
					document.getElementById("genusInputDiv").style.display="none";
					document.getElementById("genusName").innerHTML = newGenusVal; 
					document.getElementById("genus").style.display="inline";
					document.getElementById("editGenusBtn").onclick = function () { editMedPlant(); };
					document.getElementById("editGenusBtn").classList.remove("btn-success");
					document.getElementById("editGenusBtn").classList.add("btn-outline-dark");
					document.getElementById("editGenusLogo").classList.remove("fa-check");
					document.getElementById("editGenusLogo").classList.add("fa-pencil"); 
					document.getElementById("cancelEditGenusBtn").style.visibility="hidden";
					window.location.href = "ViewPlantServlet?medPlant=${medPlantsList.get(0).getMedicinalPlant()}"; 
				}else{
					cancelEditGenus();
				}
			}
			}); 
		
	}
	</script>

	<script type="text/javascript">
	function editSpecie(){
		document.getElementById("specie").style.display="none";
		document.getElementById("specieInput").value = document.getElementById("specieName").innerHTML.trim();
		document.getElementById("specieInputDiv").style.display= "inline";
		document.getElementById("editSpecieBtn").onclick = function () { saveSpecie(); };
		document.getElementById("editSpecieBtn").classList.remove("btn-outline-dark");
		document.getElementById("editSpecieBtn").classList.add("btn-success");
		document.getElementById("editSpecieLogo").classList.remove("fa-pencil");
		document.getElementById("editSpecieLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditSpecieBtn").style.visibility="visible";
	}
	
	function cancelEditSpecie(){
		document.getElementById("specie").style.display="inline";
		document.getElementById("specieInputDiv").style.display="none";   
		document.getElementById("editSpecieLogo").classList.remove("fa-check");
		document.getElementById("editSpecieLogo").classList.add("fa-pencil"); 
		document.getElementById("editSpecieBtn").onclick = function () { editSpecie(); };	
		document.getElementById("editSpecieBtn").classList.remove("btn-success");
		document.getElementById("editSpecieBtn").classList.add("btn-outline-dark");
		document.getElementById("cancelEditSpecieBtn").style.visibility="hidden";
	}
	
	function saveSpecie(){
		var newSpecieVal = document.getElementById("specieInput").value;
		var oldSpecieVal = document.getElementById("specieName").innerHTML.trim();
		var medPlantName = document.getElementById("medPlantName").innerHTML.trim();
		
		console.log(newSpecieVal);
		console.log(oldSpecieVal);
 		$.ajax({
			type : "GET",
			url : 'EditSci',
			dataType: "text",
			data: {
				newSpecieVal: newSpecieVal,
				oldSpecieVal: oldSpecieVal,
				medPlantName: medPlantName
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Scientific Name Successfully Edited"){
					document.getElementById("specieInputDiv").style.display="none";
					document.getElementById("specieName").innerHTML = newSpecieVal; 
					document.getElementById("specie").style.display="inline";
					document.getElementById("editSpecieBtn").onclick = function () { editMedPlant(); };
					document.getElementById("editSpecieBtn").classList.remove("btn-success");
					document.getElementById("editSpecieBtn").classList.add("btn-outline-dark");
					document.getElementById("editSpecieLogo").classList.remove("fa-check");
					document.getElementById("editSpecieLogo").classList.add("fa-pencil"); 
					document.getElementById("cancelEditSpecieBtn").style.visibility="hidden";
					window.location.href = "ViewPlantServlet?medPlant=${medPlantsList.get(0).getMedicinalPlant()}"; 
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