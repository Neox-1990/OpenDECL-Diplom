<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:openDECL="http://www.tu-dresden.de" exclude-result-prefixes="openDECL">
<xsl:template match="//openDECL:display-setup[@id='flat']">
<pvx>
<Process Type="client" />
<Process Type="render-server">
<xsl:for-each select="openDECL:display">
<Machine Name="MULTI" Environment="DISPLAY=:0" FullScreen="0" ShowBorders="0">
<xsl:attribute name="Geometry"><xsl:value-of select="@pixel-size-x"/>x<xsl:value-of select="@pixel-size-y"/>+<xsl:value-of select="openDECL:virtual/openDECL:upper-left/openDECL:vector/@x"/>+<xsl:value-of select="openDECL:virtual/openDECL:upper-left/openDECL:vector/@y"/></xsl:attribute>
<xsl:attribute name="LowerLeft"><xsl:value-of select="openDECL:physical/openDECL:lower-left/openDECL:vector/@x"/><xsl:text disable-output-escaping="yes"> </xsl:text><xsl:value-of select="openDECL:physical/openDECL:lower-left/openDECL:vector/@y"/><xsl:text disable-output-escaping="yes"> </xsl:text><xsl:value-of select="openDECL:physical/openDECL:lower-left/openDECL:vector/@z"/></xsl:attribute>
<xsl:attribute name="LowerRight"><xsl:value-of select="openDECL:physical/openDECL:lower-right/openDECL:vector/@x"/><xsl:text disable-output-escaping="yes"> </xsl:text><xsl:value-of select="openDECL:physical/openDECL:lower-right/openDECL:vector/@y"/><xsl:text disable-output-escaping="yes"> </xsl:text><xsl:value-of select="openDECL:physical/openDECL:lower-right/openDECL:vector/@z"/></xsl:attribute>
<xsl:attribute name="UpperRight"><xsl:value-of select="openDECL:physical/openDECL:upper-right/openDECL:vector/@x"/><xsl:text disable-output-escaping="yes"> </xsl:text><xsl:value-of select="openDECL:physical/openDECL:upper-right/openDECL:vector/@y"/><xsl:text disable-output-escaping="yes"> </xsl:text><xsl:value-of select="openDECL:physical/openDECL:upper-right/openDECL:vector/@z"/></xsl:attribute>
</Machine>
</xsl:for-each>
</Process>
</pvx>
</xsl:template>
</xsl:stylesheet>