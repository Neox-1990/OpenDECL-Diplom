<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">

<set name="tile1-window">
<xsl:attribute name="value">x<xsl:value-of select="//display[@id='projL']/virtual/upper-left/vector/@x"/>y<xsl:value-of select="//display[@id='projL']/virtual/upper-left/vector/@y"/>w<xsl:value-of select="//display[@id='projL']/virtual/lower-right/vector/@x"/>h<xsl:value-of select="//display[@id='projL']/virtual/lower-right/vector/@y"/>nd</xsl:attribute>
</set>
<set name="tile2-window">
<xsl:attribute name="value">x<xsl:value-of select="//display[@id='projR']/virtual/upper-left/vector/@x"/>y<xsl:value-of select="//display[@id='projL']/virtual/upper-left/vector/@y"/>w<xsl:value-of select="//display[@id='projL']/virtual/lower-right/vector/@x"/>h<xsl:value-of select="//display[@id='projL']/virtual/lower-right/vector/@y"/>nd</xsl:attribute>
</set>
<xsl:choose>
<xsl:when test="//display[@id='projL']/@stereo = 'left-eye'">
::tile1::TileView1::eye=Left Eye
::tile1::TileView1::projType=Stereo OffAxis</xsl:when>
<xsl:when test="//display[@id='projL']/@stereo = 'right-eye'">
::tile1::TileView1::eye=Right Eye
::tile1::TileView1::projType=Stereo OffAxis</xsl:when>
</xsl:choose>
::tile1::TileView1::tile=<xsl:value-of select="//display[@id='projL']/virtual/upper-left/vector/@x"/>;<xsl:value-of select="//display[@id='projL']/virtual/upper-left/vector/@y"/>;<xsl:value-of select="//display[@id='projL']/@pixel-size-x"/>;<xsl:value-of select="//display[@id='projL']/@pixel-size-y"/>
::tile1::TileView1::virtSize=<xsl:value-of select="//display[@id='projL']/@pixel-size-x"/>;<xsl:value-of select="//display[@id='projL']/@pixel-size-y"/>
<xsl:choose>
<xsl:when test="//display[@id='projR']/@stereo = 'left-eye'">
::tile2::TileView1::eye=Left Eye
::tile2::TileView1::projType=Stereo OffAxis</xsl:when>
<xsl:when test="//display[@id='projR']/@stereo = 'right-eye'">
::tile2::TileView1::eye=Right Eye
::tile2::TileView1::projType=Stereo OffAxis</xsl:when>
</xsl:choose>
::tile2::TileView1::tile=<xsl:value-of select="//display[@id='projR']/virtual/upper-left/vector/@x"/>;<xsl:value-of select="//display[@id='projR']/virtual/upper-left/vector/@y"/>;<xsl:value-of select="//display[@id='projR']/@pixel-size-x"/>;<xsl:value-of select="//display[@id='projR']/@pixel-size-y"/>
::tile2::TileView1::virtSize=<xsl:value-of select="//display[@id='projR']/@pixel-size-x"/>;<xsl:value-of select="//display[@id='projR']/@pixel-size-y"/>
</xsl:template>
</xsl:transform>