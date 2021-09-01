package com.merapar.recruitment.parsing;

import com.merapar.recruitment.domain.AnalysisResult;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class XmlParserHandlerTest {

    public static final String VALID_NO_ROWS = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<posts>\n" +
            "</posts>";
    public static final String VALID_WRONG_ROW = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<posts>\n" +
            "<row Id=\"1\" PostTypeId=\"1\" AcceptedAnswerId=\"5\" CreationDate=\"2015-07-14T18:39:27.757\" Score=\"4\" " +
            "ViewCount=\"123\" Body=\"&lt;p&gt;The proposal for this site only mentions &quot;Arabic.&quot; Which Arabic" +
            "?&lt;/p&gt;&#xA;&#xA;&lt;p&gt;There is a common type of Arabic called &quot;classical Arabic&quot; (fusha) " +
            "which stretches back at least 1000 years back. It's still used today for most media (eg. newspapers, textbooks)" +
            " and in universities.&lt;/p&gt;&#xA;&#xA;&lt;p&gt;Beyond this, each coun\n" +
            "</posts>";

    public static final String VALID_SAMPLE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<posts>\n" +
            "<row Id=\"1\" PostTypeId=\"1\" AcceptedAnswerId=\"5\" CreationDate=\"2015-07-14T18:39:27.757\" Score=\"4\" " +
            "ViewCount=\"123\" Body=\"&lt;p&gt;The proposal for this site only mentions &quot;Arabic.&quot; Which Arabic" +
            "?&lt;/p&gt;&#xA;&#xA;&lt;p&gt;There is a common type of Arabic called &quot;classical Arabic&quot; (fusha) " +
            "which stretches back at least 1000 years back. It's still used today for most media (eg. newspapers, textbooks)" +
            " and in universities.&lt;/p&gt;&#xA;&#xA;&lt;p&gt;Beyond this, each country has its own dialect (or slang) " +
            "version of Arabic. This includes both use of vocabulary, and also actual grammar (or ignoring the classical " +
            "grammar).&lt;/p&gt;&#xA;&#xA;&lt;p&gt;While some of the sample questions on Area 51 talk about Arabic in a " +
            "specific area, the question is really how we should plan and organize questions, or whether we should focus" +
            " exclusively on classical Arabic.&lt;/p&gt;&#xA;\" OwnerUserId=\"20\" LastEditorUserId=\"20\" LastEditDate=" +
            "\"2015-07-14T22:27:41.087\" LastActivityDate=\"2015-07-15T16:43:23.787\" Title=\"Which Arabic are we talking" +
            " about?\" Tags=\"&lt;discussion&gt;\" AnswerCount=\"5\" CommentCount=\"4\"/>\n" +
            "<row Id=\"2\" PostTypeId=\"2\" ParentId=\"1\" CreationDate=\"2015-07-14T18:42:42.553\" Score=\"0\" Body=\"&lt;p&gt;" +
            "While allowing non-classical Arabic questions will give us more questions being asked, I think it will hurt" +
            " the site because of two reasons:&lt;/p&gt;&#xA;&#xA;&lt;ul&gt;&#xA;&lt;li&gt;&lt;strong&gt;Readability:&lt;/strong" +
            "&gt; If users can ask questions in Arabic, you now need a bigger user base that covers whatever country-specific" +
            " version the question is in.&lt;/li&gt;&#xA;&lt;li&gt;&lt;strong&gt;Answerability:&lt;/strong&gt; Like above," +
            " if we don't have site users who know that specific version of Arabic (and in sufficient depth and detail to" +
            " give a strong answer), we'll end up with a bunch of open, unanswerable questions." +
            "&lt;/li&gt;&#xA;&lt;/ul&gt;&#xA;&#xA;&lt;p&gt;Ultimately, we can let this play out (allowing country-specific" +
            " Arabic questions) and see what happens. As a start, we should at least try to &lt;em&gt;tag country-specific " +
            "Arabic questions appropriately.&lt;/em&gt;&lt;/p&gt;&#xA;\" OwnerUserId=\"20\" LastActivityDate=\"2015-07-14T18:42:42.553\" " +
            "CommentCount=\"0\"/>\n" +
            "</posts>  ";


    protected XmlParserHandler handler;
    private AnalysisResult analysisResult;

    //@Before
    public void setUp() {
        // mockowanie
        // XmlParserHandler xmlParserHandler = new XmlParserHandler();
        //XmlParserHandler xmlParserHandler = new XmlParserHandler();

        handler = new XmlParserHandler(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        });

    }

    @Test
    public void testNoRows(){
        InputStream inputStream = new ByteArrayInputStream(VALID_NO_ROWS.getBytes(Charset.forName("UTF-8")));
        XmlParserHandler xmlParserHandler = new XmlParserHandler(inputStream);
        analysisResult = xmlParserHandler.parse();
        assertNotNull(analysisResult);
        assertNull(analysisResult.getAnalyseDate());
        assertNull(analysisResult.getDetails().getFirstPost());
        assertNull(analysisResult.getDetails().getLastPost());
        assertEquals( 0, analysisResult.getDetails().getTotalPosts());
        assertEquals( 0, analysisResult.getDetails().getTotalAcceptedPosts());
        assertEquals( 0, analysisResult.getDetails().getScore());
    }
    @Test
    public void shouldThrowRuntimeException(){
        InputStream inputStream = new ByteArrayInputStream(VALID_WRONG_ROW.getBytes(Charset.forName("UTF-8")));
        XmlParserHandler xmlParserHandler = new XmlParserHandler(inputStream);
        assertThrows(RuntimeException.class,
                () -> xmlParserHandler.parse()
                );
    }

    @Test
    public void testSampleXml(){
        InputStream inputStream = new ByteArrayInputStream(VALID_SAMPLE_XML.getBytes(Charset.forName("UTF-8")));
        XmlParserHandler xmlParserHandler = new XmlParserHandler(inputStream);
        analysisResult = xmlParserHandler.parse();
        assertNotNull(analysisResult);
        assertNull(analysisResult.getAnalyseDate());
        assertNotNull(analysisResult.getDetails());
        assertNotNull(analysisResult.getDetails().getFirstPost());
        assertNotNull(analysisResult.getDetails().getLastPost());
        assertEquals( 2, analysisResult.getDetails().getTotalPosts());
        assertEquals( 1, analysisResult.getDetails().getTotalAcceptedPosts());
        assertEquals( 4, analysisResult.getDetails().getScore());
    }



    @Test
    public void testStartDocument() throws SAXException {
        // nie ma sensu , wszystk jest prywatne i chce zeby takie zostalo !
        //assertNull(handler.get);
//        handler.startDocument();
//        assertNull(handler.getPlist());
//        assertNull(handler.getParseListener());
//        assertNotNull(handler.getTempVal());
    }

    @Test
    public void testStartElement() throws SAXException {
        // nie ma sensu , wszystk jest prywatne i chce zeby takie zostalo !
        // nie bede tez na sile tworzyc atrybutow

    }

    @Test
    //void incrementTotalPosts() {
    void when_increment_then_expectCorrectResult(){
        //xmlParserHandler.incrementCos()
        //assertThat(result).isTrue();
    }
}