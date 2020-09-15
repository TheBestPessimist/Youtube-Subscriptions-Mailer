package land.tbp.xmltest

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.Test
import kotlin.test.assertEquals


class XMLSerializeTest {

    @Test
    fun deserialize() {

        val s = """<?xml version="1.0" encoding="UTF-8"?>
<feed xmlns="http://www.w3.org/2005/Atom">
<status feed="http://push-pub.appspot.com/feed" xmlns="http://superfeedr.com/xmpp-pubsub-ext">
    <http code="200">Fetched (ping) 200 86400 and parsed 1/20 entries</http>
    <next_fetch>1970-01-19T12:26:38.839Z</next_fetch>
    <entries_count_since_last_maintenance>9</entries_count_since_last_maintenance>
    <velocity>1.6</velocity>
    <popularity>1.22431540551227</popularity>
    <title>Publisher example</title>
    <period>86400</period>
    <last_fetch>1970-01-19T12:24:55.431Z</last_fetch>
    <last_parse>1970-01-19T12:24:55.431Z</last_parse>
    <last_maintenance_at>1970-01-19T12:24:53.150Z</last_maintenance_at>
</status>
<link rel="hub" href="http://pubsubhubbub.superfeedr.com"/>
<link rel="self" href="http://push-pub.appspot.com/feed"/>
<link title="Publisher example" rel="self" href="http://push-pub.appspot.com/feed" type="application/atom+xml"/>
<link title="Publisher example" rel="alternate" href="http://push-pub.appspot.com/" type="text/html"/>
<link title="" rel="hub" href="https://pubsubhubbub.superfeedr.com" type="text/html"/>
<title>Publisher example</title>
<updated>2020-09-12T07:23:51.000Z</updated>
<id>http://push-pub.appspot.com/feed</id>
<entry xmlns="http://www.w3.org/2005/Atom" xmlns:geo="http://www.georss.org/georss"
       xmlns:as="http://activitystrea.ms/spec/1.0/" xmlns:sf="http://superfeedr.com/xmpp-pubsub-ext">
    <id>http://push-pub.appspot.com/feed/5767968478724096</id>
    <published>2020-09-12T07:23:51.000Z</published>
    <updated>2020-09-12T07:23:51.000Z</updated>
    <title>10</title>
    <link title="10" rel="alternate" href="http://push-pub.appspot.com/entry/5767968478724096" type="text/html"/>
</entry>
</feed>
        """.trimIndent()


        val xmlMapper = XmlMapper()
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.registerModule(KotlinModule())

        val feed: PubFeed = xmlMapper.readValue(s, PubFeed::class.java)
        assertEquals("Publisher example", feed.title)
        println(feed)
    }
}

data class PubFeed(
        val title: String
)
