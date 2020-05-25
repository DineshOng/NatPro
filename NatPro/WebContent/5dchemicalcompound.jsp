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
    </head>
    <body>
        <%@include file="includeNavBar.html"%>
        <div class="section green darken-1" id="index-banner">
            <div class="container">
                <!--<br><br>-->
                <h1 class="header center white-text"><b>${compound.getCompoundName()}</b></h1>
                <div class="row center white-text">
                    <img class="responsive-img circle" src="media/compound/b.svg">
                    <br><br>
                    <a class="waves-effect waves-light btn green accent-4 white-text">Fill out information from pubchem</a>
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
                                        <td>${compound.getIupac()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>Canonical SMILES</th>
                                        <td>${compound.getCanSMILES()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>Formula</th>
                                        <td>${compound.getMolForm()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>InChl</th>
                                        <td>${compound.getInchi()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>InChl key</th>
                                        <td>${compound.getInchikey()}</td>
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
                                        <td>${compound.getMolWeight()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>XLogP</th>
                                        <td>${compound.getXlogp()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>TPSA</th>
                                        <td>${compound.getTpsa()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>H-Bond Acceptors</th>
                                        <td>${compound.gethBondAcceptor()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>H-Bond Donor</th>
                                        <td>${compound.gethBondDonor()}</td>
                                        <td><i class="material-icons">edit</i></td>
                                    </tr>
                                    <tr>
                                        <th>Rotatable Bonds</th>
                                        <td>${compound.getRotBondCount()}</td>
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
                                <tbody>
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
        <script>
            $(document).ready(function(){
                $('.tabs').tabs();
            });
        </script>
    </body>
</html>