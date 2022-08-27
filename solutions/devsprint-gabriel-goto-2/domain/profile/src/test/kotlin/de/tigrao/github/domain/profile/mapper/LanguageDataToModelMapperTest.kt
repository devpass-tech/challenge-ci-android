package de.tigrao.github.domain.profile.mapper

import de.tigrao.github.domain.profile.model.LanguageModel
import dev.tigrao.github.fragment.LanguageFragment
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LanguageDataToModelMapperTest {

    private val subject = LanguageDataToModelMapper()

    @Test
    fun mapFrom_nonNullValue_returnMapped() {
        val result = subject.mapFrom(
            from = LanguageFragment(
                name = "Kotlin",
                color = "#FFFFFF",
            )
        )

        val expected = LanguageModel(
            name = "Kotlin",
            color = "#FFFFFF",
        )

        assertEquals(expected, result)
    }

    @Test
    fun mapFrom_nullValue_returnNull() {
        val result = subject.mapFrom(
            from = null
        )

        assertNull(result)
    }
}
