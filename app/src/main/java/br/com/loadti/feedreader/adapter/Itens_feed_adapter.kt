package br.com.loadti.feedreader.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.loadti.feedreader.R
import br.com.loadti.feedreader.data.ItemFeed
import br.com.loadti.feedreader.downloads.DownloadTask
import br.com.loadti.feedreader.util.Util
import kotlinx.android.synthetic.main.itens_feed.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Itens_feed_adapter(private var list: ArrayList<ItemFeed>, val context: Context) :
    RecyclerView.Adapter<Itens_feed_adapter.ItensViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItensViewHolder {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.itens_feed, parent, false)
        return ItensViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItensViewHolder, position: Int) {

        holder?.titulo?.text = list[position].titulo
        holder?.autor!!.text = list[position].autor
        holder?.data?.text =
            SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).format(Date(list[position].data))
        holder?.btnVerMais?.setOnClickListener {


            var intent = Intent(Intent.ACTION_VIEW, list[position].link)
            /*Verifica se existe algum app que irá capturar e executar a intent*/
            if (Util.existeIntentView(intent, context)) {

                context.startActivity(intent)

            } else {

                Toast.makeText(
                    context,
                    "Você não possui navegador web instalado. Instale para continuar.",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }

        /*Faz o download da imagem e exibe na imageview*/
        val downloadTask = DownloadTask()
        downloadTask.getBitmap(holder?.imagem!!, list[position].image!!, context)

    }

    override fun getItemCount(): Int = list?.size


    class ItensViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        var titulo = itemView.tv_itens_titulo
        var autor = itemView.tv_itens_autor
        var data = itemView.tv_itens_data
        var imagem = itemView.img_feed_itens
        var btnVerMais = itemView.btn_ver_mais
    }
}