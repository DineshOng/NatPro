<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        <div class="section no-pad-bot" id="index-banner">
            <div class="container">
                <!--<br><br>-->
                <h1 class="header center green-text text-darken-3">Add</h1>
                <div class="row center">
                    <h6>Manually add a plant here</h6>
                </div>
                <form action="3aadded.jsp" class="row">
                    <div class="col s12">
                        <div class="input-field col s6 offset-s3">
                            <input id="taxonomicInformation" type="text" class="validate">
                            <label for="taxonomicInformation">Taxonomic Information</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="scientificName" type="text" class="validate">
                            <label for="scientificName">Scientific Name</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="commonPlantName" type="text" class="validate">
                            <label for="commonPlantName">Common Plant Name</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="location" type="text" class="validate">
                            <label for="location">Location</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="biologicalActivity" type="text" class="validate">
                            <label for="biologicalActivity">Biological Activity</label>
                        </div>
                        <div class="input-field col s9">
                            <input id="biologicalActivity" type="text" class="validate">
                            <label for="biologicalActivity">Biological Activity</label>
                        </div>
                        <div class="input-field col s3">
                            <select class="browser-default">
                                <option value="0" disabled selected>select a part</option>
                                <option value="1">leaves</option>
                                <option value="2">stem</option>
                                <option value="3">bark</option>
                            </select>
                        </div>
                        <div class="input-field col s6 offset-s3">
                            <input id="chemicalCompound" type="text" class="validate">
                            <label for="chemicalCompound">Chemical Compound</label>
                        </div>
                    </div>
                    <input type="submit" id="btnSubmit" class="waves-effect waves-light btn green darken-3 center col s6 offset-s3">
                </form>
                <br><br>

            </div>
        </div>


        <div class="container">
            <div class="section">

                <!--   Icon Section   -->
                <div class="row">
                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center light-blue-text"><i class="material-icons">flash_on</i></h2>
                            <h5 class="center">Speeds up development</h5>

                            <p class="light">We did most of the heavy lifting for you to provide a default stylings that incorporate our custom components. Additionally, we refined animations and transitions to provide a smoother experience for developers.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center light-blue-text"><i class="material-icons">group</i></h2>
                            <h5 class="center">User Experience Focused</h5>

                            <p class="light">By utilizing elements and principles of Material Design, we were able to create a framework that incorporates components and animations that provide more feedback to users. Additionally, a single underlying responsive system across all platforms allow for a more unified user experience.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center light-blue-text"><i class="material-icons">settings</i></h2>
                            <h5 class="center">Easy to work with</h5>

                            <p class="light">We have provided detailed documentation as well as specific code examples to help new users get started. We are also always open to feedback and can answer any questions a user may have about Materialize.</p>
                        </div>
                    </div>
                </div>

            </div>
            <br><br>
        </div>

        <%@include file="includeFooter.html"%>
		<%@include file="includeScripts.html"%>
        <script>
            $(document).ready(function(){
                $('select').formSelect();
            });
        </script>
    </body>
</html>