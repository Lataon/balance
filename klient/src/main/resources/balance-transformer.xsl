<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="users">
        <users>
            <xsl:apply-templates/>
        </users>
    </xsl:template>

    <xsl:template match="user">

        <xsl:element name="user">
            <xsl:element name="id">
                <xsl:value-of select="id"/>
            </xsl:element>

            <xsl:element name="balance">
                <xsl:value-of select="balance"/>
            </xsl:element>
        </xsl:element>

    </xsl:template>

</xsl:stylesheet>