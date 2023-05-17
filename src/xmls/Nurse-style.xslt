<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
	 <head>
		<style>
			 body{
			 	background-color: lightblue;
			 }
			 h1{
			 	color: white;
			 	text-align: center;
			 }
			 p{
			 	font-family: verdana;
			 	text-align: center;
			 }
		</style>
	</head>
	<h1><b>Nurse:</b></h1>
   <p>Name: <xsl:value-of select="Nurse/@nurseName" /></p>
   <p>Surname: <xsl:value-of select="Nurse/@nurseSurname" /></p>
   <p>Email: <xsl:value-of select="Nurse/email" /></p>
   </html>
</xsl:template>

</xsl:stylesheet>