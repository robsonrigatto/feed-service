package br.com.rr.feed.service;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;

import br.com.rr.feed.enumeration.DescriptionType;
import br.com.rr.feed.vo.FeedVO;
import br.com.rr.feed.vo.ItemVO;
import br.com.rr.feed.vo.ItemDescriptionVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XMLParserTest {
	
	@Autowired
	private XMLParser xmlParser;
	
	@Test
	public void globoLinkTest() throws Exception {
		FeedVO feed = xmlParser.parseToJSON("feed.xml");
		
		Assert.assertNotNull(feed);
		Assert.assertFalse(feed.getItems().isEmpty());
		
		Assert.assertNotNull(feed.getItems().get(0).getTitle());
		Assert.assertNotNull(feed.getItems().get(0).getLink());
		Assert.assertFalse(feed.getItems().get(0).getDescriptions().isEmpty());
	}
	
	@Test
	public void onlyTextTest() throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		String xml = 
				"<rss>" + 
						"<item>" + 
							"<title><![CDATA[ Auto Esporte ]]></title>" +
							"<link>http://revistaautoesporte.globo.com/</link>" +
							"<description><![CDATA[" +
							"<p>Se por fora tudo &eacute; diferente (e muito mais agressivo) comparado aos..." +
							"]]></description>" +
						"</item>" + 
				"</rss>";
		InputStream in = IOUtils.toInputStream(xml, "UTF-8");
		
		Document document = dBuilder.parse(in);
		FeedVO feed = this.xmlParser.parseDocument(document);
		
		Assert.assertNotNull(feed);
		Assert.assertEquals(1, (int) feed.getItems().size());
		
		ItemVO item = feed.getItems().get(0);
		Assert.assertEquals("Auto Esporte", item.getTitle());
		Assert.assertEquals("http://revistaautoesporte.globo.com/", item.getLink());
		
		Assert.assertEquals(1, (int) item.getDescriptions().size());
		Assert.assertEquals(DescriptionType.TEXT, item.getDescriptions().get(0).getType());
	}
	
	@Test
	public void onlyImageAndEmptyParagraphTest() throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		String xml = 
				"<rss>" + 
						"<item>" + 
							"<title><![CDATA[ G1 - Portal de Noticias ]]></title>" +
							"<link>http://g1.globo.com/</link>" +
							"<description><![CDATA[" +
							"<div class=\"foto componente_materia midia-largura-620\">\n" + 
								"<img alt=\"Volkswagen Arteon (Foto: Divulgação)\" height=\"413\" id=\"209447\" src=\"http://s2.glbimg.com/RJ9LxS8YaH4bCAlP1f_1Nm8cocY=/620x413/e.glbimg.com/og/ed/f/original/2017/03/06/4_vYYIbul.jpg\" title=\"Volkswagen Arteon (Foto: Divulgação)\" width=\"620\" />" + 
								"<label class=\"foto-legenda\">Volkswagen Arteon (Foto: Divulga&ccedil;&atilde;o)</label>" + 
							"</div>\n" + 
							"<p> &nbsp; </p>" +
							"]]></description>" +
						"</item>" + 
				"</rss>";
		InputStream in = IOUtils.toInputStream(xml, "UTF-8");
		
		Document document = dBuilder.parse(in);
		FeedVO feed = this.xmlParser.parseDocument(document);
		
		Assert.assertNotNull(feed);
		Assert.assertEquals(1, (int) feed.getItems().size());
		
		ItemVO item = feed.getItems().get(0);
		Assert.assertEquals("G1 - Portal de Noticias", item.getTitle());
		Assert.assertEquals("http://g1.globo.com/", item.getLink());
		
		Assert.assertEquals(1, (int) item.getDescriptions().size());
		Assert.assertEquals(DescriptionType.IMAGE, item.getDescriptions().get(0).getType());
	}
	
	@Test
	public void onlyLinksTest() throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		String xml = 
				"<rss>" + 
						"<item>" + 
							"<title><![CDATA[ globoesporte.com ]]></title>" +
							"<link>http://globoesporte.globo.com/</link>" +
							"<description><![CDATA[" +
							"<div class=\"saibamais componente_materia expandido\">" +
						    	"<strong>saiba mais</strong>" +
							    "<ul>" +
							        "<li>" +
							            "<a href=\"http://globoesporte.globo.com/Noticias/noticia/2016/01/os-12-mimos-mais-legais-do-volkswagen-passat.html\">OS 12 MIMOS MAIS LEGAIS DO VOLKSWAGEN PASSAT</a>" + 
							        "</li>" +
							        "<li>" +
							            "<a href=\"http://globoesporte.globo.com/Analises/noticia/2015/11/avaliacao-novo-volkswagen-passat.html\">AVALIA&Ccedil;&Atilde;O: NOVO VOLKSWAGEN PASSAT</a>" + 
							        "</li>" +
							    "</ul>" +
							"</div>" +
							"<p> &nbsp; </p>" +
							"]]></description>" +
						"</item>" + 
				"</rss>";
		InputStream in = IOUtils.toInputStream(xml, "UTF-8");
		
		Document document = dBuilder.parse(in);
		FeedVO feed = this.xmlParser.parseDocument(document);
		
		Assert.assertNotNull(feed);
		Assert.assertEquals(1, (int) feed.getItems().size());
		
		ItemVO item = feed.getItems().get(0);
		Assert.assertEquals("globoesporte.com", item.getTitle());
		Assert.assertEquals("http://globoesporte.globo.com/", item.getLink());
		
		Assert.assertEquals(1, (int) item.getDescriptions().size());
		
		ItemDescriptionVO itemDescription = item.getDescriptions().get(0);
		Assert.assertEquals(DescriptionType.LINKS, itemDescription.getType());
		
		List<String> linksList = (List<String>)itemDescription.getContent();
		Assert.assertEquals(2, (int) linksList.size());
	}
	
	@Test
	public void onlyLinksWithEmptyListTest() throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		String xml = 
				"<rss>" + 
						"<item>" + 
							"<title><![CDATA[ globoesporte.com ]]></title>" +
							"<link>http://globoesporte.globo.com/</link>" +
							"<description><![CDATA[" +
							"<div class=\"saibamais componente_materia expandido\">" +
						    	"<strong>saiba mais</strong>" +
							    "<ul>" +
							    "</ul>" +
							"</div>" +
							"<p> &nbsp; </p>" +
							"]]></description>" +
						"</item>" + 
				"</rss>";
		InputStream in = IOUtils.toInputStream(xml, "UTF-8");
		
		Document document = dBuilder.parse(in);
		FeedVO feed = this.xmlParser.parseDocument(document);
		
		Assert.assertNotNull(feed);
		Assert.assertEquals(1, (int) feed.getItems().size());
		
		ItemVO item = feed.getItems().get(0);
		Assert.assertEquals("globoesporte.com", item.getTitle());
		Assert.assertEquals("http://globoesporte.globo.com/", item.getLink());
		
		Assert.assertEquals(0, (int) item.getDescriptions().size());
	}

}
