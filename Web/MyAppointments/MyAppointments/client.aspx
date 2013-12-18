<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="MyAppointments.index" %>

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
                <form action="client.aspx" autocomplete="on" runat="server"> 
                    <h1>Comptes Clients</h1> 
                    <input class="recherche" name="searchbar" type="text" />
                    <table id="lst_Cli">
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Mail</th>
                            <th>Téléphone</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>GRAUX</td>
                            <td>Benjamin</td>
                            <td>benjamin.graux@gmail.com</td>
                            <td>06 12 12 12 12</td>
                            <td>
                                <a href="/gestionClient.aspx"><img class="icone_Client" src="images/gestion.png" width="30" /></a> 
                                <a href="#"><img class="icone_Client" src="images/notif.png" width="30" /></a> 
                                <a href="#"><img class="icone_Client" src="images/history.png" width="30" /></a>
                            </td>
                        </tr>
                        <tr>
                            <td>LEICHNIG</td>
                            <td>Roman</td>
                            <td>roman.leichnig@gmail.com</td>
                            <td>06 12 12 12 12</td>
                            <td>
                                <a href="#"><img class="icone_Client" src="images/gestion.png" width="30" /></a> 
                                <a href="#"><img class="icone_Client" src="images/notif.png" width="30" /></a> 
                                <a href="#"><img class="icone_Client" src="images/history.png" width="30" /></a>
                            </td>
                        </tr>
                        <tr>
                            <td>WETSTEIN</td>
                            <td>Julien</td>
                            <td>julien@gmail.com</td>
                            <td>06 12 12 12 12</td>
                            <td>
                                <a href="#"><img class="icone_Client" src="images/gestion.png" width="30" /></a> 
                                <a href="#"><img class="icone_Client" src="images/notif.png" width="30" /></a> 
                                <a href="#"><img class="icone_Client" src="images/history.png" width="30" /></a>
                            </td>
                        </tr>
                        <tr>
                            <td>DUPONT</td>
                            <td>Lucie</td>
                            <td>lucie.dupont@gmail.com</td>
                            <td>06 12 12 12 12</td>
                            <td>
                                <a href="#"><img class="icone_Client" src="images/gestion.png" width="30" /></a> 
                                <a href="#"><img class="icone_Client" src="images/notif.png" width="30" /></a> 
                                <a href="#"><img class="icone_Client" src="images/history.png" width="30" /></a>
                            </td>
                        </tr>
                    </table>
                    <p class="change_link_lst">
                        <a href="/admin.aspx" class="to_register">Retour</a>
                    </p>
                </form>
            </div>					
        </div>
    </div>
    
</body>
</html>
