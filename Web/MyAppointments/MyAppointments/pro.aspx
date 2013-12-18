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
                <form id="Form1" action="pro.aspx" autocomplete="on" runat="server"> 
                    <h1>Comptes professionels</h1> 
                    <input class="recherche" name="searchbar" type="text" />
                    <table id="lst_Pro">
                        <tr>
                            <th>Raison Soc.</th>
                            <th>SIREN</th>
                            <th>Responsable</th>
                            <th>Téléphone</th>
                            <th>Mail</th>
                            <th>Adresse</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>LaProcu</td>
                            <td>093031</td>
                            <td>M. Labrune</td>
                            <td>06 12 12 12 12</td>
                            <td>labr@lac.com</td>
                            <td>19, rue de Pau</td>
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
