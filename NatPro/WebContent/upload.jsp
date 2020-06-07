<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="navbarnix.html"%>

<title>NatPro Upload</title>

<body>

<div class="row center">
                    <h6>Upload your document here</h6>
                    <br>
                    <form action="/NatPro/uploadSERVLET"  method="POST" enctype="multipart/form-data" autocomplete="off">
                        <div class="file-field" input field>
                            <div class="col s6 offset-s3">
                                <div class="waves-effect waves-light btn green darken-1">
                                <span>Browse File/s</span>
                                   	<!--   <input type="file" multiple>-->
                                    <input id="upload"  type="file" name="file-upload"  value="" multiple="multiple" accept=".txt,.pdf">
                                </div>
                                <div class="file-path-wrapper">
                                    <input class="file-path validate" type="text" placeholder="Upload your document(s) here">
                                </div>
                            </div>
                            <input type="submit" value="Upload" class="btn waves-effect waves-light green darken-3 col s2 offset-s5" id="btnSubmit">
                        </div>
                    </form>
                    <!--<form action="2acomplete.html" class="">
                        <label for="docFile">Select a file(s):</label>
                        <input type="file" id="docFile" multiple>
                        <br><br>
                        <input type="submit" value="Upload" class="btn waves-effect waves-light green darken-3" id="btnSubmit">
                    </form>-->
                </div>

</body>