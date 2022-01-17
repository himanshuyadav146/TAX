package dell.com.allindiaitr.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tax.R
import com.example.tax.models.ItrBaseModel
import dell.com.allindiaitr.holders.SourceOfIncomeHolder
import dell.com.allindiaitr.models.ITROption_Model
import retrofit2.Response
import com.example.tax.models.SourceOfIncome as SourceOfIncome


class SourceOfIncomeAdapter() : RecyclerView.Adapter<SourceOfIncomeHolder>() {

    lateinit var mContext: Context
    lateinit var mModelList: List<ITROption_Model>
    var itrBaseModel = ItrBaseModel.instance
    var sourceOfIncome= SourceOfIncome()
    private lateinit var holder:SourceOfIncomeHolder
    constructor(mContext: Context, mModelList: List<ITROption_Model>) : this() {
        this.mContext = mContext
        this.mModelList = mModelList
//        this.itrBaseModel.isSalary = "false"
//        this.itrBaseModel.isHouseProperty = "false"
//        this.itrBaseModel.isBusiness = "false"
//        this.itrBaseModel.isCapitalGain = "false"
//        this.itrBaseModel.isOtherSource = "false"
//        this.itrBaseModel.isForeignIncome = "false"
    }


//
    constructor(mContext: Context, mModelList: List<ITROption_Model>,newItrBaseModel: ItrBaseModel) : this() {
        this.mContext = mContext
        this.mModelList = mModelList
        this.itrBaseModel.setSourceOfIncome(newItrBaseModel.getSourceOfIncome())
//        this.newItrBase.isHouseProperty = newItrBase.isHouseProperty
//        this.newItrBase.isBusiness = newItrBase.isBusiness
//        this.newItrBase.isCapitalGain = newItrBase.isCapitalGain
//        this.newItrBase.isOtherSource = newItrBase.isOtherSource
//        this.newItrBase.isForeignIncome = newItrBase.isForeignIncome
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SourceOfIncomeHolder {
        return SourceOfIncomeHolder(
            LayoutInflater.from(mContext).inflate(R.layout.card_options, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return mModelList.size
    }

    override fun onBindViewHolder(p0: SourceOfIncomeHolder, p1: Int) {
        mModelList[0].isSelected = itrBaseModel.getSourceOfIncome()?.getIsSalary() == true
        mModelList[1].isSelected = itrBaseModel.getSourceOfIncome()?.getIsHouseProperty() == true
        mModelList[2].isSelected = itrBaseModel.getSourceOfIncome()?.getIsBusiness() == true
        mModelList[3].isSelected = itrBaseModel.getSourceOfIncome()?.getIsCapitalGain() == true
        mModelList[4].isSelected = itrBaseModel.getSourceOfIncome()?.getIsOtherSource() == true
        mModelList[5].isSelected = itrBaseModel.getSourceOfIncome()?.getIsForeignIncome() == true
        holder=p0
        p0.income_source_textView.text = mModelList[p1].text
        p0.income_cource_imageView.setImageResource(mModelList[p1].image!!)
        if (mModelList[p1].isSelected) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                p0.income_source_textView.setTextColor(mContext.getColor(R.color.white))
//                p0.card_options.setCardBackgroundColor(mContext.getColor(R.color.colorPrimary))
//                p0.income_cource_imageView.setColorFilter(mContext.getColor(R.color.white))
//            } else {
//                p0.income_source_textView.setTextColor(Color.parseColor("#FFFFFF"))
//                p0.card_options.setCardBackgroundColor(Color.parseColor("#00B894"))
//                p0.income_cource_imageView.setColorFilter(Color.parseColor("#FFFFFF"))
//            }
            p0.income_source_textView.typeface = Typeface.DEFAULT_BOLD
        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                p0.income_source_textView.setTextColor(mContext.getColor(R.color.dark_grey))
//                p0.card_options.setCardBackgroundColor(mContext.getColor(R.color.white))
//                p0.income_cource_imageView.setColorFilter(mContext.getColor(R.color.dark_grey))
//            } else {
//                p0.income_source_textView.setTextColor(Color.parseColor("#777777"))
//                p0.card_options.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
//                p0.income_cource_imageView.setColorFilter(Color.parseColor("#777777"))
//            }
            p0.income_source_textView.typeface = Typeface.DEFAULT
        }

        p0.itemView.setOnClickListener {
            mModelList[p1].isSelected = !mModelList[p1].isSelected
            if (mModelList[p1].isSelected) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    p0.income_source_textView.setTextColor(mContext.getColor(R.color.white))
//                    p0.card_options.setCardBackgroundColor(mContext.getColor(R.color.colorPrimary))
//                    p0.income_cource_imageView.setColorFilter(mContext.getColor(R.color.white))
//                } else {
//                    p0.income_source_textView.setTextColor(Color.parseColor("#FFFFFF"))
//                    p0.card_options.setCardBackgroundColor(Color.parseColor("#00B894"))
//                    p0.income_cource_imageView.setColorFilter(Color.parseColor("#FFFFFF"))
//                }
                p0.income_source_textView.typeface = Typeface.DEFAULT_BOLD
                itrBaseModel.getSourceOfIncome()?.setIsSalary(true)
                mModelList[p1].text?.let { it1 -> setSelectionIncomeSource(it1,true) }
                itrBaseModel.setSourceOfIncome(sourceOfIncome)
            } else {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    p0.income_source_textView.setTextColor(mContext.getColor(R.color.dark_grey))
//                    p0.card_options.setCardBackgroundColor(mContext.getColor(R.color.white))
//                    p0.income_cource_imageView.setColorFilter(mContext.getColor(R.color.dark_grey))
//                } else {
//                    p0.income_source_textView.setTextColor(Color.parseColor("#777777"))
//                    p0.card_options.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
//                    p0.income_cource_imageView.setColorFilter(Color.parseColor("#777777"))
//                }
                p0.income_source_textView.typeface = Typeface.DEFAULT
                mModelList[p1].text?.let { it1 -> setSelectionIncomeSource(it1,false) }
                itrBaseModel.setSourceOfIncome(sourceOfIncome)
            }
        }
    }

    public fun setModelData( sourceOfI: SourceOfIncome){
        if (sourceOfI.getIsSalary()!=null && sourceOfI.getIsSalary()!!){
            setSelectionIncomeSource("Salary/Pension", sourceOfI.getIsSalary()!!)
            holder.income_source_textView.typeface = Typeface.DEFAULT_BOLD
        }

        if (sourceOfI.getIsHouseProperty()!=null && sourceOfI.getIsHouseProperty()!!){
            setSelectionIncomeSource("House Property", sourceOfI.getIsHouseProperty()!!)
            holder.income_source_textView.typeface = Typeface.DEFAULT_BOLD
        }

        if (sourceOfI.getIsBusiness()!=null && sourceOfI.getIsBusiness()!!){
            setSelectionIncomeSource("Business/Profession", sourceOfI.getIsBusiness()!!)
            holder.income_source_textView.typeface = Typeface.DEFAULT_BOLD
        }

        if (sourceOfI.getIsCapitalGain()!=null && sourceOfI.getIsCapitalGain()!!){
            setSelectionIncomeSource("Capital Gains", sourceOfI.getIsCapitalGain()!!)
            holder.income_source_textView.typeface = Typeface.DEFAULT_BOLD
        }

        if (sourceOfI.getIsOtherSource()!=null && sourceOfI.getIsOtherSource()!!){
            setSelectionIncomeSource("Other Sources", sourceOfI.getIsOtherSource()!!)
            holder.income_source_textView.typeface = Typeface.DEFAULT_BOLD
        }

        if (sourceOfI.getIsForeignIncome()!=null && sourceOfI.getIsForeignIncome()!!){
            setSelectionIncomeSource("Foreign Income", sourceOfI.getIsForeignIncome()!!)
            holder.income_source_textView.typeface = Typeface.DEFAULT_BOLD
        }
    }

    private fun setSelectionIncomeSource(title: String, isSelect: Boolean) {
        when (title) {
            "Salary/Pension" -> sourceOfIncome.setIsSalary(isSelect)
            "House Property" -> sourceOfIncome.setIsHouseProperty(isSelect)
            "Business/Profession" -> sourceOfIncome.setIsBusiness(isSelect)
            "Capital Gains" -> sourceOfIncome.setIsCapitalGain(isSelect)
            "Other Sources" -> sourceOfIncome.setIsOtherSource(isSelect)
            "Foreign Income" -> sourceOfIncome.setIsForeignIncome(isSelect)
            else -> {

            }
        }
    }

}