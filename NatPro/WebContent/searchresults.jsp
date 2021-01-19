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

<style>
td {
	max-width: 0;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}
</style>

<title>NatPro : ${searchKey}</title>
</head>
<body>

	<%@include file="navbarnix.html"%>

	<div id="firstDiv">
		<table id="table_id" class="table table-striped table-bordered"
			style="width: 100%">
			<c:choose>
				<c:when test="${searchCategory =='1'}">
					<thead>
						<tr>
							<th>Common Name</th>
							<th>Scientific Name</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${medPlantsList}" var="medPlantsList">
							<tr>
								<td><a
									href="ViewPlantServlet?medPlant=${medPlantsList.getMedicinalPlant()}">${medPlantsList.getMedicinalPlant()}</a></td>
								<td><c:forEach items="${medPlantsList.getSpecies()}"
										var="speciesList" varStatus="loop">
										<i><a
											href="ViewSciPlantServlet?specie=${speciesList.getSpecie()}&medPlant=${medPlantsList.getMedicinalPlant()}">${speciesList.getSpecie()}</a></i>
										<c:if test="${!loop.last}">, </c:if>
									</c:forEach></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:when test="${searchCategory =='2'}">
					<thead>
						<tr>
							<th>Scientific Name</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${speciesList}" var="speciesList">
							<tr>
								<td><i><a
										href="ViewSciPlantServlet?specie=${speciesList}">${speciesList}</a></i>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:when test="${searchCategory =='3'}">
					<thead>
						<tr>
							<th>Genus</th>
							<th>Family</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${genusList}" var="genusList">
							<tr>
								<td><a
									href="ViewGenusServlet?genus=${genusList.getGenus()}">${genusList.getGenus()}</a></td>
								<td><a
									href="ViewFamilyServlet?family=${genusList.getFamily().getFamily()}">${genusList.getFamily().getFamily()}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:when test="${searchCategory =='4'}">
					<thead>
						<tr>
							<th>Family</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${familyList}" var="familyList">
							<tr>
								<td><a
									href="ViewFamilyServlet?family=${familyList.getFamily()}">${familyList.getFamily()}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:when test="${searchCategory =='5'}">
					<thead>
						<tr>
							<th class="hid"></th>
							<th>Compound</th>
							<th>Compound Synonym</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${compoundList}" var="compoundList">
							<tr>
								<td class="hid"><a>${compoundList.getCompoundName()}</a></td>
								<td><a
									href="ViewCompoundServlet?compound=${compoundList.getCompoundName()}">${compoundList.getCompoundNameHTML()}</a></td>
								<td><c:forEach
										items="${compoundList.getCompoundSynonyms()}" var="csList"
										varStatus="loop">
										<a
											href="ViewCompoundServlet?compound=${compoundList.getCompoundName()}">${csList}
										</a>
										<c:if test="${!loop.last}">, </c:if>
									</c:forEach></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:when test="${searchCategory =='6'}">
					<thead>
						<tr>
							<th class="hid"></th>
							<th>Location</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${locList}" var="locs">
							<tr>
								<td class="hid"><a></a></td>
								<td><a href="ViewLocationServlet?location=${locs}">${locs}</a></td>

							</tr>
						</c:forEach>
					</tbody>
				</c:when>

				<c:when test="${searchCategory =='7'}">
					<thead>
						<tr>
							<th class="hid"></th>
							<th>Biological Activity</th>
							<th>Cell Line</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bioActList}" var="bioActs">
							<tr>
								<td class="hid"><a></a></td>
								<td><a
									href="ViewBiologicalActivityServlet?bioact=${bioActs.getBiologicalActivity()}&cellline=${bioActs.getCellLine().getCellLine()}">${bioActs.getBiologicalActivity()}</a></td>
								<td><a href="https://www.google.com/search?q=${bioActs.getCellLine().getCellLine()}" target="_blank">${bioActs.getCellLine().getCellLine()}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
			</c:choose>
		</table>
	</div>

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
		$('.hid').css('display', 'none');
		ddfunc(${searchCategory});
	</script>

</body>
</html>