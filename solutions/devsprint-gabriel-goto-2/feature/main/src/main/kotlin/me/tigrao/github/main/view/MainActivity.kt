package me.tigrao.github.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewArg
import by.kirich1409.viewbindingdelegate.viewBinding
import de.tigrao.github.sdu.card.model.CardModel
import dev.tigrao.github.sdu.card.processor.CardProcessor
import kotlinx.coroutines.launch
import me.tigrao.github.main.R
import me.tigrao.github.main.databinding.ActivityMainBinding
import me.tigrao.github.main.presenter.ProfileContract
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ProfileContract.View {

    @Inject
    internal lateinit var presenter: ProfileContract.Presenter
    @Inject
    internal lateinit var cardProcessor: CardProcessor

    private val binder by viewBinding(ActivityMainBinding::bind, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)

        setContentView(R.layout.activity_main)

        binder.swipe.isRefreshing = true
        prepareRecycler()

        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch {
            presenter.fetchProfileData()
        }
    }

    private fun fetchDataRefresh() {
        lifecycleScope.launch {
            presenter.forceRefresh()
        }
    }

    private fun prepareRecycler() {
        with(binder.recycler) {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        binder.swipe.setOnRefreshListener {
            fetchDataRefresh()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun setProfileData(cards: List<CardModel>) {
        cardProcessor.process(cards, binder.recycler)
        binder.swipe.isRefreshing = false
        binder.state.isVisible = false
        binder.recycler.isVisible = true
    }

    override fun setErrorState(state: StateViewArg) {
        binder.swipe.isRefreshing = false
        binder.state.isVisible = true
        binder.recycler.isVisible = false
        binder.state.prepareLayout(state)
    }
}
