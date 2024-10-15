package com.rickaban.android.getrandomusers.app.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.rickaban.android.getrandomusers.NavGraphs
import com.rickaban.android.getrandomusers.app.ui.theme.ThemeWrapper
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AppActivity : FragmentActivity() {
    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navEngine = rememberAnimatedNavHostEngine()
            val navController = navEngine.rememberNavController()
            val viewModel: AppViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val lifecycleOwner = LocalLifecycleOwner.current

            DisposableEffect(lifecycleOwner){
                val observer = LifecycleEventObserver { _, event ->
                    when (event) {
                        Lifecycle.Event.ON_RESUME -> {
                            viewModel.onResume()
                        }
                        Lifecycle.Event.ON_PAUSE -> {
                            viewModel.onPause()
                        }
                        else -> {
                            Timber.e("TESTX app activity ${event.name}")
                        }
                    }
                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            }

            ThemeWrapper {
                Surface(Modifier.fillMaxSize()) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        engine = navEngine
                    )
                }
            }
        }
    }
}        