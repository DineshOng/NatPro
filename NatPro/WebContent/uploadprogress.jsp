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

	${html}
	<div class="container-fluid mt-3">
		<div class="row justify-content-around">
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
	<div></div>



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
        var i = 0;
        var currPercent = 0;
        var progressBar = $("#bar");
        var s = document.getElementById("numdocsplural").getAttribute("val");
        var isare = "is";
        if(s == "s"){
        	isare="are";
        }
        var startTime = document.getElementById("startTime").getAttribute("val");
        var genDocIdDuration = document.getElementById("genDocIdDuration").getAttribute("val");
        var checkDocDuration = document.getElementById("checkDocDuration").getAttribute("val");
        var saveDocDuration = document.getElementById("saveDocDuration").getAttribute("val");
        var preprocDuration = document.getElementById("preprocDuration").getAttribute("val");
        var convertTxtDuration = (preprocDuration *0.1)
        var cleanDuration =  (preprocDuration *0.1)
        var splitDuration= (preprocDuration * 0.1)
        var tagDuration =  (preprocDuration * 0.7)
        var endTime = document.getElementById("endTime").getAttribute("val");
         console.log("start time"+startTime);
         console.log("genDocIdDuration"+genDocIdDuration);
         console.log("checkDocDuration"+checkDocDuration);
         console.log("saveDocDuration"+saveDocDuration);
         console.log("preprocDuration"+preprocDuration);
         console.log("convertTxtDuration"+convertTxtDuration);
         console.log("cleanDuration"+cleanDuration);
         console.log("splitDuration"+splitDuration);
         console.log("tagDuration"+tagDuration);
         console.log("end time"+endTime);
         var timeStops = new Array(genDocIdDuration,checkDocDuration,saveDocDuration,convertTxtDuration,cleanDuration,splitDuration,tagDuration);
        function countNumbers(){
        	if(i<=7){
        		switch(i){
        		case 0: 
        			document.getElementById("but1").classList.add("btn-warning");
        			document.getElementById("but1").innerHTML = "Generating Unique Document ID"+s;
        			break;
        		case 1: 
        			document.getElementById("but1").innerHTML = "Generated Unique Document ID"+s;
        			document.getElementById("but1").classList.remove("btn-warning");
        			document.getElementById("but1").classList.add("btn-success");        		
        			document.getElementById("but2").classList.add("btn-warning");
        			document.getElementById("but2").innerHTML = "Checking for Duplicate Document"+s;
        			break;
        		case 2: 
        			document.getElementById("but2").innerHTML = "Document"+s+" "+isare+" Unique";
        			document.getElementById("but2").classList.remove("btn-warning");
        			document.getElementById("but2").classList.add("btn-success");   
        			document.getElementById("but3").classList.add("btn-warning");
        			document.getElementById("but3").innerHTML = "Saving Document"+s;
        			break;
        		case 3: 
        			document.getElementById("but3").innerHTML = "Document"+s+" Saved";
        			document.getElementById("but3").classList.remove("btn-warning");
        			document.getElementById("but3").classList.add("btn-success");   
        			document.getElementById("but4").classList.add("btn-warning");
        			document.getElementById("but4").innerHTML = "Converting Document"+s+" to Text File"; 
        			break;
        		case 4: 
        			document.getElementById("but4").innerHTML = "Document"+s+" Converted to Text File";
        			document.getElementById("but4").classList.remove("btn-warning");
        			document.getElementById("but4").classList.add("btn-success"); 
        			document.getElementById("but5").classList.add("btn-warning");
        			document.getElementById("but5").innerHTML = "Cleaning the Text File"+s;
        			break;
        		case 5: 
        			document.getElementById("but5").innerHTML = "Text File"+s+" Cleaned";
        			document.getElementById("but5").classList.remove("btn-warning");
        			document.getElementById("but5").classList.add("btn-success");
        			document.getElementById("but6").classList.add("btn-warning");
        			document.getElementById("but6").innerHTML = "Splitting Sentences";
        			break;
        		case 6: 
        			document.getElementById("but6").innerHTML = "Text File Sentences Split";
        			document.getElementById("but6").classList.remove("btn-warning");
        			document.getElementById("but6").classList.add("btn-success");
        			document.getElementById("but7").classList.add("btn-warning");
        			document.getElementById("but7").innerHTML = "Tagging Document"+s;
        			break;
        		case 7: 
        			document.getElementById("but7").innerHTML = "Document"+s+" Tagged";
        			document.getElementById("but7").classList.remove("btn-warning");
        			document.getElementById("but7").classList.add("btn-success");
        			document.getElementById("but8").classList.add("btn-warning");
        			document.getElementById("but8").innerHTML = "Finalizing Document"+s;
        			break;
        		}
        		currPercent = currPercent + (timeStops[i]/endTime)*100
        		progressBar.css("width", currPercent + "%");
        		i=i+1;
        	}else{
        		document.getElementById("but8").classList.remove("btn-warning");
    			document.getElementById("but8").classList.add("btn-success");
    			document.getElementById("but8").innerHTML = "Finished Processing";
    			
    			document.getElementById("header").innerHTML = "Document"+s+" Upload Successful!";
                document.getElementById("spinner").style.display="none";
                document.getElementById("bar").classList.remove("progress-bar-animated");
        	}        	
            // Wait for sometime before running this script again
            setTimeout("countNumbers()", 500);
        }
        countNumbers();
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