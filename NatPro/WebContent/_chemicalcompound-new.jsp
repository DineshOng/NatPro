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
	
	<!-- CONTENT HERE -->
	<div class="jumbotron bg-success" style="text-align:center">
		<h1 class="display-4 text-white">
			<p id="chemCompoundName">*insert code here*</p>
		</h1>
		<button type="button" class="btn btn-outline-light btn-sm h-25 mt-3" onclick="filloutPubChemInfo()" data-toggle="tooltip" data-placement="top" title="get information from PubChem" class="btn btn-primary" id="pubchemFilloutInfoBtn">
			Fill Out Information from PubChem
		</button>
		<input class="form-control w-auto h-25 mt-3" type="text" id="editChemCompoundField" name="editChemCompoundName" style="display: none" required>
		<button type="button" class="btn btn-outline-light btn-sm h-25 mt-3" onclick="editChemCompound()" data-toggle="tooltip" data-placement="top" title="edit entry" class="btn btn-primary" id="editChemCompoundBtn">
			<i class="fa fa-pencil" aria-hidden="true" id="editChemCompoundLogo"></i>
		</button>
		<button type="button" class="btn btn-outline-danger btn-sm h-25 mt-3" onclick="cancelEditChemCompound()" data-toggle="tooltip" data-placement="top" title="edit entry" class="btn btn-primary" id="cancelEditChemCompoundBtn" style="display:none">
			<i class="fa fa-times" aria-hidden="true"></i>
		</button>
		<h3 class="text-white text-center">Chemical Compound</h3>
	</div>
	
	<div class="d-flex flex-row list-group text-center">
		<a class="list-group-item list-group-item-action list-group-item-success active" id="list-geninfo-list" data-toggle="list" href="#genInfo" role="tab" aria-controls="GeneralInformation">General Information</a>
		<a class="list-group-item list-group-item-action list-group-item-success" id="list-cheminfo-list" data-toggle="list" href="#chemInfo" role="tab" aria-controls="ChemicalInformation">Chemical Information</a>
		<a class="list-group-item list-group-item-action list-group-item-success" id="list-compsynonym-list" data-toggle="list" href="#compSynonyms" role="tab" aria-controls="CompoundSynonyms">Compound Synonyms</a>
		<a class="list-group-item list-group-item-action list-group-item-success" id="list-bioact-list" data-toggle="list" href="#bioActivities" role="tab" aria-controls="BiologicalActivities">Biological Activities</a>
	</div>
	
	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="genInfo" role="tabpanel" aria-labelledby="list-geninfo-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25">
					<thead>
						<tr>
							<th colspan="2">
								<h4 class="text-center">General Information</h4>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								Common Name
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								IUPAC Name
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								Canonical SMILES
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								Formula
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								InChl
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								InChl key
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								Chemical Class
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								PubChem CID
							</th>
							<td>
								
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	
		<div class="tab-pane fade show active" id="chemInfo" role="tabpanel" aria-labelledby="list-cheminfo-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25">
					<thead>
						<tr>
							<th colspan="2">
								<h4 class="text-center">Chemical Information</h4>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								Molecular Weight
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								XLogP
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								Mass
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								TPSA
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								Complexity
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								Charge
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								H-Bond Acceptors
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								H-Bond Donor
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								Rotatable Bonds
							</th>
							<td>
								
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="tab-pane fade show active" id="compSynonyms" role="tabpanel" aria-labelledby="list-compsynonym-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25">
					<thead>
						<tr>
							<th>Compound Synonyms</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="tab-pane fade show active" id="bioActivities" role="tabpanel" aria-labelledby="list-bioact-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25">
					<thead>
						<tr>
							<th>Biological Activities</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
						</tr>
					</tbody>
				</table>
			</div>
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