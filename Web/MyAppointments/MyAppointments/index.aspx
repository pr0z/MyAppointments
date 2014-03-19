<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="MyAppointments.index2" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8"/>
    <link rel="icon" href="images/icone.png" />
    <link rel="stylesheet" type="text/css" href="CSS/main.css" />
    <link rel="stylesheet" type="text/css" href="CSS/style.css" />
    <link rel="stylesheet" type="text/css" href="CSS/animate-custom.css" />
    <link rel="stylesheet" type="text/css" href="CSS/overcast/jquery-ui-1.10.3.custom.min.css" />
    <script type="text/javascript" src="js/fonctions.js"></script>
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.10.3.custom.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker({
                changeMonth: true,
                changeYear: true,
                firstDay: 1
            });
        });
    </script>
    <title>MyAppointments</title>
</head>
<body>
    <form id="indexAppli" runat="server">
        <div id="titleIndex">
            <img src="images/logo.png"/><br />
            <p>Service de gestion de prises de rendez-vous <br />pour vous faciliter la vie</p>
        </div>
        <div id="menuIndex">
            <p>Text qui introduit le menu</p><br /><br />
			<a href="CompteClient/indexClient.aspx" class="btnLinkIndex">Compte Client</a><br /><br />
            <a href="ComptePro/indexPro.aspx" class="btnLinkIndex">Compte Professionel</a><br /><br />
            <p>Text qui conclut le menu</p>
        </div>
        <div style="clear: both"></div>  
    </form>
</body>
</html>
