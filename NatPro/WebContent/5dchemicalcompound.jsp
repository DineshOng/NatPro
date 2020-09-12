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

	<!-- CONTENT HERE -->
	<div class="jumbotron bg-success" style="text-align: center">
		<div class="d-flex justify-content-center">
			<h1 class="display-4 text-white">
				<p id="chemCompoundName">
					<b>${compound.getCompoundNameHTML()}</b>
				</p>
			</h1>
			<button type="button" id="editBT"
				class="btn btn-outline-light btn-sm h-25 mt-3"
				onclick="editChemCompound()" data-toggle="tooltip"
				data-placement="top" title="edit entry" style="display: inline;">
				<i class="fa fa-pencil" aria-hidden="true" id="editChemCompoundLogo"></i>
			</button>
		</div>
		<img id="compoundIMG" class="responsive-img circle"
			src="https://pubchem.ncbi.nlm.nih.gov/image/imgsrv.fcgi?cid=${compound.getPubCID()}&t=l">
		<h3 class="text-white text-center">Chemical Compound</h3>
	</div>

	<div class="d-flex flex-row list-group text-center">
		<a
			class="list-group-item list-group-item-action list-group-item-success active"
			id="list-geninfo-list" data-toggle="list" href="#genInfo" role="tab"
			aria-controls="GeneralInformation">General Information</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-cheminfo-list" data-toggle="list" href="#chemInfo"
			role="tab" aria-controls="ChemicalInformation">Chemical
			Information</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-compsynonym-list" data-toggle="list" href="#compSynonyms"
			role="tab" aria-controls="CompoundSynonyms">Compound Synonyms</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="list-bioact-list" data-toggle="list" href="#bioActivities"
			role="tab" aria-controls="BiologicalActivities">Biological
			Activities</a>
	</div>

	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="genInfo" role="tabpanel"
			aria-labelledby="list-geninfo-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25">
					<thead>
						<tr>
							<th colspan="3">
								<h4 class="text-center">General Information</h4>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Common Name</th>
							<td>
								<div id="compound">${compound.getCompoundName()}</div> <input
								type="text" id="editCompoundName" name="editCompoundName" hidden
								required>
							</td>
						</tr>
						<tr>
							<th>IUPAC Name</th>
							<td>
								<div id="iupac">${compound.getIupac()}</div> <input type="text"
								id="editIupacName" name="editIupacName" hidden required>
							</td>
						</tr>
						<tr>
							<th>Canonical SMILES</th>
							<td>
								<div id="canSMILES">${compound.getCanSMILES()}</div> <input
								type="text" id="editCanSmilesName" name="editCanSmilesName"
								hidden required>
							</td>
						</tr>
						<tr>
							<th>Formula</th>
							<td>
								<div id="molForm">${compound.getMolFormHTML()}</div> <input
								type="text" id="editMolFormName" name="editMolFormName" hidden
								required>
							</td>

						</tr>
						<tr>
							<th>InChl</th>
							<td>
								<div id="inchi">${compound.getInchi()}</div> <input type="text"
								id="editInchiName" name="editInchiName" hidden required>
							</td>
						</tr>
						<tr>
							<th>InChl key</th>
							<td>
								<div id="inchikey">${compound.getInchikey()}</div> <input
								type="text" id="editInchiKeyName" name="editInchiKeyName" hidden
								required>
							</td>
						</tr>
						<tr>
							<th>Chemical Class</th>
							<td>
								<div id="chemClass">
									<%-- ${compound.getChemClass()} --%>
								</div> <input type="text" id="editChemClassName"
								name="editChemClassName" hidden required>
							</td>
						</tr>
						<tr>
							<th>PubChem CID</th>
							<td>
								<div id="pubCID">${compound.getPubCID()}</div> <input
								type="text" id="editPubCidName" name="editPubCidName" hidden
								required>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="tab-pane fade show" id="chemInfo" role="tabpanel"
			aria-labelledby="list-cheminfo-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25">
					<thead>
						<tr>
							<th colspan="2">
								<h4 class="text-center">Chemical Information</h4>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Molecular Weight</th>
							<td>
								<div id="molWeight">${compound.getMolWeight()}</div> <input
								type="text" id="editMolWeightName" name="editMolWeightName"
								hidden required>
							</td>
						</tr>
						<tr>
							<th>XLogP</th>
							<td>
								<div id="xlogp">${compound.getXlogp()}</div> <input type="text"
								id="editXlogpName" name="editXlogpName" hidden required>
							</td>
						</tr>
						<tr>
							<th>Mass</th>
							<td>
								<div id="mass">${compound.getMass()}</div> <input type="text"
								id="editMassName" name="editMassName" hidden required>
							</td>
						</tr>
						<tr>
							<th>TPSA</th>
							<td>
								<div id="tpsa">${compound.getTpsa()}</div> <input type="text"
								id="editTpsaName" name="editTpsaName" hidden required>
							</td>
						</tr>
						<tr>
							<th>Complexity</th>
							<td>
								<div id="complexity">${compound.getComplexity()}</div> <input
								type="text" id="editComplexityName" name="editComplexityName"
								hidden required>
							</td>
						</tr>
						<tr>
							<th>Charge</th>
							<td>
								<div id="charge">${compound.getCharge()}</div> <input
								type="text" id="editChargeName" name="editChargeName" hidden
								required>
							</td>
						</tr>
						<tr>
							<th>H-Bond Acceptors</th>
							<td>
								<div id="hBondAcceptor">${compound.getHBondAcceptor()}</div> <input
								type="text" id="editHBondAcceptorName"
								name="editHBondAcceptorName" hidden required>
							</td>
						</tr>
						<tr>
							<th>H-Bond Donor</th>
							<td>
								<div id="hBondDonor">${compound.getHBondDonor()}</div> <input
								type="text" id="editHBondDonorName" name="editHBondDonorName"
								hidden required>
							</td>
						</tr>
						<tr>
							<th>Rotatable Bonds</th>
							<td>
								<div id="rotBondCount">${compound.getRotBondCount()}</div> <input
								type="text" id="editRotBondCountName"
								name="editRotBondCountName" hidden required>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="tab-pane fade show" id="compSynonyms" role="tabpanel"
			aria-labelledby="list-compsynonym-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25">
					<thead>
						<tr>
							<th>Compound Synonyms</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="synonym"><c:forEach
									items="${compound.getCompoundSynonyms()}" var="cs">
									<tr>
										<td>${cs}</td>
									</tr>
								</c:forEach></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="tab-pane fade show" id="bioActivities" role="tabpanel"
			aria-labelledby="list-bioact-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-25">
					<thead>
						<tr>
							<th>Biological Activities</th>
							<th>Cell Line</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${compound.getBioActs()}" var="baList">
							<c:forEach items="${baList.getBiologicalActivity()}" var="ba">
								<tr>
									<td>${ba}</td>
									<td>${baList.getCellLine().getCellLine()}</td>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<form id="saveForm" action="AddCompoundServlet" method="POST">
			<input type="hidden" name="compound"> <input type="hidden"
				name="pubCID"> <input type="hidden" name="molForm">
			<input type="hidden" name="canSMILES"> <input type="hidden"
				name="inchi"> <input type="hidden" name="inchikey">
			<input type="hidden" name="iupac"> <input type="hidden"
				name="molWeight" value=0> <input type="hidden" name="xlogp"
				value=0> <input type="hidden" name="mass" value=0> <input
				type="hidden" name="tpsa" value=0> <input type="hidden"
				name="complexity" value=0> <input type="hidden"
				name="charge" value=0> <input type="hidden"
				name="hBondDonor" value=0> <input type="hidden"
				name="hBondAcceptor" value=0> <input type="hidden"
				name="rotBondCount" value=0>

		</form>

	</div>


	<!-- INCLUDE FOOTER HTML -->
	<%@include file="_includeFooter.html"%>
	<script type="text/javascript" charset="utf8"
		src="js/jquery-3.5.1.min.js"></script>

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

	<script>
       
     $(document).ready(function() {
     	//$('#loading').hide();
     	
     	$('#saveBT').click(function() {
     		
     		$('input[name="compound"]').val($("#compound").text());
     		
     		$('input[name="pubCID"]').val($("#pubCID").text());
     		$('input[name="molForm"]').val($("#molForm").text());
     		$('input[name="canSMILES"]').val($("#canSMILES").text());
     		$('input[name="inchi"]').val($("#inchi").text());
     		$('input[name="inchikey"]').val($("#inchikey").text());
     		$('input[name="iupac"]').val($("#iupac").text());
     		
     		$('input[name="molWeight"]').val($("#molWeight").text());
     		$('input[name="xlogp"]').val($("#xlogp").text());
     		$('input[name="mass"]').val($("#mass").text());
     		$('input[name="tpsa"]').val($("#tpsa").text());
     		$('input[name="complexity"]').val($("#complexity").text());
     		
     		$('input[name="charge"]').val($("#charge").text());
     		$('input[name="hBondDonor"]').val($("#hBondDonor").text());
     		$('input[name="hBondAcceptor"]').val($("#hBondAcceptor").text());
     		$('input[name="rotBondCount"]').val($("#rotBondCount").text());
     		
     		$("#saveForm").submit();
     	});
     	
	$('#editBT').click(function() {
     		
	
	/*var strAction = "compound.jsp?";
	
	if($("#compound").text() != "")
		strAction += ("compound="+escape($("#compound").text()));
	if($("#pubCID").text() != "")
		strAction += ("&pubCID="+escape($("#pubCID").text()));
	if($("#molForm").text() != "")
		strAction += ("&molForm="+escape($("#molForm").text()));
	if($("#canSMILES").text() != "")
		strAction += ("&canSMILES="+escape($("#canSMILES").text()));
	if($("#inchi").text() != "")
		strAction += ("&inchi="+escape($("#inchi").text()));
	if($("#inchikey").text() != "")
		strAction += ("&inchikey="+escape($("#inchikey").text()));
	if($("#iupac").text() != "")
		strAction += ("&iupac="+escape($("#iupac").text()));
	
	if($("#molWeight").text() != "")
		strAction += ("&molWeight="+escape($("#molWeight").text()));
	if($("#xlogp").text() != "")
		strAction += ("&xlogp="+escape($("#xlogp").text()));
	if($("#mass").text() != "")
		strAction += ("&mass="+escape($("#massmass").text()));
	if($("#tpsa").text() != "")
		strAction += ("&tpsa="+escape($("#tpsa").text()));
	if($("#complexity").text() != "")
		strAction += ("&complexity="+escape($("#complexity").text()));
	
	if($("#charge").text() != "")
		strAction += ("&charge="+escape($("#charge").text()));
	if($("#hBondDonor").text() != "")
		strAction += ("&hBondDonor="+escape($("#hBondDonor").text()));
	if($("#hBondAcceptor").text() != "")
		strAction += ("&hBondAcceptor="+escape($("#hBondAcceptor").text()));
	if($("#rotBondCount").text() != "")
		strAction += ("&rotBondCount="+escape($("#rotBondCount").text()));
	*/
	var strAction = "EditCompoundServlet";
		
	$("#saveForm").attr("action", strAction);
	
     		$('input[name="compound"]').val($("#compound").text());
     		
     		$('input[name="pubCID"]').val($("#pubCID").text());
     		$('input[name="molForm"]').val($("#molForm").text());
     		$('input[name="canSMILES"]').val($("#canSMILES").text());
     		$('input[name="inchi"]').val($("#inchi").text());
     		$('input[name="inchikey"]').val($("#inchikey").text());
     		$('input[name="iupac"]').val($("#iupac").text());
     		
     		$('input[name="molWeight"]').val($("#molWeight").text());
     		$('input[name="xlogp"]').val($("#xlogp").text());
     		$('input[name="mass"]').val($("#mass").text());
     		$('input[name="tpsa"]').val($("#tpsa").text());
     		$('input[name="complexity"]').val($("#complexity").text());
     		
     		$('input[name="charge"]').val($("#charge").text());
     		$('input[name="hBondDonor"]').val($("#hBondDonor").text());
     		$('input[name="hBondAcceptor"]').val($("#hBondAcceptor").text());
     		$('input[name="rotBondCount"]').val($("#rotBondCount").text());
     		
     		$("#saveForm").submit();
     	});
     	
     	$('#pubchemBT').click(function() {
     		$.LoadingOverlay("show");
     		$.ajax({
     			url : 'RetrieveCompoundServlet?compound=${compound.getCompoundName()}',
     			data : {
     				userName : $('#compound').val()
     			},
     			success : function(obj) {
     				if(obj.molForm == null) {
     					//$('#loading').hide();
     					$.LoadingOverlay("hide");
     					
     					alert("compound not found");
     				} else {
     				
      				$('#compoundIMG').attr("src", "https://pubchem.ncbi.nlm.nih.gov/image/imgsrv.fcgi?cid="+obj.pubCID+"&t=l");
      				
      				$('#pubCID').html(obj.pubCID);
      				$('#molForm').html(obj.molForm);
      				$('#canSMILES').html(obj.canSMILES);
      				$('#inchi').html(obj.inchi);
      				$('#inchikey').html(obj.inchikey);
      				$('#iupac').html(obj.iupac);
      				
      				$('#molWeight').html(obj.molWeight);
      				$('#xlogp').html(obj.xlogp);
      				$('#mass').html(obj.mass);
      				$('#tpsa').html(obj.tpsa);
      				$('#complexity').html(obj.complexity);
      				
      				$('#charge').html(obj.charge);
      				$('#hBondDonor').html(obj.hBondDonor);
      				$('#hBondAcceptor').html(obj.hBondAcceptor);
      				$('#rotBondCount').html(obj.rotBondCount);
      				
      				var arr = obj.synonym;
      				var syn = arr.split("\n");
      				var text = "";
      				
      				var i;
      				for (i = 0; i < syn.length; i++) {
      				  text += '<tr><td>' + syn[i] + "</td></tr>";
      				}
      				$('#synonym').append(text);
      				
      				$('#pubchemBT').hide();
      				
      				$.LoadingOverlay("hide");
      			}
     				//$.LoadingOverlay("hide");
     				
     			}
     		});
     	});
     });
     
    </script>
	<script type="text/javascript" charset="utf8"
		src="js/loadingoverlay.min.js"></script>
</body>
</html>