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
				<p id="familyName">${family}</p>
			</h1>
			<input class="form-control w-auto h-25 mt-3" type="text"
				id="editFamilyField" name="editFamilyName" style="display: none"
				required>
			<button type="button" class="btn btn-outline-light btn-sm h-25 mt-3"
				onclick="editFamily()" data-toggle="tooltip" data-placement="top"
				title="edit entry" class="btn btn-primary" id="editFamilyBtn">
				<i class="fa fa-pencil" aria-hidden="true" id="editFamilyLogo"></i>
			</button>
			<button type="button" class="btn btn-outline-danger btn-sm h-25 mt-3"
				onclick="cancelEditFamily()" data-toggle="tooltip"
				data-placement="top" title="edit entry" class="btn btn-primary"
				id="cancelEditFamilyBtn" style="display: none">
				<i class="fa fa-times" aria-hidden="true"></i>
			</button>
		</div>
		<h3 class="text-white text-center">Family</h3>
	</div>
	<div class="d-flex flex-row list-group text-center ">
		<a
			class="list-group-item list-group-item-action list-group-item-success active"
			id="list-home-list" data-toggle="list" href="#genusList" role="tab"
			aria-controls="GenusList">Genus List</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-home-list" data-toggle="list" href="#plantList" role="tab"
			aria-controls="TaxonomicInformation">Plant List</a>
	</div>
	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="genusList" role="tabpanel"
			aria-labelledby="list-home-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-auto text-center">
					<thead>
						<tr>
							<th>Genus
								<button type="button" class="btn btn-outline-dark btn-sm"
									onclick="editGenusEntry()" data-toggle="tooltip"
									data-placement="top" title="edit entry" id="editEntryBtn"
									data-trigger="hover">
									<i class="fa fa-pencil" aria-hidden="true"
										id="editGenusEntryLogo"></i>
								</button>
							</th>

						</tr>
					</thead>
					<tbody>
						<c:set var="genusNum" value="1" scope="page" />
						<c:forEach items="${genusList}" var="genusList">
							<tr>
								<td><a href="ViewGenusServlet?genus=${genusList}"><p
											style="display: inline;" id="genusName${genusNum}">${genusList}</p></a>
									<div style="display: inline; float: right;">
										<button type="button" class="btn btn-outline-danger btn-sm "
											style="display: none" onclick="removeGenus(${genusNum})"
											data-toggle="tooltip" data-trigger="hover"
											data-placement="top" title="remove genus"
											id="removeGenusBtn${genusNum}">
											<i class="fa fa-trash" id="removeGenusLogo"
												aria-hidden="true"></i>
										</button>
									</div></td>
							</tr>
							<c:set var="genusNum" value="${genusNum + 1}" scope="page" />
						</c:forEach>
						<tr>
							<td>
								<div class="justify-content-center">
									<div class="autocomplete" style="width: 300px; display: none"
										id="genusInputDiv">
										<input type="text" id="genusInput" name="genus"
											placeholder="Genus">
									</div>
									<button type="button" class="btn btn-outline-dark btn-sm "
										style="display: none" onclick="addGenus()"
										data-toggle="tooltip" data-trigger="hover"
										data-placement="top" title="add genus" id="addGenusBtn">
										<i class="fa fa-plus" id="addGenusLogo" aria-hidden="true"></i>
										Genus
									</button>
									<button type="button" class="btn btn-outline-danger btn-sm"
										onclick="cancelAddGenus()" data-toggle="tooltip"
										data-trigger="hover" data-placement="top"
										id="cancelAddGenusBtn" title="cancel" style="display: none">
										<i class="fa fa-times" aria-hidden="true"></i>
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade show" id="plantList" role="tabpanel"
			aria-labelledby="list-home-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25">
					<thead>
						<tr>
							<th>Family</th>
							<th>Genus</th>
							<th>Plant Name</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${medPlantsList}" var="medPlantsList">
							<tr>
								<td><a
									href="ViewFamilyServlet?family=${medPlantsList.getSpecies().get(0).getFamily()}">${medPlantsList.getSpecies().get(0).getFamily()}</a></td>
								<td><a
									href="ViewGenusServlet?genus=${medPlantsList.getSpecies().get(0).getGenus()}">${medPlantsList.getSpecies().get(0).getGenus()}</a></td>
								<td><a
									href="ViewPlantServlet?medPlant=${medPlantsList.getMedicinalPlant()}">${medPlantsList.getMedicinalPlant()}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- INCLUDE FOOTER HTML -->
	<%@include file="_includeFooter.html"%>

	<!-- Script for Add/Delete Genus -->
	<script type="text/javascript">
	function addGenus(){
		document.getElementById("genusInputDiv").style.display= "inline";
		document.getElementById("addGenusBtn").onclick = function () { saveGenus(); };
		document.getElementById("addGenusBtn").classList.remove("btn-outline-dark");
		document.getElementById("addGenusBtn").classList.add("btn-success");
		document.getElementById("addGenusLogo").classList.remove("fa-plus");
		document.getElementById("addGenusLogo").classList.add("fa-check");  
		
		document.getElementById("cancelAddGenusBtn").style.display="inline";
	}
	
	function cancelAddGenus(){
		document.getElementById("genusInputDiv").style.display="none";   
		document.getElementById("addGenusLogo").classList.remove("fa-check");
		document.getElementById("addGenusLogo").classList.add("fa-plus"); 
		document.getElementById("addGenusBtn").onclick = function () { addGenus(); };	
		document.getElementById("addGenusBtn").classList.remove("btn-success");
		document.getElementById("addGenusBtn").classList.add("btn-outline-dark");
		document.getElementById("cancelAddGenusBtn").style.display="none";
		document.getElementById("genusInput").value = "";
	}
	
	function saveGenus(){
		var genusVal = document.getElementById("genusInput").value.trim();
		var familyName = document.getElementById("familyName").innerHTML.trim();
		
		console.log(genusVal);
		console.log(familyName);
  		$.ajax({
			type : "GET",
			url : 'AddGenus',
			dataType: "text",
			data: {
				genusVal: genusVal,
				familyName: familyName
				  },
			success : function(data) {
				alert(data)
				
				if(data.trim() == "Genus Added Successfully"){
					window.location.href = "ViewFamilyServlet?family="+familyName; 
					cancelAddGenus();
				}else{
					cancelAddGenus();
				}
			}
			});  
		
	}
	
	function removeGenus(genusNum){
		var genusVal = document.getElementById("genusName"+genusNum).innerHTML.trim();
		var familyName = document.getElementById("familyName").innerHTML.trim();
		
    		$.ajax({
			type : "GET",
			url : 'RemoveGenus',
			dataType: "text",
			data: {
				genusVal: genusVal,
				familyName: familyName
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Genus Removed"){
					window.location.href = "ViewFamilyServlet?family="+familyName; 
				}
			}
			});   
	}
	</script>

	<script type="text/javascript">
	function editGenusEntry(){
		var i;
		for (i = 1; i < "${genusNum}"; i++) {
			var id = 'removeGenusBtn'+i;
			document.getElementById(id).style.display="inline";
		}
		document.getElementById("editEntryBtn").onclick = function () { cancelEditGenusEntry(); };
		document.getElementById("editEntryBtn").classList.remove("btn-outline-dark");
		document.getElementById("editEntryBtn").classList.add("btn-danger");
		document.getElementById("editGenusEntryLogo").classList.remove("fa-pencil");
		document.getElementById("editGenusEntryLogo").classList.add("fa-times");  
		document.getElementById("addGenusBtn").style.display="inline";
		
	}
	
	function cancelEditGenusEntry(){
		var i;
		for (i = 1; i < "${genusNum}"; i++) {
			var id = 'removeGenusBtn'+i;
			document.getElementById(id).style.display="none";
		}
		document.getElementById("editEntryBtn").onclick = function () { editGenusEntry(); };
		document.getElementById("editEntryBtn").classList.remove("btn-danger");
		document.getElementById("editEntryBtn").classList.add("btn-outline-dark");
		document.getElementById("editGenusEntryLogo").classList.remove("fa-times");  
		document.getElementById("editGenusEntryLogo").classList.add("fa-pencil");
		document.getElementById("addGenusBtn").style.display="none";
		cancelAddGenus();
		
	}
	</script>

	<script type="text/javascript">
	function editFamily(){
		console.log("edit");
		document.getElementById("familyName").style.display="none";
		document.getElementById("editFamilyField").value = document.getElementById("familyName").innerHTML;
		document.getElementById("editFamilyField").style.display="block";
		document.getElementById("editFamilyBtn").onclick = function () { saveEditFamily(); };
		document.getElementById("editFamilyLogo").classList.remove("fa-pencil");
		document.getElementById("editFamilyLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditFamilyBtn").style.display="block";
	}
	
	function cancelEditFamily(){
		console.log("cancel");
		document.getElementById("familyName").style.display="block";
		document.getElementById("editFamilyField").style.display="none";   
		document.getElementById("editFamilyLogo").classList.remove("fa-check");
		document.getElementById("editFamilyLogo").classList.add("fa-pencil"); 
		document.getElementById("editFamilyBtn").onclick = function () { editFamily(); };	
		document.getElementById("cancelEditFamilyBtn").style.display="none";
	}
	
	function saveEditFamily(){
		console.log("save");
		var newFamilyNameVal = document.getElementById("editFamilyField").value;
		var oldFamilyNameVal = document.getElementById("familyName").innerHTML;
	
		$.ajax({
			type : "GET",
			url : 'EditFamilyName',
			dataType: "text",
			data: { 
				    newFamilyName: newFamilyNameVal,
				    oldFamilyName: oldFamilyNameVal
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Family Name Successfully Edited"){
					document.getElementById("editFamilyField").style.display="none";
					document.getElementById("familyName").innerHTML = newFamilyNameVal; 
					document.getElementById("familyName").style.display="block";
					document.getElementById("editFamilyBtn").onclick = function () { editFamily(); };		
					document.getElementById("editFamilyLogo").classList.remove("fa-check");
					document.getElementById("editFamilyLogo").classList.add("fa-pencil");   
					document.getElementById("cancelEditFamilyBtn").style.display="none";
					window.location.href = "ViewFamilyServlet?family="+newFamilyNameVal; 
				}else{
					cancelEditFamily();
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

	<script type="text/javascript" src="js/autocomplete.js"></script>
	<!-- Script to Generate Autocomplete Fields -->
	<script>	
		var genus = [];
		autocomplete(document.getElementById("genusInput"), genus); 
	</script>

	<c:forEach items="${allGenusList}" var="genus">
		<script>
			genus.push("${genus}");
		</script>
	</c:forEach>

</body>
</html>