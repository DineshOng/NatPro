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
        <nav class="white" role="navigation">
            <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">NatPro</a>
                <ul class="right hide-on-med-and-down">
                    <li><a href="1mainpage.jsp">Home</a></li>
                    <li><a href="2upload.jsp">Upload</a></li>
                    <li><a href="3add.jsp">Add</a></li>
                    <li><a href="4validation.jsp">Validation</a></li>
                    <li><a href="5search.jsp" class="white-text green darken-3">Search</a></li>
                    <li><a href="6view.jsp">View</a></li>
                </ul>

                <ul id="nav-mobile" class="sidenav">
                    <li><a href="1mainpage.jsp">Home</a></li>
                    <li><a href="2upload.jsp">Upload</a></li>
                    <li><a href="3add.jsp">Add</a></li>
                    <li><a href="4validation.jsp">Validation</a></li>
                    <li><a href="5search.jsp" class="white-text green darken-3">Search</a></li>
                    <li><a href="6view.jsp">View</a></li>
                </ul>
                <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
            </div>
        </nav>
        <div class="section no-pad-bot" id="index-banner">
            <div class="container">
                <!--<br><br>-->
                <h1 class="header center green-text text-darken-3">Search</h1>
                <div class="row center">
                    <h6>Search for plants, chemical compound, etc.</h6>
                </div>
                <br><br>
                <div class="row">
                	<a class="col s3 offset-s9 waves-effect waves-light btn green darken-3" onclick="switchSearch()" id="switchSearch">Switch to advanced search</a>
                </div>
                <form class="row" action="5asearchresults.jsp" id="normSearch">
                    <div class="input-field col s9">
                        <i class="material-icons prefix">search</i>
                        <input id="searchInput" class="materialize-textarea" type="text">
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
                <form id="advSearch" action="" class="row">
                	<div class="input-field col s12">
                        <i class="material-icons prefix">search</i>
                        <input id="searchInput" class="materialize-textarea" type="text">
                        <label for="searchInput">Search for anything</label>
                    </div>
                    
                    <div class="row">
                    	<h6>Limit search for only:</h6><br>
                    	<label class="">
                    		<input type="checkbox" class="filled-in">
                    		<span style="padding-right: 20px;">Scientific Plant Name</span>
                    	</label>
                    	<label class="">
                    		<input type="checkbox" class="filled-in">
                    		<span style="padding-right: 20px;">Common Plant Name</span>
                    	</label>
                    	<label class="">
                    		<input type="checkbox" class="filled-in">
                    		<span style="padding-right: 20px;">Genus</span>
                    	</label>
                    	<label class="">
                    		<input type="checkbox" class="filled-in">
                    		<span style="padding-right: 20px;">Family</span>
                    	</label>
                    	<label class="">
                    		<input type="checkbox" class="filled-in">
                    		<span style="padding-right: 20px;">Chemical Compound</span>
                    	</label>
                    	<label class="">
                    		<input type="checkbox" class="filled-in">
                    		<span style="padding-right: 20px;">Location</span>
                    	</label>
                    	<label class="">
                    		<input type="checkbox" class="filled-in">
                    		<span style="padding-right: 20px;">Biological Activites</span>
                    	</label>
                    </div>
                    <div class="row">
                    	<h6>Plant Part Usage: </h6><br>
                    	
                    	<!-- 
	                    	<div class="input-field col s8">
		                        <i class="material-icons prefix">search</i>
		                        <input id="searchInput" class="materialize-textarea" type="text">
		                        <label for="searchInput">Search</label>
		                    </div>
		                    <div class="col s1">
		                    	<h6>for</h6>
		                    </div>
	                    -->
	                    <div class="col s1" style="padding-top: 15px">
	                    	<h6>Use</h6>
	                    </div>
	                    <!-- should get values from orgpart.text and procedurally generated every time search is called -->
	                    <div class="input-field col s5">
	                        <select class="browser-default">
	                            <option value="" disabled selected>Choose plant part</option>
	                            <option value="1">Aerial Plant Parts</option>
	                            <option value="2">Petiole and Rachis</option>
	                            <option value="3">Unripe Sarcotesta</option>
	                            <option value="4">Aerial Plant Part</option>
	                            <option value="5">Ripe Sarcotesta</option>
	                            <option value="6">Fruiting Bodies</option>
	                            <option value="7">Fruiting Body</option>
	                            <option value="8">Aerial Parts</option>
	                            <option value="9">Female Cone</option>
	                            <option value="10">Aerial Part</option>
	                            <option value="11">Fruit Rinds</option>
	                            <option value="12">Sclerotesta</option>
	                            <option value="13">Sarcotesta</option>
	                            <option value="14">Fruit Rind</option>
	                            <option value="15">Male Cone</option>
	                            <option value="16">Stem Bark</option>
	                            <option value="17">Cone Base</option>
	                            <option value="18">Endotesta</option>
	                            <option value="19">Flowers</option>
	                            <option value="20">Leaflet</option>
	                            <option value="21">Flower</option>
	                            <option value="22">Fruits</option>
	                            <option value="23">Trunks</option>
	                            <option value="24">Leaves</option>
	                            <option value="25">Fruit</option>
	                            <option value="26">Trunk</option>
	                            <option value="27">Seeds</option>
	                            <option value="28">Roots</option>
	                            <option value="29">Twigs</option>
	                            <option value="30">Stems</option>
	                            <option value="31">Seed</option>
	                            <option value="32">Root</option>
	                            <option value="33">Twig</option>
	                            <option value="34">Stem</option>
	                            <option value="35">Pods</option>
	                            <option value="36">Leaf</option>
	                            <option value="37">Bark</option>
	                            <option value="38">Pod</option>
	                        </select>
	                    </div>
	                    <div class="col s1"  style="padding-top: 15px">
	                    	<h6>to</h6>
	                    </div>
	                    <!-- should get values from bodypart.text and procedurally generated every time search is called -->
	                    <div class="input-field col s5">
	                        <select class="browser-default">
	                            <option value="" disabled selected>Choose body part</option>
	                            <option value="1">Gall Bladder</option>
	                            <option value="2">Intestines</option>
	                            <option value="3">Esophagus</option>
	                            <option value="4">Intestine</option>
	                            <option value="5">Shoulders</option>
	                            <option value="6">Buttocks</option>
	                            <option value="7">Bladder</option>
	                            <option value="8">Kidneys</option>
	                            <option value="9">Ovaries</option>
	                            <option value="10">Stomach</option>
	                            <option value="11">Thyroid</option>
	                            <option value="12">Fingers</option>
	                            <option value="13">Breasts</option>
	                            <option value="14">Abdomen</option>
	                            <option value="15">Kidney</option>
	                            <option value="16">Spleen</option>
	                            <option value="17">Thymus</option>
	                            <option value="18">Cheeks</option>
	                            <option value="19">Tongue</option>
	                            <option value="20">Throat</option>
	                            <option value="21">Elbows</option>
	                            <option value="22">Wrists</option>
	                            <option value="23">Finger</option>
	                            <option value="24">Breat</option>
	                            <option value="25">Thighs</option>
	                            <option value="26">Calves</option>
	                            <option value="27">Ankles</option>
	                            <option value="28">Brain</option>
	                            <option value="29">Heart</option>
	                            <option value="30">Liver</option>
	                            <option value="31">Lungs</option>
	                            <option value="32">Ovary</option>
	                            <option value="33">Veins</option>
	                            <option value="34">Cheek</option>
	                            <option value="35">Mouth</option>
	                            <option value="36">Teeth</option>
	                            <option value="37">Tooth</option>
	                            <option value="38">Elbow</option>
	                            <option value="39">Wrist</option>
	                            <option value="40">Hands</option>
	                            <option value="41">Spine</option>
	                            <option value="42">Chest</option>
	                            <option value="43">Navel</option>
	                            <option value="44">Thigh</option>
	                            <option value="45">Knees</option>
	                            <option value="46">Heels</option>
	                            <option value="47">Ankle</option>
	                            <option value="48">Eyes</option>
	                            <option value="49">Vein</option>
	                            <option value="50">Head</option>
	                            <option value="51">Jaw</option>
	                            <option value="52">Chin</option>
	                            <option value="53">Ears</option>
	                            <option value="54">Nose</option>
	                            <option value="55">Neck</option>
	                            <option value="56">Arms</option>
	                            <option value="57">Hand</option>
	                            <option value="58">Hips</option>
	                            <option value="59">Legs</option>
	                            <option value="60">Knee</option>
	                            <option value="61">Calf</option>
	                            <option value="62">Heel</option>
	                            <option value="63">Foot</option>
	                            <option value="64">Feet</option>
	                            <option value="65">Toes</option>
	                            <option value="66">Eye</option>
	                            <option value="67">Jaw</option>
	                            <option value="68">Ear</option>
	                            <option value="69">Arm</option>
	                            <option value="70">Hip</option>
	                            <option value="71">Leg</option>
	                            <option value="72">Toe</option>
	                        </select>
	                    </div>
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

        <footer class="page-footer green darken-3">
            <div class="container">
                <div class="row">
                    <div class="col l6 s12">
                    <h5 class="white-text">Company Bio</h5>
                    <p class="grey-text text-lighten-4">We are a team of college students who are working on this prototype.</p>


                    </div>
                    <div class="col l3 s12">
                        <h5 class="white-text">Team</h5>
                        <ul>
                            <li><a class="white-text" href="#!">Altea</a></li>
                            <li><a class="white-text" href="#!">Dagdag</a></li>
                            <li><a class="white-text" href="#!">Embestro</a></li>
                            <li><a class="white-text" href="#!">Ong</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="footer-copyright green darken-4">
                <div class="container">
                    Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
                </div>
            </div>
        </footer>


        <!--  Scripts-->
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
        <script>
            $(document).ready(function() {
                $('select').formSelect();
            })
            
            function switchSearch() {
            	var nSearch = document.getElementById("normSearch");
            	var aSearch = document.getElementById("advSearch");
            	var sSearch = document.getElementById("switchSearch");
            	
            	if (nSearch.style.display == "none" && aSearch.style.display == "block") {
            		aSearch.style.display = "none";
            		nSearch.style.display = "block";
            		sSearch.textContent = "switch to advanced search";
            	}
            	else {
            		aSearch.style.display = "block";
            		nSearch.style.display = "none";
            		sSearch.textContent = "switch to normal search";
            	}
            	
            }
        </script>
    </body>
</html>