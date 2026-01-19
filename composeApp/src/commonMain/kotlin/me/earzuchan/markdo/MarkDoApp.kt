package me.earzuchan.markdo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import me.earzuchan.markdo.duties.AppDuty
import me.earzuchan.markdo.duties.LoginDuty
import me.earzuchan.markdo.duties.MainDuty
import me.earzuchan.markdo.duties.SplashDuty
import me.earzuchan.markdo.ui.screens.LoginScreen
import me.earzuchan.markdo.ui.screens.MainScreen
import me.earzuchan.markdo.ui.screens.SplashScreen
import me.earzuchan.markdo.ui.themes.MarkDoTheme
import me.earzuchan.markdo.utils.PlatformFunctions

@Composable
fun MarkDoApp(appDuty: AppDuty)  {
    val TAG = "MarkDoApp"

    DisposableEffect(Unit) {
        PlatformFunctions.setupApp()

        onDispose { PlatformFunctions.stopApp() }
    }

    MarkDoTheme {
        Surface {
            Children(appDuty.navStack, Modifier.fillMaxSize()) {
                when (val ins = it.instance) {
                    is MainDuty -> MainScreen(ins)

                    is LoginDuty -> LoginScreen(ins)

                    is SplashDuty -> SplashScreen()
                }
            }
        }
    }
}