package de.tigrao.github.domain.profile

import de.tigrao.github.domain.profile.data.GetProfileDatasource
import de.tigrao.github.domain.profile.mapper.ProfileErrorMapper
import de.tigrao.github.domain.profile.mapper.ProfileSuccessMapper
import dev.tigrao.github.UserProfileQuery
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetProfileDefaultUseCaseTest {

    private val datasource = mockk<GetProfileDatasource>()
    private val errorMapper = mockk<ProfileErrorMapper>(relaxed = true)
    private val successMapper = mockk<ProfileSuccessMapper>(relaxed = true)

    private val subject = GetProfileDefaultUseCase(
        datasource,
        successMapper,
        errorMapper,
    )

    @Test
    fun mapFrom_apiWithSuccess_callApiSuccessMapper() = runBlocking {
        val data = mockk<UserProfileQuery.Data>()
        prepare(data = data)

        subject(force = true, userName = "GabriellCosta")

        verify {
            successMapper.mapFrom(data)
        }
    }

    @Test
    fun mapFrom_apiWithError_callApiErrorMapper() = runBlocking {
        mockk<UserProfileQuery.Data>()
        prepare(data = null)

        subject(force = true, userName = "GabriellCosta")

        verify {
            errorMapper.mapFrom(any())
        }
    }

    fun prepare(data: UserProfileQuery.Data?) {
        if (data != null) {
            coEvery {
                datasource.fetchProfileData(any())
            } returns data
        } else {
            coEvery {
                datasource.fetchProfileData(any())
            } throws Exception()
        }
    }
}
