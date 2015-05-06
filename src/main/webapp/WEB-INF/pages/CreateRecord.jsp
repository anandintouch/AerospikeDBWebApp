<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
table, th, td {
    border: 1px solid black;
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
    top:10px;
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
<script type="text/javascript">
function clearFields() {
    document.getElementById("setName").value=""
    document.getElementById("primaryKey").value=""
    document.getElementById("binName").value=""
    document.getElementById("binValue").value=""
}
</script>
<body>

<p class="padding" align="left">
<img src="images/aerospike_logo_set_horizontal2.png" style="width:304px;height:60px">
In-memory NOSQL DB


</p>

<div class="topcorner"><a href="http://${credential.hostName}:8080/AerospikeDBWebApp/credential.htm">Sign out</a></div>
<div class="my-block">
      <h4>Input data to create a Record in Aerospike DB:</h4>
</div>


<form:form method="POST" commandName="record" action="/AerospikeDBWebApp/record.htm">
<form:errors path="*" cssClass="errorblock" element="div"/>
<br>
<br>
<table style="width:100%">
  <tr>
    <th>Set Name</th>
    <th>Key</th>
    <th>Bin Name</th>
   <th colspan="2">Bin Value</th>
  </tr>
  <tr>
    <td><input type="text" name="setName"></td>
    <td><input type="text" name="primaryKey" /></td>
    <td><input type="text" name="binName" /></td>
    <td><input type="text" name="binValue" /></td>
  </tr>
</table>
<br>
<br>
<tr>
<td colspan="3"><input type="submit" value="Save Record" /></td>
</tr>
<br><br>

<h4>Performance Test:</h4>
<table class="center-div" style="width: 500px; border: #999999 1px solid;">

	<td class="td-top td-center">
		<table style="width:100%" >
		<tr>
		<td > Set Name : </td>
		<td><input type="text" name="setName"></td>
		</tr>
		<tr>
		<td> Bin Name : </td>
		<td><input type="text" name="binName"></td>
		</tr>
		<tr>
		<td> Bin Value : </td>
		<td><input type="text" name="binValue"></td>
		</tr>
		<tr>
		<td> Record Count : </td>
		<td><input type="text" name="recordCount"></td>
		</tr>
		</table>
	</td>
		<tr>
		<td colspan="3"><input type="submit" value="Test Load" /></td>
		</tr>
</table>

</form:form>
<footer>
<div class="my-block">
      Powered by <a href="http://www.aerospike.com" target="_blank">Aerospike NOSQL DB</a> 
</div>
</footer>
</body>
</html>