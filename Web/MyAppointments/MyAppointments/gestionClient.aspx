﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="MyAppointments.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="icon" href="images/icone.png" />
    <link rel="stylesheet" type="text/css" href="CSS/main.css" />
    <link rel="stylesheet" type="text/css" href="CSS/style.css" />
    <link rel="stylesheet" type="text/css" href="CSS/animate-custom.css" />
    <title>MyAppointments</title>
</head>
<body>
    <div id="title">
        <img src="images/logo.png" />
    </div>
    <div id="container_demo" >
        <div id="wrapper_list">
            <div id="login" class="animate form">
                <form id="gestionClient" runat="server">
                    <h1>Comptes de ...</h1>     
                    <table class="modifCli">                 
                        <tr> 
                            <td><label for="gestNum" class="uname">Numéro de Dossier</label></td>
                            <td>1</td>
                        </tr>  
                        <tr> 
                            <td><label for="gestNom" class="uname">Nom</label></td>
                            <td><input id="gestNom" name="gestNom" required="required" type="text" size="60"/></td>
                        </tr>
                        <tr> 
                            <td><label for="gestPrenom" class="uname">Prénom</label></td>
                            <td><input id="gestPrenom" name="gestPrenom" required="required" type="text" size="60"/></td>
                        </tr>
                        <tr> 
                            <td><label for="gestTel" class="uname">Numéro de Téléphone</label></td>
                            <td><input id="gestTel" name="gestTel" required="required" type="text" size="60"/></td>
                        </tr>
                        <tr> 
                            <td><label for="gestMail" class="uname">E-Mail</label></td>
                            <td><input id="gestMail" name="gestMail" required="required" type="text" size="60"/></td>
                        </tr>
                    </table>
                    <div id="wrapper_btn" >
                        <p class="button modif"> 
						    <input type="submit" value="Modifier"/> 
					    </p>
                        <p class="button supp"> 
						    <input type="submit" value="Supprimer"/> 
					    </p>   
                    </div>      
                    <p class="change_link_lst">
                        <a href="/client.aspx" class="to_register">Annuler</a>
                    </p>
                 </form>
            </div>					
        </div>
    </div>  
</body>
</html>
