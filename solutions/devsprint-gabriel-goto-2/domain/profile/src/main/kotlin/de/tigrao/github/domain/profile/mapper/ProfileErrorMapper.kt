package de.tigrao.github.domain.profile.mapper

import de.tigrao.github.domain.profile.model.UserProfileErrorModel
import deb.tigrao.github.infra.api.ResultDomainError
import javax.inject.Inject

internal class ProfileErrorMapper @Inject constructor() {

    fun mapFrom(from: ResultDomainError) = UserProfileErrorModel.GenericError
}
