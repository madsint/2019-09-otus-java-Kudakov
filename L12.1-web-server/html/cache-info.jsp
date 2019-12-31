<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="styles.css">
<title>Cache Information</title>
</head>
<body>

	<h3>Cache parameters</h3>

	<table>
		<tr>
			<th>Attribute</th>
			<th>Value</th>
		</tr>

		<tr>
			<td>Max.capacity</td>
			<td>${maxCapacity}</td>
		</tr>

		<tr>
			<td>Life time</td>
			<td>${lifeTime}</td>
		</tr>

		<tr>
			<td>Idle time</td>
			<td>${idleTime}</td>
		</tr>

		<tr>
			<td>Eternal</td>
			<td>${isEternal}</td>
		</tr>

		<tr>
			<td>Elements in cache</td>
			<td>${cacheSize}</td>
		</tr>

		<tr>
			<td>Hit count</td>
			<td>${hitCount}</td>
		</tr>

		<tr>
			<td>Miss count</td>
			<td>${missCount}</td>
		</tr>

	</table>

	<a class="button" href="cache-info">Refresh</a>

</body>
</html>
