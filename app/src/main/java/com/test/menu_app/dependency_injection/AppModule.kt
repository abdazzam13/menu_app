package com.test.menu_app.dependency_injection

import com.test.menu_app.model.api.ApiConfig
import com.test.menu_app.model.repository.ApiRepository
import com.test.menu_app.viewmodel.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        ApiConfig().getApiService()
    }
    single {
        ApiRepository(get())
    }
    viewModel{
        MenuViewModel(get())
    }
}