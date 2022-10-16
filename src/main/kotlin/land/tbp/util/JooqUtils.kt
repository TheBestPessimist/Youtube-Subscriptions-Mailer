package land.tbp.land.tbp.util

fun <T : Any> T.dbg() = this.also { println(">${it::class}<\n$it") }
