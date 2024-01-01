package com.example.homesecuritytesttask.controller.di

import com.example.homesecuritytesttask.data.localData.LocalDataSource
import com.example.homesecuritytesttask.data.remoteData.ApiFactory
import com.example.homesecuritytesttask.data.remoteData.RemoteDataSource
import com.example.homesecuritytesttask.data.repository.RepositoryLocal
import com.example.homesecuritytesttask.data.repository.RepositoryLocalImpl
import com.example.homesecuritytesttask.data.repository.RepositoryRemote
import com.example.homesecuritytesttask.data.repository.RepositoryRemoteImpl
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    factory<RemoteDataSource> { RemoteDataSource(client = get()) }
    factory<LocalDataSource> { LocalDataSource() }
    single<HttpClient> { ApiFactory.provideClient() }

    single<RepositoryRemote> { RepositoryRemoteImpl(remoteDataSource = get()) }
    single<RepositoryLocal> { RepositoryLocalImpl(localDataSource = get()) }
}