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
						<label for="Scientific Name">Scientific Name</label>
    					<input type="text" class="form-control" id="ScientificName" placeholder="">
					</div>
					<div class="col-6">
						<label for="Location">Location</label>
    					<input type="text" class="form-control" id="Location" placeholder="">
					</div>
					<div class="col-6"> </div>
					<div class="col-6">
						<button type="button" class="btn btn-primary btn-sm" style="margin-top:5px">Location</button>
					</div>
				</div>
				
				
				
				<h4 style="padding-bottom: 20px; text-align:center">Preparation</h4>
				<div class="form-group form-row">
					<div class="col">
						<label for="Preparation">Preparation</label>
    					<input type="text" class="form-control" id="Preparation" placeholder="">
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
				
				<div class="form-group form-row">
					<div class="col-3"> </div>
					<div class="col-6">
						<label for="Illness">Illness</label>
    					<input type="text" class="form-control" id="Illness" placeholder="">
					</div>
					<div class="col-3"> </div>
					
					<div class="col-3"> </div>
					<div class="col-6">
						<button type="button" class="btn btn-primary btn-sm" style="margin-top:5px">Illness</button>
					</div>
					
					<div class="col-12" style="text-align: center">
						<button type="button" class="btn btn-primary btn-lg" style="margin-top:10px">Preparation</button>
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
					</div
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
    
  </body>
</html>