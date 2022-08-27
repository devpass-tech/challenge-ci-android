package me.tigrao.github.main.di

import dagger.Binds
import dagger.Module
import me.tigrao.github.main.domain.FetchProfileDataUseCase
import me.tigrao.github.main.domain.FetchProfileDataUseCaseDefault
import me.tigrao.github.main.presenter.ProfileContract
import me.tigrao.github.main.presenter.ProfilePresenter

@Module
internal interface ProfileFeatureInternalModule {
    @Binds
    fun bindPresenter(bind: ProfilePresenter): ProfileContract.Presenter

    @Binds
    fun binFetchProfileData(bind: FetchProfileDataUseCaseDefault): FetchProfileDataUseCase
}
