package br.com.loadti.feedreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.loadti.feedreader.adapter.Itens_feed_adapter
import br.com.loadti.feedreader.data.ItemFeed
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Callback {

    val listItensFeed = arrayListOf<ItemFeed>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        inicialiarFeed()
        inicialiarRecyclerView()
    }

    /**/
    fun inicialiarRecyclerView() {

        with(rv_list) {
            rv_list

            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = Itens_feed_adapter(listItensFeed, this@MainActivity)
        }


    }


    //
    fun inicialiarFeed() =
        PkRSS.with(this).load("https://rss.tecmundo.com.br/feed").callback(this)
            .async()


    override fun onPreload() = Unit


    override fun onLoaded(newArticles: MutableList<Article>?) {

        try {

            newArticles?.mapTo(listItensFeed) {

                ItemFeed(it.title, it.author, it.date, it.source, it.enclosure.url)
            }

            rv_list?.adapter?.notifyDataSetChanged()

        } catch (e: Exception) {

            e.printStackTrace()
            Toast.makeText(this, "Erro em onLoaded ${e.message}", Toast.LENGTH_LONG).show()

        }


    }


    override fun onLoadFailed() = Unit
}