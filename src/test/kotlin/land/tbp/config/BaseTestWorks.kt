package land.tbp.config

import land.tbp.jooq.tables.pojos.User
import land.tbp.land.tbp.config.Components.userRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo

class BaseTestWorks : BaseTest {
    @RepeatedTest(10)
    fun `baseTest works`(repetitionInfo: RepetitionInfo) {
        val id = repetitionInfo.currentRepetition.toLong()
        assertThat(userRepository.count()).isZero
        userRepository.insert(User(null, "a@a.a", "TheBestPessimist", "69"))
        assertThat(userRepository.findAll()).containsExactly(User(id, "a@a.a", "TheBestPessimist", "69"))
    }
}
