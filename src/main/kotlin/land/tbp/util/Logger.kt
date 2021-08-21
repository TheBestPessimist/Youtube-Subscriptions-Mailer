package land.tbp.land.tbp.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC

inline fun <reified R : Any> R.logger(): Logger = LoggerFactory.getLogger(this::class.java.name.substringBefore("\$Companion"))

fun removeNewLines(s: String): String = s.replace("\r", "").replace("\n", "")

object MDCKeys {
    const val someKey = "someKey"
}

fun MDC_LOG(
    key: String,
    value: String?
) {
    MDC.put(key, """$key=$value""")
}
