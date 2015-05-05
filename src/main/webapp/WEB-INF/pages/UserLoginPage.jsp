<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<style>
.error {
	color: #ff0000;
}
.errorblock{
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding:8px;
	margin:16px;
}
.button
{
background-color: #color;
border-bottom:solid;
border-left: #FFEEEE;
border-right:solid;
border-top: #EEEEEE;
color: black;
font-family: Verdana, Arial
}

#user-id-div
{
 width:200px;
margin:auto;    	
}
p {
    background-color: CornflowerBlue;
}

p.padding {
    padding-top: 10px;
    padding-right: 30px;
    padding-bottom: 10px;
    padding-left: 30px;
}
.my-block {
    text-align: center;
    vertical-align: middle;
}
footer {
    background-color: DarkGray;
    position: absolute;
    left: 0;
    bottom: 0;
    height: 60px;
    width: 100%;
    overflow:hidden;
}
</style>

</head>
<body>
<p class="padding" ><img src="images/aerospike_logo_set_horizontal2.png" style="width:304px;height:60px">
In-memory fastest NOSQL DB</p>

<div class="my-block">
      <h2>Aerospike Web Client Application</h2>
</div>

<div id="user-id-div">
<form:form method="POST" commandName="credential">

<form:errors path="*" cssClass="errorblock" element="div"/>

<br></br>
<table width="250%">
<tr>
<th>User Credentials </th>
</tr>

<tr>
<td> User Name : </td>
<td><form:input path="username" /></td>
<td><form:errors path="username" cssClass="error" /></td>
</tr>
<tr>
<td>Password : </td>
<td><form:input type ="password" path="password" /></td>
<td><form:errors path="password" cssClass="error" /></td>
</tr>
<tr>
<br></br>
<th>Seed Node details </th>
</tr>
<tr>
<td>Hostname : </td>
<td><form:input path="hostName" /></td>
<td><form:errors path="hostName" cssClass="error" /></td>
</tr>
<tr>
<td>Port : </td>
<td><form:input path="port" /></td>
<td><form:errors path="port" cssClass="error" /></td>
</tr>

<form:hidden path="secretValue" />

<tr>
<td colspan="3"><input type="submit" /></td>
</tr>

</table>

</form:form>
<footer>
<div class="my-block">
      Powered by <a href="http://www.aerospike.com" target="_blank">Aerospike NOSQL DB</a> 
</div>
</footer>
</div>
</body>
</html>