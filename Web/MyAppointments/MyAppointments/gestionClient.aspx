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
                <form id="gestionClient" runat="server">
                   <h1>Comptes de ...</h1> 
                </form>
                <p class="change_link_lst">
                     <a href="#" class="to_register">Modifier</a>
                     <a href="#" class="to_register">Supprimer ce compte</a>
                    <a href="/client.aspx" class="to_register">Annuler</a>
                </p>
            </div>					
        </div>
    </div>  
</body>
</html>
