<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>
		<form action="servlet_docUpload">
			<input type="file" id="file" onchange="FileValidation()" accept=".jpg,.gif">
		</form>
	</p>
	
	<p id="size">fe</p>
</body>
<script>
	function FileValidation() {
		const fi = document.getElementById('file');
		// Check if any file is selected.
		if (fi.files.length > 0) {
			for (const i = 0; i <= fi.files.length - 1; i++) {
				
				const fsize = fi.files.item(i).size;
				const file = Math.round(( fsize / 1024 ));
				// The size of the file;
				if (file >= 4096) {
					alert("Too big. Select file less than 4mb");
				}
				else if (file < 2048) {
					alert("Too small. Select file more than 2mb");
				}
				else {
					document.getElementById('size').innerHTML = '<b>' + file + '</b> KB';
				}
			}
		}
	}
</script>
</html>