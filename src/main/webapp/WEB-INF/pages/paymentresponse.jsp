
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<style>
p {
    background-color: CornflowerBlue;
}

p.padding {
    padding-top: 10px;
    padding-right: 30px;
    padding-bottom: 10px;
    padding-left: 30px;
}
.topcorner{
    position:absolute;
    top:15px;
    right: 10px;
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
<title>Aerospike NoSQL DB Application</title>
</head>
<script>
function goBack() {
    window.history.back()
}
</script>
<body>
<p class="padding"><img src="images/aerospike_logo_set_horizontal2.png" style="width:304px;height:60px">
In-memory fastest NOSQL DB</p>
<div class="topcorner"><a href="http://${credential.hostName}:8080/AerospikeDBWebApp/credential.htm">Sign out</a></div>
<div class="my-block">
      <h4>Response from Aerospike DB API call from the Cluster of Nodes!</h4>
</div>

<%--<table> 
<tr>
<td>Hostname Used :</td><td>${payment.hostName}</td>
</tr>
</table> --%>

 <table border="0">
<tr>
<td><h4>Hostname :</h4></td><td> ${credential.hostName}</td>
</tr>

</table> 


<table border="1">
<tr>
<h4>Database Response</h4>
<td height="150">${message}</td>
</tr>
</table> 
<br />
<button onclick="goBack()">Go Back</button>
<footer>
<div class="my-block">
      Powered by <a href="http://www.aerospike.com" target="_blank">Aerospike NOSQL DB</a> 
</div>
</footer>
</body>
</html>