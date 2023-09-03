import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class DynamicDialogFragment : DialogFragment() {

    private var dialogLayoutId: Int = -1
    private var dialogView: View? = null
    private var dialogTitle: String? = null
    private var dismissDelayMillis: Long = -1 // Default: Dialog won't auto-dismiss

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dialogLayoutId = it.getInt(ARG_LAYOUT_ID)
            dialogTitle = it.getString(ARG_TITLE)
            dismissDelayMillis = it.getLong(ARG_DISMISS_DELAY, -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the custom dialog layout
        dialogView = inflater.inflate(dialogLayoutId, container, false)

        // You can customize the dialog content here, e.g., set text for TextViews, onClickListeners, etc.

        return dialogView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onResume() {
        super.onResume()
        if (dismissDelayMillis > 0) {
            // If a valid dismiss delay is set, start the dismiss timer
            Handler().postDelayed({
                dismiss()
            }, dismissDelayMillis)
        }
    }

    // Function to show the dialog for an Activity
    fun show(activity: Activity, tag: String? = null) {
        val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
        showInternal(fragmentManager, tag)
    }

    // Function to show the dialog for a Fragment
    fun show(fragment: Fragment, tag: String? = null) {
        val fragmentManager = fragment.childFragmentManager // Use childFragmentManager for AndroidX
        showInternal(fragmentManager, tag)
    }

    private fun showInternal(fragmentManager: FragmentManager, tag: String?) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(this, tag)
        transaction.commitAllowingStateLoss()
    }

    companion object {
        private const val ARG_LAYOUT_ID = "layout_id"
        private const val ARG_TITLE = "title"
        private const val ARG_DISMISS_DELAY = "dismiss_delay"

        @JvmStatic
        fun newInstance(
            layoutId: Int,
            title: String? = null,
            dismissDelayMillis: Long = -1 // Default: Dialog won't auto-dismiss
        ): DynamicDialogFragment {
            val fragment = DynamicDialogFragment()
            val args = Bundle()
            args.putInt(ARG_LAYOUT_ID, layoutId)
            args.putString(ARG_TITLE, title)
            args.putLong(ARG_DISMISS_DELAY, dismissDelayMillis)
            fragment.arguments = args
            return fragment
        }
    }
}
