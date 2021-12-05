package com.example.buah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.buah.recyclerView.MainUser
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navMenuView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var btnDrawer: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navMenuView = findViewById(R.id.nav_menu_view)
        navMenuView.setNavigationItemSelectedListener (this)
        drawerLayout = findViewById(R.id.drawer_layout)

        toolbar = findViewById(R.id.toolbar)

        btnDrawer = toolbar.findViewById(R.id.btn_drawer)
        btnDrawer.setOnClickListener{
            drawerLayout.openDrawer(Gravity.LEFT)
        }
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId){
            R.id.menu_home-> {
                Toast.makeText(this,"Daftar Menu", Toast.LENGTH_SHORT).show()
            }
            R.id.aboutme->{
                Toast.makeText(this,"About Me", Toast.LENGTH_SHORT).show() // ini buat toast tulisan setelah di klik
                val pindah = Intent(this, aboutMe::class.java) // ini buat pindah aktivity disini make fungsi intent
                startActivity(pindah)
            }
            R.id.Githubuserdicoding->{
                Toast.makeText(this,"Github User Dicoding", Toast.LENGTH_SHORT).show()
                val pindah2 = Intent(this, MainUser::class.java)
                startActivity(pindah2)
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
  }
}