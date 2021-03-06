package com.example.adfmp1h21_bird

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.adfmp1h21_bird.database.NoteDatabase
import com.example.adfmp1h21_bird.note.MyNote
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
//        val db = NoteDatabase.getInstance(this.applicationContext)
//        Log.d("BIRD_DATABASE", "got instance")
//        Log.d("BIRD_DATABASE", "current notes :" + db.getAllNotes())
//        db.addNote(MyNote(0,"name","uri","geotag", "tags", "date", "comment"))
//        Log.d("BIRD_DATABASE", "note added")
//        Log.d("BIRD_DATABASE", "current notes :" + db.getAllNotes())

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_base,
                R.id.nav_settings,
                R.id.nav_statistics
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val headerUserName:TextView = navView.getHeaderView(0).findViewById(R.id.header_user_name)
        headerUserName.setOnClickListener {
            navController.navigate(R.id.nav_login)
            drawerLayout.closeDrawer(navView)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}