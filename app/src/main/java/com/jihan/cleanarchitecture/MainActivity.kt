package com.jihan.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jihan.cleanarchitecture.domain.model.ImageModel
import com.jihan.cleanarchitecture.presentation.SearchImageScreen
import com.jihan.cleanarchitecture.presentation.SetWallpaper
import com.jihan.cleanarchitecture.ui.theme.CleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureTheme {


                Navigator(Navigation.ImageSearchScreen())
            }


        }

    }
}


object Navigation {
    class ImageSearchScreen : Screen {
        @Composable
        override fun Content() {

            val navigator = LocalNavigator.currentOrThrow

            SearchImageScreen {
                navigator push SetWallpaperScreen(it)
            }
        }
    }


    data class SetWallpaperScreen(private val imageModel: ImageModel) : Screen {
        @Composable
        override fun Content() {
            SetWallpaper(imageModel)
        }
    }

}

