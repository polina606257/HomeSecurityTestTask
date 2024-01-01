package com.example.homesecuritytesttask.controller.di

import com.example.homesecuritytesttask.controller.cameras.CameraScreenViewModel
import com.example.homesecuritytesttask.controller.doors.DoorScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        CameraScreenViewModel(
           repositoryRemote = get(),
           repositoryLocal = get()
        )
    }
    viewModel {
        DoorScreenViewModel(
            repositoryRemote = get(),
            repositoryLocal = get()
        )
    }
}