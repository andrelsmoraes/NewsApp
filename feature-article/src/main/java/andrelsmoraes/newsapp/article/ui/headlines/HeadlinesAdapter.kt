package andrelsmoraes.newsapp.article.ui.headlines

import andrelsmoraes.newsapp.article.R
import andrelsmoraes.newsapp.article.databinding.HeadlineItemBinding
import andrelsmoraes.newsapp.article.domain.model.Article
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.ocpsoft.prettytime.PrettyTime
import java.util.Date

internal class HeadlinesAdapter :
    PagingDataAdapter<Article, ArticleViewHolder>(ArticleDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.headline_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

internal class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = HeadlineItemBinding.bind(view)

    fun bind(article: Article?) {
        article?.let {
            val imageTransitionName = it.urlToImage.orEmpty()

            binding.apply {
                image.apply {
                    load(it.urlToImage)
                    transitionName = imageTransitionName
                }
                title.text = it.title
                publishedAt.text = PrettyTime(Date()).format(it.publishedAt)

                root.setOnClickListener { view ->
                    val action = HeadlinesFragmentDirections
                        .actionHeadlinesFragmentToArticleDetailsFragment(it)
                    val extras = FragmentNavigatorExtras(
                        binding.image to imageTransitionName)
                    view.findNavController().navigate(action, extras)
                }
            }
        }
    }
}

internal class ArticleDiffUtilCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return areContentsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.author == newItem.author &&
                oldItem.title == newItem.title &&
                oldItem.url == newItem.url &&
                oldItem.publishedAt == newItem.publishedAt
    }
}
