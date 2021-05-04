package andrelsmoraes.newsapp.article.ui.details

import andrelsmoraes.newsapp.article.databinding.FragmentArticleDetailsBinding
import andrelsmoraes.newsapp.core.ui.setTextOrHide
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.load
import org.ocpsoft.prettytime.PrettyTime
import java.util.Date

class ArticleDetailsFragment : Fragment() {
    private lateinit var binding: FragmentArticleDetailsBinding
    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val article = args.article

            image.apply {
                load(article.urlToImage)
                transitionName = article.urlToImage
            }
            source.setTextOrHide(article.source?.title)
            author.setTextOrHide(article.author)
            publishedAt.setTextOrHide(PrettyTime(Date()).format(article.publishedAt))
            title.setTextOrHide(article.title)
            description.setTextOrHide(article.description)
            newsLink.apply {
                setTextOrHide(article.url)
                setOnClickListener { openNews(article.url) }
            }
        }
    }

    private fun openNews(url: String?) {
        Uri.parse(url)?.let { page ->
            requireActivity().startActivity(Intent(Intent.ACTION_VIEW, page))
        }
    }
}
