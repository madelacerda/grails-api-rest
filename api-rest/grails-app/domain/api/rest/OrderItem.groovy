package api.rest

class OrderItem {

    Integer qty
    Float total
    static belongsTo = [order:CustomerOrder, product:Product]
    static constraints = {
        qty(nullable: false)
        total(nullable: false)
    }
}
