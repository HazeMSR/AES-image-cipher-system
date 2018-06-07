
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>





<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<!DOCTYPE html>
<html lang="es">
<head>
 

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>Login</title>

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
              <h3 class="blue-text">Image upload</h3>
              <br>
            </div>
          </div>
            
          <form id="uploadImg" action="UploadImage" method="post">
            <div class="row">
              <div class="file-field input-field">
                <div class="btn">
                  <span>Select the image</span>
                  <input type="file" accept="image/*">
                </div>

              <input type="hidden" name="user" id="user" value="admin">
              <div class="file-path-wrapper">
                  <input class="file-path validate" type="text">
              </div>
                  </div>
              </div>
              <div class="row center">
                  <input type="submit" class="btn purple" value="Submit" >
              </div>
          </form>
        </div>
        <div class="row center">
            <div class="col s12 m12 l12">
              <h3 class="blue-text">Received images</h3>
              <br>
            </div>
          </div>
<%
/*
        String selectSQL = "SELECT * FROM user WHERE id=";
        ResultSet rs = null;
       
 
        try (Connection conn=Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(selectSQL);) {
                  pstmt.setInt(1, candidateId);
 
            System.out.println("Writing to file ");
             rs = pstmt.executeQuery();
            while (rs.next()) {
*/

%>
  <img class="responsive-img" src="img/file.png" style="max-width: 15%">
<%
/*
            }
        } catch (SQLException) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
*/
%>

    </div>
  </div>




  <div class="parallax-container valign-wrapper" id="banner">
    <div class="section no-pad-bot">
      <div class="container">
        <div class="row center">
            <div class="col s12 m12 l12">
                <h2 class="black-text">Decrypt an image</h2>
                <br>
            </div>
        </div>
        <div class="row center">
            <div class="col s12 m12 l12">
                <h3 class="blue-text">Steps:</h3>
            </div>
        </div>
        <div class="row center">
            <div class="col s12 m12 l12">
                <h4 class="blue-text">1) Download the files</h4>
            </div>
        </div>
        <div class="row center">
            <div class="col s12 m6 l6">
                <form action="<%=response.encodeURL("DownloadEncImg") %>" method="post">
                        <input type="submit" class="btn black" value="Download Encrypted Image" >
                </form>
            </div>
        </div>
        <div class="row center">
            <div class="col s12 m6 l6">
                <form action="<%=response.encodeURL("DownloadEncKey") %>" method="post">
                    <input type="hidden" name="enc_key" id="enc_key" value="">
			                <input type="submit" class="btn black" value="Download Encrypted Key" >
                </form>
            </div>
        </div>
                
            <!--
            <div class="row center">
                <div class="col s12 m12 l12">
                    <h4 class="white-text">2) Write the user name who wants to be reference</h4>
                </div>
            </div>
            <div class="row center">
                <div class="col s12 m6 l6">
                    <div class="input-field col s6">
                        <i class="material-icons prefix">account_circle</i>
     
                        <div class="input-field col s6">
                            <i class="material-icons prefix">account_circle</i>
                            <input name="user" id="icon_prefix" type="hidden" class="validate" data-validetta="required,maxLength[255]">
                            <label for="icon_prefix">First Name</label>
                        </div>>
                        
                    </div>
                </div>
            </div>
            -->
        <div class="row center">
            <div class="col s12 m12 l12">
                <h4 class="blue-text">2) Upload the files</h4>
            </div>
        </div>
        <div class="row">
            <div class="file-field input-field">
                <div class="btn">
                    <span>Select the encrypted image</span>
                    <input type="file" name="enc_img" >
                    
                </div>
                <input type="hidden" name="user" id="user" value="admin">
                <div class="file-path-wrapper">
                    <input class="file-path validate" type="text">
                </div>
            </div>
        </div>
                                
        <div class="file-field input-field">
            <div class="btn">
                <span>Select the encrypted key</span>
                <input type="file" name="enc_key" >
                
            </div>
            
            <div class="file-path-wrapper">
                <input class="file-path validate" type="text">
            </div>
        </div>
        <div class="row center">
          <div class="col s12 m12 l12">
              <h4 class="blue-text">3) Download the decrypted img</h4>
          </div>
        </div>
        <div class="row center">
          <div class="col s12 m6 l6">
            <form action="<%=response.encodeURL("DownloadEncImg") %>" method="post">
	          <input type="submit" class="btn black" value="Download Decrypted Image" >
          </div>
        </div>              
      </div>        
    </div>
    <div class="parallax"><img src="img/wu2.jpeg" alt="Unsplashed background img 2"></div>
  </div>



  <div class="container">
    <div class="section">
            <div class="row center">
                <div class="col s12 m12 l12">
                    <h3 class="green-text"> Confirm the price to pay with digital certificate</h3>
                </div>
            </div>
            <form>
              <div class="row center">
                <div class="col s12 m12 l12 input-fields">
                  <i class="material-icons prefix">account_circle</i>
  
                  <input name="message" id="icon_prefix" type="text" class="validate" data-validetta="required,maxLength[255]">
                  <label for="icon_prefix">Enter the message:</label>
                </div>
              </div>
              <div class="row center">
                <div class="col s12 m12 l12">
                  <input type="submit" class="btn blue" value="Submit">
                </div>
              </div>
            </form>
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
        <div class="row">
            <div class="col s6 l6 input-field">
                <form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
                 <input type="submit" class="btn red" value="Logout" >
              </form>  
            </div>
        </div>
      </div>
    </div>
    <div class="parallax"><img src="img/w2.jpg" alt="Unsplashed background img 2"></div>
  </div>

  </body>


</html>