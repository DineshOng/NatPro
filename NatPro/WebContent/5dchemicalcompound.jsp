<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>NatPro - Materialize</title>

        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        
        
      	<script type="text/javascript" charset="utf8" src="js/jquery-3.5.1.min.js"></script>
       
        
        <script>
       
	        $(document).ready(function() {
	        	//$('#loading').hide();
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
		        				
		        				var arr = obj.syn;
		        				var syn = arr.split("@$@");
		        				var text = "";
		        				
		        				var i;
		        				for (i = 0; i < syn.length; i++) {
		        				  text += '<tr><td>' + syn[i] + "</td></tr>";
		        				}
		        				$('#syn').append(text);
		        				
		        				$('#pubchemBT').hide();
		        				$.LoadingOverlay("hide");
		        			}
	        				//$('#loading').hide();
	        				
	        			}
	        		});
	        	});
	        });
	        
        </script>
        
    </head>
    <body>
    
        <%@include file="includeNavBar.html"%>
        <div class="section green darken-1" id="index-banner">
            <div class="container">
                <!--<br><br>-->
                <h1 class="header center white-text" id="compound"><b>${compound.getCompoundName()}</b></h1>
                <div class="row center white-text">
                    <img id="compoundIMG" class="responsive-img circle" src="media/compound/b.svg">
                    <br><br>
                    <!-- href="RetrieveCompoundServet?compound=${compound.getCompoundName()}"  -->
                    <a id="pubchemBT" class="waves-effect waves-light btn green accent-4 white-text">Fill out information from pubchem</a>
                    <div id="loading" class="lds-ring"><div></div><div></div><div></div><div></div></div>
                </div>
                <!--
                    <div class="row center">
                        <a href="http://materializecss.com/getting-started.jsp" id="download-button" class="btn-large waves-effect waves-light green darken-3">Get Started</a>
                    </div>
                    <br><br>
                -->
            </div>
        </div>


        <div class="section" id="index-banner">
            <div class="container">
                <div class="row">
                    <div class="center">
                        <ul class="tabs">
                            <li class="tab col s3 black-text">
                                <a href="#genInfo" class="active flow-text">General Information</a>
                            </li>
                            <li class="tab col s3">
                                <a href="#chemInfo" class="flow-text">Chemical Information</a>
                            </li>
                            <li class="tab col s3">
                                <a href="#compSynonyms" class="flow-text">Compound Synonyms</a>
                            </li>
                            <li class="tab col s3">
                                <a href="#bioAct" class="flow-text">Biological Activities</a>
                            </li>
                        </ul>
                    </div>
                    <div id="genInfo" class="row center">
                        <div class="col s6 offset-s3">
                            <table class="highlight">
                                <thead>
                                    <tr>
                                        <td colspan=3>
                                            <h4 class="center">General Information</h4>
                                        </td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th>Common Name</th>
                                        <td>${compound.getCompoundName()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>IUPAC Name</th>
                                        <td id="iupac">${compound.getIupac()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>Canonical SMILES</th>
                                        <td id="canSMILES">${compound.getCanSMILES()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>Formula</th>
                                        <td id="molForm">${compound.getMolForm()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>InChl</th>
                                        <td id="inchi">${compound.getInchi()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>InChl key</th>
                                        <td id="inchikey">${compound.getInchikey()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>Chemical Class</th>
                                        <td>////</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="chemInfo" class="row center">
                        <div class="col s6 offset-s3">
                            <table class="highlight">
                                <thead>
                                    <tr>
                                        <td colspan=3>
                                            <h4 class="center">Chemical Information</h4>
                                        </td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th>Molecular Weight</th>
                                        <td id="molWeight">${compound.getMolWeight()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>XLogP</th>
                                        <td id="xlogp">${compound.getXlogp()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>TPSA</th>
                                        <td id="tpsa">${compound.getTpsa()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>H-Bond Acceptors</th>
                                        <td id="hBondAcceptor">${compound.gethBondAcceptor()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>H-Bond Donor</th>
                                        <td id="hBondDonor">${compound.gethBondDonor()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>Rotatable Bonds</th>
                                        <td id="rotBondCount">${compound.getRotBondCount()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="compSynonyms" class="row center">
                        <div class="col s6 offset-s3">
                            <table class="highlight">
                                <thead>
                                    <tr>
                                        <th>
                                           <h4>Compound Synonyms</h4> 
                                        </th>
                                    </tr>
                                </thead>
                                <tbody id="syn">
                                   <c:forEach items="${compound.getCompoundSynonyms()}" var="cs">
										<tr>
											<td>${cs}</td>
										</tr>
									</c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="bioAct" class="row center">
                        <div class="col s6 offset-s3">
                            <table class="highlight">
                                <thead>
                                    <tr>
                                        <th>
                                           <h4>Biological Activies</h4> 
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Biological Activities</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="section green accent-4" id="index-banner">
            <div class="container">
                <table class="highlight">
                <thead>
                    <tr>
                        <th>Plant Name</th>
                        <th>Genus</th>
                        <th>Family</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Plant Name</td>
                        <td>Genus</td>
                        <td>Family</td>
                    </tr>
                </tbody>
            </table>
            </div>
        </div>

        <%@include file="includeFooter.html"%>
		<%@include file="includeScripts.html"%>
		
		
		<script type="text/javascript" charset="utf8" src="js/loadingoverlay.min.js"></script>
		
		
        <script>
            $(document).ready(function(){
                $('.tabs').tabs();
            });
        </script>
    </body>
</html>