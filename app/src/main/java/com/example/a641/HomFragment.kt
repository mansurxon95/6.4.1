package com.example.a641


import android.app.AlertDialog
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Intent
import android.database.Observable
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.a641.databinding.FragmentHomBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.Collections


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentHomBinding
    lateinit var adapterrv: Adapterrv

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHomBinding.inflate(inflater,container,false)

        adapterrv = Adapterrv(object : Adapterrv.OnClik{
            override fun clickmenu(contact: Contact, btn: View) {
                super.clickmenu(contact, btn)
            }

            override fun clickcall(contact: Contact) {
                super.clickcall(contact)
            }

            override fun clicksms(contact: Contact) {
                super.clicksms(contact)
            }


        })

        var list = ArrayList<Contact>()
        list.add(Contact(1,"mansur","+79781964731"))
        list.add(Contact(2,"mansur","+79781964731"))
        list.add(Contact(3,"mansur","+79781964731"))
        list.add(Contact(4,"mansur","+79781964731"))
        list.add(Contact(5,"mansur","+79781964731"))
        list.add(Contact(6,"mansur","+79781964731"))
        list.add(Contact(7,"mansur","+79781964731"))
        binding.rvView.adapter = adapterrv
        adapterrv.submitList(list)

        var itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                val sourcePosition = viewHolder.adapterPosition
                val targetPosition = target.adapterPosition
                Collections.swap(list,sourcePosition,targetPosition)
                adapterrv.notifyItemMoved(sourcePosition,targetPosition)

                return true

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }


            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean,
            ) {

                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.sms_color
                        )
                    )
                    .addSwipeRightBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.call_color
                        )
                    )
                    .addSwipeLeftActionIcon(R.drawable.group)
                    .addSwipeLeftActionIcon(R.drawable.frame)
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

        }).attachToRecyclerView(binding.rvView)

        return binding.root
    }

//    fun myMethod() {
//            askPermission(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.ACCESS_FINE_LOCATION) {
//                //all permissions already granted or just granted
//                //your action
//                resultView.setText("Accepted :${it.accepted}")
//            }.onDeclined { e ->
//                if (e.hasDenied()) {
//                    AppendText.appendText(resultView, "Denied :")
//                    //the list of denied permissions
//                    e.denied.forEach {
//                        AppendText.appendText(resultView, it)
//                    }
//
//                    AlertDialog.Builder(requireContext())
//                        .setMessage("Please accept our permissions")
//                        .setPositiveButton("yes") { dialog, which ->
//                            e.askAgain();
//                        } //ask again
//                        .setNegativeButton("no") { dialog, which ->
//                            dialog.dismiss();
//                        }
//                        .show();
//                }
//
//                if (e.hasForeverDenied()) {
//                    AppendText.appendText(resultView, "ForeverDenied :")
//                    //the list of forever denied permissions, user has check 'never ask again'
//                    e.foreverDenied.forEach {
//                        AppendText.appendText(resultView, it)
//                    }
//                    // you need to open setting manually if you really need it
//                    e.goToSettings();
//                }
//            }
//        }



//        fun getNamePhoneDetails(): ArrayList<List<String>>? {
//            val names = ArrayList<List<String>>()
//            val cr = binding.root.context.contentResolver
//            val cur = cr.query(
//                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//                    ContactsContract.CommonDataKinds.Phone.NUMBER ),
//                null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
//
//                while (cur!!.moveToNext()) {
//                    val id = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NAME_RAW_CONTACT_ID))
//                        val name = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
//                    val number = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                    names.add(listOf(id, name, number))
//                }
//
//            return names
//        }


        private fun requestPermissions() {
            // below line is use to request permission in the current activity.
            // this method is use to handle error in runtime permissions
            Dexter.withContext(binding.root.context)
                // below line is use to request the number of permissions which are required in our app.
                .withPermissions(android.Manifest.permission.WRITE_CONTACTS,
                    // below is the list of permissions
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.READ_CONTACTS)
                // after adding permissions we are calling an with listener method.
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                        // this method is called when all permissions are granted
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            // do you work now
                            Toast.makeText(binding.root.context, "Barcha ruxsatlar berilgan..", Toast.LENGTH_SHORT).show()
                        }
                        // check for permanent denial of any permission
                        if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied) {
                            // permission is denied permanently, we will show user a dialog message.
                            showSettingsDialog()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(list: List<PermissionRequest>, permissionToken: PermissionToken) {
                        // this method is called when user grants some permission and denies some of them.
                        permissionToken.continuePermissionRequest()
                    }
                }).withErrorListener {
                    // we are displaying a toast message for error message.
                    Toast.makeText(binding.root.context, "Error occurred! ", Toast.LENGTH_SHORT).show()
                }
                // below line is use to run the permissions on same thread and to check the permissions
                .onSameThread().check()
        }

        // below is the shoe setting dialog method
        // which is use to display a dialogue message.
        private fun showSettingsDialog() {
            // we are displaying an alert dialog for permissions
            val builder = AlertDialog.Builder(binding.root.context)

            // below line is the title for our alert dialog.
            builder.setTitle("Need Permissions")

            // below line is our message for our dialog
            builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
            builder.setPositiveButton("GOTO SETTINGS") { dialog, which ->
                // this method is called on click on positive button and on clicking shit button
                // we are redirecting our user from our app to the settings page of our app.
                dialog.cancel()
                // below is the intent from which we are redirecting our user.
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", null, "HomFragment")
                intent.data = uri
                startActivityForResult(intent, 101)
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                // this method is called when user click on negative button.
                dialog.cancel()
            }
            // below line is used to display our dialog
            builder.show()
        }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
