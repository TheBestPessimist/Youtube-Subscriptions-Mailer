package land.tbp.land.tbp.util

import org.jooq.Field
import org.jooq.InsertSetStep
import org.jooq.InsertValuesStepN
import org.jooq.Record

fun <T : Any> T.dbg() = this.also { println(">${it::class}<\n$it") }


fun <R : Record> InsertSetStep<R>.columns(fields: Array<Field<*>>): InsertValuesStepN<R> = this.columns(*fields)
