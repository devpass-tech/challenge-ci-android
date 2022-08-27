package de.tigrao.github.domain.profile

import de.tigrao.github.domain.profile.data.GetProfileDatasource
import de.tigrao.github.domain.profile.mapper.ProfileErrorMapper
import de.tigrao.github.domain.profile.mapper.ProfileSuccessMapper
import de.tigrao.github.domain.profile.model.UserProfileErrorModel
import de.tigrao.github.domain.profile.model.UserProfileModel
import deb.tigrao.github.infra.api.ResultDomain
import deb.tigrao.github.infra.api.callApi
import javax.inject.Inject

interface GetProfileUseCase {
    suspend operator fun invoke(
        userName: String,
        force: Boolean
    ): ResultDomain<UserProfileModel, UserProfileErrorModel>
}

internal class GetProfileDefaultUseCase @Inject constructor(
    private val datasource: GetProfileDatasource,
    private val successMapper: ProfileSuccessMapper,
    private val errorMapper: ProfileErrorMapper,
) : GetProfileUseCase {

    override suspend fun invoke(
        userName: String,
        force: Boolean
    ): ResultDomain<UserProfileModel, UserProfileErrorModel> {
        return callApi {
            datasource.fetchProfileData(
                force = force,
            )
        }.transformMap(
            successMapper::mapFrom,
            errorMapper::mapFrom,
        )
    }
}
