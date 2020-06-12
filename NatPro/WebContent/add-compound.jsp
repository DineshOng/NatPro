<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>NatPro Compound</title>
</head>
<body>

	<%@include file="navbarnix.html"%>

	<div id="firstDiv">
	
	<h3 class="card-title" >
						<b id="cardTitle">Edit Compound: ${compound.getCompoundNameHTML()}</b>
					</h3>

					<div class="mb-4">

						<!-- Solid divider -->
						<hr class="solid">
					</div>
	
		<form id="compoundForm" action="SaveCompoundServlet" method="POST">
			<div class="card border-success">
				<div class="card-body">
					<div id="compoundAlertFail" class="alert alert-danger" role="alert">
						<a>Compound not found!</a>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    						<span aria-hidden="true">&times;</span>
  						</button>
  					</div>
  					<div id="compoundAlertSuccess" class="alert alert-success" role="alert">
						<a>Compound found!</a>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    						<span aria-hidden="true">&times;</span>
  						</button>
  					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="">Compound Name</label> 
							
							<input
								onchange="checkIfNull()" name="compound" type="text"
								class="form-control" id="compound" aria-describedby="emailHelp"
								placeholder="Enter Compound Name" value="${compound.getCompoundName()}" required> 
								
							<small
								id="compoundHelp" class="form-text text-muted">Try
								clicking the <a href="#pubchemBT">PubChem button</a> on the
								bottom of the page to fill the other information.
							</small>
						</div>

						<div class="form-group col-md-6">
							<label for="">Molecular Formula</label> 
							
							<input
								pattern="(([A-Z][a-z]?)([1-9]0?)*)+" title="ex. H20, NaCl"
								name="molForm" type="text" class="form-control"
								id="exampleInputPassword1" placeholder="Enter Molecular Formula" value="${compound.getMolForm()}">
						</div>

					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="">IUPAC Name</label> <input name="iupac" type="text"
								class="form-control" id="exampleInputPassword1"
								placeholder="Enter IUPAC Name" value="${compound.getIupac()}">
						</div>

						<div class="form-group col-md-6">
							<label for="">Canonical SMILES</label> <input name="canSMILES"
								type="text" class="form-control" id="exampleInputPassword1"
								placeholder="Enter Canonical SMILES" value="${compound.getCanSMILES()}">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="">InChI</label> <input name="inchi" type="text"
								class="form-control" id="exampleInputPassword1"
								placeholder="Enter InChI" value="${compound.getInchi()}">
						</div>
						<div class="form-group col-md-6">
							<label for="">InChl key</label> <input name="inchikey"
								type="text" class="form-control" id="exampleInputPassword1"
								placeholder="Enter InChl key" value="${compound.getInchikey()}">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="exampleFormControlTextarea1">Compound Classes</label>
							<textarea name="compoundClass" class="form-control"
								id="exampleFormControlTextarea1" rows="3"
								placeholder="Enter Compound Classes"></textarea>
							<small id="exampleFormControlTextarea1"
								class="form-text text-muted">Enter compound classes
								separated by each line.</small>
						</div>
						
						<div class="form-group col-md-6">
							<label for="exampleFormControlTextarea1">Compound Synonyms</label>
							
							<textarea name="synonym" class="form-control"
								id="exampleFormControlTextarea1" rows="3"
								placeholder="Enter Compound Synonyms">${compound.getAllCompoundSynonymsHTML()}</textarea>
								
								
							<small id="exampleFormControlTextarea1"
								class="form-text text-muted">Enter compound synonyms
								separated by each line.</small>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-4">

							<label for="">Molecular Weight</label> <input
								pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" title="number only"
								name="molWeight" type="text" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp"
								placeholder="Enter Molecular Weight" value="${compound.getMolWeight()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">XLogP</label> <input
								pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" title="number only"
								name="xlogp" type="text" class="form-control"
								id="exampleInputPassword1" placeholder="Enter XLogP" value="${compound.getXlogp()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">Mass</label> <input
								pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" title="number only"
								name="mass" type="text" class="form-control"
								id="exampleInputPassword1" placeholder="Enter Mass" value="${compound.getMass()}">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-4">
							<label for="">TPSA</label> <input
								pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" title="number only"
								name="tpsa" type="text" class="form-control"
								id="exampleInputPassword1" placeholder="Enter TPSA" value="${compound.getTpsa()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">Complexity</label> <input
								pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" title="number only"
								name="complexity" type="text" class="form-control"
								id="exampleInputPassword1" placeholder="Enter Complexity" value="${compound.getComplexity()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">Charge</label> <input
								pattern="^-?([1-9][0-9]*)+|0$" title="integer only"
								name="charge" type="text" class="form-control"
								id="exampleInputPassword1" placeholder="Enter Charge" value="${compound.getCharge()}">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-4">
							<label for="">H-Bond Acceptors</label> <input
								pattern="^-?([1-9][0-9]*)+|0$" title="integer only"
								name="hBondDonor" type="text" class="form-control"
								id="exampleInputPassword1" placeholder="Enter H-Bond Acceptors" value="${compound.getHBondAcceptor()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">H-Bond Donor</label> <input
								pattern="^-?([1-9][0-9]*)+|0$" title="integer only"
								name="hBondAcceptor" type="text" class="form-control"
								id="exampleInputPassword1" placeholder="Enter H-Bond Donor" value="${compound.getHBondDonor()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">Rotatable Bonds</label> <input
								pattern="^-?([1-9][0-9]*)+|0$" title="integer only"
								name="rotBondCount" type="text" class="form-control"
								id="exampleInputPassword1" placeholder="Enter Rotatable Bonds" value="${compound.getRotBondCount()}">
						</div>
					</div>
					<div class="form-row">
						<a id="pubchemBT" class="btn btn-info" href="#" role="button">Auto-fill
							from PubChem</a>
					</div>
					&nbsp; 
					<input type="hidden" name="oldCompound" value="${compound.getCompoundName()}">
					<input type="hidden" name="pubCID" value="${compound.getPubCID()}">
					<div class="form-row">
						<button id="subBT" type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript"
		src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>
	<script type="text/javascript" charset="utf8"
		src="js/loadingoverlay.min.js"></script>

	<script type="text/javascript">
	$("#compoundAlertFail").hide();
	$("#compoundAlertSuccess").hide();
	
	    $(document).ready(function() {
	    	$("#compoundHelp").css("display", "none");
	        $('#table_id').DataTable();
	        
	        var action = '${action}';
			if(action === "") {
				$("#compoundForm").attr("action", "AddCompoundServlet");
			}
			
			var cc = '${compound.getCompoundName()}';
			if(cc === "") {
				$("#cardTitle").html("Add New Compound");
			}
	        
	        $('#pubchemBT').click(function() {
	        	//$("#compound").checkValidity();
	       	 	$("#compoundAlertFail").hide();
		       	$("#compoundAlertSuccess").hide();
	        	var compound = $('input[name="compound"]').val();
	        	if(compound != "") {
	        		$.LoadingOverlay("show");
	        		$.ajax({
	        			url : 'RetrieveCompoundServlet?compound='+compound+"&option=1",
	        			data : {
	        				userName : $('#compound').val()
	        			},
	        			success : function(obj) {
	        				if(obj.molForm == null) {
	        					$.LoadingOverlay("hide");
	        					
	        					//alert("compound not found");
	        					$("#compoundAlertFail").show();
	        				} else {
	        			
		        				
		        				//$('input[name="compound"]').val($("#compound").text());
		    	        		
		    	        		$('input[name="pubCID"]').val(obj.pubCID);
		    	        		$('input[name="molForm"]').val(obj.molForm);
		    	        		$('input[name="canSMILES"]').val(obj.canSMILES);
		    	        		$('input[name="inchi"]').val(obj.inchi);
		    	        		$('input[name="inchikey"]').val(obj.inchikey);
		    	        		$('input[name="iupac"]').val(obj.iupac);
		    	        		
		    	        		$('input[name="molWeight"]').val(obj.molWeight);
		    	        		$('input[name="xlogp"]').val(obj.xlogp);
		    	        		$('input[name="mass"]').val(obj.mass);
		    	        		$('input[name="tpsa"]').val(obj.tpsa);
		    	        		$('input[name="complexity"]').val(obj.complexity);
		    	        		
		    	        		$('input[name="charge"]').val(obj.charge);
		    	        		$('input[name="hBondDonor"]').val(obj.hBondDonor);
		    	        		$('input[name="hBondAcceptor"]').val(obj.hBondAcceptor);
		    	        		$('input[name="rotBondCount"]').val(obj.rotBondCount);
		    	        		
		    	        		$('textarea[name="synonym"]').val($('textarea[name="synonym"]').val()+"\n"+obj.synonym);
		        				
		        				
		        				
		        				$.LoadingOverlay("hide");
		        				$("#compoundAlertFail").hide();
		        				$("#compoundAlertSuccess").show();
		        			}
	        				
	        			}
	        		});
	        	} else {
	        		
	        		$("#subBT").click();
	        	}
	        });
	        
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
		
		
		function checkIfNull(){
			var cc = $("#compound").val();
			if(cc!=""){
				$("#compoundHelp").css("display", "block");
			} else {
				$("#compoundHelp").css("display", "none");
			}
		}
		
		$("#search").val('${searchKey}');
		$('.hid').css('display', 'none');
		
		
		
		ddfunc('${searchCategory}');
	</script>

</body>
</html>