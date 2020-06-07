<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="navbarnix.html"%>

<title>NatPro : "${searchKey}"</title>

<body>
	<div>
		<table id="table_id" class="table table-striped table-bordered" style="width:100%">
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
										<i><a href="6dentry.jsp?specie=${speciesList.getSpecie()}">${speciesList.getSpecie()}</a></i>
										<c:if test="${!loop.last}">, </c:if>
									</c:forEach></td>
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
									href="ViewPlantServlet?genus=${genusList.getGenus()}">${genusList.getGenus()}</a></td>
								<td><a
									href="ViewPlantServlet?genus=${genusList.getFamily().getFamily()}">${genusList.getFamily().getFamily()}</a></td>
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
									href="ViewPlantServlet?genus=${familyList.getFamily()}">${familyList.getFamily()}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:when test="${searchCategory =='5'}">
					<thead>
						<tr>
							<th>Compound</th>
							<th>Compound Synonym</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${compoundList}" var="compoundList">
							<tr>
								<td><a
									href="ViewCompoundServlet?compound=${compoundList.getCompoundName()}">${compoundList.getCompoundNameNorm()}</a></td>
								<td><c:forEach
										items="${compoundList.getCompoundSynonyms()}" var="csList" varStatus="loop">
										<a
											href="ViewCompoundServlet?compound=${compoundList.getCompoundName()}">${csList}
										</a><c:if test="${!loop.last}">, </c:if></c:forEach></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
			</c:choose>
		</table>
	</div>
	
	<script src="js/jquery-3.5.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="DataTables/dataTables.bootstrap.min.css">
	<script type="text/javascript" charset="utf8" src="DataTables/datatables.min.js"></script>
	<script type="text/javascript" charset="utf8" src="DataTables/dataTables.bootstrap.min.js"></script>
	
	
	<style>
		#table_id_wrapper {
			margin: 50px;
		}
	</style>
  	
	<script>
		$(document).ready(function() {
			
		});
		
		$('#table_id').DataTable();
		
		var x = '${searchCategory}'
		var txt;
		
		if(x==1) {
			txt = "Plant Name<b class='caret'></b>";
			$('input[name="searchCategory"]').val(1);
		} else if(x==2) {
			txt = "Scientific Name<b class='caret'></b>";
			$('input[name="searchCategory"]').val(2);
		} else if(x==3) {
			txt = "Genus<b class='caret'></b>";
			$('input[name="searchCategory"]').val(3);
		} else if(x==4) {
			txt = "Family<b class='caret'></b>";
			$('input[name="searchCategory"]').val(4);
		} else if(x==5) {
			txt = "Compound<b class='caret'></b>";
			$('input[name="searchCategory"]').val(5);
		} else if(x==6) {
			txt = "Location<b class='caret'></b>";
			$('input[name="searchCategory"]').val(6);
		} else if(x==7) {
			txt = "Biological Activites<b class='caret'></b>";
			$('input[name="searchCategory"]').val(7);
		}
		
		$("#ddtext").html(txt);
		$("#search").val('${searchKey}');
		
	</script>
</body>
