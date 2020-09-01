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
	<div class="jumbotron jumbotron-fluid" style="text-align: center"
		id="uploadForm">
		<div class="container">
			<h1 class="display-4">Upload</h1>
			<p class="lead">Upload your document/s here</p>
			<form action="/NatPro/SampleServlet" method="GET"
				enctype="multipart/form-data" autocomplete="off">
				<div class="input-group">
					<div class="custom-file">
						<input type="file" class="custom-file-input" name="file-upload"
							id="inputGroupFile" multiple accept=".txt,.pdf" /> <label
							class="custom-file-label" for="inputGroupFile">Choose
							file/s</label>
					</div>
					<div class="input-group-append">
						<button class="btn btn-success" type="Submit">Upload</button>
					</div>
				</div>
			</form>


			<div aria-live="polite" aria-atomic="true"
				style="position: relative; min-height: 200px;" hidden>
				<!-- Position it -->
				<div style="position: absolute; top: 0; right: 0;">

					<!-- Then put toasts within -->
					<div class="toast" role="alert" aria-live="assertive"
						aria-atomic="true">
						<div class="toast-header">
							<img src="..." class="rounded mr-2" alt="..."> <strong
								class="mr-auto">Bootstrap</strong> <small class="text-muted">just
								now</small>
							<button type="button" class="ml-2 mb-1 close"
								data-dismiss="toast" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="toast-body">See? Just like this.</div>
					</div>

					<div class="toast" role="alert" aria-live="assertive"
						aria-atomic="true">
						<div class="toast-header">
							<img src="..." class="rounded mr-2" alt="..."> <strong
								class="mr-auto">Bootstrap</strong> <small class="text-muted">2
								seconds ago</small>
							<button type="button" class="ml-2 mb-1 close"
								data-dismiss="toast" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="toast-body">Heads up, toasts will stack
							automatically</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	<div id="progressView" style="display: none;">
		<div class="jumbotron bg-success">
			<div class="d-flex justify-content-center">
				<h1 class="display-4 text-white text-center" id="header">Processing
					Document${numdocsplural}</h1>

				<div class="spinner-grow text-light mt-2 ml-4"
					style="width: 3rem; height: 3rem;" role="status" id="spinner">
				</div>
			</div>
		</div>
		<h1 class="display-6 text-dark text-center">Document${numdocsplural}:</h1>
		<c:forEach items="${filenamelist}" var="filenameList">
			<p class="display-6 text-dark text-center">${filenameList}</p>
		</c:forEach>
		<h4 class="text-dark">Preprocess:</h4>
		${preprocProg}
		<div class="container-fluid mt-3">
			<div class="row">
				<div class="col">
					<button id="but1" type="button" class="btn btn-secondary">Generate
						Unique Document ID${numdocsplural}</button>

				</div>

				<div class="col">
					<button id="but2" type="button" class="btn btn-secondary">Check
						Document${numdocsplural} for Duplicate</button>
				</div>
				<div class="col">
					<button id="but3" type="button" class="btn btn-secondary">Save
						Document${numdocsplural}</button>
				</div>

				<div class="col">
					<button id="but4" type="button" class="btn btn-secondary">Convert
						Document${numdocsplural} to Text File</button>
				</div>

				<div class="col">
					<button id="but5" type="button" class="btn btn-secondary">Clean
						the Text File${numdocsplural}</button>
				</div>

				<div class="col">
					<button id="but6" type="button" class="btn btn-secondary">Split
						Sentences</button>
				</div>

				<div class="col">
					<button id="but7" type="button" class="btn btn-secondary">Tag
						the Document${numdocsplural}</button>
				</div>

				<div class="col">
					<button id="but8" type="button" class="btn btn-secondary">Finish
						Preprocessing</button>
				</div>
			</div>
		</div>
		<br>
		<h4 class="text-dark">Bootstrap:</h4>
		<div class="progress" style="height: 30px;">
			<div
				class="progress-bar progress-bar-striped progress-bar-animated bg-success"
				id="bar2" role="progressbar" style="width: 0%;" aria-valuenow="25"
				aria-valuemin="0" aria-valuemax="100">BOOTSRAPPING
				DOCUMENT${numdocsplural}</div>
		</div>

		<div class="container-fluid mt-3">
			<div class="row justify-content-around">
				<div class="col">
					<button id="but11" type="button" class="btn btn-secondary">Load
						Tagged Document</button>
				</div>
				<div class="col">
					<button id="but22" type="button" class="btn btn-secondary">Load
						Seeds</button>
				</div>
				<div class="col">
					<button id="but33" type="button" class="btn btn-secondary">Process
						Tagged Document</button>
				</div>
				<div class="col">
					<button id="but44" type="button" class="btn btn-secondary">Generate
						Patterns from Document</button>
				</div>
				<div class="col">
					<button id="but55" type="button" class="btn btn-secondary">POS
						Tag Document</button>
				</div>
				<div class="col">
					<button id="but66" type="button" class="btn btn-secondary">Match
						Patterns with Document</button>
				</div>
				<div class="col">
					<button id="but77" type="button" class="btn btn-secondary">Add
						Seed Patterns</button>
				</div>
				<div class="col">
					<button id="but88" type="button" class="btn btn-secondary">Generate
						File for Validation</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>

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