package org.openmrs.module.reporting.xml.converter;

import com.thoughtworks.xstream.XStream;
import org.junit.Assert;
import org.junit.Test;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.util.ReportUtil;
import org.openmrs.module.reporting.xml.XmlReportUtil;

import static org.hamcrest.CoreMatchers.is;

public class DefinitionConverterTest {

    protected String getXml() {
        return ReportUtil.readStringFromResource("org/openmrs/module/reporting/xml/converter/definition.xml");
    }

    @Test
    public void testMarshall() throws Exception {
        XStream xstream = XmlReportUtil.getXStream();
        ReportDefinition rd = (ReportDefinition)xstream.fromXML(getXml());
        Assert.assertThat(rd.getName(), is("Test Report"));
        Assert.assertThat(rd.getDescription().trim(), is("Here is a longer description of the report"));
        Assert.assertThat(rd.getParameters().size(), is(1));
    }
}
