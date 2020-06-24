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

<title>NatPro Advanced Search</title>
</head>
<body>

	<%@include file="navbarnix.html"%>

	<!--  INSERT HTML CODE -->
	<div id="adSearchDiv">
		<form action="AdvancedSearchCompoundServlet" method="POST">
			<div class="form-row">
				<div class="form-group col-md-2">
					<label for="">Location</label> 
					<input
						name="location" type="text"
						class="form-control" id="compound" aria-describedby="emailHelp"
						placeholder="">
				</div>

				<div class="form-group col-md-2">
					<label for="">Family</label> 
					
					<input
						name="family" type="text" class="form-control"
						id="exampleInputPassword1" placeholder="">
				</div>
				
				<div class="form-group col-md-2">
					<label for="">Genus</label> 
					
					<input
						name="genus" type="text" class="form-control"
						id="exampleInputPassword1" placeholder="">
				</div>
				
				<div class="form-group col-md-2">
					<label for="">Species</label> 
					
					<input
						name="species" type="text" class="form-control"
						id="exampleInputPassword1" placeholder="">
				</div>
				
				<div class="form-group col-md-2">
					<label for="">Plant Part</label> 
					
					<input
						name="plantPart" type="text" class="form-control"
						id="exampleInputPassword1" placeholder="">
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-2">
					<label for="">Illness</label> 
					<input
						name="illness" type="text"
						class="form-control" aria-describedby="emailHelp"
						placeholder="">
				</div>

				<div class="form-group col-md-2">
					<label for="">Biological Activity</label> 
					
					<input
						name="bioAct" type="text" class="form-control"
						id="exampleInputPassword1" placeholder="">
				</div>
				
				<div class="form-group col-md-2">
					<label for="">Cell Line</label> 
					
					<input
						name="cellLine" type="text" class="form-control"
						id="exampleInputPassword1" placeholder="">
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-2">
					<label for="">Compound Name</label> 
					<input
						name="compound" type="text"
						class="form-control" id="compound" aria-describedby="emailHelp"
						placeholder="">
				</div>

				<div class="form-group col-md-2">
					<label for="">Molecular Formula</label> 
					
					<input
						pattern="(([A-Z][a-z]?)([1-9]0?)*)+" title="ex. H20, NaCl"
						name="molForm" type="text" class="form-control"
						id="exampleInputPassword1" placeholder="">
				</div>
				
				<div class="form-group col-md-2">
					<label for="">Compound Classification</label> 
					
					<input
						name="compoundClass" type="text" class="form-control"
						id="exampleInputPassword1" placeholder="">
				</div>
			</div>
			
			<div class="form-row align-items-center">
			
				<div class="col-auto">
			      <div class="input-group mb-2">
			        <input name="molWeight_lhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			        <div class="input-group-append">
			          <div class="input-group-text">&le;</div>
			        </div>
			      </div>
			    </div>
			    
			    <div class="col-md-2 text-center">
			        <label>Molecular Weight</label>
			    </div>
			    
			   <div class="col-auto" style="margin-right: 8px">
			      <div class="input-group mb-2">
			       	<div class="input-group-prepend">
			          <div class="input-group-text">&le;</div>
			        </div>
			        <input name="molWeight_rhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			      </div>
			    </div>
			    
			    
			    <div class="col-auto">
			      <div class="input-group mb-2">
			        <input name="xlogp_lhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			        <div class="input-group-append">
			          <div class="input-group-text">&le;</div>
			        </div>
			      </div>
			    </div>
			    
			    <div class="col-md-2 text-center">
			        <label>XLogP</label>
			    </div>
			    
			    <div class="col-auto">
			      <div class="input-group mb-2">
			       	<div class="input-group-prepend">
			          <div class="input-group-text">&le;</div>
			        </div>
			        <input name="xlogp_rhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			      </div>
			    </div>
			    
			</div>
			
			<div class="form-row align-items-center">
				<div class="col-auto">
			      <div class="input-group mb-2">
			        <input name="mass_lhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			        <div class="input-group-append">
			          <div class="input-group-text">&le;</div>
			        </div>
			      </div>
			    </div>
			    
			    <div class="col-md-2 text-center">
			        <label>Mass</label>
			    </div>
			    
			    <div class="col-auto" style="margin-right: 8px">
			      <div class="input-group mb-2">
			       	<div class="input-group-prepend">
			          <div class="input-group-text">&le;</div>
			        </div>
			        <input name="mass_rhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			      </div>
			    </div>
			    
			    <div class="col-auto">
			      <div class="input-group mb-2">
			        <input name="tpsa_lhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			        <div class="input-group-append">
			          <div class="input-group-text">&le;</div>
			        </div>
			      </div>
			    </div>
			    
			    <div class="col-md-2 text-center">
			        <label>TPSA</label>
			    </div>
			    
			    <div class="col-auto">
			      <div class="input-group mb-2">
			       	<div class="input-group-prepend">
			          <div class="input-group-text">&le;</div>
			        </div>
			        <input name="tpsa_rhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			      </div>
			    </div>
			    
			</div>
			
			<div class="form-row align-items-center">
				<div class="col-auto">
			      <div class="input-group mb-2">
			        <input name="complexity_lhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			        <div class="input-group-append">
			          <div class="input-group-text">&le;</div>
			        </div>
			      </div>
			    </div>
			    
			    <div class="col-md-2 text-center">
			        <label>Complexity</label>
			    </div>
			    
			    <div class="col-auto" style="margin-right: 8px">
			      <div class="input-group mb-2">
			       	<div class="input-group-prepend">
			          <div class="input-group-text">&le;</div>
			        </div>
			        <input name="complexity_rhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			      </div>
			    </div>
			    
			    <div class="col-auto">
			      <div class="input-group mb-2">
			        <input name="charge_lhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			        <div class="input-group-append">
			          <div class="input-group-text">&le;</div>
			        </div>
			      </div>
			    </div>
			    
			    <div class="col-md-2 text-center">
			        <label>Charge</label>
			    </div>
			    
			    <div class="col-auto">
			      <div class="input-group mb-2">
			       	<div class="input-group-prepend">
			          <div class="input-group-text">&le;</div>
			        </div>
			        <input name=charge_rhs type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			      </div>
			    </div>
			</div>
			
			<div class="form-row align-items-center">
				<div class="col-auto">
			      <div class="input-group mb-2">
			        <input name="hBondAcceptor_lhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			        <div class="input-group-append">
			          <div class="input-group-text">&le;</div>
			        </div>
			      </div>
			    </div>
			    
			    <div class="col-md-2 text-center">
			        <label>H-Bond Acceptors</label>
			    </div>
			    
			    <div class="col-auto" style="margin-right: 8px">
			      <div class="input-group mb-2">
			       	<div class="input-group-prepend">
			          <div class="input-group-text">&le;</div>
			        </div>
			        <input name="hBondAcceptor_rhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			      </div>
			    </div>
			    
			    <div class="col-auto">
			      <div class="input-group mb-2">
			        <input name="hBondDonor_lhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			        <div class="input-group-append">
			          <div class="input-group-text">&le;</div>
			        </div>
			      </div>
			    </div>
			    
			    <div class="col-md-2 text-center">
			        <label>H-Bond Donors</label>
			    </div>
			    
			    <div class="col-auto">
			      <div class="input-group mb-2">
			       	<div class="input-group-prepend">
			          <div class="input-group-text">&le;</div>
			        </div>
			        <input name="hBondDonor_rhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			      </div>
			    </div>
			</div>
			
			<div class="form-row align-items-center">
				<div class="col-auto">
			      <div class="input-group mb-2">
			        <input name="rotBondCount_lhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			        <div class="input-group-append">
			          <div class="input-group-text">&le;</div>
			        </div>
			      </div>
			    </div>
			    
			    <div class="col-md-2 text-center">
			        <label>Rotatable Bonds</label>
			    </div>
			    
			    <div class="col-auto">
			      <div class="input-group mb-2">
			       	<div class="input-group-prepend">
			          <div class="input-group-text">&le;</div>
			        </div>
			        <input name="rotBondCount_rhs" type="text" pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" type="text" class="form-control" id="inlineFormInputGroup" placeholder="">
			      </div>
			    </div>
			</div>
			&nbsp;
			<div class="form-row">
				<div class="col-auto">
					<button class="btn btn-primary " type="submit" >
						Search compound
						<i class="fa fa-flask" aria-hidden="true"></i>
					</button>
				</div>
				<div class="col-auto">
					<button class="btn btn-success " type="button" >
						Search plant
						<i class="fa fa-leaf" aria-hidden="true"></i>
						</button>
				</div>
			</div>
		</form>
	</div>


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