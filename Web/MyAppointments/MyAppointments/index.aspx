<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="MyAppointments.index" %>

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
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form action="index.aspx" autocomplete="on" runat="server"> 
                        <h1>Bienvenue</h1> 
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
                        <h1> Inscription </h1>                    
                        <table id="menu">
                            <tr>
                                <td><label for="typesignup1" >CLIENT</label></td>
                                <td><input id="typesignup1" name="type" type="radio" checked="checked" required="required" value="1"/></td>
                                <td><label for="typesignup1" >PROFESSIONEL</label></td>
                                <td><input id="typesignup2" name="type" type="radio" required="required" value="2" /></td>
                            </tr>
                        </table>                                       
                        <p> 
                            <label for="usernamesignup" class="uname" data-icon="u">Nom</label>
                            <input id="usernamesignup" name="usernamesignup" required="required" type="text" />
                        </p>
                        <p> 
                            <label for="usersurnamesignup" class="uname" data-icon="u">Prénom</label>
                            <input id="usersurnamesignup" name="usersurnamesignup" required="required" type="text" />
                        </p>
                        <p> 
                            <label for="usertelsignup" class="uname">Numéro de téléphone</label>
                            <input id="usersurtelsignup" name="usersurtelsignup" required="required" type="text" />
                        </p>
                        <p> 
                            <label for="userbirthsignup" class="uname">Date de naissance</label><br />
                            <select id="userbirthdaysignup" required="required" runat="server">
                                <option>1</option>
                                <option>1</option>
                                <option>1</option>
                                <option>1</option>
                            </select>
                            <select id="userbirthmonthsignup" required="required" runat="server">
                                <option>1</option>
                                <option>1</option>
                                <option>1</option>
                                <option>1</option>
                            </select>
                            <select id="userbirthyearsignup" required="required" runat="server">
                                <option>1</option>
                                <option>1</option>
                                <option>1</option>
                                <option>1</option>
                            </select>
                        </p>
                        <p> 
                            <label for="emailsignup" class="youmail" data-icon="e" >E-mail</label>
                            <input id="emailsignup" name="emailsignup" required="required" type="email" /> 
                        </p>
                        <p> 
                            <label for="passwordsignup" class="youpasswd" data-icon="p">Choisir un mot de passe </label>
                            <input id="passwordsignup" name="passwordsignup" required="required" type="password" />
                        </p>
                        <p> 
                            <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Confirmez votre mot de passe </label>
                            <input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password"/>
                        </p>
                        <p class="signin button"> 
							<input type="submit" value="S'inscrire"/> 
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