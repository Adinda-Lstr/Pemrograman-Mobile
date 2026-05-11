package com.example.scrollablelistcompose.data

import com.example.scrollablelistcompose.R

data class Destination(
    val id: Int,
    val name: String,
    val rating: String,
    val location: String,
    val description: String,
    val imageResId: Int,
    val mapsUrl: String
)

object DestinationDataSource {
    val dummyDestinations = listOf(
        Destination(1, "Pasar Terapung",
            "★ 4.8",
            "Banjarmasin",
            "Belanja sayur dan buah unik langsung dari atas perahu klotok di Sungai Martapura. Ciri khas budaya sungai Kalimantan.",
            R.drawable.pasar_terapung,
            "geo:0,0?q=Pasar+Terapung+Lok+Baintan"),
        Destination(2, "Tahura Sultan Adam",
            "★ 4.6",
            "Kab. Banjar",
            "Menikmati sunrise dan lautan awan dari puncak bukit Mandiangin. Destinasi alam favorit untuk bersantai.",
            R.drawable.tahura,
            "geo:0,0?q=Tahura+Sultan+Adam+Mandiangin"),
        Destination(3, "Danau Seran",
            "★ 4.5",
            "Banjarbaru",
            "Eks area tambang intan yang kini jadi danau jernih dengan pulau kecil di tengahnya. Sangat cocok untuk bersantai.",
            R.drawable.danau_seran,
            "geo:0,0?q=Danau+Seran+Banjarbaru"),
        Destination(4, "Bukit Telang",
            "★ 4.7",
            "Pelaihari",
            "Trekking seru dengan pemandangan hamparan padang rumput hijau yang luas, mirip bukit Teletubbies.",
            R.drawable.bukit_telang,
            "geo:0,0?q=Bukit+Telang+Pelaihari"),
        Destination(5, "Pantai Gedambaan",
            "★ 4.4",
            "Kotabaru",
            "Pantai pasir putih yang bersih dengan ombak tenang dan fasilitas lengkap untuk liburan keluarga.",
            R.drawable.gedambaan, "geo:0,0?q=Pantai+Gedambaan+Kotabaru")
    )
}