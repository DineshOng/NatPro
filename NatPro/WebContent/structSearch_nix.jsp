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

<title>NatPro : Structure Search</title>

<script type="text/javascript" language="javascript"
	src="jsme/jsme.nocache.js"></script>

<script type="text/javascript"
	src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>

<script>
	    //this function will be called after the JavaScriptApplet code has been loaded.
	    function jsmeOnLoad() {
	    	var w = $("#jsme_div").width();
	        jsmeApplet = new JSApplet.JSME("jsme_container", w+"px", "400px");
	        jsmeApplet.options('nocanonize');
	        
	        jsmeApplet2 = new JSApplet.JSME("jsme_container2", w+"px", "400px");
	        jsmeApplet2.options('nocanonize');
	   }
	    
	    
	</script>

<link rel="stylesheet" type="text/css"
	href="kekule/themes/default/kekule.css" />
<style>
#searchPanel {
	overflow: hidden;
}

#searchOptionPanel, #composerSearch {
	margin: 0.5em 0.5em;
	float: left;
}

#composerSearch {
	width: 600px;
	height: 400px;
}

#searchOptionPanel {
	margin-top: 2em;
	padding: 1em;
	padding-top: 0.2em;
	border: 1px dotted #666;
	line-height: 2;
}
</style>


<script src="kekule/kekule.js?modules=io,chemWidget,algorithm"></script>
</head>
<body>

	<%@include file="navbarnix.html"%>

	<!--  INSERT HTML CODE -->

	<div id="firstDiv">

		<h3 class="card-title">
			<b id="cardTitle">Compound Structure Search</b>
		</h3>

		<div class="mb-4">
			<!-- Solid divider -->
			<hr class="solid">
		</div>

		<div class="row">
			<div class="col-md-6">
				<div id="jsme_div">
					<div id="jsme_container" onmouseover="search()"
						style="margin-bottom: 1rem"></div>
					<div id="jsme_container2"
						style="margin-bottom: 1rem; display: none"></div>
				</div>
			</div>

			<div class="col-md-6">
				<div id="result" style="height: 400px; width: 100%">
					<fieldset id="searchOptionPanel" onchange="changeOptions()">
						<legend>Search Options</legend>
						<label for="listCompareLevel">Comparation Level: </label><br /> <select
							id="listCompareLevel">
							<option value="Skeletal">Skeletal</option>
							<option value="Constitution" selected="selected">Constitution</option>
							<!--
			                <option value="Configuration">Configuration</option>
							-->
						</select><br /> <input type="checkbox" id="checkboxExactly" /><label
							for="checkboxExactly">Exactly structure</label><br /> <input
							type="checkbox" id="checkboxIgnoreCharge" checked="true" /><label
							for="checkboxIgnoreCharge">Ignore charge</label><br /> <input
							type="checkbox" id="checkboxIgnoreMass" checked="true" /><label
							for="checkboxIgnoreMass">Ignore Mass Number</label><br />

						<!-- <form id="subform" action="StructSearchResultServlet"
							onsubmit="return search()" method="POST">
							<div class="form-row">
								<input type="hidden" name="data" id="data"> <input
									type="submit" id="subBT" class="btn btn-primary" value="Search">
							</div>

						</form> -->


						<div class="form-row">
							<input type="hidden" name="data" id="data"> <input
								type="button" onclick="search()" id="subBT"
								class="btn btn-primary" value="Search">
						</div>



						<!-- <button id="btnSearch" data-widget="Kekule.Widget.Button">Search</button> -->
					</fieldset>
				</div>
			</div>
		</div>

		<div id="">
			<h4 class="card-title" style="margin-top: 50px">
				<b id="resultCount"></b>
			</h4>
			<div id="results" style="display:inline"></div>
		</div>

		<script type="text/javascript"
			src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="DataTables/datatables.min.js"></script>

		<script type="text/javascript">
			$(window).on('resize', function(){
		    	var w = $("#jsme_div").width();
		    	jsmeApplet.setSize(w+"px", "400px");
		    	$("#result").css("height", "400px");
		    	//transformStick.resize(w, 340);
			});
		
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
		
		var data = [<c:forEach items="${data}" var="cc">{"com":"${cc.getCompoundName()}","smi":"${cc.getCanSMILES()}"},</c:forEach>];
		let out = [];
		
		function showResults() {
			var txt = '';
			for(var i=0; i<out.length; i++) {
				//<a href="ViewCompoundServlet?compound=${compoundList.getCompoundName()}">${compoundList.getCompoundNameHTML()}</a
	    		//txt += `<div class="card" style="margin-right: 10px; margin-bottom: 10px; max-width: 350px; display:inline-block" id=`+out[i].com+`><div class="card-footer">`+out[i].com+`</div></div>`;
				txt += `<div class="card" style="margin-right: 10px; margin-bottom: 10px; max-width: 350px; display:inline-block" id=`+out[i].com+`><div class="card-footer"><a href="ViewCompoundServlet?compound=`+out[i].com+`">`+out[i].com+`</div></div>`;
				
			}
			
			//<script>new JSApplet.JSME(`+out[i].com+`, "100%", "100%", {"smiles": `+out[i].smi+`, "options": "depict"})<\/script>
			
			$("#results").html(txt);
			insertSmiles();
	    }
		
		 function insertSmiles() {
	        for(var i= 0; i<out.length; i++) {
	            var cpd_id  = out[i].com;
	            var smiles = out[i].smi;
	            
	            new JSApplet.JSME(cpd_id, "300px", "250px", {"smiles": smiles, "options": "depict"});
	        }
		 }

		
		function search() {
			out = [];
			$("#jsme_container").attr("onmouseover", "");
			for(var x of data) {
				subStructSearch(x);
			}
			$("#data").val(JSON.stringify(out));
			
			if(out.length != 0) {
				showResults();
				console.log(out.length);
				$("#resultCount").html(out.length + " Result(s)")
			} else {
				$("#results").html("");
				$("#resultCount").html(out.length + " Result(s)")
			}
		}
		
		 // search options
		var options = {
		  'level': Kekule.StructureComparationLevel.CONSTITUTION,  // compare in consititution level
		  'compareCharge': false,   // ignore charge
		  'compareMass': false      // ignore mass number difference
		};
		
		function changeOptions() {
			
			var lev = $("#listCompareLevel").val();
			
			if(lev === "Skeletal") {
				options.level = Kekule.StructureComparationLevel.SKELETAL;
			} else if(lev === "Constitution") {
				options.level = Kekule.StructureComparationLevel.CONSTITUTION;
			}
			
			options.compareCharge = !($('#checkboxIgnoreCharge').is(':checked'));
			options.compareMass = !($('#checkboxIgnoreMass').is(':checked'));
			options.exactMatch = $('#checkboxExactly').is(':checked');
			
			console.log(options);
		}
		
		function subStructSearch(smi) {
			
			jsmeApplet2.readGenericMolecularInput(smi.smi);
			var molFile = jsmeApplet2.molFile(false);
			jsmeApplet2.reset();
			
			// get two molecules
			var srcMol =  Kekule.IO.loadFormatData(jsmeApplet.molFile(false), 'mol');
			var targetMol = Kekule.IO.loadFormatData(molFile, 'mol');
			
			
			
			// check if srcMol is a sub structure in targetMol
			var result = targetMol.search(srcMol, options) || [];
			// result is an array that contains all matching child objects (atoms and bonds)
			
			// iterate the search result
			for (var i = 0, l = result.length; i < l; ++i)
			{
			  var obj = result[i];
			  if (obj instanceof Kekule.ChemStructureConnector) {  // is bond
			    //console.log(obj.getClassName(), obj.getBondOrder? obj.getBondOrder(): '');
			  } else { // is atom
			    //console.log(obj.getClassName(), obj.getLabel());
			  }
			}
			// hightlight matching part in targetMol
			for (var i = 0, l = targetMol.getChildCount(); i < l; ++i)
			{
			  var child = targetMol.getChildAt(i);
			  var color = (result.indexOf(child) >= 0)? 'red': null;
			  child.setRenderOption('color', color);
			}
			// report in console and web page
			var msg = result.length? out.push({"com":smi.com,"smi":smi.smi,"mol":molFile}): 'Sub structure not found' ;
			//out.push({"msg":msg,"com":smi.com});
			var sClass = result.length? 'Positive': 'Nagative';
			//console.log(msg + " " + smi.com);
			//var elem = document.getElementById('labelResult');
			//elem.className = sClass;
			//elem.innerHTML = msg;
			
    	}
		
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