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
	<div class="jumbotron jumbotron-fluid" style="text-align: center">
		<div class="container">
			<h1 class="display-4">Upload</h1>
			<p class="lead">Upload your document/s here</p>
			<form action="/NatPro/uploadSERVLET" method="POST"
				enctype="multipart/form-data" autocomplete="off">
				<div class="input-group">
					<div class="custom-file">
						<input type="file" class="custom-file-input" name="file-upload" id="inputGroupFile"
							multiple accept=".txt,.pdf"/> <label class="custom-file-label"
							for="inputGroupFile">Choose file</label>
					</div>
					<div class="input-group-append">
						<button class="btn btn-success">Upload</button>
					</div>
				</div>
			</form>
			<div class="progress">
				<div
					class="progress-bar progress-bar-striped progress-bar-animated bg-success"
					role="progressbar" aria-valuenow="75" aria-valuemin="0"
					aria-valuemax="100" style="width: 100%" hidden></div>
			</div>

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

	<script type="text/javascript">

    $('.custom-file input').change(function (e) {
        var files = [];
        for (var i = 0; i < $(this)[0].files.length; i++) {
            files.push($(this)[0].files[i].name);
        }
        $(this).next('.custom-file-label').html(files.join(', '));
    });

	</script>

</body>
</html>