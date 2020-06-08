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
	
    <title>NatPro Upload</title>
  </head>
  <body>
    <nav class="navbar navbar-default navbar-expand-lg navbar-light">
	<div class="navbar-header d-flex col">
		<a class="navbar-brand" href="#">Nat<b>Pro</b></a>  		
		<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle navbar-toggler ml-auto">
			<span class="navbar-toggler-icon"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
	</div>
	<!-- Collection of nav links, forms, and other content for toggling -->
	<div id="navbarCollapse" class="collapse navbar-collapse justify-content-start">
		<ul class="nav navbar-nav">
			<li class="nav-item"><a href="#" class="nav-link">Home</a></li>
			
			<li class="nav-item dropdown">
				<a id="ddtext" data-toggle="dropdown" class="nav-link dropdown-toggle" href="#">Choose <b class="caret"></b></a>
				<ul class="dropdown-menu">					
					<li><a onclick="ddfunc(1)" href="#" class="dropdown-item">Plant Name</a></li>
					<li><a onclick="ddfunc(2)" href="#" class="dropdown-item">Scientific Name</a></li>
					<li><a onclick="ddfunc(3)" href="#" class="dropdown-item">Genus</a></li>
					<li><a onclick="ddfunc(4)" href="#" class="dropdown-item">Family</a></li>
					<li><a onclick="ddfunc(5)" href="#" class="dropdown-item">Compound</a></li>
					<li><a onclick="ddfunc(6)" href="#" class="dropdown-item">Location</a></li>
					<li><a onclick="ddfunc(7)" href="#" class="dropdown-item">Biological Activites</a></li>
				</ul>
			</li>
			<li>
            	<form action="SearchServlet" method="POST">
                	<div class="input-group search-box">		
                								
                    	<input type="text" id="search" class="form-control" name="searchKey" placeholder="Search here...">
                    	<input type="hidden" name="searchCategory" value=1>
						<span class="input-group-btn">
							<button type="button" class="btn btn-primary"><i class="fa fa-search"></i></button>
						</span>
						
                   	</div>
               	</form>                        
			</li>
			<li class="nav-item"><a href="upload.jsp" class="nav-link">Upload</a></li>
			<li class="nav-item"><a href="#" class="nav-link">Add</a></li>
			<li class="nav-item"><a href="#" class="nav-link">Validation</a></li>
		</ul>		
		<ul class="nav navbar-nav navbar-right ml-auto">			
			<li class="nav-item dropdown">
				<a data-toggle="dropdown" class="nav-link dropdown-toggle" href="#"><i class="fa fa-search"></i></a>
				<a data-toggle="dropdown" class="nav-link dropdown-toggle hide" href="#"><i class="fa fa-close"></i></a>
				<ul class="dropdown-menu">
					<li>
                        <form>
                            <div class="input-group search-box">								
                                <input type="text" id="search" class="form-control" placeholder="Search here...">
								<span class="input-group-btn">
									<button type="button" class="btn btn-primary"><i class="fa fa-search"></i></button>
								</span>
                            </div>
                        </form>                        
					</li>
				</ul>
			</li>
			<li class="nav-item"><a href="#" class="nav-link"><i class="fa fa-facebook"></i></a></li>
			<li class="nav-item"><a href="#" class="nav-link"><i class="fa fa-twitter"></i></a></li>
			<li class="nav-item"><a href="#" class="nav-link"><i class="fa fa-google-plus"></i></a></li>
			<li class="nav-item"><a href="#" class="nav-link"><i class="fa fa-pinterest-p"></i></a></li>
		</ul>
	</div>
</nav>
    
    <!-- INSERT CODE ---------------------------------------------------------------------------------------------------------------------------->
    
	<script type="text/javascript" src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="DataTables/datatables.min.js"></script>
    
    <script type="text/javascript">
	    $(document).ready(function() {
	        $('#table_id').DataTable();
	    } );
	    
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