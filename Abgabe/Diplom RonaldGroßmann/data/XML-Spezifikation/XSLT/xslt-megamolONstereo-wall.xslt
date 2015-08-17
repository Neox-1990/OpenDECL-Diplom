<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:openDECL="http://www.tu-dresden.de" exclude-result-prefixes="openDECL">
<xsl:template match="//openDECL:display-setup[@id='stereo']">
<set name="tile1-window">
<xsl:attribute name="value">x<xsl:value-of select="openDECL:display[@id='projL']/openDECL:virtual/openDECL:upper-left/openDECL:vector/@x"/>y<xsl:value-of select="openDECL:display[@id='projL']/openDECL:virtual/openDECL:upper-left/openDECL:vector/@y"/>w<xsl:value-of select="openDECL:display[@id='projL']/openDECL:virtual/openDECL:lower-right/openDECL:vector/@x"/>h<xsl:value-of select="openDECL:display[@id='projL']/openDECL:virtual/openDECL:lower-right/openDECL:vector/@y"/>nd</xsl:attribute>
</set>
<set name="tile2-window">
<xsl:attribute name="value">x<xsl:value-of select="openDECL:display[@id='projR']/openDECL:virtual/openDECL:upper-left/openDECL:vector/@x"/>y<xsl:value-of select="openDECL:display[@id='projR']/openDECL:virtual/openDECL:upper-left/openDECL:vector/@y"/>w<xsl:value-of select="openDECL:display[@id='projR']/openDECL:virtual/openDECL:lower-right/openDECL:vector/@x"/>h<xsl:value-of select="openDECL:display[@id='projR']/openDECL:virtual/openDECL:lower-right/openDECL:vector/@y"/>nd</xsl:attribute>
</set>
<xsl:choose>
<xsl:when test="openDECL:display[@id='projL']/@stereo = 'left-eye'">
::tile1::TileView1::eye=Left Eye
::tile1::TileView1::projType=Stereo OffAxis</xsl:when>
<xsl:when test="openDECL:display[@id='projL']/@stereo = 'right-eye'">
::tile1::TileView1::eye=Right Eye
::tile1::TileView1::projType=Stereo OffAxis</xsl:when>
</xsl:choose>
::tile1::TileView1::tile=<xsl:value-of select="openDECL:display[@id='projL']/openDECL:virtual/openDECL:upper-left/openDECL:vector/@x"/>;<xsl:value-of select="openDECL:display[@id='projL']/openDECL:virtual/openDECL:upper-left/openDECL:vector/@y"/>;<xsl:value-of select="openDECL:display[@id='projL']/@pixel-size-x"/>;<xsl:value-of select="openDECL:display[@id='projL']/@pixel-size-y"/>
::tile1::TileView1::virtSize=<xsl:value-of select="openDECL:display[@id='projL']/@pixel-size-x"/>;<xsl:value-of select="openDECL:display[@id='projL']/@pixel-size-y"/>
<xsl:choose>
<xsl:when test="openDECL:display[@id='projR']/@stereo = 'left-eye'">
::tile2::TileView1::eye=Left Eye
::tile2::TileView1::projType=Stereo OffAxis</xsl:when>
<xsl:when test="openDECL:display[@id='projR']/@stereo = 'right-eye'">
::tile2::TileView1::eye=Right Eye
::tile2::TileView1::projType=Stereo OffAxis</xsl:when>
</xsl:choose>
::tile2::TileView1::tile=<xsl:value-of select="openDECL:display[@id='projL']/openDECL:virtual/openDECL:upper-left/openDECL:vector/@x"/>;<xsl:value-of select="openDECL:display[@id='projL']/openDECL:virtual/openDECL:upper-left/openDECL:vector/@y"/>;<xsl:value-of select="openDECL:display[@id='projR']/@pixel-size-x"/>;<xsl:value-of select="openDECL:display[@id='projR']/@pixel-size-y"/>
::tile2::TileView1::virtSize=<xsl:value-of select="openDECL:display[@id='projR']/@pixel-size-x"/>;<xsl:value-of select="openDECL:display[@id='projR']/@pixel-size-y"/>
</xsl:template>
</xsl:stylesheet>