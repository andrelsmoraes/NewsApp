package andrelsmoraes.newsapp

import andrelsmoraes.newsapp.core.ui.updateToolbar
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        findNavController(R.id.fragmentContainer)
            .addOnDestinationChangedListener { _, destination, _ ->
                updateToolbar(
                    title = destination.label.let {
                        if (it.isNullOrBlank()) getString(R.string.app_name) else it.toString()
                    },
                    backButtonEnabled = destination.id == R.id.articleDetailsFragment
                )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.fragmentContainer).navigateUp()
        return super.onSupportNavigateUp()
    }
}
