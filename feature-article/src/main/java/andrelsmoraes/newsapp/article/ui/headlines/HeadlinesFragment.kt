package andrelsmoraes.newsapp.article.ui.headlines

import andrelsmoraes.newsapp.core.ui.UiStateEvent
import andrelsmoraes.newsapp.article.R
import andrelsmoraes.newsapp.article.databinding.FragmentHeadlinesBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class HeadlinesFragment : Fragment() {
    private val viewModel: HeadlinesViewModel by viewModel()
    private lateinit var binding: FragmentHeadlinesBinding

    private var snackbar: Snackbar? = null

    private val listAdapter = HeadlinesAdapter()
    private val loadStates: (CombinedLoadStates) -> Unit = {
        when {
            it.prepend.endOfPaginationReached -> handleEmptyState(listAdapter.itemCount)
            it.refresh is LoadState.Error -> handleError((R.string.error_fetching_headlines))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadlinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()

        binding.apply {
            root.setColorSchemeResources(R.color.primaryLight, R.color.secondaryLight)
            root.setOnRefreshListener {
                viewModel.getHeadlines()
            }

            recyclerHeadlines.adapter = listAdapter
            recyclerHeadlines.addItemDecoration(
                DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        viewModel.uiState.observe(this.requireActivity()) { state ->
            handleUiState(state)
        }

        (view.parent as? ViewGroup)?.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    private fun handleUiState(state: UiStateEvent) {
        snackbar?.dismiss()
        binding.root.isRefreshing = state is UiStateEvent.Loading

        when (state) {
            is UiStateEvent.Error -> {
                handleError(state.messageRes)
            }
            is HeadlinesViewModel.EverythingData -> {
                lifecycleScope.launch {
                    listAdapter.submitData(viewLifecycleOwner.lifecycle, state.data)

                    listAdapter.removeLoadStateListener(loadStates)
                    listAdapter.addLoadStateListener(loadStates)
                }
            }
            else -> { } // do nothing
        }
    }

    private fun handleError(messageRes: Int) {
        if (snackbar?.isShown == true) return

        snackbar = Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            messageRes,
            BaseTransientBottomBar.LENGTH_INDEFINITE
        ).apply {
            this.setAction(R.string.try_again) {
                viewModel.getHeadlines()
            }
        }

        snackbar?.show()
    }

    private fun handleEmptyState(itemCount: Int) {
        snackbar?.dismiss()
        binding.emptyStateText.visibility = if (itemCount > 0) View.GONE else View.VISIBLE
    }
}
