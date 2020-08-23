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
		
		<!-- TEMPLATE
			<div id="">
				<h3 style="padding-top:50px; padding-bottom:20px">Compound(s)</h3>
				
				<table class="table table-hover">
					<thead>
						<tr>
							<td colspan="5" style="text-align: left">
								<button id="" type="button" class="btn btn-primary btn-sm">SELECT ALL</button>
								<button id="" type="button" class="btn btn-primary btn-sm">APPROVE ALL</button>
								<button id="" type="button" class="btn btn-primary btn-sm">REJECT ALL</button>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="text-align: left">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="" value="">
									<label class="form-check-label" for="">Document</label>
								</div>
							</td>
							<td>
								plant
							</td>
							<td>
								contains
							</td>
							<td>
								chemical
							</td>
							<td style="text-align:right; width:300px">
								<button id="" type="button" class="btn btn-primary btn-sm">APPROVE</button>
								<button id="" type="button" class="btn btn-primary btn-sm">REJECT</button>
								<button id="" type="button" class="btn btn-primary btn-sm">VIEW</button>
								<button id="" type="button" class="btn btn-primary btn-sm">EDIT</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		 -->
		
		<div class="container">
			
			<div id="CompoundGroup">
				<h3 style="padding-top:50px; padding-bottom:20px">Compound(s)</h3>
				
				<table class="table table-hover">
					<thead>
						<tr>
							<td colspan="5" style="text-align: left">
								<button id="" type="button" class="btn btn-primary btn-sm">SELECT ALL</button>
								<button id="" type="button" class="btn btn-primary btn-sm">APPROVE ALL</button>
								<button id="" type="button" class="btn btn-primary btn-sm">REJECT ALL</button>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="text-align: left">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="" value="">
									<label class="form-check-label" for="">Document</label>
								</div>
							</td>
							<td>
								plant
							</td>
							<td>
								contains
							</td>
							<td>
								chemical
							</td>
							<td style="text-align:right; width:300px">
								<button id="" type="button" class="btn btn-primary btn-sm">APPROVE</button>
								<button id="" type="button" class="btn btn-primary btn-sm">REJECT</button>
								<button id="" type="button" class="btn btn-primary btn-sm">VIEW</button>
								<button id="" type="button" class="btn btn-primary btn-sm">EDIT</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
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