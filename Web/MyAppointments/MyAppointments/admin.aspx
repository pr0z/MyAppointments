﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="admin.aspx.cs" Inherits="MyAppointments.admin" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
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
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form action="admin.aspx" autocomplete="on"> 
                        <h1>Administrateur</h1> 
                        <table id="menu">
                            <tr>
                                <td><a href="client.aspx"><img src="images/client.png" width="120"/></a></td>
                                <td><a href="pro.aspx"><img src="images/pro.png" width="115" /></a></td>                                   
                            </tr>
                            <tr>
                                <td><label for="cli" class="uname"><a href="client.aspx">Comptes CLIENTS</a></label></td>
                                <td><label for="pro" class="uname"><a href="pro.aspx"> Comptes PROFESSIONELS</a></label></td>
                            </tr>
                        </table> 
                        <p class="change_link">  
							<a href="/index.aspx" class="to_register"> Déconnexion </a>
						</p>                         
                    </form>
                </div>					
            </div>
        </div>
</body>
</html>
