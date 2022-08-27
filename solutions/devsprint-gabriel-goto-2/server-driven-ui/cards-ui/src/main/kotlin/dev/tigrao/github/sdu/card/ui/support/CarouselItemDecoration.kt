package dev.tigrao.github.sdu.card.ui.support

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.view.View
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.RecyclerView
import dev.tigrao.github.sdu.card.ui.R

internal class CarouselItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = view.context.getAttrDimen(R.attr.spacingXS)
    }

    fun Context.getAttrDimen(
        @AttrRes
        attr: Int
    ): Int {
        val textSizeAttr = intArrayOf(attr)
        val indexOfAttrTextSize = 0
        val a: TypedArray = obtainStyledAttributes(R.style.Theme_MyApp, textSizeAttr)
        val textSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1)
        return textSize.also {
                a.recycle()
            }
    }
}
