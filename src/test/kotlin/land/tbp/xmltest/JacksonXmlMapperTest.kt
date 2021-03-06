package land.tbp.xmltest

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.assertj.core.api.Assertions
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime


class JacksonXmlMapperTest {
    @Test
    fun `deserialize xml`() {

        @Language("XML")
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
        xmlMapper.registerModule(JavaTimeModule())


        val expected = PubFeed(
            title = "Publisher example",
            entry = FeedEntry(
                id = "http://push-pub.appspot.com/feed/5767968478724096",
                published = ZonedDateTime.of(2020, Month.SEPTEMBER.value, 12, 7, 23, 51, 0, ZoneId.of("UTC")),
                updated = ZonedDateTime.of(2020, Month.SEPTEMBER.value, 12, 7, 23, 51, 0, ZoneId.of("UTC")),
                title = "10",
                link = Link(
                    title = "10",
                    href = "http://push-pub.appspot.com/entry/5767968478724096",
                    type = "text/html",
                    rel = "alternate"
                )
            ),
            aaaalinkszzzz = listOf(
                Link(rel = "hub", href = "http://pubsubhubbub.superfeedr.com"),
                Link(rel = "self", href = "http://push-pub.appspot.com/feed"),
                Link(title = "Publisher example", rel = "self", href = "http://push-pub.appspot.com/feed", type = "application/atom+xml"),
                Link(title = "Publisher example", rel = "alternate", href = "http://push-pub.appspot.com/", type = "text/html"),
                Link(title = "", rel = "hub", href = "https://pubsubhubbub.superfeedr.com", type = "text/html"),
            )
        )

        val actualDeserializedFeed: PubFeed = xmlMapper.readValue(s, PubFeed::class.java)
        Assertions.assertThat(actualDeserializedFeed).isEqualTo(expected)
    }
}


private data class PubFeed(
    val title: String,
    val entry: FeedEntry,
    /*
    these annotations say:
    - `@JacksonXmlElementWrapper` there are multiple elements in the <feed> with the same name,
    - `useWrapping = false` but they are not grouped together in an `<elements>` (plural) element. They just all hang around in <feed>
    - `@JacksonXmlProperty` the field `links` is a property (ie. it appears in the XML)
    - `localName = "link"` the name of each element inside `<feed>` is called `link` (needed because my field is not named `link`)
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "link")
    val aaaalinkszzzz: List<Link>
)


private data class FeedEntry(
    val id: String,
    val published: ZonedDateTime,
    val updated: ZonedDateTime,
    val title: String,
    val link: Link,
)

private data class Link(
    @JacksonXmlProperty(isAttribute = true) val title: String? = null,
    @JacksonXmlProperty(isAttribute = true) val href: String? = null,
    @JacksonXmlProperty(isAttribute = true) val rel: String? = null,
    @JacksonXmlProperty(isAttribute = true) val type: String? = null, // todo should probably be some other type, which directly maps to text/html. i'm sure there's a Ktor class/enum for this, but i'm too lazy to search for it now.
)
