package com.bytedance.tools.codelocator.processor

import com.bytedance.tools.codelocator.model.EditModel
import com.bytedance.tools.codelocator.model.EditTextSizeModel
import com.bytedance.tools.codelocator.model.WView
import com.bytedance.tools.codelocator.utils.ResUtils
import com.intellij.openapi.project.Project

class TextSizeProcessor(project: Project, view: WView) : ViewValueProcessor(project, "TextSize", view) {

    override fun getHint(view: WView): String {
        return ResUtils.getString("edit_text_tip")
    }

    override fun isValid(changeText: String): Boolean {
        try {
            changeText.trim().toFloat()
        } catch (t: Throwable) {
            return false
        }
        return true
    }

    override fun getShowValue(view: WView): String = "${view.textSize}"

    override fun getChangeModel(view: WView, changeString: String): EditModel? {
        return EditTextSizeModel(changeString.trim().toFloat())
    }

}