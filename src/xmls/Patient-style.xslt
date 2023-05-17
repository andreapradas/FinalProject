<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
 
   <p><b>Patient:</b></p><xsl:value-of select="Patient" />
   <p>Name: <xsl:value-of select="@patientName" /></p>
   <p>Surname: <xsl:value-of select="@patientSurname" /></p>
   <p>Phone: <xsl:value-of select="phoneNumber" /></p>

  
   </html>
</xsl:template>

</xsl:stylesheet>