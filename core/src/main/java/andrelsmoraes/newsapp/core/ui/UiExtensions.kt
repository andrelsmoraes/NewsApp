package andrelsmoraes.newsapp.core.ui

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

fun TextView.setTextOrHide(text: String?) {
    if (text.isNullOrBlank()) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
        this.text = text
    }
}

fun AppCompatActivity.updateToolbar(title: String, backButtonEnabled: Boolean = false) {
    supportActionBar?.title = title
    supportActionBar?.setDisplayHomeAsUpEnabled(backButtonEnabled)
    supportActionBar?.setDisplayShowHomeEnabled(backButtonEnabled)
}
