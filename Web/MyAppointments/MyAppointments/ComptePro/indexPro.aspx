﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="indexPro.aspx.cs" Inherits="MyAppointments.ComptePro.indexPro" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8"/>
    <link rel="icon" href="../images/icone.png" />
    <link rel="stylesheet" type="text/css" href="../CSS/main.css" />
    <link rel="stylesheet" type="text/css" href="../CSS/style.css" />
    <link rel="stylesheet" type="text/css" href="../CSS/animate-custom.css" />
    <link rel="stylesheet" type="text/css" href="../CSS/overcast/jquery-ui-1.10.3.custom.min.css" />
    <script type="text/javascript" src="../js/fonctions.js"></script>
    <script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="../js/jquery-ui-1.10.3.custom.js"></script>
    <script type="text/javascript" src="../js/jquery-ui-1.10.3.custom.min.js"></script>
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
        <div id="title">
            <img src="../images/logo.png" />
        </div>
        <div id="container_demo" >
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form id="Form1" action="index.aspx" autocomplete="on" runat="server"> 
                        <h1>Professionel</h1> 
                        <p id="msgerror">
                            <asp:Label ID="error" runat="server" Text=""></asp:Label>
                        </p>
                        <p> 
                            <label for="username" class="uname" data-icon="u" >Adresse E-mail</label>
                            <asp:TextBox ID="username" runat="server"></asp:TextBox>
                        </p>
                        <p> 
                            <label for="password" class="youpasswd" data-icon="p"> Mot de passe </label>                          
                            <input id="password" name="password" type="password" required="required" runat="server" />
                        </p>
                        <p class="keeplogin"> 
							<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> 
							<label for="loginkeeping">Se souvenir de moi</label>
						</p>
                        <p class="login button"> 
                            <a href="../index.aspx"><input type="button" value="Quitter" runat="server" /></a>
                            <asp:Button ID="connexion" text="Connexion" runat="server" OnClick="OnClickConnexion" />
						</p>
                        <p class="change_link">
							Pas encore membre ?
							<a href="#toregister" class="to_register">Inscrivez-vous</a>
						</p>
                    </form>
                </div>
                <div id="register" class="animate form">
                    <form  action="index.aspx" autocomplete="on"> 
                        <h1> Inscription</h1>                                                                            
                        <p> 
                            <label for="prosirensignup" class="uname" data-icon="u">N° SIREN</label>
                            <input id="prosirensignup" name="prosirensignup" required="required" type="text" />
                        </p>
                        <p> 
                            <label for="pronamesignup" class="uname" data-icon="u">Nom</label>
                            <input id="pronamesignup" name="pronamesignup" required="required" type="text" />
                        </p>
                        <p> 
                            <label for="protelsignup" class="uname">Numéro de téléphone</label>
                            <input id="protelsignup" name="protelsignup" required="required" type="text" />
                            <img src="../images/phone.png" class="icon_phone"/> 
                        </p>
                        <p> 
                            <label for="promailsignup" class="youmail" data-icon="e" >E-mail</label>
                            <input id="promailsignup" name="promailsignup" required="required" type="email" /> 
                        </p>
                        <p> 
                            <label for="procontactnamesignup" class="uname" data-icon="u" >Nom Contact</label>
                            <input id="procontactnamesignup" name="procontactnamesignup" required="required" type="email" /> 
                        </p>
                        <p> 
                            <label for="procontactsurnamesignup" class="uname" data-icon="u" >Prénom Contact</label>
                            <input id="procontactsurnamesignup" name="procontactsurnamesignup" required="required" type="email" /> 
                        </p>
                        <p> 
                            <label for="propasswordsignup" class="youpasswd" data-icon="p">Choisir un mot de passe </label>
                            <input id="propasswordsignup" name="propasswordsignup" required="required" type="password" />
                        </p>
                        <p> 
                            <label for="propasswordsignup_confirm" class="youpasswd" data-icon="p">Confirmez votre mot de passe </label>
                            <input id="propasswordsignup_confirm" name="propasswordsignup_confirm" required="required" type="password"/>
                            <asp:Label ID="labelTest" runat="server" Text=""></asp:Label>
                        </p>
                        <p class="signin button"> 
                            <a href="../index.aspx"><input type="button" value="Quitter" runat="server" /></a>
							<input id="Submit1" type="submit" value="S'inscrire" runat="server" onclick="OnClickInscription"/> 
						</p>
                        <p class="change_link">  
							Déjà membre ?
							<a href="#tologin" class="to_register"> Connexion </a>
						</p>
                    </form>
                </div>					
            </div>
        </div>         
</body>
</html>

