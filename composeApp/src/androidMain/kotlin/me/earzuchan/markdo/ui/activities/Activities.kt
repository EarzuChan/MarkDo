package me.earzuchan.markdo.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import me.earzuchan.markdo.MarkDoApp
import me.earzuchan.markdo.di.appModule
import me.earzuchan.markdo.duties.AppDuty
import org.koin.compose.KoinApplication

class InitAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()

        setContent {
            KoinApplication({ modules(appModule) }) {
                val appDuty = AppDuty(defaultComponentContext())
                MarkDoApp(appDuty)
            }
        }
    }
}