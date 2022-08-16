package com.example.heroapi.ui.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.heroapi.R
import com.example.heroapi.databinding.HeroDetailsFragmentBinding
import com.example.heroapi.model.HeroModel
import com.example.heroapi.model.PowerStatsModel
import com.example.heroapi.ui.viewmodel.HeroDetailsViewModel
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_details_fragment.*

class HeroDetailsFragment : Fragment() {
    private lateinit var hero: HeroModel
    private lateinit var binding: HeroDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HeroDetailsFragmentBinding.inflate(inflater)
        val view = binding.root
        hero = arguments?.getParcelable<HeroModel>("hero")!!

        binding.tvDetails.text = hero.name

        val url = hero.image.url
        Picasso.get()
            .load(url)
            .into(binding.ivDetails)

        initializeGrafic()
        initializeData()
        return view
    }

    private fun validatePoweStats(pw: PowerStatsModel) : PowerStatsModel{

        if(pw.combat == "null") pw.combat = "0"
        if (pw.durability == "null") pw.durability = "0"
        if (pw.intelligence == "null")pw.intelligence = "0"
        if (pw.power == "null") pw.power = "0"
        if (pw.speed == "null") pw.speed = "0"
        if (pw.strength == "null") pw.strength = "0"

        return pw
    }

    private fun initializeGrafic() {
        //X values
        var pw = hero.powerstats
        pw = validatePoweStats(pw)

        val xvalues = ArrayList<String>()
        xvalues.add("")
        //Y values
        val combat = ArrayList<BarEntry>()
        combat.add(BarEntry(pw.combat.toFloat(), 0))
        val durability = ArrayList<BarEntry>()
        durability.add(BarEntry(pw.durability.toFloat(), 0))
        val intelligence = ArrayList<BarEntry>()
        intelligence.add(BarEntry(pw.intelligence.toFloat(), 0))
        val power = ArrayList<BarEntry>()
        power.add(BarEntry(pw.power.toFloat(), 0))
        val speed = ArrayList<BarEntry>()
        speed.add(BarEntry(pw.speed.toFloat(), 0))
        val strength = ArrayList<BarEntry>()
        strength.add(BarEntry(pw.strength.toFloat(), 0))

        //bardata set
        val v1 = BarDataSet(durability, "Durability")
        val v2 = BarDataSet(combat, "Combat")
        val v3 = BarDataSet(intelligence, "intelligence")
        val v4 = BarDataSet(power, "power")
        val v5 = BarDataSet(speed, "speed")
        val v6 = BarDataSet(strength, "strength")
        v1.color = Color.BLUE
        v2.color = Color.MAGENTA
        v3.color = Color.CYAN
        v4.color = Color.RED
        v5.color = Color.YELLOW
        v6.color = Color.LTGRAY
        //make a bar datda
        val data = BarData(xvalues, v1)
        data.addDataSet(v2)
        data.addDataSet(v3)
        data.addDataSet(v4)
        data.addDataSet(v5)
        data.addDataSet(v6)


        binding.barChart.data =data

        binding.barChart.setBackgroundColor(resources.getColor(com.google.android.material.R.color.design_dark_default_color_on_background))
        binding.barChart.animateXY(3000,3000)
    }

    private fun initializeData(){

        //Biography
        binding.tvName.text = hero.biography.fullName
        binding.tvAlter.text = hero.biography.alteregos
        var alias = ""
        for (a in hero.biography.aliases){
            alias += "$a,"
        }
        binding.tvAlias.text = alias
        binding.tvNacimiento.text = hero.biography.placeOfBirth
        binding.tvAparicion.text = hero.biography.firstAppearance
        binding.tvPublisher.text = hero.biography.publisher
        binding.tvTeam.text = hero.biography.alignment
        //Appearence
        binding.tvSexo.text = hero.appearance.gender
        binding.tvRaza.text = hero.appearance.race
        var altura = ""
        for (al in hero.appearance.height){
            altura += "$al,"
        }
        binding.tvAltura.text = altura
        var peso = ""
        for (p in hero.appearance.weight){
            peso += "$p,"
        }
        binding.tvPeso.text = peso
        binding.tvOjos.text = hero.appearance.eyeColor
        binding.tvPelo.text = hero.appearance.hairColor
        //Work
        var work = ""
        work += hero.work.occupation + " Base: " + hero.work.base
        binding.tvWork.text = work
        //Conecctions
        binding.tvGroup.text = hero.connections.group
        binding.tvRelatives.text = hero.connections.relatives

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HeroDetailsFragment()

        @JvmStatic
        fun newInstance(bundle: Bundle) =
            HeroDetailsFragment()

    }

}