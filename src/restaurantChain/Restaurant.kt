 package restaurantChain

 import java.util.stream.Collectors

 class Restaurant(val name: String?, val nTables: Int) {

    val listing = mutableMapOf<String, Menu>()
    val prenotations = mutableMapOf<String, Int>()
    var busyTables: Int = 0
    var refused: Int = 0
    val orders = mutableMapOf<String, List<String?>>()
    val paid = mutableMapOf<String, Double>()

     @Throws (InvalidName::class)
    fun addMenu(name: String, price: Double) {
        if(listing.containsKey(name)){
            throw InvalidName()
        }
        listing.put(name , Menu(name, price))
    }

     @Throws (InvalidName::class)
     fun reserve(name: String, persons: Int): Int{
        if (prenotations.containsKey(name))
            throw InvalidName()
        val requiredTables = Math.ceil(((persons.toDouble()/4)))
        if (nTables - requiredTables - busyTables < 0){
            refused += persons
            return 0
        }
        busyTables += requiredTables.toInt()
        prenotations.put(name, persons)
        return requiredTables.toInt()
    }

    fun getUnusedTables() =  nTables - busyTables

     @Throws (InvalidName::class)
    fun order(name: String?, vararg menu : String? ): Boolean{
        if (!prenotations.containsKey(name))
            throw InvalidName()
        if (menu.size < prenotations[name] as Int)
            return false
        for (m in menu){
            if (!listing.containsKey(m))
                throw InvalidName()
        }
        if (name != null) {
            orders.put(name, menu.toList())
        }
        return true
    }

    fun getUnordered(): List<String>{
        return prenotations.keys.stream().filter({ o -> !orders.containsKey(o) }).sorted().collect(Collectors.toList())
    }

     @Throws (InvalidName::class)
    fun pay(name: String): Double{
        if (!prenotations.containsKey(name)) throw InvalidName()
        if (!orders.containsKey(name)) return 0.0
        var toPay = 0.0
        for (m in orders.get(name) as List){
            toPay += listing.get(m)?.price as Double
        }
        paid.put(name, toPay)
        return toPay
    }

     fun getUnpaid(): List<String>{
         return  orders.keys.filter { o -> !paid.containsKey(o) }.sorted().toList()
     }

     fun getIncome(): Double{
         return paid.values.stream().reduce(0.0, {x,y -> x+y})
     }
}