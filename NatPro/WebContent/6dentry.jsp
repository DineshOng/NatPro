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
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link href="css/autocomplete.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<title>NatPro : ${searchKey}</title>
</head>
<body>
	<!-- INCLUDE NAV BAR HTML -->
	<%@include file="navbarnix.html"%>

	<div class="jumbotron bg-success">
		<div class="d-flex justify-content-center">
			<h1 class="display-4 text-white" style="display: block">
				<p id="medPlantName">${medPlantsList.get(0).getMedicinalPlant()}</p>
			</h1>
			<input class="form-control w-auto h-25 mt-3" type="text"
				id="editMedPlantField" name="editMedPlantName" style="display: none"
				required>
			<button type="button" class="btn btn-outline-light btn-sm h-25 mt-3"
				style="display: none" onclick="editMedPlant()" data-toggle="tooltip"
				data-trigger="hover" data-placement="top" title="edit plant name"
				id="editMedPlantBtn">
				<i class="fa fa-pencil" aria-hidden="true" id="editMedPlantLogo"></i>
			</button>
			<button type="button" class="btn btn-outline-danger btn-sm h-25 mt-3"
				onclick="cancelEditMedPlant()" data-toggle="tooltip"
				data-trigger="hover" data-placement="top" title="cancel"
				id="cancelEditMedPlantBtn" style="display: none">
				<i class="fa fa-times" aria-hidden="true"></i>
			</button>
		</div>
		<h3 class="text-white text-center">Plant</h3>

		<div class="d-flex flex-row-reverse bd-highlight">
			<button type="button" class="btn btn-outline-light btn-sm h-25 mt-3"
				onclick="editEntry()" data-toggle="tooltip" data-placement="top"
				title="edit entry" id="editEntryBtn" data-trigger="hover">
				<i class="fa fa-pencil" aria-hidden="true" id="editEntryLogo"></i>
				<p style="display: inline;" id="editEntryName">EDIT</p>
			</button>
		</div>
		<div class="d-flex flex-row-reverse bd-highlight"
			style="margin-top: 40px; margin-bottom: -50px;">
			<!-- Button trigger modal -->
			<button style="display: none" id="deletePlantBtn" type="button"
				class="btn btn-danger" data-toggle="modal"
				data-target="#deletePlantModal">
				<i class="fa fa-trash" id="deletePlantLogo" aria-hidden="true"></i>
				Delete Plant
			</button>
		</div>


		<!-- Modal -->
		<div class="modal fade" id="deletePlantModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Deleting Plant</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">Are you sure you want to delete plant
						${medPlantsList.get(0).getMedicinalPlant()}?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-outline-secondary"
							data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-danger"
							onclick="deletePlant('${medPlantsList.get(0).getMedicinalPlant()}')">Delete</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="d-flex flex-row list-group text-center ">
		<a
			class="list-group-item list-group-item-action list-group-item-success active"
			id="taxInfoTab" data-toggle="list" href="#taxInfo" role="tab"
			aria-controls="TaxonomicInformation">Taxonomic Information</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="sciNameTab" data-toggle="list" href="#plantName" role="tab"
			aria-controls="ScientificName">Scientific Name(s)</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="locTab" data-toggle="list" href="#location" role="tab"
			aria-controls="Location">Location(s)</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="bioActTab" data-toggle="list" href="#bioAct" role="tab"
			aria-controls="BiologicalActivities">Biological Activities</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="prepTab" data-toggle="list" href="#prep" role="tab"
			aria-controls="Preparation">Preparation(s)</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="chemCompTab" data-toggle="list" href="#chemComp" role="tab"
			aria-controls="ChemicalCompounds">Chemical Compound(s)</a> <a
			class="list-group-item list-group-item-action list-group-item-success"
			id="refTab" data-toggle="list" href="#ref" role="tab"
			aria-controls="Reference">Reference</a>
	</div>
	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="taxInfo" role="tabpanel"
			aria-labelledby="list-home-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-auto">
					<thead>
						<tr>
							<td>
								<h4>Taxonomic Information</h4>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Genus</th>
							<td class="center"><a id="genus" style="display: inline"
								href="ViewGenusServlet?genus=${medPlantsList.get(0).getSpecies().get(0).getGenus()}">
									<p style="display: inline;" id="genusName">${medPlantsList.get(0).getSpecies().get(0).getGenus()}</p>
							</a>
								<div class="autocomplete" style="width: 300px; display: none"
									id="genusInputDiv">
									<input type="text" id="genusInput" name="genus"
										placeholder="Genus">
								</div> <i id="editGenusTip" style="display: none;"
								class="fa fa-info-circle" data-toggle="popover"
								data-placement="top" data-trigger="hover"
								data-content="You may edit this genus in the genus page."></i>
								<button type="button" class="btn btn-outline-dark btn-sm "
									style="display: none" onclick="editGenus()"
									data-toggle="tooltip" data-trigger="hover" data-placement="top"
									title="select the genus this plant belongs to"
									id="editGenusBtn">
									<i class="fa fa-pencil" aria-hidden="true" id="editGenusLogo"></i>
								</button>
								<button type="button" class="btn btn-outline-danger btn-sm"
									onclick="cancelEditGenus()" data-toggle="tooltip"
									data-trigger="hover" data-placement="top" title="cancel"
									id="cancelEditGenusBtn" style="visibility: hidden">
									<i class="fa fa-times" aria-hidden="true"></i>
								</button></td>
						</tr>
						<tr>
							<th>Family</th>
							<td class="center"><a id="family" style="display: inline"
								href="ViewFamilyServlet?family=${medPlantsList.get(0).getSpecies().get(0).getFamily()}">
									<p style="display: inline;" id="familyName">${medPlantsList.get(0).getSpecies().get(0).getFamily()}
									</p>
							</a> <i id="editFamilyTip" style="display: none;"
								class="fa fa-info-circle" data-toggle="popover"
								data-placement="top" data-trigger="hover"
								data-content="You may edit this family in the family page."></i>
								<!-- <div class="autocomplete" style="width: 300px; display: none"
									id="familyInputDiv">
									<input type="text" id="familyInput" name="family"
										placeholder="Family">
								</div>
								<button type="button" class="btn btn-outline-dark btn-sm "
									onclick="editFamily()" data-toggle="tooltip"
									data-placement="top" title="edit entry" class="btn btn-primary"
									id="editFamilyBtn">
									<i class="fa fa-pencil" aria-hidden="true" id="editFamilyLogo"></i>
								</button>
								<button type="button" class="btn btn-outline-danger btn-sm"
									onclick="cancelEditFamily()" data-toggle="tooltip"
									data-placement="top" title="edit entry" class="btn btn-primary"
									id="cancelEditFamilyBtn" style="visibility: hidden">
									<i class="fa fa-times" aria-hidden="true"></i>
								</button> --></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="plantName" role="tabpanel"
			aria-labelledby="list-profile-list">
			<div class="d-flex justify-content-center">
				<table class="table table-hover w-auto text-center">
					<thead>
						<tr>
							<td>
								<h4>Scientific Name(s)</h4>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach items="${medPlantsList.get(0).getSpecies()}"
								var="speciesList">
								<tr>
									<td><c:set var="contains" value="false" /> <c:set
											var="noIndiv" value="true" /> <c:forEach var="synNoIndiv"
											items="${synNoIndiv}">
											<c:if test="${synNoIndiv eq speciesList.getSpecie()}">
												<c:set var="contains" value="true" />
											</c:if>
										</c:forEach> <c:choose>
											<c:when test="${not contains}">
												<c:set var="noIndiv" value="false" />
												<a id="specie" style="display: inline"
													href="ViewSciPlantServlet?specie=${speciesList.getSpecie()}">
													<i><p style="display: inline;" id="specieName">${speciesList.getSpecie()}</p></i>
												</a>
												<div class="autocomplete"
													style="width: 300px; display: none" id="specieInputDiv">
													<input type="text" id="specieInput" name="specie"
														placeholder="Scientific Name">
												</div>
												<i id="editSpecieTip" style="display: none;"
													class="fa fa-info-circle" data-toggle="popover"
													data-placement="top" data-trigger="hover"
													data-content="You may edit this name in the scientific name page."></i>
												<button type="button" class="btn btn-outline-dark btn-sm "
													style="display: none" onclick="editSpecie()"
													data-toggle="tooltip" data-trigger="hover"
													data-placement="top"
													title="select this plant's scientific name"
													id="editSpecieBtn">
													<i class="fa fa-pencil" aria-hidden="true"
														id="editSpecieLogo"></i>
												</button>
												<button type="button" class="btn btn-outline-danger btn-sm"
													onclick="cancelEditSpecie()" data-toggle="tooltip"
													data-trigger="hover" data-placement="top" title="cancel"
													id="cancelEditSpecieBtn" style="display: none">
													<i class="fa fa-times" aria-hidden="true"></i>
												</button>
											</c:when>
											<c:otherwise>
												<i><p style="display: inline;" id="specieName2">${speciesList.getSpecie()}</p></i>
											</c:otherwise>
										</c:choose></td>
								</tr>

							</c:forEach>
						</tr>
						<c:if test="${noIndiv}">
							<tr>
								<td><p id="specie" style="display: inline">
										<i><p style="display: inline;" id="specieName"></p></i>
									</p>
									<div class="autocomplete" style="width: 300px; display: none"
										id="specieInputDiv">
										<input type="text" id="specieInput" name="specie"
											placeholder="Scientific Name">
									</div> <i id="editSpecieTip" style="display: none;"
									class="fa fa-info-circle" data-toggle="popover"
									data-placement="top" data-trigger="hover"
									data-content="You may edit this name in the scientific name page."></i>
									<button type="button" class="btn btn-outline-dark btn-sm "
										style="display: none" onclick="editSpecie()"
										data-toggle="tooltip" data-trigger="hover"
										data-placement="top"
										title="select this plant's scientific name" id="editSpecieBtn">
										<i class="fa fa-pencil" aria-hidden="true" id="editSpecieLogo"></i>
									</button>
									<button type="button" class="btn btn-outline-danger btn-sm"
										onclick="cancelEditSpecie()" data-toggle="tooltip"
										data-trigger="hover" data-placement="top" title="cancel"
										id="cancelEditSpecieBtn" style="display: none">
										<i class="fa fa-times" aria-hidden="true"></i>
									</button></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="location" role="tabpanel"
			aria-labelledby="list-messages-list">
			<div class="d-flex justify-content-center mt-5 pb-5">
				<table id="table_id_loc"
					class="table table-striped table-bordered text-center"
					style="width: 100%">
					<thead>
						<tr>
							<td>
								<h4>Location(s)</h4>
							</td>
						</tr>
					</thead>
					<tbody>
						<c:set var="locNum" value="1" scope="page" />
						<c:forEach items="${medPlantsList.get(0).getLocations()}"
							var="locationsList">
							<tr>
								<td><p style="display: inline;" id="locName${locNum}">${locationsList}</p>
									<div style="display: inline; float: right;">
										<button type="button" class="btn btn-outline-danger btn-sm "
											style="display: none" onclick="removeLoc(${locNum})"
											data-toggle="tooltip" data-trigger="hover"
											data-placement="top" title="remove location"
											id="removeLocBtn${locNum}">
											<i class="fa fa-trash" id="removeLocLogo" aria-hidden="true"></i>
										</button>
									</div></td>
							</tr>
							<c:set var="locNum" value="${locNum + 1}" scope="page" />
						</c:forEach>
						<tr>
							<td>
								<div class="justify-content-center">
									<div class="autocomplete" style="width: 300px; display: none"
										id="locInputDiv">
										<input type="text" id="locInput" name="loc"
											placeholder="Location">
									</div>
									<button type="button" class="btn btn-outline-dark btn-sm "
										style="display: none" onclick="addLoc()" data-toggle="tooltip"
										data-trigger="hover" data-placement="top" title="add location"
										id="addLocBtn">
										<i class="fa fa-plus" id="addLocLogo" aria-hidden="true"></i>
										Location
									</button>
									<button type="button" class="btn btn-outline-danger btn-sm"
										onclick="cancelAddLoc()" data-toggle="tooltip"
										data-trigger="hover" data-placement="top" id="cancelAddLocBtn"
										title="cancel" style="display: none">
										<i class="fa fa-times" aria-hidden="true"></i>
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="bioAct" role="tabpanel"
			aria-labelledby="list-settings-list">
			<div class="d-flex justify-content-center mt-5 pb-5">
				<table id="table_id_bioact"
					class="table table-striped table-bordered" style="width: 100%">
					<thead>
						<tr>
							<th colspan="3"><h4>
									Biological Activities <i id="editBioActTip"
										style="display: none;" class="fa fa-info-circle"
										data-toggle="popover" data-placement="top"
										data-trigger="hover"
										data-content="You may edit the biological activity/s in the compound page."></i>
								</h4></th>
						</tr>
						<tr>
							<th>Chemical Compound</th>
							<th>Biological Activity</th>
							<th>Cell Line</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${medPlantsList.get(0).getSpecies()}"
							var="speciesList">
							<c:forEach items="${speciesList.getSpeciesParts()}"
								var="speciesPartList">
								<c:forEach items="${speciesPartList.getCompounds()}"
									var="compoundsList">
									<c:forEach items="${compoundsList.getBioActs()}"
										var="bioActsList">
										<tr>
											<td><a
												href="ViewCompoundServlet?compound=${compoundsList.getCompoundName()}">${compoundsList.getCompoundNameHTML()}</a></td>
											<td>${bioActsList.getBiologicalActivity()}</td>
											<td><a href="https://www.google.com/search?q=${bioActsList.getCellLine().getCellLine()}" target="_blank">${bioActsList.getCellLine().getCellLine()}</a></td>
										</tr>
									</c:forEach>
								</c:forEach>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="prep" role="tabpanel"
			aria-labelledby="list-settings-list">
			<div class="d-flex justify-content-center mt-5 pb-5">
				<table id="table_id_prep" class="table table-striped table-bordered"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="3"><h4>Preparation</h4></th>
						</tr>
						<tr>
							<th>Preparation</th>
							<th>Utilized Plant Part</th>
							<th>Illness</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="prepNum" value="1" scope="page" />
						<c:forEach items="${medPlantsList.get(0).getPreparations()}"
							var="prepList">
							<c:forEach items="${prepList.getIllness()}" var="illnessList">
								<tr>
									<td><p style="display: inline;" id="prepName${prepNum}">${prepList.getPreparation()}</p>
										<div class="autocomplete" style="width: 300px; display: none"
											id="prepInputDiv${prepNum}">
											<input type="text" id="prepInput${prepNum}" name="prep"
												style="width: 600px;" placeholder="Preparation">
										</div></td>
									<td><p style="display: inline;"
											id="plantPartName${prepNum}">${prepList.getUtilizedPlantPart()}</p>
										<div class="autocomplete" style="width: 300px; display: none"
											id="plantPartInputDiv${prepNum}">
											<input type="text" id="plantPartInput${prepNum}"
												name="plantPart" placeholder="Plant Part">
										</div></td>
									<td><p style="display: inline;" id="illnessName${prepNum}">${illnessList}</p>
										<div class="autocomplete" style="width: 300px; display: none"
											id="illnessInputDiv${prepNum}">
											<input type="text" id="illnessInput${prepNum}" name="illness"
												placeholder="Illness">
										</div>
										<div style="display: inline; float: right;">
											<button type="button" class="btn btn-outline-dark btn-sm "
												style="display: none;" onclick="editPrep(${prepNum})"
												data-toggle="tooltip" data-trigger="hover"
												data-placement="top" title="edit preparation"
												id="editPrepBtn${prepNum}">
												<i class="fa fa-pencil" aria-hidden="true"
													id="editPrepLogo${prepNum}"></i>
											</button>
											<button type="button" class="btn btn-outline-danger btn-sm"
												onclick="cancelEditPrep(${prepNum})" data-toggle="tooltip"
												data-trigger="hover" data-placement="top" title="cancel"
												id="cancelEditPrepBtn${prepNum}" style="visibility: hidden">
												<i class="fa fa-times" aria-hidden="true"></i>
											</button>
										</div></td>
								</tr>
								<c:set var="prepNum" value="${prepNum + 1}" scope="page" />
							</c:forEach>
						</c:forEach>
						<tr>
							<td><p style="display: inline;" id="prepName${prepNum}"></p>
								<div class="autocomplete" style="width: 300px; display: none"
									id="prepInputDiv${prepNum}">
									<input type="text" id="prepInput${prepNum}" name="prep"
										style="width: 600px;" placeholder="Preparation">
								</div></td>
							<td><p style="display: inline;" id="plantPartName${prepNum}"></p>
								<div class="autocomplete" style="width: 300px; display: none"
									id="plantPartInputDiv${prepNum}">
									<input type="text" id="plantPartInput${prepNum}"
										name="plantPart" placeholder="Plant Part">
								</div></td>
							<td><p style="display: inline;" id="illnessName${prepNum}"></p>
								<div class="autocomplete" style="width: 300px; display: none"
									id="illnessInputDiv${prepNum}">
									<input type="text" id="illnessInput${prepNum}" name="illness"
										placeholder="Illness">
								</div>
								<button type="button" class="btn btn-outline-dark btn-sm "
									style="display: none" onclick="addPrep(${prepNum})"
									data-toggle="tooltip" data-trigger="hover" data-placement="top"
									title="add preparation" id="addPrepBtn">
									<i class="fa fa-plus" id="addPrepLogo" aria-hidden="true"></i>
									Preparation
								</button>
								<button type="button" class="btn btn-outline-danger btn-sm"
									onclick="cancelAddPrep(${prepNum})" data-toggle="tooltip"
									data-placement="top" id="cancelAddPrepBtn" title="cancel"
									style="display: none">
									<i class="fa fa-times" aria-hidden="true"></i>
								</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="chemComp" role="tabpanel"
			aria-labelledby="list-settings-list">
			<div class="d-flex justify-content-center mt-5 pb-5">
				<table id="table_id_chem" class="table table-striped table-bordered"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="11"><h4>Chemical Compounds</h4></th>
						</tr>
						<tr>
							<th>Plant Part</th>
							<th>Structure</th>
							<th>Name</th>
							<th>Formula</th>
							<th>Molecular Weight</th>
							<th>XLoGP</th>
							<th>TPSA</th>
							<th># HBA</th>
							<th># HDB</th>
							<th># Rotatable Bonds</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:set var="compNum" value="1" scope="page" />
						<c:forEach items="${medPlantsList.get(0).getSpecies()}"
							var="speciesList">
							<c:forEach items="${speciesList.getSpeciesParts()}"
								var="speciesPartList">
								<c:forEach items="${speciesPartList.getCompounds()}"
									var="compoundsList">
									<tr>
										<td><p style="display: inline;"
												id="plantPartCompName${compNum}">${speciesPartList.getPlantPart()}</p>
											<div class="autocomplete" style="width: 300px; display: none"
												id="plantPartCompInputDiv${compNum}">
												<input type="text" id="plantPartCompInput${compNum}"
													name="plantPartComp" placeholder="Plant Part">
											</div></td>
										<td></td>
										<td><a
											href="ViewCompoundServlet?compound=${compoundsList.getCompoundName()}"><p
													style="display: inline;" id="chemCompName${compNum}">${compoundsList.getCompoundNameHTML()}</p></a>
											<div class="autocomplete" style="width: 300px; display: none"
												id="chemCompInputDiv${compNum}">
												<input type="text" id="chemCompInput${compNum}"
													name="chemComp" placeholder="Chemical Compound">
											</div> <i id="editSpecieTip${compNum}" style="display: none;"
											class="fa fa-info-circle" data-toggle="popover"
											data-placement="top" data-trigger="hover"
											data-content="You may edit this compound's attributes in the compound page."></i></td>
										<td>${compoundsList.getMolForm()}</td>
										<td>${compoundsList.getMolWeight()}</td>
										<td>${compoundsList.getXlogp()}</td>
										<td>${compoundsList.getTpsa()}</td>
										<td>${compoundsList.getHBondAcceptor()}</td>
										<td>${compoundsList.getHBondDonor()}</td>
										<td>${compoundsList.getRotBondCount()}</td>
										<td><button type="button"
												class="btn btn-outline-dark btn-sm " style="display: none"
												onclick="editComp(${compNum})" data-toggle="tooltip"
												data-trigger="hover" data-placement="top"
												title="edit compound" id="editCompBtn${compNum}">
												<i class="fa fa-pencil" aria-hidden="true"
													id="editCompLogo${compNum}"></i>
											</button>
											<button type="button" class="btn btn-outline-danger btn-sm"
												onclick="cancelEditComp(${compNum})" data-toggle="tooltip"
												data-trigger="hover" data-placement="top" title="cancel"
												id="cancelEditCompBtn${compNum}" style="display: none">
												<i class="fa fa-times" aria-hidden="true"></i>
											</button></td>
									</tr>
									<c:set var="compNum" value="${compNum + 1}" scope="page" />
								</c:forEach>
							</c:forEach>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>

		<div class="tab-pane fade" id="ref" role="tabpanel"
			aria-labelledby="list-settings-list">
			<div class="d-flex justify-content-center mt-5 pb-5">
				<c:if
					test="${not empty medPlantsList.get(0).getSpecies().get(0).getDocument()}">
					<button type="button" class="btn btn-primary btn-sm"
						data-toggle="modal" data-target="#docuModal"
						onclick="viewDocument('${medPlantsList.get(0).getSpecies().get(0).getDocument()}')">View
						Document</button>
				</c:if>
			</div>
		</div>
		<!-- Document Modal -->
		<div class="modal fade" id="docuModal" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-xl">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="docuModalLabel">Document</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="embed-responsive embed-responsive-16by9">
							<iframe id="documentIframe" class="embed-responsive-item" src=""></iframe>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- INCLUDE FOOTER HTML -->
	<%@include file="_includeFooter.html"%>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<!-- <script type="text/javascript"
		src="DataTables/jQuery-3.3.1/jquery-3.3.1.min.js"></script> -->
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
	    function viewDocument(docu) {
	    	var entryDocument = "\\NatPro\\Documents\\UploadedDocuments\\"+docu;
	    	var modalIframe = document.getElementById('documentIframe');
	    	
	    	if (entryDocument != null) {
	    		console.log(entryDocument);
	    		modalIframe.src = entryDocument;
	    	} else {
	    		modalIframe.src = '';
	    	}
	    }
	</script>

	<script>
	$(function () {
		  $('[data-toggle="tooltip"]').tooltip();
		  $('[data-toggle="popover"]').popover();
		})
	</script>
	<!-- Script for Edit Plant -->
	<script type="text/javascript">
	function editMedPlant(){
		console.log("edit");
		document.getElementById("medPlantName").style.display="none";
		document.getElementById("editMedPlantField").value = document.getElementById("medPlantName").innerHTML;
		document.getElementById("editMedPlantField").style.display="block";
		document.getElementById("editMedPlantBtn").onclick = function () { saveEditMedPlant(); };
		document.getElementById("editMedPlantLogo").classList.remove("fa-pencil");
		document.getElementById("editMedPlantLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditMedPlantBtn").style.display="block";
	}
	
	function cancelEditMedPlant(){
		console.log("cancel");
		document.getElementById("medPlantName").style.display="block";
		document.getElementById("editMedPlantField").style.display="none";   
		document.getElementById("editMedPlantLogo").classList.remove("fa-check");
		document.getElementById("editMedPlantLogo").classList.add("fa-pencil"); 
		document.getElementById("editMedPlantBtn").onclick = function () { editMedPlant(); };	
		document.getElementById("cancelEditMedPlantBtn").style.display="none";
	}
	
	function saveEditMedPlant(){
		console.log("save");
		var newMedPlantNameVal = document.getElementById("editMedPlantField").value;
		var oldMedPlantNameVal = document.getElementById("medPlantName").innerHTML;
	
		$.ajax({
			type : "GET",
			url : 'EditMedPlant',
			dataType: "text",
			data: { 
				    newMedPlantName: newMedPlantNameVal,
				    oldMedPlantName: oldMedPlantNameVal
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Plant Name Successfully Edited"){
					document.getElementById("editMedPlantField").style.display="none";
					document.getElementById("medPlantName").innerHTML = newMedPlantNameVal; 
					document.getElementById("medPlantName").style.display="block";
					document.getElementById("editMedPlantBtn").onclick = function () { editMedPlant(); };		
					document.getElementById("editMedPlantLogo").classList.remove("fa-check");
					document.getElementById("editMedPlantLogo").classList.add("fa-pencil"); 
					document.getElementById("cancelEditMedPlantBtn").style.display="none";
					window.location.href = "ViewPlantServlet?medPlant="+newMedPlantNameVal; 
				}else{
					cancelEditMedPlant();
				}
			}
			});
		
	}
	
	</script>

	<!-- Script for Edit Genus -->
	<script type="text/javascript">
	function editGenus(){
		document.getElementById("genus").style.display="none";
		document.getElementById("genusInput").value = document.getElementById("genusName").innerHTML.trim();
		document.getElementById("genusInputDiv").style.display= "inline";
		document.getElementById("editGenusBtn").onclick = function () { saveGenus(); };
		document.getElementById("editGenusBtn").classList.remove("btn-outline-dark");
		document.getElementById("editGenusBtn").classList.add("btn-success");
		document.getElementById("editGenusLogo").classList.remove("fa-pencil");
		document.getElementById("editGenusLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditGenusBtn").style.visibility="visible";
	}
	
	function cancelEditGenus(){
		document.getElementById("genus").style.display="inline";
		document.getElementById("genusInputDiv").style.display="none";   
		document.getElementById("editGenusLogo").classList.remove("fa-check");
		document.getElementById("editGenusLogo").classList.add("fa-pencil"); 
		document.getElementById("editGenusBtn").onclick = function () { editGenus(); };	
		document.getElementById("editGenusBtn").classList.remove("btn-success");
		document.getElementById("editGenusBtn").classList.add("btn-outline-dark");
		document.getElementById("cancelEditGenusBtn").style.visibility="hidden";
	}
	
	function saveGenus(){
		var newGenusVal = document.getElementById("genusInput").value;
		var oldGenusVal = document.getElementById("genusName").innerHTML.trim();
		var sciNameVal = document.getElementById("specieName").innerHTML.trim();
		var medPlantName = document.getElementById("medPlantName").innerHTML.trim();
		
		console.log(newGenusVal);
		console.log(oldGenusVal);
 		$.ajax({
			type : "GET",
			url : 'EditGenus',
			dataType: "text",
			data: {
				newGenusVal: newGenusVal,
				oldGenusVal: oldGenusVal,
				sciNameVal: sciNameVal
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Genus Successfully Edited"){
					document.getElementById("genusInputDiv").style.display="none";
					document.getElementById("genusName").innerHTML = newGenusVal; 
					document.getElementById("genus").style.display="inline";
					document.getElementById("editGenusBtn").onclick = function () { editMedPlant(); };
					document.getElementById("editGenusBtn").classList.remove("btn-success");
					document.getElementById("editGenusBtn").classList.add("btn-outline-dark");
					document.getElementById("editGenusLogo").classList.remove("fa-check");
					document.getElementById("editGenusLogo").classList.add("fa-pencil"); 
					document.getElementById("cancelEditGenusBtn").style.visibility="hidden";
					window.location.href = "ViewPlantServlet?medPlant="+medPlantName; 
				}else{
					cancelEditGenus();
				}
			}
			}); 
		
	}
	</script>

	<!-- Script for Edit Specie -->
	<script type="text/javascript">
	function editSpecie(){
		document.getElementById("specie").style.display="none";
		document.getElementById("specieInput").value = document.getElementById("specieName").innerHTML.trim();
		document.getElementById("specieInputDiv").style.display= "inline";
		document.getElementById("editSpecieBtn").onclick = function () { saveSpecie(); };
		document.getElementById("editSpecieBtn").classList.remove("btn-outline-dark");
		document.getElementById("editSpecieBtn").classList.add("btn-success");
		document.getElementById("editSpecieLogo").classList.remove("fa-pencil");
		document.getElementById("editSpecieLogo").classList.add("fa-check");  
		
		document.getElementById("cancelEditSpecieBtn").style.display= "inline";
	}
	
	function cancelEditSpecie(){
		document.getElementById("specie").style.display="inline";
		document.getElementById("specieInputDiv").style.display="none";   
		document.getElementById("editSpecieLogo").classList.remove("fa-check");
		document.getElementById("editSpecieLogo").classList.add("fa-pencil"); 
		document.getElementById("editSpecieBtn").onclick = function () { editSpecie(); };	
		document.getElementById("editSpecieBtn").classList.remove("btn-success");
		document.getElementById("editSpecieBtn").classList.add("btn-outline-dark");
		document.getElementById("cancelEditSpecieBtn").style.display="none";
	}
	
	function saveSpecie(){
		var newSpecieVal = document.getElementById("specieInput").value.trim();
		var oldSpecieVal = document.getElementById("specieName").innerHTML.trim();
		var medPlantName = document.getElementById("medPlantName").innerHTML.trim();
		
		console.log(newSpecieVal);
		console.log(oldSpecieVal);
 		$.ajax({
			type : "GET",
			url : 'EditSci',
			dataType: "text",
			data: {
				newSpecieVal: newSpecieVal,
				oldSpecieVal: oldSpecieVal,
				medPlantName: medPlantName
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Scientific Name Successfully Edited"){
					document.getElementById("specieInputDiv").style.display="none";
					document.getElementById("specieName").innerHTML = newSpecieVal; 
					document.getElementById("specie").style.display="inline";
					document.getElementById("editSpecieBtn").onclick = function () { editMedPlant(); };
					document.getElementById("editSpecieBtn").classList.remove("btn-success");
					document.getElementById("editSpecieBtn").classList.add("btn-outline-dark");
					document.getElementById("editSpecieLogo").classList.remove("fa-check");
					document.getElementById("editSpecieLogo").classList.add("fa-pencil"); 
					document.getElementById("cancelEditSpecieBtn").style.display="none";
					window.location.href = "ViewPlantServlet?medPlant="+medPlantName; 
				}else{
					cancelEditSpecie();
				}
			}
			}); 
		
	}
	</script>

	<!-- Script for Edit Genus -->
	<script type="text/javascript">
	function editPrep(prepNum){
		var editBtnId = "editPrepBtn"+prepNum;
		var editLogoId = "editPrepLogo"+prepNum;
		var cancelEditBtnId = "cancelEditPrepBtn"+prepNum;
		
		var prepInputDivId = "prepInputDiv"+prepNum;
		var plantPartInputDivId = "plantPartInputDiv"+prepNum;
		var illnessInputDivId = "illnessInputDiv"+prepNum;
		
		var prepInputId = "prepInput"+prepNum;
		var plantPartInputId = "plantPartInput"+prepNum;
		var illnessInputId = "illnessInput"+prepNum;
		
				
		var prepNameId = "prepName"+prepNum;
		var plantPartNameId = "plantPartName"+prepNum;
		var illnessNameId = "illnessName"+prepNum;
		
		document.getElementById(editBtnId).onclick = function () { savePrep(prepNum); };
		
		document.getElementById(editBtnId).classList.remove("btn-outline-dark");
		document.getElementById(editBtnId).classList.add("btn-success");
		
		document.getElementById(editLogoId).classList.remove("fa-pencil");
		document.getElementById(editLogoId).classList.add("fa-check");  
		
		document.getElementById(prepInputDivId).style.display= "inline";	
		document.getElementById(plantPartInputDivId).style.display= "inline";		
		document.getElementById(illnessInputDivId).style.display= "inline";		
		
		document.getElementById(prepNameId).style.display="none";
		document.getElementById(plantPartNameId).style.display="none";
		document.getElementById(illnessNameId).style.display="none";
		
		document.getElementById(prepInputId).value = document.getElementById(prepNameId).innerHTML.trim();
		document.getElementById(plantPartInputId).value = document.getElementById(plantPartNameId).innerHTML.trim();
		document.getElementById(illnessInputId).value = document.getElementById(illnessNameId).innerHTML.trim();
		
		document.getElementById(cancelEditBtnId).style.visibility="visible"; 
		
	}
		
	function cancelEditPrep(prepNum){
		var editBtnId = "editPrepBtn"+prepNum;
		var editLogoId = "editPrepLogo"+prepNum;
		var cancelEditBtnId = "cancelEditPrepBtn"+prepNum;
		
		var prepInputDivId = "prepInputDiv"+prepNum;
		var plantPartInputDivId = "plantPartInputDiv"+prepNum;
		var illnessInputDivId = "illnessInputDiv"+prepNum;
		
		var prepInputId = "prepInput"+prepNum;
		var plantPartInputId = "plantPartInput"+prepNum;
		var illnessInputId = "illnessInput"+prepNum;
		
				
		var prepNameId = "prepName"+prepNum;
		var plantPartNameId = "plantPartName"+prepNum;
		var illnessNameId = "illnessName"+prepNum;
		
		document.getElementById(editBtnId).onclick = function () { editPrep(prepNum); };
		
		document.getElementById(editBtnId).classList.add("btn-outline-dark");
		document.getElementById(editBtnId).classList.remove("btn-success");
		
		document.getElementById(editLogoId).classList.add("fa-pencil");
		document.getElementById(editLogoId).classList.remove("fa-check");  
		
		document.getElementById(prepInputDivId).style.display= "none";	
		document.getElementById(plantPartInputDivId).style.display= "none";		
		document.getElementById(illnessInputDivId).style.display= "none";		
		
		document.getElementById(prepNameId).style.display="inline";
		document.getElementById(plantPartNameId).style.display="inline";
		document.getElementById(illnessNameId).style.display="inline";
				
		document.getElementById(cancelEditBtnId).style.visibility="hidden"; 
	}
	
	function savePrep(prepNum){
		var editBtnId = "editPrepBtn"+prepNum;
		var editLogoId = "editPrepLogo"+prepNum;
		var cancelEditBtnId = "cancelEditPrepBtn"+prepNum;
		
		var prepInputDivId = "prepInputDiv"+prepNum;
		var plantPartInputDivId = "plantPartInputDiv"+prepNum;
		var illnessInputDivId = "illnessInputDiv"+prepNum;
		
		var prepInputId = "prepInput"+prepNum;
		var plantPartInputId = "plantPartInput"+prepNum;
		var illnessInputId = "illnessInput"+prepNum;
		
				
		var prepNameId = "prepName"+prepNum;
		var plantPartNameId = "plantPartName"+prepNum;
		var illnessNameId = "illnessName"+prepNum;
		
		var oldPrepVal = document.getElementById(prepNameId).innerHTML.trim();
		var oldPlantPartVal = document.getElementById(plantPartNameId).innerHTML.trim();
		var oldIllnessVal = document.getElementById(illnessNameId).innerHTML.trim();
		
		var prepVal = document.getElementById(prepInputId).value.trim();		
		var plantPartVal = document.getElementById(plantPartInputId).value.trim();		
		var illnessVal = document.getElementById(illnessInputId).value.trim();
		
		var medPlantName = document.getElementById("medPlantName").innerHTML.trim();
	
		console.log(prepVal);
		console.log(plantPartVal);
		console.log(illnessVal);
  		$.ajax({
			type : "GET",
			url : 'EditPrep',
			dataType: "text",
			data: {
				oldPrepVal: oldPrepVal,
				oldPlantPartVal: oldPlantPartVal,
				oldIllnessVal: oldIllnessVal,
				prepVal: prepVal,				
				plantPartVal: plantPartVal,				
				illnessVal: illnessVal,				
				medPlantName: medPlantName
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Preparation Successfully Edited"){			
					document.getElementById(prepNameId).innerHTML = prepVal;
					document.getElementById(plantPartNameId).innerHTML = plantPartVal;
					document.getElementById(illnessNameId).innerHTML = illnessVal;
					cancelEditPrep(prepNum);
				}else{
					cancelEditPrep(prepNum);
				}
			}
			}); 
		
	} 
	
	function addPrep(prepNum){
		var addBtnId = "addPrepBtn";
		var addLogoId = "addPrepLogo";
		var cancelAddBtnId = "cancelAddPrepBtn";
		
		var prepInputDivId = "prepInputDiv"+prepNum;
		var plantPartInputDivId = "plantPartInputDiv"+prepNum;
		var illnessInputDivId = "illnessInputDiv"+prepNum;
		
		var prepInputId = "prepInput"+prepNum;
		var plantPartInputId = "plantPartInput"+prepNum;
		var illnessInputId = "illnessInput"+prepNum;
		
				
		var prepNameId = "prepName"+prepNum;
		var plantPartNameId = "plantPartName"+prepNum;
		var illnessNameId = "illnessName"+prepNum;
		
		document.getElementById(addBtnId).onclick = function () { saveAddPrep(prepNum); };
		
		document.getElementById(addBtnId).classList.remove("btn-outline-dark");
		document.getElementById(addBtnId).classList.add("btn-success");
		
		document.getElementById(addLogoId).classList.remove("fa-plus");
		document.getElementById(addLogoId).classList.add("fa-check");  
		
		document.getElementById(prepInputDivId).style.display= "inline";	
		document.getElementById(plantPartInputDivId).style.display= "inline";		
		document.getElementById(illnessInputDivId).style.display= "inline";		
		
		document.getElementById(prepNameId).style.display="none";
		document.getElementById(plantPartNameId).style.display="none";
		document.getElementById(illnessNameId).style.display="none";
		
		document.getElementById(prepInputId).value = document.getElementById(prepNameId).innerHTML.trim();
		document.getElementById(plantPartInputId).value = document.getElementById(plantPartNameId).innerHTML.trim();
		document.getElementById(illnessInputId).value = document.getElementById(illnessNameId).innerHTML.trim();
		
		document.getElementById(cancelAddBtnId).style.display="inline"; 
		
	}
	
	
	function cancelAddPrep(prepNum){
		var addBtnId = "addPrepBtn";
		var addLogoId = "addPrepLogo";
		var cancelAddBtnId = "cancelAddPrepBtn";
		
		var prepInputDivId = "prepInputDiv"+prepNum;
		var plantPartInputDivId = "plantPartInputDiv"+prepNum;
		var illnessInputDivId = "illnessInputDiv"+prepNum;
		
		var prepInputId = "prepInput"+prepNum;
		var plantPartInputId = "plantPartInput"+prepNum;
		var illnessInputId = "illnessInput"+prepNum;
		
				
		var prepNameId = "prepName"+prepNum;
		var plantPartNameId = "plantPartName"+prepNum;
		var illnessNameId = "illnessName"+prepNum;
		
		document.getElementById(addBtnId).onclick = function () { addPrep(prepNum); };
		
		document.getElementById(addBtnId).classList.add("btn-outline-dark");
		document.getElementById(addBtnId).classList.remove("btn-success");
		
		document.getElementById(addLogoId).classList.add("fa-plus");
		document.getElementById(addLogoId).classList.remove("fa-check");  
		
		document.getElementById(prepInputDivId).style.display= "none";	
		document.getElementById(plantPartInputDivId).style.display= "none";		
		document.getElementById(illnessInputDivId).style.display= "none";		
		
		document.getElementById(prepNameId).style.display="inline";
		document.getElementById(plantPartNameId).style.display="inline";
		document.getElementById(illnessNameId).style.display="inline";
				
		document.getElementById(cancelAddBtnId).style.display="none"; 
	}
	
	function saveAddPrep(prepNum){
		var addBtnId = "addPrepBtn";
		var addLogoId = "addPrepLogo";
		var cancelAddBtnId = "cancelAddPrepBtn";
		
		var prepInputDivId = "prepInputDiv"+prepNum;
		var plantPartInputDivId = "plantPartInputDiv"+prepNum;
		var illnessInputDivId = "illnessInputDiv"+prepNum;
		
		var prepInputId = "prepInput"+prepNum;
		var plantPartInputId = "plantPartInput"+prepNum;
		var illnessInputId = "illnessInput"+prepNum;
		
				
		var prepNameId = "prepName"+prepNum;
		var plantPartNameId = "plantPartName"+prepNum;
		var illnessNameId = "illnessName"+prepNum;
			
		var prepVal = document.getElementById(prepInputId).value.trim();		
		var plantPartVal = document.getElementById(plantPartInputId).value.trim();		
		var illnessVal = document.getElementById(illnessInputId).value.trim();
		
		var medPlantName = document.getElementById("medPlantName").innerHTML.trim();

  		$.ajax({
			type : "GET",
			url : 'AddPrep',
			dataType: "text",
			data: {
				prepVal: prepVal,				
				plantPartVal: plantPartVal,				
				illnessVal: illnessVal,				
				medPlantName: medPlantName
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Preparation Added Successfully"){	
					window.location.href = "ViewPlantServlet?medPlant=${medPlantsList.get(0).getMedicinalPlant()}";
/* 					document.getElementById(prepNameId).innerHTML = prepVal;
					document.getElementById(plantPartNameId).innerHTML = plantPartVal;
					document.getElementById(illnessNameId).innerHTML = illnessVal;
					cancelEditPrep(prepNum); */
				}else{
					cancelAddPrep(prepNum);
				}
			}
			}); 
		
	} 
	</script>

	<!-- Script for Edit Genus -->
	<script type="text/javascript">
	function editComp(compNum){
		var editBtnId = "editCompBtn"+compNum;
		var editLogoId = "editCompLogo"+compNum;
		var cancelEditBtnId = "cancelEditCompBtn"+compNum;
		
		var plantPartCompInputDivId = "plantPartCompInputDiv"+compNum;
		var chemCompInputDivId = "chemCompInputDiv"+compNum;
		
		var plantPartCompInputId = "plantPartCompInput"+compNum;
		var chemCompInputId = "chemCompInput"+compNum;
		
		var plantPartCompNameId = "plantPartCompName"+compNum;
		var chemCompNameId = "chemCompName"+compNum;
		
		document.getElementById(editBtnId).onclick = function () { saveComp(compNum); };
		
		document.getElementById(editBtnId).classList.remove("btn-outline-dark");
		document.getElementById(editBtnId).classList.add("btn-success");
		
		document.getElementById(editLogoId).classList.remove("fa-pencil");
		document.getElementById(editLogoId).classList.add("fa-check");  
		
		document.getElementById(plantPartCompInputDivId).style.display= "inline";	
		document.getElementById(chemCompInputDivId).style.display= "inline";		
		
		document.getElementById(plantPartCompNameId).style.display="none";
		document.getElementById(chemCompNameId).style.display="none";
		
		document.getElementById(plantPartCompInputId).value = document.getElementById(plantPartCompNameId).innerHTML.trim();
		document.getElementById(chemCompInputId).value = document.getElementById(chemCompNameId).innerHTML.trim();
		
		document.getElementById(cancelEditBtnId).style.display="inline"; 
		
	}
		
	function cancelEditComp(compNum){
		var editBtnId = "editCompBtn"+compNum;
		var editLogoId = "editCompLogo"+compNum;
		var cancelEditBtnId = "cancelEditCompBtn"+compNum;
		
		var plantPartCompInputDivId = "plantPartCompInputDiv"+compNum;
		var chemCompInputDivId = "chemCompInputDiv"+compNum;
		
		var plantPartCompInputId = "plantPartCompInput"+compNum;
		var chemCompInputId = "chemCompInput"+compNum;
		
		var plantPartCompNameId = "plantPartCompName"+compNum;
		var chemCompNameId = "chemCompName"+compNum;
		
		document.getElementById(editBtnId).onclick = function () { editComp(compNum); };
		
		document.getElementById(editBtnId).classList.add("btn-outline-dark");
		document.getElementById(editBtnId).classList.remove("btn-success");
		
		document.getElementById(editLogoId).classList.add("fa-pencil");
		document.getElementById(editLogoId).classList.remove("fa-check");  
		
		document.getElementById(plantPartCompInputDivId).style.display= "none";	
		document.getElementById(chemCompInputDivId).style.display= "none";	
		
		document.getElementById(plantPartCompNameId).style.display="inline";
		document.getElementById(chemCompNameId).style.display="inline";
				
		document.getElementById(cancelEditBtnId).style.display="none"; 
	}
	
	function saveComp(compNum){
		var editBtnId = "editCompBtn"+compNum;
		var editLogoId = "editCompLogo"+compNum;
		var cancelEditBtnId = "cancelEditCompBtn"+compNum;
		
		var plantPartCompInputDivId = "plantPartCompInputDiv"+compNum;
		var chemCompInputDivId = "chemCompInputDiv"+compNum;
		
		var plantPartCompInputId = "plantPartCompInput"+compNum;
		var chemCompInputId = "chemCompInput"+compNum;
		
		var plantPartCompNameId = "plantPartCompName"+compNum;
		var chemCompNameId = "chemCompName"+compNum;
		
		var oldPlantPartCompVal = document.getElementById(plantPartCompNameId).innerHTML.trim();
		var oldChemCompVal = document.getElementById(chemCompNameId).innerHTML.trim();
		
		var plantPartCompVal = document.getElementById(plantPartCompInputId).value.trim();		
		var chemCompVal = document.getElementById(chemCompInputId).value.trim();		
		
		var specieName = document.getElementById("specieName").innerHTML.trim();
	
  		$.ajax({
			type : "GET",
			url : 'EditComp',
			dataType: "text",
			data: {
				oldPlantPartCompVal: oldPlantPartCompVal,
				oldChemCompVal: oldChemCompVal,
				plantPartCompVal: plantPartCompVal,
				chemCompVal: chemCompVal,
				specieName: specieName
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Chemical Compound Successfully Edited"){			
					document.getElementById(plantPartCompNameId).innerHTML = plantPartCompVal;
					document.getElementById(chemCompNameId).innerHTML = chemCompVal;
					cancelEditComp(compNum);
				}else{
					cancelEditComp(compNum);
				}
			}
			}); 
		
	} 
	</script>


	<!-- Script for Add/Delete Location -->
	<script type="text/javascript">
	function addLoc(){
		/* document.getElementById("loc").style.display="none"; */
		/* document.getElementById("locInput").value = document.getElementById("specieName").innerHTML.trim(); */
		document.getElementById("locInputDiv").style.display= "inline";
		document.getElementById("addLocBtn").onclick = function () { saveLoc(); };
		document.getElementById("addLocBtn").classList.remove("btn-outline-dark");
		document.getElementById("addLocBtn").classList.add("btn-success");
		document.getElementById("addLocLogo").classList.remove("fa-plus");
		document.getElementById("addLocLogo").classList.add("fa-check");  
		
		document.getElementById("cancelAddLocBtn").style.display="inline";
	}
	
	function cancelAddLoc(){
		/* document.getElementById("loc").style.display="inline"; */
		document.getElementById("locInputDiv").style.display="none";   
		document.getElementById("addLocLogo").classList.remove("fa-check");
		document.getElementById("addLocLogo").classList.add("fa-plus"); 
		document.getElementById("addLocBtn").onclick = function () { addLoc(); };	
		document.getElementById("addLocBtn").classList.remove("btn-success");
		document.getElementById("addLocBtn").classList.add("btn-outline-dark");
		document.getElementById("cancelAddLocBtn").style.display="none";
	}
	
	function removeLoc(locNum){
		var locVal = document.getElementById("locName"+locNum).innerHTML.trim();
		var medPlantName = document.getElementById("medPlantName").innerHTML.trim();
		
		console.log(locVal);
   		$.ajax({
			type : "GET",
			url : 'RemoveLoc',
			dataType: "text",
			data: {
				locVal: locVal,
				medPlantName: medPlantName
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Location Removed"){
					window.location.href = "ViewPlantServlet?medPlant=${medPlantsList.get(0).getMedicinalPlant()}";
				}
			}
			});   
	}
	
	function saveLoc(){
		var locVal = document.getElementById("locInput").value;
		var medPlantName = document.getElementById("medPlantName").innerHTML.trim();
		
		console.log(locVal);
		console.log(medPlantName);
 		$.ajax({
			type : "GET",
			url : 'AddLoc',
			dataType: "text",
			data: {
				locVal: locVal,
				medPlantName: medPlantName
				  },
			success : function(data) {
				alert(data)
				

				if(data.trim() == "Location Added Successfully"){
					document.getElementById("locInputDiv").style.display="none";
					document.getElementById("addLocBtn").onclick = function () { addLoc(); };
					document.getElementById("addLocBtn").classList.remove("btn-success");
					document.getElementById("addLocBtn").classList.add("btn-outline-dark");
					document.getElementById("addLocLogo").classList.remove("fa-check");
					document.getElementById("addLocLogo").classList.add("fa-plus");
					document.getElementById("cancelAddLocBtn").style.display="none";
					/* document.getElementById("locTab").classList.add("active"); */
					window.location.href = "ViewPlantServlet?medPlant=${medPlantsList.get(0).getMedicinalPlant()}"; 
				}else{
					cancelAddLoc();
				}
			}
			}); 
		
	}
	</script>

	<!-- Script for Edit Entry -->
	<script type="text/javascript">
	function editEntry(){
		var loc_table = $('#table_id_loc').DataTable();
		var loc_table_length = ${locNum};
		loc_table.page.len( loc_table_length ).draw();
		var i;
		for (i = 1; i < loc_table_length; i++) {
			var id = 'removeLocBtn'+i;
			document.getElementById(id).style.display="inline";
		} 
		
		var prep_table = $('#table_id_prep').DataTable();
		var prep_table_length = ${prepNum};
		prep_table.page.len( prep_table_length ).draw();
		var j;
		for (j = 1; j < prep_table_length; j++) {
			var id = 'editPrepBtn'+j;
			document.getElementById(id).style.display="inline";
		} 
				
		var comp_table = $('#table_id_chem').DataTable();
		var comp_table_length = ${compNum};
		comp_table.page.len( comp_table_length ).draw();
		var k;
		for (k = 1; k < comp_table_length; k++) {
			var id = 'editCompBtn'+k;
			var idTip = 'editSpecieTip'+k;
			document.getElementById(id).style.display="inline";
			document.getElementById(idTip).style.display="inline";
		} 
		
		
		document.getElementById("editEntryLogo").classList.remove("fa-pencil");
		document.getElementById("editEntryLogo").classList.add("fa-check");
		document.getElementById("editEntryName").innerHTML=" Finish Editing";
		document.getElementById("editEntryBtn").onclick = function () { cancelEditEntry(); };
		document.getElementById("deletePlantBtn").style.display="inline";
		document.getElementById("editMedPlantBtn").style.display="inline";
		document.getElementById("editGenusBtn").style.display="inline";
		document.getElementById("editGenusTip").style.display="inline";
		document.getElementById("editFamilyTip").style.display="inline";
		document.getElementById("editSpecieBtn").style.display="inline";
		document.getElementById("editSpecieTip").style.display="inline";
		document.getElementById("editBioActTip").style.display="inline";
		document.getElementById("addLocBtn").style.display="inline";
		document.getElementById("addPrepBtn").style.display="inline";
	}
	
	function cancelEditEntry(){
		var i;
		for (i = 1; i < "${locNum}"; i++) {
			var id = 'removeLocBtn'+i;
			document.getElementById(id).style.display="none";
		} 
		var loc_table = $('#table_id_loc').DataTable();
		loc_table.page.len(10).draw();
		
		var j;
		for (j = 1; j < "${prepNum}"; j++) {
			var id = 'editPrepBtn'+j;
			document.getElementById(id).style.display="none";
		} 		
		var prep_table = $('#table_id_prep').DataTable();
		prep_table.page.len(10).draw();
		
		
		var k;
		for (k = 1; k < "${compNum}"; k++) {
			var id = 'editCompBtn'+k;
			var idTip = 'editSpecieTip'+k;
			document.getElementById(id).style.display="none";
			document.getElementById(idTip).style.display="none";
		} 
		var comp_table = $('#table_id_chem').DataTable();
		comp_table.page.len(10).draw();
		
		document.getElementById("editMedPlantBtn").style.display="none";
		document.getElementById("editGenusBtn").style.display="none";
		document.getElementById("editGenusTip").style.display="none";
		document.getElementById("editFamilyTip").style.display="none";
		document.getElementById("editSpecieBtn").style.display="none";
		document.getElementById("editSpecieTip").style.display="none";
		document.getElementById("editBioActTip").style.display="none";
		document.getElementById("addLocBtn").style.display="none";
		document.getElementById("addPrepBtn").style.display="none";
		
		document.getElementById("editEntryLogo").classList.add("fa-pencil");
		document.getElementById("editEntryLogo").classList.remove("fa-check");
		document.getElementById("editEntryName").innerHTML=" EDIT";
		document.getElementById("editEntryBtn").onclick = function () { editEntry(); };
		document.getElementById("deletePlantBtn").style.display="none";
	}
	</script>

	<script type="text/javascript">
	function deletePlant(plantName){
		var plantVal = plantName;
		console.log(plantVal);
		
		$.ajax({
			type : "GET",
			url : 'DeletePlant',
			dataType: "text",
			data: {
				plantVal: plantVal,
				  },
			success : function(data) {
				alert(data)
				if(data.trim() == "Plant Successfully Removed"){			
					window.location.href = "index.jsp";
				}else{
					cancelEditEntry();
				}
			}
			}); 

	}
	</script>

	<script type="text/javascript">
	    $(document).ready(function() {
	        $('#table_id_loc').DataTable();
	        $('#table_id_bioact').DataTable();
	        $('#table_id_prep').DataTable();
	        $('#table_id_chem').DataTable();

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

	<script type="text/javascript" src="js/autocomplete.js"></script>

	<!-- Script to Generate Autocomplete Fields -->
	<script>	
		var families = [];
		var genus = [];
		var syns = [];
		var locs = [];
		var plantParts = [];
		var illness = [];
		var compounds = [];
		/* autocomplete(document.getElementById("familyInput"), families);  */
		autocomplete(document.getElementById("genusInput"), genus); 
		autocomplete(document.getElementById("specieInput"), syns); 
		autocomplete(document.getElementById("locInput"), locs); 
		
		var i;
		for (i = 1; i <= "${prepNum}"; i++) {
			var plantPartInputId = 'plantPartInput'+i;
			var illnessInputId = 'illnessInput'+i;
			autocomplete(document.getElementById(illnessInputId), illness);
			autocomplete(document.getElementById(plantPartInputId), plantParts);
		} 
		
		var j;
		for (j = 1; j < "${compNum}"; j++) {
			var plantPartCompInputId = 'plantPartCompInput'+j;
			var chemCompInputId = 'chemCompInput'+j;
			autocomplete(document.getElementById(plantPartCompInputId), plantParts);
			autocomplete(document.getElementById(chemCompInputId), compounds);
		} 
	</script>

	<c:forEach items="${familyList}" var="family">
		<script>
			families.push("${family}")
		</script>
	</c:forEach>

	<c:forEach items="${genusList}" var="genus">
		<script>
			genus.push("${genus}")
		</script>
	</c:forEach>

	<c:forEach items="${synList}" var="syn">
		<script>
			syns.push("${syn}")
		</script>
	</c:forEach>

	<c:forEach items="${locList}" var="loc">
		<script>
			locs.push("${loc}")
		</script>
	</c:forEach>

	<c:forEach items="${plantPartsList}" var="plantPart">
		<script>	
			plantParts.push("${plantPart}")
		</script>
	</c:forEach>

	<c:forEach items="${illnessList}" var="illness">
		<script>
			illness.push("${illness}")
		</script>
	</c:forEach>

	<c:forEach items="${compoundList}" var="compound">
		<script>
			compounds.push("${compound}")
		</script>
	</c:forEach>

</body>
</html>