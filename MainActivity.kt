package com.rozkmin.recyclerdatabindingshowcase

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout.VERTICAL
import com.rozkmin.recyclerdatabindingshowcase.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    lateinit var binding: ActivityMainBinding

    companion object {
        const val myUserId = "id_123"
    }

    val presenter = Presenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setAdapter()
        presenter.attach(this)
    }


    var list = listOf<Item>(
            Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"),
            Item(UUID.randomUUID().toString(), "Edward", "Baker", 3, "Team KDE"),
            Item(myUserId, "Tony", "Ashenberg", 4, "Team xfce"),
            Item(UUID.randomUUID().toString(), "Dolan", "Dolański", 7, "Team KDE")
    )


    val itemAdapter = ItemAdapter()

    private fun setAdapter() {
        binding.activityMainRecycler.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        binding.activityMainRecycler.adapter = itemAdapter

        val viewModelList = list.map {
            ItemViewModel(it.position.toString(), "${it.firstName} ${it.lastName}", it.teamName, it.id.contentEquals(myUserId))
        }

        itemAdapter.items.addAll(viewModelList)
    }

    override fun addItem(item: Item) {
        itemAdapter.items
                .add(ItemViewModel(
                        item.position.toString(),
                        "${item.firstName} ${item.lastName}",
                        item.teamName,
                        item.id.contentEquals(myUserId)))
    }

    override fun addItems(itemList: MutableList<Item>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(throwable: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
