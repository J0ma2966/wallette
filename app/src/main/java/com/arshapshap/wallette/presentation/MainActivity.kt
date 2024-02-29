package com.arshapshap.wallette.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.arshapshap.wallette.core.common.di.findComponentDependencies
import com.arshapshap.wallette.R
import com.arshapshap.wallette.core.common.presentation.floatingButtonInterfaces.FloatingButtonListenersManager
import com.arshapshap.wallette.core.common.presentation.floatingButtonInterfaces.OnFloatingButtonClickListener
import com.arshapshap.wallette.databinding.ActivityMainBinding
import com.arshapshap.wallette.di.main.MainComponent
import com.arshapshap.wallette.navigation.Navigator
import com.arshapshap.wallette.presentation.extensions.applyStyle
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnFloatingButtonClickListener,
    FloatingButtonListenersManager {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigator: Navigator

    private lateinit var onFloatingButtonClickListener: OnFloatingButtonClickListener

    private fun inject() {
        MainComponent
            .init(this, findComponentDependencies())
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        inject()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val controller = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment).navController
        hideNavBars(controller)

        binding.bottomNavigationView.setupWithNavController(controller)
        navigator.attachNavController(controller, this)

        setDefaultOnFloatingButtonClickListener()

        binding.addFloatingButton.setOnClickListener {
            onFloatingButtonClickListener.onFloatingButtonClick()
        }
    }

    override fun subscribeOnFloatingButtonClick(listener: OnFloatingButtonClickListener) {
        onFloatingButtonClickListener = listener
        binding.addFloatingButton.applyStyle(R.style.App_Custom_FloatingActionButton_Outlined)
        binding.addFloatingButton.setImageResource(com.arshapshap.wallette.core.common.R.drawable.ic_done)
    }

    override fun setDefaultOnFloatingButtonClickListener() {
        onFloatingButtonClickListener = this
        binding.addFloatingButton.applyStyle(R.style.App_Custom_FloatingActionButton)
        binding.addFloatingButton.setImageResource(com.arshapshap.wallette.feature.settings.R.drawable.ic_plus)
    }

    override fun onFloatingButtonClick() {
        navigator.openSingleTransaction(null)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            //R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun hideNavBars(controller: NavController) {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> setNavBarsVisibility(View.GONE)
                R.id.registerFragment -> setNavBarsVisibility(View.GONE)
                else -> setNavBarsVisibility(View.VISIBLE)
            }
        }
    }

    private fun setNavBarsVisibility(visibility: Int) {
        binding.appBarLayout.visibility = visibility
        binding.bottomAppBar.visibility = visibility
        binding.bottomNavigationView.visibility = visibility
        binding.addFloatingButton.visibility = visibility
    }
}