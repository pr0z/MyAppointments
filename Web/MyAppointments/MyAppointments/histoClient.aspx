<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="histoClient.aspx.cs" Inherits="MyAppointments.histoClient" %>

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
        <div id="wrapper_list">
            <div id="login" class="animate form">
                <form id="formhistoClient" runat="server">
                   <h1>Historique de ...</h1>    
                    <input class="recherche" name="searchbar" type="text" />
                    <table id="lst_Cli">
                        <tr>
                            <th>Date</th>					
                            <th>Catégorie</th>
                            <th>Profession</th>
                            <th>Professionnel</th>
                            <th>Ville</th>
                            <th>Note</th>
                            <th>Détails</th>
                        </tr>
                        <tr>
                            <td>20/01/2014</td>					
                            <td>Santé</td>
                            <td>Cardiologue</td>
                            <td>Dr. Jean Cartier</td>
                            <td>Paris</td>
                            <td>*****</td>
                            <td> <a href="#"><img class="icone_Client" src="images/detail.png" width="30" /></a></td>
                        </tr>
                        <tr>
                            <td>12/04/2014</td>					
                            <td>Service</td>
                            <td>Plombier</td>
                            <td>Philipe Bonval</td>
                            <td>Paris</td>
                            <td>***</td>
                            <td> <a href="#"><img class="icone_Client" src="images/detail.png" width="30" /></a></td>
                        </tr>
                    </table>                     						          
                    <p class="change_link_lst">
                        <a href="/client.aspx" class="to_register">Retour</a>
                    </p>
                 </form>
            </div>					
        </div>
    </div>  
</body>
</html>
