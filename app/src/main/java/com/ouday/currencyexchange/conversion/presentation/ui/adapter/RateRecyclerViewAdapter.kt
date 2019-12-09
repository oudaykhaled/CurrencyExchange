package com.ouday.currencyexchange.conversion.presentation.ui.adapter

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding3.widget.textChanges
import com.ouday.currencyexchange.R
import com.ouday.currencyexchange.conversion.data.model.Currency
import com.ouday.currencyexchange.conversion.domain.CurrencyImageProvider
import com.ouday.currencyexchange.conversion.domain.ext.convertTo
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.row_currency.view.*
import kotlinx.android.synthetic.main.row_currency_base.view.*
import kotlinx.android.synthetic.main.row_currency_base.view.ivCurrency
import kotlinx.android.synthetic.main.row_currency_base.view.tvCurrency
import kotlinx.android.synthetic.main.row_currency_base.view.tvCurrencyDesc
import java.math.BigDecimal

class RateRecyclerViewAdapter : RecyclerView.Adapter<RateRecyclerViewAdapter.AbstractViewHolder>() {

    private val currencies = ArrayList<Currency>()
    private var baseCurrency: Currency? = null
    private var input: String? = null

    fun setCurrencies(currencies: List<Currency>) {
        this.currencies.clear()
        if (baseCurrency == null){
            baseCurrency = currencies[0]
        }
        this.currencies.addAll(currencies.subList(1, currencies.size))
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) R.layout.row_currency_base
        else R.layout.row_currency
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        if (viewType == R.layout.row_currency_base) {
            return PrimaryViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_currency_base,
                    parent,
                    false
                )
            ).bindEditText()
        } else {
            return SecondaryViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_currency,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return currencies.size + 1
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        baseCurrency?.let { curr ->
            input?.let { textInput ->
                holder.bind(
                    if (position == 0) this.baseCurrency else currencies[position - 1],
                    curr, textInput
                )
            }
        }
    }


    abstract class AbstractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun bind(currency: Currency?, baseCurrency: Currency, input: String) {
            itemView.tvCurrencyDesc.text = currency?.description
            itemView.tvCurrency.text = currency?.code?.toUpperCase()
            Glide.with(itemView.context)
                .load(currency?.code?.let { CurrencyImageProvider.loadImage(itemView.context, it) })
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .fitCenter()
                )
                .into(itemView.ivCurrency)
        }



    }

    inner class SecondaryViewHolder(itemView: View) :
        AbstractViewHolder(itemView) {

        override fun bind(currency: Currency?, base: Currency, input: String) {
            super.bind(currency, base, input)
            itemView.tvAmount.text =
                currency?.convertTo(safeNum(input), base)
                    ?.setScale(2, BigDecimal.ROUND_HALF_UP).toString()

            itemView.setOnClickListener {
                baseCurrency = currency
                currencies.remove(currency)
                currency?.let { it1 -> currencies.add(0, it1) }
                baseCurrency?.let { it1 -> currencies.add(1, it1) }
                setCurrencies(ArrayList(currencies))
            }
        }

        private fun safeNum(amount: String?): BigDecimal {
            return try {
                BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP)
            } catch (ex: Exception) {
                BigDecimal("1.0").setScale(2, BigDecimal.ROUND_HALF_UP)
            }
        }

    }

    inner class PrimaryViewHolder(itemView: View) : AbstractViewHolder(itemView) {

        private var compositeDisposable = CompositeDisposable()

        override fun bind(currency: Currency?, base: Currency, input: String) {
            super.bind(currency, base, input)
            itemView.etInput.requestFocus()
        }

        fun bindEditText(): PrimaryViewHolder {
            compositeDisposable.add(itemView.etInput.textChanges().subscribe { charSequence ->
                input =
                    charSequence.toString()
                Handler().postDelayed({
                    notifyItemRangeChanged(1, currencies.size -1)
                }, 100)

            })
            return this
        }


    }

}



