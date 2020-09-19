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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<title>NatPro : ${searchKey}</title>
</head>
<body>
	<!-- INCLUDE NAV BAR HTML -->
	<%@include file="navbarnix.html"%>
	<h1>${i}</h1>
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
	<div class="progress" style="height: 30px;">
		<div
			class="progress-bar bg-success progress-bar-striped
			progress-bar-animated"
			role="progressbar" id="bar" style="width: 0%" aria-valuenow="25"
			aria-valuemin="0" aria-valuemax="100"></div>

	</div>
	<span id="currentProcess"></span>
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

	<div id="preproc"></div>



	<p hidden id="startTime" val="${startTime} "></p>
	<p hidden id="genDocIdDuration" val="${genDocIdDuration} "></p>
	<p hidden id="checkDocDuration" val="${checkDocDuration} "></p>
	<p hidden id="saveDocDuration" val="${saveDocDuration}"></p>
	<p hidden id="preprocDuration" val="${preprocDuration}"></p>
	<p hidden id="endTime" val="${endTime}"></p>
	<p hidden id="numdocsplural" val="${numdocsplural}"></p>
	<!-- INCLUDE FOOTER HTML -->
	<%@include file="_includeFooter.html"%>


	<script type="text/javascript"
		src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>

	<script type="text/javascript">
	$(document).ready(function() {
        var progressBar = $("#bar");
        var progressBar2 = $("#bar2");
		var s = document.getElementById("numdocsplural").getAttribute("val");
        var isare = "is";
        if(s == "s"){
        	isare="are";
        }
        
		// Progress
		$.LoadingOverlay("show", {
			background: 'rgba(255, 255, 255, 0.6)',
			progress    : true,
			progressColor:"#35b557",
			textResizeFactor: 0.3,
			text: " ",
		    image       : '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1000 1000">'+
		    '<circle r="80" cx="500" cy="90"/>'+
		    '<circle r="80" cx="500" cy="910"/>'+
		    '<circle r="80" cx="90" cy="500"/>'+
		    '<circle r="80" cx="910" cy="500"/>'+
		    '<circle r="80" cx="212" cy="212"/>'+
		    '<circle r="80" cx="788" cy="212"/>'+
		    '<circle r="80" cx="212" cy="788"/>'+
		    '<circle r="80" cx="788" cy="788"/></svg>',
		    imageResizeFactor: 3,
		    size:80
		});
		var count     = 0;
		var i = 0;
		var interval  = setInterval(function(){
		    if (count >= 100) {
		        clearInterval(interval);
		        $.LoadingOverlay("hide");
		        return;
		    }
		    switch(i){        		
    		case 0: 
    			$.LoadingOverlay("text", "Generating Unique Document ID"+s);
    			document.getElementById("but1").classList.add("btn-warning");
     			document.getElementById("but1").innerHTML = "Generating Unique Document ID"+s;
    			break;
    		case 1: 
    			$.LoadingOverlay("text", "Checking for Duplicate Document"+s);
    			document.getElementById("but1").innerHTML = "Generated Unique Document ID"+s;
    			document.getElementById("but1").classList.remove("btn-warning");
    			document.getElementById("but1").classList.add("btn-success");        		
    			document.getElementById("but2").classList.add("btn-warning");
    			document.getElementById("but2").innerHTML = "Checking for Duplicate Document"+s;
    			break;
    		case 2: 
    			$.LoadingOverlay("text", "Saving Document"+s);
    			document.getElementById("but2").innerHTML = "Document"+s+" "+isare+" Unique";
    			document.getElementById("but2").classList.remove("btn-warning");
    			document.getElementById("but2").classList.add("btn-success");   
    			document.getElementById("but3").classList.add("btn-warning");
    			document.getElementById("but3").innerHTML = "Saving Document"+s;
    			break;
    		case 3: 
    			$.LoadingOverlay("text", "Converting Document"+s+" to Text File");
    			document.getElementById("but3").innerHTML = "Document"+s+" Saved";
    			document.getElementById("but3").classList.remove("btn-warning");
    			document.getElementById("but3").classList.add("btn-success");   
    			document.getElementById("but4").classList.add("btn-warning");
    			document.getElementById("but4").innerHTML = "Converting Document"+s+" to Text File"; 
    			break;
    		case 4: 
    			$.LoadingOverlay("text", "Cleaning the Text File"+s);
    			document.getElementById("but4").innerHTML = "Document"+s+" Converted to Text File";
    			document.getElementById("but4").classList.remove("btn-warning");
    			document.getElementById("but4").classList.add("btn-success"); 
    			document.getElementById("but5").classList.add("btn-warning");
    			document.getElementById("but5").innerHTML = "Cleaning the Text File"+s;
    			break;
    		case 5: 
    			$.LoadingOverlay("text", "Splitting Sentences");
    			document.getElementById("but5").innerHTML = "Text File"+s+" Cleaned";
    			document.getElementById("but5").classList.remove("btn-warning");
    			document.getElementById("but5").classList.add("btn-success");
    			document.getElementById("but6").classList.add("btn-warning");
    			document.getElementById("but6").innerHTML = "Splitting Sentences";
    			break;
    		case 6: 
    			$.LoadingOverlay("text", "Tagging Document"+s);
    			document.getElementById("but6").innerHTML = "Text File Sentences Split";
    			document.getElementById("but6").classList.remove("btn-warning");
    			document.getElementById("but6").classList.add("btn-success");
    			document.getElementById("but7").classList.add("btn-warning");
    			document.getElementById("but7").innerHTML = "Tagging Document"+s;
    			break;
    		case 7: 
    			$.LoadingOverlay("text", "Processing Document"+s);
    			document.getElementById("but7").innerHTML = "Document"+s+" Tagged";
    			document.getElementById("but7").classList.remove("btn-warning");
    			document.getElementById("but7").classList.add("btn-success");
    			document.getElementById("but8").classList.add("btn-warning");
    			document.getElementById("but8").innerHTML = "Processing Document"+s;
    			break;
    		case 8: 
    			progressBar.css("width", "100%");     
        		document.getElementById("but8").classList.remove("btn-warning");
    			document.getElementById("but8").classList.add("btn-success");
    			document.getElementById("but8").innerHTML = "Ready for Bootstrapping";
    			document.getElementById("bar").innerHTML = "Preprocessing Complete"; 
    			document.getElementById("header").innerHTML = "Bootstrapping Document"+s;
                document.getElementById("bar").classList.remove("progress-bar-animated"); 
                document.getElementById("bar").classList.remove("progress-bar-striped");
                
    			$.LoadingOverlay("text", "Loading Tagged Document");
    			document.getElementById("but11").classList.add("btn-warning");
    			document.getElementById("but11").innerHTML = "Loading Tagged Document";
    			break;
    		case 9: 
    			$.LoadingOverlay("text", "Loading Seeds");
    			document.getElementById("but11").innerHTML = "Tagged Document Loaded";
    			document.getElementById("but11").classList.remove("btn-warning");
    			document.getElementById("but11").classList.add("btn-success");        		
    			document.getElementById("but22").classList.add("btn-warning");
    			document.getElementById("but22").innerHTML = "Loading Seeds";
    			break;
    		case 10: 
    			$.LoadingOverlay("text", "Processing Tagged Document");
    			document.getElementById("but22").innerHTML = "Seeds Loaded";
    			document.getElementById("but22").classList.remove("btn-warning");
    			document.getElementById("but22").classList.add("btn-success");   
    			document.getElementById("but33").classList.add("btn-warning");
    			document.getElementById("but33").innerHTML = "Processing Tagged Document";
    			break;
    		case 11: 
    			$.LoadingOverlay("text", "Generating Patterns from Document");
    			document.getElementById("but33").innerHTML = "Tagged Document Processed";
    			document.getElementById("but33").classList.remove("btn-warning");
    			document.getElementById("but33").classList.add("btn-success");   
    			document.getElementById("but44").classList.add("btn-warning");
    			document.getElementById("but44").innerHTML = "Generating Patterns from Document"; 
    			break;
    		case 12: 
    			$.LoadingOverlay("text", "Tagging POS");
    			document.getElementById("but44").innerHTML = "Patterns Generated";
    			document.getElementById("but44").classList.remove("btn-warning");
    			document.getElementById("but44").classList.add("btn-success"); 
    			document.getElementById("but55").classList.add("btn-warning");
    			document.getElementById("but55").innerHTML = "Tagging POS";
    			break;
    		case 13: 
    			$.LoadingOverlay("text", "Matching Patterns");
    			document.getElementById("but55").innerHTML = "Document POS Tagged";
    			document.getElementById("but55").classList.remove("btn-warning");
    			document.getElementById("but55").classList.add("btn-success");
    			document.getElementById("but66").classList.add("btn-warning");
    			document.getElementById("but66").innerHTML = "Matching Patterns";
    			break;
    		case 14: 
    			$.LoadingOverlay("text", "Saving Seed Patterns");
    			document.getElementById("but66").innerHTML = "Patterns Matched with Document";
    			document.getElementById("but66").classList.remove("btn-warning");
    			document.getElementById("but66").classList.add("btn-success");
    			document.getElementById("but77").classList.add("btn-warning");
    			document.getElementById("but77").innerHTML = "Saving Seed Patterns";
    			break;
    		case 15: 
    			$.LoadingOverlay("text", "Generating File for Validation");
    			document.getElementById("but77").innerHTML = "New Seed Patterns Saved";
    			document.getElementById("but77").classList.remove("btn-warning");
    			document.getElementById("but77").classList.add("btn-success");
    			document.getElementById("but88").classList.add("btn-warning");
    			document.getElementById("but88").innerHTML = "Generating File for Validation";
    			break;
    		case 16: 
    			$.LoadingOverlay("text", "Document"+s+" Ready for Validation");
    			progressBar2.css("width", "100%");     
        		document.getElementById("but88").classList.remove("btn-warning");
    			document.getElementById("but88").classList.add("btn-success");
    			document.getElementById("but88").innerHTML = "Document"+s+" Ready for Validation";
    			document.getElementById("bar2").innerHTML = "Bootstrapping Complete"; 
    			document.getElementById("header").innerHTML = "Document"+s+" Upload Successful!"; 
                document.getElementById("spinner").style.display="none";
                document.getElementById("bar2").classList.remove("progress-bar-animated"); 
                document.getElementById("bar2").classList.remove("progress-bar-striped"); 
    			break;
		    }
		    count += 6;
		    i+=1;
		    $.LoadingOverlay("progress", count);
		}, 500);
    });      
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
	<script type="text/javascript" charset="utf8"
		src="js/loadingoverlay.min.js"></script>
</body>
</html>