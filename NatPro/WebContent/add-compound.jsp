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

<link href="cheminfo/kekule.css" rel="stylesheet" type="text/css">
		
	<script src="cheminfo/raphael-min.2.0.1.js"></script>	
	<script src="cheminfo/Three.js"></script>	
	
	<script id="scriptOb" src="cheminfo/openbabel.js"></script>	
	<script src="kekule/kekule.js?modules=io,chemWidget,openbabel"></script>	

<title>NatPro Compound</title>

	<style>
	
	    #compoundHelp, #compoundAlertFail, #compoundAlertSuccess, #hidSubBT{
	    	display: none;
	    }
    </style>
    
    <script type="text/javascript" language="javascript" src="jsme/jsme.nocache.js"></script>
    
    <link rel="stylesheet" href="ChemDoodleWeb/ChemDoodleWeb.css" type="text/css">
	<script type="text/javascript" src="ChemDoodleWeb/ChemDoodleWeb.js"></script>
	
	<script type="text/javascript"
		src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script>

	<script>
	    //this function will be called after the JavaScriptApplet code has been loaded.
	    function jsmeOnLoad() {
	    	var w = $("#jsme_div").width();
	        jsmeApplet = new JSApplet.JSME("jsme_container", w+"px", "340px");
	        jsmeApplet.options('nocanonize');
	        generateStructure();
	        //updateModel();
	        
	        jsmeApplet.setCallBack("InchiKeySearch", function(jsmeEvent) {
				//alert(jsmeEvent.argument.key + "\n" + jsmeEvent.argument.inchi + "\n");
				$('input[name="inchi"]').val(jsmeEvent.argument.inchi);
				$('input[name="inchikey"]').val(jsmeEvent.argument.key);
			});
	   }
	</script>
    
</head>
<body>

	<%@include file="navbarnix.html"%>

	<div id="firstDiv">
	
	<h3 class="card-title" >
						<b id="cardTitle">Edit Compound: ${compound.getCompoundNameHTML()}</b>
					</h3>

					<div class="mb-4">

						<!-- Solid divider -->
						<hr class="solid">
					</div>
	
		<form id="compoundForm" action="SaveCompoundServlet" method="POST" onsubmit="return validateForm()">
			<div class="card border-success">
				<div class="card-body">
					<div id="compoundAlertFail" class="alert alert-danger" role="alert">
						<a>Compound not found!</a>
						<!--  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    						<span aria-hidden="true">&times;</span>-->
  						</button>
  					</div>
  					<div id="compoundAlertSuccess" class="alert alert-success" role="alert">
						<a>Compound found!</a>
						<!--<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    						<span aria-hidden="true">&times;</span>-->
  						</button>
  					</div>
  					
  					<div class="row">
  						<div class="col-md-6">
		  					<div id="jsme_div">
								<div id="jsme_container" onmouseover="generateKey()" style="margin-bottom: 1rem"></div>
							</div>
						</div>
						<div class="col-md-6">
							<div id="cd">
								<script>
								  var w = $("#cd").width();
								  let transformStick = new ChemDoodle.TransformCanvas3D('transformStick', w, 340);
								  transformStick.styles.set3DRepresentation('Stick');
								  transformStick.styles.backgroundColor = 'black';
								  let molFile = '';
								  let molecule = ChemDoodle.readMOL(molFile, 1);
								  transformStick.loadMolecule(molecule);
								</script>
							</div>
						</div>
						
					</div>
					
					<div class="form-row">
						  
					</div>
  					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="">Compound Name</label> 
							
							<input
								name="compound" type="text"
								class="form-control" id="compound" aria-describedby="emailHelp"
								placeholder="" value="${compound.getCompoundName()}" required> 
								
							<small
								id="compoundHelp" class="form-text text-muted">Try
								clicking the <a href="#pubchemBT">PubChem button</a> on the
								bottom of the page to fill the other information.
							</small>
						</div>

						<div class="form-group col-md-6">
							<label for="">Molecular Formula</label> 
							
							<input
								pattern="(([A-Z][a-z]?)([1-9]0?)*)+" title="ex. H20, NaCl"
								name="molForm" type="text" class="form-control"
								 placeholder="" value="${compound.getMolForm()}">
						</div>

					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="">IUPAC Name</label> <input name="iupac" type="text"
								class="form-control" 
								placeholder="" value="${compound.getIupac()}">
						</div>

						<div class="form-group col-md-6">
							<label for="">SMILES</label> <input name="canSMILES"
								type="text" class="form-control" id="SMILES"
								placeholder="" value="${compound.getCanSMILES()}">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="">InChI</label> <input name="inchi" type="text"
								class="form-control" 
								placeholder="" value="${compound.getInchi()}">
						</div>
						<div class="form-group col-md-6">
							<label for="">InChI key</label> <input name="inchikey"
								type="text" class="form-control" 
								placeholder="" value="${compound.getInchikey()}">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="exampleFormControlTextarea1">Compound Classes</label>
							<textarea name="compoundClass" class="form-control"
								rows="3"
								placeholder=""></textarea>
							<small id="exampleFormControlTextarea1"
								class="form-text text-muted">Enter compound classes
								separated by each line.</small>
						</div>
						
						<div class="form-group col-md-6">
							<label for="exampleFormControlTextarea1">Compound Synonyms</label>
							
							<textarea name="synonym" class="form-control"
								rows="3"
								placeholder="">${compound.getAllCompoundSynonymsHTML()}</textarea>
								
								
							<small id="exampleFormControlTextarea1"
								class="form-text text-muted">Enter compound synonyms
								separated by each line.</small>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-4">

							<label for="">Molecular Weight</label> <input
								pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" title="number only"
								name="molWeight" type="text" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp"
								placeholder="" value="${compound.getMolWeight()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">XLogP</label> <input
								pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" title="number only"
								name="xlogp" type="text" class="form-control"
								 placeholder="" value="${compound.getXlogp()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">Mass</label> <input
								pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" title="number only"
								name="mass" type="text" class="form-control"
								 placeholder="" value="${compound.getMass()}">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-4">
							<label for="">TPSA</label> <input
								pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" title="number only"
								name="tpsa" type="text" class="form-control"
								 placeholder="" value="${compound.getTpsa()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">Complexity</label> <input
								pattern="^(-?)(0|([1-9][0-9]*))(\.[0-9]+)?$" title="number only"
								name="complexity" type="text" class="form-control"
								 placeholder="" value="${compound.getComplexity()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">Charge</label> <input
								pattern="^-?([1-9][0-9]*)+|0$" title="integer only"
								name="charge" type="text" class="form-control"
								 placeholder="" value="${compound.getCharge()}">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-4">
							<label for="">H-Bond Acceptors</label> <input
								pattern="^-?([1-9][0-9]*)+|0$" title="integer only"
								name="hBondDonor" type="text" class="form-control"
								 placeholder="" value="${compound.getHBondAcceptor()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">H-Bond Donors</label> <input
								pattern="^-?([1-9][0-9]*)+|0$" title="integer only"
								name="hBondAcceptor" type="text" class="form-control"
								 placeholder="" value="${compound.getHBondDonor()}">
						</div>
						<div class="form-group col-md-4">
							<label for="">Rotatable Bonds</label> <input
								pattern="^-?([1-9][0-9]*)+|0$" title="integer only"
								name="rotBondCount" type="text" class="form-control"
								 placeholder="" value="${compound.getRotBondCount()}">
						</div>
					</div>
					
					<div class="mb-4">

						<!-- Solid divider -->
						<hr class="solid">
					</div>
					
					<div class="form-row">
						<div class="form-group col-md-8">
							<table class="table">
			                    <thead>
				                    <tr>
				                        <td style="width: 55%">Biological Activity</th>
				                        <td style="width: 40%">Cell Line</th>
				                        <td style="width: 5%"></th>
				                    </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach items="${compound.getBioActs()}" var="bioactList" varStatus="loop">
			                    		<tr>
					                    	<td>
					                            <input
					                                type="text" name="bioActNX${loop.index+100}"
					                                class="form-control" placeholder="" value="${bioactList.getBiologicalActivity()}">
					                        </td>
					                        <td>
					                        	<input
					                        		type="text" name="cellLineNX${loop.index+100}"
					                        		class="form-control" placeholder="" value="${bioactList.getCellLine().getCellLine()}">
					                        </td>
					                        <td>
					                        	<input type="button" class="ibtnDel btn btn-sm btn-danger " style="float: right" value="Delete">
					                        </td>
					                	</tr>
				                   	</c:forEach>
				                   	
				                   	<c:if test="${empty compound.getBioActs()}">
				                    <tr id="firstTR">
				                        <td>
				                            <input
				                                type="text" name="bioActNX"
				                                class="form-control" placeholder="">
				                        </td>
				                        <td>
				                        	<input
				                        		type="text" name="cellLineNX"
				                        		class="form-control" placeholder="">
				                        </td>
				                        <td></td>
				                    </tr>
				                    </c:if>
			                    </tbody>
		                	</table>
		                </div>
		                <div class="form-group col-md-4">
		                	<button id="addrow" type="button" class="btn btn-secondary btn-sm" style="margin-top:5px"><i class="fa fa-plus" aria-hidden="true"></i> Biological Activity</button>
		                </div>
		            </div>
		            
		            <div class="form-row">
						<button id="pubchemBT" class="btn btn-info" role="button">Auto-fill fields from PubChem</button>
					</div>
					&nbsp; 
					
					<input type="hidden" name="bioActjson" value="">
					
					<input type="hidden" name="oldCompound" value="${compound.getCompoundName()}">
					<input type="hidden" name="pubCID" value="${compound.getPubCID()}">
					<div class="form-row">
						<button id="hidSubBT" type="button" class="btn btn-primary"></button>
						<button id="subBT" type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	
		
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>

	<script type="text/javascript">
	    $(document).ready(function() {
	        $('#table_id').DataTable();
	        
	        //$("#compoundHelp").css("display", "none");
	        //$("#compoundAlertFail").hide();
			//$("#compoundAlertSuccess").hide();
			//$("#hidSubBT").hide();
	        
			$("#search").val('${searchKey}');
			$('.hid').css('display', 'none');
			//$("#jsme_div").hide();
			ddfunc('${searchCategory}');
	        
	        var action = '${action}';
			if(action === "") {
				$("#compoundForm").attr("action", "AddCompoundServlet");
				//$("#jsme_div").show();
			}
			
			var cc = '${compound.getCompoundName()}';
			if(cc === "") {
				$("#cardTitle").html("Add New Compound");
				$("#loadTitle").html("Adding...");
			}
	        
	        $('#pubchemBT').click(function() {
	        	
	       	 	$("#compoundAlertFail").hide();
		       	$("#compoundAlertSuccess").hide();
		       	
	        	
	        	if($('input[name="compound"]')[0].checkValidity()) {
	        		//$.LoadingOverlay("show");
	        		
	        		$("#pubchemBT").show();
	           		$("#pubchemBT").prop("disabled", true);
	            	$("#pubchemBT").html('<i class="fa fa-spinner fa-spin"></i>&#32;Retrieving compound...');
	        		
	        		$.ajax({
	        			url : 'RetrieveCompoundServlet?compound='+$('#compound').val()+"&option=1",
	        			data : {
	        				userName : $('#compound').val()
	        			},
	        			success : function(obj) {
	        				if(obj.molForm == null) {
	        					//$.LoadingOverlay("hide");
	        					
	        					//alert("compound not found");
	        					$("#compoundAlertFail").show();
	        					
	        					$("#pubchemBT").show();
		    	           		$("#pubchemBT").prop("disabled", false);
		    	            	$("#pubchemBT").html('Auto-fill from PubChem');
	        				} else {
	        			
		        				
		        				//$('input[name="compound"]').val($("#compound").text());
		    	        		
		    	        		$('input[name="pubCID"]').val(obj.pubCID);
		    	        		$('input[name="molForm"]').val(obj.molForm);
		    	        		$('input[name="canSMILES"]').val(obj.canSMILES);
		    	        		$('input[name="inchi"]').val(obj.inchi);
		    	        		$('input[name="inchikey"]').val(obj.inchikey);
		    	        		$('input[name="iupac"]').val(obj.iupac);
		    	        		
		    	        		$('input[name="molWeight"]').val(obj.molWeight);
		    	        		$('input[name="xlogp"]').val(obj.xlogp);
		    	        		$('input[name="mass"]').val(obj.mass);
		    	        		$('input[name="tpsa"]').val(obj.tpsa);
		    	        		$('input[name="complexity"]').val(obj.complexity);
		    	        		
		    	        		$('input[name="charge"]').val(obj.charge);
		    	        		$('input[name="hBondDonor"]').val(obj.hBondDonor);
		    	        		$('input[name="hBondAcceptor"]').val(obj.hBondAcceptor);
		    	        		$('input[name="rotBondCount"]').val(obj.rotBondCount);
		    	        		
		    	        		generateStructure();
		    	        		
		    	        		$('textarea[name="synonym"]').val($('textarea[name="synonym"]').val()+"\n"+obj.synonym);
		        				
		    	        		
		    	        		$("#pubchemBT").show();
		    	           		$("#pubchemBT").prop("disabled", false);
		    	            	$("#pubchemBT").html('Auto-fill fields from PubChem');
		        				
		        				
		        				//$.LoadingOverlay("hide");
		        				$("#compoundAlertFail").hide();
		        				$("#compoundAlertSuccess").show();
		        			}
	        				
	        			}
	        		});
	        	} else {
	        		
	        		$("#subBT").click();
	        	}
	        });
	        
	    });
	    
	    $(document).ready(function () {
	    	var counter = 0;

            $("#addrow").on("click", function () {
                //if(counter < 4) {
                    var newRow = $("<tr>");
                    var cols = "";

                    cols += '<td><input type="text" name="bioActNX' + counter + '" class="form-control" placeholder=""></td>';

                    cols += '<td><input type="text" name="cellLineNX' + counter + '" class="form-control" placeholder=""></td>';

                    cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " style="float: right" value="Delete"></td>';
                    newRow.append(cols);
                    $("table").append(newRow);
                    counter++;
                //}
            });

            $("table").on("click", ".ibtnDel", function (event) {
                $(this).closest("tr").remove();       
                counter -= 1
            });
	        
	    });
	    
	    $("#compound").change(mapRC);

        function mapRC() {
            var RC = getRC();
            var vals = RC.filter((RC) => {
                return RC.bioAct != "" 
            })
            console.log(vals);
            return vals;
        }

        function getRC() {
            var arr = [{}];
            
            $("table").find('input[name^="bioActNX"]').each(function () {
                var x = {};
                var num = $(this).attr("name").split("NX")[1];
                console.log(num);
                x.cellLine = $('input[name="cellLineNX'+num+'"]').val().trim();
                x.bioAct = $(this).val().trim();
                arr.push(x);
            });
            return arr;
        }
        
        $("#SMILES").change(updateModel);
	    
	    $("#SMILES").on("keyup", updateModel);
	    
	   
	    
	    /*var typingTimer;                //timer identifier
	    var doneTypingInterval = 1000;  //time in ms, 5 second for example
	    var $input = $('#SMILES');

	    //on keyup, start the countdown
	    $input.on('keyup', function () {
	      clearTimeout(typingTimer);
	      typingTimer = setTimeout(updateModel, doneTypingInterval);
	    });

	    //on keydown, clear the countdown 
	    $input.on('keydown', function () {
	      clearTimeout(typingTimer);
	    });

	    //user is "finished typing," do something
	    function doneTyping () {
	    	generateStructure();
	    	$("#3d").attr("src", "3dmodel.html?nixon="+escape(jsmeApplet.molFile(false)));
	    }*/
	    
	    $("#compound").on("keydown keyup", function() {
	    	checkIfNull();
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
		
		function generateKey() {
			
			//console.log(jsmeApplet.getWebSearchInchiKeyBaseUrl());	
			if($('input[name="canSMILES"]').val() !== jsmeApplet.smiles()){
				console.log("f");
				update3d();
				$('input[name="canSMILES"]').val(jsmeApplet.smiles());
				
			}
			
		}
		
		function generateStructure() {
			var mol = $('input[name="canSMILES"]').val();
			jsmeApplet.readGenericMolecularInput(mol);
			//update3d();
			setTimeout(function() { update3d(); }, 500);
	    }

		function updateModel() {
	    	generateStructure();
	    	update3d();
		}
		
		function update3d() {
			//$("#3d").attr("src", "3dmodel.html?nixon="+escape(lzw_encode(jsmeApplet.molFile(false))));
			//molFile = jsmeApplet.molFile(false);
			gen3d();
			molFile = obMol3d;
			molecule = ChemDoodle.readMOL(molFile, 1);
			transformStick.loadMolecule(molecule);
			 /*	ChemDoodle.iChemLabs.optimize(molecule, {
	                dimension: 3
	            }, function(mol) {
	            	transformStick.loadMolecule(mol);
	            });*/
		}
		
		$(window).on('resize', function(){
	    	var w = $("#jsme_div").width();
	    	jsmeApplet.setSize(w+"px", "340px");
	    	transformStick.resize(w, 340);
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
		
		function checkIfNull(){
			var cc = $("#compound").val();
			if(cc!=""){
				$("#compoundHelp").css("display", "block");
			} else {
				$("#compoundHelp").css("display", "none");
			}
		}
		
		function validateForm() {
			if($('input[name="compound"]')[0].checkValidity())
    		
    		if($('input[name="pubCID"]')[0].checkValidity())
    		if($('input[name="molForm"]')[0].checkValidity())
    		if($('input[name="canSMILES"]')[0].checkValidity())
    		if($('input[name="inchi"]')[0].checkValidity())
    		if($('input[name="inchikey"]')[0].checkValidity())
    		if($('input[name="iupac"]')[0].checkValidity())
    		
    		if($('input[name="molWeight"]')[0].checkValidity())
    		if($('input[name="xlogp"]')[0].checkValidity())
    		if($('input[name="mass"]')[0].checkValidity())
    		if($('input[name="tpsa"]')[0].checkValidity())
    		if($('input[name="complexity"]')[0].checkValidity())
    		
    		if($('input[name="charge"]')[0].checkValidity())
    		if($('input[name="hBondDonor"]')[0].checkValidity())
    		if($('input[name="hBondAcceptor"]')[0].checkValidity())
    		if($('input[name="rotBondCount"]')[0].checkValidity()) {
	        	//$("#subBT").hide();
	        	
	        	
	        	$('input[name="bioActjson"]').val(JSON.stringify(mapRC()));
	        	
	        	$("#subBT").show();
	            $("#subBT").prop("disabled", true);
	            $("#subBT").html(
	              '<i class="fa fa-spinner fa-spin"></i>&#32;Saving...'
	            );
    		}
		}
		
		var obMol3d;
		
		function gen3d() {
			var OpenBabel = Kekule.OpenBabel.getModule();
			var smiles= jsmeApplet.smiles();
			var conv = new OpenBabel.ObConversionWrapper();
			conv.setInFormat('', 'smi');
			var mol = new OpenBabel.OBMol();
			conv.readString(mol, smiles);
			var gen3d = OpenBabel.OBOp.FindType('Gen3D');  // FindType is actually a factory constructor, no need to use "new" here
			if (!gen3d.Do(mol, ''))
			  console.error('Generate 3D failed');
			else
			{
			  conv.setOutFormat('', 'mol');
			  var outputData = conv.writeString(mol, false);
			  //console.log(outputData);
			  obMol3d = outputData;
			}
			conv.delete();
			mol.delete();
		}
		
		/*function lzw_encode(s) {
		    var dict = {};
		    var data = (s + "").split("");
		    var out = [];
		    var currChar;
		    var phrase = data[0];
		    var code = 256;
		    for (var i=1; i<data.length; i++) {
		        currChar=data[i];
		        if (dict[phrase + currChar] != null) {
		            phrase += currChar;
		        }
		        else {
		            out.push(phrase.length > 1 ? dict[phrase] : phrase.charCodeAt(0));
		            dict[phrase + currChar] = code;
		            code++;
		            phrase=currChar;
		        }
		    }
		    out.push(phrase.length > 1 ? dict[phrase] : phrase.charCodeAt(0));
		    for (var i=0; i<out.length; i++) {
		        out[i] = String.fromCharCode(out[i]);
		    }
		    return out.join("");
		}*/
	</script>

</body>
</html>