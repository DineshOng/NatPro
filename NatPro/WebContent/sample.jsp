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

<title>NatPro : ${searchKey}</title>
</head>
<body>

	<%@include file="navbarnix.html"%>
	<!-- HTML CODE -->
	<form>
		<label id="fname1" for="fname">EDIT THIS </label><input type="text"
			id="fname" name="fname" style="display: none"> <input
			type="button" value="EDIT" onclick="edit()">
	</form>
	<!-- 	
	<input type="text" id="lname" name="lname"> -->
	<script type="text/javascript"
		src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>

	<script>
	function edit(){
		document.getElementById("fname").style.display="block";
		document.getElementById("fname").value = document.getElementById("fname1").innerHTML;
		var element = document.getElementById("fname1");
	    element.parentNode.removeChild(element);
		/* document.getElementById("fname1").style.display="none"; */
	}
	</script>

	<!-- 	<script>
	$( document ).ready(function() {
		$.ajax({
			type : "GET",
			url : 'SampleServlet',
			dataType: "text",
			success : function(data) {
			document.getElementById("header").innerHTML = "Document Upload Successful!";
				console.log(data);
			}
			});
	});

	</script> -->
	<script>
	function ajaxAsyncRequest(){
/*  		alert("Please wait while processing data");  */
		document.getElementById("uploadForm").style.display = "none";  
		document.getElementById("progressView").style.display = "block";  
		$.ajax({
			type : "GET",
			url : 'SampleServlet',
			dataType: "text",
			success : function(data) {
				data;
				/* document.getElementById("bar2").style.width = data+"%"; */
			/* 	document.getElementById("bar2").style.width = "100%"; */
			/* uploadProgress(); */
				console.log(data);
			}
			});
	}
	</script>

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