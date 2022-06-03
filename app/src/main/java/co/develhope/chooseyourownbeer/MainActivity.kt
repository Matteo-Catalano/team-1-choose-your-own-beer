package co.develhope.chooseyourownbeer

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.develhope.chooseyourownbeer.databinding.ActivityMainBinding
import co.develhope.chooseyourownbeer.model.Beer
import co.develhope.chooseyourownbeer.model.MainViewModel
import co.develhope.chooseyourownbeer.network.BeersResult
import co.develhope.chooseyourownbeer.network.PunkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Call method for beer repo
        retrievePunkRepos()

        //Hide actionBar
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    fun retrievePunkRepos(){
        lifecycleScope.launch{
            try{
                //@TODO showRepos(repos)
            } catch (e: Exception){
                Log.e("MainActivity", "Error retrieving repos: $e")
                Snackbar.make(findViewById(R.id.container), "Error retrieving repos",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry"){ retrievePunkRepos() }.show()
            }
        }
    }
    //@TODO create function showRepos(repos)
    fun showRepos(repoResults: BeersResult){
        Log.d("MainActivity", "Repos received!")
        val list = findViewById<RecyclerView>(R.id.repo_list_beer)
        list.visibility = View.VISIBLE
        //val adapter = BeerAdapter(repoResults)
        //list.adapter = adapter
    }

}