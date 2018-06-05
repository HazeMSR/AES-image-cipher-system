
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
</head>
<body>

<br>
<!-- need to encode all the URLs where we want session information to be passed -->

</body>
</html>




<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="es">
<head>
 

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>Artcripted</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="js/validetta101/validetta.min.css">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style2.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link rel="stylesheet" type="text/css" href="js/confirm/jquery-confirm.min.css">
  
  <script src="js/jquery-3.1.1.min.js"></script>
  <script src="js/validetta101/validetta.min.js"></script>
  <script src="js/validetta101/validetta.js"></script>
  <script src="js/index.js"></script>
  <script src="js/signin.js"></script>
  <script src="js/materialize.js"></script>

<script src="js/confirm/jquery-confirm.min.js"></script>


  <script src="js/init.js"></script>
  <script>

      $(document).ready(function() {
        
        //Abre las reseñas
        /*
        $("#openMod").on("click",function(){
          $("#modalSignIn").modal();
          $("#modalSignIn").modal("open");
        });
*/
      });
  </script>
</head>
<body> 

<%
//allow access only if session exists
String user = null;
if(session.getAttribute("user") == null){
	response.sendRedirect("index.html");
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}else{
	sessionID = session.getId();
}
%>
    <!--Modifica los datos del alumno con la instruccion update en el crud.js y update_AX-->
    <div id="modalSignIn" class="modal">
      <form id="signIn" >
        <div class="modal-content">
          <h4 class="center-align blue white-text"></h4>
            
            <div class="row">
              <div class="col s12 l12 input-field">
                <label for="user">User:</label>
                <input type="text" id="user2" name="user2" data-validetta="required">
              </div>
            </div>
            <div class="row">
              <div class="col s12 l12 input-field">
                <label for="pass">Password:</label>
                <input type="text" id="pass2" name="pass2" data-validetta="required,minLength[8],regExp[passwords]">
              </div>
            </div>

                <br>

           <div class="row">
                <div class="col s12 l12">
                   <button type="submit" class="btn green">Submit</button>
               </div>
           </div>
        </div>
        <div class="modal-footer">
           
                   <button class="modal-close waves-effect waves-red btn-flat">Close</button>
          
        </div>
      </form>
    </div> 
  <div id="modalAX" class="modal">
      <div class="modal-content">
          <h4 class="center-align blue white-text "><div class="fuente">Artcripted</div></h4>
        </div>
      </div>
  </div>
  <div id="index-banner" class="parallax-container">
    <div class="section no-pad-bot">
      <div class="container">
      	<div class="row">
            <div class="col s6 l6 input-field">
                <form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
					<input type="submit" class="btn red" value="Logout" >
				</form>
              </div>
        </div>
        <br><br>
        <h2 class="header center teal-text text-lighten-2"><div style="color:#FFF; font-family: 'Times New Roman', Times, serif;">Hi <%=userName %>, Login successful. Your Session ID=<%=sessionID %></div></h2>
        <div class="row center">
        </div>
        <div class="container">

            <div class="row center">
              <div class="col s12 l6 input-field">
                <!--<label for="user" id="luser"><strong>User:</strong></label>
                  <input type="text" id="user" name="user" data-validetta="required" style=" font-size: 2em;">
              	-->
              </div>
              <div class="col s12 l6 input-field">
                <!--<label for="pass" id="lpass"><strong>Password:</strong></label>
                  <input type="password" id="pass" name="pass" data-validetta="required" style=" font-size: 2em;">
                 -->
              </div>
            </div>

         
            <br>
          <div class="row center">
            <div class="col s12 l12 input-field" id="openMod">
                <div class="col s12 l6 input-field">
                	<form method="post">
						<input type="submit" class="btn black" value="Generate new keys" >
					</form>
              	</div>
				<div class="col s12 l6 input-field">
                	<form action="<%=response.encodeURL("DownloadFileServlet") %>" method="get">
						<input type="submit" class="btn black" value="Download digital certificate" >
					</form>
              	</div>
            </div>
          </div>
        </div>
        <br><br>

      </div>
    </div>
    <div class="parallax"><img src="img/wu1.jpg" alt="Unsplashed background img 1"></div>
  </div>


  <div class="container">
    <div class="section">
      <!--   Icon Section   -->
          <div class="icon-block">

            <div class="row center">
                <div class="col s12 m12 l12">
                    <h3><b?</b></h3>
                    <br>
                </div>
            </div>
            <div class="row center">
                <div class="col s12 m8 l12">
                    <h5> ? </h5>
                    
                    <h5>?</h5>
                </div>
            </div>
        </div>
    </div>
  </div>



  <div class="parallax-container valign-wrapper" id="banner">
    <div class="section no-pad-bot">
      <div class="container">
        <div class="row center">
          <div class="azul-p col s12 l12" ><h3><b>?</b></h3></div>
          <BR>
        </div>
        <div class="row center">
          <div class="azul-p col s12 l12" ><h5>? </h5></div>
        </div>
      </div>
    </div>
    <div class="parallax"><img src="img/w3.jpg" alt="Unsplashed background img 2"></div>
  </div>



  <div class="container">
    <div class="section">
      <!--   Icon Section   -->
          <div class="icon-block">
            <div class="row">
                <div class="col s12 m12 l12">
                <h1 class="center blue-text"><i class="material-icons">group</i></h1>
                </div>
            </div>
            <div class="row center">
                <div class="col s12 m12 l12">
                     <h5>Developed by:</h5>
                </div>
            </div>
            <div class="row center">
                <div class="col s12 m12 l12">
              ANDRADE GUZMAN JAVIER ALEJANDRO
                </div>
            </div>
            <div class="row center">
                <div class="col s12 m12 l12">
              CID VAZQUEZ EDDER ROBERTO
                </div>
            </div>
            <div class="row center">
                <div class="col s12 m12 l12">
              MARTINEZ SAN ROMAN AARON HAZEL
                </div>
            </div>
          </div>
        </div>
    </div>
  <div class="parallax-container valign-wrapper" id="banner">
    <div class="section no-pad-bot">
      <div class="container">
        <div class="row center">
          <div class="azul-p col s12 l12" ><h2><b>Cryptography - 3CV7</b></h2></div>
          <BR>
        </div>
        <div class="row center">
          <div class="azul-p col s12 l12" ><h5>ESCOM - IPN</h5></div>
        </div>
      </div>
    </div>
    <div class="parallax"><img src="img/w2.jpg" alt="Unsplashed background img 2"></div>
  </div>
  </body>


</html>