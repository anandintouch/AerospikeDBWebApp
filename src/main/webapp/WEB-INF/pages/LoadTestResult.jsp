
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<title>Aerospike NoSQL DB Application</title>
<style>
table, th, td {
    border: 2px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;    
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
</head>
<script>
function goBack() {
    window.history.back()
}
</script>
<body>

<p class="padding"><img src="images/aerospike_logo_set_horizontal2.png" style="width:304px;height:60px">
In-memory NOSQL DB</p>

<div class="topcorner"><a href="http://${record.loggedInHostName}:8080/AerospikeDBWebApp/credential.htm">Sign out</a></div>
<div class="bottomcorner">
	<a href="images/DataModel.png" target="_blank">Data Model Sample</a>
</div>
<div class="my-block">
     <h4>Response from Aerospike DB API call from the Cluster of Nodes!</h4>
</div>

<br>
<br>
 <table border="1">
 <h4>Performance Test Response :</h4>
<tr>
<td height="150">${message1}</td>
</tr>

</table> 
<br />

<button onclick="goBack()">Load more Records</button>
<footer>
<div class="my-block">
      Powered by <a href="http://www.aerospike.com" target="_blank">Aerospike NOSQL DB</a> 
</div>
</footer>
</body>
</html>