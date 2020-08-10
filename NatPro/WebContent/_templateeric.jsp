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
	<div class="container-fluid">
		<h1 class="text-center text-success">Add</h1>

		<div class="container">
			<form>
				<h5 class="text-center">General</h5>
				<div class="form-group align-items-center">
					<label for="inputcomplant ">Common Plant Name</label> <input
						type="text" class="form-control" placeholder="Common Plant Name">
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputgenus">Genus</label> <input type="email"
							class="form-control" id="inputEmail4" placeholder="Genus">
					</div>
					<div class="form-group col-md-6">
						<label for="inputfamily">Family</label> <input type="password"
							class="form-control" id="inputPassword4" placeholder="Family">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputsciname">Scientific Name</label> <input
							type="email" class="form-control" id="inputEmail4"
							placeholder="Scientific Name">
					</div>
					<div class="form-group col-md-6">
						<label for="inputloc">Location</label> <input type="password"
							class="form-control" id="inputPassword4" placeholder="Location">
					</div>
				</div>

				<br>
				<h5 class="text-center">Preparation</h5>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputCity">Preparation</label> <input type="text"
							class="form-control" id="inputCity" placeholder="Preparation">
					</div>
					<div class="form-group col-md-3">
						<label for="inputState">Plant Part</label> <select id="inputState"
							class="form-control">
							<option selected>Choose utilized plant part</option>
							<option>...</option>
						</select>
					</div>
					<div class="form-group col-md-3">
						<label for="inputZip">Illness</label> <input type="text"
							class="form-control" id="inputZip" placeholder="Illness">
					</div>
				</div>

				<br>
				<h5 class="text-center">Species</h5>
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="inputState">Plant Part</label> <select id="inputState"
							class="form-control">
							<option selected>Choose plant part</option>
							<option>...</option>
						</select>
					</div>
					<div class="form-group col-md-3">
						<label for="inputZip">Other Plant Part</label> <input type="text"
							class="form-control" id="inputZip" placeholder="Other Plant Part">
					</div>
				</div>
				<div class="form-group align-items-center">
					<label for="inputcomplant ">Chemical Compound</label> <input
						type="text" class="form-control" placeholder="Chemical Compound">
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputgenus">Biological Activity</label> <input type="email"
							class="form-control" id="inputEmail4" placeholder="Biological Activity">
					</div>
					<div class="form-group col-md-6">
						<label for="inputfamily">Illness</label> <input type="password"
							class="form-control" id="inputPassword4" placeholder="Illness">
					</div>
				</div>
				<button type="submit" class="btn btn-success">Submit</button>
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

</body>
</html>