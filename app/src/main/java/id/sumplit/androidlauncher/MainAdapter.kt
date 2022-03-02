package id.sumplit.androidlauncher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.sumplit.androidlauncher.databinding.ItemListBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ItemHolder>() {

    val listApp = ArrayList<ListApps>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(listApp[position])
    }

    override fun getItemCount() = listApp.size

    fun replace(apps: List<ListApps>) {
        listApp.clear()
        listApp.addAll(apps)
        notifyDataSetChanged()
    }

    class ItemHolder(val itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun bind(listApps: ListApps) {
            itemListBinding.apply {
                tvAppName.text = listApps.nameApp
                appIcon.setImageDrawable(listApps.iconApp)
                root.setOnClickListener {
                    val packageManager = root.context.packageManager
                    root.context.startActivity(
                        packageManager.getLaunchIntentForPackage(
                            listApps.packageApp ?: ""
                        )
                    )

                }
            }
        }
    }
}

