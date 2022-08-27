package br.com.hippopotamus.tabarato.designsystem.viewstate

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.tigrao.github.designsytstem.emptystate.databinding.ViewStateBinding

class StateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinder by viewBinding {
        ViewStateBinding.inflate(LayoutInflater.from(context), it)
    }

    var stateViewDispatchAction: StateViewActionDispatcher? = null

    init {
        viewBinder
    }

    fun prepareLayout(arg: StateViewArg) {
        viewBinder.txtViewStateTitle.text = arg.title

        arg.description?.let {
            viewBinder.txtViewStateDescription.text = it
            viewBinder.txtViewStateDescription.isVisible = true
        }

        applyButton(arg.positiveButton, viewBinder.btnViewStateAction)
        applyButton(arg.negativeButton, viewBinder.btnViewStateNegativeAction)

        viewBinder.imgViewStateIcon.setImageResource(arg.type.drawable)
    }

    private fun applyButton(button: ButtonViewArg?, view: Button) {
        button?.let {
            view.text = it.text
            view.setOnClickListener {
                stateViewDispatchAction?.dispatch(button.action)
            }
        }

        view.isVisible = button != null
    }

    override fun onViewRemoved(view: View?) {
        stateViewDispatchAction = null
        super.onViewRemoved(view)
    }
}
