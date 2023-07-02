package com.aerotech.flytix.model.penumpang

data class PenumpangData (
    var ktppaspor: String,
    var dateofbirth: String,
    var familyName: String,
    var citizenship: String,
    var name: String,
    var role: String,
)

data class PenumpangRequest(
    var ticketsId:String,
    var passengers:List<PenumpangData>,
    var total_passenger:Int
)


data class Penumpang(
    var penumpang:String,
    var role:String
)