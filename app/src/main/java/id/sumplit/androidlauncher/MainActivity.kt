package id.sumplit.androidlauncher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.sumplit.androidlauncher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainAdapter by lazy { MainAdapter() }
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        setupListApp()
    }

    private fun setupListApp() {
        val packageManager = this.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val apps = packageManager.queryIntentActivities(intent, 0)
        val listApp = ArrayList<ListApps>()
        for (app in apps) {
            listApp.add(
                ListApps(
                    nameApp = app.loadLabel(packageManager).toString(),
                    packageApp = app.activityInfo.packageName,
                    iconApp = app.activityInfo.loadIcon(packageManager)
                )
            )
        }

        mainAdapter.replace(listApp)
        binding?.rvApps?.layoutManager = LinearLayoutManager(this)
        binding?.rvApps?.adapter = mainAdapter
    }
}