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
		<div class="container">
			<h1 class="display-4">Validation</h1>
			<p class="lead">Validate the gathered data from the processed files here</p>
		</div>
		
		<!-- TEMPLATE FOR THE TABLE -->
		<!--
			<div class="container">
				<form>
					<h3 style="padding-top:50px; padding-bottom:20px">Compound(s)</h3>
					<div class="row">
						<div class="col-4" style="text-align:left">
							<button type="button" class="btn btn-primary btn-sm" onclick="selectAll(0)">Select All</button>
							<button type="button" class="btn btn-success btn-sm" onclick="approveAll(0)">Approve All</button>
							<button type="button" class="btn btn-danger btn-sm" onclick="rejectAll(0)">Reject All</button>
						</div>
						<div class="w-100"></div>
					</div>
					
					<div id="(name)Group">
						<div id="(name)Entry(number)" class="form-row" style="padding-top:5px">
							<div class="col-1" style="text-align:left">
								<input class="form-check-input" type="checkbox" id="(name)Label(number)" name="(cc,cpn,ill)" value="(number)">
			  					<label class="form-check-label" for="(name)Entry(number)">Document</label>
							</div>
							<div class="col-3">
							plant
							</div>
							<div class="col-3">
							contains
							</div>
							<div class="col-3">
							chemical
							</div>
							<div class="col-2" style="text-align:right">
								<button type="button" class="btn btn-success btn-sm" onclick="approveEntry(0,0)" data-toggle="tooltip" data-placement="top" title="approve entry"><i class="fa fa-check" aria-hidden="true"></i></button>
								<button type="button" class="btn btn-danger btn-sm" onclick="rejectEntry(0,0)"><i class="fa fa-times" aria-hidden="true"></i></button>
								<button type="button" class="btn btn-primary btn-sm" onclick="viewEntry(0,0)"><i class="fa fa-eye" aria-hidden="true"></i></button>
								<button type="button" class="btn btn-secondary btn-sm" onclick="editEntry(0,0)"><i class="fa fa-pencil" aria-hidden="true"></i></button>
							</div>
						</div>
					</div>
					
					
				</form>
			</div>
		 -->
		
		<div class="container">
	
			<form>
				<h3 style="padding-top:50px; padding-bottom:20px">Compound(s)</h3>
				<div class="row">
					<div class="col-4" style="text-align:left">
						<button type="button" class="btn btn-primary btn-sm" onclick="selectAll(0)">Select All</button>
						<button type="button" class="btn btn-success btn-sm" onclick="approveAll(0)">Approve All</button>
						<button type="button" class="btn btn-danger btn-sm" onclick="rejectAll(0)">Reject All</button>
					</div>
					<div class="w-100"></div>
				</div>
				
				<div id="CompoundGroup">
					<div id="CompoundEntry0" class="form-row" style="padding-top:5px">
						<div class="col-1" style="text-align:left">
							<input class="form-check-input" type="checkbox" id="CompoundLabel0" name="cc" value="0">
		  					<label class="form-check-label" for="CompoundLabel0">Document</label>
						</div>
						<div class="col-3">
						plant
						</div>
						<div class="col-3">
						contains
						</div>
						<div class="col-3">
						chemical
						</div>
						<div class="col-2" style="text-align:right">
							<button type="button" class="btn btn-success btn-sm" onclick="approveEntry(0,0)" data-toggle="tooltip" data-placement="top" title="approve entry"><i class="fa fa-check" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-danger btn-sm" onclick="rejectEntry(0,0)"><i class="fa fa-times" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-primary btn-sm" onclick="viewEntry(0,0)"><i class="fa fa-eye" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-secondary btn-sm" onclick="editEntry(0,0)"><i class="fa fa-pencil" aria-hidden="true"></i></button>
						</div>
					</div>
				</div>
				
			</form>
			
			<form>
				<h3 style="padding-top:50px; padding-bottom:20px">Common Plant Name(s)</h3>
				<div class="row">
					<div class="col-4" style="text-align:left">
						<button type="button" class="btn btn-primary btn-sm" onclick="selectAll(1)">Select All</button>
						<button type="button" class="btn btn-success btn-sm" onclick="approveAll(1)">Approve All</button>
						<button type="button" class="btn btn-danger btn-sm" onclick="rejectAll(1)">Reject All</button>
					</div>
					<div class="w-100"></div>
				</div>
				
				<div id="CommonPlantNameGroup">
					<div id="CommonPlantNameEntry0" class="form-row" style="padding-top:5px">
						<div class="col-1" style="text-align:left">
							<input class="form-check-input" type="checkbox" id="CommonPlantNameLabel0" name="cpn" value="0">
		  					<label class="form-check-label" for="CommonPlantNameLabel0">Document</label>
						</div>
						<div class="col-3">
						name
						</div>
						<div class="col-3">
						also known as
						</div>
						<div class="col-3">
						name
						</div>
						<div class="col-2" style="text-align:right">
							<button type="button" class="btn btn-success btn-sm" onclick="approveEntry(1,0)" data-toggle="tooltip" data-placement="top" title="approve entry"><i class="fa fa-check" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-danger btn-sm" onclick="rejectEntry(1,0)"><i class="fa fa-times" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-primary btn-sm" onclick="viewEntry(1,0)"><i class="fa fa-eye" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-secondary btn-sm" onclick="editEntry(1,0)"><i class="fa fa-pencil" aria-hidden="true"></i></button>
						</div>
					</div>
				</div>
				
			</form>
			
			<form>
				<h3 style="padding-top:50px; padding-bottom:20px">Illness(es)</h3>
				<div class="row">
					<div class="col-4" style="text-align:left">
						<button type="button" class="btn btn-primary btn-sm" onclick="selectAll(2)">Select All</button>
						<button type="button" class="btn btn-success btn-sm" onclick="approveAll(2)">Approve All</button>
						<button type="button" class="btn btn-danger btn-sm" onclick="rejectAll(2)">Reject All</button>
					</div>
					<div class="w-100"></div>
				</div>
				
				<div id="IllnessGroup">
					<div id="IllnessEntry0" class="form-row" style="padding-top:5px">
						<div class="col-1" style="text-align:left">
							<input class="form-check-input" type="checkbox" id="IllnessLabel0" name="ill" value="0">
		  					<label class="form-check-label" for="IllnessLabel0">Document</label>
						</div>
						<div class="col-3">
						plant
						</div>
						<div class="col-3">
						treats
						</div>
						<div class="col-3">
						illness
						</div>
						<div class="col-2" style="text-align:right">
							<button type="button" class="btn btn-success btn-sm" onclick="approveEntry(2,0)" data-toggle="tooltip" data-placement="top" title="approve entry"><i class="fa fa-check" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-danger btn-sm" onclick="rejectEntry(2,0)"><i class="fa fa-times" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-primary btn-sm" onclick="viewEntry(2,0)"><i class="fa fa-eye" aria-hidden="true"></i></button>
							<button type="button" class="btn btn-secondary btn-sm" onclick="editEntry(2,0)"><i class="fa fa-pencil" aria-hidden="true"></i></button>
						</div>
					</div>
				</div>
				
			</form>
		</div>
	</div>
	
    
    <!-- INCLUDE FOOTER HTML -->
    <%@include file="_includeFooter.html" %>
       
    <script>
    
	    /*
	    	CHEATSHEET
	    
	    	tValue = table Value
	    	0 = cc = compound
	    	1 = cpn = common plant name
	    	2 = ill = illness
	    	
	    	cValue = counter Value (nakalimutan ko kasi kung ganito rin yung pValue)
	    */
	    
	    function selectAll(tValue) {
	    	console.log(tValue);
	    	
	    	var items;
	    	
	    	switch (tValue) {
	    	case 0:
	    		items = document.getElementsByName('cc');
	    		console.log('cc');
	    		break;
	    	case 1:
	    		items = document.getElementsByName('cpn');
	    		console.log('cpn');
	    		break;
	    	case 2:
	    		items = document.getElementsByName('ill');
	    		console.log('ill');
	    		break;
	    	}
	    	
	    	for (var i in items) {
	    		items[i].checked = true;
	    	}
	    }
	    
	    function approveAll(tValue) {
	    	console.log(tValue);
	    	
	    	var items;
	    	
	    	switch (tValue) {
	    	case 0:
	    		items = document.getElementsByName('cc');
	    		console.log('cc');
	    		break;
	    	case 1:
	    		items = document.getElementsByName('cpn');
	    		console.log('cpn');
	    		break;
	    	case 2:
	    		items = document.getElementsByName('ill');
	    		console.log('ill');
	    		break;
	    	}
	    	
	    	/*
	    		code for accepting entries
	    	*/
	    }
	    
	    function rejectAll(tValue) {
	    	console.log(tValue);
	    	
	    	var items;
	    	
	    	switch (tValue) {
	    	case 0:
	    		items = document.getElementsByName('cc');
	    		console.log('cc');
	    		break;
	    	case 1:
	    		items = document.getElementsByName('cpn');
	    		console.log('cpn');
	    		break;
	    	case 2:
	    		items = document.getElementsByName('ill');
	    		console.log('ill');
	    		break;
	    	}
	    	
	    	/*
	    		code for rejecting all entries
	    	*/
	    }
	    
	    function approveEntry(tValue,cValue) {
	    	console.log(tValue + ', ' + cValue);
	    	
	    	
	    	// finding a specific category using the tValue
	    	switch (tValue) {
	    	case 0:
	    		break;
	    	case 1:
	    		break;
	    	case 2:
	    		break;
	    	}
	    	
	    	/*
	    		code to approve the specific entry using tValue and cValue
	    	*/
	    	
	    }
	    
	    function rejectEntry(tValue,cValue) {
	    	console.log(tValue + ', ' + cValue);
	    	
	    	
	    	// finding a specific category using the tValue
	    	switch (tValue) {
	    	case 0:
	    		break;
	    	case 1:
	    		break;
	    	case 2:
	    		break;
	    	}
	    	
	    	/*
	    		code to reject the specific entry using tValue and cValue
	    	*/
	    	
	    }
	    
	    function viewEntry(tValue,cValue) {
	    	console.log(tValue + ', ' + cValue);
	    	
	    	
	    	// finding a specific category using the tValue
	    	switch (tValue) {
	    	case 0:
	    		break;
	    	case 1:
	    		break;
	    	case 2:
	    		break;
	    	}
	    	
	    	/*
	    		code to view the specific entry using tValue and cValue
	    	*/
	    	
	    }
	    
	    function editEntry(tValue,cValue) {
	    	console.log(tValue + ', ' + cValue);
	    	
	    	
	    	// finding a specific category using the tValue
	    	switch (tValue) {
	    	case 0:
	    		break;
	    	case 1:
	    		break;
	    	case 2:
	    		break;
	    	}
	    	
	    	/*
	    		code to edit the specific entry using tValue and cValue
	    	*/
	    	
	    }
    </script>
	
    
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