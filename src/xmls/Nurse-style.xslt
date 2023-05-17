<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
	   
   <p><b>Nurse:</b></p><xsl:value-of select="Nurse" />
   <p>Name: <xsl:value-of select="@nurseName" /></p>
   <p>Surname: <xsl:value-of select="@nurseSurname" /></p>
   <p>Email: <xsl:value-of select="email" /></p>

  
   </html>
</xsl:template>

</xsl:stylesheet>