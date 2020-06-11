<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	
    <title>NatPro Compound</title>
  </head>
  <body>
  
  	<%@include file="navbarnix.html"%>
  	 
	<div id="firstDiv">
		<form action="SearchServlet" method="POST">
			<div class="card border-success">
				<div class="card-header">
			    	<a>General Information</a>
			  	</div>
			  	<div class="card-body">
			  		
					  <div class="form-group">
					    <label for="">Compound Name</label>
					    <input name="compound" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Compound Name" required>
					    <!--  <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
					  </div>
					  <div class="form-group">
					  	<a id="pubchemBT" class="btn btn-info" href="#" role="button">Autofill from PubChem</a>
					  </div>
					  <div class="form-group">
					    <label for="">IUPAC Name</label>
					    <input name="iupac" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter IUPAC Name">
					  </div>
					  <div class="form-group">
					    <label for="">Molecular Formula</label>
					    <input name="molForm" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter Molecular Formula">
					  </div>
					  <div class="form-group">
					    <label for="">Canonical SMILES</label>
					    <input name="canSMILES" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter Canonical SMILES">
					  </div>
					  <div class="form-group">
					    <label for="">InChI</label>
					    <input name="inchi" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter InChI">
					  </div>
					  <div class="form-group">
					    <label for="">InChl key</label>
					    <input name="inchikey" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter InChl key">
					  </div>
					  <div class="form-group">
					    <label for="exampleFormControlTextarea1">Compound Classes</label>
					    <textarea name="compoundClass" class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="Enter Compound Classes"></textarea>
					    <small id="exampleFormControlTextarea1" class="form-text text-muted">Enter compound classes separated by each line.</small>
					  </div>
					  <div class="form-group">
					    <label for="exampleFormControlTextarea1">Compound Synonyms</label>
					    <textarea name="synonym" class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="Enter Compound Synonyms"></textarea>
					    <small id="exampleFormControlTextarea1" class="form-text text-muted">Enter compound synonyms separated by each line.</small>
					  </div>
					  <!-- 
					  <div class="form-check">
					    <input type="checkbox" class="form-check-input" id="exampleCheck1">
					    <label class="form-check-label" for="exampleCheck1">Check me out</label>
					  </div>
					  
					  <button type="submit" class="btn btn-primary">Submit</button>
					   -->
					<!-- 
					</form>
					-->
				</div>
			</div>
			&nbsp;
			<div class="card border-success">
				<div class="card-header">
			    	Chemical Information
			  	</div>
			  	<div class="card-body">
			  		  <div class="form-row">
						  <div class="form-group col-md-4">
						  
						    <label for="">Molecular Weight</label>
						    <input name="molWeight" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Molecular Weight">
						    <!--  <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
						  </div>
						  <div class="form-group col-md-4">
						    <label for="">XLogP</label>
						    <input name="xlogp" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter XLogP" >
						  </div>
						  <div class="form-group col-md-4">
						    <label for="">Mass</label>
						    <input name="mass" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter Mass">
						  </div>
				      </div>
				      <div class="form-row">
						  <div class="form-group col-md-4">
						    <label for="">TPSA</label>
						    <input name="tpsa" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter TPSA">
						  </div>
						  <div class="form-group col-md-4">
						    <label for="">Complexity</label>
						    <input name="complexity" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter Complexity">
						  </div>
						  <div class="form-group col-md-4">
						    <label for="">Charge</label>
						    <input name="charge" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter Charge">
						  </div>
					  </div>
					  <div class="form-row">
						  <div class="form-group col-md-4">
						    <label for="">H-Bond Acceptors</label>
						    <input name="hBondDonor" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter H-Bond Acceptors">
						  </div>
						  <div class="form-group col-md-4">
						    <label for="">H-Bond Donor</label>
						    <input name="hBondAcceptor" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter H-Bond Donor">
						  </div>
						  <div class="form-group col-md-4">
						    <label for="">Rotatable Bonds</label>
						    <input name="rotBondCount" type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter Rotatable Bonds">
						  </div>
						  <!-- 
						  <div class="form-check">
						    <input type="checkbox" class="form-check-input" id="exampleCheck1">
						    <label class="form-check-label" for="exampleCheck1">Check me out</label>
						  </div>
						   -->
					  </div>
					  
				</div>
			</div>
			&nbsp;
			
			
			
			<button type="submit" class="btn btn-success btn-lg btn-block">Submit</button>
		</form>
	</div>

	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
		  'use strict';
		  window.addEventListener('load', function() {
		    // Fetch all the forms we want to apply custom Bootstrap validation styles to
		    var forms = document.getElementsByClassName('needs-validation');
		    // Loop over them and prevent submission
		    var validation = Array.prototype.filter.call(forms, function(form) {
		      form.addEventListener('submit', function(event) {
		        if (form.checkValidity() === false) {
		          event.preventDefault();
		          event.stopPropagation();
		        }
		        form.classList.add('was-validated');
		      }, false);
		    });
		  }, false);
		})();
	</script>
    
	<script type="text/javascript" src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="DataTables/datatables.min.js"></script>
    <script type="text/javascript" charset="utf8" src="js/loadingoverlay.min.js"></script>
    
    <script type="text/javascript">
	    $(document).ready(function() {
	        $('#table_id').DataTable();
	        
	        var url_string = document.location//window.location.href
	        var url = new URL(url_string);
	        //var c = url.searchParams.get("compound");
	        
	        $('input[name="compound"]').val(url.searchParams.get("compound"));
	        
	        $('input[name="pubCID"]').val(url.searchParams.get("pubCID"));
	        $('input[name="molForm"]').val(url.searchParams.get("molForm"));
	        $('input[name="canSMILES"]').val(url.searchParams.get("canSMILES"));
	        $('input[name="inchi"]').val(url.searchParams.get("inchi"));
	        $('input[name="inchikey"]').val(url.searchParams.get("inchikey"));
	        $('input[name="iupac"]').val(url.searchParams.get("iupac"));
	        
	        $('input[name="molWeight"]').val(url.searchParams.get("molWeight"));
	        $('input[name="xlogp"]').val(url.searchParams.get("xlogp"));
	        $('input[name="mass"]').val(url.searchParams.get("mass"));
	        $('input[name="tpsa"]').val(url.searchParams.get("tpsa"));
	        $('input[name="complexity"]').val(url.searchParams.get("complexity"));
	        
	        $('input[name="charge"]').val(url.searchParams.get("charge"));
	        $('input[name="hBondDonor"]').val(url.searchParams.get("hBondDonor"));
	        $('input[name="hBondAcceptor"]').val(url.searchParams.get("hBondAcceptor"));
	        $('input[name="rotBondCount"]').val(url.searchParams.get("rotBondCount"));
	        
	        //$('.hid').css('display', 'none');
	        
	        $('#pubchemBT').click(function() {
	        	var compound = $('input[name="compound"]').val();
        		$.LoadingOverlay("show");
        		$.ajax({
        			url : 'RetrieveCompoundServlet?compound='+compound+"&option=1",
        			data : {
        				userName : $('#compound').val()
        			},
        			success : function(obj) {
        				if(obj.molForm == null) {
        					$.LoadingOverlay("hide");
        					
        					alert("compound not found");
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
	    	        		
	    	        		$('textarea[name="synonym"]').val(obj.synonym);
	        				
	        				
	        				
	        				$.LoadingOverlay("hide");
	        			}
        				
        			}
        		});
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
		
		$("#search").val('${searchKey}');
		$('.hid').css('display', 'none');
		ddfunc(${searchCategory});
	</script>
    
  </body>
</html>