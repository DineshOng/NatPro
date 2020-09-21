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
				<p id="genusName">${genus}</p>
			</h1>
			<input class="form-control w-auto h-25 mt-3" type="text"
				id="editGenusField" name="editGenusName" style="display: none"
				required>
			<button type="button" class="btn btn-outline-light btn-sm h-25 mt-3"
				onclick="editGenus()" data-toggle="tooltip" data-placement="top"
				title="edit entry" class="btn btn-primary" id="editGenusBtn">
				<i class="fa fa-pencil" aria-hidden="true" id="editGenusLogo"></i>
			</button>
			<button type="button" class="btn btn-outline-danger btn-sm h-25 mt-3"
				onclick="cancelEditGenus()" data-toggle="tooltip"
				data-placement="top" title="edit entry" class="btn btn-primary"
				id="cancelEditGenusBtn" style="display: none">
				<i class="fa fa-times" aria-hidden="true"></i>
			</button>
		</div>

		<h3 class="text-white text-center">Genus</h3>
	</div>
	<div class="d-flex flex-row list-group text-center ">
		<a
			class="list-group-item list-group-item-action list-group-item-success active"
			id="list-home-list" data-toggle="list" href="#familyList" role="tab"
			aria-controls="FamilyList">Family</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-home-list" data-toggle="list" href="#plantList" role="tab"
			aria-controls="TaxonomicInformation">Plant List</a>
	</div>
	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="familyList" role="tabpanel"
			aria-labelledby="list-home-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-auto text-center">
					<thead>
						<tr>
							<th>Belongs to Family</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="ViewFamilyServlet?family=${family}"><p
										style="display: inline;" id="familyName">${family}</p></a>
								<div class="autocomplete" style="width: 300px; display: none"
									id="familyInputDiv">
									<input type="text" id="familyInput" name="family"
										placeholder="Family">
								</div>
								<button type="button" class="btn btn-outline-dark btn-sm "
									style="display: inline" onclick="editFamily()"
									data-toggle="tooltip" data-trigger="hover" data-placement="top"
									title="select the family this genus belongs to"
									id="editFamilyBtn">
									<i class="fa fa-pencil" aria-hidden="true" id="editFamilyLogo"></i>
								</button>
								<button type="button" class="btn btn-outline-danger btn-sm"
									onclick="cancelEditFamily()" data-toggle="tooltip"
									data-trigger="hover" data-placement="top" title="cancel"
									id="cancelEditFamilyBtn" style="display: none">
									<i class="fa fa-times" aria-hidden="true"></i>
								</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade show" id="plantList" role="tabpanel"
			aria-labelledby="list-home-list">
			<div class="d-flex justify-content-center mt-5 pb-5">
				<table id="table_id_plantlist" class="table table-striped table-bordered"
					style="width: 100%">
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

	<script type="text/javascript">
	function editGenus(){
		console.log("edit");
		document.getElementById("genusName").style.display="none";
		document.getElementById("editGenusField").value = document.getElementById("genusName").innerHTML;
		document.getElementById("editGenusField").style.display="block";
		document.getElementById("editGenusBtn").onclick = function () { saveEditGenus(); };
		document.getElementById("editGenusLogo").classList.remove("fa-pencil");
		document.getElementById("editGenusLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditGenusBtn").style.display="block";
	}
	
	function cancelEditGenus(){
		console.log("cancel");
		document.getElementById("genusName").style.display="block";
		document.getElementById("editGenusField").style.display="none";   
		document.getElementById("editGenusLogo").classList.remove("fa-check");
		document.getElementById("editGenusLogo").classList.add("fa-pencil"); 
		document.getElementById("editGenusBtn").onclick = function () { editGenus(); };	
		document.getElementById("cancelEditGenusBtn").style.display="none";
	}
	
	function saveEditGenus(){
		console.log("save");
		var newGenusNameVal = document.getElementById("editGenusField").value;
		var oldGenusNameVal = document.getElementById("genusName").innerHTML;
	
		$.ajax({
			type : "GET",
			url : 'EditGenusName',
			dataType: "text",
			data: { 
				    newGenusName: newGenusNameVal,
				    oldGenusName: oldGenusNameVal
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Genus Name Successfully Edited"){
					document.getElementById("editGenusField").style.display="none";
					document.getElementById("genusName").innerHTML = newGenusNameVal; 
					document.getElementById("genusName").style.display="block";
					document.getElementById("editGenusBtn").onclick = function () { editGenus(); };		
					document.getElementById("editGenusLogo").classList.remove("fa-check");
					document.getElementById("editGenusLogo").classList.add("fa-pencil");
					document.getElementById("cancelEditGenusBtn").style.display="none";
					window.location.href = "ViewGenusServlet?genus="+newGenusNameVal; 
				}else{
					cancelEditGenus();
				}
			}
			});
		
	}
	
	</script>

	<!-- Script for Edit Family -->
	<script type="text/javascript">
	function editFamily(){
		document.getElementById("familyName").style.display="none";
		document.getElementById("familyInput").value = document.getElementById("familyName").innerHTML.trim();
		document.getElementById("familyInputDiv").style.display= "inline";
		document.getElementById("editFamilyBtn").onclick = function () { saveFamily(); };
		document.getElementById("editFamilyBtn").classList.remove("btn-outline-dark");
		document.getElementById("editFamilyBtn").classList.add("btn-success");
		document.getElementById("editFamilyLogo").classList.remove("fa-pencil");
		document.getElementById("editFamilyLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditFamilyBtn").style.display="inline";
	}
	
	function cancelEditFamily(){
		document.getElementById("familyName").style.display="inline";
		document.getElementById("familyInputDiv").style.display="none";   
		document.getElementById("editFamilyLogo").classList.remove("fa-check");
		document.getElementById("editFamilyLogo").classList.add("fa-pencil"); 
		document.getElementById("editFamilyBtn").onclick = function () { editFamily(); };
		document.getElementById("editFamilyBtn").classList.remove("btn-success");
		document.getElementById("editFamilyBtn").classList.add("btn-outline-dark");
		document.getElementById("cancelEditFamilyBtn").style.display="none";
	}
	
	function saveFamily(){
		var newFamilyVal = document.getElementById("familyInput").value;
		var oldFamilyVal = document.getElementById("familyName").innerHTML.trim();
		var genusVal = document.getElementById("genusName").innerHTML.trim();
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
					window.location.href = "ViewGenusServlet?genus="+genusVal; 
					cancelEditFamily();
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
	        $('#table_id_plantlist').DataTable();
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
		var families = [];
		autocomplete(document.getElementById("familyInput"), families); 
	</script>

	<c:forEach items="${allFamilyList}" var="family">
		<script>
			families.push("${family}");
		</script>
	</c:forEach>
</body>
</html>