<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>NatPro - Materialize</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
</head>
<body>
	<%@include file="includeNavBar.html"%>
	<div class="section" id="index-banner">
		<div class="container row center">
			<h5>Plant Entry</h5>
			<h3>${medPlantsList.get(0).getMedicinalPlant()}</h3>
		</div>
		<div class="row">
			<div class="col s12">
				<ul class="tabs">
					<li class="tab col s2"><a href="#taxInfo">Taxonomic
							Information</a></li>
					<li class="tab col s2"><a href="#plantName">Scientific/Common
							Name(s)</a></li>
					<li class="tab col s2"><a href="#location">Location(s)</a></li>
					<li class="tab col s2"><a href="#bioAct">Biological
							Activities</a></li>
					<li class="tab col s2"><a href="#prep">Preparation(s)</a></li>
					<li class="tab col s2"><a href="#chemComp">Chemical
							Compound(s)</a></li>
				</ul>
			</div>
			<div id="taxInfo" class="row center">
				<div class="col s6 offset-s3">
					<table class="highlight">
						<thead>
							<tr>
								<th colspan="2" class="center"><h4>Taxonomic
										Information</h4></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th id="tableSmallWidth" class="center">Family</th>
								<td class="center">${medPlantsList.get(0).getSpecies().get(0).getFamily()}</td>
							</tr>
							<tr>
								<th id="tableSmallWidth" class="center">Genus</th>
								<td class="center">${medPlantsList.get(0).getSpecies().get(0).getGenus()}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="plantName" class="row center">
				<div class="col s4 offset-s2">
					<table class="highlight centered">
						<thead>
							<tr>
								<th><h4>Scientific Name(s)</h4></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${medPlantsList.get(0).getSpecies()}"
									var="speciesList">
									<tr>
										<td>${speciesList.getSpecie()}</td>
									</tr>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col s4">
					<table class="highlight centered">
						<thead>
							<tr>
								<th><h4>Common Name(s)</h4></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${medPlantsList.get(0).getSpecies()}">
									<tr>
										<td>${medPlantsList.get(0).getMedicinalPlant()}</td>
									</tr>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="location" class="row center">
				<div class="col s4 offset-s4">
					<table class="highlight centered">
						<thead>
							<tr>
								<th><h4>Location(s)</h4></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${medPlantsList.get(0).getLocations()}"
								var="locationsList">
								<tr>
									<td>${locationsList}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div id="bioAct" class="row center">
				<div class="col s6 offset-s3">
					<table class="highlight centered">
						<thead>
							<tr>
								<th><h4>Biological Activities</h4></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>biological activity</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="prep" class="row center">
				<div class="col s10 offset-s1">
					<table class="highlight">
						<thead>
							<tr>
								<th colspan="4"><h4>Preparation</h4></th>
							</tr>
							<tr>
								<th>Preparation</th>
								<th>Illness</th>
								<th>Body Part</th>
								<th>Plant Part</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>preparation</td>
								<td>illness</td>
								<td>body part</td>
								<td>plant part</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="chemComp" class="row center">
				<div class="col s12">
					<table class="highlight centered">
						<thead>
							<tr>
								<th colspan="9"><h4>Chemical Compounds</h4></th>
							</tr>
							<tr>
								<th>Structure</th>
								<th>Name</th>
								<th>Formula</th>
								<th>Molecular Weight</th>
								<th>XLoGP</th>
								<th>TPSA</th>
								<th># HBA</th>
								<th># HDB</th>
								<th># Rotatable Bonds</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Structure</td>
								<td>Name</td>
								<td>Formula</td>
								<td>Molecular Weight</td>
								<td>XLoGP</td>
								<td>TPSA</td>
								<td># HBA</td>
								<td># HDB</td>
								<td># Rotatable Bonds</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


	<div class="section green accent-4">
		<div class="container row center">
			<div class="col s6 offset-s3">
				<table class="highlight centered">
					<thead>
						<tr>
							<th colspan="2"><h4>References</h4></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Document</td>
							<td><a class="waves-effect waves-light btn">View
									Docuument</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<%@include file="includeFooter.html"%>
	<%@include file="includeScripts.html"%>
	<script>
		$(document).ready(function() {
			$('.tabs').tabs();
		});
	</script>
</body>
</html>