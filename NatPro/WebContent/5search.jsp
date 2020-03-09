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
                <h1 class="header center green-text text-darken-3">Search</h1>
                <div class="row center">
                    <h6>Search for plants, chemical compound, etc.</h6>
                </div>
                <br><br>
                <form class="row" action="SearchServlet" method="POST">
                    <div class="input-field col s9">
                        <i class="material-icons prefix">search</i>
                        <input id="searchInput" class="materialize-textarea" type="text" name="searchKey">
                        <label for="searchInput">Search</label>
                    </div>
                    <div class="input-field col s3">
                        <select class="browser-default">
                            <option value="" disabled selected>Choose your option</option>
                            <option value="1">plant common name</option>
                            <option value="2">plant scientific name</option>
                            <option value="3">genus</option>
                            <option value="4">family</option>
                            <option value="5">chemical compound</option>
                            <option value="6">location</option>
                            <option value="7">biological activites</option>
                        </select>
                    </div>
                    <input type="submit" class="waves-effect waves-light btn center green darken-3 col s4 offset-s4" id="btnSubmit" value="search">
                </form>
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

        <script>
            $(document).ready(function() {
                $('select').formSelect();
            })
            
            document.getElementById("advSearch").onclick = function() {
            	document.getElementById("advSearch").style.display = "none";
            }
        </script>
    </body>
</html>